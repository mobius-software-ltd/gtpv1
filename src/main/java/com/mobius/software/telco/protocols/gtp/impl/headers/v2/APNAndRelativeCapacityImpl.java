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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.APNAndRelativeCapacity;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;


public class APNAndRelativeCapacityImpl extends AbstractTLV2 implements APNAndRelativeCapacity 
{
	private String apn;
	private Integer relativeCapacity;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.APN_AND_RELATIVE_CAPACITY;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=2;
		String value=getAPN();
		if(value!=null)
			length+=value.length();
		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(relativeCapacity!=null)
			buffer.writeByte(relativeCapacity);
		else
			throw new MissingArgumentException("Relative capacity not set");
		
		if(apn!=null)
		{
			buffer.writeByte(apn.getBytes().length);
			buffer.writeBytes(apn.getBytes());
		}
		else
			throw new MissingArgumentException("APN not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		relativeCapacity=buffer.readByte() & 0x0FF;
		byte[] data=new byte[buffer.readByte() & 0x0FF];
		buffer.readBytes(data);
		apn=new String(data);
	}
	
	@Override
	public String getAPN() 
	{
		return apn;
	}

	@Override
	public void setAPN(String apn) 
	{
		this.apn=apn;
	}

	@Override
	public Integer getCapacity() 
	{
		return relativeCapacity;
	}

	@Override
	public void setCapacity(Integer capacity) 
	{
		this.relativeCapacity=capacity;
	}
}