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

import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.ModifyAccessBearerResponseBearerContextToBeModified;
import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.ModifyAccessBearerResponseBearerContextToBeRemoved;
import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Indication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.LoadControlInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.OverloadControlInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.ModifyAccessBearersResponse;

public class ModifyAccessBearersResponseImpl extends AbstractGTP2Message implements ModifyAccessBearersResponse
{
	private Cause cause;
	private List<ModifyAccessBearerResponseBearerContextToBeModified> bearerContextModified;
	private List<ModifyAccessBearerResponseBearerContextToBeRemoved> bearerContextMarkedForRemoved;
	private Recovery recovery;
	private Indication indication;
	private LoadControlInformation sgwNodeLoadControlInformation;
	private OverloadControlInformation sgwOverloadControlInformation;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.MODIFY_ACCESS_BEARER_RESPONSE;
	}

	@Override
	public void applyTLV(TLV2 tlv) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case CAUSE:
				cause=(Cause)tlv;
				break;
			case BEARER_CONTEXT:
				switch(tlv.getInstance())
				{
					case 0:
						if(bearerContextModified==null)
							bearerContextModified=new ArrayList<ModifyAccessBearerResponseBearerContextToBeModified>();
						
						bearerContextModified.add((ModifyAccessBearerResponseBearerContextToBeModified)tlv);
						break;
					case 1:
						if(bearerContextMarkedForRemoved==null)
							bearerContextMarkedForRemoved=new ArrayList<ModifyAccessBearerResponseBearerContextToBeRemoved>();
						
						bearerContextMarkedForRemoved.add((ModifyAccessBearerResponseBearerContextToBeRemoved)tlv);
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case RECOVERY:
				recovery=(Recovery)tlv;
				break;
			case INDICATION:
				indication=(Indication)tlv;
				break;
			case LOAD_CONTROL_INFORMATION:
				switch(tlv.getInstance())
				{
					case 0:
						sgwNodeLoadControlInformation=(LoadControlInformation)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case OVERLOAD_CONTROL_INFORMATION:
				switch(tlv.getInstance())
				{
					case 0:
						sgwOverloadControlInformation=(OverloadControlInformation)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
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
	public List<TLV2> getTLVs() throws GTPParseException 
	{
		ArrayList<TLV2> output=new ArrayList<TLV2>();
		if(cause==null)
			throw new GTPParseException("Cause not set");
		
		output.add(cause);
		
		if(bearerContextModified!=null && bearerContextModified.size()>0)
			output.addAll(bearerContextModified);
		
		if(bearerContextMarkedForRemoved!=null && bearerContextMarkedForRemoved.size()>0)
			output.addAll(bearerContextMarkedForRemoved);
		
		if(recovery!=null)
			output.add(recovery);
		
		if(indication!=null)
			output.add(indication);
		
		if(sgwNodeLoadControlInformation!=null)
			output.add(sgwNodeLoadControlInformation);
		
		if(sgwOverloadControlInformation!=null)
			output.add(sgwOverloadControlInformation);
		
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
		cause.setInstance(0);
		this.cause=cause;
	}

	@Override
	public List<ModifyAccessBearerResponseBearerContextToBeModified> getBearerContextModified() 
	{
		return this.bearerContextModified;
	}

	@Override
	public void setBearerContextModified(List<ModifyAccessBearerResponseBearerContextToBeModified> bearerContext) 
	{
		if(bearerContext!=null)
			for(ModifyAccessBearerResponseBearerContextToBeModified curr:bearerContext)
				curr.setInstance(0);
		
		this.bearerContextModified=bearerContext;
	}

	@Override
	public List<ModifyAccessBearerResponseBearerContextToBeRemoved> getBearerContextMarkedForRemoved() 
	{
		return this.bearerContextMarkedForRemoved;
	}

	@Override
	public void setBearerContextMarkedForRemoved(List<ModifyAccessBearerResponseBearerContextToBeRemoved> bearerContext) 
	{
		if(bearerContext!=null)
			for(ModifyAccessBearerResponseBearerContextToBeRemoved curr:bearerContext)
				curr.setInstance(1);
		
		this.bearerContextMarkedForRemoved=bearerContext;
	}

	@Override
	public Recovery getRecovery() 
	{
		return this.recovery;
	}

	@Override
	public void setRecovery(Recovery recovery) 
	{
		recovery.setInstance(0);
		this.recovery=recovery;
	}

	@Override
	public Indication getIndication() 
	{
		return this.indication;
	}

	@Override
	public void setIndication(Indication indication) 
	{
		indication.setInstance(0);
		this.indication=indication;
	}

	@Override
	public LoadControlInformation getSGWNodeLoadControlInformation() 
	{
		return this.sgwNodeLoadControlInformation;
	}

	@Override
	public void setSGWNodeLoadControlInformation(LoadControlInformation loadInformation) 
	{
		loadInformation.setInstance(0);
		this.sgwNodeLoadControlInformation=loadInformation;
	}

	@Override
	public OverloadControlInformation getSGWOverloadControlInformation() 
	{
		return sgwOverloadControlInformation;
	}

	@Override
	public void setSGWOverloadControlInformation(OverloadControlInformation overloadInformation) 
	{
		overloadInformation.setInstance(0);
		this.sgwOverloadControlInformation=overloadInformation;
	}
}