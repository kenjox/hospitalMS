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
package net.sf.jasperreports.view.save;

import java.io.File;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.view.JRSaveContributor;

/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRHtmlSaveContributor.java 2700 2009-03-24 18:58:25Z teodord $
 */
public class JRHtmlSaveContributor extends JRSaveContributor
{

	/**
	 * 
	 */
	private static final String EXTENSION_HTM = ".htm"; 
	private static final String EXTENSION_HTML = ".html"; 

	/**
	 * 
	 */
	public JRHtmlSaveContributor(Locale locale, ResourceBundle resBundle)
	{
		super(locale, resBundle);
	}
	
	/**
	 * 
	 */
	public boolean accept(File file)
	{
		if (file.isDirectory())
		{
			return true;
		}
		String name = file.getName().toLowerCase();
		return (name.endsWith(EXTENSION_HTM) || name.endsWith(EXTENSION_HTML));
	}

	/**
	 * 
	 */
	public String getDescription()
	{
		return getBundleString("file.desc.html");
	}

	/**
	 * 
	 */
	public void save(JasperPrint jasperPrint, File file) throws JRException
	{
		if (
			!file.getName().toLowerCase().endsWith(EXTENSION_HTM)
			&& !file.getName().toLowerCase().endsWith(EXTENSION_HTML)
			)
		{
			file = new File(file.getAbsolutePath() + EXTENSION_HTML);
		}
			
		if (
			!file.exists() ||
			JOptionPane.OK_OPTION == 
				JOptionPane.showConfirmDialog(
					null, 
					MessageFormat.format(
						getBundleString("file.exists"),
						new Object[]{file.getName()}
						), 
					getBundleString("save"), 
					JOptionPane.OK_CANCEL_OPTION
					)
			)
		{
			JRHtmlExporter exporter = new JRHtmlExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint); 
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE, file);
			exporter.exportReport(); 
		}
	}

}