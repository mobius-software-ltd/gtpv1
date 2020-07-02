package com.mobius.software.telco.protocols.gtp.impl.messages;
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
import java.util.ArrayList;
import java.util.List;

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.ChargingGatewayAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.messages.NodeAliveRequest;

public class NodeAliveRequestImpl extends AbstractGTPTagMessage implements NodeAliveRequest
{
	private List<PrivateExtention> privateExtentions;
	private ChargingGatewayAddress nodeAddress;
	private ChargingGatewayAddress alernateNodeAddress;
	
	@Override
	public MessageType getMessageType() 
	{
		return MessageType.NODE_ALIVE_REQUEST;
	}

	@Override
	public void applyTLV(TLV1 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case CHARGING_GATEWAY_ADDRESS:
				if(nodeAddress==null)
					nodeAddress=(ChargingGatewayAddress)tlv;
				else if(alernateNodeAddress==null)
					alernateNodeAddress=(ChargingGatewayAddress)tlv;
				else
					throw new GTPParseException("Unknown TLV received,type:" + tlv.getElementType());
				break;
			case PRIVATE_EXTENTION:
				if(privateExtentions==null)
					privateExtentions=new ArrayList<PrivateExtention>();
				
				privateExtentions.add((PrivateExtention)tlv);
				break;
			default:
				if(ignoreUnknown==null || !ignoreUnknown)
					throw new GTPParseException("Unknown TLV received,type:" + tlv.getElementType());
		}
	}

	@Override
	public List<TLV1> getTLVs() throws GTPParseException 
	{
		ArrayList<TLV1> output=new ArrayList<TLV1>();
		if(nodeAddress==null)
			throw new GTPParseException("Node address is missing");
		
		output.add(nodeAddress);
		if(alernateNodeAddress!=null)
			output.add(alernateNodeAddress);
		
		if(privateExtentions!=null)
		{
			for(PrivateExtention curr:privateExtentions)
				output.add(curr);
		}
		
		return output;
	}

	@Override
	public List<PrivateExtention> getPrivateExtentions() 
	{
		return privateExtentions;
	}

	@Override
	public void setPrivateExtentions(List<PrivateExtention> privateExtention) 
	{
		this.privateExtentions=privateExtention;
	}

	@Override
	public ChargingGatewayAddress getNodeAddress() 
	{
		return nodeAddress;
	}

	@Override
	public ChargingGatewayAddress getAlternateNodeAdress() 
	{
		return alernateNodeAddress;
	}

	@Override
	public void setNodeAddress(ChargingGatewayAddress address) 
	{
		this.nodeAddress=address;
	}

	@Override
	public void setAlternateNodeAddress(ChargingGatewayAddress address) 
	{
		this.alernateNodeAddress=address;		
	}
}