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

public enum PeakThroughtput 
{
	SUBSCRIBED(0),MAX_1000(1),MAX_2000(2),MAX_4000(3),MAX_8000(4),MAX_16000(5),MAX_32000(6),MAX_64000(7),MAX_128000(8),MAX_256000(9),RESERVED(15);

	private static final Map<Integer, PeakThroughtput> intToTypeMap = new HashMap<Integer, PeakThroughtput>();
	static 
	{
	    for (PeakThroughtput type : PeakThroughtput.values()) 
	    {
	        intToTypeMap.put(type.value, type);
	    }
	}

	public static PeakThroughtput fromInt(int i) 
	{
		PeakThroughtput type = intToTypeMap.get(Integer.valueOf(i));
	    if (type == null) 
	        return PeakThroughtput.RESERVED;
	    
	    return type;
	}
	
	private int value;
	
	private PeakThroughtput(int value)
	{
		this.value=value;
	}
	
	public int getValue()
	{
		return value;
	}
}
