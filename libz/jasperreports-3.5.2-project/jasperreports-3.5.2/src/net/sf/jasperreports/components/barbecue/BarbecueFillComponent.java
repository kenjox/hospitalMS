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

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRComponentElement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRImage;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.component.BaseFillComponent;
import net.sf.jasperreports.engine.component.FillPrepareResult;
import net.sf.jasperreports.engine.fill.JRTemplateImage;
import net.sf.jasperreports.engine.fill.JRTemplatePrintImage;
import net.sourceforge.barbecue.Barcode;

/**
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: BarbecueFillComponent.java 2825 2009-05-28 12:13:27Z lucianc $
 */
public class BarbecueFillComponent extends BaseFillComponent
{

	private final BarbecueComponent barcodeComponent;
	
	private final Map printTemplates = new HashMap();
	
	private String code;
	private String applicationIdentifier;
	
	public BarbecueFillComponent(BarbecueComponent barcode)
	{
		this.barcodeComponent = barcode;
	}
	
	protected BarbecueComponent getBarcode()
	{
		return barcodeComponent;
	}
	
	public void evaluate(byte evaluation) throws JRException
	{
		if (isEvaluateNow())
		{
			evaluateBarcode(evaluation);
		}
	}
	
	protected void evaluateBarcode(byte evaluation) throws JRException
	{
		code = (String) fillContext.evaluate(
				barcodeComponent.getCodeExpression(), evaluation);
		
		applicationIdentifier = (String) fillContext.evaluate(
				barcodeComponent.getApplicationIdentifierExpression(), evaluation);
	}
	
	protected boolean isEvaluateNow()
	{
		return barcodeComponent.getEvaluationTime() == JRExpression.EVALUATION_TIME_NOW;
	}

	public FillPrepareResult prepare(int availableHeight)
	{
		return isEvaluateNow() && code == null 
				? FillPrepareResult.NO_PRINT_NO_OVERFLOW
				: FillPrepareResult.PRINT_NO_STRETCH;
	}

	public JRPrintElement fill()
	{
		JRTemplateImage templateImage = getTemplateImage();
		
		JRTemplatePrintImage image = new JRTemplatePrintImage(templateImage);
		JRComponentElement element = fillContext.getComponentElement();
		image.setX(element.getX());
		image.setY(fillContext.getElementPrintY());
		image.setWidth(element.getWidth());
		image.setHeight(element.getHeight());
		
		if (isEvaluateNow())
		{
			setBarcodeImage(image);
		}
		else
		{
			fillContext.registerDelayedEvaluation(image, 
					barcodeComponent.getEvaluationTime(), 
					barcodeComponent.getEvaluationGroup());
		}
		
		return image;
	}

	public void evaluateDelayedElement(JRPrintElement element, byte evaluation)
			throws JRException
	{
		evaluateBarcode(evaluation);
		setBarcodeImage((JRTemplatePrintImage) element);
	}

	protected void setBarcodeImage(JRTemplatePrintImage image)
	{
		BarcodeInfo barcodeInfo = new BarcodeInfo();
		barcodeInfo.setType(barcodeComponent.getType());
		barcodeInfo.setCode(code);
		barcodeInfo.setApplicationIdentifier(applicationIdentifier);
		barcodeInfo.setDrawText(barcodeComponent.isDrawText());
		barcodeInfo.setRequiresChecksum(barcodeComponent.isChecksumRequired());
		barcodeInfo.setBarWidth(barcodeComponent.getBarWidth());
		barcodeInfo.setBarHeight(barcodeComponent.getBarHeight());
		
		Barcode barcode = BarcodeProviders.createBarcode(barcodeInfo);
		BarbecueRenderer renderer = new BarbecueRenderer(barcode);
		image.setRenderer(renderer);
	}

	protected JRTemplateImage getTemplateImage()
	{
		JRStyle elementStyle = fillContext.getElementStyle();
		JRTemplateImage templateImage = (JRTemplateImage) printTemplates.get(elementStyle);
		if (templateImage == null)
		{
			templateImage = new JRTemplateImage(
					fillContext.getElementOrigin(), 
					fillContext.getDefaultStyleProvider());
			templateImage.setStyle(elementStyle);
			templateImage.setScaleImage(JRImage.SCALE_IMAGE_RETAIN_SHAPE);
			
			printTemplates.put(elementStyle, templateImage);
		}
		return templateImage;
	}

}
