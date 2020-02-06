package com.mobius.software.telco.protocols.gtp.impl.headers;
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
import com.mobius.software.telco.protocols.gtp.api.headers.ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.PacketCommandType;
import com.mobius.software.telco.protocols.gtp.api.headers.PacketTransferCommand;

public class PacketTransferCommandImpl extends AbstractTLV implements PacketTransferCommand 
{
	private PacketCommandType commandType;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.PACKET_TRANSFER_COMMAND;
	}

	@Override
	public Integer getLength() 
	{
		return 1;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return false;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(commandType!=null)
			buffer.writeByte(commandType.getValue() & 0x0FF);
		else
			throw new MissingArgumentException("packet command type is not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		commandType=PacketCommandType.fromInt(buffer.readByte() & 0x0FF);
	}

	@Override
	public PacketCommandType getPacketCommandType() 
	{
		return commandType;
	}

	@Override
	public void setPacketCommandType(PacketCommandType commandType) 
	{
		this.commandType=commandType;
	}
}