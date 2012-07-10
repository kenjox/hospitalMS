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

import java.util.Iterator;
import java.util.Map;

import net.sf.jasperreports.engine.JRAbstractScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRFillDatasetScriptlet.java 2696 2009-03-24 18:35:01Z teodord $
 */
public class JRFillDatasetScriptlet extends JRAbstractScriptlet
{
	
	/**
	 *
	 */
	private JRFillDataset dataset = null;

	/**
	 *
	 */
	public JRFillDatasetScriptlet(JRFillDataset dataset)
	{
		this.dataset = dataset;
	}


	/**
	 *
	 */
	public void setData(
		Map parsm,
		Map fldsm,
		Map varsm,
		JRFillGroup[] grps
		)
	{
		super.setData(parsm, fldsm, varsm, grps);
		
		for(Iterator it = dataset.scriptlets.iterator(); it.hasNext();)
		{
			((JRAbstractScriptlet)it.next()).setData(parsm, fldsm, varsm, grps);
		}
	}


	/**
	 *
	 */
	public void beforeReportInit() throws JRScriptletException
	{
		for(Iterator it = dataset.scriptlets.iterator(); it.hasNext();)
		{
			((JRAbstractScriptlet)it.next()).beforeReportInit();
		}
	}


	/**
	 *
	 */
	public void afterReportInit() throws JRScriptletException
	{
		for(Iterator it = dataset.scriptlets.iterator(); it.hasNext();)
		{
			((JRAbstractScriptlet)it.next()).afterReportInit();
		}
	}


	/**
	 *
	 */
	public void beforePageInit() throws JRScriptletException
	{
		for(Iterator it = dataset.scriptlets.iterator(); it.hasNext();)
		{
			((JRAbstractScriptlet)it.next()).beforePageInit();
		}
	}


	/**
	 *
	 */
	public void afterPageInit() throws JRScriptletException
	{
		for(Iterator it = dataset.scriptlets.iterator(); it.hasNext();)
		{
			((JRAbstractScriptlet)it.next()).afterPageInit();
		}
	}


	/**
	 *
	 */
	public void beforeColumnInit() throws JRScriptletException
	{
		for(Iterator it = dataset.scriptlets.iterator(); it.hasNext();)
		{
			((JRAbstractScriptlet)it.next()).beforeColumnInit();
		}
	}


	/**
	 *
	 */
	public void afterColumnInit() throws JRScriptletException
	{
		for(Iterator it = dataset.scriptlets.iterator(); it.hasNext();)
		{
			((JRAbstractScriptlet)it.next()).afterColumnInit();
		}
	}


	/**
	 *
	 */
	public void beforeGroupInit(String groupName) throws JRScriptletException
	{
		for(Iterator it = dataset.scriptlets.iterator(); it.hasNext();)
		{
			((JRAbstractScriptlet)it.next()).beforeGroupInit(groupName);
		}
	}


	/**
	 *
	 */
	public void afterGroupInit(String groupName) throws JRScriptletException
	{
		for(Iterator it = dataset.scriptlets.iterator(); it.hasNext();)
		{
			((JRAbstractScriptlet)it.next()).afterGroupInit(groupName);
		}
	}


	/**
	 *
	 */
	public void beforeDetailEval() throws JRScriptletException
	{
		for(Iterator it = dataset.scriptlets.iterator(); it.hasNext();)
		{
			((JRAbstractScriptlet)it.next()).beforeDetailEval();
		}
	}


	/**
	 *
	 */
	public void afterDetailEval() throws JRScriptletException
	{
		for(Iterator it = dataset.scriptlets.iterator(); it.hasNext();)
		{
			((JRAbstractScriptlet)it.next()).afterDetailEval();
		}
	}


}
