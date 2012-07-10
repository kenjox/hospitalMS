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
package net.sf.jasperreports.engine;

import net.sf.jasperreports.engine.xml.JRXmlTemplateLoader;


/**
 * A template included in a report.
 * <p/>
 * A template inclusion in a report consits of an expression that should be
 * resolved at runtime to a {@link JRTemplate JRTemplate} instance.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRReportTemplate.java 2698 2009-03-24 18:46:27Z teodord $
 * @see JRReport#getTemplates()
 */
public interface JRReportTemplate
{

	/**
	 * Returns the template source expression.
	 * <p/>
	 * The expression type should be (compatible with) one of <code>java.lang.String</code>,
	 * <code>java.io.File</code>, <code>java.net.URL</code>, <code>java.io.InputStream</code>
	 * (in which cases the template is loaded via {@link JRXmlTemplateLoader}) or
	 * <code>net.sf.jasperreports.engine.JRTemplate</code> .
	 * 
	 * @return the template source expression
	 */
	JRExpression getSourceExpression();

}
