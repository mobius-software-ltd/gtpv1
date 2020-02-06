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
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import io.netty.buffer.ByteBuf;

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.HomeENodeBIDWithTAC;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MacroENodeBIDWithTAC;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RIMRoutingAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RoutingAddressType;

public class RIMRoutingAddressImpl extends AbstractTLV2 implements RIMRoutingAddress
{
	private RoutingAddressType addressType;
	private MacroENodeBIDWithTAC macroENodeBID;
	private HomeENodeBIDWithTAC homeENodeBID;
	private Inet6Address hrpdSectorID;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.RIM_ROUTING_ADDRESS;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=0;
		if(macroENodeBID!=null)
			length+=8;
		else if(homeENodeBID!=null)
			length+=9;
		else if(hrpdSectorID!=null)
			length+=16;
		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws GTPParseException 
	{
		if(macroENodeBID!=null)
		{
			buffer.writeByte(RoutingAddressType.MACRO_ENODEBID.getValue());
			macroENodeBID.encode(buffer);
		} 
		else if(homeENodeBID!=null)
		{
			buffer.writeByte(RoutingAddressType.HOME_ENODEBID.getValue());
			homeENodeBID.encode(buffer);
		} 
		else if(hrpdSectorID!=null)
		{
			buffer.writeByte(RoutingAddressType.HRPD_SECTOR_IDENTIFIER.getValue());
			buffer.writeBytes(hrpdSectorID.getAddress());
		}			
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) throws GTPParseException 
	{
		addressType=RoutingAddressType.fromInt(buffer.readByte() & 0x0FF);
		switch(addressType)
		{
			case HOME_ENODEBID:
				homeENodeBID=new HomeENodeBIDWithTACImpl();
				homeENodeBID.decode(buffer);
				break;
			case MACRO_ENODEBID:
				macroENodeBID=new MacroENodeBIDWithTACImpl();
				macroENodeBID.decode(buffer);
				break;
			case HRPD_SECTOR_IDENTIFIER:
				try
				{
					byte[] data=new byte[16];
					buffer.readBytes(data);
					hrpdSectorID=(Inet6Address)InetAddress.getByAddress(data);
				}
				catch(UnknownHostException ex)
				{
					throw new GTPParseException(ex.getMessage());
				}
				break;
			case UNKNOWN:
				break;
			default:
				break;			
		}
	}

	@Override
	public HomeENodeBIDWithTAC getHomeENodeBID() 
	{
		return homeENodeBID;
	}

	@Override
	public void setHomeENodeBID(HomeENodeBIDWithTAC homeENodeBID) 
	{
		this.addressType=RoutingAddressType.HOME_ENODEBID;
		this.homeENodeBID=homeENodeBID;
	}

	@Override
	public MacroENodeBIDWithTAC getMacroENodeBID() 
	{
		return macroENodeBID;				
	}

	@Override
	public void setMacroENodeBID(MacroENodeBIDWithTAC macroENodeBID) 
	{
		this.addressType=RoutingAddressType.MACRO_ENODEBID;
		this.macroENodeBID=macroENodeBID;
	}

	@Override
	public Inet6Address getHRPDSectorID() 
	{
		return hrpdSectorID;
	}

	@Override
	public void setHRPDSectorID(Inet6Address hrpdSectorID) 
	{
		this.addressType=RoutingAddressType.HRPD_SECTOR_IDENTIFIER;
		this.hrpdSectorID=hrpdSectorID;
	}

	@Override
	public RoutingAddressType getRoutingAddressType() 
	{
		return addressType;
	}
}