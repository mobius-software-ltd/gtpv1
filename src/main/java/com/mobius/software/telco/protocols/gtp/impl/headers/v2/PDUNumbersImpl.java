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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PDUNumbers;

public class PDUNumbersImpl extends AbstractTLV2 implements PDUNumbers 
{
	public Integer dlGTPUSequenceNumber;
	public Integer ulGTPUSequenceNumber;
	public Integer sendPDUSequenceNumber;
	public Integer receivePDUSequenceNumber;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.PDU_NUMBERS;
	}

	@Override
	public Integer getLength() 
	{
		return 8;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(dlGTPUSequenceNumber!=null)
			buffer.writeShort(dlGTPUSequenceNumber.shortValue());			
		else
			throw new MissingArgumentException("DL GTPU Sequence Number is not set");	
		
		if(ulGTPUSequenceNumber!=null)
			buffer.writeShort(ulGTPUSequenceNumber.shortValue());			
		else
			throw new MissingArgumentException("UL GTPU Sequence Number is not set");	
		
		if(sendPDUSequenceNumber!=null)
			buffer.writeShort(sendPDUSequenceNumber.shortValue());			
		else
			throw new MissingArgumentException("Send PDU Sequence Number is not set");	
		
		if(receivePDUSequenceNumber!=null)
			buffer.writeShort(receivePDUSequenceNumber.shortValue());			
		else
			throw new MissingArgumentException("Receive PDU Sequence Number is not set");							
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		dlGTPUSequenceNumber=buffer.readUnsignedShort();	
		ulGTPUSequenceNumber=buffer.readUnsignedShort();	
		sendPDUSequenceNumber=buffer.readUnsignedShort();	
		receivePDUSequenceNumber=buffer.readUnsignedShort();			
	}

	@Override
	public Integer getDLGTPUSequenceNumber() 
	{
		return dlGTPUSequenceNumber;
	}

	@Override
	public void setDLGTPUSequenceNumber(Integer sequenceNumber) 
	{
		this.dlGTPUSequenceNumber=sequenceNumber;
	}

	@Override
	public Integer getULGTPUSequenceNumber() 
	{
		return this.ulGTPUSequenceNumber;
	}

	@Override
	public void setULGTPUSequenceNumber(Integer sequenceNumber) 
	{
		this.ulGTPUSequenceNumber=sequenceNumber;
	}

	@Override
	public Integer getSendDPUSequenceNumber() 
	{
		return sendPDUSequenceNumber;
	}

	@Override
	public void setSendPDUSequenceNumber(Integer sequenceNumber) 
	{
		this.sendPDUSequenceNumber=sequenceNumber;
	}

	@Override
	public Integer getReceiveDPUSequenceNumber() 
	{
		return receivePDUSequenceNumber;
	}

	@Override
	public void setReceivePDUSequenceNumber(Integer sequenceNumber) 
	{
		this.receivePDUSequenceNumber=sequenceNumber;
	}
}