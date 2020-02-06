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
import com.mobius.software.telco.protocols.gtp.api.headers.ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.EndUserAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.PDPOrganizationType;
import com.mobius.software.telco.protocols.gtp.api.headers.PDPTypeNumber;

public class EndUserAddressImpl extends AbstractTLV implements EndUserAddress 
{
	private Inet4Address ipv4Address;
	private Inet6Address ipv6Address;
	private PDPOrganizationType organizationType;
	private PDPTypeNumber typeNumber;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.END_USER_ADDRESS;
	}

	@Override
	public Integer getLength() 
	{
		if(organizationType==null)
			return 6;
		
		switch(organizationType)
		{
			case IP:
				if(typeNumber==null)
					return 6;
				
				switch (typeNumber) 
				{
					case IPV4:
						if(ipv4Address!=null)
							return 6;
						
						return 2;
					case IPV46:
						if(ipv4Address!=null)
						{
							if(ipv6Address!=null)
								return 22;
							
							return 6;
						}
						
						if(ipv6Address!=null)
							return 18;
						
						return 2;						
					case IPV6:
						if(ipv6Address!=null)
							return 18;
						
						return 2;						
					case PPP:					
					default:
						return 2;						
				
				}
			case PPP:	
			default:
				return 2;						
		}
	}

	@Override
	public Boolean lengthPresent() 
	{
		return true;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(organizationType==null)
			throw new MissingArgumentException("Organization type is not set");
		
		if(typeNumber==null)
			throw new MissingArgumentException("Type number is not set");
		
		buffer.writeByte(0xF0 | organizationType.getValue());
		buffer.writeByte(typeNumber.getValue());
		
		switch(organizationType)
		{
			case IP:
				switch (typeNumber) 
				{
					case IPV4:
						if(ipv4Address!=null)
							buffer.writeBytes(ipv4Address.getAddress());
						break;
					case IPV46:
						if(ipv4Address!=null)
							buffer.writeBytes(ipv4Address.getAddress());
						
						if(ipv6Address!=null)
							buffer.writeBytes(ipv6Address.getAddress());
						break;
					case IPV6:
						if(ipv6Address!=null)
							buffer.writeBytes(ipv6Address.getAddress());
						break;
					case PPP:						
					default:
						throw new MissingArgumentException("Invalid type number");						
				}
			case PPP:				
			default:
				break;
		}		
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) throws GTPParseException 
	{
		organizationType=PDPOrganizationType.fromInt(buffer.readByte() & 0x0F);
		typeNumber=PDPTypeNumber.fromInt(buffer.readByte());		
		
		if(length==2)
			return;
		
		try
		{
			switch(organizationType)
			{
				case IP:
					switch (typeNumber) 
					{
						case IPV4:
							byte[] data=new byte[4];
							buffer.readBytes(data);
							ipv4Address=(Inet4Address)InetAddress.getByAddress(data);		
							break;
						case IPV46:
							data=new byte[4];
							buffer.readBytes(data);
							ipv4Address=(Inet4Address)InetAddress.getByAddress(data);		
							data=new byte[16];
							buffer.readBytes(data);
							ipv6Address=(Inet6Address)InetAddress.getByAddress(data);		
							break;
						case IPV6:
							data=new byte[16];
							buffer.readBytes(data);
							ipv6Address=(Inet6Address)InetAddress.getByAddress(data);		
							break;
						case PPP:						
						default:
							throw new MissingArgumentException("Invalid type number");						
					}
				case PPP:				
				default:
					break;
			}
		}
		catch(UnknownHostException ex)
		{
			throw new GTPParseException("Invalid IP address format");
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
	public PDPOrganizationType getOrganizationType() 
	{
		return organizationType;
	}

	@Override
	public PDPTypeNumber getPDPTypeNumber() 
	{
		return typeNumber;
	}

	@Override
	public void setPDPOrganizationType(PDPOrganizationType organizationType) 
	{
		this.organizationType=organizationType;
	}

	@Override
	public void setPDPTypeNumber(PDPTypeNumber typeNumber) 
	{
		this.typeNumber=typeNumber;
	}
}