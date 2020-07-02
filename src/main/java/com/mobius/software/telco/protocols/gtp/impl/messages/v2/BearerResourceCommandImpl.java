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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPSBearerID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ExtendedProtocolConfigurationOptions;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FContainer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FlowQOS;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Indication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.OverloadControlInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ProcedureTransactionID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ProtocolConfigurationOption;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RatType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ServingNetwork;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SignalingPriorityIndication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TrafficAggregationDescriptor;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UserLocationInformation;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.BearerResourceCommand;

public class BearerResourceCommandImpl extends AbstractGTP2Message implements BearerResourceCommand
{
	EPSBearerID linkedEPSBearerID;
	ProcedureTransactionID procedureTransactionID;
	FlowQOS flowQOS;
	TrafficAggregationDescriptor trafficAggregationDescriptor;
	RatType ratType;
	ServingNetwork servingNetwork;
	UserLocationInformation userLocationInformation;
	EPSBearerID epsBearerID;
	Indication indication;
	FTEID sgsnFTEID;
	FTEID rncFTEID;
	ProtocolConfigurationOption protocolConfigurationOption;
	SignalingPriorityIndication signalingPriorityIndication;
	OverloadControlInformation sgsnOverloadControlInformation;
	OverloadControlInformation sgwOverloadControlInformation;
	FContainer nbifomContainer;
	ExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions;
	FTEID senderFTEIDControlPlane;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.BEARER_RESOURCE_COMMAND;
	}

	@Override
	public void applyTLV(TLV2 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case PROCEDURE_TRANSACTION_ID:
				procedureTransactionID=(ProcedureTransactionID)tlv;
				break;
			case FLOW_QOS:
				flowQOS=(FlowQOS)tlv;
				break;
			case TRAFFIC_AGGREGATION_DESCRIPTION:
				trafficAggregationDescriptor=(TrafficAggregationDescriptor)tlv;
				break;
			case USER_LOCATION_INFORMATION:
				switch(tlv.getInstance())
				{
					case 0:
						userLocationInformation=(UserLocationInformation)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case SERVING_NETWORK:
				servingNetwork=(ServingNetwork)tlv;
				break;
			case RAT_TYPE:
				ratType=(RatType)tlv;
				break;
			case INDICATION:
				indication=(Indication)tlv;
				break;
			case FTEID:
				switch(tlv.getInstance())
				{
					case 0:
						sgsnFTEID=(FTEID)tlv;
						break;
					case 1:
						rncFTEID=(FTEID)tlv;
						break;
					case 2:
						senderFTEIDControlPlane=(FTEID)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case EPS_BEARER_ID:
				switch(tlv.getInstance())
				{
					case 0:
						linkedEPSBearerID=(EPSBearerID)tlv;
						break;
					case 1:
						epsBearerID=(EPSBearerID)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case PROTOCOL_CONFIGURATION_OPTIONS:
				protocolConfigurationOption=(ProtocolConfigurationOption)tlv;
				break;
			case SIGNALING_PRIORITY_INDICATOR:
				signalingPriorityIndication=(SignalingPriorityIndication)tlv;
				break;
			case OVERLOAD_CONTROL_INFORMATION:
				switch(tlv.getInstance())
				{
					case 0:
						sgsnOverloadControlInformation=(OverloadControlInformation)tlv;
						break;
					case 1:
						sgwOverloadControlInformation=(OverloadControlInformation)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case FCONTAINER:
				nbifomContainer=(FContainer)tlv;
				break;
			case EXTENDED_PROTOCOL_CONFIGURATION_OPTIONS:
				extendedProtocolConfigurationOptions=(ExtendedProtocolConfigurationOptions)tlv;
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
		if(linkedEPSBearerID==null)
			throw new GTPParseException("Linked EPS Bearer ID not set");
		
		output.add(linkedEPSBearerID);
		
		if(procedureTransactionID==null)
			throw new GTPParseException("Procedure Transaction ID not set");
		
		output.add(procedureTransactionID);
		
		if(flowQOS!=null)
			output.add(flowQOS);
		
		if(trafficAggregationDescriptor==null)
			throw new GTPParseException("Traffic Aggregation Descriptor not set");
		
		output.add(trafficAggregationDescriptor);
		
		if(ratType!=null)
			output.add(ratType);
		
		if(servingNetwork!=null)
			output.add(servingNetwork);
		
		if(userLocationInformation!=null)
			output.add(userLocationInformation);
		
		if(epsBearerID!=null)
			output.add(epsBearerID);
			
		if(indication!=null)
			output.add(indication);
		
		if(sgsnFTEID!=null)
			output.add(sgsnFTEID);
		
		if(rncFTEID!=null)
			output.add(rncFTEID);
		
		if(protocolConfigurationOption!=null)
			output.add(protocolConfigurationOption);
		
		if(signalingPriorityIndication!=null)
			output.add(signalingPriorityIndication);
		
		if(sgsnOverloadControlInformation!=null)
			output.add(sgsnOverloadControlInformation);
		
		if(sgwOverloadControlInformation!=null)
			output.add(sgwOverloadControlInformation);
		
		if(nbifomContainer!=null)
			output.add(nbifomContainer);
		
		if(extendedProtocolConfigurationOptions!=null)
			output.add(extendedProtocolConfigurationOptions);
		
		if(senderFTEIDControlPlane==null)
			throw new GTPParseException("sender FTEID CP not set");
		
		output.add(senderFTEIDControlPlane);
		
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
	public UserLocationInformation getUserLocationInformation() 
	{
		return userLocationInformation;
	}

	@Override
	public void setUserLocationInformation(UserLocationInformation uli) 
	{
		uli.setInstance(0);
		this.userLocationInformation=uli;
	}

	@Override
	public ServingNetwork getServingNetwork() 
	{
		return servingNetwork;
	}

	@Override
	public void setServingNetwork(ServingNetwork servingNetwork) 
	{
		servingNetwork.setInstance(0);
		this.servingNetwork=servingNetwork;
	}

	@Override
	public RatType getRatType() 
	{
		return ratType;
	}

	@Override
	public void setRatType(RatType ratType) 
	{
		ratType.setInstance(0);
		this.ratType=ratType;
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
	public EPSBearerID getEPSBearerID() 
	{
		return epsBearerID;
	}

	@Override
	public void setEPSBearerID(EPSBearerID epsBearerID) 
	{
		epsBearerID.setInstance(1);
		this.epsBearerID=epsBearerID;
	}

	@Override
	public ProtocolConfigurationOption getProtocolConfigurationOption() 
	{
		return protocolConfigurationOption;
	}

	@Override
	public void setProtocolConfigurationOption(ProtocolConfigurationOption pco) 
	{
		pco.setInstance(0);
		this.protocolConfigurationOption=pco;
	}

	@Override
	public OverloadControlInformation getSGSNOverloadControlInformation() 
	{
		return sgsnOverloadControlInformation;
	}

	@Override
	public void setSGSNOverloadControlInformation(OverloadControlInformation overloadControlInformation) 
	{
		overloadControlInformation.setInstance(0);
		this.sgsnOverloadControlInformation=overloadControlInformation;
	}

	@Override
	public OverloadControlInformation getSGWOverloadControlInformation() 
	{
		return sgwOverloadControlInformation;
	}

	@Override
	public void setSGWOverloadControlInformation(OverloadControlInformation overloadControlInformation) 
	{
		overloadControlInformation.setInstance(1);
		this.sgwOverloadControlInformation=overloadControlInformation;
	}

	@Override
	public FContainer getNBIFOMContainer() 
	{
		return nbifomContainer;
	}

	@Override
	public void setNBIFOMContainer(FContainer nbifomContainer) 
	{
		nbifomContainer.setInstance(0);
		this.nbifomContainer=nbifomContainer;
	}

	@Override
	public ExtendedProtocolConfigurationOptions getExtendedProtocolConfigurationOptions() 
	{
		return extendedProtocolConfigurationOptions;
	}

	@Override
	public void setExtendedProtocolConfigurationOptions(ExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions) 
	{
		extendedProtocolConfigurationOptions.setInstance(0);
		this.extendedProtocolConfigurationOptions=extendedProtocolConfigurationOptions;
	}

	@Override
	public EPSBearerID getLinkedEPSBearedID() 
	{
		return linkedEPSBearerID;
	}

	@Override
	public void setLinkedEPSBearedID(EPSBearerID epsBearerID) 
	{
		epsBearerID.setInstance(0);
		this.linkedEPSBearerID=epsBearerID;
	}

	@Override
	public ProcedureTransactionID getProcedureTransactionID() 
	{
		return procedureTransactionID;
	}

	@Override
	public void setProcedureTransactionID(ProcedureTransactionID procedureTransactionID) 
	{
		procedureTransactionID.setInstance(0);
		this.procedureTransactionID=procedureTransactionID;
	}

	@Override
	public FlowQOS getFlowQOS() 
	{
		return this.flowQOS;
	}

	@Override
	public void setFlowQOS(FlowQOS flowQOS) 
	{
		flowQOS.setInstance(0);
		this.flowQOS=flowQOS;
	}

	@Override
	public TrafficAggregationDescriptor getTrafficAggregationDescriptor() 
	{
		return trafficAggregationDescriptor;
	}

	@Override
	public void setTrafficAggregationDescriptor(TrafficAggregationDescriptor trafficAggregationDescriptor) 
	{
		trafficAggregationDescriptor.setInstance(0);
		this.trafficAggregationDescriptor=trafficAggregationDescriptor;
	}

	@Override
	public FTEID getSGSNFTEID() 
	{		
		return this.sgsnFTEID;
	}

	@Override
	public void setSGSNFTEID(FTEID sgsnFTEID) 
	{
		sgsnFTEID.setInstance(0);
		this.sgsnFTEID=sgsnFTEID;
	}

	@Override
	public FTEID getRNCFTEID() 
	{
		return rncFTEID;
	}

	@Override
	public void setRNCFTEID(FTEID rncFTEID) 
	{
		rncFTEID.setInstance(1);
		this.rncFTEID=rncFTEID;
	}

	@Override
	public SignalingPriorityIndication getSignalingPriorityIndication() 
	{
		return this.signalingPriorityIndication;
	}

	@Override
	public void setSignalingPriorityIndication(SignalingPriorityIndication signalingPriorityIndication) {
		signalingPriorityIndication.setInstance(0);
		this.signalingPriorityIndication=signalingPriorityIndication;
	}

	@Override
	public FTEID getSenderFTEIDForControlPane() 
	{
		return this.senderFTEIDControlPlane;
	}

	@Override
	public void setSenderFTEIDForControlPane(FTEID senderFTEIDForControlPage) 
	{
		senderFTEIDForControlPage.setInstance(2);
		this.senderFTEIDControlPlane=senderFTEIDForControlPage;
	}
}