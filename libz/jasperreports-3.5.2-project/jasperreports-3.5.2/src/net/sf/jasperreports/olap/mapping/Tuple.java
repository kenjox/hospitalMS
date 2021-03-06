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
package net.sf.jasperreports.olap.mapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: Tuple.java 2699 2009-03-24 18:57:29Z teodord $
 */
public class Tuple
{
	private final List members;
	
	public Tuple ()
	{
		this.members = new ArrayList();
	}
	
	public Tuple (TupleMember member)
	{
		this.members = new ArrayList(1);
		addMember(member);
	}
	
	public void addMember (TupleMember member)
	{
		members.add(member);
	}
	
	public List getMembers ()
	{
		return members;
	}
	
	public String[] getMemberUniqueNames ()
	{
		String[] names = new String[members.size()];
		Iterator it = members.iterator();
		for (int i = 0; i < names.length; ++i)
		{
			TupleMember member = (TupleMember) it.next();
			names[i] = member.getUniqueName();
		}
		return names;
	}
}
