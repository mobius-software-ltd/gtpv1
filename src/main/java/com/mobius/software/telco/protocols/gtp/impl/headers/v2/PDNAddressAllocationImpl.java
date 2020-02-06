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
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import io.netty.buffer.ByteBuf;

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.exceptions.MissingArgumentException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PDN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PDNAddressAllocation;

public class PDNAddressAllocationImpl extends AbstractTLV2 implements PDNAddressAllocation 
{
	private Inet4Address ipv4Address;
	private Inet6Address ipv6Address;
	private Integer ipv6Prefix;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.PDN_ADDRESS_ALLOCATION;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=1;
		if(ipv4Address!=null)
			length+=4;
		
		if(ipv6Address!=null)
			length+=17;
		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{		
		if(ipv6Address==null && ipv6Prefix!=null)
			throw new MissingArgumentException("IPV6 address is not set");
		
		if(ipv6Address!=null && ipv6Prefix==null)
			throw new MissingArgumentException("IPV6 prefix is not set");
		
		if(ipv6Address!=null)
		{
			if(ipv4Address!=null)
				buffer.writeByte(PDN.BOTH.getValue() & 0x07);
			else
				buffer.writeByte(PDN.IPV6.getValue() & 0x07);
		}
		else
		{
			if(ipv4Address!=null)
				buffer.writeByte(PDN.IPV4.getValue() & 0x07);
			else
				buffer.writeByte(PDN.NON_IP.getValue() & 0x07);
		}
		
		if(ipv6Address!=null)
		{
			buffer.writeByte(ipv6Prefix);
			buffer.writeBytes(ipv6Address.getAddress());				
		}
		
		if(ipv4Address!=null)
			buffer.writeBytes(ipv4Address.getAddress());		
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) throws GTPParseException 
	{
		PDN pdn=PDN.fromInt(buffer.readByte() & 0x07);
		try
		{
			if(pdn==PDN.BOTH || pdn==PDN.IPV6)
			{
				ipv6Prefix=buffer.readByte() & 0x0FF;
				byte[] data=new byte[16];
				buffer.readBytes(data);
				ipv6Address=(Inet6Address)InetAddress.getByAddress(data);
			}
	
			if(pdn==PDN.BOTH || pdn==PDN.IPV4)
			{
				byte[] data=new byte[4];
				buffer.readBytes(data);
				ipv4Address=(Inet4Address)InetAddress.getByAddress(data);
			}				
		}
		catch(UnknownHostException ex)
		{
			throw new GTPParseException(ex.getMessage());
		}
	}

	@Override
	public Inet4Address getV4Address() 
	{
		return this.ipv4Address;
	}

	@Override
	public Inet6Address getV6Address() 
	{
		return this.ipv6Address;
	}

	@Override
	public void setV4Address(Inet4Address address) 
	{
		this.ipv4Address=address;
	}

	@Override
	public void setV6Address(Inet6Address address) 
	{
		this.ipv6Address=address;
	}

	@Override
	public Integer getIPv6Prefix() 
	{
		return ipv6Prefix;
	}

	@Override
	public void setIPv6Prefix(Integer ipv6Prefix) 
	{
		this.ipv6Prefix=ipv6Prefix;
	}
}