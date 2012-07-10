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
package net.sf.jasperreports.engine.xml;

import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.design.JRDesignBand;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRBandFactory.java 2790 2009-05-19 14:17:31Z teodord $
 */
public class JRBandFactory extends JRBaseFactory
{
	private static final Log log = LogFactory.getLog(JRBandFactory.class);
	
	/**
	 *
	 */
	public Object createObject(Attributes atts)
	{
		JRDesignBand band = new JRDesignBand();
		
		String height = atts.getValue(JRXmlConstants.ATTRIBUTE_height);
		if (height != null && height.length() > 0)
		{
			band.setHeight(Integer.parseInt(height));
		}

		String isSplitAllowed = atts.getValue(JRXmlConstants.ATTRIBUTE_isSplitAllowed);
		if (isSplitAllowed != null && isSplitAllowed.length() > 0)
		{
			if (log.isWarnEnabled())
				log.warn("The 'isSplitAllowed' attribute is deprecated. Use the 'splitType' attribute instead.");
				
			if (Boolean.valueOf(isSplitAllowed).booleanValue())
			{
				band.setSplitType(JRBand.SPLIT_TYPE_STRETCH);
			}
			else
			{
				band.setSplitType(JRBand.SPLIT_TYPE_PREVENT);
			}
		}

		Byte splitType = (Byte)JRXmlConstants.getSplitTypeMap().get(atts.getValue(JRXmlConstants.ATTRIBUTE_splitType));
		if (splitType != null)
		{
			band.setSplitType(splitType);
		}

		return band;
	}
	

}
