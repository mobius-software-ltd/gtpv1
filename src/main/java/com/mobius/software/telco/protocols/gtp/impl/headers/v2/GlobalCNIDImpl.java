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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GlobalCNID;

public class GlobalCNIDImpl extends AbstractTLV2 implements GlobalCNID 
{
	private NetworkIdentityImpl ni;
	private Integer cnID;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.GLOBAL_CN_ID;
	}

	@Override
	public Integer getLength() 
	{
		return 5;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(ni==null)
			throw new MissingArgumentException("Network Identity is not set");
		
		if(cnID==null)
			throw new MissingArgumentException("CSG ID is not set");
		
		ni.encode(buffer);
		
		buffer.writeShort(cnID);		
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		this.ni=new NetworkIdentityImpl();
		this.ni.decode(buffer);		
		cnID=buffer.readUnsignedShort();		
	}

	@Override
	public String getMCC() 
	{
		if(ni==null)
			return null;
		
		return this.ni.getMCC();
	}

	@Override
	public void setMCC(String mcc) 
	{
		if(this.ni==null)
			this.ni=new NetworkIdentityImpl();
		
		this.ni.setMCC(mcc);
	}

	@Override
	public String getMNC() 
	{
		if(ni==null)
			return null;
		
		return this.ni.getMNC();
	}

	@Override
	public void setMNC(String mnc) 
	{
		if(this.ni==null)
			this.ni=new NetworkIdentityImpl();
		
		this.ni.setMNC(mnc);
	}

	@Override
	public Integer getCNID() 
	{
		return this.cnID;
	}

	@Override
	public void setCNID(Integer cnID) 
	{
		this.cnID=cnID;
	}
}
