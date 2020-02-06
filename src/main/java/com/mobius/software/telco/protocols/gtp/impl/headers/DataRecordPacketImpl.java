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
import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;

import com.mobius.software.telco.protocols.gtp.api.exceptions.MissingArgumentException;
import com.mobius.software.telco.protocols.gtp.api.headers.DataRecordPacket;
import com.mobius.software.telco.protocols.gtp.api.headers.DataRecordType;
import com.mobius.software.telco.protocols.gtp.api.headers.ElementType;

public class DataRecordPacketImpl extends AbstractTLV implements DataRecordPacket 
{
	private DataRecordType dataRecordType;
	private Integer applicationIdentifier;
	private Integer releaseIdentifier;
	private Integer versionIdentifier;
	private List<byte[]> records;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.DATA_RECORD_PACKET;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=4;
		
		if(records!=null)
			for(int i=0;i<records.size();i++)
				length+=records.get(i).length+2;
		
		return length;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return true;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(records!=null)
			buffer.writeByte(records.size() & 0x0FF);
		else
			throw new MissingArgumentException("records are not set");
		
		if(dataRecordType!=null)
			buffer.writeByte(dataRecordType.getValue() | 0x0FF);
		else
			throw new MissingArgumentException("record type is not set");
		
		if(applicationIdentifier==null)
			throw new MissingArgumentException("application identifier is not set");
		
		if(releaseIdentifier==null)
			throw new MissingArgumentException("release identifier is not set");
		
		if(versionIdentifier==null)
			throw new MissingArgumentException("version identifier is not set");
		
		buffer.writeByte(((applicationIdentifier<<4) & 0xF0) | (releaseIdentifier & 0x0F));
		buffer.writeByte(versionIdentifier);
		
		for(int i=0;i<records.size();i++)
		{
			buffer.writeShort(records.get(i).length);
			buffer.writeBytes(records.get(i));
		}
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		records=new ArrayList<byte[]>(buffer.readByte() & 0x0FF);
		dataRecordType=DataRecordType.fromInt(buffer.readByte() & 0x0FF);
		
		byte currByte=buffer.readByte();
		applicationIdentifier=((currByte>>4) & 0x0F);
		releaseIdentifier=(currByte & 0x0F);
		versionIdentifier=buffer.readByte() & 0x0FF;
		
		for(int i=0;i<records.size();i++)
		{
			int recordLength=buffer.readUnsignedShort();
			byte[] data=new byte[recordLength];
			buffer.readBytes(data);
		}
	}

	@Override
	public DataRecordType getDataRecordType() 
	{
		return dataRecordType;
	}

	@Override
	public void setDataRecordType(DataRecordType type) 
	{
		this.dataRecordType=type;
	}

	@Override
	public Integer getApplicationIdentifier() 
	{
		return this.applicationIdentifier;
	}

	@Override
	public void setApplicationIdentifier(Integer identifier) 
	{
		this.applicationIdentifier=identifier;
	}

	@Override
	public Integer getReleaseIdentifier() 
	{
		return this.releaseIdentifier;
	}

	@Override
	public void setReleaseIdentifier(Integer identifier) 
	{
		this.releaseIdentifier=identifier;
	}

	@Override
	public Integer getVersionIdentifier() 
	{
		return this.versionIdentifier;
	}

	@Override
	public void setVersionIdentifier(Integer identifier) 
	{
		this.versionIdentifier=identifier;
	}

	@Override
	public List<byte[]> getRecords() 
	{
		return records;
	}

	@Override
	public void setRecords(List<byte[]> value) 
	{
		this.records=value;
	}
}
