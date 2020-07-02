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
import com.mobius.software.telco.protocols.gtp.api.headers.AuthenticationQuintiplet;
import com.mobius.software.telco.protocols.gtp.api.headers.AuthenticationTriplet;
import com.mobius.software.telco.protocols.gtp.api.headers.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.messages.IdentificationResponse;

public class IdentificationResponseImpl extends AbstractGTPMessage implements IdentificationResponse
{
	private Cause cause;
	private IMSI imsi;
	private AuthenticationTriplet triplet;
	private AuthenticationQuintiplet quntiplet;
	
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public MessageType getMessageType() 
	{
		return MessageType.IDENTIFICATION_RESPONSE;
	}

	@Override
	public void applyTLV(TLV1 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case CAUSE:
				cause=(Cause)tlv;
				break;
			case IMSI:
				imsi=(IMSI)tlv;
				break;
			case AUTHENTICATION_TRIPLET:
				triplet=(AuthenticationTriplet)tlv;
			case AUTHENTICATION_QUINTIPLET:
				quntiplet=(AuthenticationQuintiplet)tlv;
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
		if(cause==null)
			throw new GTPParseException("cause is missing");
		
		output.add(cause);
		
		if(imsi!=null)
			output.add(imsi);
		
		if(triplet!=null)
			output.add(triplet);
		
		if(quntiplet!=null)
			output.add(quntiplet);
		
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
	public IMSI getIMSI() 
	{
		return imsi;
	}

	@Override
	public void setIMSI(IMSI imsi) 
	{
		this.imsi=imsi;
	}

	@Override
	public AuthenticationTriplet getAuthenticationTriplet() 
	{
		return triplet;
	}

	@Override
	public void setAuthenticationTriplet(AuthenticationTriplet triplet) 
	{
		this.triplet=triplet;
	}

	@Override
	public AuthenticationQuintiplet getAuthenticationQuintiplet() 
	{
		return quntiplet;
	}

	@Override
	public void setAuthenticationQuintiplet(AuthenticationQuintiplet quintiplet) 
	{
		this.quntiplet=quintiplet;
	}
}