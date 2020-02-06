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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EUTRANRoundTripDelay;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;

public class EUTRANRoundTripDelayImpl extends AbstractTLV2 implements EUTRANRoundTripDelay 
{
	private Integer delay;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.EUTRAN_ROUND_TRIP_DELAY;
	}

	@Override
	public Integer getLength() 
	{
		return 2;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(delay!=null)
			buffer.writeShort(delay & 0x0FFF);			
		else
			throw new MissingArgumentException("Delay is not set");	
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		delay=buffer.readUnsignedShort() & 0x0FFF;
	}

	@Override
	public Integer getDelay() 
	{
		return delay;
	}

	@Override
	public void setDelay(Integer delay) 
	{
		this.delay=delay;
	}
}