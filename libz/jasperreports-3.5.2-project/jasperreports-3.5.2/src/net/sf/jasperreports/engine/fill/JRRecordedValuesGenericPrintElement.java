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
package net.sf.jasperreports.engine.fill;

import java.util.Set;

import net.sf.jasperreports.engine.JRConstants;

/**
 * Generic print element implementation that supports recorded values.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRRecordedValuesGenericPrintElement.java 2696 2009-03-24 18:35:01Z teodord $
 */
public class JRRecordedValuesGenericPrintElement extends
		JRTemplateGenericPrintElement implements JRRecordedValuesPrintElement
{

	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	private JRRecordedValues recordedValues;

	/**
	 * Creates a generic print element.
	 * 
	 * @param template the element template to be used by the element
	 */
	public JRRecordedValuesGenericPrintElement(JRTemplateGenericElement template)
	{
		super(template);
	}

	public JRRecordedValues getRecordedValues()
	{
		return recordedValues;
	}

	public void deleteRecordedValues()
	{
		recordedValues = null;
	}

	public void initRecordedValues(Set evaluationTimes)
	{
		recordedValues = new JRRecordedValues(evaluationTimes);
	}

}
