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
package net.sf.jasperreports.engine.component;

import net.sf.jasperreports.engine.JRComponentElement;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.convert.ReportConverter;
import net.sf.jasperreports.view.JasperDesignViewer;

/**
 * Converter of design report elements into a print elements used for report
 * design previewing.
 * 
 * <p>
 * Such converters are used when a report design that contains component elements
 * are previewed.  Each component implementation can include a converter that
 * provides a preview representation of a component instance.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ComponentDesignConverter.java 2719 2009-04-03 15:08:35Z lucianc $
 * @see JasperDesignViewer
 * @see JRPrintElement
 */
public interface ComponentDesignConverter
{

	/**
	 * Converts a component element into a print element that represents a preview
	 * of the component.
	 * 
	 * @param reportConverter the report converter instance
	 * @param element the component element
	 * @return a print element that represents a preview of the component
	 */
	JRPrintElement convert(ReportConverter reportConverter,
			JRComponentElement element);
	
}
