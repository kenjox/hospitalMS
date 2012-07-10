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
package net.sf.jasperreports.charts.xml;

import net.sf.jasperreports.charts.design.JRDesignValueDisplay;
import net.sf.jasperreports.engine.JRChartPlot;
import net.sf.jasperreports.engine.util.JRColorUtil;
import net.sf.jasperreports.engine.xml.JRBaseFactory;

import org.xml.sax.Attributes;


/**
 * @author Barry Klawans (bklawans@users.sourceforge.net)
 * @version $Id: JRValueDisplayFactory.java 2693 2009-03-24 17:38:19Z teodord $
 */
public class JRValueDisplayFactory extends JRBaseFactory
{
	private static final String ATTRIBUTE_color = "color";
	private static final String ATTRIBUTE_mask = "mask";

	/**
	 *
	 */
	public Object createObject(Attributes atts)
	{
		JRDesignValueDisplay valueDisplay = new JRDesignValueDisplay(null, ((JRChartPlot)digester.peek()).getChart());

		String color = atts.getValue(ATTRIBUTE_color);
		if (color != null && color.length() > 0)
		{
			valueDisplay.setColor(JRColorUtil.getColor(color, null));
		}

		String mask = atts.getValue(ATTRIBUTE_mask);
		if (mask != null && mask.length() > 0)
		{
			valueDisplay.setMask(mask);
		}

		return valueDisplay;
	}
}
