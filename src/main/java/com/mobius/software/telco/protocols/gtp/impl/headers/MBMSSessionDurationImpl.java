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
import com.mobius.software.telco.protocols.gtp.api.headers.MBMSSessionDuration;

public class MBMSSessionDurationImpl extends AbstractTLV implements MBMSSessionDuration 
{
	private Long timeInSeconds;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.MBMS_SESSION_DURATION;
	}

	@Override
	public Integer getLength() 
	{
		return 3;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return true;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(timeInSeconds!=null)
		{
			int seconds=(int)(timeInSeconds%86400);
			buffer.writeByte(((seconds >>9) & 0x0FF));
			buffer.writeByte(((seconds >>1) & 0x0FF));
			int days=(int)(timeInSeconds/86400);
			days=((days&0x7F) | ((seconds<<7) & 0x80));
			buffer.writeByte(days);
		}
		else
			throw new MissingArgumentException("time in seconds is not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		int seconds=((buffer.readByte() & 0x0FF)<<9);
		seconds|=((buffer.readByte() & 0x0FF)<<1);
		
		int currByte=buffer.readByte();
		seconds|=((currByte>>7) & 0x01);
		
		int days=(currByte&0x7F);
		timeInSeconds=days*86400L + seconds;
	}

	@Override
	public Long getTimeInSeconds() 
	{
		return timeInSeconds;
	}

	@Override
	public void setTimeInSeconds(Long timeInSeconds) 
	{
		this.timeInSeconds=timeInSeconds;
	}
}