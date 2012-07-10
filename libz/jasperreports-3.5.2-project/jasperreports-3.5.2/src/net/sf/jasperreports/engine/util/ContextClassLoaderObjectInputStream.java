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
package net.sf.jasperreports.engine.util;

import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

/**
 * A subclass of {@link ObjectInputStream} that uses
 * {@link Thread#getContextClassLoader() the context class loader} to resolve
 * classes encountered in the input stream.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ContextClassLoaderObjectInputStream.java 2697 2009-03-24 18:41:23Z teodord $
 */
public class ContextClassLoaderObjectInputStream extends ObjectInputStream
{

	/**
	 * Creates an object input stream that reads data from the specified
	 * {@link InputStream}.
	 * 
	 * @param in the input stream to read data from
	 * @throws IOException
	 * @see ObjectInputStream#ObjectInputStream(InputStream)
	 */
	public ContextClassLoaderObjectInputStream(InputStream in) throws IOException
	{
		super(in);
		
		try
		{
			enableResolveObject(true);
		}
		catch(SecurityException ex)
		{
			//FIXMEFONT we silence this for applets. but are there other similar situations that we need to deal with by signing jars?
		}
	}

	/**
	 * Calls <code>super.resolveClass()</code> and in case this fails with
	 * {@link ClassNotFoundException} attempts to load the class using the
	 * context class loader.
	 */
	protected Class resolveClass(ObjectStreamClass desc) throws IOException,
			ClassNotFoundException
	{
		try
		{
			return super.resolveClass(desc);
		}
		catch (ClassNotFoundException e)
		{
			ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
			if (contextClassLoader == null)
			{
				throw e;
			}
			
			String name = desc.getName();
			try
			{
				//attempt to load the class using the context class loader
				return Class.forName(name, false, contextClassLoader);
			}
			catch (ClassNotFoundException e2)
			{
				//fallback to the original exception
				throw e;
			}
		}
	}

	
	/**
	 * Calls <code>super.resolveObject()</code> and in case the object is 
	 * a <code>java.awt.Font</code>, it look up for it is fails with
	 * {@link ClassNotFoundException} attempts to load the class using the
	 * context class loader.
	 */
	protected Object resolveObject(Object obj) throws IOException
	{
		Font font = (obj instanceof Font) ? (Font)obj : null;
		
		if (font != null)
		{
			//String fontName = (String)font.getAttributes().get(TextAttribute.FAMILY);//FIXMEFONT check this
			String fontName = font.getName();
			Font newFont = JRFontUtil.getAwtFontFromBundles(fontName, font.getStyle(), font.getSize(), null);
			
			if (newFont != null)
			{
				return newFont; //FIXMEFONT this does not work in jdk1.4, where dialog font gets used unless we derive it using all attributes
				//return newFont.deriveFont(font.getAttributes()); //this does not work in jdk1.6 where dialog gets used if we derive the font using attributes
			}
		}
		
		return obj;
	}


}
