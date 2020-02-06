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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.AMBR;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;

public class AMBRImpl extends AbstractTLV2 implements AMBR 
{
	private Long uplink;
	private Long downlink;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.AMBR;
	}

	@Override
	public Integer getLength() 
	{
		return 8;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(uplink!=null)
			buffer.writeInt(uplink.intValue());
		else
			throw new MissingArgumentException("Uplink is not set");
		
		if(downlink!=null)
			buffer.writeInt(downlink.intValue());
		else
			throw new MissingArgumentException("Downlink is not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		uplink=buffer.readUnsignedInt();
		downlink=buffer.readUnsignedInt();
	}

	@Override
	public Long getUplink() 
	{
		return uplink;
	}

	@Override
	public void setUplink(Long uplink) 
	{
		this.uplink=uplink;
	}
	
	@Override
	public Long getDownlink() 
	{
		return downlink;
	}

	@Override
	public void setDownlink(Long downlink) 
	{
		this.downlink=downlink;
	}
}