package com.mobius.software.telco.protocols.gtp.impl.messages;
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

import java.util.ArrayList;
import java.util.List;

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.messages.GTPTagMessage;
import com.mobius.software.telco.protocols.gtp.impl.headers.TLVFactory;

public abstract class AbstractGTPTagMessage implements GTPTagMessage
{
	private Integer sequenceNumber;
	
	private List<TLV1> original=new ArrayList<TLV1>();
	
	@Override
	public Integer getVersion()
	{
		return 1;
	}
	
	@Override
	public Integer getSequenceNumber() 
	{
		return sequenceNumber;
	}

	@Override
	public void setSequenceNumber(Integer sequenceNumber) 
	{
		this.sequenceNumber=sequenceNumber;
	}

	protected Integer getExtraHeadersLength() 
	{
		return 0;
	}

	protected byte getHeaderByte() 
	{
		return 0x0F;
	}

	protected void writeExtraHeaders(ByteBuf buffer) 
	{
		buffer.writeShort(sequenceNumber);
	}

	protected Integer readExtraHeaders(ByteBuf buffer) 
	{
		sequenceNumber=buffer.readUnsignedShort();
		return 0;
	}

	@Override
	public void applyHeaderByte(byte header) 
	{
		
	}

	@Override
	public abstract MessageType getMessageType();

	@Override
	public Integer getMessageTypeValue()
	{
		return getMessageType().getValue();
	}
	
	@Override
	public void encode(ByteBuf buffer) throws GTPParseException 
	{
		byte header=getHeaderByte();
		header=(byte)(header & 0x0F);
		header|=((getVersion() <<5) & 0xE0);
		buffer.writeByte(header);
		buffer.writeByte(getMessageTypeValue());
		buffer.writeShort(getLength());
		writeExtraHeaders(buffer);
		
		List<TLV1> tlv=getTLVs();
		for(TLV1 currTLV:tlv)
			currTLV.encode(buffer);
	}

	@Override
	public void encodeOriginal(ByteBuf buffer) throws GTPParseException 
	{
		byte header=getHeaderByte();
		header=(byte)(header & 0x0F);
		header|=((getVersion() <<5) & 0xE0);
		buffer.writeByte(header);
		buffer.writeByte(getMessageTypeValue());
		buffer.writeShort(getLength());
		writeExtraHeaders(buffer);
		
		List<TLV1> tlv=original;
		for(TLV1 currTLV:tlv)
			currTLV.encode(buffer);
	}
	
	@Override
	public Integer getLength() throws GTPParseException 
	{
		int length=getExtraHeadersLength();
		List<TLV1> tlv=getTLVs();
		for(TLV1 currTLV:tlv)
			if(currTLV.lengthPresent())
				length+=currTLV.getLength() + 3;
			else
				length+=currTLV.getLength() + 1;
		
		return length;
	}

	@Override
	public abstract void applyTLV(TLV1 tlv,Boolean ignoreUnknown) throws GTPParseException;
	
	@Override
	public abstract List<TLV1> getTLVs() throws GTPParseException;

	@Override
	public void decode(ByteBuf buffer,Boolean ignoreUnknown) throws GTPParseException 
	{
		int length=buffer.readUnsignedShort();
		length-=readExtraHeaders(buffer);
		while(length>0)
		{
			TLV1 currTLV=TLVFactory.decode(buffer);
			if(currTLV!=null)
			{
				applyTLV(currTLV,ignoreUnknown);
				original.add(currTLV);
			}
			
			if(currTLV.lengthPresent())
				length-=currTLV.getLength() + 3;
			else
				length-=currTLV.getLength() + 1;		
		}
	}
}
