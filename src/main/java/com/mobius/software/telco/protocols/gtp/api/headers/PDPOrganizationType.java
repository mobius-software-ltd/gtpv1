package com.mobius.software.telco.protocols.gtp.api.headers;
/*Mobius Software LTD
Copyright 2019, Mobius Software LTD and individual contributors
by the @authors tag.

This program is free software: you can redistribute it and/or modify
under the terms of the GNU Affero General Public License as
published by the Free Software Foundation; either version 3 of
the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>*/
import java.util.HashMap;
import java.util.Map;

public enum PDPOrganizationType 
{
	PPP(0),IP(1);

	private static final Map<Integer, PDPOrganizationType> intToTypeMap = new HashMap<Integer, PDPOrganizationType>();
	static
	{
	    for (PDPOrganizationType type : PDPOrganizationType.values()) 
	    {
	        intToTypeMap.put(type.value, type);
	    }
	}

	public static PDPOrganizationType fromInt(int i) 
	{
		PDPOrganizationType type = intToTypeMap.get(Integer.valueOf(i));
	    if (type == null) 
	        return PDPOrganizationType.IP;
	    
	    return type;
	}
	
	int value;
	
	private PDPOrganizationType(int value)
	{
		this.value=value;
	}
	
	public int getValue()
	{
		return value;
	}
}
