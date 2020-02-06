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
import com.mobius.software.telco.protocols.gtp.api.headers.DaylightSavings;
import com.mobius.software.telco.protocols.gtp.api.headers.ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.MSTimezone;

public class MSTimezoneImpl extends AbstractTLV implements MSTimezone 
{
	private DaylightSavings daylightSavings;
	private Integer timezone;
	private Byte originalPrefix;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.MS_TIMEZONE;
	}

	@Override
	public Integer getLength() 
	{
		return 2;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return true;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(timezone==null)
			throw new MissingArgumentException("Timezone is not set");
		
		if(daylightSavings==null)
			throw new MissingArgumentException("Daylight savings is not set");		
		
		buffer.writeByte(timezone.byteValue());
		
		byte currByte=(byte)daylightSavings.getValue();
		if(originalPrefix!=null)
			currByte|=originalPrefix;
		
		buffer.writeByte(currByte);
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		timezone=buffer.readByte() & 0x0FF;
		byte currByte=buffer.readByte();
		originalPrefix=(byte)(currByte & 0xFC);
		daylightSavings=DaylightSavings.fromInt(currByte & 0x03);
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
