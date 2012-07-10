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
package net.sf.jasperreports.components.barbecue;

import net.sf.jasperreports.engine.component.Component;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRExpressionCollector;
import net.sf.jasperreports.engine.base.JRBaseObjectFactory;
import net.sf.jasperreports.engine.component.ComponentCompiler;
import net.sf.jasperreports.engine.design.JRVerifier;

/**
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: BarbecueCompiler.java 2787 2009-05-18 15:57:22Z lucianc $
 */
public class BarbecueCompiler implements ComponentCompiler
{
	
	public void collectExpressions(Component component, JRExpressionCollector collector)
	{
		BarbecueComponent barcode = (BarbecueComponent) component;
		collector.addExpression(barcode.getCodeExpression());
		collector.addExpression(barcode.getApplicationIdentifierExpression());
	}

	public Component toCompiledComponent(Component component,
			JRBaseObjectFactory baseFactory)
	{
		BarbecueComponent barcode = (BarbecueComponent) component;
		StandardBarbecueComponent compiledBarcode = new StandardBarbecueComponent(barcode, baseFactory);
		return compiledBarcode;
	}

	public void verify(Component component, JRVerifier verifier)
	{
		BarbecueComponent barcode = (BarbecueComponent) component;
		
		String type = barcode.getType();
		if (type == null)
		{
			verifier.addBrokenRule("No barcode type set", barcode);
		}
		
		JRExpression codeExpression = barcode.getCodeExpression();
		if (codeExpression == null)
		{
			verifier.addBrokenRule("Barcode expression is null", barcode);
		}
		else
		{
			String valueClass = codeExpression.getValueClassName();
			if (valueClass == null)
			{
				verifier.addBrokenRule("Barcode expression value class not set", codeExpression);
			}
			else if (!"java.lang.String".equals(valueClass))
			{
				verifier.addBrokenRule("Class " + valueClass 
						+ " not supported for barcode expression. Use java.lang.String instead.",
						codeExpression);
			}
		}
		
		JRExpression applicationIdentifierExpression = 
			barcode.getApplicationIdentifierExpression();
		if (applicationIdentifierExpression != null)
		{
			String valueClass = applicationIdentifierExpression.getValueClassName();
			if (valueClass == null)
			{
				verifier.addBrokenRule("Barcode application identifier expression value class not set", 
						applicationIdentifierExpression);
			}
			else if (!"java.lang.String".equals(valueClass))
			{
				verifier.addBrokenRule("Class " + valueClass 
						+ " not supported for barcode application identifier expression. Use java.lang.String instead.",
						applicationIdentifierExpression);
			}
		}
		
		byte evaluationTime = barcode.getEvaluationTime();
		if (evaluationTime == JRExpression.EVALUATION_TIME_AUTO)
		{
			verifier.addBrokenRule("Auto evaluation time is not supported for barcodes", barcode);
		}
		else if (evaluationTime == JRExpression.EVALUATION_TIME_GROUP)
		{
			String evaluationGroup = barcode.getEvaluationGroup();
			if (evaluationGroup == null || evaluationGroup.length() == 0)
			{
				verifier.addBrokenRule("No evaluation group set for barcode", barcode);
			}
			else if (!verifier.getReportDesign().getGroupsMap().containsKey(evaluationGroup))
			{
				verifier.addBrokenRule("Barcode evalution group \"" 
						+ evaluationGroup + " not found", barcode);
			}
		}
	}

}
