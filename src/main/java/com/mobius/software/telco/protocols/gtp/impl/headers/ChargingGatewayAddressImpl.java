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
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import io.netty.buffer.ByteBuf;

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.exceptions.MissingArgumentException;
import com.mobius.software.telco.protocols.gtp.api.headers.ChargingGatewayAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.ElementType;

public class ChargingGatewayAddressImpl extends AbstractTLV implements ChargingGatewayAddress 
{
	private Boolean isIPV4;
	private Inet4Address ipv4Address;
	private Inet6Address ipv6Address;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.CHARGING_GATEWAY_ADDRESS;
	}

	@Override
	public Integer getLength() 
	{
		if(isIPV4!=null && isIPV4)
			return 4;
		
		return 16;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return true;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(isIPV4==null)
			throw new MissingArgumentException("IP Address is not set");
		
		if(isIPV4)
			buffer.writeBytes(ipv4Address.getAddress());		
		else
			buffer.writeBytes(ipv6Address.getAddress());
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) throws GTPParseException 
	{
		byte[] data=new byte[length];
		buffer.readBytes(data);
		try
		{
			if(length==4)
			{
				isIPV4=true;
				ipv4Address=(Inet4Address)InetAddress.getByAddress(data);
			}
			else
			{
				isIPV4=false;
				ipv6Address=(Inet6Address)InetAddress.getByAddress(data);
			}
		}
		catch(UnknownHostException ex)
		{
			throw new GTPParseException("Invalid IP address format");
		}
	}

	@Override
	public Boolean isV4() 
	{
		return isIPV4;
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
		this.isIPV4=true;
		this.ipv4Address=address;
		this.ipv6Address=null;
	}

	@Override
	public void setV6Address(Inet6Address address) 
	{
		this.isIPV4=false;
		this.ipv4Address=null;
		this.ipv6Address=address;
	}
}
