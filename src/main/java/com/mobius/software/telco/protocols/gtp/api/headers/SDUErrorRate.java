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

public enum SDUErrorRate 
{
	SUBSCRIBED(0),_1_10_2(1),_7_10_3(2),_1_10_3(3),_1_10_4(4),_1_10_5(5),_1_10_6(6),_1_10_1(7),RESERVED(15);

	private static final Map<Integer, SDUErrorRate> intToTypeMap = new HashMap<Integer, SDUErrorRate>();
	static 
	{
	    for (SDUErrorRate type : SDUErrorRate.values()) 
	    {
	        intToTypeMap.put(type.value, type);
	    }
	}

	public static SDUErrorRate fromInt(int i) 
	{
		SDUErrorRate type = intToTypeMap.get(Integer.valueOf(i));
	    if (type == null) 
	        return SDUErrorRate.RESERVED;
	    
	    return type;
	}
	
	private int value;
	
	private SDUErrorRate(int value)
	{
		this.value=value;
	}
	
	public int getValue()
	{
		return value;
	}
}
