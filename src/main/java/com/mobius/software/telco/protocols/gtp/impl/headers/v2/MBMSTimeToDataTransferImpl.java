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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MBMSTimeToDataTransfer;

public class MBMSTimeToDataTransferImpl extends AbstractTLV2 implements MBMSTimeToDataTransfer 
{
	private Integer seconds;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.MBMS_TIME_TO_DATA_TRANSFER;
	}

	@Override
	public Integer getLength() 
	{
		return 1;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(seconds!=null)
			buffer.writeByte((seconds-1) & 0x0FF);
		else
			throw new MissingArgumentException("seconds is not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		seconds=(buffer.readByte() & 0x0FF) + 1;
	}

	@Override
	public Integer getSeconds() 
	{
		return seconds;
	}

	@Override
	public void setSeconds(Integer seconds) 
	{
		this.seconds=seconds;
	}
}