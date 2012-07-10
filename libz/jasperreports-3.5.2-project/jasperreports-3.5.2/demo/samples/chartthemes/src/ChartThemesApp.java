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
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.chartthemes.simple.AegeanSettingsFactory;
import net.sf.jasperreports.chartthemes.simple.EyeCandySixtiesSettingsFactory;
import net.sf.jasperreports.chartthemes.simple.SimpleSettingsFactory;
import net.sf.jasperreports.chartthemes.simple.XmlChartTheme;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.util.JRLoader;


/**
 * @author sanda zaharia (shertage@users.sourceforge.net)
 * @version $Id: ChartThemesApp.java 2692 2009-03-24 17:17:32Z teodord $
 */
public class ChartThemesApp
{


	/**
	 *
	 */
	private static final String TASK_THEMES = "themes";
	private static final String TASK_FILL = "fill";
	private static final String TASK_PDF = "pdf";
	private static final String TASK_HTML = "html";
	private static final String TASK_PRINT = "print";
	private static final String TASK_XML = "xml";
	private static final String TASK_XML_EMBED = "xmlEmbed";
	private static final String TASK_RTF = "rtf";
	private static final String TASK_XLS = "xls";
	private static final String TASK_JXL = "jxl";
	private static final String TASK_CSV = "csv";
	private static final String TASK_ODT = "odt";
	private static final String TASK_RUN = "run";
	
	/**
	 *
	 */
	public static void main(String[] args)
	{
		if(args.length == 0)
		{
			usage();
			return;
		}
				
		String taskName = args.length > 0 ? args[0] : null;
		String fileName = args.length > 1 ? args[1] : null;

		try
		{
			long start = System.currentTimeMillis();
			
			if (TASK_THEMES.equals(taskName))
			{
				XmlChartTheme.saveSettings(SimpleSettingsFactory.createChartThemeSettings(), new File(".\\src\\net\\sf\\jasperreports\\chartthemes\\simple\\simple.jrctx"));
				XmlChartTheme.saveSettings(EyeCandySixtiesSettingsFactory.createChartThemeSettings(), new File(".\\src\\net\\sf\\jasperreports\\chartthemes\\simple\\eye.candy.sixties.jrctx"));
				XmlChartTheme.saveSettings(AegeanSettingsFactory.createChartThemeSettings(), new File(".\\src\\net\\sf\\jasperreports\\chartthemes\\simple\\aegean.jrctx"));
				System.err.println("Theme saving time : " + (System.currentTimeMillis() - start));
			}
			else if (TASK_FILL.equals(taskName))
			{
				Map parameters = new HashMap();
				
				putDataSources(parameters);
				
				JasperFillManager.fillReportToFile(fileName, parameters, new JREmptyDataSource());
				System.err.println("Filling time : " + (System.currentTimeMillis() - start));
			}
			else if (TASK_PRINT.equals(taskName))
			{
				JasperPrintManager.printReport(fileName, true);
				System.err.println("Printing time : " + (System.currentTimeMillis() - start));
			}
			else if (TASK_PDF.equals(taskName))
			{
				JasperExportManager.exportReportToPdfFile(fileName);
				System.err.println("PDF creation time : " + (System.currentTimeMillis() - start));
			}
			else if (TASK_XML.equals(taskName))
			{
				JasperExportManager.exportReportToXmlFile(fileName, false);
				System.err.println("XML creation time : " + (System.currentTimeMillis() - start));
			}
			else if (TASK_XML_EMBED.equals(taskName))
			{
				JasperExportManager.exportReportToXmlFile(fileName, true);
				System.err.println("XML creation time : " + (System.currentTimeMillis() - start));
			}
			else if (TASK_HTML.equals(taskName))
			{
				JasperExportManager.exportReportToHtmlFile(fileName);
				System.err.println("HTML creation time : " + (System.currentTimeMillis() - start));
			}
			else if (TASK_RTF.equals(taskName))
			{
				File sourceFile = new File(fileName);
		
				JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);
		
				File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".rtf");
				
				JRRtfExporter exporter = new JRRtfExporter();
				
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
				
				exporter.exportReport();

				System.err.println("RTF creation time : " + (System.currentTimeMillis() - start));
			}
			else if (TASK_XLS.equals(taskName))
			{
				File sourceFile = new File(fileName);
				Map dateFormats = new HashMap();
				dateFormats.put("EEE, MMM d, yyyy", "ddd, mmm d, yyyy");
				JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);
				File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".xls");
				
				JRXlsExporter exporter = new JRXlsExporter();
				
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
				exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
				exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
				exporter.setParameter(JRXlsExporterParameter.FORMAT_PATTERNS_MAP, dateFormats);
				
				exporter.exportReport();

				System.err.println("XLS creation time : " + (System.currentTimeMillis() - start));
			}
			else if (TASK_JXL.equals(taskName))
			{
				File sourceFile = new File(fileName);

				JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);

				File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".jxl.xls");

				JExcelApiExporter exporter = new JExcelApiExporter();

				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
				exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);

				exporter.exportReport();

				System.err.println("XLS creation time : " + (System.currentTimeMillis() - start));
			}
			else if (TASK_CSV.equals(taskName))
			{
				File sourceFile = new File(fileName);
		
				JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);
		
				File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".csv");
				
				JRCsvExporter exporter = new JRCsvExporter();
				
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
				
				exporter.exportReport();

				System.err.println("CSV creation time : " + (System.currentTimeMillis() - start));
			}
			else if (TASK_ODT.equals(taskName))
			{
				File sourceFile = new File(fileName);

				JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);

				File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".odt");

				JROdtExporter exporter = new JROdtExporter();

				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());

				exporter.exportReport();

				System.err.println("ODT creation time : " + (System.currentTimeMillis() - start));
			}
			else if (TASK_RUN.equals(taskName))
			{
				JasperRunManager.runReportToPdfFile(fileName, new HashMap(), new JREmptyDataSource());
				System.err.println("PDF running time : " + (System.currentTimeMillis() - start));
			}
			else
			{
				usage();
			}
		
		}
		catch (JRException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	/**
	 *
	 */
	private static void usage()
	{
		System.out.println( "ChartThemesApp usage:" );
		System.out.println( "\tjava ChartThemesApp task file" );
		System.out.println( "\tTasks : themes | fill | print | pdf | xml | xmlEmbed | html | rtf | xls | jxl | csv | odt | run" );
	}


	/**
	 *
	 */
	public static final void putDataSources(Map parameters) throws UnsupportedEncodingException, JRException
	{
		JRCsvDataSource cds1 = new JRCsvDataSource(JRLoader.getLocationInputStream("datasources/categoryDatasource.csv"), "UTF-8");
		cds1.setRecordDelimiter("\r\n");
		cds1.setUseFirstRowAsHeader(true);
		parameters.put("categoryDatasource1", cds1);
		
		JRCsvDataSource cds2 = new JRCsvDataSource(JRLoader.getLocationInputStream("datasources/categoryDatasource.csv"), "UTF-8");
		cds2.setRecordDelimiter("\r\n");
		cds2.setUseFirstRowAsHeader(true);
		parameters.put("categoryDatasource2", cds2);
		
		JRCsvDataSource cds3 = new JRCsvDataSource(JRLoader.getLocationInputStream("datasources/categoryDatasource.csv"), "UTF-8");
		cds3.setRecordDelimiter("\r\n");
		cds3.setUseFirstRowAsHeader(true);
		parameters.put("categoryDatasource3", cds3);
		
		JRCsvDataSource cds4 = new JRCsvDataSource(JRLoader.getLocationInputStream("datasources/categoryDatasource.csv"), "UTF-8");
		cds4.setRecordDelimiter("\r\n");
		cds4.setUseFirstRowAsHeader(true);
		parameters.put("categoryDatasource4", cds4);
		
		JRCsvDataSource cds5 = new JRCsvDataSource(JRLoader.getLocationInputStream("datasources/categoryDatasource.csv"), "UTF-8");
		cds5.setRecordDelimiter("\r\n");
		cds5.setUseFirstRowAsHeader(true);
		parameters.put("categoryDatasource5", cds5);
		
		JRCsvDataSource cds6 = new JRCsvDataSource(JRLoader.getLocationInputStream("datasources/categoryDatasource.csv"), "UTF-8");
		cds6.setRecordDelimiter("\r\n");
		cds6.setUseFirstRowAsHeader(true);
		parameters.put("categoryDatasource6", cds6);
		
		JRCsvDataSource cds7 = new JRCsvDataSource(JRLoader.getLocationInputStream("datasources/categoryDatasource.csv"), "UTF-8");
		cds7.setRecordDelimiter("\r\n");
		cds7.setUseFirstRowAsHeader(true);
		parameters.put("categoryDatasource7", cds7);
		
		JRCsvDataSource pds1 = new JRCsvDataSource(JRLoader.getLocationInputStream("datasources/pieDatasource.csv"), "UTF-8");
		pds1.setRecordDelimiter("\r\n");
		pds1.setUseFirstRowAsHeader(true);
		parameters.put("pieDatasource1", pds1);
		
		JRCsvDataSource pds2 = new JRCsvDataSource(JRLoader.getLocationInputStream("datasources/pieDatasource.csv"), "UTF-8");
		pds2.setRecordDelimiter("\r\n");
		pds2.setUseFirstRowAsHeader(true);
		parameters.put("pieDatasource2", pds2);
		
		JRCsvDataSource tpds1 = new JRCsvDataSource(JRLoader.getLocationInputStream("datasources/timePeriodDatasource.csv"), "UTF-8");
		tpds1.setRecordDelimiter("\r\n");
		tpds1.setUseFirstRowAsHeader(true);
		parameters.put("timePeriodDatasource1", tpds1);
		
		JRCsvDataSource tsds1 = new JRCsvDataSource(JRLoader.getLocationInputStream("datasources/timeSeriesDatasource.csv"), "UTF-8");
		tsds1.setRecordDelimiter("\r\n");
		tsds1.setUseFirstRowAsHeader(true);
		tsds1.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
		parameters.put("timeSeriesDatasource1", tsds1);
		
		JRCsvDataSource tsds2 = new JRCsvDataSource(JRLoader.getLocationInputStream("datasources/timeSeriesDatasource.csv"), "UTF-8");
		tsds2.setRecordDelimiter("\r\n");
		tsds2.setUseFirstRowAsHeader(true);
		tsds2.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
		parameters.put("timeSeriesDatasource2", tsds2);
		
		JRCsvDataSource tsds3 = new JRCsvDataSource(JRLoader.getLocationInputStream("datasources/timeSeriesDatasource.csv"), "UTF-8");
		tsds3.setRecordDelimiter("\r\n");
		tsds3.setUseFirstRowAsHeader(true);
		tsds3.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
		parameters.put("timeSeriesDatasource3", tsds3);
		
		JRCsvDataSource xyds1 = new JRCsvDataSource(JRLoader.getLocationInputStream("datasources/xyDatasource.csv"), "UTF-8");
		xyds1.setRecordDelimiter("\r\n");
		xyds1.setUseFirstRowAsHeader(true);
		parameters.put("xyDatasource1", xyds1);
		
		JRCsvDataSource xyds2 = new JRCsvDataSource(JRLoader.getLocationInputStream("datasources/xyDatasource.csv"), "UTF-8");
		xyds2.setRecordDelimiter("\r\n");
		xyds2.setUseFirstRowAsHeader(true);
		parameters.put("xyDatasource2", xyds2);
		
		JRCsvDataSource xyds3 = new JRCsvDataSource(JRLoader.getLocationInputStream("datasources/xyDatasource.csv"), "UTF-8");
		xyds3.setRecordDelimiter("\r\n");
		xyds3.setUseFirstRowAsHeader(true);
		parameters.put("xyDatasource3", xyds3);
		
		JRCsvDataSource xyds4 = new JRCsvDataSource(JRLoader.getLocationInputStream("datasources/xyDatasource.csv"), "UTF-8");
		xyds4.setRecordDelimiter("\r\n");
		xyds4.setUseFirstRowAsHeader(true);
		parameters.put("xyDatasource4", xyds4);
		
		JRCsvDataSource xyds5 = new JRCsvDataSource(JRLoader.getLocationInputStream("datasources/xyDatasource.csv"), "UTF-8");
		xyds5.setRecordDelimiter("\r\n");
		xyds5.setUseFirstRowAsHeader(true);
		parameters.put("xyDatasource5", xyds5);
	}

}
