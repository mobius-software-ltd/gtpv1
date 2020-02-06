package com.mobius.software.telco.protocols.gtp.impl.headers.extention;
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
import io.netty.buffer.ByteBuf;

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.ExtentionHeader;
import com.mobius.software.telco.protocols.gtp.api.headers.ExtentionHeaderType;

public class ExtentionHeaderFactory 
{
	public static ExtentionHeader decode(ByteBuf buffer) throws GTPParseException
	{
		ExtentionHeaderType type=ExtentionHeaderType.fromInt(buffer.readByte() & 0x0FF);
		ExtentionHeader header;
		switch(type)
		{
			case MBMS_SUPPORT_INDICATION:
				header=new MBMSSupportHeaderImpl();
				break;
			case MS_INFO_CHANGE_REPORTING_SUPPORT_INDICATION:
				header=new MSInfoChargingReportngSupportIndicatorHeaderImpl();
				break;
			case PDPC_PDU_NUMBER:
				header=new PDCPPduNumberExtentionHeaderImpl();				
				break;
			case SUSPEND_REQUEST:
				header=new SuspendRequestHeaderImpl();
				break;
			case SUSPEND_RESPONSE:
				header=new SuspendResponseHeaderImpl();
				break;
			case EMPTY_NEGATIVE:
				header=new EmptyNegativeExtentionHeaderImpl();
				break;
			case EMPTY:
			default:
				header=new EmptyExtentionHeaderImpl();
				break;
		}
		
		header.decode(buffer);
		return header;
	}
}