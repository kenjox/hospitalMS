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

import java.io.IOException;
import java.io.ObjectInputStream;

import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;
import net.sf.jasperreports.engine.design.events.JRPropertyChangeSupport;


/**
 * Used for implementing band functionality. A report can contain the following bands: background, title,
 * summary, page header, page footer, last page footer, column header and column footer.
 * @see JRBaseSection
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRBaseBand.java 2790 2009-05-19 14:17:31Z teodord $
 */
public class JRBaseBand extends JRBaseElementGroup implements JRBand, JRChangeEventsSupport
{
	

	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	/**
	 * @deprecated Replaced by {@link #PROPERTY_SPLIT_TYPE}.
	 */
	public static final String PROPERTY_SPLIT_ALLOWED = "splitAllowed";

	/**
	 *
	 */
	public static final String PROPERTY_SPLIT_TYPE = "splitType";

	/**
	 *
	 */
	protected int height = 0;
	protected Byte splitType = null;

	/**
	 *
	 */
	protected JRExpression printWhenExpression = null;
	

	/**
	 *
	 */
	protected JRBaseBand(JRBand band, JRBaseObjectFactory factory)
	{
		super(band, factory);
		
		height = band.getHeight();
		splitType = band.getSplitType();

		printWhenExpression = factory.getExpression(band.getPrintWhenExpression());
	}
		

	/**
	 *
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 * @deprecated Replaced by {@link #getSplitType()}.
	 */
	public boolean isSplitAllowed()
	{
		return !JRBand.SPLIT_TYPE_PREVENT.equals(getSplitType());
	}

	/**
	 * @deprecated Replaced by {@link #setSplitType(Byte)}.
	 */
	public void setSplitAllowed(boolean isSplitAllowed)
	{
		setSplitType(isSplitAllowed ? JRBand.SPLIT_TYPE_STRETCH : JRBand.SPLIT_TYPE_PREVENT);
	}

	/**
	 * 
	 */
	public Byte getSplitType()
	{
		return splitType;
	}

	/**
	 *
	 */
	public void setSplitType(Byte splitType)
	{
		Byte old = this.splitType;
		this.splitType = splitType;
		getEventSupport().firePropertyChange(PROPERTY_SPLIT_TYPE, old, this.splitType);
	}

	/**
	 *
	 */
	public JRExpression getPrintWhenExpression()
	{
		return this.printWhenExpression;
	}

	/**
	 *
	 */
	public Object clone() 
	{
		JRBaseBand clone = (JRBaseBand)super.clone();
		if (printWhenExpression != null)
		{
			clone.printWhenExpression = (JRExpression)printWhenExpression.clone();
		}
		return clone;
	}
	
	private transient JRPropertyChangeSupport eventSupport;
	
	public JRPropertyChangeSupport getEventSupport()
	{
		synchronized (this)
		{
			if (eventSupport == null)
			{
				eventSupport = new JRPropertyChangeSupport(this);
			}
		}
		
		return eventSupport;
	}
		
	/**
	 * This field is only for serialization backward compatibility.
	 */
	private int PSEUDO_SERIAL_VERSION_UID = JRConstants.PSEUDO_SERIAL_VERSION_UID_3_5_2;
	private boolean isSplitAllowed = true;
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		in.defaultReadObject();
		
		if (PSEUDO_SERIAL_VERSION_UID < JRConstants.PSEUDO_SERIAL_VERSION_UID_3_5_2)
		{
			splitType = isSplitAllowed ? JRBand.SPLIT_TYPE_STRETCH : JRBand.SPLIT_TYPE_PREVENT;
		}
	}

}
