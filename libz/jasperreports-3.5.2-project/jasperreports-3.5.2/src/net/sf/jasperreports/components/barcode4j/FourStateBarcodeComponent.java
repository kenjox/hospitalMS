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
package net.sf.jasperreports.components.barcode4j;

import org.krysalis.barcode4j.ChecksumMode;

import net.sf.jasperreports.engine.JRConstants;

/**
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: FourStateBarcodeComponent.java 2836 2009-06-01 14:57:57Z lucianc $
 */
public abstract class FourStateBarcodeComponent extends BarcodeComponent
{
	
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	public static final String PROPERTY_ASCENDER_HEIGHT = "ascenderHeight";
	public static final String PROPERTY_CHECKSUM_MODE = "checksumMode";
	public static final String PROPERTY_INTERCHAR_GAP_WIDTH = "intercharGapWidth";
	public static final String PROPERTY_TRACK_HEIGHT = "trackHeight";

	private Double ascenderHeight;
	private String checksumMode;
	private Double intercharGapWidth;
	private Double trackHeight;

	public Double getAscenderHeight()
	{
		return ascenderHeight;
	}

	public void setAscenderHeight(Double ascenderHeight)
	{
		Object old = this.ascenderHeight;
		this.ascenderHeight = ascenderHeight;
		getEventSupport().firePropertyChange(PROPERTY_ASCENDER_HEIGHT, 
				old, this.ascenderHeight);
	}

	public String getChecksumMode()
	{
		return checksumMode;
	}

	public void setChecksumMode(String checksumMode)
	{
		Object old = this.checksumMode;
		this.checksumMode = checksumMode;
		getEventSupport().firePropertyChange(PROPERTY_CHECKSUM_MODE, 
				old, this.checksumMode);
	}

	public void setChecksumMode(ChecksumMode checksumMode)
	{
		setChecksumMode(checksumMode == null ? null : checksumMode.getName());
	}

	public Double getIntercharGapWidth()
	{
		return intercharGapWidth;
	}

	public void setIntercharGapWidth(Double intercharGapWidth)
	{
		Object old = this.intercharGapWidth;
		this.intercharGapWidth = intercharGapWidth;
		getEventSupport().firePropertyChange(PROPERTY_INTERCHAR_GAP_WIDTH, 
				old, this.intercharGapWidth);
	}

	public Double getTrackHeight()
	{
		return trackHeight;
	}

	public void setTrackHeight(Double trackHeight)
	{
		Object old = this.trackHeight;
		this.trackHeight = trackHeight;
		getEventSupport().firePropertyChange(PROPERTY_TRACK_HEIGHT, 
				old, this.trackHeight);
	}

}
