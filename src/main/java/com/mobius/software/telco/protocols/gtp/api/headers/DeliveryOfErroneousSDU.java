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

public enum DeliveryOfErroneousSDU 
{
	SUBSCRIBED(0),NO_DETECT(1),YES(2),NO(3),RESERVED(7);

	private static final Map<Integer, DeliveryOfErroneousSDU> intToTypeMap = new HashMap<Integer, DeliveryOfErroneousSDU>();
	static 
	{
	    for (DeliveryOfErroneousSDU type : DeliveryOfErroneousSDU.values()) 
	    {
	        intToTypeMap.put(type.value, type);
	    }
	}

	public static DeliveryOfErroneousSDU fromInt(int i) 
	{
		DeliveryOfErroneousSDU type = intToTypeMap.get(Integer.valueOf(i));
	    if (type == null) 
	        return DeliveryOfErroneousSDU.RESERVED;
	    
	    return type;
	}
	
	private int value;
	
	private DeliveryOfErroneousSDU(int value)
	{
		this.value=value;
	}
	
	public int getValue()
	{
		return value;
	}
}
