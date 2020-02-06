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

public enum ReasonForAbsence 
{
	NO_PAGING_RESPONSE_VIA_MSC(0),IMSI_DETACHED(1),ROAMING_RESTRICTIONS(2),DEREGISTER_IN_HLR_FOR_NON_GPRS(3),MS_PURGED_FOR_NON_GPRS(4),NO_PAGING_RESPONSE_VIA_SGSN(5),GPRS_DETACHED(6),DEREGISTER_IN_HLR_FOR_GPRS(7),MS_PURGED_FOR_GPRS(8),UNIDENTIFIED_SUBSCRIBER_VIA_MSC(9),UNIDENTIFIED_SUBSCRIBER_VIA_SGSN(10),DEREGISTER_IN_HSS_HLR_FOR_IMS(11),
	NO_RESPONSE_VIA_IP_SMGW(12),UNKNOWN(-1);

	private static final Map<Integer, ReasonForAbsence> intToTypeMap = new HashMap<Integer, ReasonForAbsence>();
	static 
	{
	    for (ReasonForAbsence type : ReasonForAbsence.values()) 
	    {
	        intToTypeMap.put(type.value, type);
	    }
	}

	public static ReasonForAbsence fromInt(int i) 
	{
		ReasonForAbsence type = intToTypeMap.get(Integer.valueOf(i));
	    if (type == null) 
	        return ReasonForAbsence.UNKNOWN;
	    
	    return type;
	}
	
	private int value;
	
	private ReasonForAbsence(int value)
	{
		this.value=value;
	}
	
	public int getValue()
	{
		return value;
	}
}