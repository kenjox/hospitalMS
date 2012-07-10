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
package net.sf.jasperreports.engine.base;

import java.io.Serializable;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRGenericElementParameter;

/**
 * A read-only implementation of {@link JRGenericElementParameter}
 * that is included in compiled reports.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRBaseGenericElementParameter.java 2694 2009-03-24 18:11:24Z teodord $
 */
public class JRBaseGenericElementParameter implements JRGenericElementParameter, Serializable
{

	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	protected String name;
	protected JRExpression valueExpression;
	protected boolean skipWhenEmpty;

	protected JRBaseGenericElementParameter()
	{
	}
	
	/**
	 * Creates a generic element parameter by copying an existing instance.
	 * 
	 * @param parameter the parameter to copy
	 * @param factory the object factory to be used for creating sub objects
	 */
	public JRBaseGenericElementParameter(JRGenericElementParameter parameter,
			JRBaseObjectFactory factory)
	{
		this.name = parameter.getName();
		this.valueExpression = factory.getExpression(parameter.getValueExpression());
		this.skipWhenEmpty = parameter.isSkipWhenEmpty();
		
		factory.put(parameter, this);
	}
	
	public String getName()
	{
		return name;
	}

	public JRExpression getValueExpression()
	{
		return valueExpression;
	}

	public boolean isSkipWhenEmpty()
	{
		return skipWhenEmpty;
	}

}
