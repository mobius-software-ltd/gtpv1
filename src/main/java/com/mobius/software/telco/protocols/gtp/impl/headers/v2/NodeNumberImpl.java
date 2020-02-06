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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.NodeNumber;
import com.mobius.software.telco.protocols.gtp.api.utils.StringFunctions;

public class NodeNumberImpl extends AbstractTLV2 implements NodeNumber 
{
	private String address;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.NODE_NUMBER;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=1;
		String value=address;
		if(value!=null)
			length=(value.length()+1)/2;
		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(address!=null)
		{
			byte[] data=StringFunctions.octetsToBytes(address);
			buffer.writeByte(data.length);
			buffer.writeBytes(data);
		}
		else
			throw new MissingArgumentException("Address not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte currByte=buffer.readByte();
		byte[] data=new byte[currByte];
		buffer.readBytes(data);
	}

	@Override
	public String getNodeNumber() 
	{
		return address;
	}

	@Override
	public void setNodeNumber(String address) 
	{
		this.address=address;
	}
}