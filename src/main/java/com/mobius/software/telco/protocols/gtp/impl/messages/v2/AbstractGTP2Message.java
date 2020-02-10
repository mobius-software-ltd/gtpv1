package com.mobius.software.telco.protocols.gtp.impl.messages.v2;
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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.GTP2Message;
import com.mobius.software.telco.protocols.gtp.impl.headers.v2.TLV2Factory;

public abstract class AbstractGTP2Message implements GTP2Message 
{
	private Boolean hasTEID=false;
	private Boolean hasMessagePriority=false;
	private Integer sequenceNumber;
	private Long teid;
	private Integer priority;
	
	private List<TLV2> originalTLVs=new ArrayList<TLV2>();
	
	@Override
	public Integer getVersion()
	{
		return 2;
	}
	
	@Override
	public Boolean getTEIDFlag() 
	{
		return hasTEID;
	}

	@Override
	public Boolean getMessagePriorityFlag() 
	{
		return hasMessagePriority;
	}

	@Override
	public Integer getMessagePriority() 
	{
		return priority;
	}

	@Override
	public void setMessagePriority(Integer priority)
	{
		this.priority=priority;
		if(this.priority!=null)
			hasMessagePriority=true;
		else
			hasMessagePriority=false;
	}

	@Override
	public Integer getSequenceNumber() 
	{
		return sequenceNumber;
	}

	@Override
	public void setSequenceNumber(Integer sequence)
	{
		this.sequenceNumber=sequence;			
	}

	@Override
	public Long getTEID() 
	{
		return teid;
	}

	@Override
	public void setTEID(Long teid) 
	{
		this.teid=teid;
		if(this.teid!=null)
			hasTEID=true;
		else
			hasTEID=false;
	}
	
	protected Integer getExtraHeadersLength() 
	{
		Integer result=4;
		
		if(hasTEID!=null && hasTEID)
			result+=4;				
		
		return result;
	}

	protected byte getHeaderByte() 
	{
		byte result=0;
		if(hasTEID)
			result|=0x08;
		
		if(hasMessagePriority)
			result|=0x04;
		
		return result;
	}

	protected void writeExtraHeaders(ByteBuf buffer) throws GTPParseException 
	{
		if(teid!=null)
			buffer.writeInt(teid.intValue());
		
		buffer.writeByte((sequenceNumber>>16) & 0x0FF);
		buffer.writeShort(sequenceNumber.shortValue());
		
		if(hasMessagePriority)
			buffer.writeByte((priority<<4) & 0x0F0);
		else
			buffer.writeByte(0);
	}

	protected Integer readExtraHeaders(ByteBuf buffer) throws GTPParseException 
	{
		Integer usedBytes=0;
		if(hasTEID)
		{
			teid=buffer.readUnsignedInt();
			usedBytes+=4;
		}
		
		usedBytes+=4;
		sequenceNumber=((buffer.readByte() <<16) & 0x0FF0000) + buffer.readUnsignedShort();
		
		if(hasMessagePriority)
			priority=(buffer.readByte()>>4) & 0x0F;
		else
			buffer.readByte();
		
		return usedBytes;
	}

	@Override
	public void applyHeaderByte(byte header) 
	{
		if((header & 0x04)!=0)
			hasMessagePriority=true;
		
		if((header & 0x08)!=0)
			hasTEID=true;		
	}

	@Override
	public abstract GTP2MessageType getMessageType();	

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
		
		List<TLV2> tlv=getTLVs();
		for(TLV2 currTLV:tlv)
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
		
		List<TLV2> tlv=originalTLVs;
		for(TLV2 currTLV:tlv)
			currTLV.encode(buffer);
	}
	
	@Override
	public Integer getLength() throws GTPParseException 
	{
		int length=getExtraHeadersLength();
		List<TLV2> tlv=getTLVs();
		for(TLV2 currTLV:tlv)
			length+=currTLV.getLength() + 4;
		
		return length;
	}
	
	@Override
	public void decode(ByteBuf buffer) throws GTPParseException 
	{
		int length=buffer.readUnsignedShort();
		length-=readExtraHeaders(buffer);
		while(length>0)
		{
			TLV2 currTLV=TLV2Factory.decode(getMessageType(), buffer);
			if(currTLV!=null)
			{
				applyTLV(currTLV);
				originalTLVs.add(currTLV);
			}
			
			length-=currTLV.getLength() + 4;
		}
	}

	@Override
	public abstract void applyTLV(TLV2 tlv) throws GTPParseException;

	@Override
	public abstract List<TLV2> getTLVs() throws GTPParseException;
}
