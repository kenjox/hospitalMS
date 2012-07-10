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

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRWrappingSvgRenderer.java 2819 2009-05-28 11:09:13Z lucianc $
 */
public class JRWrappingSvgRenderer extends JRAbstractSvgRenderer
{

	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	/**
	 *
	 */
	private JRRenderable renderer = null;
	private Dimension2D elementDimension = null;
	private Color backcolor = null;

	
	/**
	 *
	 */
	public JRWrappingSvgRenderer(
		JRRenderable renderer, 
		Dimension2D elementDimension,
		Color backcolor
		)
	{
		this.renderer = renderer;
		this.elementDimension = elementDimension;
		this.backcolor = backcolor;
	}


	/**
	 *
	 */
	public Dimension2D getDimension()
	{
		Dimension2D imageDimension = null;
		try
		{
			// use original dimension if possible
			imageDimension = renderer.getDimension();
		}
		catch (JRException e)
		{
			// ignore
		}
		
		if (imageDimension == null)
		{
			// fallback to element dimension
			imageDimension = elementDimension;
		}
		
		return imageDimension;
	}


	/**
	 *
	 */
	public Color getBackcolor()
	{
		return backcolor;
	}


	/**
	 *
	 */
	public void render(Graphics2D grx, Rectangle2D rectangle) throws JRException
	{
		renderer.render(grx, rectangle);
	}

	protected Graphics2D createGraphics(BufferedImage bi)
	{
		if (renderer instanceof JRAbstractSvgRenderer)
		{
			return ((JRAbstractSvgRenderer) renderer).createGraphics(bi);
		}
		
		return super.createGraphics(bi);
	}

}
