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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.APN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;


public class APNImpl extends AbstractTLV2 implements APN 
{
	private String apn;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.APN;
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
	public Integer getLength() 
	{
		Integer length=1;
		if(apn!=null)
			length+=apn.length();
		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(apn!=null)
		{
			String[] segments=apn.split("\\.");			
			for(String curr:segments)
			{
				buffer.writeByte(curr.length());
				buffer.writeBytes(curr.getBytes());
			}
		}
		else
			throw new MissingArgumentException("APN not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte[] data=new byte[length];
		buffer.readBytes(data);
		int currIndex=0;
		this.apn="";
		while(currIndex<data.length)
		{
			int currLength=data[currIndex++] & 0x0FF;
			if(this.apn.length()>0)
				this.apn+=".";
			
			this.apn+=new String(data,currIndex,currLength);
			currIndex+=currLength;
		}
	}
}