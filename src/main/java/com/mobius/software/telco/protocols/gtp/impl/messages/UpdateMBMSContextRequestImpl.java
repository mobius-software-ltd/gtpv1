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
import com.mobius.software.telco.protocols.gtp.api.headers.AdditionalMBMSTraceInfo;
import com.mobius.software.telco.protocols.gtp.api.headers.AdditionalTraceInfo;
import com.mobius.software.telco.protocols.gtp.api.headers.EnhancedNSAPI;
import com.mobius.software.telco.protocols.gtp.api.headers.GSNAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.MSTimezone;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.OMCIdentity;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.RatType;
import com.mobius.software.telco.protocols.gtp.api.headers.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.RoutingAreaIdentity;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.headers.TraceReference;
import com.mobius.software.telco.protocols.gtp.api.headers.TraceType;
import com.mobius.software.telco.protocols.gtp.api.headers.TriggerID;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifierControlPane;
import com.mobius.software.telco.protocols.gtp.api.headers.UserLocationInformation;
import com.mobius.software.telco.protocols.gtp.api.messages.UpdateMBMSContextRequest;

public class UpdateMBMSContextRequestImpl extends AbstractGTPMessage implements UpdateMBMSContextRequest
{
	private RoutingAreaIdentity rai;
	private Recovery recovery;
	private TunnerEndpointIdentifierControlPane controlTEI;
	private TraceReference traceReference;
	private TraceType traceType;
	private GSNAddress sgsnAddressForSignaling;
	private GSNAddress alternativeSgsnAddressForSignaling;
	private TriggerID triggerID;
	private OMCIdentity omcIdentity;
	private RatType ratType;
	private UserLocationInformation userLocationInformation;
	private MSTimezone msTimezone;
	private AdditionalTraceInfo additionalTraceInfo;
	private EnhancedNSAPI enhancedNSAPI;
	private AdditionalMBMSTraceInfo additionalMBMSTraceInfo;
	
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public MessageType getMessageType() 
	{
		return MessageType.UPDATE_MBMS_CONTEXT_REQUEST;
	}

	@Override
	public void applyTLV(TLV1 tlv) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case RAI:
				rai=(RoutingAreaIdentity)tlv;
				break;
			case RECOVERY:
				recovery=(Recovery)tlv;
				break;
			case TEICP:
				controlTEI=(TunnerEndpointIdentifierControlPane)tlv;
				break;
			case TRACE_REFERENCE:
				traceReference=(TraceReference)tlv;
				break;
			case TRACE_TYPE:
				traceType=(TraceType)tlv;
				break;
			case GSN_ADDRESS:
				if(sgsnAddressForSignaling==null)
					sgsnAddressForSignaling=(GSNAddress)tlv;
				else if(alternativeSgsnAddressForSignaling==null)
					alternativeSgsnAddressForSignaling=(GSNAddress)tlv;
				else
					throw new GTPParseException("Unknown TLV received,type:" + tlv.getElementType());
				break;
			case TRIGGER_ID:
				triggerID=(TriggerID)tlv;
				break;
			case OMC_IDENTITY:
				omcIdentity=(OMCIdentity)tlv;
				break;
			case RAT_TYPE:
				ratType=(RatType)tlv;
				break;
			case USER_LOCATION_INFORMATION:
				userLocationInformation=(UserLocationInformation)tlv;
				break;
			case MS_TIMEZONE:
				msTimezone=(MSTimezone)tlv;
				break;
			case ADDITIONAL_TRACE_INFO:
				additionalTraceInfo=(AdditionalTraceInfo)tlv;
				break;
			case ENHANCED_NSAPI:
				enhancedNSAPI=(EnhancedNSAPI)tlv;
				break;
			case ADDITIONAL_MBMS_TRACE_INFO:
				additionalMBMSTraceInfo=(AdditionalMBMSTraceInfo)tlv;
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
		if(rai==null)
			throw new GTPParseException("rai is missing");
			
		output.add(rai);
		
		if(recovery!=null)
			output.add(recovery);
	
		if(controlTEI!=null)
			output.add(controlTEI);
	
		if(traceReference!=null)
			output.add(traceReference);
	
		if(traceType!=null)
			output.add(traceType);
	
		if(sgsnAddressForSignaling==null)
			throw new GTPParseException("sgsn address for signaling is missing");
		
		output.add(sgsnAddressForSignaling);
	
		if(alternativeSgsnAddressForSignaling!=null)
			output.add(alternativeSgsnAddressForSignaling);
	
		if(triggerID!=null)
			output.add(triggerID);
	
		if(omcIdentity!=null)
			output.add(omcIdentity);
	
		if(ratType!=null)
			output.add(ratType);
	
		if(userLocationInformation!=null)
			output.add(userLocationInformation);
	
		if(msTimezone!=null)
			output.add(msTimezone);
	
		if(additionalTraceInfo!=null)
			output.add(additionalTraceInfo);
		
		if(enhancedNSAPI==null)
			throw new GTPParseException("Enhanced nsapi is missing");
		
		output.add(enhancedNSAPI);
		
		if(additionalMBMSTraceInfo!=null)
			output.add(additionalMBMSTraceInfo);
		
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
	public RoutingAreaIdentity getRAI() 
	{
		return rai;
	}

	@Override
	public void setRAI(RoutingAreaIdentity rai) 
	{
		this.rai=rai;
	}

	@Override
	public Recovery getRecovery() 
	{
		return recovery;
	}

	@Override
	public void setRecovery(Recovery recovery) 
	{
		this.recovery=recovery;
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
	public TraceReference getTraceReference() 
	{
		return traceReference;
	}

	@Override
	public void setTraceReference(TraceReference reference) 
	{
		this.traceReference=reference;
	}

	@Override
	public TraceType getTraceType() 
	{
		return traceType;
	}

	@Override
	public void setTraceType(TraceType traceType) 
	{
		this.traceType=traceType;
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
	public GSNAddress getAlternateSGSNAddressForSignaling() 
	{
		return alternativeSgsnAddressForSignaling;
	}

	@Override
	public void setAlternateSGSNAddressForSignaling(GSNAddress address) 
	{
		this.alternativeSgsnAddressForSignaling=address;
	}

	@Override
	public TriggerID getTriggerID() 
	{
		return this.triggerID;
	}

	@Override
	public void setTriggerID(TriggerID triggerID) 
	{
		this.triggerID=triggerID;
	}

	@Override
	public OMCIdentity getOMCIdentity() 
	{
		return this.omcIdentity;
	}

	@Override
	public void setOMCIdentity(OMCIdentity omcIdentity) 
	{
		this.omcIdentity=omcIdentity;	
	}

	@Override
	public RatType getRatType() 
	{
		return this.ratType;
	}

	@Override
	public void setRatType(RatType ratType) 
	{
		this.ratType=ratType;
	}

	@Override
	public UserLocationInformation getUserLocationInformation() 
	{
		return userLocationInformation;
	}

	@Override
	public void setUserLocationInformation(UserLocationInformation locationInformation) 
	{
		this.userLocationInformation=locationInformation;	
	}

	@Override
	public MSTimezone getMSTimezone() 
	{
		return msTimezone;
	}

	@Override
	public void setMSTimezone(MSTimezone timezone) 
	{
		this.msTimezone=timezone;
	}

	@Override
	public AdditionalTraceInfo getAdditionalTraceInfo() 
	{
		return this.additionalTraceInfo;
	}

	@Override
	public void setAdditionalTraceInfo(AdditionalTraceInfo additionalTraceInfo) 
	{
		this.additionalTraceInfo=additionalTraceInfo;
	}

	@Override
	public EnhancedNSAPI getEnhancedNSAPI() 
	{
		return this.enhancedNSAPI;
	}

	@Override
	public void setEnhancedNSAPI(EnhancedNSAPI enhancedNSAPI) 
	{
		this.enhancedNSAPI=enhancedNSAPI;
	}

	@Override
	public AdditionalMBMSTraceInfo getAdditionalMBMSTraceInfo() 
	{
		return additionalMBMSTraceInfo;
	}

	@Override
	public void setAdditionalMBMSTraceInfo(AdditionalMBMSTraceInfo additionalMBMSTraceInfo) 
	{
		this.additionalMBMSTraceInfo=additionalMBMSTraceInfo;
	}
}