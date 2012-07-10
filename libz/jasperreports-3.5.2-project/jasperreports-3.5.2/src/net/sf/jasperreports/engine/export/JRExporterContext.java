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
package net.sf.jasperreports.engine.export;

import java.util.Map;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * A context that represents information about an export process.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRExporterContext.java 2725 2009-04-08 16:24:09Z lucianc $
 */
public interface JRExporterContext
{

	/**
	 * Returns the report which is currently exported.
	 * 
	 * @return currently exported report
	 */
	JasperPrint getExportedReport();

	/**
	 * Returns the properties prefix for the current exporter.
	 * 
	 * @return the properties prefix for the current exporter
	 */
	String getExportPropertiesPrefix();
	
	/**
	 * Returns the map of export parameters.
	 * 
	 * <p>
	 * The map uses {@link JRExporterParameter} instances as keys.
	 * 
	 * @return the map of export parameters
	 */
	Map getExportParameters();

	/**
	 * Returns the current X-axis offset at which elements should be exported.
	 * 
	 * @return the current X-axis offset
	 */
	int getOffsetX();

	/**
	 * Returns the current Y-axis offset at which elements should be exported.
	 * 
	 * @return the current Y-axis offset
	 */
	int getOffsetY();
}
