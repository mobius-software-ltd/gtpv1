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
import com.mobius.software.telco.protocols.gtp.api.headers.AdditionalMBMSTraceInfo;
import com.mobius.software.telco.protocols.gtp.api.headers.AdditionalTraceInfo;
import com.mobius.software.telco.protocols.gtp.api.headers.EndUserAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.EnhancedNSAPI;
import com.mobius.software.telco.protocols.gtp.api.headers.GSNAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.IMEISV;
import com.mobius.software.telco.protocols.gtp.api.headers.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.MBMSProtocolConfigurationOptions;
import com.mobius.software.telco.protocols.gtp.api.headers.MSISDN;
import com.mobius.software.telco.protocols.gtp.api.headers.MSTimezone;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.OMCIdentity;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.RatType;
import com.mobius.software.telco.protocols.gtp.api.headers.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.RoutingAreaIdentity;
import com.mobius.software.telco.protocols.gtp.api.headers.SelectionMode;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.headers.TraceReference;
import com.mobius.software.telco.protocols.gtp.api.headers.TraceType;
import com.mobius.software.telco.protocols.gtp.api.headers.TriggerID;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifierControlPane;
import com.mobius.software.telco.protocols.gtp.api.headers.UserLocationInformation;
import com.mobius.software.telco.protocols.gtp.api.messages.CreateMBMSContextRequest;

public class CreateMBMSContextRequestImpl extends AbstractGTPMessage implements CreateMBMSContextRequest
{
	private IMSI imsi;
	private RoutingAreaIdentity rai;
	private Recovery recovery;
	private SelectionMode selectionMode;
	private TunnerEndpointIdentifierControlPane controlTEI;
	private TraceReference traceReference;
	private TraceType traceType;
	private EndUserAddress endUserAddress;
	private APN apn;
	private GSNAddress sgsnAddressForSignaling;
	private MSISDN msisdn;
	private TriggerID triggerID;
	private OMCIdentity omcIdentity;
	private RatType ratType;
	private UserLocationInformation userLocationInformation;
	private MSTimezone msTimezone;
	private IMEISV imeisv;
	private MBMSProtocolConfigurationOptions protocolConfigurationOptions;
	private AdditionalTraceInfo additionalTraceInfo;
	private EnhancedNSAPI enhancedNSAPI;
	private AdditionalMBMSTraceInfo additionalMBMSTraceInfo;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public MessageType getMessageType() 
	{
		return MessageType.CREATE_MBMS_CONTEXT_REQUEST;
	}

	@Override
	public void applyTLV(TLV1 tlv) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case IMSI:
				imsi=(IMSI)tlv;
				break;
			case RAI:
				rai=(RoutingAreaIdentity)tlv;
				break;
			case RECOVERY:
				recovery=(Recovery)tlv;
				break;
			case SELECTION_MODE:
				selectionMode=(SelectionMode)tlv;
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
			case END_USER_ADDRESS:
				endUserAddress=(EndUserAddress)tlv;
				break;
			case APN:
				apn=(APN)tlv;
				break;
			case GSN_ADDRESS:
				sgsnAddressForSignaling=(GSNAddress)tlv;
				break;
			case MSISDN:
				msisdn=(MSISDN)tlv;
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
			case IMEI_SV:
				imeisv=(IMEISV)tlv;
				break;
			case MBMS_PROTOCOL_CONFIGURATION_OPTION:
				protocolConfigurationOptions=(MBMSProtocolConfigurationOptions)tlv;
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
		if(imsi!=null)
			output.add(imsi);
		
		if(rai==null)
			throw new GTPParseException("rai is missing");
		
		output.add(rai);
	
		if(recovery!=null)
			output.add(recovery);
	
		if(selectionMode!=null)
			output.add(selectionMode);
	
		if(controlTEI!=null)
			output.add(controlTEI);
	
		if(traceReference!=null)
			output.add(traceReference);
	
		if(traceType!=null)
			output.add(traceType);
	
		if(endUserAddress==null)
			throw new GTPParseException("End user address is missing");
		
		output.add(endUserAddress);
	
		if(apn==null)
			throw new GTPParseException("APN is missing");
		
		output.add(apn);
	
		if(sgsnAddressForSignaling==null)
			throw new GTPParseException("sgsn address for signaling is missing");
		
		output.add(sgsnAddressForSignaling);
	
		if(msisdn!=null)
			output.add(msisdn);
	
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
	
		if(imeisv!=null)
			output.add(imeisv);
	
		if(protocolConfigurationOptions!=null)
			output.add(protocolConfigurationOptions);
	
		if(additionalTraceInfo!=null)
			output.add(additionalTraceInfo);
		
		if(enhancedNSAPI==null)
			throw new GTPParseException("enhanced NSAPI is missing");
		
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
	public SelectionMode getSelectionMode() 
	{
		return this.selectionMode;
	}

	@Override
	public void setSelectionMode(SelectionMode selectionMode) 
	{
		this.selectionMode=selectionMode;
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
	public MSISDN getMSISDN() 
	{
		return msisdn;
	}

	@Override
	public void setMSISDN(MSISDN msisdn) 
	{
		this.msisdn=msisdn;
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
	public IMEISV getIMEISV() 
	{
		return this.imeisv;
	}

	@Override
	public void setIMEISV(IMEISV imeiSV) 
	{
		this.imeisv=imeiSV;
	}

	@Override
	public MBMSProtocolConfigurationOptions getMBMSProtocolConfigurationOptions() 
	{
		return this.protocolConfigurationOptions;
	}

	@Override
	public void setMBMSProtocolConfigurationOptions(MBMSProtocolConfigurationOptions protocolConfigurationOptions) 
	{
		this.protocolConfigurationOptions=protocolConfigurationOptions;
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
		return enhancedNSAPI;
	}

	@Override
	public void setEnhancedNSAPI(EnhancedNSAPI enhancedNSAPI) 
	{
		this.enhancedNSAPI=enhancedNSAPI;	
	}

	@Override
	public AdditionalMBMSTraceInfo getAdditionalMBMSTraceInfo() 
	{
		return this.additionalMBMSTraceInfo;
	}

	@Override
	public void setAdditionalMBMSTraceInfo(AdditionalMBMSTraceInfo additionalMBMSTraceInfo) 
	{
		this.additionalMBMSTraceInfo=additionalMBMSTraceInfo;
	}
}