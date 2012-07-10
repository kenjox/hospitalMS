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

import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import net.sf.jasperreports.engine.util.JRDataUtils;
import net.sf.jasperreports.engine.util.JRFontUtil;
import net.sf.jasperreports.engine.util.JRStyledTextParser;

/**
 * Selector of element-level styled text attributes for print text objects.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRStyledTextAttributeSelector.java 2751 2009-05-04 08:45:09Z teodord $
 * @see JRPrintText#getStyledText(JRStyledTextAttributeSelector)
 * @see JRPrintText#getFullStyledText(JRStyledTextAttributeSelector)
 */
public abstract class JRStyledTextAttributeSelector
{
	/**
	 * 
	 */
	private static Locale getLocale()
	{
		return JRStyledTextParser.getLocale();
	}
	
	/**
	 * 
	 */
	public static Locale getTextLocale(JRPrintText printText)
	{
		String localeCode = printText.getLocaleCode();
		if (localeCode == null)
		{
			return getLocale();
		}
		return JRDataUtils.getLocale(localeCode);
	}
	
	/**
	 * Selects all styled text attributes, i.e. font attributes plus forecolor
	 * and backcolor.
	 */
	public static final JRStyledTextAttributeSelector ALL = new JRStyledTextAttributeSelector()
	{
		public Map getStyledTextAttributes(JRPrintText printText)
		{
			Map attributes = new HashMap(); 
			JRFontUtil.getAttributes(attributes, printText, getTextLocale(printText));
			attributes.put(TextAttribute.FOREGROUND, printText.getForecolor());
			if (printText.getMode() == JRElement.MODE_OPAQUE)
			{
				attributes.put(TextAttribute.BACKGROUND, printText.getBackcolor());
			}
			return attributes;
		}
	};

	/**
	 * Selects all styled text attribute except backcolor, i.e. font attributes
	 * plus forecolor.
	 */
	public static final JRStyledTextAttributeSelector NO_BACKCOLOR = new JRStyledTextAttributeSelector()
	{
		public Map getStyledTextAttributes(JRPrintText printText)
		{
			Map attributes = new HashMap(); 
			JRFontUtil.getAttributes(attributes, printText, getTextLocale(printText));
			attributes.put(TextAttribute.FOREGROUND, printText.getForecolor());
			return attributes;
		}
	};

	/**
	 * Doesn't select any styled text attribute.
	 */
	public static final JRStyledTextAttributeSelector NONE = new JRStyledTextAttributeSelector()
	{
		public Map getStyledTextAttributes(JRPrintText printText)
		{
			return null;
		}
	};
	
	/**
	 * Construct a map containing the selected element-level styled text attributes
	 * for a print text element.
	 * 
	 * @param printText the print text object
	 * @return a map containing styled text attributes
	 */
	public abstract Map getStyledTextAttributes(JRPrintText printText);
	
}
