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

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum(String.class)
public enum InterfaceType 
{	
	S1U_ENODEB(0),S1U_SGW(1),S12_RNC_U(2),S12_SGW_U(3),S5_S8_SGW_U(4),S5_S8_PGW_U(5),S5_S8_SGW_C(6),S5_S8_PGW_C(7),S5_S8_SGW_PMIPV6(8),S5_S8_PGW_PMIPV6(9),S11_MME_C(10),S11_S4_SGW_C(11),S10_MME_C(12),S3_MME_C(13),S3_SGSN_C(14),S4_SGSN_U(15),S4_SGW_U(16),S4_SGSN_C(17),S16_SGSN_C(18),ENODEB_U_DL(19),ENODEB_U_UL(20),RNC_U(21),SGSN_U(22),SGW_UPF_U(23),SM_MBMS_GW_C(24),SN_MBMS_GW_C(25),SM_MME_C(26),SN_MME_C(27),SGW_U_UL(28),SN_SGNS_U(29),S2B_EPDG_C(30),S2B_U_EPDG(31),S2B_PGW_C(32),S2B_U_PGW(33),S2A_TWAN_U(34),S2A_TWAN_C(35),S2A_PGW_C(36),S2A_PGW_U(37),S11_MME_U(38),S11_SWG_U(39),UNKNOWN(-1);

	private static final Map<Integer, InterfaceType> intToTypeMap = new HashMap<Integer, InterfaceType>();
	static 
	{
	    for (InterfaceType type : InterfaceType.values()) 
	    {
	        intToTypeMap.put(type.value, type);
	    }
	}

	public static InterfaceType fromInt(int i) 
	{
		InterfaceType type = intToTypeMap.get(Integer.valueOf(i));
	    if (type == null) 
	        return InterfaceType.UNKNOWN;
	    
	    return type;
	}
	
	int value;
	
	private InterfaceType(int value)
	{
		this.value=value;
	}
	
	public int getValue()
	{
		return value;
	}
}
