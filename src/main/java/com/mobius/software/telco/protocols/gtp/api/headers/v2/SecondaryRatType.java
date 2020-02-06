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

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum(String.class)
public enum SecondaryRatType 
{	
	NR(0),UNLICENSED_SPECTRUM(1),UNKNOWN(-1);

	private static final Map<Integer, SecondaryRatType> intToTypeMap = new HashMap<Integer, SecondaryRatType>();
	static 
	{
	    for (SecondaryRatType type : SecondaryRatType.values()) 
	    {
	        intToTypeMap.put(type.value, type);
	    }
	}

	public static SecondaryRatType fromInt(int i) 
	{
		SecondaryRatType type = intToTypeMap.get(Integer.valueOf(i));
	    if (type == null) 
	        return SecondaryRatType.UNKNOWN;
	    
	    return type;
	}
	
	int value;
	
	private SecondaryRatType(int value)
	{
		this.value=value;
	}
	
	public int getValue()
	{
		return value;
	}
}
