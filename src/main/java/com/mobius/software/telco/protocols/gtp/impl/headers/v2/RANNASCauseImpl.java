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

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.exceptions.MissingArgumentException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ProtocolType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RANNASCause;

public class RANNASCauseImpl extends AbstractTLV2 implements RANNASCause 
{
	private ProtocolType protocolType;
	private Integer causeType;
	
	private Integer number;
	private Boolean isByte=false;
	private Boolean isShort=false;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.RAN_NAS_CAUSE;
	}

	@Override
	public Integer getLength() 
	{
		if(isByte!=null && isByte)
			return 2;
		
		if(isShort!=null && isShort)
			return 3;
		
		return 5;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		buffer.writeByte(((protocolType.getValue()<<4) & 0xF0) | (causeType & 0x0F));
		if(number!=null)
		{
			if(isByte!=null && isByte)
				buffer.writeByte(number);
			else if(isShort!=null && isShort)
				buffer.writeShort(number);
			else
				buffer.writeInt(number);
		}
		else
			throw new MissingArgumentException("Value is not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) throws GTPParseException 
	{
		byte currByte=buffer.readByte();
		protocolType=ProtocolType.fromInt((currByte>>4) & 0x0F);
		causeType=(currByte & 0x0F);
		if(length==2)
			number=buffer.readByte() & 0x0FF;
		else if(length==3)
			number=buffer.readShort() & 0x0FFFF;
		else if(length==5)
			number=buffer.readInt();
		else
			throw new GTPParseException("Invalid length for integer number");
	}

	@Override
	public Integer getValue() 
	{
		return number;
	}

	@Override
	public void setNumberAsByte(Integer value)
	{
		isByte=true;
		isShort=false;
		number=value;
	}
	
	@Override
	public void setNumberAsShort(Integer value)
	{
		isByte=false;
		isShort=true;
		number=value;
	}
	
	@Override
	public void setNumberAsInteger(Integer value)
	{
		isByte=false;
		isShort=false;
		number=value;
	}

	@Override
	public ProtocolType getProtocolType() 
	{
		return protocolType;
	}

	@Override
	public void setProtocolType(ProtocolType protocolType) 
	{
		this.protocolType=protocolType;
	}

	@Override
	public Integer setCauseType() 
	{
		return causeType;
	}

	@Override
	public void getCauseType(Integer value) 
	{
		this.causeType=value;
	}
}