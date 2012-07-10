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
package net.sf.jasperreports.extensions;

import java.util.Collections;
import java.util.List;

import net.sf.jasperreports.charts.ChartThemeBundle;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.fill.DefaultChartTheme;
import net.sf.jasperreports.engine.query.DefaultQueryExecuterFactoryBundle;
import net.sf.jasperreports.engine.query.QueryExecuterFactoryBundle;
import net.sf.jasperreports.engine.scriptlets.DefaultScriptletFactory;
import net.sf.jasperreports.engine.scriptlets.ScriptletFactory;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: DefaultExtensionsRegistryFactory.java 2698 2009-03-24 18:46:27Z teodord $
 */
public class DefaultExtensionsRegistryFactory implements ExtensionsRegistryFactory
{
	private static final ExtensionsRegistry defaultExtensionsRegistry = 
		new ExtensionsRegistry()
		{
			public List getExtensions(Class extensionType) 
			{
				if (QueryExecuterFactoryBundle.class.equals(extensionType))
				{
					return Collections.singletonList(DefaultQueryExecuterFactoryBundle.getInstance());
				}
				else if (ScriptletFactory.class.equals(extensionType))
				{
					return Collections.singletonList(DefaultScriptletFactory.getInstance());
				}
				else if (ChartThemeBundle.class.equals(extensionType))
				{
					return Collections.singletonList(DefaultChartTheme.BUNDLE);
				}
				return null;
			}
		};
	
	public ExtensionsRegistry createRegistry(String registryId, JRPropertiesMap properties) 
	{
		return defaultExtensionsRegistry;
	}
}
