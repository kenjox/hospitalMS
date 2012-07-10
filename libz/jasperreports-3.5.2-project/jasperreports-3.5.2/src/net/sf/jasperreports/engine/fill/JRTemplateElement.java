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

import java.awt.Color;
import java.io.Serializable;
import java.util.Random;

import net.sf.jasperreports.engine.JRCommonElement;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRDefaultStyleProvider;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JROrigin;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.util.JRStyleResolver;


/**
 * Base class consisting of print element information shared by multiple
 * print elements.
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRTemplateElement.java 2696 2009-03-24 18:35:01Z teodord $
 */
public abstract class JRTemplateElement implements JRCommonElement, Serializable, JRPropertiesHolder
{


	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	private static final Random randomId = new Random();

	/**
	 *
	 */
	private String key;
	private Byte mode = null;
	private Color forecolor = null;
	private Color backcolor = null;

	protected JROrigin origin = null;

	protected JRDefaultStyleProvider defaultStyleProvider;
	protected JRStyle parentStyle = null;

	private final String id;
	
	private JRPropertiesMap propertiesMap;
	
	/**
	 *
	 */
	protected JRTemplateElement(JROrigin origin, JRDefaultStyleProvider defaultStyleProvider)
	{
		this.origin = origin;
		this.defaultStyleProvider = defaultStyleProvider;
		id = createId();
	}

	/**
	 *
	 */
	protected JRTemplateElement(JROrigin origin, JRElement element)
	{
		this.origin = origin;
		setElement(element);
		id = createId();
	}

	protected JRTemplateElement(String id)
	{
		this.id = id;
	}
	
	private String createId()
	{
		return System.identityHashCode(this) + "_" + System.currentTimeMillis() + "_" + randomId.nextInt();
	}


	/**
	 * Copies basic element attributes: the element style, key,
	 * mode, forecolor and backcolor.
	 * 
	 * @param element the element to copy attributes from
	 */
	public void setElement(JRElement element)
	{
		parentStyle = element.getStyle();
		
		setKey(element.getKey());
		
		mode = element.getOwnMode();
		forecolor = element.getOwnForecolor();
		backcolor = element.getOwnBackcolor();
	}
	
	/**
	 *
	 */
	public JROrigin getOrigin()
	{
		return origin;
	}

	/**
	 *
	 */
	public JRDefaultStyleProvider getDefaultStyleProvider()
	{
		return defaultStyleProvider;
	}

	/**
	 *
	 */
	public JRStyle getStyle()
	{
		return parentStyle;
	}

	/**
	 *
	 */
	protected JRStyle getBaseStyle()
	{
		if (parentStyle != null)
			return parentStyle;
		if (defaultStyleProvider != null)
			return defaultStyleProvider.getDefaultStyle();
		return null;
	}

	/**
	 *
	 */
	public byte getMode()
	{
		return JRStyleResolver.getMode(this, JRElement.MODE_OPAQUE);
	}
		
	/**
	 *
	 */
	public Byte getOwnMode()
	{
		return mode;
	}
	
	/**
	 *
	 */
	protected void setMode(byte mode)
	{
		this.mode = new Byte(mode);
	}
	
	/**
	 *
	 */
	protected void setMode(Byte mode)
	{
		this.mode = mode;
	}
	
	/**
	 *
	 */
	public Color getForecolor()
	{
		return JRStyleResolver.getForecolor(this);
	}
	
	/**
	 *
	 */
	public Color getOwnForecolor()
	{
		return this.forecolor;
	}
	
	/**
	 *
	 */
	protected void setForecolor(Color forecolor)
	{
		this.forecolor = forecolor;
	}
	
	/**
	 *
	 */
	public Color getBackcolor()
	{
		return JRStyleResolver.getBackcolor(this);
	}
	
	/**
	 *
	 */
	public Color getOwnBackcolor()
	{
		return this.backcolor;
	}
	
	/**
	 *
	 */
	protected void setBackcolor(Color backcolor)
	{
		this.backcolor = backcolor;
	}
	
	/**
	 *
	 */
	public String getId()
	{
		return id;
	}

	
	public String getKey()
	{
		return key;
	}

	
	public void setKey(String key)
	{
		this.key = key;
	}


	/**
	 * Returns null as external style references are not allowed for print objects.
	 */
	public String getStyleNameReference()
	{
		return null;
	}

	
	public synchronized boolean hasProperties()
	{
		return propertiesMap != null && propertiesMap.hasProperties();
	}

	public synchronized JRPropertiesMap getPropertiesMap()
	{
		if (propertiesMap == null)
		{
			propertiesMap = new JRPropertiesMap();
		}
		return propertiesMap;
	}

	public JRPropertiesHolder getParentProperties()
	{
		return null;
	}

	/**
	 * Sets the template element style.
	 * 
	 * @param style the style
	 */
	public void setStyle(JRStyle style)
	{
		this.parentStyle = style;
	}
}
