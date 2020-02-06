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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.DaylightSavings;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UETimezone;

public class UETimezoneImpl extends AbstractTLV2 implements UETimezone 
{
	private DaylightSavings daylightSavings;
	private Integer timezone;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.UE_TIMEZONE;
	}

	@Override
	public Integer getLength() 
	{
		return 2;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(timezone==null)
			throw new MissingArgumentException("Timezone is not set");
		
		if(daylightSavings==null)
			throw new MissingArgumentException("Daylight savings is not set");		
		
		buffer.writeByte(timezone.byteValue());
		buffer.writeByte(daylightSavings.getValue());
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		timezone=buffer.readByte() & 0x0FF;
		daylightSavings=DaylightSavings.fromInt(buffer.readByte() & 0x0FF);
	}

	@Override
	public Integer getTimezone() 
	{
		return this.timezone;
	}

	@Override
	public void setTimezone(Integer timezone) 
	{
		this.timezone=timezone;
	}

	@Override
	public DaylightSavings getDaylightSavings() 
	{
		return this.daylightSavings;
	}

	@Override
	public void setDaylightSavings(DaylightSavings daylightSavings) 
	{
		this.daylightSavings=daylightSavings;
	}
}
