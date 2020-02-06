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

public enum ResidualBER 
{
	SUBSCRIBED(0),_5_10_2(1),_1_10_2(2),_5_10_3(3),_4_10_3(4),_1_10_3(5),_1_10_4(6),_1_10_5(7),_1_10_6(8),_6_10_8(9),RESERVED(15);

	private static final Map<Integer, ResidualBER> intToTypeMap = new HashMap<Integer, ResidualBER>();
	static 
	{
	    for (ResidualBER type : ResidualBER.values()) 
	    {
	        intToTypeMap.put(type.value, type);
	    }
	}

	public static ResidualBER fromInt(int i) 
	{
		ResidualBER type = intToTypeMap.get(Integer.valueOf(i));
	    if (type == null) 
	        return ResidualBER.RESERVED;
	    
	    return type;
	}
	
	private int value;
	
	private ResidualBER(int value)
	{
		this.value=value;
	}
	
	public int getValue()
	{
		return value;
	}
}
