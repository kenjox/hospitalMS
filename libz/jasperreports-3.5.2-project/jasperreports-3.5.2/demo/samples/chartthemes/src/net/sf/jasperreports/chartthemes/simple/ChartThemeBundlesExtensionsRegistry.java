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
package net.sf.jasperreports.chartthemes.simple;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.charts.ChartThemeBundle;
import net.sf.jasperreports.extensions.ExtensionsRegistry;

/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ChartThemeBundlesExtensionsRegistry.java 2692 2009-03-24 17:17:32Z teodord $
 */
public class ChartThemeBundlesExtensionsRegistry implements ExtensionsRegistry
{

	private final List chartThemeBundles;
	
	public ChartThemeBundlesExtensionsRegistry(List chartThemeBundles)
	{
		this.chartThemeBundles = chartThemeBundles;
	}
	
	public ChartThemeBundlesExtensionsRegistry(ChartThemeBundle chartThemeBundle)
	{
		this.chartThemeBundles = new ArrayList(1);
		this.chartThemeBundles.add(chartThemeBundle);
	}
	
	public List getExtensions(Class extensionType)
	{
		if (ChartThemeBundle.class.equals(extensionType)) {
			return chartThemeBundles;
		}
		return null;
	}

}
