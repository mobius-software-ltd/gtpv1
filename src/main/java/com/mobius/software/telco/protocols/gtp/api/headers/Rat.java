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

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum(String.class)
public enum Rat 
{	
	RESERVED(0),UTRAN(1),GERAN(2),WLAN(3),GAN(4),HSPA_EVOLUTION(5),EUTRAN(6),VIRTUAL(7),EUTRAN_NB_IoT(8),LTE_M(28),IEEE_802(101),EHRPD(102),HRPD(103),RTT_1X(104),UMB(105),UNKNOWN(-1);

	private static final Map<Integer, Rat> intToTypeMap = new HashMap<Integer, Rat>();
	static 
	{
	    for (Rat type : Rat.values()) 
	    {
	        intToTypeMap.put(type.value, type);
	    }
	}

	public static Rat fromInt(int i) 
	{
		Rat type = intToTypeMap.get(Integer.valueOf(i));
	    if (type == null) 
	        return Rat.UNKNOWN;
	    
	    return type;
	}
	
	int value;
	
	private Rat(int value)
	{
		this.value=value;
	}
	
	public int getValue()
	{
		return value;
	}
}
