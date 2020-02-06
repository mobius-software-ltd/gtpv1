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

public enum MeanThroughtput 
{
	SUBSCRIBED(0),MEAN_100(1),MEAN_200(2),MEAN_500(3),MEAN_1000(4),MEAN_2000(5),MEAN_5000(6),MEAN_10000(7),MEAN_20000(8),MEAN_50000(9),MEAN_100000(10),MEAN_200000(11),MEAN_500000(12),MEAN_1MIL(13),MEAN_2MIL(14),MEAN_5MIL(15),MEAN_10MIL(16),MEAN_20MIL(17),MEAN_50MIL(18),RESERVED(30),BEST_EFFORT(31);

	private static final Map<Integer, MeanThroughtput> intToTypeMap = new HashMap<Integer, MeanThroughtput>();
	static 
	{
	    for (MeanThroughtput type : MeanThroughtput.values()) 
	    {
	        intToTypeMap.put(type.value, type);
	    }
	}

	public static MeanThroughtput fromInt(int i) 
	{
		MeanThroughtput type = intToTypeMap.get(Integer.valueOf(i));
	    if (type == null) 
	        return MeanThroughtput.BEST_EFFORT;
	    
	    return type;
	}
	
	private int value;
	
	private MeanThroughtput(int value)
	{
		this.value=value;
	}
	
	public int getValue()
	{
		return value;
	}
}
