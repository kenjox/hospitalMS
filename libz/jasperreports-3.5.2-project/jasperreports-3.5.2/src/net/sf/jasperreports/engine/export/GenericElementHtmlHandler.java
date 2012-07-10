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

import net.sf.jasperreports.engine.JRGenericPrintElement;

/**
 * A generic print element HTML export handler.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: GenericElementHtmlHandler.java 2695 2009-03-24 18:14:25Z teodord $
 */
public interface GenericElementHtmlHandler extends GenericElementHandler
{

	/**
	 * Returns an HTML fragment that is to be inserted in the export output.
	 * 
	 * @param element the generic print element
	 * @return the HTML fragment that represents the exported element
	 */
	String getHtmlFragment(JRHtmlExporterContext exporterContext, JRGenericPrintElement element);
	
}
