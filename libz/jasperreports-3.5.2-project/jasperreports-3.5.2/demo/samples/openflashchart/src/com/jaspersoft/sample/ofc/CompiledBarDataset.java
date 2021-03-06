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
package com.jaspersoft.sample.ofc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JRExpressionCollector;
import net.sf.jasperreports.engine.base.JRBaseElementDataset;
import net.sf.jasperreports.engine.base.JRBaseObjectFactory;

/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: CompiledBarDataset.java 2692 2009-03-24 17:17:32Z teodord $
 */
public class CompiledBarDataset extends JRBaseElementDataset implements BarDataset
{
	
	private static final long serialVersionUID = 1L;

	private List seriesList;
	
	public CompiledBarDataset(BarDataset dataset, JRBaseObjectFactory factory)
	{
		super(dataset, factory);
		
		List series = dataset.getSeries();
		seriesList = new ArrayList(series.size());
		for (Iterator it = series.iterator(); it.hasNext();)
		{
			BarSeries barSeries = (BarSeries) it.next();
			DefaultBarSeries compiledSeries = new DefaultBarSeries();
			compiledSeries.setSeriesExpression(factory.getExpression(barSeries.getSeriesExpression()));
			compiledSeries.setCategoryExpression(factory.getExpression(barSeries.getCategoryExpression()));
			compiledSeries.setValueExpression(factory.getExpression(barSeries.getValueExpression()));
			seriesList.add(compiledSeries);
		}
	}

	public void collectExpressions(JRExpressionCollector collector)
	{
		BarChartCompiler.collectExpressions(this, collector);
	}

	public List getSeries()
	{
		return seriesList;
	}

}
