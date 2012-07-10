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
package net.sf.jasperreports.components.list;

import net.sf.jasperreports.engine.base.JRBaseElementGroup;
import net.sf.jasperreports.engine.base.JRBaseObjectFactory;

/**
 * {@link ListContents} implementation used in compiled reports.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: BaseListContents.java 2714 2009-03-27 15:54:36Z lucianc $
 */
public class BaseListContents extends JRBaseElementGroup implements ListContents
{

	private static final long serialVersionUID = 1L;
	
	private final int height;

	protected BaseListContents(ListContents listContents, JRBaseObjectFactory factory)
	{
		super(listContents, factory);
		
		this.height = listContents.getHeight();
	}

	public int getHeight()
	{
		return height;
	}
	
}
