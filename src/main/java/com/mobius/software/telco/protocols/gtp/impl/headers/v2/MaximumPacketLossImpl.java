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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MaximumPacketLossRate;

public class MaximumPacketLossImpl extends AbstractTLV2 implements MaximumPacketLossRate 
{
	private Integer ulRate;
	private Integer dlRate;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.MAXIMUM_PACKET_LOSS_RATE;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=1;
		if(ulRate!=null)
			length+=2;
		
		if(dlRate!=null)
			length+=2;
		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		byte currByte=0;
		if(ulRate!=null)
			currByte|=0x01;
		
		if(dlRate!=null)
			currByte|=0x02;
		
		buffer.writeByte(currByte);
		
		if(ulRate!=null)
			buffer.writeShort(ulRate.shortValue());			
		
		if(dlRate!=null)
			buffer.writeShort(dlRate.shortValue());	
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte currByte=buffer.readByte();
		if((currByte & 0x01)!=0)
			ulRate=buffer.readUnsignedShort();
		
		if((currByte & 0x02)!=0)
			dlRate=buffer.readUnsignedShort();			
	}

	@Override
	public Integer getMaximumPacketLossRateUL() 
	{
		return ulRate;
	}

	@Override
	public void setMaximumPacketLossRateUL(Integer rate) 
	{
		this.ulRate=rate;
	}

	@Override
	public Integer getMaximumPacketLossRateDL() 
	{
		return dlRate;
	}

	@Override
	public void setMaximumPacketLossRateDL(Integer rate) 
	{
		this.dlRate=rate;
	}
}