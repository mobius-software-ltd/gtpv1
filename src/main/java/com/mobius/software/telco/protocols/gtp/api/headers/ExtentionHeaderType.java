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

public enum ExtentionHeaderType 
{
	EMPTY(0),MBMS_SUPPORT_INDICATION(1),MS_INFO_CHANGE_REPORTING_SUPPORT_INDICATION(2),UDP_PORT(0x40),PDPC_PDU_NUMBER(0x0C0),SUSPEND_REQUEST(0x0C1),SUSPEND_RESPONSE(0x0C2),EMPTY_NEGATIVE(0xFF);

	private static final Map<Integer, ExtentionHeaderType> intToTypeMap = new HashMap<Integer, ExtentionHeaderType>();
	static
	{
	    for (ExtentionHeaderType type : ExtentionHeaderType.values()) 
	    {
	        intToTypeMap.put(type.value, type);
	    }
	}

	public static ExtentionHeaderType fromInt(int i) 
	{
		ExtentionHeaderType type = intToTypeMap.get(Integer.valueOf(i));
	    if (type == null) 
	        return ExtentionHeaderType.EMPTY;
	    
	    return type;
	}
	
	int value;
	
	private ExtentionHeaderType(int value)
	{
		this.value=value;
	}
	
	public int getValue()
	{
		return value;
	}
}
