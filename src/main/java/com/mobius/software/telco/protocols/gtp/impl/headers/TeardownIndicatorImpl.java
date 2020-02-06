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
import com.mobius.software.telco.protocols.gtp.api.headers.TeardownIndicator;

public class TeardownIndicatorImpl extends AbstractTLV implements TeardownIndicator 
{
	private Boolean teardownIndicator;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.TEARDOWN_IND;
	}

	@Override
	public Integer getLength() 
	{
		return 1;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return false;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(teardownIndicator!=null && teardownIndicator)
			buffer.writeByte(0xFF);
		else
			buffer.writeByte(0xFE);
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		teardownIndicator=(buffer.readByte() & 0x01)!=0;
	}

	@Override
	public Boolean getTeardownIndicator() 
	{
		return teardownIndicator;
	}

	@Override
	public void setTeardownIndicator(Boolean teardownIndicator) 
	{
		this.teardownIndicator=teardownIndicator;
	}
}