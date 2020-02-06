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

import com.mobius.software.telco.protocols.gtp.api.exceptions.MissingArgumentException;
import com.mobius.software.telco.protocols.gtp.api.headers.ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.XIDParameters;

public class XIDParametersImpl extends AbstractTLV implements XIDParameters 
{
	private byte[] xidParameters;
	private Integer sapi;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.PS_HANDOVER_XID_PARAMETERS;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return true;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=1;
		if(xidParameters!=null)
			length+=1+xidParameters.length;
		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(sapi!=null)
			buffer.writeByte(sapi.byteValue() & 0x0F);
		else
			throw new MissingArgumentException("SAPI is not set");
		
		if(xidParameters==null)
			throw new MissingArgumentException("XID Parameters not set");
		
		buffer.writeByte(xidParameters.length);
		buffer.writeBytes(xidParameters);
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		sapi=(buffer.readByte() & 0x0F);
		xidParameters=new byte[buffer.readByte() & 0x0FF];
		buffer.readBytes(xidParameters);
	}

	@Override
	public byte[] getXIDParameters() 
	{
		return xidParameters;
	}

	@Override
	public void setXIDParameters(byte[] value) 
	{
		this.xidParameters=value;
	}

	@Override
	public Integer getSAPI() 
	{
		return sapi;
	}

	@Override
	public void setSAPI(Integer sapi) 
	{
		this.sapi=sapi;
	}
}