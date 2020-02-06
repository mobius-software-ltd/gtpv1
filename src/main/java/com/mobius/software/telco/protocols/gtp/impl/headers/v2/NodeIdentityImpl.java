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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.NodeIdentifier;


public class NodeIdentityImpl extends AbstractTLV2 implements NodeIdentifier 
{
	private String nodeName;
	private String nodeRealm;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.NODE_IDENTIFIER;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=2;
		if(nodeName!=null)
			length+=nodeName.length();
		
		if(nodeRealm!=null)
			length+=nodeRealm.length();
		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(nodeName!=null)
		{
			byte[] nodeNameBytes=nodeName.getBytes();
			buffer.writeByte(nodeNameBytes.length);
			buffer.writeBytes(nodeNameBytes);
		}
		else
			throw new MissingArgumentException("Node name not set");
		
		if(nodeRealm!=null)
		{
			byte[] nodeRealmBytes=nodeRealm.getBytes();
			buffer.writeByte(nodeRealmBytes.length);
			buffer.writeBytes(nodeRealmBytes);
		}
		else
			throw new MissingArgumentException("Node realm not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte[] data=new byte[buffer.readByte()];
		buffer.readBytes(data);
		nodeName=new String(data);
		
		data=new byte[buffer.readByte()];
		buffer.readBytes(data);
		nodeRealm=new String(data);
	}

	@Override
	public String getNodeName() 
	{
		return nodeName;
	}

	@Override
	public void setNodeName(String nodeName) 
	{
		this.nodeName=nodeName;
	}

	@Override
	public String getNodeRealm() 
	{
		return this.nodeRealm;
	}

	@Override
	public void setNodeRealm(String nodeRealm) 
	{
		this.nodeRealm=nodeRealm;
	}
}