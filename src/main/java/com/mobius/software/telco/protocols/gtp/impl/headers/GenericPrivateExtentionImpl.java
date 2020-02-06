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
import io.netty.buffer.Unpooled;

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;

public class GenericPrivateExtentionImpl extends AbstractTLV implements PrivateExtention
{
	private ByteBuf data;
	private Integer extentionID;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.PRIVATE_EXTENTION;
	}

	@Override
	public Integer getLength() 
	{
		return data.readableBytes()+2;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return true;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws GTPParseException 
	{
		buffer.writeShort(extentionID);
		buffer.writeBytes(Unpooled.wrappedBuffer(data));
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) throws GTPParseException 
	{
		extentionID=buffer.readUnsignedShort();
		data=buffer.slice(buffer.readerIndex(), length-2);
		buffer.skipBytes(length-2);
	}

	@Override
	public Integer getExtentionID() 
	{
		return extentionID;
	}

	@Override
	public void setExtentionID(Integer value) 
	{
		this.extentionID=value;
	}
}