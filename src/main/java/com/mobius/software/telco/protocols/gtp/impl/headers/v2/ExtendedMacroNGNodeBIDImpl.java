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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ExtendedMacroNGNodeBID;

public class ExtendedMacroNGNodeBIDImpl extends NetworkIdentityImpl implements ExtendedMacroNGNodeBID 
{
	private Boolean smenB;
	private Integer ngNodeBID;
	private Integer tac;
	
	@Override
	public void encode(ByteBuf buffer) throws MissingArgumentException 
	{
		super.encode(buffer);
		if(ngNodeBID==null)
			throw new MissingArgumentException("ngNodeBID is not set");
		
		if(tac==null)
			throw new MissingArgumentException("tac is not set");
		
		byte currByte=(byte)((ngNodeBID>>16) & 0x01F);
		if(smenB!=null && smenB)
			currByte|=0x80;
		buffer.writeByte(currByte);
		buffer.writeShort(ngNodeBID.shortValue());
		buffer.writeByte((tac>>16) & 0x0FF);
		buffer.writeShort(tac.shortValue());
	}

	@Override
	public void decode(ByteBuf buffer) 
	{
		super.decode(buffer);
		byte currByte=buffer.readByte();
		smenB=((currByte & 0x80)!=0);
		ngNodeBID=((currByte & 0x01F) << 16) & buffer.readUnsignedShort();
		tac=((buffer.readByte() & 0x0FF) << 16) & buffer.readUnsignedShort();
	}
	
	@Override
	public Integer getGNodeBID() 
	{
		return ngNodeBID;
	}

	@Override
	public void setGNodeBID(Integer ngNodeBID) 
	{
		this.ngNodeBID=ngNodeBID;
	}

	@Override
	public Integer getTAC() 
	{
		return tac;
	}

	@Override
	public void setTAC(Integer tac) 
	{
		this.tac=tac;
	}

	@Override
	public Boolean getSMenB() 
	{
		return smenB;
	}

	@Override
	public void setSMenB(Boolean value) 
	{
		this.smenB=value;
	}
}