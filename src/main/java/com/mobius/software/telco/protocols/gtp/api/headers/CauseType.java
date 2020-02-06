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
public enum CauseType 
{	
	REQUEST_IMSI(0),REQUEST_IMEI(1),REQUEST_IMSI_AND_IMEI(2),NO_IDENTITY_NEEDED(3),MS_REFUSES(4),
	MS_IS_NOT_GPRS_RESPONDING(5),REACTIVATION_REQUESTED(6),PDP_ADDRESS_TIMER_EXPIRED(7),NETWORK_FAILURE(8),QOS_PARAMETER_MISMATCH(9),
	SYSTEM_FAILURE(59),THE_TRANSFER_BUFFERS_ARE_BECOMING_FULL(60),THE_RECEIVE_BUFFERS_ARE_BECOMING_FULL(61),ANOTHER_NODE_IS_ABOUT_TO_GO_DOWN(62),THIS_NODE_IS_ABOUT_TO_GO_DOWN(63),
	REQUEST_ACCEPTED(128),NEW_PDP_NETWORK(129),NEW_PDP_SINGLE_ADDRESS(130),CDR_DECODING_ERROR(177),NON_EXISTENT(192),
	INVALID_MESSAGE_FORMAT(193),IMSI_IMEI_NOT_KNOWN(194),MS_IS_GPRS_DETACHED(195),MS_IS_NOT_GPRS_RESPONDING_(196),MS_REFUSES_(197),VERSION_NOT_SUPPORTED(198),
	NO_RESOURCES_AVAILABLE(199),SERVICE_NOT_SUPPORTED(200),MANDATORY_IE_INCORRECT(201),MANDATORY_IE_MISSING(202),OPTIONAL_IE_INCORRECT(203),
	RESPONSE_SYSTEM_FAILURE(204),ROAMING_RESTRICTION(205),PTMSI_SIGNATURE_MISMATCH(206),GPRS_CONNECTION_SUSPENDED(207),AUTHENTICATION_FAILURE(208),
	USER_AUTHENTICATION_FAILED(209),CONTEXT_NOT_FOUND(210),ALL_ADDRESSES_OCCUPIED(211),NO_MEMORY_AVAILABLE(212),RELOCATION_FAILURE(213),UNKNOWN_HEADER(214), 
	SEMANTIC_ERROR_TFT(215),SYNTACTIC_ERROR(216),SEMANTIC_ERROR_PF(217),SYNTACTIT_ERROR_PF(218),UNKNOWN_APN(219),UNKNOWN_PDP_ADDRESS(220),PDP_ALREADY_ACTIVATED(221),
	APN_ACCESS_DENIED(222),APN_INVALID_TYPE(223),MS_MBMS_CAPABILITIES_ERROR(224),INVALID_CORRELATION_ID(225),MBMS_SUPERSEDED(226),BEARER_MODE_VIOLATION(227),
	COLLISION_WITH_NETWORK(228),APN_CONGESTION(229),BEARER_HANDLING_NOT_SUPPORTED(230),TARGET_ACCESS_RESTRICTED(231),REQUEST_RELATED_TO_POSSIBLY_DUPLICATED_PACKETS_ALREADY_FULFILLED(252),
	REQUEST_ALREADY_FULFILLED(253),SEQUENCE_NUMBERS_OF_RELEASED_CANCELLED_PACKETS_IE_INCORRECT(254),REQUEST_NOT_FULFILLED(255),UNKNOWN(-1);

	private static final Map<Integer, CauseType> intToTypeMap = new HashMap<Integer, CauseType>();
	static 
	{
	    for (CauseType type : CauseType.values()) 
	    {
	        intToTypeMap.put(type.value, type);
	    }
	}

	public static CauseType fromInt(int i) 
	{
		CauseType type = intToTypeMap.get(Integer.valueOf(i));
	    if (type == null) 
	        return CauseType.UNKNOWN;
	    
	    return type;
	}
	
	private int value;
	
	private CauseType(int value)
	{
		this.value=value;
	}
	
	public int getValue()
	{
		return value;
	}
}
