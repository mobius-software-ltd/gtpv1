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
import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.exceptions.MissingArgumentException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.HCIndicator;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MBMSIPMulticastDistribution;

public class MBMSIPMulticastDistributionImpl extends AbstractTLV2 implements MBMSIPMulticastDistribution 
{
	private Long commonTunnelEndpointIdentifier;
	private List<InetAddress> addresses;
	private HCIndicator hcIndicator;
	
	@Override
	public Long getCommonTunnelEndpointIdentifier() 
	{
		return commonTunnelEndpointIdentifier;
	}

	@Override
	public void setCommonTunnelEndpointIdentifier(Long data) 
	{
		this.commonTunnelEndpointIdentifier=data;
	}

	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.MBMS_IP_MULTICAST_DISTRIBUTION;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=5;
		if(addresses!=null && addresses.size()>0)
			for(InetAddress curr:addresses)
				if(curr.getAddress().length==4)
					length+=5;
				else
					length+=17;
		
		return 4;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(commonTunnelEndpointIdentifier!=null)
			buffer.writeInt(commonTunnelEndpointIdentifier.intValue());
		else
			throw new MissingArgumentException("Common Tunnel Endpoint Identifier is not set");
		
		if(addresses!=null && addresses.size()>0)
		{
			for(InetAddress curr:addresses)
			{
				byte[] value=curr.getAddress();
				if(value.length==4)
				{
					buffer.writeByte(0x04);
					buffer.writeBytes(value);
				}
				else
				{
					buffer.writeByte(0x50);
					buffer.writeBytes(value);
				}
			}
		}
		else
			throw new MissingArgumentException("addresses are not set");
		
		if(hcIndicator!=null)
			buffer.writeByte(hcIndicator.getValue());
		else
			throw new MissingArgumentException("HC Indicator is not set");
		
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) throws GTPParseException 
	{
		commonTunnelEndpointIdentifier=buffer.readUnsignedInt();
		addresses=new ArrayList<InetAddress>();
		int usedBytes=4;
		while(usedBytes<length-1)
		{
			byte currHeader=buffer.readByte();
			if(currHeader==0x04)
			{
				byte[] addr=new byte[4];
				buffer.readBytes(addr);
				try
				{
					addresses.add(Inet4Address.getByAddress(addr));
				}
				catch(UnknownHostException ex)
				{
					throw new GTPParseException("Invalid IP address format");
				}
				usedBytes+=5;
			}
			else if(currHeader==0x50)
			{
				byte[] addr=new byte[16];
				buffer.readBytes(addr);
				try
				{
					addresses.add(Inet6Address.getByAddress(addr));
				}
				catch(UnknownHostException ex)
				{
					throw new GTPParseException("Invalid IP address format");
				}
				usedBytes+=17;
			}
			else
				throw new GTPParseException("Invalid IP address format");
		}
		
		hcIndicator=HCIndicator.fromInt(buffer.readByte());
	}

	@Override
	public List<InetAddress> getAddresses() 
	{
		return addresses;
	}

	@Override
	public void setAddresses(List<InetAddress> addresses) 
	{
		this.addresses=addresses;
	}

	@Override
	public HCIndicator getHCIndicator() 
	{
		return this.hcIndicator;	
	}

	@Override
	public void setHCIndicator(HCIndicator indicator) 
	{
		this.hcIndicator=indicator;
	}
}
