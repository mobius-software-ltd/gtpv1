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

import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.ModifyAccessBearerRequestBearerContextToBeModified;
import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.ModifyAccessBearerRequestBearerContextToBeRemoved;
import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.DelayValue;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Indication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SecondaryRatUsageDataReport;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.ModifyAccessBearersRequest;

public class ModifyAccessBearersRequestImpl extends AbstractGTP2Message implements ModifyAccessBearersRequest
{
	Indication indication;
	FTEID senderFTEIDControlPlane;
	DelayValue delayValue;
	List<ModifyAccessBearerRequestBearerContextToBeModified> bearerContextToBeModified;
	List<ModifyAccessBearerRequestBearerContextToBeRemoved> bearerContextToBeRemoved;
	Recovery recovery;
	SecondaryRatUsageDataReport secondaryRatUsageDataReport;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.MODIFY_ACCESS_BEARER_REQUEST;
	}

	@Override
	public void applyTLV(TLV2 tlv) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case DELAY_VALUE:
				delayValue=(DelayValue)tlv;
				break;
			case INDICATION:
				indication=(Indication)tlv;
				break;
			case FTEID:
				switch(tlv.getInstance())
				{
					case 0:
						senderFTEIDControlPlane=(FTEID)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case BEARER_CONTEXT:
				switch(tlv.getInstance())
				{
					case 0:
						if(bearerContextToBeModified==null)
							bearerContextToBeModified=new ArrayList<ModifyAccessBearerRequestBearerContextToBeModified>();
						
						bearerContextToBeModified.add((ModifyAccessBearerRequestBearerContextToBeModified)tlv);
						break;
					case 1:
						if(bearerContextToBeRemoved==null)
							bearerContextToBeRemoved=new ArrayList<ModifyAccessBearerRequestBearerContextToBeRemoved>();
						
						bearerContextToBeRemoved.add((ModifyAccessBearerRequestBearerContextToBeRemoved)tlv);
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case RECOVERY:
				recovery=(Recovery)tlv;
				break;
			case SECONDARY_RAT_DATA_USAGE_REPORT:
				secondaryRatUsageDataReport=(SecondaryRatUsageDataReport)tlv;
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
		if(indication!=null)
			output.add(indication);
		
		if(senderFTEIDControlPlane!=null)
			output.add(senderFTEIDControlPlane);
		
		if(delayValue!=null)
			output.add(delayValue);
			
		if(bearerContextToBeModified!=null && bearerContextToBeModified.size()>0)
			output.addAll(bearerContextToBeModified);
		
		if(bearerContextToBeRemoved!=null && bearerContextToBeRemoved.size()>0)
			output.addAll(bearerContextToBeRemoved);
		
		if(recovery!=null)
			output.add(recovery);
		
		if(secondaryRatUsageDataReport!=null)
			output.add(secondaryRatUsageDataReport);
		
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
	public FTEID getSenderFTEIDControlPlane() 
	{
		return this.senderFTEIDControlPlane;
	}

	@Override
	public void setSenderFTEIDControlPlane(FTEID fteid) 
	{
		fteid.setInstance(0);
		this.senderFTEIDControlPlane=fteid;
	}

	@Override
	public List<ModifyAccessBearerRequestBearerContextToBeModified> getBearerContextToBeModified() 
	{
		return bearerContextToBeModified;
	}

	@Override
	public void setBearerContextToBeModified(List<ModifyAccessBearerRequestBearerContextToBeModified> bearerContext) 
	{
		if(bearerContext!=null)
			for(ModifyAccessBearerRequestBearerContextToBeModified curr:bearerContext)				
				curr.setInstance(0);
		
		this.bearerContextToBeModified=bearerContext;
	}

	@Override
	public List<ModifyAccessBearerRequestBearerContextToBeRemoved> getBearerContextToBeRemoved() 
	{
		return bearerContextToBeRemoved;
	}

	@Override
	public void setBearerContextToBeRemoved(List<ModifyAccessBearerRequestBearerContextToBeRemoved> bearerContext) 
	{
		if(bearerContext!=null)
			for(ModifyAccessBearerRequestBearerContextToBeRemoved curr:bearerContext)
				curr.setInstance(1);
		
		this.bearerContextToBeRemoved=bearerContext;
	}

	@Override
	public Recovery getRecovery() 
	{
		return recovery;
	}

	@Override
	public void setRecovery(Recovery recovery) 
	{
		recovery.setInstance(0);
		this.recovery=recovery;
	}

	@Override
	public SecondaryRatUsageDataReport getSecondaryRatUsageDataReport() 
	{
		return secondaryRatUsageDataReport;
	}

	@Override
	public void setSecondaryRatUsageDataReport(SecondaryRatUsageDataReport secondaryRatUsageDataReport) 
	{
		secondaryRatUsageDataReport.setInstance(0);
		this.secondaryRatUsageDataReport=secondaryRatUsageDataReport;
	}

	@Override
	public DelayValue getDelayValue() 
	{
		return this.delayValue;
	}

	@Override
	public void setDelayValue(DelayValue delayValue) 
	{
		delayValue.setInstance(0);
		this.delayValue=delayValue;
	}
}