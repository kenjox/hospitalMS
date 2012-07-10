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

import net.sf.jasperreports.engine.JRCloneable;
import net.sf.jasperreports.engine.JRDatasetRun;
import net.sf.jasperreports.engine.component.Component;

/**
 * List component interface.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ListComponent.java 2851 2009-06-02 21:14:46Z lucianc $
 */
public interface ListComponent extends Component, JRCloneable
{

	/**
	 * Returns the subdataset run information that will be used by this list.
	 * 
	 * <p>
	 * This information is required to instantiate a subdataset from the report.
	 * The data produced by the subdataset is fed to the list item contents.
	 * 
	 * @return the subdataset run information
	 */
	JRDatasetRun getDatasetRun();

	/**
	 * Returns the list item contents.
	 * 
	 * <p>
	 * Each record produced by the list subdataset is used to fill the list
	 * item contents and the result is included in the generated report.
	 * 
	 * @return the list item contents
	 */
	ListContents getContents();

}