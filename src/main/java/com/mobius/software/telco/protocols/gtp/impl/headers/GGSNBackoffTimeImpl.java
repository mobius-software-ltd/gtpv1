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
import com.mobius.software.telco.protocols.gtp.api.headers.GGSNBackoffTime;
import com.mobius.software.telco.protocols.gtp.api.headers.TimerUnit;

public class GGSNBackoffTimeImpl extends AbstractTLV implements GGSNBackoffTime 
{
	private TimerUnit timerUnit;
	private Integer timerValue;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.GGSN_BACK_OFF_TIME;
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
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(timerUnit==null)
			throw new MissingArgumentException("Timer Unit not set");
		
		if(timerValue==null)
			throw new MissingArgumentException("Timer Value not set");
		
		byte currByte=(byte)(timerValue & 0x1F);		
		currByte|=((timerUnit.getValue()<<5) & 0xE0);
		buffer.writeByte(currByte);			
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte currByte=buffer.readByte();
		timerUnit=TimerUnit.fromInt(((currByte>>5) & 0x07));
		timerValue=currByte & 0x1F;
	}

	@Override
	public TimerUnit getTimerUnit() 
	{
		return timerUnit;
	}

	@Override
	public void setTimerUnit(TimerUnit value) 
	{
		this.timerUnit=value;
	}
	
	@Override
	public Integer getTimerValue() 
	{
		return timerValue;
	}

	@Override
	public void setTimerValue(Integer value) 
	{
		this.timerValue=value;
	}
}