package com.mobius.software.telco.protocols.gtp.impl.headers.v2;
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

import com.mobius.software.telco.protocols.gtp.api.exceptions.MissingArgumentException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CompleteRequestMessage;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CompleteRequestMessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;

public class CompleteRequestMessageImpl extends AbstractTLV2 implements CompleteRequestMessage 
{
	private CompleteRequestMessageType messageType;
	private ByteBuf value;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.COMPLETE_REQUEST_MESSAGE;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=1;
		ByteBuf value=getValue();
		if(value!=null)
			length=value.readableBytes();
		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(messageType!=null)
			buffer.writeByte((byte)(messageType.getValue() & 0x0FF));
		else
			throw new MissingArgumentException("Message Type not set");
		
		if(getValue()!=null)
			buffer.writeBytes(getValue());
		else
			throw new MissingArgumentException("Value not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		messageType=CompleteRequestMessageType.fromInt(buffer.readByte() & 0x0FF);
		setValue(buffer.slice(buffer.readerIndex(), length));
		buffer.skipBytes(length);
	}

	@Override
	public ByteBuf getValue() 
	{
		return this.value;
	}

	@Override
	public void setValue(ByteBuf value) 
	{
		this.value=value;
	}

	@Override
	public CompleteRequestMessageType getMessageType() 
	{
		return this.messageType;
	}

	@Override
	public void setMessageType(CompleteRequestMessageType messageType) 
	{
		this.messageType=messageType;		
	}
}