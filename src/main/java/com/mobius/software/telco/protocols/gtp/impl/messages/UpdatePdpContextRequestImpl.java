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
import com.mobius.software.telco.protocols.gtp.api.headers.APNAMBR;
import com.mobius.software.telco.protocols.gtp.api.headers.AdditionalTraceInfo;
import com.mobius.software.telco.protocols.gtp.api.headers.CNOperatorSelectionEntity;
import com.mobius.software.telco.protocols.gtp.api.headers.CSGInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.CommonFlags;
import com.mobius.software.telco.protocols.gtp.api.headers.CorrelationID;
import com.mobius.software.telco.protocols.gtp.api.headers.DirectTunnelFlags;
import com.mobius.software.telco.protocols.gtp.api.headers.EvolvedAllocationRetentionPriority1;
import com.mobius.software.telco.protocols.gtp.api.headers.ExtendedCommonFlags;
import com.mobius.software.telco.protocols.gtp.api.headers.GSNAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.MSTimezone;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.NSAPI;
import com.mobius.software.telco.protocols.gtp.api.headers.OMCIdentity;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.ProtocolConfigurationOption;
import com.mobius.software.telco.protocols.gtp.api.headers.QosProfile;
import com.mobius.software.telco.protocols.gtp.api.headers.RatType;
import com.mobius.software.telco.protocols.gtp.api.headers.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.RoutingAreaIdentity;
import com.mobius.software.telco.protocols.gtp.api.headers.SignalingPriorityIndication;
import com.mobius.software.telco.protocols.gtp.api.headers.TFT;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.headers.TraceReference;
import com.mobius.software.telco.protocols.gtp.api.headers.TraceType;
import com.mobius.software.telco.protocols.gtp.api.headers.TriggerID;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifier1;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifierControlPane;
import com.mobius.software.telco.protocols.gtp.api.headers.UserLocationInformation;
import com.mobius.software.telco.protocols.gtp.api.messages.UpdatePdpContextRequest;

public class UpdatePdpContextRequestImpl extends AbstractGTPMessage implements UpdatePdpContextRequest
{
	private IMSI imsi;
	private RoutingAreaIdentity rai;
	private Recovery recovery;
	private TunnerEndpointIdentifier1 tei;
	private TunnerEndpointIdentifierControlPane controlTEI;
	private NSAPI nsapi;
	private TraceReference traceReference;
	private TraceType traceType;
	private ProtocolConfigurationOption protocolConfigurationOption;
	private GSNAddress sgsnAddressForSignaling;
	private GSNAddress sgsnAddressForTraffic;
	private GSNAddress alternativeSgsnAddressForSignaling;
	private GSNAddress alternativeSgsnAddressForTraffic;
	private QosProfile qosProfile;
	private TFT tft;
	private TriggerID triggerID;
	private OMCIdentity omcIdentity;
	private CommonFlags commonFlags;
	private RatType ratType;
	private UserLocationInformation userLocationInformation;
	private MSTimezone msTimezone;
	private AdditionalTraceInfo additionalTraceInfo;
	private CorrelationID correlationID;
	private DirectTunnelFlags directTunnelFlags;
	private EvolvedAllocationRetentionPriority1 artp1;
	private ExtendedCommonFlags extendedCommonFlags;
	private CSGInformation userCSGInformation;
	private APNAMBR apnAMBR;
	private SignalingPriorityIndication signalingPriorityIndication;
	private CNOperatorSelectionEntity cnOperationSelectionEntity;
	
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public MessageType getMessageType() 
	{
		return MessageType.UPDATE_PDP_CONTEXT_REQUEST;
	}

	@Override
	public void applyTLV(TLV1 tlv,Boolean ignoreUnknown) throws GTPParseException 
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
			case TEID_1:
				tei=(TunnerEndpointIdentifier1)tlv;
				break;
			case TEICP:
				controlTEI=(TunnerEndpointIdentifierControlPane)tlv;
				break;
			case NSAPI:
				nsapi=(NSAPI)tlv;
				break;
			case TRACE_REFERENCE:
				traceReference=(TraceReference)tlv;
				break;
			case TRACE_TYPE:
				traceType=(TraceType)tlv;
				break;
			case PROTOCOL_CONFIGURATION_OPTIONS:
				protocolConfigurationOption=(ProtocolConfigurationOption)tlv;
				break;
			case GSN_ADDRESS:
				if(sgsnAddressForSignaling==null)
					sgsnAddressForSignaling=(GSNAddress)tlv;
				else if(sgsnAddressForTraffic==null)
					sgsnAddressForTraffic=(GSNAddress)tlv;
				else if(alternativeSgsnAddressForSignaling==null)
					alternativeSgsnAddressForSignaling=(GSNAddress)tlv;
				else if(alternativeSgsnAddressForTraffic==null)
					alternativeSgsnAddressForTraffic=(GSNAddress)tlv;
				else
					throw new GTPParseException("Unknown TLV received,type:" + tlv.getElementType());
				break;
			case QOS_PROFILE:
				qosProfile=(QosProfile)tlv;
				break;
			case TRAFFIC_FLOW_TEMPLATE:
				tft=(TFT)tlv;
				break;
			case TRIGGER_ID:
				triggerID=(TriggerID)tlv;
				break;
			case OMC_IDENTITY:
				omcIdentity=(OMCIdentity)tlv;
				break;
			case COMMON_FLAGS:
				commonFlags=(CommonFlags)tlv;
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
			case CORRELATION_ID:
				correlationID=(CorrelationID)tlv;
				break;
			case DIRECT_TUNNEL_FLAGS:
				directTunnelFlags=(DirectTunnelFlags)tlv;
				break;
			case EVOLVED_ALLOCATION_RETENTION_PRIORITY_1:
				artp1=(EvolvedAllocationRetentionPriority1)tlv;
				break;
			case EXTENDED_COMMON_FLAGS:
				extendedCommonFlags=(ExtendedCommonFlags)tlv;
				break;
			case USER_CSG_INFORMATION:
				userCSGInformation=(CSGInformation)tlv;
				break;
			case APN_AMBR:
				apnAMBR=(APNAMBR)tlv;
				break;
			case SIGNALING_PRIORITY_INDICATION:
				signalingPriorityIndication=(SignalingPriorityIndication)tlv;
				break;
			case CN_OPERATOR_SELECTION_ENTITY:
				cnOperationSelectionEntity=(CNOperatorSelectionEntity)tlv;
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
		if(imsi!=null)
			output.add(imsi);
		
		if(rai!=null)
			output.add(rai);
		
		if(recovery!=null)
			output.add(recovery);
	
		if(tei==null)
			throw new GTPParseException("tei is missing");
		
		output.add(tei);
	
		if(controlTEI!=null)
			output.add(controlTEI);
	
		if(nsapi==null)
			throw new GTPParseException("nsapi is missing");
		
		output.add(nsapi);
	
		if(traceReference!=null)
			output.add(traceReference);
	
		if(traceType!=null)
			output.add(traceType);
	
		if(protocolConfigurationOption!=null)
			output.add(protocolConfigurationOption);
	
		if(sgsnAddressForSignaling==null)
			throw new GTPParseException("sgsn address for signaling is missing");
		
		output.add(sgsnAddressForSignaling);
	
		if(sgsnAddressForTraffic==null)
			throw new GTPParseException("sgsn address for traffic is missing");
		
		output.add(sgsnAddressForTraffic);
	
		if(alternativeSgsnAddressForSignaling!=null)
			output.add(alternativeSgsnAddressForSignaling);
	
		if(alternativeSgsnAddressForTraffic!=null)
			output.add(alternativeSgsnAddressForTraffic);
	
		if(qosProfile==null)
			throw new GTPParseException("qos profile is missing");
		
		output.add(qosProfile);
	
		if(tft!=null)
			output.add(tft);
	
		if(triggerID!=null)
			output.add(triggerID);
	
		if(omcIdentity!=null)
			output.add(omcIdentity);
	
		if(commonFlags!=null)
			output.add(commonFlags);
	
		if(ratType!=null)
			output.add(ratType);
	
		if(userLocationInformation!=null)
			output.add(userLocationInformation);
	
		if(msTimezone!=null)
			output.add(msTimezone);
	
		if(additionalTraceInfo!=null)
			output.add(additionalTraceInfo);
		
		if(correlationID!=null)
			output.add(correlationID);
		
		if(directTunnelFlags!=null)
			output.add(directTunnelFlags);
		
		if(artp1!=null)
			output.add(artp1);
		
		if(extendedCommonFlags!=null)
			output.add(extendedCommonFlags);
		
		if(userCSGInformation!=null)
			output.add(userCSGInformation);
		
		if(apnAMBR!=null)
			output.add(apnAMBR);
		
		if(signalingPriorityIndication!=null)
			output.add(signalingPriorityIndication);
		
		if(cnOperationSelectionEntity!=null)
			output.add(cnOperationSelectionEntity);	
		
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
	public NSAPI getNSAPI() 
	{
		return nsapi;
	}

	@Override
	public void setNSAPI(NSAPI nsapi) 
	{
		this.nsapi=nsapi;
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
	public ProtocolConfigurationOption getProtocolConfigurationOption() 
	{
		return protocolConfigurationOption;
	}

	@Override
	public void setProtocolConfigurationOption(ProtocolConfigurationOption option) 
	{
		this.protocolConfigurationOption=option;
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
	public GSNAddress getSGSNAddressForTraffic() 
	{
		return sgsnAddressForTraffic;
	}

	@Override
	public void setSGSNAddressForTraffic(GSNAddress address) 
	{
		this.sgsnAddressForTraffic=address;
	}

	@Override
	public QosProfile getQosProfile() 
	{
		return qosProfile;
	}

	@Override
	public void setQosProfile(QosProfile profile) 
	{
		this.qosProfile=profile;
	}

	@Override
	public TFT getTFT() 
	{
		return tft;
	}

	@Override
	public void setTFT(TFT tft) 
	{
		this.tft=tft;
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
	public CommonFlags getCommonFlags() 
	{
		return commonFlags;
	}

	@Override
	public void setCommonFlags(CommonFlags commonFlags) 
	{
		this.commonFlags=commonFlags;
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
	public EvolvedAllocationRetentionPriority1 getEvolvedAllocationRetentionPriority1() 
	{
		return this.artp1;
	}

	@Override
	public void setEvolvedAllocationRetentionPriority1(EvolvedAllocationRetentionPriority1 priority) 
	{
		this.artp1=priority;
	}

	@Override
	public ExtendedCommonFlags getExtendedCommonFlags() 
	{
		return extendedCommonFlags;
	}

	@Override
	public void setExtendedCommonFlags(ExtendedCommonFlags flags) 
	{
		this.extendedCommonFlags=flags;
	}

	@Override
	public CSGInformation getCSGInformation() 
	{
		return this.userCSGInformation;
	}

	@Override
	public void setCSGInformation(CSGInformation information) 
	{
		this.userCSGInformation=information;
	}

	@Override
	public APNAMBR getAPNAMBR() 
	{
		return this.apnAMBR;
	}

	@Override
	public void setAPNAMBR(APNAMBR apnambr) 
	{
		this.apnAMBR=apnambr;
	}

	@Override
	public SignalingPriorityIndication getSignalingPriorityIndication() 
	{
		return this.signalingPriorityIndication;
	}

	@Override
	public void setSignalingPriorityIndication(SignalingPriorityIndication indication) 
	{
		this.signalingPriorityIndication=indication;
	}

	@Override
	public CNOperatorSelectionEntity getCNOperatorSelectionEntity() 
	{
		return cnOperationSelectionEntity;
	}

	@Override
	public void setCNOperatorSelectionEntity(CNOperatorSelectionEntity entity) 
	{
		this.cnOperationSelectionEntity=entity;
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
	public GSNAddress getAlternateSGSNAddressForTraffic() 
	{
		return alternativeSgsnAddressForTraffic;
	}

	@Override
	public void setAlternateSGSNAddressForTraffic(GSNAddress address) 
	{
		this.alternativeSgsnAddressForTraffic=address;
	}

	@Override
	public DirectTunnelFlags getDirectTunnelFlags() 
	{
		return directTunnelFlags;
	}

	@Override
	public void setDirectTunnelFlags(DirectTunnelFlags flags) 
	{
		this.directTunnelFlags=flags;
	}
}