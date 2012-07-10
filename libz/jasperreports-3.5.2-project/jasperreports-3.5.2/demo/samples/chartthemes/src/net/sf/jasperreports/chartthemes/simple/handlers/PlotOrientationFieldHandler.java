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
package net.sf.jasperreports.chartthemes.simple.handlers;

import org.exolab.castor.mapping.GeneralizedFieldHandler;
import org.jfree.chart.plot.PlotOrientation;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: PlotOrientationFieldHandler.java 2692 2009-03-24 17:17:32Z teodord $
 */
public class PlotOrientationFieldHandler extends GeneralizedFieldHandler
{
	/**
	 *
	 */
	public PlotOrientationFieldHandler()
	{
		super();
	}
	
	/**
	 *
	 */
	public Object convertUponGet(Object value)
	{
		if (value == null)
		{
			return null;
		}
		return ((PlotOrientation)value).toString();
	}

	/**
	 *
	 */
	public Object convertUponSet(Object value)
	{
		if (value == null)
		{
			return null;
		}
		return 
			PlotOrientation.HORIZONTAL.toString().equals(value) 
			? PlotOrientation.HORIZONTAL 
			: PlotOrientation.VERTICAL.toString().equals(value)
			? PlotOrientation.VERTICAL : null;
	}
	
	/**
	 *
	 */
	public Class getFieldType()
	{
		return PlotOrientation.class;
	}

	/**
	 *
	 */
	public Object newInstance(Object parent) throws IllegalStateException
	{
		//-- Since it's marked as a string...just return null,
		//-- it's not needed.
		return null;
	}
}
