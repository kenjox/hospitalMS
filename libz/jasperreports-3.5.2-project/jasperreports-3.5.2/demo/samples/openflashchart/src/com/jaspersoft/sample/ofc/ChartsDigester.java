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
import net.sf.jasperreports.engine.component.XmlDigesterConfigurer;
import net.sf.jasperreports.engine.xml.JRExpressionFactory;

import org.apache.commons.digester.Digester;

/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ChartsDigester.java 2692 2009-03-24 17:17:32Z teodord $
 */
public class ChartsDigester implements XmlDigesterConfigurer
{

	public void configureDigester(Digester digester)
	{
		setPieChartRules(digester);
		setBarChartRules(digester);
	}

	protected void setBarChartRules(Digester digester)
	{
		String barChartPattern = "*/componentElement/barChart";
		digester.addFactoryCreate(barChartPattern, BarChartXmlFactory.class.getName());
		
		String titleExpressionPattern = barChartPattern + "/titleExpression";
		digester.addFactoryCreate(titleExpressionPattern, JRExpressionFactory.StringExpressionFactory.class.getName());
		digester.addCallMethod(titleExpressionPattern, "setText", 0);
		digester.addSetNext(titleExpressionPattern, "setTitleExpression", JRExpression.class.getName());
		
		String barDatasetPattern = barChartPattern + "/barDataset";
		digester.addObjectCreate(barDatasetPattern, DesignBarDataset.class.getName());
		digester.addSetNext(barDatasetPattern, "setDataset", BarDataset.class.getName());
		
		String barSeriesPattern = barDatasetPattern + "/barSeries";
		digester.addObjectCreate(barSeriesPattern, DefaultBarSeries.class.getName());
		digester.addSetNext(barSeriesPattern, "addSeries", BarSeries.class.getName());

		String seriesExpressionPattern = barSeriesPattern + "/seriesExpression";
		digester.addFactoryCreate(seriesExpressionPattern, JRExpressionFactory.StringExpressionFactory.class.getName());
		digester.addCallMethod(seriesExpressionPattern, "setText", 0);
		digester.addSetNext(seriesExpressionPattern, "setSeriesExpression", JRExpression.class.getName());

		String categoryExpressionPattern = barSeriesPattern + "/categoryExpression";
		digester.addFactoryCreate(categoryExpressionPattern, JRExpressionFactory.StringExpressionFactory.class.getName());
		digester.addCallMethod(categoryExpressionPattern, "setText", 0);
		digester.addSetNext(categoryExpressionPattern, "setCategoryExpression", JRExpression.class.getName());

		String valueExpressionPattern = barSeriesPattern + "/valueExpression";
		digester.addFactoryCreate(valueExpressionPattern, JRExpressionFactory.NumberExpressionFactory.class.getName());
		digester.addCallMethod(valueExpressionPattern, "setText", 0);
		digester.addSetNext(valueExpressionPattern, "setValueExpression", JRExpression.class.getName());
	}

	protected void setPieChartRules(Digester digester)
	{
		String pieChartPattern = "*/componentElement/pieChart";
		digester.addFactoryCreate(pieChartPattern, PieChartXmlFactory.class.getName());
		
		String pieDatasetPattern = pieChartPattern + "/pieDataset";
		digester.addObjectCreate(pieDatasetPattern, DesignPieDataset.class.getName());
		digester.addSetNext(pieDatasetPattern, "setDataset", PieDataset.class.getName());

		String keyExpressionPattern = pieDatasetPattern + "/keyExpression";
		digester.addFactoryCreate(keyExpressionPattern, JRExpressionFactory.StringExpressionFactory.class.getName());
		digester.addCallMethod(keyExpressionPattern, "setText", 0);
		digester.addSetNext(keyExpressionPattern, "setKeyExpression", JRExpression.class.getName());

		String valueExpressionPattern = pieDatasetPattern + "/valueExpression";
		digester.addFactoryCreate(valueExpressionPattern, JRExpressionFactory.NumberExpressionFactory.class.getName());
		digester.addCallMethod(valueExpressionPattern, "setText", 0);
		digester.addSetNext(valueExpressionPattern, "setValueExpression", JRExpression.class.getName());

		String titleExpressionPattern = pieChartPattern + "/titleExpression";
		digester.addFactoryCreate(titleExpressionPattern, JRExpressionFactory.StringExpressionFactory.class.getName());
		digester.addCallMethod(titleExpressionPattern, "setText", 0);
		digester.addSetNext(titleExpressionPattern, "setTitleExpression", JRExpression.class.getName());
	}

}
