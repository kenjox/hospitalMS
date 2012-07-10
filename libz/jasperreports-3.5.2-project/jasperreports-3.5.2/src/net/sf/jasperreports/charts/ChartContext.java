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
package net.sf.jasperreports.charts;

import java.util.Locale;

import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;

import org.jfree.data.general.Dataset;

/**
 * @author Teodor Danciu (teodord@users.sourceforge.net) 
 * @version $Id: ChartContext.java 2693 2009-03-24 17:38:19Z teodord $
 */
public interface ChartContext
{

	/**
	 * 
	 */
	public JRChart getChart();

	/**
	 * 
	 */
	public Dataset getDataset();

	/**
	 * 
	 */
	public Object getLabelGenerator();

	/**
	 * 
	 */
	public Locale getLocale();

	/**
	 * 
	 */
	public Object evaluateExpression(JRExpression expression) throws JRException;

}
