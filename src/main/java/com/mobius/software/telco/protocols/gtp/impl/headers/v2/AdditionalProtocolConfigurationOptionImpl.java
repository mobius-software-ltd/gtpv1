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
import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;

import com.mobius.software.telco.protocols.gtp.api.exceptions.MissingArgumentException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.AdditionalProtocolConfigurationOption;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ConfigurationProtocol;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Protocol;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ProtocolConfigurationOptionRecord;

public class AdditionalProtocolConfigurationOptionImpl extends AbstractTLV2 implements AdditionalProtocolConfigurationOption 
{
	private List<ProtocolConfigurationOptionRecord> records;
	private ConfigurationProtocol configurationProtocol;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.ADDITIONAL_PROTOCOL_CONFIGURATION_OPTIONS;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=1;
		if(records!=null)
			for(ProtocolConfigurationOptionRecord record:records)
				length+=3+record.getLength();
		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(configurationProtocol==null)
			throw new MissingArgumentException("Configuration Protocol is not set");
		
		buffer.writeByte(0x80 | configurationProtocol.getValue());
		if(records!=null)
		{
			for(ProtocolConfigurationOptionRecord currOption:records)
			{
				if(currOption.getProtocol()==null)
					throw new MissingArgumentException("Protocol is not set");
				
				ByteBuf data=currOption.getProtocolData();
				if(data==null)
					throw new MissingArgumentException("Data is not set");
				
				buffer.writeShort(currOption.getProtocol().getValue());
				buffer.writeByte(currOption.getLength());
				buffer.writeBytes(currOption.getProtocolData());
			}
		}
		else
			throw new MissingArgumentException("Records are not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		configurationProtocol=ConfigurationProtocol.fromInt(buffer.readByte() & 0x07);
		records=new ArrayList<ProtocolConfigurationOptionRecord>();
		while(length>1)
		{
			ProtocolConfigurationOptionRecordImpl currRecord=new ProtocolConfigurationOptionRecordImpl();
			currRecord.setProtocol(Protocol.fromInt(buffer.readUnsignedShort()));
			int innerLength=buffer.readByte() & 0x0FF;
			currRecord.setProtocolData(buffer.slice(buffer.readerIndex(), innerLength));
			buffer.skipBytes(innerLength);
			length-=3+innerLength;
			records.add(currRecord);
		}
	}

	@Override
	public List<ProtocolConfigurationOptionRecord> getOptionRecords() 
	{
		return records;
	}

	@Override
	public void setOptionRecords(List<ProtocolConfigurationOptionRecord> value) 
	{
		this.records=value;
	}

	@Override
	public ConfigurationProtocol getConfigurationProtocol() 
	{
		return configurationProtocol;
	}

	@Override
	public void setConfigurationProtocol(ConfigurationProtocol value) 
	{
		this.configurationProtocol=value;		
	}
}