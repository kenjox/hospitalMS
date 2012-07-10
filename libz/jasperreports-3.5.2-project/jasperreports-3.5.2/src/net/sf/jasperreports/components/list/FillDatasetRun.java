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

import java.sql.Connection;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRDatasetRun;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRRewindableDataSource;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.fill.JRFillDataset;
import net.sf.jasperreports.engine.fill.JRFillDatasetRun;
import net.sf.jasperreports.engine.fill.JRFillExpressionEvaluator;
import net.sf.jasperreports.engine.fill.JRFillObjectFactory;
import net.sf.jasperreports.engine.fill.JRFillSubreport;

/**
 * Used to iterate on the list subdataset at fill time.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: FillDatasetRun.java 2714 2009-03-27 15:54:36Z lucianc $
 */
public class FillDatasetRun extends JRFillDatasetRun
{

	private static final Log log = LogFactory.getLog(FillDatasetRun.class);
	
	private final JRFillExpressionEvaluator expressionEvaluator;
	
	private Map parameterValues;
	private JRDataSource dataSource;
	private Connection connection;
	private boolean first;
	
	public FillDatasetRun(JRDatasetRun datasetRun,
			JRFillObjectFactory factory) throws JRException
	{
		super(factory.getFiller(), datasetRun, 
				createFillDataset(datasetRun, factory));
		
		this.expressionEvaluator = factory.getExpressionEvaluator();
	}

	private static JRFillDataset createFillDataset(JRDatasetRun datasetRun,
			JRFillObjectFactory factory) throws JRException
	{
		JasperReport jasperReport = factory.getFiller().getJasperReport();
		JRDataset reportDataset = findSubdataset(datasetRun, jasperReport);
		JRFillDataset fillDataset = new JRFillDataset(factory.getFiller(), reportDataset, factory);
		fillDataset.createCalculator(jasperReport);
		return fillDataset;
	}
	
	private static JRDataset findSubdataset(JRDatasetRun datasetRun, 
			JasperReport report)
	{
		JRDataset[] datasets = report.getDatasets();
		JRDataset reportDataset = null;
		if (datasets != null)
		{
			for (int i = 0; i < datasets.length; i++)
			{
				if (datasetRun.getDatasetName().equals(
						datasets[i].getName()))
				{
					reportDataset = datasets[i];
					break;
				}
			}
		}
		
		if (reportDataset == null)
		{
			throw new JRRuntimeException("Could not find subdataset named \"" 
					+ datasetRun.getDatasetName() + "\" in report \"" 
					+ report.getName() + "\"");
		}
		return reportDataset;
	}

	public void evaluate(byte evaluation) throws JRException
	{
		if (log.isDebugEnabled())
		{
			log.debug("Evaluating list dataset run parameters");
		}
		
		parameterValues = JRFillSubreport.getParameterValues(
			filler, 
			parametersMapExpression, 
			parameters, 
			evaluation, 
			false, 
			dataset.getResourceBundle() != null,//hasResourceBundle
			false//hasFormatFactory
			);

		if (dataSourceExpression != null)
		{
			dataSource = (JRDataSource) expressionEvaluator.evaluate(
					dataSourceExpression, evaluation);
		}
		else if (connectionExpression != null)
		{
			connection = (Connection) expressionEvaluator.evaluate(
					connectionExpression, evaluation);
		}
	}
	
	public void start() throws JRException
	{
		if (log.isDebugEnabled())
		{
			log.debug("Starting list dataset iteration");
		}
		
		if (dataSourceExpression != null)
		{
			dataset.setDatasourceParameterValue(parameterValues, dataSource);
		}
		else if (connectionExpression != null)
		{
			dataset.setConnectionParameterValue(parameterValues, connection);
		}
		
		copyConnectionParameter(parameterValues);
		dataset.initCalculator();
		dataset.setParameterValues(parameterValues);
		dataset.initDatasource();
		
		dataset.start();
		init();
		first = true;
	}
	
	public boolean next() throws JRException
	{
		checkInterrupted();
		
		if (dataset.next())
		{
			if (!first)
			{
				group();
			}
			
			detail();
			
			return true;
		}
		
		return false;
	}
	
	public void end()
	{
		if (log.isDebugEnabled())
		{
			log.debug("Closing the data source");
		}
		
		dataset.closeDatasource();
	}
	
	public void rewind() throws JRException
	{
		if (dataSource != null)
		{
			if (dataSource instanceof JRRewindableDataSource)
			{
				if (log.isDebugEnabled())
				{
					log.debug("Rewinding the list data source");
				}
				
				((JRRewindableDataSource) dataSource).moveFirst();
			}
			else
			{
				log.warn("Cannot rewind list data source as it is not a JRRewindableDataSource");
			}
		}
	}
	
	public Object evaluateDatasetExpression(JRExpression expression, byte evaluationType) 
		throws JRException
	{
		return dataset.evaluateExpression(expression, evaluationType);
	}
}
