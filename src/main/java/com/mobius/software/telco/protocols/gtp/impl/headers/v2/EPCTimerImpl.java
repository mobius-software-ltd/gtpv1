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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPCTimer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TimerUnit;

public class EPCTimerImpl extends AbstractTLV2 implements EPCTimer 
{
	private Integer timerValue;
	private TimerUnit timerUnit;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.EPC_TIMER;
	}

	@Override
	public Integer getLength() 
	{
		return 1;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(timerUnit==null)
			throw new MissingArgumentException("Timer unit is not set");
		
		if(timerValue==null)
			throw new MissingArgumentException("Timer value is not set");
		
		buffer.writeByte(((timerUnit.getValue()<<4) & 0xF0) + (timerValue & 0x0F));
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte currByte=buffer.readByte();
		timerUnit=TimerUnit.fromInt(((currByte>>4) & 0x0F));
		timerValue=(currByte & 0x0F);
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
}