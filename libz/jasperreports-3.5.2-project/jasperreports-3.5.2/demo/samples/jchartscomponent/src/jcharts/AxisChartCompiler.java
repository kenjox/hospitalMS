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
package jcharts;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRExpressionCollector;
import net.sf.jasperreports.engine.base.JRBaseObjectFactory;
import net.sf.jasperreports.engine.component.Component;
import net.sf.jasperreports.engine.component.ComponentCompiler;
import net.sf.jasperreports.engine.design.JRVerifier;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: AxisChartCompiler.java 2692 2009-03-24 17:17:32Z teodord $
 */
public class AxisChartCompiler implements ComponentCompiler
{

	public void collectExpressions(Component component, JRExpressionCollector collector)
	{
		AxisChartComponent chart = (AxisChartComponent) component;
		collector.addExpression(chart.getLegendLabelExpression());
		collectExpressions(chart.getDataset(), collector);
	}

	public static void collectExpressions(AxisDataset dataset, JRExpressionCollector collector)
	{
		collector.collect(dataset);
		
		JRExpressionCollector datasetCollector = collector.getCollector(dataset);
		datasetCollector.addExpression(dataset.getLabelExpression());
		datasetCollector.addExpression(dataset.getValueExpression());
	}

	public void verify(Component component, JRVerifier verifier)
	{
		AxisChartComponent chart = (AxisChartComponent) component;
		
		if (chart.getAreaColor() == null)
		{
			verifier.addBrokenRule("No area color set for axis chart", chart);
		}
		
		verifyEvaluation(verifier, chart);
		
		verifyLegendLabelExpression(verifier, chart);
		
		AxisDataset dataset = chart.getDataset();
		if (dataset == null)
		{
			verifier.addBrokenRule("No dataset for axis chart", chart);
		}
		else
		{
			verify(verifier, dataset);
		}
	}

	protected void verifyEvaluation(JRVerifier verifier,
			AxisChartComponent chart)
	{
		byte evaluationTime = chart.getEvaluationTime();
		if (evaluationTime == JRExpression.EVALUATION_TIME_AUTO)
		{
			verifier.addBrokenRule("Axis chart evaluation time cannot be Auto", chart);
		}
		else if (evaluationTime == JRExpression.EVALUATION_TIME_GROUP)
		{
			String groupName = chart.getEvaluationGroup();
			if (groupName == null)
			{
				verifier.addBrokenRule("Evaluation group not set for axis chart", chart);
			}
			else
			{
				JasperDesign report = verifier.getReportDesign();
				if (!report.getGroupsMap().containsKey(groupName))
				{
					verifier.addBrokenRule("Axis chart evaluation group " + groupName 
							+ " not found in report", chart);
				}
			}
		}
	}

	protected void verifyLegendLabelExpression(JRVerifier verifier,
			AxisChartComponent chart)
	{
		JRExpression legendLabelExpression = chart.getLegendLabelExpression();
		if (legendLabelExpression == null)
		{
			verifier.addBrokenRule("No legend label expression for axis chart", chart);
		}
		else
		{
			String valueClass = legendLabelExpression.getValueClassName();
			if (valueClass == null)
			{
				verifier.addBrokenRule("No value class for axis chart legend label expression", 
						legendLabelExpression);
			}
			else if (!"java.lang.String".equals(valueClass))
			{
				verifier.addBrokenRule("Class " + valueClass 
						+ " not supported for axis chart legend label expression. Use java.lang.String instead.",
						legendLabelExpression);
			}
		}
	}

	protected void verify(JRVerifier verifier, AxisDataset dataset)
	{
		verifier.verifyElementDataset(dataset);
		
		JRExpression labelExpression = dataset.getLabelExpression();
		if (labelExpression == null)
		{
			verifier.addBrokenRule("No label expression for axis chart dataset", dataset);
		}
		else
		{
			String valueClass = labelExpression.getValueClassName();
			if (valueClass == null)
			{
				verifier.addBrokenRule("No value class for axis chart dataset label expression", 
						labelExpression);
			}
			else if (!"java.lang.String".equals(valueClass))
			{
				verifier.addBrokenRule("Class " + valueClass 
						+ " not supported for axis chart dataset label expression. Use java.lang.String instead.",
						labelExpression);
			}
		}
		
		JRExpression valueExpression = dataset.getValueExpression();
		if (valueExpression == null)
		{
			verifier.addBrokenRule("No value expression for axis chart dataset", dataset);
		}
		else
		{
			String valueClass = valueExpression.getValueClassName();
			if (valueClass == null)
			{
				verifier.addBrokenRule("No value class for axis chart dataset value expression", 
						valueExpression);
			}
			else if (!"java.lang.Double".equals(valueClass))
			{
				verifier.addBrokenRule("Class " + valueClass 
						+ " not supported for axis chart dataset value expression. Use java.lang.Double instead.",
						valueExpression);
			}
		}
	}

	public Component toCompiledComponent(Component component,
			JRBaseObjectFactory baseFactory)
	{
		AxisChartComponent chart = (AxisChartComponent) component;
		AxisChartComponent compiledChart = new AxisChartComponent(chart, baseFactory);
		return compiledChart;
	}
	
}
