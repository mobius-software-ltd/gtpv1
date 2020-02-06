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

public enum SRVCCType 
{
	RESERVED(0),UNSPECIFIED(1),CANCELLED_BY_SOURCE(2),FAILURE_WITH_TARGET(3),NOT_ALLOWED(4),UNKOWN_TARGET_ID(5),TARGET_CELL_NOT_AVAILABLE(6),NO_RADIO_RESOURCE_AVAILABLE(7),FAILURE_IN_RADIO_INTERFACE_PROCEDURE(8),PERMANENT_SESSION_ERROR(9),TEMPORARY_SESSION_ERROR(10),UNKNOWN(-1);

	private static final Map<Integer, SRVCCType> intToTypeMap = new HashMap<Integer, SRVCCType>();
	static
	{
	    for (SRVCCType type : SRVCCType.values()) 
	    {
	        intToTypeMap.put(type.value, type);
	    }
	}

	public static SRVCCType fromInt(int i) 
	{
		SRVCCType type = intToTypeMap.get(Integer.valueOf(i));
	    if (type == null) 
	        return SRVCCType.UNKNOWN;
	    
	    return type;
	}
	
	int value;
	
	private SRVCCType(int value)
	{
		this.value=value;
	}
	
	public int getValue()
	{
		return value;
	}
}
