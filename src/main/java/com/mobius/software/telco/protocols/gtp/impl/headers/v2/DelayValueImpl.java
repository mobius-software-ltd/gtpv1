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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.DelayValue;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;

public class DelayValueImpl extends AbstractTLV2 implements DelayValue 
{
	private Integer delayValue;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.DELAY_VALUE;
	}

	@Override
	public Integer getLength() 
	{
		return 1;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(delayValue!=null)
			buffer.writeByte(delayValue.byteValue());			
		else
			throw new MissingArgumentException("Delaty is not set");			
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		delayValue=buffer.readByte() & 0x0FF;
	}

	@Override
	public Integer getDelay() 
	{
		return delayValue;
	}

	@Override
	public void setDelay(Integer delay) 
	{
		this.delayValue=delay;
	}
}