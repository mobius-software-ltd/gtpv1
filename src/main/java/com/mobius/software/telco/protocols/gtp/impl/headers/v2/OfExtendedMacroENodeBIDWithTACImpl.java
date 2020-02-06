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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ExtendedMacroENodeBID;

public class OfExtendedMacroENodeBIDWithTACImpl extends NetworkIdentityImpl implements ExtendedMacroENodeBID 
{
	private Integer eNodeBID;
	private Boolean sMenB;
	
	@Override
	public void encode(ByteBuf buffer) throws MissingArgumentException 
	{
		super.encode(buffer);
		if(eNodeBID==null)
			throw new MissingArgumentException("eNodeBID is not set");
		
		if(sMenB==null || !sMenB)
		{
			buffer.writeByte((eNodeBID>>16) & 0x01F);
			buffer.writeShort(eNodeBID & 0x0FFFF);
		}
		else
		{
			buffer.writeByte(((eNodeBID>>16) & 0x03) | 0x80);
			buffer.writeShort(eNodeBID & 0x0FFFF);
		}
	}

	@Override
	public void decode(ByteBuf buffer) 
	{
		super.decode(buffer);
		byte currByte=buffer.readByte();
		if((currByte & 0x80)!=0)
		{
			sMenB=true;
			eNodeBID=(currByte<<16 & 0x03) + buffer.readUnsignedShort();
		}
		else
		{
			sMenB=false;
			eNodeBID=(currByte<<16 & 0x1F) + buffer.readUnsignedShort();
		}
	}

	@Override
	public Integer getENodeBID() 
	{
		return eNodeBID;
	}

	@Override
	public void setENodeBID(Integer eNodeBID) 
	{
		this.eNodeBID=eNodeBID;
	}

	@Override
	public Boolean getSMenB() 
	{
		return sMenB;
	}

	@Override
	public void setSMenB(Boolean value) 
	{
		this.sMenB=value;
	}
}