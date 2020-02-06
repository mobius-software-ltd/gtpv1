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
import com.mobius.software.telco.protocols.gtp.api.headers.PTMSISignature;

public class PTMSISignatureImpl extends AbstractTLV implements PTMSISignature 
{
	private Integer signature;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.PTMSI_SIGNATURE;
	}

	@Override
	public Integer getLength() 
	{
		return 3;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return false;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(signature!=null)
		{
			buffer.writeByte((byte)((signature>>16) & 0x0FF));
			buffer.writeShort(signature);
		}
		else
			throw new MissingArgumentException("Signature is not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		signature=((buffer.readByte()<<16) & 0x00FF0000);
		signature|=buffer.readUnsignedShort();
	}
	
	@Override
	public Integer getSignature() 
	{
		return signature;
	}

	@Override
	public void setSignature(Integer signature) 
	{
		this.signature=signature;
	}
}