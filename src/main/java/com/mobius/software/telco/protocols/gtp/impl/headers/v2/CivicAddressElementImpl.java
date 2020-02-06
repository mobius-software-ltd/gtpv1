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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CivicAddressElement;

public class CivicAddressElementImpl implements CivicAddressElement 
{
	Integer caType;
	String value;
	
	@Override
	public Integer getLength()
	{
		if(value!=null)
			return value.getBytes().length+2;
		
		return 2;
	}
	
	@Override
	public void encode(ByteBuf buffer) throws MissingArgumentException 
	{
		if(caType==null)
			throw new MissingArgumentException("CAType is not set");
		
		if(value==null)
			throw new MissingArgumentException("Value is not set");
		
		buffer.writeByte(caType.byteValue());
		byte[] data=value.getBytes();
		buffer.writeByte(data.length);
		buffer.writeBytes(data);
	}

	@Override
	public void decode(ByteBuf buffer) 
	{
		caType=buffer.readByte() & 0x0FF;
		byte[] data=new byte[buffer.readByte() & 0x0FF];
		buffer.readBytes(data);
		value=new String(data);
	}

	@Override
	public Integer getCAType() 
	{
		return caType;
	}

	@Override
	public void setCAType(Integer value) 
	{
		this.caType=value;
	}

	@Override
	public String getValue() 
	{
		return this.value;
	}

	@Override
	public void setValue(String value) 
	{
		this.value=value;
	}
}