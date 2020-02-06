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
import com.mobius.software.telco.protocols.gtp.api.headers.AddressOfRecommendedNode;
import com.mobius.software.telco.protocols.gtp.api.headers.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.messages.RedirectionRequest;

public class RedirectionRequestImpl extends AbstractGTPTagMessage implements RedirectionRequest
{
	private List<PrivateExtention> privateExtentions;
	private AddressOfRecommendedNode nodeAddress;
	private AddressOfRecommendedNode alernateNodeAddress;
	private Cause cause;
	
	@Override
	public MessageType getMessageType() 
	{
		return MessageType.REDIRECTION_REQUEST;
	}

	@Override
	public void applyTLV(TLV1 tlv) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case CAUSE:
				cause=(Cause)tlv;
				break;
			case ADDRESS_OF_RECOMMENDED_NODE:
				if(nodeAddress==null)
					nodeAddress=(AddressOfRecommendedNode)tlv;
				else if(alernateNodeAddress==null)
					alernateNodeAddress=(AddressOfRecommendedNode)tlv;
				else
					throw new GTPParseException("Unknown TLV received,type:" + tlv.getElementType());
				break;
			case PRIVATE_EXTENTION:
				if(privateExtentions==null)
					privateExtentions=new ArrayList<PrivateExtention>();
				
				privateExtentions.add((PrivateExtention)tlv);
				break;
			default:
				throw new GTPParseException("Unknown TLV received,type:" + tlv.getElementType());
		}
	}

	@Override
	public List<TLV1> getTLVs() throws GTPParseException 
	{
		ArrayList<TLV1> output=new ArrayList<TLV1>();
		if(cause==null)
			throw new GTPParseException("Cause is missing");
		
		output.add(cause);
		if(nodeAddress!=null)
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
	public AddressOfRecommendedNode getRecommendedNodeAddress() 
	{
		return nodeAddress;
	}

	@Override
	public AddressOfRecommendedNode getAlternateRecommendedNodeAdress() 
	{
		return alernateNodeAddress;
	}

	@Override
	public Cause getCause() 
	{
		return cause;
	}

	@Override
	public void setRecommendedNodeAddress(AddressOfRecommendedNode address) 
	{
		this.nodeAddress=address;
	}

	@Override
	public void setAlternateRecommendedNodeAdress(AddressOfRecommendedNode address) 
	{
		this.alernateNodeAddress=address;
	}

	@Override
	public void setCause(Cause cause) 
	{
		this.cause=cause;
	}
}