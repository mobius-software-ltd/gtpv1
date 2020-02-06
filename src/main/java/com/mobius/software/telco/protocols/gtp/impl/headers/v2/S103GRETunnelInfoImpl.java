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
import com.mobius.software.telco.protocols.gtp.api.exceptions.MissingArgumentException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.S103GRETunnelInfo;


public class S103GRETunnelInfoImpl extends AbstractTLV2 implements S103GRETunnelInfo 
{
	private String apn;
	private Long greKey;	
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.S103_GRE_TUNNEL_INFO;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=5;
		if(apn!=null)
			length+=apn.length();
		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(apn!=null)
		{
			buffer.writeByte(apn.getBytes().length);
			buffer.writeBytes(apn.getBytes());
		}
		else
			throw new MissingArgumentException("APN not set");
		
		if(greKey!=null)
			buffer.writeInt(greKey.intValue());
		else
			throw new MissingArgumentException("GRE Key is not set");		
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) throws GTPParseException 
	{
		byte[] data=new byte[buffer.readByte() & 0x0FF];
		buffer.readBytes(data);
		apn=new String(data);
		
		greKey=buffer.readUnsignedInt();
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
	public Long getGREKey() 
	{
		return greKey;
	}

	@Override
	public void setGREKey(Long value) 
	{
		this.greKey=value;
	}
}