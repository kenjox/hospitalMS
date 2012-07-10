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
import net.sf.jasperreports.engine.component.XmlDigesterConfigurer;
import net.sf.jasperreports.engine.xml.JRExpressionFactory;

import org.apache.commons.digester.Digester;

/**
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ChartsDigester.java 2692 2009-03-24 17:17:32Z teodord $
 */
public class ChartsDigester implements XmlDigesterConfigurer
{

	public void configureDigester(Digester digester)
	{
		String axisChartPattern = "*/componentElement/axisChart";
		digester.addFactoryCreate(axisChartPattern, AxisChartXmlFactory.class.getName());
		
		String axisDatasetPattern = axisChartPattern + "/axisDataset";
		digester.addFactoryCreate(axisDatasetPattern, AxisDatasetXmlFactory.class.getName());
		digester.addSetNext(axisDatasetPattern, "setDataset", AxisDataset.class.getName());

		String labelExpressionPattern = axisDatasetPattern + "/labelExpression";
		digester.addFactoryCreate(labelExpressionPattern, JRExpressionFactory.StringExpressionFactory.class.getName());
		digester.addCallMethod(labelExpressionPattern, "setText", 0);
		digester.addSetNext(labelExpressionPattern, "setLabelExpression", JRExpression.class.getName());

		String valueExpressionPattern = axisDatasetPattern + "/valueExpression";
		digester.addFactoryCreate(valueExpressionPattern, JRExpressionFactory.DoubleExpressionFactory.class.getName());
		digester.addCallMethod(valueExpressionPattern, "setText", 0);
		digester.addSetNext(valueExpressionPattern, "setValueExpression", JRExpression.class.getName());

		String legendExpressionPattern = axisChartPattern + "/legendLabelExpression";
		digester.addFactoryCreate(legendExpressionPattern, JRExpressionFactory.StringExpressionFactory.class.getName());
		digester.addCallMethod(legendExpressionPattern, "setText", 0);
		digester.addSetNext(legendExpressionPattern, "setLegendLabelExpression", JRExpression.class.getName());
	}

}
