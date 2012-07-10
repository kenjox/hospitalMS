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

import net.sf.jasperreports.engine.JRComponentElement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRGenericElementType;
import net.sf.jasperreports.engine.JRGenericPrintElement;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.component.BaseFillComponent;
import net.sf.jasperreports.engine.component.FillPrepareResult;
import net.sf.jasperreports.engine.fill.JRFillCloneFactory;
import net.sf.jasperreports.engine.fill.JRFillCloneable;
import net.sf.jasperreports.engine.fill.JRFillObjectFactory;
import net.sf.jasperreports.engine.fill.JRTemplateGenericElement;
import net.sf.jasperreports.engine.fill.JRTemplateGenericPrintElement;

/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: FillBarChart.java 2692 2009-03-24 17:17:32Z teodord $
 */
public class FillBarChart extends BaseFillComponent implements JRFillCloneable
{

	public static final JRGenericElementType CHART_PRINT_TYPE = 
		new JRGenericElementType("http://jaspersoft.com/sample/openflashchart", "chart");
	
	private final BarChartComponent chart;
	private final FillBarDataset dataset;
	
	private String title;
	
	public FillBarChart(BarChartComponent chart, JRFillObjectFactory factory)
	{
		this.chart = chart;
		this.dataset = new FillBarDataset(chart.getDataset(), factory);
		factory.registerElementDataset(this.dataset);
	}

	protected boolean isEvaluateNow()
	{
		return chart.getEvaluationTime() == JRExpression.EVALUATION_TIME_NOW;
	}
	
	public void evaluate(byte evaluation) throws JRException
	{
		if (isEvaluateNow())
		{
			 evaluateChart(evaluation);
		}
	}

	protected void evaluateChart(byte evaluation) throws JRException
	{
		title = (String) fillContext.evaluate(chart.getTitleExpression(), evaluation);
		
		dataset.evaluateDatasetRun(evaluation);
	}

	public JRPrintElement fill()
	{
		JRComponentElement element = fillContext.getComponentElement();
		JRTemplateGenericElement template = new JRTemplateGenericElement(
				fillContext.getElementOrigin(), 
				fillContext.getDefaultStyleProvider(),
				CHART_PRINT_TYPE);
		
		JRTemplateGenericPrintElement printElement = new JRTemplateGenericPrintElement(template);
		printElement.setX(element.getX());
		printElement.setY(fillContext.getElementPrintY());
		printElement.setWidth(element.getWidth());
		printElement.setHeight(element.getHeight());

		if (isEvaluateNow())
		{
			copy(printElement);
		}
		else
		{
			fillContext.registerDelayedEvaluation(printElement, 
					chart.getEvaluationTime(), chart.getEvaluationGroup());
		}
		
		return printElement;
	}

	public FillPrepareResult prepare(int availableHeight)
	{
		return FillPrepareResult.PRINT_NO_STRETCH;
	}

	public JRFillCloneable createClone(JRFillCloneFactory factory)
	{
		throw new UnsupportedOperationException();
	}

	public void evaluateDelayedElement(JRPrintElement element, byte evaluation) throws JRException
	{
		evaluateChart(evaluation);
		copy((JRGenericPrintElement) element);
	}

	protected void copy(JRGenericPrintElement printElement)
	{
		dataset.finishDataset();
		
		String chartData = ChartDataGenerator.instance().generateBarChart(title, dataset);
		printElement.setParameterValue(ChartHtmlHandler.PARAMETER_CHART_DATA, chartData);
	}

}
