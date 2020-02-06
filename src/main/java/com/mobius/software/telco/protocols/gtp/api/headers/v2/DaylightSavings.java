package com.mobius.software.telco.protocols.gtp.api.headers.v2;
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

public enum DaylightSavings 
{
	NO_ADJUSTMENT(0),PLUS_1(1),PLUS_2(2),RESERVED(3);

	private static final Map<Integer, DaylightSavings> intToTypeMap = new HashMap<Integer, DaylightSavings>();
	static 
	{
	    for (DaylightSavings type : DaylightSavings.values()) 
	    {
	        intToTypeMap.put(type.value, type);
	    }
	}

	public static DaylightSavings fromInt(int i) 
	{
		DaylightSavings type = intToTypeMap.get(Integer.valueOf(i));
	    if (type == null) 
	        return DaylightSavings.RESERVED;
	    
	    return type;
	}
	
	private int value;
	
	private DaylightSavings(int value)
	{
		this.value=value;
	}
	
	public int getValue()
	{
		return value;
	}
}
