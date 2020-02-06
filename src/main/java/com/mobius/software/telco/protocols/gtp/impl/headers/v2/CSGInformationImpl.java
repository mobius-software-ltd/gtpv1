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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CSGAccessMode;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CSGInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CSGMembership;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;

public class CSGInformationImpl extends AbstractTLV2 implements CSGInformation 
{
	private NetworkIdentityImpl ni;
	private Integer csgID;
	private CSGAccessMode csgAccessMode;
	private CSGMembership csgMembership;
	private Boolean leaveCSG;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.USER_CSG_INFORMATION;
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
		
		if(csgID==null)
			throw new MissingArgumentException("CSG ID is not set");
		
		ni.encode(buffer);
		
		buffer.writeByte((csgID>>16 ) & 0x07);
		buffer.writeShort(csgID);
		byte currByte=0;
		if(csgAccessMode!=null)
			currByte|=((csgAccessMode.getValue()<<6) & 0xC0);
		
		if(csgMembership!=null)
			currByte|=(csgMembership.getValue()& 0x01);
		
		if(leaveCSG!=null && leaveCSG)
			currByte|=0x02;
		
		buffer.writeByte(currByte);
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		this.ni=new NetworkIdentityImpl();
		this.ni.decode(buffer);
		
		csgID=buffer.readByte()<<16 & 0x070000;
		csgID|=buffer.readShort();
		
		byte currValue=buffer.readByte();
		csgMembership=CSGMembership.fromInt(currValue & 0x01);
		csgAccessMode=CSGAccessMode.fromInt((currValue>>6) & 0x01);
		leaveCSG=(currValue & 0x02)!=0;
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
	public Integer getCSGID() 
	{
		return this.csgID;
	}

	@Override
	public void setCSGID(Integer csgID) 
	{
		this.csgID=csgID;
	}

	@Override
	public CSGAccessMode getCSGAccessMode() 
	{
		return this.csgAccessMode;
	}

	@Override
	public void setCSGAccessMode(CSGAccessMode csgAccessMode) 
	{
		this.csgAccessMode=csgAccessMode;
	}
	
	@Override
	public CSGMembership getCSGMembership() 
	{
		return this.csgMembership;
	}

	@Override
	public void setCSGMembership(CSGMembership csgMembership) 
	{
		this.csgMembership=csgMembership;
	}

	@Override
	public Boolean getLeaveCSG() 
	{
		return this.leaveCSG;
	}

	@Override
	public void setLeaveCSG(Boolean value) 
	{
		this.leaveCSG=value;
	}
}
