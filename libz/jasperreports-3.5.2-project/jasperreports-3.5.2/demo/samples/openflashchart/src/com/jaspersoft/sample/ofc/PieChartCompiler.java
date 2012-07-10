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
package com.jaspersoft.sample.ofc;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRExpressionCollector;
import net.sf.jasperreports.engine.base.JRBaseObjectFactory;
import net.sf.jasperreports.engine.component.Component;
import net.sf.jasperreports.engine.component.ComponentCompiler;
import net.sf.jasperreports.engine.design.JRVerifier;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: PieChartCompiler.java 2692 2009-03-24 17:17:32Z teodord $
 */
public class PieChartCompiler implements ComponentCompiler
{

	public void collectExpressions(Component component, JRExpressionCollector collector)
	{
		PieChartComponent chart = (PieChartComponent) component;
		collector.addExpression(chart.getTitleExpression());
		collectExpressions(chart.getDataset(), collector);
	}

	public static void collectExpressions(PieDataset dataset, JRExpressionCollector collector)
	{
		collector.collect(dataset);
		
		JRExpressionCollector datasetCollector = collector.getCollector(dataset);
		datasetCollector.addExpression(dataset.getKeyExpression());
		datasetCollector.addExpression(dataset.getValueExpression());
	}

	public void verify(Component component, JRVerifier verifier)
	{
		PieChartComponent chart = (PieChartComponent) component;
		
		verifyEvaluation(verifier, chart);
		
		verifyTitleExpression(verifier, chart);
		
		PieDataset dataset = chart.getDataset();
		if (dataset == null)
		{
			verifier.addBrokenRule("No dataset for pie chart", chart);
		}
		else
		{
			verify(verifier, dataset);
		}
	}

	protected void verifyEvaluation(JRVerifier verifier,
			PieChartComponent chart)
	{
		byte evaluationTime = chart.getEvaluationTime();
		if (evaluationTime == JRExpression.EVALUATION_TIME_AUTO)
		{
			verifier.addBrokenRule("Pie chart evaluation time cannot be Auto", chart);
		}
		else if (evaluationTime == JRExpression.EVALUATION_TIME_GROUP)
		{
			String groupName = chart.getEvaluationGroup();
			if (groupName == null)
			{
				verifier.addBrokenRule("Evaluation group not set for pie chart", chart);
			}
			else
			{
				JasperDesign report = verifier.getReportDesign();
				if (!report.getGroupsMap().containsKey(groupName))
				{
					verifier.addBrokenRule("Pie chart evaluation group " + groupName 
							+ " not found in report", chart);
				}
			}
		}
	}

	protected void verifyTitleExpression(JRVerifier verifier,
			PieChartComponent chart)
	{
		JRExpression titleExpression = chart.getTitleExpression();
		if (titleExpression == null)
		{
			verifier.addBrokenRule("No title expression for pie chart", chart);
		}
		else
		{
			String valueClass = titleExpression.getValueClassName();
			if (valueClass == null)
			{
				verifier.addBrokenRule("No value class for pie chart title expression", 
						titleExpression);
			}
			else if (!"java.lang.String".equals(valueClass))
			{
				verifier.addBrokenRule("Class " + valueClass 
						+ " not supported for pie chart title expression. Use java.lang.String instead.",
						titleExpression);
			}
		}
	}

	protected void verify(JRVerifier verifier, PieDataset dataset)
	{
		verifier.verifyElementDataset(dataset);
		
		JRExpression keyExpression = dataset.getKeyExpression();
		if (keyExpression == null)
		{
			verifier.addBrokenRule("No key expression for pie chart dataset", dataset);
		}
		else
		{
			String valueClass = keyExpression.getValueClassName();
			if (valueClass == null)
			{
				verifier.addBrokenRule("No value class for key chart dataset key expression", 
						keyExpression);
			}
			else if (!"java.lang.String".equals(valueClass))
			{
				verifier.addBrokenRule("Class " + valueClass 
						+ " not supported for key chart dataset key expression. Use java.lang.String instead.",
						keyExpression);
			}
		}
		
		JRExpression valueExpression = dataset.getValueExpression();
		if (valueExpression == null)
		{
			verifier.addBrokenRule("No value expression for key chart dataset", dataset);
		}
		else
		{
			String valueClass = valueExpression.getValueClassName();
			if (valueClass == null)
			{
				verifier.addBrokenRule("No value class for key chart dataset value expression", 
						valueExpression);
			}
			else if (!"java.lang.Number".equals(valueClass))
			{
				verifier.addBrokenRule("Class " + valueClass 
						+ " not supported for key chart dataset value expression. Use java.lang.Number instead.",
						valueExpression);
			}
		}
	}

	public Component toCompiledComponent(Component component,
			JRBaseObjectFactory baseFactory)
	{
		PieChartComponent chart = (PieChartComponent) component;
		PieChartComponent compiledChart = new PieChartComponent(chart, baseFactory);
		return compiledChart;
	}
	
}
