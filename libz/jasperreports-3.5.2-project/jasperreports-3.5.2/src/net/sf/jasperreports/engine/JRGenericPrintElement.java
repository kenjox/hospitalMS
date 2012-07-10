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
package net.sf.jasperreports.engine;

import java.util.Set;

/**
 * A generic print element.
 * 
 * <p>
 * Such an element has {@link #getGenericType() a type} and includes
 * a set of parameters.
 * Export handlers need to registered for the element's type and they are
 * responsible for producing export output for the element.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRGenericPrintElement.java 2698 2009-03-24 18:46:27Z teodord $
 * @see JRGenericElement
 */
public interface JRGenericPrintElement extends JRPrintElement
{

	/**
	 * Returns the type of this element.
	 * 
	 * @return the element type
	 */
	JRGenericElementType getGenericType();

	/**
	 * Returns the set of parameter names for this element.
	 * 
	 * @return the set of parameter names (as <code>String</code>s).
	 */
	Set getParameterNames();
	
	/**
	 * Determines whether the element includes a parameter having a given name.
	 * 
	 * @param name the parameter name
	 * @return whether a parameter having the specified name exists in the element
	 */
	boolean hasParameter(String name);
	
	/**
	 * Returns the value of a parameter.
	 * 
	 * @param name the parameter name
	 * @return the parameter value, or <code>null</code> if a parameter by
	 * the specified name does not exist.
	 */
	Object getParameterValue(String name);

	/**
	 * Sets a parameter value.
	 * 
	 * @param name the parameter name
	 * @param value the parameter value
	 */
	void setParameterValue(String name, Object value);
	
}
