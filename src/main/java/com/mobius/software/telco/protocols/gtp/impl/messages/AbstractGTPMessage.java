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
import com.mobius.software.telco.protocols.gtp.api.headers.ExtentionHeader;
import com.mobius.software.telco.protocols.gtp.api.headers.ExtentionHeaderType;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.messages.GTPMessage;
import com.mobius.software.telco.protocols.gtp.impl.headers.TLVFactory;
import com.mobius.software.telco.protocols.gtp.impl.headers.extention.EmptyExtentionHeaderImpl;
import com.mobius.software.telco.protocols.gtp.impl.headers.extention.ExtentionHeaderFactory;

public abstract class AbstractGTPMessage implements GTPMessage 
{
	private Boolean hasExtentionHeaders=false;
	private Boolean hasNPDU=false;
	private Boolean hasSequenceNumber=false;
	private List<ExtentionHeader> extentionsHeaders;
	private Integer npdu;
	private Integer sequenceNumber;
	private Long teid;
	
	private List<TLV1> original=new ArrayList<TLV1>();
	
	@Override
	public Integer getVersion()
	{
		return 1;
	}
	
	@Override
	public void encode(ByteBuf buffer) throws GTPParseException 
	{
		byte header=getHeaderByte();
		header=(byte)(header & 0x1F);
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
		header=(byte)(header & 0x1F);
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
	public Boolean getExtentionHeaderFlag() 
	{
		return hasExtentionHeaders;
	}

	@Override
	public Boolean getNPDUFlag() 
	{
		return hasNPDU;
	}

	@Override
	public Integer getNPDUNumber() 
	{
		return npdu;
	}

	@Override
	public void setNPDUNumber(Integer npdu)
	{
		this.npdu=npdu;
		if(this.npdu!=null)
			hasNPDU=true;
		else
			hasNPDU=false;
	}
	
	@Override
	public Boolean getSequenceNumberFlag() 
	{
		return hasSequenceNumber;
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
		if(this.sequenceNumber!=null)
			hasSequenceNumber=true;
		else
			hasSequenceNumber=false;		
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
	}

	@Override
	public List<ExtentionHeader> getExtentionHeaders() 
	{
		return extentionsHeaders;
	}

	@Override
	public void setExtentionHeaders(List<ExtentionHeader> headers)
	{
		this.extentionsHeaders=headers;
		if(this.extentionsHeaders!=null && this.extentionsHeaders.size()!=0)
			hasExtentionHeaders=true;
		else
			hasExtentionHeaders=false;
	}
	
	protected Integer getExtraHeadersLength() 
	{
		Integer result=0;
		if(sequenceNumber!=null)
			result+=2;
		
		if(npdu!=null)
			result+=1;
		
		if(extentionsHeaders!=null)
		{
			for(ExtentionHeader curr:extentionsHeaders)
			{
				int currLength=curr.getLength();
				if(currLength==0)
					result+=2;				
				else
					result+=curr.getLength()*4;
			}
		}
		else
			result+=2;
		
		return result;
	}

	protected byte getHeaderByte() 
	{
		byte result=0x10;
		if(getExtentionHeaderFlag())
			result|=0x04;
		
		if(getSequenceNumberFlag())
			result|=0x02;
		
		if(getNPDUFlag())
			result|=0x01;
		
		return result;
	}

	protected void writeExtraHeaders(ByteBuf buffer) throws GTPParseException 
	{
		if(teid==null)
			throw new GTPParseException("teid not set");
		
		buffer.writeInt(teid.intValue());
		
		if(hasSequenceNumber)
			buffer.writeShort(sequenceNumber.shortValue());
		
		if(hasNPDU)
			buffer.writeByte(npdu.byteValue());
		
		if(hasExtentionHeaders)
		{
			if(extentionsHeaders!=null)
			{
				for(ExtentionHeader header:extentionsHeaders)
					header.encode(buffer);
			}
			
			if(extentionsHeaders==null || extentionsHeaders.size()==0 || (extentionsHeaders.get(extentionsHeaders.size()-1).getExtentionHeaderType()!=ExtentionHeaderType.EMPTY && extentionsHeaders.get(extentionsHeaders.size()-1).getExtentionHeaderType()!=ExtentionHeaderType.EMPTY_NEGATIVE))
			{
				EmptyExtentionHeaderImpl emptyHeader=new EmptyExtentionHeaderImpl();
				emptyHeader.encode(buffer);
			}
		}
		else
		{
			if(extentionsHeaders!=null && extentionsHeaders.size()!=0)
				extentionsHeaders.get(0).encode(buffer);
			else
			{
				EmptyExtentionHeaderImpl emptyHeader=new EmptyExtentionHeaderImpl();
				emptyHeader.encode(buffer);
			}
		}
	}

	protected Integer readExtraHeaders(ByteBuf buffer) throws GTPParseException 
	{
		Integer usedBytes=0;
		teid=buffer.readUnsignedInt();
		if(hasSequenceNumber)
		{
			usedBytes+=2;
			sequenceNumber=buffer.readUnsignedShort();
		}
		
		if(hasNPDU)
		{
			usedBytes+=1;
			npdu=buffer.readByte() & 0x0FF;
		}
		
		extentionsHeaders=new ArrayList<ExtentionHeader>();
		if(hasExtentionHeaders)
		{
			usedBytes+=1;
			ExtentionHeader header;
			do
			{
				header=ExtentionHeaderFactory.decode(buffer);
				if(header.getLength()!=0)
					usedBytes+=4*header.getLength();
				else
					usedBytes+=2;
				
				extentionsHeaders.add(header);
			}
			while(header.getExtentionHeaderType()!=ExtentionHeaderType.EMPTY && header.getExtentionHeaderType()!=ExtentionHeaderType.EMPTY_NEGATIVE);			
		}
		else 
		{
			ExtentionHeader header=ExtentionHeaderFactory.decode(buffer);
			extentionsHeaders.add(header);
			usedBytes+=2;
		}
		
		return usedBytes;
	}

	@Override
	public void applyHeaderByte(byte header) 
	{
		if((header & 0x01)!=0)
			hasNPDU=true;
		
		if((header & 0x02)!=0)
			hasSequenceNumber=true;
		
		if((header&0x04) !=0)
			hasExtentionHeaders=true;
	}

	@Override
	public abstract MessageType getMessageType();	

	@Override
	public Integer getMessageTypeValue()
	{
		return getMessageType().getValue();
	}
	
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

	@Override
	public abstract void applyTLV(TLV1 tlv,Boolean ignoreUnknown) throws GTPParseException;

	@Override
	public abstract List<TLV1> getTLVs() throws GTPParseException;
}
