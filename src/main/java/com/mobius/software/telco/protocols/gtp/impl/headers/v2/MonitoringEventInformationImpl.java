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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MonitoringEventInformation;


public class MonitoringEventInformationImpl extends AbstractTLV2 implements MonitoringEventInformation 
{
	private String scefID;
	private Long scefReferenceID;
	private Integer remainingNumberOfReports;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.MONITORING_EVENT_INFORMATION;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=7;
		if(scefID!=null)
			length+=scefID.length();
		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(scefReferenceID!=null)
			buffer.writeInt(scefReferenceID.intValue());
		else
			throw new MissingArgumentException("SCEF Reference ID not set");
		
		if(scefID!=null)
		{
			byte[] scefIDBytes=scefID.getBytes();
			buffer.writeByte(scefIDBytes.length);
			buffer.writeBytes(scefIDBytes);
		}
		else
			throw new MissingArgumentException("SCEF ID not set");
		
		if(remainingNumberOfReports!=null)
			buffer.writeShort(remainingNumberOfReports & 0x0FFFF);
		else
			throw new MissingArgumentException("Remaining number of reports not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		scefReferenceID=buffer.readUnsignedInt();
		
		byte[] data=new byte[buffer.readByte()];
		buffer.readBytes(data);
		scefID=new String(data);
		
		remainingNumberOfReports=buffer.readUnsignedShort();
	}

	@Override
	public Long getSCEFReferenceID() 
	{
		return scefReferenceID;
	}

	@Override
	public void setSCEFReferenceID(Long referenceID) 
	{
		this.scefReferenceID=referenceID;
	}

	@Override
	public String getSCEFID() 
	{
		return scefID;
	}

	@Override
	public void setSCEFID(String scefID) 
	{
		this.scefID=scefID;
	}

	@Override
	public Integer getRemainingNumberOfReports() 
	{
		return remainingNumberOfReports;
	}

	@Override
	public void setRemainingNumberOfReports(Integer remainingNumber) 
	{
		this.remainingNumberOfReports=remainingNumber;
	}
}