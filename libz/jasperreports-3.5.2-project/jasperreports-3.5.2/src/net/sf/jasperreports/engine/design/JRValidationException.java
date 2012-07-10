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
package net.sf.jasperreports.engine.design;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRException;


/**
 * An exception that contains a list of {@link JRValidationFault report validation faults}.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRValidationException.java 2694 2009-03-24 18:11:24Z teodord $
 * @see JRVerifier
 */
public class JRValidationException extends JRException
{
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	private final Collection faults;

	/**
	 * Creates a validation exception containing a single fault.
	 * 
	 * @param message the validation message
	 * @param source the validation source
	 * @see JRValidationFault
	 */
	public JRValidationException(String message, Object source)
	{
		this(createFault(message, source));
	}
	
	private static JRValidationFault createFault(String message, Object source)
	{
		JRValidationFault fault = new JRValidationFault();
		fault.setMessage(message);
		fault.setSource(source);
		return fault;
	}

	/**
	 * Creates a validation exception containing a single fault.
	 * 
	 * @param fault the fault
	 */
	public JRValidationException(JRValidationFault fault)
	{
		this(Arrays.asList(new JRValidationFault[]{fault}));
	}
	
	/**
	 * Create an exception.
	 * 
	 * @param faults a list of {@link JRValidationFault validation faults}
	 */
	public JRValidationException(Collection faults)
	{
		this(null, faults);
	}
	
	/**
	 * Create a validation exception.
	 * 
	 * @param message the message to be used as header for the exception message
	 * @param faults a list of {@link JRValidationFault validation faults}
	 */
	public JRValidationException(String message, Collection faults)
	{
		super(appendMessages(message, faults));
		
		this.faults = faults;
	}
	
	/**
	 * Returns the list of {@link JRValidationFault validation faults}.
	 * 
	 * @return the list of {@link JRValidationFault JRValidationFault} instances.
	 */
	public Collection getFaults()
	{
		return faults;
	}
	
	protected static String appendMessages(String header, Collection faults)
	{
		if (header == null)
		{
			header = "Report design not valid : ";
		}

		StringBuffer sbuffer = new StringBuffer();
		sbuffer.append(header);
		int i = 1;
		for(Iterator it = faults.iterator(); it.hasNext(); i++)
		{
			JRValidationFault fault = (JRValidationFault) it.next();
			sbuffer.append("\n\t " + i + ". " + fault.getMessage());
		}
		return sbuffer.toString();
	}
}
