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

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.exceptions.InvalidMessageException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;

public abstract class AbstractTLV2 implements TLV2 
{
	private Integer instance;
	
	@Override
	public Integer getInstance()
	{
		return instance;
	}
	
	@Override
	public void setInstance(Integer value)
	{
		this.instance=value;
	}
	
	public Integer getElementTypeValue()
	{
		return getElementType().getValue();
	}
	
	@Override
	public abstract Integer getLength();

	protected abstract void writeValue(ByteBuf buffer) throws GTPParseException;
	
	protected abstract void readValue(ByteBuf buffer,Integer length) throws GTPParseException;
	
	@Override
	public void encode(ByteBuf buffer) throws GTPParseException 
	{
		buffer.writeByte(getElementType().getValue());
		buffer.writeByte(getLength()>>8);
		buffer.writeByte(getLength());
		buffer.writeByte(getInstance() & 0x0F);
		writeValue(buffer);
	}

	@Override
	public void decode(ByteBuf buffer) throws GTPParseException 
	{
		Integer length=0;
		if(buffer.readableBytes()<3)
			throw new InvalidMessageException("not enough bytes found in message");
		
		length|=(buffer.readByte() & 0x0FF)<<8;
		length|=(buffer.readByte() & 0x0FF);
		instance=buffer.readByte() & 0x0F;
		
		if(buffer.readableBytes()<length)
			throw new InvalidMessageException("not enough bytes found in message");
		
		readValue(buffer, length);
	}
}