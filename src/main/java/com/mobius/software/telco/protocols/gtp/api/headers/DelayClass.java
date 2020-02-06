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

public enum DelayClass 
{
	SUBSCRIBED(0),CLASS_1(1),CLASS_2(2),CLASS_3(3),CLASS_4(4),RESERVED(7);

	private static final Map<Integer, DelayClass> intToTypeMap = new HashMap<Integer, DelayClass>();
	static 
	{
	    for (DelayClass type : DelayClass.values()) 
	    {
	        intToTypeMap.put(type.value, type);
	    }
	}

	public static DelayClass fromInt(int i) 
	{
		DelayClass type = intToTypeMap.get(Integer.valueOf(i));
	    if (type == null) 
	        return DelayClass.RESERVED;
	    
	    return type;
	}
	
	private int value;
	
	private DelayClass(int value)
	{
		this.value=value;
	}
	
	public int getValue()
	{
		return value;
	}
}
