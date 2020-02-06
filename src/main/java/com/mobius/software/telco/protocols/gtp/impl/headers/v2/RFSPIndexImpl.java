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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RFSPIndex;

public class RFSPIndexImpl extends AbstractTLV2 implements RFSPIndex 
{
	private Integer rfspIndex;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.RFSP_INDEX;
	}

	@Override
	public Integer getLength() 
	{
		return 2;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(rfspIndex!=null)
			buffer.writeShort(rfspIndex.shortValue());			
		else
			throw new MissingArgumentException("RFSP Index is not set");				
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		rfspIndex=buffer.readUnsignedShort();		
	}

	@Override
	public Integer getIndex() 
	{
		return rfspIndex;
	}

	@Override
	public void setIndex(Integer index) 
	{
		this.rfspIndex=index;
	}
}