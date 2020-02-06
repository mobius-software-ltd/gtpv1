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
import com.mobius.software.telco.protocols.gtp.api.headers.CSGID;
import com.mobius.software.telco.protocols.gtp.api.headers.ElementType;

public class CSGIDImpl extends AbstractTLV implements CSGID 
{
	private Integer csgID;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.CSG_ID;
	}

	@Override
	public Integer getLength() 
	{
		return 3;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return false;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(csgID==null)
			throw new MissingArgumentException("CSG ID is not set");
		
		buffer.writeByte((csgID>>16 ) & 0x07);
		buffer.writeShort(csgID);		
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		csgID=buffer.readByte()<<16 & 0x070000;
		csgID|=buffer.readShort();		
	}

	@Override
	public Integer getCSGID() 
	{
		return this.csgID;
	}

	@Override
	public void setCSGID(Integer csgID) 
	{
		this.csgID=csgID;
	}
}
