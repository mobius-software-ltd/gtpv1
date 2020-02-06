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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ChannelNeeded;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ChannelType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;

public class ChannelNeededImpl extends AbstractTLV2 implements ChannelNeeded 
{
	private ChannelType firstChannel;
	private ChannelType secondChannel;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.CHANNEL_NEEDED;
	}

	@Override
	public Integer getLength() 
	{
		return 1;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(firstChannel==null)
			throw new MissingArgumentException("First Channel is not set");
		
		if(secondChannel==null)
			throw new MissingArgumentException("Second Channel is not set");
		
		byte currValue=0;
		currValue+=firstChannel.getValue() & 0x03;
		currValue+=(secondChannel.getValue() & 0x03)<<2;
		buffer.writeByte(currValue);		
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte currValue=buffer.readByte();
		firstChannel=ChannelType.fromInt(currValue & 0x03);
		secondChannel=ChannelType.fromInt((currValue >> 2) & 0x03);
	}

	@Override
	public ChannelType getFirstChannel() 
	{
		return firstChannel;
	}

	@Override
	public void setFirstChannel(ChannelType value) 
	{
		this.firstChannel=value;
	}

	@Override
	public ChannelType getSecondChannel() 
	{
		return this.secondChannel;
	}

	@Override
	public void setSecondChannel(ChannelType value) 
	{
		this.secondChannel=value;
	}
}