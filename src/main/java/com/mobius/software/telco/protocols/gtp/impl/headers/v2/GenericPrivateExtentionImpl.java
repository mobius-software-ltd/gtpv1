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
import io.netty.buffer.Unpooled;

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;

public class GenericPrivateExtentionImpl extends AbstractTLV2 implements PrivateExtention
{
	private ByteBuf data;
	private Integer enterpriseID;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.PRIVATE_EXTENTION;
	}

	@Override
	public Integer getLength() 
	{
		return data.readableBytes()+2;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws GTPParseException 
	{
		buffer.writeShort(enterpriseID);
		buffer.writeBytes(Unpooled.wrappedBuffer(data));
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) throws GTPParseException 
	{
		enterpriseID=buffer.readUnsignedShort();
		data=buffer.slice(buffer.readerIndex(), length-2);
		buffer.skipBytes(length-2);
	}

	@Override
	public Integer getEnterpriseID() 
	{
		return enterpriseID;
	}

	@Override
	public void setEnterpriseID(Integer value) 
	{
		this.enterpriseID=value;
	}
}