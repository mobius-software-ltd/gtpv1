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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TransactionIdentifier;

public class TransactionIdentifierImpl extends AbstractTLV2 implements TransactionIdentifier 
{
	private Boolean originator;
	private Integer ti;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.TRANSACTION_IDENTIFIER;
	}

	@Override
	public Integer getLength() 
	{
		if(ti==null || ti<7)
			return 1;
		
		return 2;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(ti!=null)
		{
			byte currByte=0;
			if(originator==null || !originator)
				currByte=(byte)0x80;
			
			if(ti<7)
			{
				currByte|=((ti<<4) & 0x70);
				buffer.writeByte(currByte);
			}
			else
			{
				currByte|=0x70;
				buffer.writeByte(currByte);
				buffer.writeByte(0x80 | ti);
			}
		}
		else
			throw new MissingArgumentException("TI is not set");	
		
		if(ti!=null)
			buffer.writeByte(ti.byteValue());			
		else
			throw new MissingArgumentException("Counter is not set");	
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte currByte=buffer.readByte();
		originator=(currByte & 0x80)==0;
		ti=((currByte & 0x70)>>4);
		if(ti==7)
			ti=buffer.readByte() & 0x07F;
	}

	@Override
	public Integer getTI() 
	{
		return ti;
	}

	@Override
	public void setTI(Integer value) 
	{
		this.ti=value;
	}

	@Override
	public Boolean getOriginator() 
	{
		return originator;
	}

	@Override
	public void setOriginator(Boolean value) 
	{
		this.originator=value;
	}
}