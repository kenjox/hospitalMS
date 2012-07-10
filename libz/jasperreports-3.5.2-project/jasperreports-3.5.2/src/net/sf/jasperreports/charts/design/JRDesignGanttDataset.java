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
package net.sf.jasperreports.charts.design;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.charts.JRGanttDataset;
import net.sf.jasperreports.charts.JRGanttSeries;
import net.sf.jasperreports.engine.JRChartDataset;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRExpressionCollector;
import net.sf.jasperreports.engine.design.JRDesignChartDataset;
import net.sf.jasperreports.engine.design.JRVerifier;

/**
 * @author Peter Risko (peter@risko.hu)
 * @version $Id: JRDesignGanttDataset.java 2693 2009-03-24 17:38:19Z teodord $
 */
public class JRDesignGanttDataset  extends JRDesignChartDataset implements JRGanttDataset {

	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	private List ganttSeriesList = new ArrayList();


	/**
	 *
	 */
	public JRDesignGanttDataset(JRChartDataset dataset)
	{
		super(dataset);
	}


	/**
	 *
	 */
	public JRGanttSeries[] getSeries()
	{
		JRGanttSeries[] ganttSeriesArray = new JRGanttSeries[ganttSeriesList.size()];

		ganttSeriesList.toArray(ganttSeriesArray);

		return ganttSeriesArray;
	}


	/**
	 *
	 */
	public List getSeriesList()
	{
		return ganttSeriesList;
	}


	/**
	 *
	 */
	public void addGanttSeries(JRGanttSeries ganttSeries)
	{
		ganttSeriesList.add(ganttSeries);
	}


	/**
	 *
	 */
	public JRGanttSeries removeGanttSeries(JRGanttSeries ganttSeries)
	{
		if (ganttSeries != null)
		{
			ganttSeriesList.remove(ganttSeries);
		}

		return ganttSeries;
	}


	/**
	 *
	 */
	public byte getDatasetType() {
		return JRChartDataset.GANTT_DATASET;
	}


	/**
	 *
	 */
	public void collectExpressions(JRExpressionCollector collector)
	{
		collector.collect(this);
	}


	public void validate(JRVerifier verifier)
	{
		verifier.verify(this);
	}


}
