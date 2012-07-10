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

import net.sf.jasperreports.engine.JRComponentElement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRExpressionCollector;
import net.sf.jasperreports.engine.JROrigin;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JRVisitor;
import net.sf.jasperreports.engine.component.Component;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.component.ComponentManager;
import net.sf.jasperreports.engine.component.ComponentsEnvironment;
import net.sf.jasperreports.engine.component.FillComponent;
import net.sf.jasperreports.engine.component.FillContext;
import net.sf.jasperreports.engine.component.FillPrepareResult;

/**
 * A {@link JRComponentElement} which is used during report fill.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRFillComponentElement.java 2800 2009-05-21 12:39:16Z teodord $
 */
public class JRFillComponentElement extends JRFillElement implements JRComponentElement, FillContext
{

	private FillComponent fillComponent;
	private boolean filling;
	
	public JRFillComponentElement(JRBaseFiller filler, JRComponentElement element,
			JRFillObjectFactory factory)
	{
		super(filler, element, factory);
		
		ComponentKey componentKey = element.getComponentKey();
		ComponentManager manager = ComponentsEnvironment.getComponentManager(componentKey);
		fillComponent = manager.getComponentFillFactory().toFillComponent(element.getComponent(), factory);
		fillComponent.initialize(this);
	}

	public JRFillComponentElement(JRFillComponentElement element,
			JRFillCloneFactory factory)
	{
		super(element, factory);
		
		ComponentKey componentKey = element.getComponentKey();
		ComponentManager manager = ComponentsEnvironment.getComponentManager(componentKey);
		fillComponent = manager.getComponentFillFactory().cloneFillComponent(element.fillComponent, factory);
		fillComponent.initialize(this);
	}

	protected void evaluate(byte evaluation) throws JRException
	{
		reset();
		evaluatePrintWhenExpression(evaluation);

		if (isPrintWhenExpressionNull() || isPrintWhenTrue())
		{
			fillComponent.evaluate(evaluation);
		}
		
		filling = false;
	}
	
	protected boolean prepare(int availableHeight, boolean isOverflow)
			throws JRException
	{
		boolean willOverflow = false;

		super.prepare(availableHeight, isOverflow);
		
		if (!isToPrint())
		{
			return willOverflow;
		}
		
		boolean isToPrint = true;
		boolean isReprinted = false;

		if (!filling 
				&& isOverflow && isAlreadyPrinted() && !isPrintWhenDetailOverflows())
		{
			isToPrint = false;
		}

		if (isToPrint && availableHeight <  getRelativeY() + getHeight())
		{
			isToPrint = false;
			willOverflow = true;
		}

		if (!filling && isToPrint && isOverflow && isPrintWhenDetailOverflows()
				&& (isAlreadyPrinted() || !isPrintRepeatedValues()))
		{
			isReprinted = true;
		}

		if (isToPrint)
		{
			FillPrepareResult result = fillComponent.prepare(availableHeight - getRelativeY());
			
			isToPrint = result.isToPrint();
			willOverflow = result.willOverflow();
			setStretchHeight(result.getStretchHeight());
			
			// if the component will overflow, set the filling flag to true
			// to know next time that the component is continuing
			filling = willOverflow;
		}
		
		setToPrint(isToPrint);
		setReprinted(isReprinted);
		
		return willOverflow;
	}

	protected JRPrintElement fill() throws JRException
	{
		return fillComponent.fill();
	}

	protected JRTemplateElement createElementTemplate()
	{
		// not called
		return null;
	}

	protected void resolveElement (JRPrintElement element, byte evaluation, 
			JREvaluationTime evaluationTime) throws JRException
	{
		performDelayedEvaluation(element, evaluation);
	}
	
	protected void resolveElement(JRPrintElement element, byte evaluation)
			throws JRException
	{
		fillComponent.evaluateDelayedElement(element, evaluation);
	}

	protected void rewind() throws JRException
	{
		fillComponent.rewind();
		filling = false;
	}

	public void collectExpressions(JRExpressionCollector collector)
	{
		collector.collect(this);
	}

	public void visit(JRVisitor visitor)
	{
		visitor.visitComponentElement(this);
	}

	public JRFillCloneable createClone(JRFillCloneFactory factory)
	{
		return new JRFillComponentElement(this, factory);
	}

	public Component getComponent()
	{
		return ((JRComponentElement) parent).getComponent();
	}

	public ComponentKey getComponentKey()
	{
		return ((JRComponentElement) parent).getComponentKey();
	}
	
	public Object evaluate(JRExpression expression, byte evaluation)
			throws JRException
	{
		return super.evaluateExpression(expression, evaluation);
	}

	public JRComponentElement getComponentElement()
	{
		return this;
	}

	public JROrigin getElementOrigin()
	{
		return super.getElementOrigin();
	}

	public int getElementPrintY()
	{
		return getRelativeY();
	}

	public JRStyle getElementStyle()
	{
		return getStyle();
	}

	public void registerDelayedEvaluation(JRPrintElement printElement, 
			byte evaluationTime, String evaluationGroup)
	{
		filler.addBoundElement(this, printElement, 
				evaluationTime, evaluationGroup, band);
	}

}
