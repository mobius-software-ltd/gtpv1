package com.mobius.software.telco.protocols.gtp.impl.headers.extention;
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
import com.mobius.software.telco.protocols.gtp.api.headers.ExtentionHeaderType;
import com.mobius.software.telco.protocols.gtp.api.headers.extention.PDCPPduNumberExtentionHeader;
import com.mobius.software.telco.protocols.gtp.impl.headers.AbstractExtentionHeader;

public class PDCPPduNumberExtentionHeaderImpl extends AbstractExtentionHeader implements PDCPPduNumberExtentionHeader
{
	Integer pdcpPDUNumber;
	
	@Override
	public ExtentionHeaderType getExtentionHeaderType()
	{
		return ExtentionHeaderType.PDPC_PDU_NUMBER;
	}

	@Override
	public Integer getLength() 
	{
		return 1;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(pdcpPDUNumber==null)
			throw new MissingArgumentException("PDCP PDU Number is not set");
		
		buffer.writeByte((pdcpPDUNumber >> 8) & 0xFF);
		buffer.writeByte((pdcpPDUNumber & 0xFF));
	}

	@Override
	protected void readValue(ByteBuf buffer,Integer length) 
	{
		Integer value=0;
		value |= (buffer.readByte()&0x0FF)<<8;
		value |= buffer.readByte();
		pdcpPDUNumber = value;
	}

	@Override
	public Integer getPDCPPduNumber() 
	{
		return pdcpPDUNumber;
	}
	
	public void setPDCPPduNumber(Integer value)
	{
		this.pdcpPDUNumber = value;
	}
}