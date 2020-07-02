package com.mobius.software.telco.protocols.gtp.impl.messages;
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
import com.mobius.software.telco.protocols.gtp.api.messages.GenericGTPMessage;
import com.mobius.software.telco.protocols.gtp.impl.messages.v2.GTP2MessageFactory;

public class MessageFactory 
{
	public static GenericGTPMessage decode(ByteBuf buffer,Boolean ignoreUnknown) throws GTPParseException
	{
		byte currByte=buffer.readByte();
		Integer protocol=(currByte>>5)&0x07;	
		if(protocol==1)
			return GTPMessageFactory.decode(currByte, buffer,ignoreUnknown);
		else if(protocol==2)
			return GTP2MessageFactory.decode(currByte, buffer, ignoreUnknown);		
		else
			throw new GTPParseException("Invalid message protocol version");
	}
}