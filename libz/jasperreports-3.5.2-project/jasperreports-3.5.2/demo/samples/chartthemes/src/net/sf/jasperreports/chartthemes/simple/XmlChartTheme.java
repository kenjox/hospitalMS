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
package net.sf.jasperreports.chartthemes.simple;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.util.JRLoader;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.InputSource;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: XmlChartTheme.java 2692 2009-03-24 17:17:32Z teodord $
 */
public class XmlChartTheme extends SimpleChartTheme
{
	private static final String MAPPING_FILE = "net/sf/jasperreports/chartthemes/simple/chart.theme.mapping.xml";
	
	/**
	 *
	 */
	private String file = null;
	
	/**
	 *
	 */
	public XmlChartTheme()
	{
	}
	
	/**
	 *
	 */
	public XmlChartTheme(String file)
	{
		this.file = file;
	}
	

	/**
	 *
	 */
	public void setFile(String file)
	{
		this.file = file;
		this.chartThemeSettings = null;
	}
	
	
	/**
	 *
	 */
	public ChartThemeSettings getChartThemeSettings()
	{
		if (chartThemeSettings == null)
		{
			chartThemeSettings = loadSettings(file);
		}
		return chartThemeSettings;
	}
	
	
	/**
	 *
	 */
	public static ChartThemeSettings loadSettings(String file)
	{
		InputStream is = null; 
		
		try
		{
			is = JRLoader.getLocationInputStream(file);
			return loadSettings(is);
		}
		catch (JRException e)
		{
			throw new JRRuntimeException(e);
		}
		finally
		{
			if (is != null)
			{
				try
				{
					is.close();
				}
				catch(IOException e)
				{
				}
			}
		}
	}
	
	
	/**
	 *
	 */
	public static ChartThemeSettings loadSettings(InputStream is)
	{
		ChartThemeSettings settings = null;
		
		InputStream mis = null;
		
		try
		{
			mis = JRLoader.getLocationInputStream(MAPPING_FILE);

			Mapping mapping = new Mapping();
			mapping.loadMapping(
				new InputSource(mis)
				);
			
			Unmarshaller unmarshaller = new Unmarshaller(mapping);
			settings = 
				(ChartThemeSettings)unmarshaller.unmarshal(
					new InputSource(is)
				);
		}
		catch (MappingException e)
		{
			throw new JRRuntimeException(e);
		}
		catch (MarshalException e)
		{
			throw new JRRuntimeException(e);
		}
		catch (ValidationException e)
		{
			throw new JRRuntimeException(e);
		}
		catch (JRException e)
		{
			throw new JRRuntimeException(e);
		}
		finally
		{
			if (mis != null)
			{
				try
				{
					mis.close();
				}
				catch(IOException e)
				{
				}
			}
		}
		
		return settings;
	}
	
	
	/**
	 *
	 */
	public static void saveSettings(ChartThemeSettings settings, Writer writer)
	{
		InputStream mis = null;
		
		try
		{
			mis = JRLoader.getLocationInputStream(MAPPING_FILE);

			Marshaller marshaller = new Marshaller(writer);

			Mapping mapping = new Mapping();
			mapping.loadMapping(
				new InputSource(mis)
				);
			marshaller.setMapping(mapping);

			marshaller.marshal(settings);
		}
		catch (IOException e)
		{
			throw new JRRuntimeException(e);
		}
		catch (MappingException e)
		{
			throw new JRRuntimeException(e);
		}
		catch (MarshalException e)
		{
			throw new JRRuntimeException(e);
		}
		catch (ValidationException e)
		{
			throw new JRRuntimeException(e);
		}
		catch (JRException e)
		{
			throw new JRRuntimeException(e);
		}
		finally
		{
			if (mis != null)
			{
				try
				{
					mis.close();
				}
				catch(IOException e)
				{
				}
			}
		}
	}
	

	/**
	 *
	 */
	public static void saveSettings(ChartThemeSettings settings, File file)
	{
		Writer writer = null;
		
		try
		{
			writer = new FileWriter(file);
			saveSettings(settings, writer);
		}
		catch (IOException e)
		{
			throw new JRRuntimeException(e);
		}
		finally
		{
			if (writer != null)
			{
				try
				{
					writer.close();
				}
				catch(IOException e)
				{
				}
			}
		}
	}
	

	/**
	 *
	 */
	public static String saveSettings(ChartThemeSettings settings)
	{
		StringWriter writer = new StringWriter();
		
		try
		{
			saveSettings(settings, writer);
		}
		finally
		{
			try
			{
				writer.close();
			}
			catch(IOException e)
			{
			}
		}
		
		return writer.toString();
	}

}
