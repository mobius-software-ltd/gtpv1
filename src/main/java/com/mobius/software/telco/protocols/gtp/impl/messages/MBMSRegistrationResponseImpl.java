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
import com.mobius.software.telco.protocols.gtp.api.headers.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.GSNAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.RequiredMBMSBearerCapabilities;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.headers.TMGI;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifierControlPane;
import com.mobius.software.telco.protocols.gtp.api.messages.MBMSRegistrationResponse;

public class MBMSRegistrationResponseImpl extends AbstractGTPMessage implements MBMSRegistrationResponse
{
	private Cause cause;
	private TMGI tmgi;
	private TunnerEndpointIdentifierControlPane controlTEI;
	private GSNAddress sgsnAddressForSignaling;
	private RequiredMBMSBearerCapabilities requiredMBMSBearerCapabilities;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public MessageType getMessageType() 
	{
		return MessageType.MBMS_REGISTRATION_RESPONSE;
	}

	@Override
	public void applyTLV(TLV1 tlv) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case CAUSE:
				cause=(Cause)tlv;
				break;
			case TMGI:
				tmgi=(TMGI)tlv;
				break;
			case TEICP:
				controlTEI=(TunnerEndpointIdentifierControlPane)tlv;
				break;
			case GSN_ADDRESS:
				sgsnAddressForSignaling=(GSNAddress)tlv;
				break;
			case REQUIRED_MBMS_BEARER_CAPABILITIES:
				requiredMBMSBearerCapabilities=(RequiredMBMSBearerCapabilities)tlv;
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
			throw new GTPParseException("cause is missing");
		
		output.add(cause);
	
		if(tmgi!=null)
			output.add(tmgi);
	
		if(controlTEI!=null)
			output.add(controlTEI);
	
		if(sgsnAddressForSignaling!=null)
			output.add(sgsnAddressForSignaling);
	
		if(requiredMBMSBearerCapabilities!=null)
			output.add(requiredMBMSBearerCapabilities);
	
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
	public TMGI getTMGI() 
	{
		return tmgi;
	}

	@Override
	public void setTMGI(TMGI tmgi) 
	{
		this.tmgi=tmgi;
	}

	@Override
	public TunnerEndpointIdentifierControlPane getControlPaneTEI() 
	{
		return controlTEI;
	}

	@Override
	public void setControlPageTEI(TunnerEndpointIdentifierControlPane tei) 
	{
		this.controlTEI=tei;
	}

	@Override
	public GSNAddress getSGSNAddressForSignaling() 
	{
		return sgsnAddressForSignaling;
	}

	@Override
	public void setSGSNAddressForSignaling(GSNAddress address) 
	{
		this.sgsnAddressForSignaling=address;
	}

	@Override
	public RequiredMBMSBearerCapabilities getRequiredMBMSBearerCapabilities() 
	{
		return requiredMBMSBearerCapabilities;
	}

	@Override
	public void setRequiredMBMSBearerCapabilities(RequiredMBMSBearerCapabilities requiredMBMSBearerCapabilities) 
	{
		this.requiredMBMSBearerCapabilities=requiredMBMSBearerCapabilities;
	}
}