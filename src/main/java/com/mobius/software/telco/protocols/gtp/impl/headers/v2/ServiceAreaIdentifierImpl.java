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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ServiceAreaIdentifier;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;

public class ServiceAreaIdentifierImpl extends AbstractTLV2 implements ServiceAreaIdentifier 
{
	private NetworkIdentityImpl ni;
	private Integer sac;
	private Integer lac;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.SAI;
	}

	@Override
	public Integer getLength() 
	{
		return 7;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(ni==null)
			throw new MissingArgumentException("Network Identity is not set");
		
		if(lac==null)
			throw new MissingArgumentException("LAC is not set");
		
		if(sac==null)
			throw new MissingArgumentException("SAC is not set");
		
		ni.encode(buffer);
		buffer.writeShort(lac);
		buffer.writeShort(sac);		
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		this.ni=new NetworkIdentityImpl();
		this.ni.decode(buffer);
		lac=buffer.readUnsignedShort();
		sac=buffer.readUnsignedShort();		
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
	public Integer getSAC() 
	{
		return this.sac;
	}

	@Override
	public void setSAC(Integer sac) 
	{
		this.sac=sac;
	}
}