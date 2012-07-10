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

import net.sf.jasperreports.engine.JRExpressionCollector;

/**
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: BarcodeExpressionCollector.java 2810 2009-05-26 17:30:50Z lucianc $
 */
public class BarcodeExpressionCollector extends UniformBarcodeVisitor
{

	private final JRExpressionCollector collector;

	public BarcodeExpressionCollector(JRExpressionCollector collector)
	{
		this.collector = collector;
	}
	
	protected void collectBarcode(BarcodeComponent barcode)
	{
		collector.addExpression(barcode.getCodeExpression());
		collector.addExpression(barcode.getPatternExpression());
	}

	protected void visitBarcode(BarcodeComponent barcode)
	{
		collectBarcode(barcode);
	}

}
