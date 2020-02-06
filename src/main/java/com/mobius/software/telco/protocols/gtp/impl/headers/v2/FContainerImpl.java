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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ContainerType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FContainer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;

public class FContainerImpl extends AbstractTLV2 implements FContainer 
{
	private ContainerType containerType;
	private ByteBuf value;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.FCONTAINER;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=1;
		ByteBuf value=getValue();
		if(value!=null)
			length+=value.readableBytes();
		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(containerType!=null)
			buffer.writeByte(containerType.getValue() & 0x0F);
		else
			throw new MissingArgumentException("Container type not set");
		
		if(getValue()!=null)
			buffer.writeBytes(getValue());
		else
			throw new MissingArgumentException("Value not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		containerType=ContainerType.fromInt(buffer.readByte() & 0x0F);
		setValue(buffer.slice(buffer.readerIndex(), length-1));
		buffer.skipBytes(length-1);
	}

	@Override
	public ContainerType getContainerType() 
	{
		return containerType;
	}

	@Override
	public void setContainerType(ContainerType value) 
	{
		this.containerType=value;
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
}