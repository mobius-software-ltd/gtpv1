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
import com.mobius.software.telco.protocols.gtp.api.headers.APN;
import com.mobius.software.telco.protocols.gtp.api.headers.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.EndUserAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.ProtocolConfigurationOption;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifier1;
import com.mobius.software.telco.protocols.gtp.api.messages.PduNotificationRejectRequest;

public class PduNotificationRejectRequestImpl extends AbstractGTPMessage implements PduNotificationRejectRequest
{
	private Cause cause;
	private TunnerEndpointIdentifier1 tei;
	private EndUserAddress endUserAddress;
	private APN apn;
	private ProtocolConfigurationOption protocolConfigurationOption;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public MessageType getMessageType() 
	{
		return MessageType.PDU_NOTIFICATION_REJECT_REQUEST;
	}

	@Override
	public void applyTLV(TLV1 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case CAUSE:
				cause=(Cause)tlv;
				break;
			case TEID_1:
				tei=(TunnerEndpointIdentifier1)tlv;
				break;
			case END_USER_ADDRESS:
				endUserAddress=(EndUserAddress)tlv;
				break;
			case APN:
				apn=(APN)tlv;
				break;
			case PROTOCOL_CONFIGURATION_OPTIONS:
				protocolConfigurationOption=(ProtocolConfigurationOption)tlv;
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
		if(cause!=null)
			output.add(cause);
		
		if(tei==null)
			throw new GTPParseException("tei is missing");
		
		output.add(tei);
	
		if(endUserAddress!=null)
			output.add(endUserAddress);
	
		if(apn!=null)
			output.add(apn);
	
		if(protocolConfigurationOption!=null)
			output.add(protocolConfigurationOption);
	
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
	public Cause getCause() 
	{
		return cause;
	}

	@Override
	public void setCause(Cause cause) 
	{
		this.cause=cause;
	}
	
	@Override
	public TunnerEndpointIdentifier1 getTEI() 
	{
		return tei;
	}

	@Override
	public void setTEI(TunnerEndpointIdentifier1 tei) 
	{
		this.tei=tei;	
	}

	@Override
	public EndUserAddress getEndUserAddress() 
	{
		return endUserAddress;
	}

	@Override
	public void setEndUserAddress(EndUserAddress address)
	{
		this.endUserAddress=address;
	}

	@Override
	public APN getAPN() 
	{
		return apn;
	}

	@Override
	public void setAPN(APN apn) 
	{
		this.apn=apn;
	}

	@Override
	public ProtocolConfigurationOption getProtocolConfigurationOption() 
	{
		return protocolConfigurationOption;
	}

	@Override
	public void setProtocolConfigurationOption(ProtocolConfigurationOption option) 
	{
		this.protocolConfigurationOption=option;
	}	
}