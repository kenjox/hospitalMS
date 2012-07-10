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

/**
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: UniformBarcodeVisitor.java 2844 2009-06-01 18:10:59Z lucianc $
 */
public abstract class UniformBarcodeVisitor implements BarcodeVisitor
{

	protected abstract void visitBarcode(BarcodeComponent barcode);
	
	public void visitCodabar(CodabarComponent codabar)
	{
		visitBarcode(codabar);
	}

	public void visitCode128(Code128Component code128)
	{
		visitBarcode(code128);
	}

	public void visitDataMatrix(DataMatrixComponent dataMatrix)
	{
		visitBarcode(dataMatrix);
	}

	public void visitEANCode128(EAN128Component ean128)
	{
		visitBarcode(ean128);
	}

	public void visitCode39(Code39Component code39)
	{
		visitBarcode(code39);
	}

	public void visitUPCA(UPCAComponent upcA)
	{
		visitBarcode(upcA);
	}

	public void visitUPCE(UPCEComponent upcE)
	{
		visitBarcode(upcE);
	}

	public void visitEAN13(EAN13Component ean13)
	{
		visitBarcode(ean13);
	}

	public void visitEAN8(EAN8Component ean8)
	{
		visitBarcode(ean8);
	}

	public void visitInterleaved2Of5(Interleaved2Of5Component interleaved2Of5)
	{
		visitBarcode(interleaved2Of5);
	}

	public void visitRoyalMailCustomer(
			RoyalMailCustomerComponent royalMailCustomer)
	{
		visitBarcode(royalMailCustomer);
	}

	public void visitUSPSIntelligentMail(
			USPSIntelligentMailComponent intelligentMail)
	{
		visitBarcode(intelligentMail);
	}

	public void visitPostnet(POSTNETComponent postnet)
	{
		visitBarcode(postnet);
	}

	public void visitPDF417(PDF417Component pdf417)
	{
		visitBarcode(pdf417);
	}

}
