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
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.RANTRansparentContainer;
import com.mobius.software.telco.protocols.gtp.api.headers.RIMRoutingAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.RIMRoutingAddressDiscriminator;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.messages.RANInformationRelayRequest;

public class RANInformationRelayImpl extends AbstractGTPMessage implements RANInformationRelayRequest
{
	private RANTRansparentContainer container;
	private RIMRoutingAddress rimRoutingAddress;
	private RIMRoutingAddressDiscriminator discriminator;
	
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public MessageType getMessageType() 
	{
		return MessageType.RAN_INFORMATION_RELAY;
	}

	@Override
	public void applyTLV(TLV1 tlv) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case RAN_TRANSPARENT_CONTAINER:
				container=(RANTRansparentContainer)tlv;
				break;
			case RIM_ROUTING_ADDRESS:
				rimRoutingAddress=(RIMRoutingAddress)tlv;
				break;
			case RIM_ROUTING_ADDRESS_DISCRIMITATOR:
				discriminator=(RIMRoutingAddressDiscriminator)tlv;
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
		if(container==null)
			throw new GTPParseException("RAN Transparent container not set");
			
		output.add(container);
	
		if(rimRoutingAddress!=null)
			output.add(rimRoutingAddress);
	
		if(discriminator!=null)
			output.add(discriminator);
	
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
	public RANTRansparentContainer getRANTRansparentContainer() 
	{
		return this.container;
	}

	@Override
	public void setRANTRansparentContainer(RANTRansparentContainer container) 
	{
		this.container=container;
	}

	@Override
	public RIMRoutingAddress getRIMRoutingAddress() 
	{
		return this.rimRoutingAddress;
	}

	@Override
	public void setRIMRoutingAddress(RIMRoutingAddress address) 
	{
		this.rimRoutingAddress=address;
	}

	@Override
	public RIMRoutingAddressDiscriminator getRIMRoutingAddressDiscriminator() 
	{
		return this.discriminator;
	}

	@Override
	public void setRIMRoutingAddressDiscriminator(RIMRoutingAddressDiscriminator discriminator) 
	{
		this.discriminator=discriminator;		
	}
}