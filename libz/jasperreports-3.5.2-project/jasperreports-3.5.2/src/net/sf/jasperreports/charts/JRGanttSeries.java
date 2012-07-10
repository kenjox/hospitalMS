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

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRHyperlink;

/**
 * @author Peter Risko (peter@risko.hu)
 * @version $Id: JRGanttSeries.java 2693 2009-03-24 17:38:19Z teodord $
 */
public interface JRGanttSeries {

	/**
	 *
	 */
	public JRExpression getSeriesExpression();

	/**
	 *
	 */
	public JRExpression getTaskExpression();

	/**
	 *
	 */
	public JRExpression getSubtaskExpression();

	/**
	 *
	 */
	public JRExpression getStartDateExpression();

	/**
	 *
	 */
	public JRExpression getEndDateExpression();

	/**
	 *
	 */
	public JRExpression getPercentExpression();

	/**
	 *
	 */
	public JRExpression getLabelExpression();


	/**
	 * Returns the hyperlink specification for chart items.
	 * <p>
	 * The hyperlink will be evaluated for every chart item and an image map will be created for the chart.
	 * </p>
	 *
	 * @return hyperlink specification for chart items
	 */
	public JRHyperlink getItemHyperlink();

}
