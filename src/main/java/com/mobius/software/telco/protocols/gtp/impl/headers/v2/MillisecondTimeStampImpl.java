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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MillisecondTimeStamp;

public class MillisecondTimeStampImpl extends AbstractTLV2 implements MillisecondTimeStamp 
{
	private Integer seconds;
	private Integer fractions;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.MILLISECONDS_TIMESTAMP;
	}

	@Override
	public Integer getLength() 
	{
		return 6;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(seconds!=null)
		{
			buffer.writeByte((seconds>>16) & 0x0FF);
			buffer.writeShort(seconds.shortValue());
		}
		else
			throw new MissingArgumentException("Seconds is not set");
		
		if(fractions!=null)
		{
			buffer.writeByte((fractions>>16) & 0x0FF);
			buffer.writeShort(fractions.shortValue());
		}
		else
			throw new MissingArgumentException("Fractions is not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		seconds=(buffer.readByte()<<16) & 0x0FF0000;
		seconds+=buffer.readShort();
		
		fractions=(buffer.readByte()<<16) & 0x0FF0000;
		fractions+=buffer.readShort();
	}

	@Override
	public Integer getSeconds() 
	{
		return seconds;
	}

	@Override
	public void setSeconds(Integer seconds) 
	{
		this.seconds=seconds;
	}

	@Override
	public Integer getFractions() 
	{
		return this.fractions;				
	}

	@Override
	public void setFractions(Integer fractions) 
	{
		this.fractions=fractions;
	}
}