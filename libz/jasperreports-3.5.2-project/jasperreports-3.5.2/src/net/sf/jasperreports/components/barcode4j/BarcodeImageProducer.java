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

import net.sf.jasperreports.engine.JRComponentElement;
import net.sf.jasperreports.engine.JRRenderable;

import org.krysalis.barcode4j.BarcodeGenerator;

/**
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: BarcodeImageProducer.java 2815 2009-05-27 11:34:54Z lucianc $
 */
public interface BarcodeImageProducer
{

	String PROPERTY_IMAGE_PRODUCER = 
		BarcodeComponent.PROPERTY_PREFIX + "image.producer";

	String PROPERTY_PREFIX_IMAGE_PRODUCER = 
		BarcodeComponent.PROPERTY_PREFIX + "image.producer.";
	
	JRRenderable createImage(JRComponentElement componentElement, 
			BarcodeGenerator barcode, String message, int orientation);
	
}
