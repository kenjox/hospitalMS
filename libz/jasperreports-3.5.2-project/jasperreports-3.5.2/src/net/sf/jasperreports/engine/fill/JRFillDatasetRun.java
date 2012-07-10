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

import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDatasetParameter;
import net.sf.jasperreports.engine.JRDatasetRun;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRQuery;
import net.sf.jasperreports.engine.JRScriptletException;
import net.sf.jasperreports.engine.JRVariable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class used to instantiate sub datasets.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRFillDatasetRun.java 2714 2009-03-27 15:54:36Z lucianc $
 */
public class JRFillDatasetRun implements JRDatasetRun
{
	
	private static final Log log = LogFactory.getLog(JRFillDatasetRun.class);
	
	protected final JRBaseFiller filler;

	protected final JRFillDataset dataset;

	protected JRExpression parametersMapExpression;

	protected JRDatasetParameter[] parameters;

	protected JRExpression connectionExpression;

	protected JRExpression dataSourceExpression;

	
	/**
	 * Construct an instance for a dataset run.
	 * 
	 * @param filler the filler
	 * @param datasetRun the dataset run
	 * @param factory the fill object factory
	 */
	public JRFillDatasetRun(JRBaseFiller filler, JRDatasetRun datasetRun, JRFillObjectFactory factory)
	{
		this(filler, datasetRun, 
				(JRFillDataset) filler.datasetMap.get(datasetRun.getDatasetName()));
		
		factory.put(datasetRun, this);
	}

	protected JRFillDatasetRun(JRBaseFiller filler, JRDatasetRun datasetRun, 
			JRFillDataset dataset)
	{
		this.filler = filler;
		this.dataset = dataset;

		parametersMapExpression = datasetRun.getParametersMapExpression();
		parameters = datasetRun.getParameters();
		connectionExpression = datasetRun.getConnectionExpression();
		dataSourceExpression = datasetRun.getDataSourceExpression();
	}

	
	/**
	 * Instantiates and iterates the sub dataset for a chart dataset evaluation.
	 * 
	 * @param elementDataset the chart dataset
	 * @param evaluation the evaluation type
	 * @throws JRException
	 */
	public void evaluate(JRFillElementDataset elementDataset, byte evaluation) throws JRException
	{
		Map parameterValues = 
			JRFillSubreport.getParameterValues(
				filler, 
				parametersMapExpression, 
				parameters, 
				evaluation, 
				false, 
				dataset.getResourceBundle() != null,//hasResourceBundle
				false//hasFormatFactory
				);

		try
		{
			if (dataSourceExpression != null)
			{
				JRDataSource dataSource = (JRDataSource) filler.evaluateExpression(dataSourceExpression, evaluation);
				dataset.setDatasourceParameterValue(parameterValues, dataSource);
			}
			else if (connectionExpression != null)
			{
				Connection connection = (Connection) filler.evaluateExpression(connectionExpression, evaluation);
				dataset.setConnectionParameterValue(parameterValues, connection);
			}

			copyConnectionParameter(parameterValues);
			
			dataset.setParameterValues(parameterValues);
			dataset.initDatasource();
			
			dataset.filterElementDatasets(elementDataset);
			
			dataset.initCalculator();//FIXME too late for cascade param evaluations?

			iterate();
		}
		finally
		{
			dataset.closeDatasource();
			dataset.restoreElementDatasets();
		}
	}

	protected void copyConnectionParameter(Map parameterValues)
	{
		JRQuery query = dataset.getQuery();
		if (query != null)
		{
			String language = query.getLanguage();
			if (connectionExpression == null && 
					(language.equals("sql") || language.equals("SQL")) &&
					!parameterValues.containsKey(JRParameter.REPORT_CONNECTION))
			{
				JRFillParameter connParam = (JRFillParameter) filler.getParametersMap().get(JRParameter.REPORT_CONNECTION);
				Connection connection = (Connection) connParam.getValue();
				parameterValues.put(JRParameter.REPORT_CONNECTION, connection);
			}
		}
	}

	protected void iterate() throws JRException
	{
		dataset.start();

		init();

		if (dataset.next())
		{
			detail();

			while (dataset.next())
			{
				checkInterrupted();

				group();

				detail();
			}
		}

	}

	
	protected void checkInterrupted()
	{
		if (Thread.currentThread().isInterrupted() || filler.isInterrupted())
		{
			if (log.isDebugEnabled())
			{
				log.debug("Fill " + filler.fillerId + ": interrupting");
			}

			filler.setInterrupted(true);

			throw new JRFillInterruptedException();
		}
	}

	
	protected void group() throws JRException, JRScriptletException
	{
		dataset.calculator.estimateGroupRuptures();

		dataset.delegateScriptlet.callBeforeGroupInit();
		dataset.calculator.initializeVariables(JRVariable.RESET_TYPE_GROUP);
		dataset.delegateScriptlet.callAfterGroupInit();
	}

	protected void init() throws JRScriptletException, JRException
	{
		dataset.delegateScriptlet.callBeforeReportInit();
		dataset.calculator.initializeVariables(JRVariable.RESET_TYPE_REPORT);
		dataset.delegateScriptlet.callAfterReportInit();
	}

	protected void detail() throws JRScriptletException, JRException
	{
		dataset.delegateScriptlet.callBeforeDetailEval();
		dataset.calculator.calculateVariables();
		dataset.delegateScriptlet.callAfterDetailEval();
	}

	public String getDatasetName()
	{
		return dataset.getName();
	}

	public JRExpression getParametersMapExpression()
	{
		return parametersMapExpression;
	}

	public JRDatasetParameter[] getParameters()
	{
		return parameters;
	}

	public JRExpression getConnectionExpression()
	{
		return connectionExpression;
	}

	public JRExpression getDataSourceExpression()
	{
		return dataSourceExpression;
	}
	
	protected JRFillDataset getDataset()
	{
		return dataset;
	}
	
	/**
	 *
	 */
	public Object clone() 
	{
		throw new UnsupportedOperationException();
	}
}
