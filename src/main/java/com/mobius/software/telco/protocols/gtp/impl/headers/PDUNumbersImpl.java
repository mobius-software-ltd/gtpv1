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
import com.mobius.software.telco.protocols.gtp.api.headers.ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.PDUNumbers;

public class PDUNumbersImpl extends AbstractTLV implements PDUNumbers 
{
	private Integer dlSequenceNumber;
	private Integer ulSequenceNumber;
	private Integer sendNumber;
	private Integer receiveNumber;
	private Integer nsapi;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.PDU_NUMBERS;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return true;
	}

	@Override
	public Integer getLength() 
	{
		return 9;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(nsapi!=null)
			buffer.writeByte(nsapi.byteValue() & 0x0F);
		else
			throw new MissingArgumentException("NSAPI is not set");
		
		if(dlSequenceNumber!=null)
			buffer.writeShort(dlSequenceNumber);
		else
			throw new MissingArgumentException("DL Sequence number is not set");			
		
		if(ulSequenceNumber!=null)
			buffer.writeShort(ulSequenceNumber);
		else
			throw new MissingArgumentException("UL Sequence number is not set");
		
		if(sendNumber!=null)
			buffer.writeShort(sendNumber);
		else
			throw new MissingArgumentException("Send number is not set");
		
		if(receiveNumber!=null)
			buffer.writeShort(receiveNumber);
		else
			throw new MissingArgumentException("Receive number is not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		nsapi=(buffer.readByte() & 0x0F);
		dlSequenceNumber=buffer.readUnsignedShort();
		ulSequenceNumber=buffer.readUnsignedShort();
		sendNumber=buffer.readUnsignedShort();
		receiveNumber=buffer.readUnsignedShort();
	}

	@Override
	public Integer getNSAPI() 
	{
		return nsapi;
	}

	@Override
	public void setNSAPI(Integer nsapi) 
	{
		this.nsapi=nsapi;
	}

	@Override
	public Integer getDLSequenceNumber() 
	{
		return dlSequenceNumber;
	}

	@Override
	public void setDLSequenceNumber(Integer dlSequenceNumber) 
	{
		this.dlSequenceNumber = dlSequenceNumber;
	}

	@Override
	public Integer getULSequenceNumber() 
	{
		return ulSequenceNumber;
	}

	@Override
	public void setULSequenceNumber(Integer ulSequenceNumber) 
	{
		this.ulSequenceNumber = ulSequenceNumber;
	}

	@Override
	public Integer getSendNumber() 
	{
		return sendNumber;
	}

	@Override
	public void setSendNumber(Integer sendNumber) 
	{
		this.sendNumber = sendNumber;
	}

	@Override
	public Integer getReceiveNumber() 
	{
		return receiveNumber;
	}

	@Override
	public void setReceiveNumber(Integer receiveNumber) 
	{
		this.receiveNumber = receiveNumber;
	}	
}