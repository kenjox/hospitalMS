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
package net.sf.jasperreports.charts.fill;

import net.sf.jasperreports.charts.JRPieSeries;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRHyperlink;
import net.sf.jasperreports.engine.JRHyperlinkHelper;
import net.sf.jasperreports.engine.JRPrintHyperlink;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.fill.JRCalculator;
import net.sf.jasperreports.engine.fill.JRExpressionEvalException;
import net.sf.jasperreports.engine.fill.JRFillHyperlinkHelper;
import net.sf.jasperreports.engine.fill.JRFillObjectFactory;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRFillPieSeries.java 2693 2009-03-24 17:38:19Z teodord $
 */
public class JRFillPieSeries implements JRPieSeries
{

	/**
	 *
	 */
	protected JRPieSeries parent = null;

	private Comparable key = null;
	private Number value = null;
	private String label = null;
	private JRPrintHyperlink sectionHyperlink;
	
	
	/**
	 *
	 */
	public JRFillPieSeries(
		JRPieSeries pieSeries, 
		JRFillObjectFactory factory
		)
	{
		factory.put(pieSeries, this);

		parent = pieSeries;
	}


	/**
	 *
	 */
	public JRExpression getKeyExpression()
	{
		return parent.getKeyExpression();
	}
		
	/**
	 *
	 */
	public JRExpression getValueExpression()
	{
		return parent.getValueExpression();
	}
		
	/**
	 *
	 */
	public JRExpression getLabelExpression()
	{
		return parent.getLabelExpression();
	}
	
	
	/**
	 *
	 */
	protected Comparable getKey()
	{
		return key;
	}
		
	/**
	 *
	 */
	protected Number getValue()
	{
		return value;
	}
		
	/**
	 *
	 */
	protected String getLabel()
	{
		return label;
	}
	
	/**
	 *
	 */
	protected JRPrintHyperlink getPrintSectionHyperlink()
	{
		return sectionHyperlink;
	}
	
	
	/**
	 *
	 */
	protected void evaluate(JRCalculator calculator) throws JRExpressionEvalException
	{
		key = (Comparable)calculator.evaluate(getKeyExpression()); 
		value = (Number)calculator.evaluate(getValueExpression());
		label = (String)calculator.evaluate(getLabelExpression());
		
		if (hasSectionHyperlinks())
		{
			evaluateSectionHyperlink(calculator);
		}
	}


	protected void evaluateSectionHyperlink(JRCalculator calculator) throws JRExpressionEvalException
	{
		try
		{
			sectionHyperlink = JRFillHyperlinkHelper.evaluateHyperlink(getSectionHyperlink(), calculator, JRExpression.EVALUATION_DEFAULT);
		}
		catch (JRExpressionEvalException e)
		{
			throw e;
		}
		catch (JRException e)
		{
			throw new JRRuntimeException(e);
		}
	}

	
	public boolean hasSectionHyperlinks()
	{
		return !JRHyperlinkHelper.isEmpty(getSectionHyperlink()); 
	}


	public JRHyperlink getSectionHyperlink()
	{
		return parent.getSectionHyperlink();
	}

	/**
	 *
	 */
	public Object clone() 
	{
		throw new UnsupportedOperationException();
	}
}
