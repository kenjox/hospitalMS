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
package net.sf.jasperreports.charts.util;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import net.sf.jasperreports.charts.ChartTheme;
import net.sf.jasperreports.charts.ChartThemeBundle;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintHyperlink;
import net.sf.jasperreports.engine.JRPrintImageArea;
import net.sf.jasperreports.engine.JRPrintImageAreaHyperlink;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.fill.DefaultChartTheme;
import net.sf.jasperreports.engine.util.JRProperties;
import net.sf.jasperreports.engine.util.JRSingletonCache;
import net.sf.jasperreports.extensions.ExtensionsEnvironment;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.EntityCollection;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: ChartUtil.java 2693 2009-03-24 17:38:19Z teodord $
 */
public class ChartUtil
{

	/**
	 *
	 */
	private static final JRSingletonCache CHART_RENDERER_FACTORY_CACHE = new JRSingletonCache(ChartRendererFactory.class);

	/**
	 * 
	 */
	public static List getImageAreaHyperlinks(
		JFreeChart chart,
		ChartHyperlinkProvider chartHyperlinkProvider,
		Graphics2D grx,
		Rectangle2D renderingArea
		)// throws JRException
	{
		List areaHyperlinks = null;
		
		if (chartHyperlinkProvider != null && chartHyperlinkProvider.hasHyperlinks())
		{
			ChartRenderingInfo renderingInfo = new ChartRenderingInfo();

			if (grx == null)
			{
				chart.createBufferedImage((int) renderingArea.getWidth(), (int)  renderingArea.getHeight(), renderingInfo);
			}
			else
			{
				chart.draw(grx, renderingArea, renderingInfo);
			}
			
			EntityCollection entityCollection = renderingInfo.getEntityCollection();
			if (entityCollection != null && entityCollection.getEntityCount() > 0)
			{
				areaHyperlinks = new ArrayList(entityCollection.getEntityCount());
				
				for (Iterator it = entityCollection.iterator(); it.hasNext();)
				{
					ChartEntity entity = (ChartEntity) it.next();
					JRPrintHyperlink printHyperlink = chartHyperlinkProvider.getEntityHyperlink(entity);
					if (printHyperlink != null)
					{
						JRPrintImageArea area = getImageArea(entity);

						JRPrintImageAreaHyperlink areaHyperlink = new JRPrintImageAreaHyperlink();
						areaHyperlink.setArea(area);
						areaHyperlink.setHyperlink(printHyperlink);
						areaHyperlinks.add(areaHyperlink);
					}
				}
			}
		}
		
		return areaHyperlinks;
	}

	private static JRPrintImageArea getImageArea(ChartEntity entity)
	{
		JRPrintImageArea area = new JRPrintImageArea();
		area.setShape(JRPrintImageArea.getShape(entity.getShapeType()));
		
		int[] coordinates = getCoordinates(entity);
		if (coordinates != null)
		{
			area.setCoordinates(coordinates);
		}
		return area;
	}
	
	private static int[] getCoordinates(ChartEntity entity)
	{
		int[] coordinates = null;
		String shapeCoords = entity.getShapeCoords();
		if (shapeCoords != null && shapeCoords.length() > 0)
		{
			StringTokenizer tokens = new StringTokenizer(shapeCoords, ",");
			coordinates = new int[tokens.countTokens()];
			int idx = 0;
			while (tokens.hasMoreTokens())
			{
				String coord = tokens.nextToken();
				coordinates[idx] = Integer.parseInt(coord);
				++idx;
			}
		}
		return coordinates;
	}

	/**
	 * 
	 */
	public static ChartTheme getChartTheme(String themeName)
	{
		if (themeName == null)
		{
			return new DefaultChartTheme();
		}

		List themeBundles = ExtensionsEnvironment.getExtensionsRegistry().getExtensions(ChartThemeBundle.class);
		for (Iterator it = themeBundles.iterator(); it.hasNext();)
		{
			ChartThemeBundle bundle = (ChartThemeBundle) it.next();
			ChartTheme chartTheme = bundle.getChartTheme(themeName);
			if (chartTheme != null)
			{
				return chartTheme;
			}
		}
		throw new JRRuntimeException("Chart theme '" + themeName + "' not found.");
	}

	/**
	 * 
	 */
	public static ChartRendererFactory getChartRendererFactory(String renderType)
	{
		ChartRendererFactory chartRendererFactory = null;

		String factoryClass = JRProperties.getProperty(ChartRendererFactory.PROPERTY_CHART_RENDERER_FACTORY_PREFIX + renderType);
		if (factoryClass == null)
		{
			throw new JRRuntimeException("No chart renderer factory specifyed for '" + renderType + "' render type.");
		}

		try
		{
			chartRendererFactory = (ChartRendererFactory) CHART_RENDERER_FACTORY_CACHE.getCachedInstance(factoryClass);
		}
		catch (JRException e)
		{
			throw new JRRuntimeException(e);
		}
		
		return chartRendererFactory;
	}

}
