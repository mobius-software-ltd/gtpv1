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
import io.netty.buffer.Unpooled;

import com.mobius.software.telco.protocols.gtp.api.exceptions.MissingArgumentException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PacketFlowID;

public class PacketFlowIDImpl extends AbstractTLV2 implements PacketFlowID 
{
	private Integer ebi;
	private ByteBuf packetFlowID;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.PACKET_FLOW_ID;
	}

	@Override
	public Integer getLength() 
	{
		if(packetFlowID!=null)
			return 1+packetFlowID.readableBytes();
		
		return 1;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(ebi!=null)
			buffer.writeByte(ebi & 0x0F);
		else
			throw new MissingArgumentException("EBI is not set");
		
		if(packetFlowID!=null)
		{
			packetFlowID.markReaderIndex();
			buffer.writeBytes(packetFlowID);
			packetFlowID.resetReaderIndex();
		}
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		ebi=buffer.readByte() & 0x0F;
		packetFlowID=buffer.slice(buffer.readerIndex(), length-1);
		buffer.skipBytes(length-1);
	}

	@Override
	public Integer getEBI() 
	{
		return ebi;
	}

	@Override
	public void setEBI(Integer value) 
	{
		this.ebi=value;				
	}

	@Override
	public ByteBuf getPacketFlowID() 
	{
		return Unpooled.wrappedBuffer(packetFlowID);
	}

	@Override
	public void setPacketFlowID(ByteBuf value) 
	{
		this.packetFlowID=value;
	}
}