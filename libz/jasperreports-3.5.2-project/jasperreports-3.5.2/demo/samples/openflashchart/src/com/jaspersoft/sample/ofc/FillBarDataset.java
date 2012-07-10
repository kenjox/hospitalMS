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
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JRExpressionCollector;
import net.sf.jasperreports.engine.fill.JRCalculator;
import net.sf.jasperreports.engine.fill.JRExpressionEvalException;
import net.sf.jasperreports.engine.fill.JRFillElementDataset;
import net.sf.jasperreports.engine.fill.JRFillObjectFactory;
import net.sf.jasperreports.engine.util.Pair;

/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: FillBarDataset.java 2692 2009-03-24 17:17:32Z teodord $
 */
public class FillBarDataset extends JRFillElementDataset
{
	
	private final BarDataset dataset;

	private List seriesList;
	
	private Set seriesKeys = new LinkedHashSet();
	private Set categories = new LinkedHashSet();
	private Map values = new LinkedHashMap();
	
	public FillBarDataset(BarDataset dataset, JRFillObjectFactory factory)
	{
		super(dataset, factory);
		
		this.dataset = dataset;
		
		List datasetSeries = dataset.getSeries();
		seriesList = new ArrayList(datasetSeries.size());
		for (Iterator it = datasetSeries.iterator(); it.hasNext();)
		{
			BarSeries series = (BarSeries) it.next();
			FillBarSeries fillSeries = new FillBarSeries(series);
			seriesList.add(fillSeries);
		}
	}

	protected void customEvaluate(JRCalculator calculator)
			throws JRExpressionEvalException
	{
		for (Iterator it = seriesList.iterator(); it.hasNext();)
		{
			FillBarSeries series = (FillBarSeries) it.next();
			series.evaluate(calculator);
		}
	}

	protected void customIncrement()
	{
		for (Iterator it = seriesList.iterator(); it.hasNext();)
		{
			FillBarSeries series = (FillBarSeries) it.next();
			String seriesKey = series.getSeriesKey();
			String category = series.getCategory();
			Number value = series.getValue();
			
			seriesKeys.add(seriesKey);
			categories.add(category);
			values.put(new Pair(seriesKey, category), value);
		}
	}

	protected void customInitialize()
	{
		seriesKeys.clear();
		categories.clear();
		values.clear();
	}

	public void collectExpressions(JRExpressionCollector collector)
	{
		BarChartCompiler.collectExpressions(dataset, collector);
	}

	public void finishDataset()
	{
		super.increment();
	}
	
	public Set getSeriesKeys()
	{
		return seriesKeys;
	}
	
	public Set getCategories()
	{
		return categories;
	}
	
	public Number getValue(String seriesKey, String category)
	{
		return (Number) values.get(new Pair(seriesKey, category));
	}

}
