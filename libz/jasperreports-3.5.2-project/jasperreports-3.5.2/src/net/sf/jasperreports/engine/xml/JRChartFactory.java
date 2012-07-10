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
package net.sf.jasperreports.engine.xml;

import java.awt.Color;
import java.util.Collection;

import net.sf.jasperreports.charts.util.JRAxisFormat;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.util.JRColorUtil;

import org.xml.sax.Attributes;


/**
 * @author Ionut Nedelcu (ionutned@users.sourceforge.net)
 * @version $Id: JRChartFactory.java 2697 2009-03-24 18:41:23Z teodord $
 */
public class JRChartFactory extends JRBaseFactory
{

	/**
	 *
	 */
	public Object createObject(Attributes atts)
	{
		JRXmlLoader xmlLoader = (JRXmlLoader)digester.peek(digester.getCount() - 1);
		Collection groupEvaluatedCharts = xmlLoader.getGroupEvaluatedCharts();

		JRDesignChart chart = (JRDesignChart) digester.peek();

		String isShowLegend = atts.getValue(JRXmlConstants.ATTRIBUTE_isShowLegend);
		if (isShowLegend != null && isShowLegend.length() > 0)
			chart.setShowLegend(Boolean.valueOf(isShowLegend));

		Byte evaluationTime = (Byte)JRXmlConstants.getEvaluationTimeMap().get(atts.getValue(JRXmlConstants.ATTRIBUTE_evaluationTime));
		if (evaluationTime != null)
		{
			chart.setEvaluationTime(evaluationTime.byteValue());
		}
		if (chart.getEvaluationTime() == JRExpression.EVALUATION_TIME_GROUP)
		{
			groupEvaluatedCharts.add(chart);

			String groupName = atts.getValue(JRXmlConstants.ATTRIBUTE_evaluationGroup);
			if (groupName != null)
			{
				JRDesignGroup group = new JRDesignGroup();
				group.setName(groupName);
				chart.setEvaluationGroup(group);
			}
		}

		chart.setLinkType(atts.getValue(JRXmlConstants.ATTRIBUTE_hyperlinkType));
		chart.setLinkTarget(atts.getValue(JRXmlConstants.ATTRIBUTE_hyperlinkTarget));

		String bookmarkLevelAttr = atts.getValue(JRXmlConstants.ATTRIBUTE_bookmarkLevel);
		if (bookmarkLevelAttr != null)
		{
			chart.setBookmarkLevel(Integer.parseInt(bookmarkLevelAttr));
		}

		String chartCustomizerClass = atts.getValue( JRXmlConstants.ATTRIBUTE_customizerClass );
		if( chartCustomizerClass != null && chartCustomizerClass.length() > 0 ){
			chart.setCustomizerClass(chartCustomizerClass);
		}
		
		chart.setRenderType(atts.getValue(JRXmlConstants.ATTRIBUTE_renderType));
		chart.setTheme(atts.getValue(JRXmlConstants.ATTRIBUTE_theme));
		
		return chart;
	}


	/**
	 *
	 */
	public static class JRChartTitleFactory extends JRBaseFactory
	{
		public Object createObject(Attributes atts)
		{
			JRDesignChart chart = (JRDesignChart) digester.peek();

			String position = atts.getValue(JRXmlConstants.ATTRIBUTE_position);
			if (position != null && position.length() > 0)
				chart.setTitlePosition(((Byte)JRXmlConstants.getChartEdgeMap().get(position)));


			Color color = JRColorUtil.getColor(atts.getValue(JRXmlConstants.ATTRIBUTE_color), Color.black);
			if (color != null)
			{
				chart.setTitleColor(color);
			}

			return chart;
		}
	}


	/**
	 *
	 */
	public static class JRChartSubtitleFactory extends JRBaseFactory
	{
		public Object createObject(Attributes atts)
		{
			JRDesignChart chart = (JRDesignChart) digester.peek();

			Color color = JRColorUtil.getColor(atts.getValue(JRXmlConstants.ATTRIBUTE_color), Color.black);
			if (color != null)
			{
				chart.setSubtitleColor(color);
			}

			return chart;
		}
	}


	/**
	 * A factory responsible for creating new chart legend formatting objects.
	 *
	 * @author Barry Klawans (bklawans@users.sourceforge.net)
	 */
	public static class JRChartLegendFactory extends JRBaseFactory
	{
		/**
		 *
		 */
		public Object createObject(Attributes atts) throws JRException
		{
			// Grab the chart from the object stack.
			JRDesignChart chart = (JRDesignChart)digester.peek();

			// Set the text color
			String attrValue = atts.getValue(JRXmlConstants.ATTRIBUTE_textColor);
			if (attrValue != null && attrValue.length() > 0)
			{
				Color color = JRColorUtil.getColor(attrValue, null);
				chart.setLegendColor(color);
			}

			// Set the background color
			attrValue = atts.getValue(JRXmlConstants.ATTRIBUTE_backgroundColor);
			if (attrValue != null && attrValue.length() > 0)
			{
				Color color = JRColorUtil.getColor(attrValue, null);
				chart.setLegendBackgroundColor(color);
			}

			String position = atts.getValue(JRXmlConstants.ATTRIBUTE_position);
			if (position != null && position.length() > 0)
				chart.setLegendPosition(((Byte)JRXmlConstants.getChartEdgeMap().get(position)));

			// Any font set will be put in the chart directly by the digester

			return chart;
		}
	}


	/**
	 * A factory responsible for creating new chart axis formatting objects.
	 *
	 * @author Barry Klawans (bklawans@users.sourceforge.net)
	 */
	public static class JRChartAxisFormatFactory extends JRBaseFactory
	{
		/**
		 *
		 */
		public Object createObject(Attributes atts) throws JRException
		{
			// Create an empty axis formatting object
			JRAxisFormat axisLabel = new JRAxisFormat();

			// Set the label color
			String attrValue = atts.getValue(JRXmlConstants.ATTRIBUTE_labelColor);
			if (attrValue != null && attrValue.length() > 0)
			{
				Color color = JRColorUtil.getColor(attrValue, null);
				axisLabel.setLabelColor(color);
			}

			// Set the tick label color
			attrValue = atts.getValue(JRXmlConstants.ATTRIBUTE_tickLabelColor);
			if (attrValue != null && attrValue.length() > 0)
			{
				Color color = JRColorUtil.getColor(attrValue, null);
				axisLabel.setTickLabelColor(color);
			}

			// Set the tick mask
			attrValue = atts.getValue(JRXmlConstants.ATTRIBUTE_tickLabelMask);
			if (attrValue != null && attrValue.length() > 0)
			{
				axisLabel.setTickLabelMask(attrValue);
			}

			// And finally set the axis line color
			attrValue = atts.getValue(JRXmlConstants.ATTRIBUTE_axisLineColor);
			if (attrValue != null && attrValue.length() > 0)
			{
				Color color = JRColorUtil.getColor(attrValue, null);
				axisLabel.setLineColor(color);
			}

			// Any fonts set will be put in the axis format object by the digester.

			return axisLabel;
		}
	}
}
