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
import com.mobius.software.telco.protocols.gtp.api.headers.CorrelationID;
import com.mobius.software.telco.protocols.gtp.api.headers.EvolvedAllocationRetentionPriority1;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.NSAPI;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.ProtocolConfigurationOption;
import com.mobius.software.telco.protocols.gtp.api.headers.QosProfile;
import com.mobius.software.telco.protocols.gtp.api.headers.TFT;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.messages.InitiatePdpContextActivationRequest;

public class InitiatePdpContextActivationRequestImpl extends AbstractGTPMessage implements InitiatePdpContextActivationRequest
{
	private NSAPI linkedNSAPI;
	private ProtocolConfigurationOption protocolConfigurationOption;
	private QosProfile qosProfile;
	private TFT tft;
	private CorrelationID correlationID;
	private EvolvedAllocationRetentionPriority1 artp1;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public MessageType getMessageType() 
	{
		return MessageType.INITIATE_PDP_CONTEXT_ACTIVATION_REQUEST;
	}

	@Override
	public void applyTLV(TLV1 tlv) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case NSAPI:
				linkedNSAPI=(NSAPI)tlv;
				break;
			case PROTOCOL_CONFIGURATION_OPTIONS:
				protocolConfigurationOption=(ProtocolConfigurationOption)tlv;
				break;
			case QOS_PROFILE:
				qosProfile=(QosProfile)tlv;
				break;
			case TRAFFIC_FLOW_TEMPLATE:
				tft=(TFT)tlv;
				break;
			case CORRELATION_ID:
				correlationID=(CorrelationID)tlv;
				break;
			case EVOLVED_ALLOCATION_RETENTION_PRIORITY_1:
				artp1=(EvolvedAllocationRetentionPriority1)tlv;
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
		if(linkedNSAPI==null)
			throw new GTPParseException("linked nsapi is missing");
		
		output.add(linkedNSAPI);
	
		if(protocolConfigurationOption!=null)
			output.add(protocolConfigurationOption);
	
		if(qosProfile!=null)
			output.add(qosProfile);
	
		if(tft!=null)
			output.add(tft);
	
		if(correlationID==null)
			throw new GTPParseException("correlation ID is missing");
		
		output.add(correlationID);
		
		if(artp1!=null)
			output.add(artp1);
		
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
	public NSAPI getLinkedNSAPI() 
	{
		return linkedNSAPI;
	}

	@Override
	public void setLinkedNSAPI(NSAPI nsapi) 
	{
		this.linkedNSAPI=nsapi;
	}

	@Override
	public ProtocolConfigurationOption getProtocolConfigurationOption() 
	{
		return protocolConfigurationOption;
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
	public CorrelationID getCorrelationID() 
	{
		return correlationID;
	}

	@Override
	public void setCorrelationID(CorrelationID correlationID) 
	{
		this.correlationID=correlationID;	
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
}