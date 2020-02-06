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
import com.mobius.software.telco.protocols.gtp.api.headers.BSSRecordType;
import com.mobius.software.telco.protocols.gtp.api.headers.ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.HLRInvokingEvents;
import com.mobius.software.telco.protocols.gtp.api.headers.HLRRecordType;
import com.mobius.software.telco.protocols.gtp.api.headers.HLRTraceType;
import com.mobius.software.telco.protocols.gtp.api.headers.MSCInvokingEvents;
import com.mobius.software.telco.protocols.gtp.api.headers.MSCRecordType;
import com.mobius.software.telco.protocols.gtp.api.headers.MSCTraceType;

public class TraceTypeImpl extends AbstractTLV implements MSCTraceType,HLRTraceType 
{
	private Boolean priority;
	private HLRRecordType hlrRecordType;
	private BSSRecordType bssRecordType;
	private MSCRecordType mscRecordType;
	private HLRInvokingEvents hlrInvokingEvents;
	private MSCInvokingEvents mscInvokingEvents;
	
	@Override
	public Boolean getPriority() 
	{
		return priority;
	}

	@Override
	public void setPriority(Boolean priority) 
	{
		this.priority=priority;
	}

	@Override
	public HLRRecordType getHLRRecordType() 
	{
		return hlrRecordType;
	}

	@Override
	public void setHLRRecordType(HLRRecordType hlrRecordType) 
	{
		this.hlrRecordType=hlrRecordType;
	}

	@Override
	public HLRInvokingEvents getHLRInvokingEvents() 
	{
		return hlrInvokingEvents;
	}

	@Override
	public void setHLRInvokingEvents(HLRInvokingEvents invokingEvents) 
	{
		this.hlrInvokingEvents=invokingEvents;
	}

	@Override
	public MSCRecordType getMSCRecordType() 
	{
		return mscRecordType;
	}

	@Override
	public void setMSCRecordType(MSCRecordType mscRecordType) 
	{
		this.mscRecordType=mscRecordType;
	}

	@Override
	public BSSRecordType getBSSRecordType() 
	{
		return bssRecordType;
	}

	@Override
	public void setBSSRecordType(BSSRecordType bssRecordType) 
	{
		this.bssRecordType=bssRecordType;
	}

	@Override
	public MSCInvokingEvents getMSCInvokingEvents() 
	{
		return this.mscInvokingEvents;
	}

	@Override
	public void setMSCInvokingEvents(MSCInvokingEvents invokingEvents) 
	{
		this.mscInvokingEvents=invokingEvents;
	}

	@Override
	public ElementType getElementType() 
	{
		return ElementType.TRACE_TYPE;
	}

	@Override
	public Integer getLength() 
	{
		return 2;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return false;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		Integer value=0;
		if(priority!=null && priority)
			value|=0x080;
		
		if(mscRecordType!=null)
		{
			if(bssRecordType!=null)
				value|=(bssRecordType.getValue()<<4);
			else
				throw new MissingArgumentException("BSS Record Type is not set");
			
			value|=(mscRecordType.getValue()<<2);
			
			if(mscInvokingEvents!=null)
				value|=mscInvokingEvents.getValue();
			else
				throw new MissingArgumentException("MSC Invoking Events is not set");			
		}
		else if(hlrRecordType!=null)
		{
			value|=(hlrRecordType.getValue()<<2);
			
			if(hlrInvokingEvents!=null)
				value|=hlrInvokingEvents.getValue();
			else
				throw new MissingArgumentException("HLR Invoking Events is not set");			
		}
		else
			throw new MissingArgumentException("Neither MSC Record Type nor HLR Record Type are not set");
			
			
		buffer.writeByte(value);
		buffer.writeByte(0x00);
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		Integer value=buffer.readByte() & 0x0FF;
		priority=(value & 0xF0)!=0;
		bssRecordType=BSSRecordType.fromInt((value>>4) & 0x03);
		mscRecordType=MSCRecordType.fromInt((value>>2) & 0x03);
		hlrRecordType=HLRRecordType.fromInt((value>>2) & 0x03);
		mscInvokingEvents=MSCInvokingEvents.fromInt(value & 0x03);
		hlrInvokingEvents=HLRInvokingEvents.fromInt(value & 0x03);
		buffer.readByte();
	}
}