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

/**
 * A generic report element parameter.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRGenericElementParameter.java 2698 2009-03-24 18:46:27Z teodord $
 * @see JRGenericElement#getParameters()
 */
public interface JRGenericElementParameter
{

	/**
	 * Returns the name of the parameter.
	 * 
	 * <p>
	 * The name will be propagated into the generic print element, as in
	 * {@link JRGenericPrintElement#setParameterValue(String, Object)}.
	 * 
	 * @return the name of the parameter
	 */
	String getName();
	
	/**
	 * Returns the expression that provides parameter values.
	 * 
	 * <p>
	 * The result of the expression evaluation will be propagated into the
	 * generic print element as parameter value, as in
	 * {@link JRGenericPrintElement#setParameterValue(String, Object)}.
	 * 
	 * @return the parameter's value expression
	 */
	JRExpression getValueExpression();
	
	/**
	 * Decides whether the parameter is skipped when its value evaluates to
	 * <code>null</code>.
	 * 
	 * <p>
	 * When the parameter's expression evaluates to <code>null</code> and this
	 * flag is set and , the parameter is not included in the generated print
	 * element.  If the flag is not set, the parameter is included with a
	 * <code>null</code> value. 
	 * 
	 * @return whether the parameter is skipped when its value is
	 * <code>null</code>
	 */
	boolean isSkipWhenEmpty();
	
}
