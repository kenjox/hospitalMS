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
package net.sf.jasperreports.engine.component;

import net.sf.jasperreports.engine.fill.JRFillCloneFactory;
import net.sf.jasperreports.engine.fill.JRFillObjectFactory;

/**
 * A factory of fill component instances.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ComponentFillFactory.java 2694 2009-03-24 18:11:24Z teodord $
 * @see ComponentManager
 * @see FillComponent
 */
public interface ComponentFillFactory
{

	/**
	 * Creates a fill component instance for a component.
	 * 
	 * @param component the component
	 * @param factory the fill objects factory
	 * @return the fill component instance
	 */
	FillComponent toFillComponent(Component component, JRFillObjectFactory factory);

	/**
	 * Creates a clone of a fill component.
	 * 
	 * <p>
	 * Fill components clones are currently only created when the component
	 * element is placed inside a crosstab.
	 * 
	 * @param component the fill component
	 * @param factory the clone factory
	 * @return a clone of the fill component
	 */
	FillComponent cloneFillComponent(FillComponent component, JRFillCloneFactory factory);
	
}
