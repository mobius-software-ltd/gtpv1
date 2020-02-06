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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RAI;

public class RAIImpl extends NetworkIdentityImpl implements RAI 
{
	private Integer lac;
	private Integer rac;
	
	@Override
	public void encode(ByteBuf buffer) throws MissingArgumentException 
	{
		super.encode(buffer);
		if(lac==null)
			throw new MissingArgumentException("LAC is not set");
		
		if(rac==null)
			throw new MissingArgumentException("RAC is not set");
		
		buffer.writeShort(lac.shortValue());
		buffer.writeShort(rac.shortValue());
	}

	@Override
	public void decode(ByteBuf buffer) 
	{
		super.decode(buffer);
		lac=buffer.readShort() & 0x0FFFF;
		rac=buffer.readShort() & 0x0FFFF;
	}

	@Override
	public Integer getLAC() 
	{
		return this.lac;
	}

	@Override
	public void setLAC(Integer lac) 
	{
		this.lac=lac;
	}

	@Override
	public Integer getRAC() 
	{
		return this.rac;
	}

	@Override
	public void setRAC(Integer rac) 
	{
		this.rac=rac;
	}
}
