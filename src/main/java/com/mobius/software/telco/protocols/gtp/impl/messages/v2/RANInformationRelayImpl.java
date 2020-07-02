package com.mobius.software.telco.protocols.gtp.impl.messages.v2;
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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FContainer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TargetIdentification;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.RANInformationRelay;

public class RANInformationRelayImpl extends AbstractGTP2Message implements RANInformationRelay
{
	FContainer bssContainer;
	TargetIdentification targetIdentification;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.RAN_INFORMATION_RELAY;
	}

	@Override
	public void applyTLV(TLV2 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case FCONTAINER:
				switch(tlv.getInstance())
				{
					case 0:
						bssContainer=(FContainer)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case TARGET_IDENTIFICATION:
				targetIdentification=(TargetIdentification)tlv;
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
	public List<TLV2> getTLVs() throws GTPParseException 
	{
		ArrayList<TLV2> output=new ArrayList<TLV2>();
		if(bssContainer==null)
			throw new GTPParseException("BSS Container not set");
			output.add(bssContainer);
		
		if(targetIdentification!=null)
			output.add(targetIdentification);
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
	public FContainer getBSSContainer() 
	{
		return bssContainer;
	}

	@Override
	public void setBSSContainer(FContainer bssContainer) 
	{
		bssContainer.setInstance(2);
		this.bssContainer=bssContainer;
	}

	@Override
	public TargetIdentification getTargetIdentification() 
	{
		return this.targetIdentification;		
	}

	@Override
	public void setTargetIdentification(TargetIdentification identification) 
	{
		identification.setInstance(0);
		this.targetIdentification=identification;
	}
}