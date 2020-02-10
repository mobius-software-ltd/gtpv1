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

public enum PriorityLevel 
{
	SUBSCRIBED(0),LEVEL_1(1),LEVEL_2(2),LEVEL_3(3),LEVEL_4(4),LEVEL_5(5),LEVEL_6(6),LEVEL_7(7),LEVEL_8(8),LEVEL_9(9),LEVEL_10(10),LEVEL_11(11),LEVEL_12(12);

	private static final Map<Integer, PriorityLevel> intToTypeMap = new HashMap<Integer, PriorityLevel>();
	static 
	{
	    for (PriorityLevel type : PriorityLevel.values()) 
	    {
	        intToTypeMap.put(type.value, type);
	    }
	}

	public static PriorityLevel fromInt(int i) 
	{
		PriorityLevel type = intToTypeMap.get(Integer.valueOf(i));
	    if (type == null) 
	        return PriorityLevel.SUBSCRIBED;
	    
	    return type;
	}
	
	private int value;
	
	private PriorityLevel(int value)
	{
		this.value=value;
	}
	
	public int getValue()
	{
		return value;
	}
}
