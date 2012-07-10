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
 * Eugene D - eugenedruy@users.sourceforge.net 
 * Adrian Jackson - iapetus@users.sourceforge.net
 * David Taylor - exodussystems@users.sourceforge.net
 * Lars Kristensen - llk@users.sourceforge.net
 */
package net.sf.jasperreports.engine.convert;

import java.awt.geom.Rectangle2D;

import net.sf.jasperreports.charts.ChartContext;
import net.sf.jasperreports.charts.ChartTheme;
import net.sf.jasperreports.charts.util.ChartUtil;
import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRImage;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRRenderable;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.base.JRBasePrintImage;
import net.sf.jasperreports.engine.util.JRExpressionUtil;
import net.sf.jasperreports.engine.util.JRProperties;

import org.jfree.chart.JFreeChart;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: ChartConverter.java 2694 2009-03-24 18:11:24Z teodord $
 */
public class ChartConverter extends ElementConverter
{

	/**
	 *
	 */
	private final static ChartConverter INSTANCE = new ChartConverter();
	
	/**
	 *
	 */
	private ChartConverter()
	{
	}

	/**
	 *
	 */
	public static ChartConverter getInstance()
	{
		return INSTANCE;
	}
	
	/**
	 *
	 */
	public JRPrintElement convert(ReportConverter reportConverter, JRElement element)
	{
		JRBasePrintImage printImage = new JRBasePrintImage(reportConverter.getDefaultStyleProvider());
		JRChart chart = (JRChart)element;

		copyElement(reportConverter, chart, printImage);
		
		printImage.copyBox(chart.getLineBox());
		
		printImage.setAnchorName(JRExpressionUtil.getExpressionText(chart.getAnchorNameExpression()));
		printImage.setBookmarkLevel(chart.getBookmarkLevel());
		printImage.setLinkType(chart.getLinkType());
		printImage.setOnErrorType(JRImage.ON_ERROR_TYPE_ICON);
		printImage.setRenderer(getRenderer(reportConverter, chart));
		printImage.setScaleImage(JRImage.SCALE_IMAGE_CLIP);
		
		return printImage;
	}

	/**
	 * 
	 */
	private JRRenderable getRenderer(ReportConverter reportConverter, JRChart chart)
	{
		String renderType = chart.getRenderType();//FIXMETHEME try reuse this sequence
		if(renderType == null)
		{
			renderType = JRProperties.getProperty(reportConverter.getReport(), JRChart.PROPERTY_CHART_RENDER_TYPE);
		}
		
		String themeName = chart.getTheme();
		if(themeName == null)
		{
			themeName = JRProperties.getProperty(reportConverter.getReport(), JRChart.PROPERTY_CHART_THEME);
		}
		
		ChartTheme theme = ChartUtil.getChartTheme(themeName);
		
		ChartContext chartContext = new ConvertChartContext(chart);
		
		JFreeChart jfreeChart = null;
		try
		{
			jfreeChart = theme.createChart(chartContext);
		}
		catch (JRException e)
		{
			throw new JRRuntimeException(e);
		}
		
		Rectangle2D rectangle = new Rectangle2D.Double(0, 0, chart.getWidth(), chart.getHeight());

		return 
			ChartUtil.getChartRendererFactory(renderType).getRenderer(
				jfreeChart, 
				null,
				rectangle
				);
	}
}
