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
import net.sf.jasperreports.engine.xml.JRBaseFactory;
import net.sf.jasperreports.engine.xml.JRXmlConstants;

import org.xml.sax.Attributes;

/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: BarChartXmlFactory.java 2692 2009-03-24 17:17:32Z teodord $
 */
public class BarChartXmlFactory extends JRBaseFactory
{

	public Object createObject(Attributes attrs) throws Exception
	{
		BarChartComponent chart = new BarChartComponent();
		
		String evaluationAttr = attrs.getValue(JRXmlConstants.ATTRIBUTE_evaluationTime);
		if (evaluationAttr != null)
		{
			Byte evaluationTime = (Byte) JRXmlConstants.getEvaluationTimeMap().get(evaluationAttr);
			chart.setEvaluationTime(evaluationTime.byteValue());
		}

		if (chart.getEvaluationTime() == JRExpression.EVALUATION_TIME_GROUP)
		{
			String groupName = attrs.getValue(JRXmlConstants.ATTRIBUTE_evaluationGroup);
			chart.setEvaluationGroup(groupName);
		}
		
		return chart;
	}

}
