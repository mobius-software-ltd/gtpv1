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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FQCSID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.NodeIDType;

public class FQCSIDImpl extends AbstractTLV2 implements FQCSID 
{
	private Inet4Address ipv4Address;
	private Inet6Address ipv6Address;
	private Long uint32;
	List<Integer> csids;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.FQ_CSID;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=1;
		if(ipv6Address!=null)
			length+=16;
		else
			length+=4;
		
		if(csids!=null)
			length+=csids.size()*2;
		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{		
		if(ipv6Address==null && ipv4Address==null && uint32==null)
			throw new MissingArgumentException("One of identifiers should be set");
		
		Integer csidsCount=0;
		if(csids!=null)
			csidsCount=csids.size();
		
		if(ipv6Address!=null)
			buffer.writeByte((NodeIDType.IPV6.getValue() & 0x0F)<<4 | (csidsCount & 0x0F));			
		else if(ipv4Address!=null)
			buffer.writeByte((NodeIDType.IPV4.getValue() & 0x0F)<<4 | (csidsCount & 0x0F));
		else
			buffer.writeByte((NodeIDType.UINT32.getValue() & 0x0F)<<4 | (csidsCount & 0x0F));		
		
		if(ipv6Address!=null)
			buffer.writeBytes(ipv6Address.getAddress());				
		else if(ipv4Address!=null)
			buffer.writeBytes(ipv4Address.getAddress());
		else
			buffer.writeInt(uint32.intValue());
		
		if(csidsCount>0)
		{
			for(Integer curr:csids)
				buffer.writeShort(curr);
		}
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) throws GTPParseException 
	{
		byte currByte=buffer.readByte();
		NodeIDType nodeType=NodeIDType.fromInt((currByte >>4) & 0x0F);
		try
		{
			if(nodeType==NodeIDType.IPV6)
			{
				byte[] data=new byte[16];
				buffer.readBytes(data);
				ipv6Address=(Inet6Address)InetAddress.getByAddress(data);
			}
			else if(nodeType==NodeIDType.IPV4)
			{
				byte[] data=new byte[4];
				buffer.readBytes(data);
				ipv4Address=(Inet4Address)InetAddress.getByAddress(data);
			}				
			else if(nodeType==NodeIDType.UINT32)
				uint32=buffer.readUnsignedInt();
		}
		catch(UnknownHostException ex)
		{
			throw new GTPParseException(ex.getMessage());
		}
		
		csids=new ArrayList<Integer>();
		int csidsCount=currByte & 0x0F;
		for(int i=0;i<csidsCount;i++)
			csids.add(buffer.readUnsignedShort());
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
	public Long getUInt() 
	{
		return uint32;
	}

	@Override
	public void setUInt(Long uint32) 
	{
		this.uint32=uint32;
	}

	@Override
	public List<Integer> getCSIDs() 
	{
		return this.csids;
	}

	@Override
	public void setCSIDs(List<Integer> value) 
	{
		this.csids=value;
	}
}