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

import net.sf.jasperreports.engine.fill.JRCalculator;
import net.sf.jasperreports.engine.fill.JRExpressionEvalException;


/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: FillBarSeries.java 2692 2009-03-24 17:17:32Z teodord $
 */
public class FillBarSeries
{
	
	private final BarSeries barSeries;
	private String series;
	private String category;
	private Number value;

	public FillBarSeries(BarSeries series)
	{
		this.barSeries = series;
	}

	protected void evaluate(JRCalculator calculator) throws JRExpressionEvalException
	{
		series = (String) calculator.evaluate(barSeries.getSeriesExpression());
		category = (String) calculator.evaluate(barSeries.getCategoryExpression());
		value = (Number) calculator.evaluate(barSeries.getValueExpression());
	}
	
	/**
	 * @return the series
	 */
	public String getSeriesKey()
	{
		return series;
	}

	/**
	 * @return the category
	 */
	public String getCategory()
	{
		return category;
	}

	/**
	 * @return the value
	 */
	public Number getValue()
	{
		return value;
	}
}
