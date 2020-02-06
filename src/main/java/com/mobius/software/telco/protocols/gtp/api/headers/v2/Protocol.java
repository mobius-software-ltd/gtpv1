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

public enum Protocol 
{
	UNKOWN(-1),LCP(0x0C021),PAP(0x0C023),CHAP(0x0C223),IPCP(0x08021),DNS(0x000D),DNS_IPV6(0x0003),IP_VIA_NAS(0x000A),NRBSI(0x0005),IPV4_MTU(0x0010),MS_SUPPORT_IN_TFT(0x0011);

	private static final Map<Integer, Protocol> intToTypeMap = new HashMap<Integer, Protocol>();
	static
	{
	    for (Protocol type : Protocol.values()) 
	    {
	        intToTypeMap.put(type.value, type);
	    }
	}

	public static Protocol fromInt(int i) 
	{
		Protocol type = intToTypeMap.get(Integer.valueOf(i));
	    if (type == null) 
	        return Protocol.UNKOWN;
	    
	    return type;
	}
	
	int value;
	
	private Protocol(int value)
	{
		this.value=value;
	}
	
	public int getValue()
	{
		return value;
	}
}
