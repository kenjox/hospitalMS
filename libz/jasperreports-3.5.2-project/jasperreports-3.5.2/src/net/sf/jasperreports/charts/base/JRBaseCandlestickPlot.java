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
package net.sf.jasperreports.charts.base;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;

import net.sf.jasperreports.charts.JRCandlestickPlot;
import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartPlot;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRExpressionCollector;
import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.base.JRBaseChartPlot;
import net.sf.jasperreports.engine.base.JRBaseFont;
import net.sf.jasperreports.engine.base.JRBaseObjectFactory;
import net.sf.jasperreports.engine.util.JRStyleResolver;


/**
 * @author Ionut Nedelcu (ionutned@users.sourceforge.net)
 * @version $Id: JRBaseCandlestickPlot.java 2693 2009-03-24 17:38:19Z teodord $
 */
public class JRBaseCandlestickPlot extends JRBaseChartPlot implements JRCandlestickPlot
{
	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	public static final String PROPERTY_SHOW_VOLUME = "showVolume";

	protected JRExpression timeAxisLabelExpression = null;
	protected JRFont timeAxisLabelFont = null;
	protected Color timeAxisLabelColor = null;
	protected JRFont timeAxisTickLabelFont = null;
	protected Color timeAxisTickLabelColor = null;
	protected String timeAxisTickLabelMask = null;
	protected Color timeAxisLineColor = null;

	protected JRExpression valueAxisLabelExpression = null;
	protected JRExpression rangeAxisMinValueExpression = null;
	protected JRExpression rangeAxisMaxValueExpression = null;
	protected JRExpression domainAxisMinValueExpression = null;
	protected JRExpression domainAxisMaxValueExpression = null;
	protected JRFont valueAxisLabelFont = null;
	protected Color valueAxisLabelColor = null;
	protected JRFont valueAxisTickLabelFont = null;
	protected Color valueAxisTickLabelColor = null;
	protected String valueAxisTickLabelMask = null;
	protected Color valueAxisLineColor = null;

	protected Boolean showVolume = null;

	/**
	 *
	 */
	protected JRBaseCandlestickPlot(JRChartPlot plot, JRChart chart)
	{
		super(plot, chart);
		
		JRCandlestickPlot candlestickPlot = plot instanceof JRCandlestickPlot ? (JRCandlestickPlot)plot : null;
		if (candlestickPlot == null)
		{
			timeAxisLabelFont = new JRBaseFont(chart, null);
			timeAxisTickLabelFont = new JRBaseFont(chart, null);
			valueAxisLabelFont = new JRBaseFont(chart, null);
			valueAxisTickLabelFont = new JRBaseFont(chart, null);
		}
		else
		{
			timeAxisLabelFont = new JRBaseFont(chart, candlestickPlot.getTimeAxisLabelFont());
			timeAxisTickLabelFont = new JRBaseFont(chart, candlestickPlot.getTimeAxisTickLabelFont());
			valueAxisLabelFont = new JRBaseFont(chart, candlestickPlot.getValueAxisLabelFont());
			valueAxisTickLabelFont = new JRBaseFont(chart, candlestickPlot.getValueAxisTickLabelFont());
		}
	}


	/**
	 *
	 */
	public JRBaseCandlestickPlot(JRCandlestickPlot candlestickPlot, JRBaseObjectFactory factory)
	{
		super(candlestickPlot, factory);

		showVolume = candlestickPlot.getShowVolume();

		timeAxisLabelExpression = factory.getExpression( candlestickPlot.getTimeAxisLabelExpression() );
		timeAxisLabelFont = new JRBaseFont(candlestickPlot.getChart(), candlestickPlot.getTimeAxisLabelFont());
		timeAxisLabelColor = candlestickPlot.getOwnTimeAxisLabelColor();
		timeAxisTickLabelFont = new JRBaseFont(candlestickPlot.getChart(), candlestickPlot.getTimeAxisTickLabelFont());
		timeAxisTickLabelColor = candlestickPlot.getOwnTimeAxisTickLabelColor();
		timeAxisTickLabelMask = candlestickPlot.getTimeAxisTickLabelMask();
		timeAxisLineColor = candlestickPlot.getOwnTimeAxisLineColor();
		
		valueAxisLabelExpression = factory.getExpression( candlestickPlot.getValueAxisLabelExpression() );
		domainAxisMinValueExpression = factory.getExpression( candlestickPlot.getDomainAxisMinValueExpression() );
		domainAxisMaxValueExpression = factory.getExpression( candlestickPlot.getDomainAxisMaxValueExpression() );
		rangeAxisMinValueExpression = factory.getExpression( candlestickPlot.getRangeAxisMinValueExpression() );
		rangeAxisMaxValueExpression = factory.getExpression( candlestickPlot.getRangeAxisMaxValueExpression() );
		valueAxisLabelFont = new JRBaseFont(candlestickPlot.getChart(), candlestickPlot.getValueAxisLabelFont());
		valueAxisLabelColor = candlestickPlot.getOwnValueAxisLabelColor();
		valueAxisTickLabelFont = new JRBaseFont(candlestickPlot.getChart(), candlestickPlot.getValueAxisTickLabelFont());
		valueAxisTickLabelColor = candlestickPlot.getOwnValueAxisTickLabelColor();
		valueAxisTickLabelMask = candlestickPlot.getValueAxisTickLabelMask();
		valueAxisLineColor = candlestickPlot.getOwnValueAxisTickLabelColor();
	}


	/**
	 * 
	 */
	public JRExpression getTimeAxisLabelExpression(){
		return timeAxisLabelExpression;
	}
	
	/**
	 * 
	 */
	public JRFont getTimeAxisLabelFont()
	{
		return timeAxisLabelFont;
	}
	
	/**
	 * 
	 */
	public Color getTimeAxisLabelColor()
	{
		return JRStyleResolver.getTimeAxisLabelColor(this, this);
	}
	
	/**
	 * 
	 */
	public Color getOwnTimeAxisLabelColor()
	{
		return timeAxisLabelColor;
	}
	
	/**
	 * 
	 */
	public JRFont getTimeAxisTickLabelFont()
	{
		return timeAxisTickLabelFont;
	}
	
	/**
	 * 
	 */
	public Color getTimeAxisTickLabelColor()
	{
		return JRStyleResolver.getTimeAxisTickLabelColor(this, this);
	}

	/**
	 * 
	 */
	public Color getOwnTimeAxisTickLabelColor()
	{
		return timeAxisTickLabelColor;
	}

	/**
	 * 
	 */
	public String getTimeAxisTickLabelMask()
	{
		return timeAxisTickLabelMask;
	}

	/**
	 * 
	 */
	public Color getTimeAxisLineColor()
	{
		return JRStyleResolver.getTimeAxisLineColor(this, this);
	}

	/**
	 * 
	 */
	public Color getOwnTimeAxisLineColor()
	{
		return timeAxisLineColor;
	}

	/**
	 * 
	 */
	public JRExpression getValueAxisLabelExpression(){
		return valueAxisLabelExpression;
	}

	/**
	 * 
	 */
	public JRExpression getDomainAxisMinValueExpression(){
		return domainAxisMinValueExpression;
	}

	/**
	 * 
	 */
	public JRExpression getDomainAxisMaxValueExpression(){
		return domainAxisMaxValueExpression;
	}

	/**
	 * 
	 */
	public JRExpression getRangeAxisMinValueExpression(){
		return rangeAxisMinValueExpression;
	}

	/**
	 * 
	 */
	public JRExpression getRangeAxisMaxValueExpression(){
		return rangeAxisMaxValueExpression;
	}

	/**
	 * 
	 */
	public JRFont getValueAxisLabelFont()
	{
		return valueAxisLabelFont;
	}
	
	/**
	 * 
	 */
	public Color getValueAxisLabelColor()
	{
		return JRStyleResolver.getValueAxisLabelColor(this, this);
	}
	
	/**
	 * 
	 */
	public Color getOwnValueAxisLabelColor()
	{
		return valueAxisLabelColor;
	}
	
	/**
	 * 
	 */
	public JRFont getValueAxisTickLabelFont()
	{
		return valueAxisTickLabelFont;
	}
	
	/**
	 * 
	 */
	public Color getValueAxisTickLabelColor()
	{
		return JRStyleResolver.getValueAxisTickLabelColor(this, this);
	}

	/**
	 * 
	 */
	public Color getOwnValueAxisTickLabelColor()
	{
		return valueAxisTickLabelColor;
	}

	/**
	 * 
	 */
	public String getValueAxisTickLabelMask()
	{
		return valueAxisTickLabelMask;
	}

	/**
	 * 
	 */
	public Color getValueAxisLineColor()
	{
		return JRStyleResolver.getValueAxisLineColor(this, this);
	}
	
	/**
	 * 
	 */
	public Color getOwnValueAxisLineColor()
	{
		return valueAxisLineColor;
	}
	
	/**
	 * @deprecated Replaced by {@link #getShowVolume()}
	 */
	public boolean isShowVolume()
	{
		return showVolume == null ? true : showVolume.booleanValue();
	}

	/**
	 * 
	 */
	public Boolean getShowVolume()
	{
		return showVolume;
	}


	/**
	 * @deprecated Replaced by {@link #setShowVolume(Boolean)}
	 */
	public void setShowVolume(boolean isShowVolume)
	{
		setShowVolume(Boolean.valueOf(isShowVolume));
	}

	/**
	 * 
	 */
	public void setShowVolume(Boolean showVolume)
	{
		Boolean old = this.showVolume;
		this.showVolume = showVolume;
		getEventSupport().firePropertyChange(PROPERTY_SHOW_VOLUME, old, this.showVolume);
	}

	/**
	 *
	 */
	public void collectExpressions(JRExpressionCollector collector)
	{
		collector.collect(this);
	}

	/**
	 *
	 */
	public Object clone(JRChart parentChart) 
	{
		JRBaseCandlestickPlot clone = (JRBaseCandlestickPlot)super.clone(parentChart);
		if (timeAxisLabelExpression != null)
		{
			clone.timeAxisLabelExpression = (JRExpression)timeAxisLabelExpression.clone();
		}
		if (valueAxisLabelExpression != null)
		{
			clone.valueAxisLabelExpression = (JRExpression)valueAxisLabelExpression.clone();
		}
		if (domainAxisMinValueExpression != null)
		{
			clone.domainAxisMinValueExpression = (JRExpression)domainAxisMinValueExpression.clone();
		}
		if (domainAxisMaxValueExpression != null)
		{
			clone.domainAxisMaxValueExpression = (JRExpression)domainAxisMaxValueExpression.clone();
		}
		if (rangeAxisMinValueExpression != null)
		{
			clone.rangeAxisMinValueExpression = (JRExpression)rangeAxisMinValueExpression.clone();
		}
		if (rangeAxisMaxValueExpression != null)
		{
			clone.rangeAxisMaxValueExpression = (JRExpression)rangeAxisMaxValueExpression.clone();
		}
		return clone;
	}
	
	/**
	 * This field is only for serialization backward compatibility.
	 */
	private int PSEUDO_SERIAL_VERSION_UID = JRConstants.PSEUDO_SERIAL_VERSION_UID_3_1_3;
	private boolean isShowVolume = true;
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		in.defaultReadObject();
		
		if (PSEUDO_SERIAL_VERSION_UID < JRConstants.PSEUDO_SERIAL_VERSION_UID_3_1_3)
		{
			showVolume = Boolean.valueOf(isShowVolume);
		}
	}
	
}
