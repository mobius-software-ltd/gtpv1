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
public enum BSSGPCauseType 
{	
	PROCESSOR_OVERLOAD(0),EQUIPMENT_FAILURE(1),TRANSIT_NETWORK_SERVICE_FAILURE(2),NETWORK_SERVICE_CAPACITY_MODIFIED_FROM_0_TO_NON_0(3),
	UNKNOWN_MS(4),BVCI_UNKNOWN(5),CELL_TRAFFIC_CONGESTION(6),SGSN_CONGESTION(7),OM_INTERVENTION(8),BVCI_BLOCKED(9),
	PFC_CREATE_FAILURE(10),PFC_PREEMPTED(11),ABQP_NO_MORE_SUPPORTED(12),SEMANTICALLY_INCORRECT_PDU(32),INVALID_MANDATORY_INFORMATION(33),
	MISSING_MANDATORY_IE(34),MISSING_CONDITIONAL_IE(35),UNEXPECTED_CONDITIONAL_IE(36),CONDITIONAL_IE_ERROR(37),PDU_NOT_COMPATIBLE_WITH_PROTO_STATE(38),
	PROTOCOL_ERROR_UNSPECIFIED(39),PDU_NOT_COMPATIBLE_WITH_FEATURE_SET(40),REQUESTED_INFORMATION_NOT_AVAILABLE(41),UNKNOWN_DESTINATION_ADDRESS(42),
	UNKNOWN_RIM_IDENTITY_OR_RIM_APPLICATION_DISABLED(43),INVALID_CONTAINER_UNIT_INFORMATION(44),PFC_QUEING(45),PFC_CREATED_SUCCESFULLY(46),
	T12_EXPIRED(47),MS_UNDER_PS_HANDOVER_TREATMENT(48),UPLINK_QUALITY(49),UPLINK_STRENGTH(50),DOWNLINK_QUALITY(51),DOWNLINK_STRENGTH(52),
	DISTANCE(53),BETTER_CELL(54),TRAFFIC(55),RADIO_CONTACT_LOST_WITH_MS(56),MS_BACK_ON_OLD_CHANNEL(57),T13_EXPIRY(58),T14_EXPIRY(59),
	NOT_ALL_REQUESTED_PFC_CREATED(60),CS_CAUSE(61),REQUESTED_CIPHERING_OR_INTEGRITY_PROTECTION_ALGO_NOT_SUPPORTED(62),RELOCATION_FAILURE_IN_TARGET_SYSTEM(63),
	DIRECTED_RETRY(64),TIME_CRITICAL_RELLOCATION(65),PS_HANDOVER_TARGET_NOT_ALLOWED(66),PS_HANDOVER_NOT_SUPPORTED_IN_TARGET_BSS_OR_TARGET_SYSTEM(67),
	INCOMING_RELOCATION_NOT_SUPPORTED_DUE_TO_PUESBINE_FEATURE(68),DTM_HANDOVER_NO_CS_RESOURCE(69),DTM_HANDOVER_PS_ALLOCATION_FAILURE(70),
	DTM_HANDOVER_PS_ALLOCATION_T24_EXPIRY(71),DTM_HANDOVER_INVALID_CS_INDICATION_IE(72),DTM_HANDOVER_PS_ALLOCATION_T23_EXPIRY(73),
	DTM_HANDOVER_MSC_ERROR(74), INVALID_CSG_CELL(75),UNKNOWN(-1);

	private static final Map<Integer, BSSGPCauseType> intToTypeMap = new HashMap<Integer, BSSGPCauseType>();
	static 
	{
	    for (BSSGPCauseType type : BSSGPCauseType.values()) 
	    {
	        intToTypeMap.put(type.value, type);
	    }
	}

	public static BSSGPCauseType fromInt(int i) 
	{
		BSSGPCauseType type = intToTypeMap.get(Integer.valueOf(i));
	    if (type == null) 
	        return BSSGPCauseType.UNKNOWN;
	    
	    return type;
	}
	
	private int value;
	
	private BSSGPCauseType(int value)
	{
		this.value=value;
	}
	
	public int getValue()
	{
		return value;
	}
}
