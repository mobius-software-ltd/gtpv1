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

public enum MapCauseCode 
{
	UNKNOWN_SUBSCRIBER(1),UNKNOWN_MSC(3),UNIDENTIFIED_SUBSCRIBER(5),ABSCENT_SUBSCRIBER_SM(6),UNKNOWN_EQUIPMENT(7),ROAMING_NOT_ALLOWED(8),ILLEGAL_SUBSCRIBER(9),BEARED_SERVICE_NOT_PROVISIONED(10),TELESERVICE_NOT_PROVISIONED(11),ILLEGAL_EQUIPMENT(12),CALL_BARRED(13),FORWARING_VIOLATION(14),
	CUG_REJECT(15),ILLEGAL_SS_OPERATION(16),SS_ERROR_CODE(17),SS_NOT_AVAILABLE(18),SS_SUBSCRIPTION_VIOLATION(19),SS_INCOMPATIBILITY(20),FACILITY_NOT_SUPPORTED(21),NO_HANDOVER_NUMBER_AVAILABLE(25),SUBSEQUENT_HANDOVER_NUMBER(26),ABSENT_SUBSCRIBER(27),INCOMPATIBLE_TERMINAL(28),SHORT_TERM_DENIAL(29),LONG_TERM_DENIAL(30),SUBSCRIBER_BUSY_FOR_MT_SMS(31),
	SM_DELIVERY_FAILURE(32),MESSAGE_WAITING_LIST_FULL(33),SYSTEM_FAILURE(34),DATA_MISSING(35),UNEXPECTED_DATA_VALUE(36),PW_REGISTRATION_FAILURE(37),NEGATIVE_PW_CHECK(38),NO_ROAMING_NUMBER_AVAILABLE(39),TRACING_BUFFER_FULL(40),TARGET_CELL_OUTSIDE_GROUP_CELL_AREA(42),NUMBER_OF_PW_ATTEMPTS_VIOLATION(43),NUMBER_CHANGED(44),BUSY_SUBSCRIBER(45),NO_SUBSCRIBER_REPLY(46),
	FORWARING_FAILED(47),OR_NOT_ALLOWED(48),ATI_NOT_ALLOWED(49),NO_GROUP_CALL_NUMBER_AVAILABLE(50),RESOURCE_LIMITATION(51),UNAUTHORIZED_REQUESTING_NETWORK(52),UNAUTHORIZED_LCS_CLIENT(53),POSITION_METHOD_FAILURE(54),UNKNOWN_OR_UNREACHABLE_LCS_CLIENT(58),MM_EVENT_NOT_SUPPORTED(59),ATSI_NOT_ALLOWED(60),ATM_NOT_ALLOWED(61),INFORMATION_NOT_AVAILABLE(62),UNKNOWN_ALPHABET(71),
	USSD_BUSY(72),NBR_SB_EXCEED(120),REJECTED_BY_USER(121),REJECTED_BY_NETWORK(122),DEFLECTION_TO_SERVED_SUBSCRIBER(123),SPECIAL_SERVICE_CODE(124),INVALID_DEFLECTED_TO_NUMBER(125),MAX_NUMBER_OF_MPTY_PARTICIPANT_EXCEEDED(126),RESOURCES_NOT_AVAILABLE(127),UNKNOWN(0);

	private static final Map<Integer, MapCauseCode> intToTypeMap = new HashMap<Integer, MapCauseCode>();
	static 
	{
	    for (MapCauseCode type : MapCauseCode.values()) 
	    {
	        intToTypeMap.put(type.value, type);
	    }
	}

	public static MapCauseCode fromInt(int i) 
	{
		MapCauseCode type = intToTypeMap.get(Integer.valueOf(i));
	    if (type == null) 
	        return MapCauseCode.UNKNOWN;
	    
	    return type;
	}
	
	private int value;
	
	private MapCauseCode(int value)
	{
		this.value=value;
	}
	
	public int getValue()
	{
		return value;
	}
}