/*
 * ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * JasperReports - Free Java report-generating library.
 * Copyright (C) 2001-2009 JasperSoft Corporation http://www.jaspersoft.com
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 *
 * JasperSoft Corporation
 * 539 Bryant Street, Suite 100
 * San Francisco, CA 94107
 * http://www.jaspersoft.com
 */

/*
 * Contributors:
 * Greg Hilton
 */

package net.sf.jasperreports.engine.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRAlignment;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintEllipse;
import net.sf.jasperreports.engine.JRPrintFrame;
import net.sf.jasperreports.engine.JRPrintGraphicElement;
import net.sf.jasperreports.engine.JRPrintImage;
import net.sf.jasperreports.engine.JRPrintLine;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JRPrintRectangle;
import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.JRStyledTextAttributeSelector;
import net.sf.jasperreports.engine.JRTextElement;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.base.JRBasePrintText;
import net.sf.jasperreports.engine.util.JRProperties;
import net.sf.jasperreports.engine.util.JRStyledText;
import net.sf.jasperreports.engine.util.JRProperties.PropertySuffix;

/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRXlsAbstractExporter.java 2770 2009-05-07 11:57:53Z shertage $
 */
public abstract class JRXlsAbstractExporter extends JRAbstractExporter
{

	private static final String XLS_EXPORTER_PROPERTIES_PREFIX = JRProperties.PROPERTY_PREFIX + "export.xls.";

	
	protected static class TextAlignHolder
	{
		public final short horizontalAlignment;
		public final short verticalAlignment;
		public final short rotation;

		public TextAlignHolder(short horizontalAlignment, short verticalAlignment, short rotation)
		{
			this.horizontalAlignment = horizontalAlignment;
			this.verticalAlignment = verticalAlignment;
			this.rotation = rotation;
		}
	}

	/**
	 *
	 */
	protected List loadedFonts = new ArrayList();

	/**
	 *
	 */
	protected boolean isOnePagePerSheet;
	protected boolean isRemoveEmptySpaceBetweenRows;
	protected boolean isRemoveEmptySpaceBetweenColumns;
	protected boolean isWhitePageBackground;
	protected boolean isAutoDetectCellType = false;
	protected boolean isDetectCellType;
	protected boolean isFontSizeFixEnabled;
	protected boolean isImageBorderFixEnabled;
	protected boolean isIgnoreGraphics;
	protected boolean isCollapseRowSpan;
	protected boolean isIgnoreCellBorder;

	protected int maxRowsPerSheet;

	protected String[] sheetNames = null;


	protected JRExportProgressMonitor progressMonitor = null;

	protected int reportIndex = 0;


	protected Map fontMap = null;

	/**
	 *
	 */
	protected JRFont defaultFont = null;

	/**
	 * used for counting the total number of sheets
	 */
	protected int sheetIndex = 0;

	/**
	 * used when indexing the identical sheet generated names with ordering numbers;
	 * contains sheet names as keys and the number of occurences of each sheet name as values
	 */
	protected Map sheetNamesMap = null;
	protected String currentSheetName = null;

	protected boolean isIgnorePageMargins;

	/**
	 *
	 */
	protected JRFont getDefaultFont()
	{
		return defaultFont;
	}


	/**
	 *
	 */
	public void exportReport() throws JRException
	{
		progressMonitor = (JRExportProgressMonitor)parameters.get(JRExporterParameter.PROGRESS_MONITOR);

		/*   */
		setOffset();

		try
		{
			/*   */
			setExportContext();

			/*   */
			setInput();

			if (!parameters.containsKey(JRExporterParameter.FILTER))
			{
				filter = createFilter(XLS_EXPORTER_PROPERTIES_PREFIX);
			}
			
			/*   */
			if (!isModeBatch)
			{
				setPageRange();
			}

			setParameters();

			OutputStream os = (OutputStream)parameters.get(JRExporterParameter.OUTPUT_STREAM);
			if (os != null)
			{
				exportReportToStream(os);
			}
			else
			{
				File destFile = (File)parameters.get(JRExporterParameter.OUTPUT_FILE);
				if (destFile == null)
				{
					String fileName = (String)parameters.get(JRExporterParameter.OUTPUT_FILE_NAME);
					if (fileName != null)
					{
						destFile = new File(fileName);
					}
					else
					{
						throw new JRException("No output specified for the exporter.");
					}
				}

				try
				{
					os = new FileOutputStream(destFile);
					exportReportToStream(os);
					os.flush();
				}
				catch (IOException e)
				{
					throw new JRException("Error trying to export to file : " + destFile, e);
				}
				finally
				{
					if (os != null)
					{
						try
						{
							os.close();
						}
						catch(IOException e)
						{
						}
					}
				}
			}
		}
		finally
		{
			resetExportContext();
		}
	}

	protected void setParameters()
	{
		isOnePagePerSheet = 
			getBooleanParameter(
				JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET,
				JRXlsAbstractExporterParameter.PROPERTY_ONE_PAGE_PER_SHEET,
				false
				);

		isRemoveEmptySpaceBetweenRows = 
			getBooleanParameter(
				JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				JRXlsAbstractExporterParameter.PROPERTY_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				false
				);

		isRemoveEmptySpaceBetweenColumns = 
			getBooleanParameter(
				JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
				JRXlsAbstractExporterParameter.PROPERTY_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
				false
				);

		isWhitePageBackground = 
			getBooleanParameter(
				JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND,
				JRXlsAbstractExporterParameter.PROPERTY_WHITE_PAGE_BACKGROUND,
				false
				);
		setBackground();

		Boolean isAutoDetectCellTypeParameter = (Boolean)parameters.get(JRXlsAbstractExporterParameter.IS_AUTO_DETECT_CELL_TYPE);
		if (isAutoDetectCellTypeParameter != null)
		{
			isAutoDetectCellType = isAutoDetectCellTypeParameter.booleanValue();
		}

		isDetectCellType = 
			getBooleanParameter(
				JRXlsAbstractExporterParameter.IS_DETECT_CELL_TYPE,
				JRXlsAbstractExporterParameter.PROPERTY_DETECT_CELL_TYPE,
				false
				);

		isFontSizeFixEnabled = 
			getBooleanParameter(
				JRXlsAbstractExporterParameter.IS_FONT_SIZE_FIX_ENABLED,
				JRXlsAbstractExporterParameter.PROPERTY_FONT_SIZE_FIX_ENABLED,
				false
				);

		isImageBorderFixEnabled = 
			getBooleanParameter(
				JRXlsAbstractExporterParameter.IS_IMAGE_BORDER_FIX_ENABLED,
				JRXlsAbstractExporterParameter.PROPERTY_IMAGE_BORDER_FIX_ENABLED,
				false
				);
		
		isIgnoreGraphics = 
			getBooleanParameter(
				JRXlsAbstractExporterParameter.IS_IGNORE_GRAPHICS,
				JRXlsAbstractExporterParameter.PROPERTY_IGNORE_GRAPHICS,
				false
				);

		isCollapseRowSpan = 
			getBooleanParameter(
				JRXlsAbstractExporterParameter.IS_COLLAPSE_ROW_SPAN,
				JRXlsAbstractExporterParameter.PROPERTY_COLLAPSE_ROW_SPAN,
				false
				);

		isIgnoreCellBorder = 
			getBooleanParameter(
				JRXlsAbstractExporterParameter.IS_IGNORE_CELL_BORDER,
				JRXlsAbstractExporterParameter.PROPERTY_IGNORE_CELL_BORDER,
				false
				);

		Object names = getParameters(
				JRXlsAbstractExporterParameter.SHEET_NAMES,
				JRXlsAbstractExporterParameter.PROPERTY_SHEET_NAMES_PREFIX
				);
		if(names != null && names instanceof List && !((List)names).isEmpty())
		{
			List sheetNamesList = (List)names;
			int j = 0;
			sheetNames = new String[jasperPrint.getPages().size()];
			for(int i=0; i < sheetNamesList.size(); i++)
			{
				String[] currentSheetNamesArray = ((PropertySuffix)sheetNamesList.get(i)).getValue().split("/");
				for(int k = 0; k < currentSheetNamesArray.length && j < sheetNames.length ; k++, j++)
				{
					sheetNames[j] = currentSheetNamesArray[k];
				}
			
			}
			while(j < sheetNames.length)
			{
				sheetNames[j] = "Page "+ (++j);
			}
		}
		else if(names instanceof String[])
		{
			String[] sheetNamesArray = (String[])names;
			sheetNames = new String[jasperPrint.getPages().size()];
			int j = 0;
			for(int i = 0; i < sheetNamesArray.length; i++)
			{
				String[] currentSheetNamesArray = sheetNamesArray[i].split("/");
				for(int k = 0; k < currentSheetNamesArray.length && j < sheetNames.length ; k++, j++)
				{
					sheetNames[j] = currentSheetNamesArray[k];
				}
			}
			while(j < sheetNames.length)
			{
				sheetNames[j] = "Page "+ (++j);
			}
		}

		fontMap = (Map) parameters.get(JRExporterParameter.FONT_MAP);

		setHyperlinkProducerFactory();

		maxRowsPerSheet = 
			getIntegerParameter(
				JRXlsAbstractExporterParameter.MAXIMUM_ROWS_PER_SHEET,
				JRXlsAbstractExporterParameter.PROPERTY_MAXIMUM_ROWS_PER_SHEET,
				0
				);
		
		isIgnorePageMargins = 
			getBooleanParameter(
				JRExporterParameter.IGNORE_PAGE_MARGINS, 
				JRExporterParameter.PROPERTY_IGNORE_PAGE_MARGINS, 
				false
				); 
			
		
	}

	protected abstract void setBackground();

	protected void exportReportToStream(OutputStream os) throws JRException
	{
		openWorkbook(os);
		sheetNamesMap = new HashMap();
		sheetNamesMap.put("Page", new Integer(0)); // in order to skip first sheet name that would have no index

		for(reportIndex = 0; reportIndex < jasperPrintList.size(); reportIndex++)
		{
			setJasperPrint((JasperPrint)jasperPrintList.get(reportIndex));
			defaultFont = new JRBasePrintText(jasperPrint.getDefaultStyleProvider());

			List pages = jasperPrint.getPages();
			if (pages != null && pages.size() > 0)
			{
				if (isModeBatch)
				{
					startPageIndex = 0;
					endPageIndex = pages.size() - 1;
				}

				if (isOnePagePerSheet)
				{
					for(int pageIndex = startPageIndex; pageIndex <= endPageIndex; pageIndex++)
					{
						if (Thread.currentThread().isInterrupted())
						{
							throw new JRException("Current thread interrupted.");
						}

						JRPrintPage page = (JRPrintPage)pages.get(pageIndex);

						if (sheetNames != null && sheetIndex < sheetNames.length)
						{
							createSheet(getSheetName(sheetNames[sheetIndex]));
						}
						else
						{
							createSheet(getSheetName("Page"));
						}

						// we need to count all sheets generated for all exported documents
						sheetIndex++;

						/*   */
						exportPage(page, /*xCuts*/null, /*startRow*/0);
					}
				}
				else
				{
					// Create the sheet before looping.
					if (sheetNames != null && sheetIndex < sheetNames.length)
					{
						createSheet(getSheetName(sheetNames[sheetIndex]));
					}
					else
					{
						createSheet(getSheetName(jasperPrint.getName()));
					}

					// we need to count all sheets generated for all exported documents
					sheetIndex++;

					/*
					 * Make a pass and calculate the X cuts for all pages on this sheet.
					 * The Y cuts can be calculated as each page is exported.
					 */
					CutsInfo xCuts = 
						JRGridLayout.calculateXCuts(
							getNature(), pages, startPageIndex, endPageIndex,
							jasperPrint.getPageWidth(), globalOffsetX
							);
					//clear the filter's internal cache that might have built up
					if (filter instanceof ResetableExporterFilter)
						((ResetableExporterFilter)filter).reset();
					
					int startRow = 0;

					for(int pageIndex = startPageIndex; pageIndex <= endPageIndex; pageIndex++)
					{
						if (Thread.currentThread().isInterrupted())
						{
							throw new JRException("Current thread interrupted.");
						}
						JRPrintPage page = (JRPrintPage)pages.get(pageIndex);
						startRow = exportPage(page, xCuts, startRow);
					}
					
					if (isRemoveEmptySpaceBetweenColumns)
					{
						removeEmptyColumns(xCuts);
					}
				}
			}
		}

		closeWorkbook(os);
	}

	/**
	 *
	 * @return the number of rows added.
	 */
	protected int exportPage(JRPrintPage page, CutsInfo xCuts, int startRow) throws JRException
	{
		JRGridLayout layout =
			new JRGridLayout(
				getNature(),
				page.getElements(),
				jasperPrint.getPageWidth(),
				jasperPrint.getPageHeight(),
				globalOffsetX,
				globalOffsetY,
				xCuts
				);

		JRExporterGridCell grid[][] = layout.getGrid();

		boolean createXCuts = (xCuts == null); 
		
		if (createXCuts) 
		{
			xCuts = layout.getXCuts();
		}

		CutsInfo yCuts = layout.getYCuts();

		int skippedRows = 0;
		int rowIndex = startRow;
		boolean columnsWidthsToSet = true;
		for(int y = 0; y < grid.length; y++)
		{
			rowIndex = y - skippedRows + startRow;

			//if number of rows is too large a new sheet is created and populated with remaining rows
			if(
				(maxRowsPerSheet > 0 && rowIndex >= maxRowsPerSheet)
				|| yCuts.isBreak(y) 
				)
			{
				createSheet(getSheetName(currentSheetName));
				startRow = 0;
				rowIndex = 0;
				skippedRows = y;
				columnsWidthsToSet = true;
			}
			
			if (columnsWidthsToSet)
			{
				setColumnWidths(xCuts);
				columnsWidthsToSet = false;
			}

			if (
				yCuts.isCutNotEmpty(y)
				|| ((!isRemoveEmptySpaceBetweenRows || yCuts.isCutSpanned(y))
				&& !isCollapseRowSpan)
				)
			{
				JRExporterGridCell[] gridRow = grid[y];

				int emptyCellColSpan = 0;
				int emptyCellWidth = 0;

				setRowHeight(
					rowIndex,
					isCollapseRowSpan
						?  JRGridLayout.getMaxRowHeight(gridRow)
						: JRGridLayout.getRowHeight(gridRow)
					);

				int emptyCols = 0;
				for(int colIndex = 0; colIndex < gridRow.length; colIndex++)
				{
					emptyCols += (isRemoveEmptySpaceBetweenColumns && (!(xCuts.isCutNotEmpty(colIndex) || xCuts.isCutSpanned(colIndex))) ? 1 : 0);
					
					setCell(colIndex, rowIndex);

					JRExporterGridCell gridCell = gridRow[colIndex];
					if(gridCell.getWrapper() != null)
					{
						if (emptyCellColSpan > 0)
						{
							if (emptyCellColSpan > 1)
							{
								//sbuffer.append(" colspan=" + emptyCellColSpan);
								//sheet.addMergedRegion(new Region(y, (short)(x - emptyCellColSpan - 1), y, (short)(x - 1)));
							}
							emptyCellColSpan = 0;
							emptyCellWidth = 0;
						}

						JRPrintElement element = gridCell.getWrapper().getElement();

						if (element instanceof JRPrintLine)
						{
							exportLine((JRPrintLine)element, gridCell, colIndex, rowIndex);
						}
						else if (element instanceof JRPrintRectangle)
						{
							exportRectangle((JRPrintRectangle)element, gridCell, colIndex, rowIndex);
						}
						else if (element instanceof JRPrintEllipse)
						{
							exportRectangle((JRPrintEllipse)element, gridCell, colIndex, rowIndex);
						}
						else if (element instanceof JRPrintImage)
						{
							exportImage((JRPrintImage) element, gridCell, colIndex, rowIndex, emptyCols);
						}
						else if (element instanceof JRPrintText)
						{
							exportText((JRPrintText)element, gridCell, colIndex, rowIndex);
						}
						else if (element instanceof JRPrintFrame)
						{
							exportFrame((JRPrintFrame) element, gridCell, colIndex, y);//FIXME rowIndex?
						}

						colIndex += gridCell.getColSpan() - 1;
					}
					else
					{
						emptyCellColSpan++;
						emptyCellWidth += gridCell.getWidth();
						addBlankCell(gridCell, colIndex, rowIndex);
					}
				}

				if (emptyCellColSpan > 0)
				{
					if (emptyCellColSpan > 1)
					{
						//sbuffer.append(" colspan=" + emptyCellColSpan);
						//sheet.addMergedRegion(new Region(y, (short)x, y, (short)(x + emptyCellColSpan - 1)));
					}
				}
				
				//increment row index to return proper value
				++rowIndex;
			}
			else
			{
				skippedRows++;
//				setRowHeight(y, 0);
//
//				for(int x = 0; x < grid[y].length; x++)
//				{
//					addBlankCell(x, y);
//					setCell(x, y);
//				}
			}
		}

		if (createXCuts && isRemoveEmptySpaceBetweenColumns)
		{
			removeEmptyColumns(xCuts);
		}
		
		if (progressMonitor != null)
		{
			progressMonitor.afterPageExport();
		}

		// Return the number of rows added
		return rowIndex;
	}


	protected void setColumnWidths(CutsInfo xCuts)
	{
		for(int col = 0; col < xCuts.size() - 1; col++)
		{
			if (!isRemoveEmptySpaceBetweenColumns || (xCuts.isCutNotEmpty(col) || xCuts.isCutSpanned(col)))
			{
				int width = xCuts.getCut(col + 1) - xCuts.getCut(col);
				setColumnWidth((short)(col), (short)(width * 43));
			}
		}
	}

	protected void removeEmptyColumns(CutsInfo xCuts)
	{
		for(int col = xCuts.size() - 1; col >= 0; col--)
		{
			if (!(xCuts.isCutNotEmpty(col) || xCuts.isCutSpanned(col)))
			{
				removeColumn(col);
			}
		}
	}

	/**
	 *
	 */
	protected JRStyledText getStyledText(JRPrintText textElement)
	{
		return textElement.getFullStyledText(JRStyledTextAttributeSelector.NONE);
	}

	/**
	 *
	 */
	protected static TextAlignHolder getTextAlignHolder(JRPrintText textElement)
	{
		short horizontalAlignment;
		short verticalAlignment;
		short rotation = textElement.getRotation();

		switch (textElement.getRotation())
		{
			case JRTextElement.ROTATION_LEFT :
			{
				switch (textElement.getHorizontalAlignment())
				{
					case JRAlignment.HORIZONTAL_ALIGN_LEFT :
					{
						verticalAlignment = JRAlignment.VERTICAL_ALIGN_BOTTOM;
						break;
					}
					case JRAlignment.HORIZONTAL_ALIGN_CENTER :
					{
						verticalAlignment = JRAlignment.VERTICAL_ALIGN_MIDDLE;
						break;
					}
					case JRAlignment.HORIZONTAL_ALIGN_RIGHT :
					{
						verticalAlignment = JRAlignment.VERTICAL_ALIGN_TOP;
						break;
					}
					case JRAlignment.HORIZONTAL_ALIGN_JUSTIFIED :
					{
						verticalAlignment = JRAlignment.VERTICAL_ALIGN_JUSTIFIED;
						break;
					}
					default :
					{
						verticalAlignment = JRAlignment.VERTICAL_ALIGN_BOTTOM;
					}
				}

				switch (textElement.getVerticalAlignment())
				{
					case JRAlignment.VERTICAL_ALIGN_TOP :
					{
						horizontalAlignment = JRAlignment.HORIZONTAL_ALIGN_LEFT;
						break;
					}
					case JRAlignment.VERTICAL_ALIGN_MIDDLE :
					{
						horizontalAlignment = JRAlignment.HORIZONTAL_ALIGN_CENTER;
						break;
					}
					case JRAlignment.VERTICAL_ALIGN_BOTTOM :
					{
						horizontalAlignment = JRAlignment.HORIZONTAL_ALIGN_RIGHT;
						break;
					}
					default :
					{
						horizontalAlignment = JRAlignment.HORIZONTAL_ALIGN_LEFT;
					}
				}

				break;
			}
			case JRTextElement.ROTATION_RIGHT :
			{
				switch (textElement.getHorizontalAlignment())
				{
					case JRAlignment.HORIZONTAL_ALIGN_LEFT :
					{
						verticalAlignment = JRAlignment.VERTICAL_ALIGN_TOP;
						break;
					}
					case JRAlignment.HORIZONTAL_ALIGN_CENTER :
					{
						verticalAlignment = JRAlignment.VERTICAL_ALIGN_MIDDLE;
						break;
					}
					case JRAlignment.HORIZONTAL_ALIGN_RIGHT :
					{
						verticalAlignment = JRAlignment.VERTICAL_ALIGN_BOTTOM;
						break;
					}
					case JRAlignment.HORIZONTAL_ALIGN_JUSTIFIED :
					{
						verticalAlignment = JRAlignment.VERTICAL_ALIGN_JUSTIFIED;
						break;
					}
					default :
					{
						verticalAlignment = JRAlignment.VERTICAL_ALIGN_TOP;
					}
				}

				switch (textElement.getVerticalAlignment())
				{
					case JRAlignment.VERTICAL_ALIGN_TOP :
					{
						horizontalAlignment = JRAlignment.HORIZONTAL_ALIGN_RIGHT;
						break;
					}
					case JRAlignment.VERTICAL_ALIGN_MIDDLE :
					{
						horizontalAlignment = JRAlignment.HORIZONTAL_ALIGN_CENTER;
						break;
					}
					case JRAlignment.VERTICAL_ALIGN_BOTTOM :
					{
						horizontalAlignment = JRAlignment.HORIZONTAL_ALIGN_LEFT;
						break;
					}
					default :
					{
						horizontalAlignment = JRAlignment.HORIZONTAL_ALIGN_RIGHT;
					}
				}

				break;
			}
			case JRTextElement.ROTATION_UPSIDE_DOWN:
			case JRTextElement.ROTATION_NONE :
			default :
			{
				horizontalAlignment = textElement.getHorizontalAlignment();
				verticalAlignment = textElement.getVerticalAlignment();
			}
		}

		return new TextAlignHolder(horizontalAlignment, verticalAlignment, rotation);
	}

	/**
	 *
	 */
	protected int getImageBorderCorrection(JRPen pen)
	{
		float lineWidth = pen.getLineWidth().floatValue();
		
		if (lineWidth > 0f)
		{
			if (lineWidth >= 2f)
			{
				return 2;
			}

			return 1;
		}
		
		return isImageBorderFixEnabled ? 1 : 0;
	}

	
	/**
	 *
	 */
	private String getSheetName(String sheetName)
	{
		currentSheetName = sheetName;

		// sheet names must be unique
		if(!sheetNamesMap.containsKey(sheetName))
		{
			// first time this sheet name is found;
			sheetNamesMap.put(sheetName, new Integer(1));
			return sheetName;
		}

		int currentIndex = ((Integer)sheetNamesMap.get(sheetName)).intValue() + 1;
		sheetNamesMap.put(sheetName, new Integer(currentIndex));

		return sheetName + " " + currentIndex;
	}

	// Berechnungsvorschriften f�r die DIN Formate A, B, und C.
	// Die Angabe der Breite/H�he erfolgt in [mm].

	protected final int calculateWidthForDinAN(int n)
	{
		return (int) (Math.pow(2.0, (-0.25 - (n / 2.0))) * 1000.0);
	}

	protected final int calculateHeightForDinAN(int n)
	{
		return (int) (Math.pow(2.0, (0.25 - (n / 2.0))) * 1000.0);
	}

	protected final int calculateWidthForDinBN(int n)
	{
		return (int) (Math.pow(2.0, -(n / 2.0)) * 1000.0);
	}

	protected final int calculateHeightForDinBN(int n)
	{
		return (int) (Math.pow(2.0, (0.5 - (n / 2.0))) * 1000.0);
	}

	protected final int calculateWidthForDinCN(int n)
	{
		return (int) (Math.pow(2.0, (-0.125 - (n / 2.0))) * 1000.0);
	}

	protected final int calculateHeightForDinCN(int n)
	{
		return (int) (Math.pow(2.0, (0.375 - (n / 2.0))) * 1000.0);
	}

	protected abstract ExporterNature getNature();

	protected abstract void openWorkbook(OutputStream os) throws JRException;

	protected abstract void createSheet(String name);

	protected abstract void closeWorkbook(OutputStream os) throws JRException;

	protected abstract void setColumnWidth (int col, int width);

	protected abstract void removeColumn (int col);

	protected abstract void setRowHeight(int rowIndex, int lastRowHeight) throws JRException;

	protected abstract void setCell(int colIndex, int rowIndex);

	protected abstract void addBlankCell(JRExporterGridCell gridCell, int colIndex, int rowIndex) throws JRException;

	protected abstract void exportText(JRPrintText text, JRExporterGridCell cell, int colIndex, int rowIndex) throws JRException;

	protected abstract void exportImage(JRPrintImage image, JRExporterGridCell cell, int colIndex, int rowIndex, int emptyCols) throws JRException;

	protected abstract void exportRectangle(JRPrintGraphicElement element, JRExporterGridCell cell, int colIndex, int rowIndex) throws JRException;

	protected abstract void exportLine(JRPrintLine line, JRExporterGridCell cell, int colIndex, int rowIndex) throws JRException;

	protected abstract void exportFrame(JRPrintFrame frame, JRExporterGridCell cell, int colIndex, int rowIndex) throws JRException;
}
