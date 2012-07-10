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
package net.sf.jasperreports.components.barbecue;

import java.io.Serializable;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.base.JRBaseObjectFactory;
import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;
import net.sf.jasperreports.engine.design.events.JRPropertyChangeSupport;
import net.sf.jasperreports.engine.util.JRCloneUtils;

/**
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: StandardBarbecueComponent.java 2851 2009-06-02 21:14:46Z lucianc $
 */
public class StandardBarbecueComponent implements BarbecueComponent, Serializable, JRChangeEventsSupport
{

	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_CODE_EXPRESSION = "codeExpression";
	public static final String PROPERTY_APPLICATION_IDENTIFIER_EXPRESSION = "applicationIdentifierExpression";
	public static final String PROPERTY_DRAW_TEXT = "drawText";
	public static final String PROPERTY_CHECKSUM_REQUIRED = "checksumRequired";
	public static final String PROPERTY_BAR_WIDTH = "barWidth";
	public static final String PROPERTY_BAR_HEIGTH = "barHeight";
	public static final String PROPERTY_EVALUATION_TIME = "evaluationTime";
	public static final String PROPERTY_EVALUATION_GROUP = "evaluationGroup";

	private String type;
	private JRExpression codeExpression;
	private JRExpression applicationIdentifierExpression;
	private boolean drawText;
	private boolean checksumRequired;
	private Integer barWidth;
	private Integer barHeight;
	
	private byte evaluationTime = JRExpression.EVALUATION_TIME_NOW;
	private String evaluationGroup;
	
	private transient JRPropertyChangeSupport eventSupport;

	public StandardBarbecueComponent()
	{
	}

	public StandardBarbecueComponent(BarbecueComponent barcode, JRBaseObjectFactory objectFactory)
	{
		this.type = barcode.getType();
		this.codeExpression = objectFactory.getExpression(
				barcode.getCodeExpression());
		this.applicationIdentifierExpression = objectFactory.getExpression(
				barcode.getApplicationIdentifierExpression());
		this.drawText = barcode.isDrawText();
		this.checksumRequired = barcode.isChecksumRequired();
		this.barWidth = barcode.getBarWidth();
		this.barHeight = barcode.getBarHeight();
		this.evaluationTime = barcode.getEvaluationTime();
		this.evaluationGroup = barcode.getEvaluationGroup();
	}
	
	public JRExpression getCodeExpression()
	{
		return codeExpression;
	}

	public void setCodeExpression(JRExpression codeExpression)
	{
		Object old = this.codeExpression;
		this.codeExpression = codeExpression;
		getEventSupport().firePropertyChange(PROPERTY_CODE_EXPRESSION, 
				old, this.codeExpression);
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		Object old = this.type;
		this.type = type;
		getEventSupport().firePropertyChange(PROPERTY_TYPE, 
				old, this.type);
	}

	public JRExpression getApplicationIdentifierExpression()
	{
		return applicationIdentifierExpression;
	}

	public void setApplicationIdentifierExpression(
			JRExpression applicationIdentifierExpression)
	{
		Object old = this.applicationIdentifierExpression;
		this.applicationIdentifierExpression = applicationIdentifierExpression;
		getEventSupport().firePropertyChange(PROPERTY_APPLICATION_IDENTIFIER_EXPRESSION, 
				old, this.applicationIdentifierExpression);
	}

	public Integer getBarWidth()
	{
		return barWidth;
	}

	public void setBarWidth(Integer barWidth)
	{
		Object old = this.barWidth;
		this.barWidth = barWidth;
		getEventSupport().firePropertyChange(PROPERTY_BAR_WIDTH, 
				old, this.barWidth);
	}

	public Integer getBarHeight()
	{
		return barHeight;
	}

	public void setBarHeight(Integer barHeight)
	{
		Object old = this.barHeight;
		this.barHeight = barHeight;
		getEventSupport().firePropertyChange(PROPERTY_BAR_HEIGTH, 
				old, this.barHeight);
	}

	public boolean isChecksumRequired()
	{
		return checksumRequired;
	}

	public void setChecksumRequired(boolean checksumRequired)
	{
		boolean old = this.checksumRequired;
		this.checksumRequired = checksumRequired;
		getEventSupport().firePropertyChange(PROPERTY_CHECKSUM_REQUIRED, 
				old, this.checksumRequired);
	}

	public boolean isDrawText()
	{
		return drawText;
	}

	public void setDrawText(boolean drawText)
	{
		boolean old = this.drawText;
		this.drawText = drawText;
		getEventSupport().firePropertyChange(PROPERTY_DRAW_TEXT, 
				old, this.drawText);
	}
	
	public byte getEvaluationTime()
	{
		return evaluationTime;
	}

	public void setEvaluationTime(byte evaluationTime)
	{
		byte old = this.evaluationTime;
		this.evaluationTime = evaluationTime;
		getEventSupport().firePropertyChange(PROPERTY_EVALUATION_TIME, 
				old, this.evaluationTime);
	}

	public String getEvaluationGroup()
	{
		return evaluationGroup;
	}

	public void setEvaluationGroup(String evaluationGroup)
	{
		Object old = this.evaluationGroup;
		this.evaluationGroup = evaluationGroup;
		getEventSupport().firePropertyChange(PROPERTY_EVALUATION_GROUP, 
				old, this.evaluationGroup);
	}
	
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
	
	public Object clone()
	{
		try
		{
			StandardBarbecueComponent clone = (StandardBarbecueComponent) super.clone();
			clone.codeExpression = (JRExpression) JRCloneUtils
					.nullSafeClone(codeExpression);
			clone.applicationIdentifierExpression = (JRExpression) JRCloneUtils
					.nullSafeClone(applicationIdentifierExpression);
			return clone;
		}
		catch (CloneNotSupportedException e)
		{
			// never
			throw new JRRuntimeException(e);
		}
	}
}
