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
import com.mobius.software.telco.protocols.gtp.api.headers.APNRestriction;
import com.mobius.software.telco.protocols.gtp.api.headers.BearerMode;
import com.mobius.software.telco.protocols.gtp.api.headers.CSGInformationReportingAction;
import com.mobius.software.telco.protocols.gtp.api.headers.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.ChangeReportAction;
import com.mobius.software.telco.protocols.gtp.api.headers.ChargingGatewayAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.ChargingID;
import com.mobius.software.telco.protocols.gtp.api.headers.CommonFlags;
import com.mobius.software.telco.protocols.gtp.api.headers.EndUserAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.EvolvedAllocationRetentionPriority1;
import com.mobius.software.telco.protocols.gtp.api.headers.GGSNBackoffTime;
import com.mobius.software.telco.protocols.gtp.api.headers.GSNAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.NSAPI;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.ProtocolConfigurationOption;
import com.mobius.software.telco.protocols.gtp.api.headers.QosProfile;
import com.mobius.software.telco.protocols.gtp.api.headers.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.ReorderingRequired;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifier1;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifierControlPane;
import com.mobius.software.telco.protocols.gtp.api.messages.CreatePdpContextResponse;

public class CreatePdpContextResponseImpl extends AbstractGTPMessage implements CreatePdpContextResponse
{
	private Cause cause;
	private ReorderingRequired reorderingRequired;
	private Recovery recovery;
	private TunnerEndpointIdentifier1 tei;
	private TunnerEndpointIdentifierControlPane controlTEI;
	private NSAPI nsapi;
	private ChargingID chargingID;
	private EndUserAddress endUserAddress;
	private ProtocolConfigurationOption protocolConfigurationOption;
	private GSNAddress ggsnAddressForSignaling;
	private GSNAddress ggsnAddressForTraffic;
	private GSNAddress alternateGgsnAddressForSignaling;
	private GSNAddress alternateGgsnAddressForTraffic;
	private QosProfile qosProfile;
	private ChargingGatewayAddress chargingGatewayAddress;
	private ChargingGatewayAddress alternateChargingGatewayAddress;
	private CommonFlags commonFlags;
	private APNRestriction apnRestriction;
	private ChangeReportAction changeReportAction;
	private BearerMode bearerControlMode;
	private EvolvedAllocationRetentionPriority1 artp1;
	private CSGInformationReportingAction reportingAction;
	private APNAMBR apnAMBR;
	private GGSNBackoffTime ggsnBackoffTime;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public MessageType getMessageType() 
	{
		return MessageType.CREATE_PDP_CONTEXT_RESPONSE;
	}

	@Override
	public void applyTLV(TLV1 tlv) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case CAUSE:
				cause=(Cause)tlv;
				break;
			case REORDERING_REQUIRED:
				reorderingRequired=(ReorderingRequired)tlv;
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
			case CHARGING_ID:
				chargingID=(ChargingID)tlv;
				break;
			case END_USER_ADDRESS:
				endUserAddress=(EndUserAddress)tlv;
				break;
			case PROTOCOL_CONFIGURATION_OPTIONS:
				protocolConfigurationOption=(ProtocolConfigurationOption)tlv;
				break;
			case GSN_ADDRESS:
				if(ggsnAddressForSignaling==null)
					ggsnAddressForSignaling=(GSNAddress)tlv;
				else if(ggsnAddressForTraffic==null)
					ggsnAddressForTraffic=(GSNAddress)tlv;
				else if(alternateGgsnAddressForSignaling==null)
					alternateGgsnAddressForSignaling=(GSNAddress)tlv;
				else if(alternateGgsnAddressForTraffic==null)
					alternateGgsnAddressForTraffic=(GSNAddress)tlv;
				else
					throw new GTPParseException("Unknown TLV received,type:" + tlv.getElementType());
				break;
			case QOS_PROFILE:
				qosProfile=(QosProfile)tlv;
				break;
			case CHARGING_GATEWAY_ADDRESS:
				if(chargingGatewayAddress==null)
					chargingGatewayAddress=(ChargingGatewayAddress)tlv;
				else if(alternateChargingGatewayAddress==null)
					alternateChargingGatewayAddress=(ChargingGatewayAddress)tlv;
				else
					throw new GTPParseException("Unknown TLV received,type:" + tlv.getElementType());
				break;
			case COMMON_FLAGS:
				commonFlags=(CommonFlags)tlv;
				break;
			case APN_RESTRICTION:
				apnRestriction=(APNRestriction)tlv;
				break;
			case MS_INFO_CHANGE_REPORTING_ACTION:
				changeReportAction=(ChangeReportAction)tlv;
				break;
			case BEARER_CONTROL_MODE:
				bearerControlMode=(BearerMode)tlv;
				break;
			case EVOLVED_ALLOCATION_RETENTION_PRIORITY_1:
				artp1=(EvolvedAllocationRetentionPriority1)tlv;
				break;
			case CSG_INFORMATION_REPORTING_ACTION:
				reportingAction=(CSGInformationReportingAction)tlv;
				break;
			case APN_AMBR:
				apnAMBR=(APNAMBR)tlv;
				break;
			case GGSN_BACK_OFF_TIME:
				ggsnBackoffTime=(GGSNBackoffTime)tlv;
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
	
		if(reorderingRequired!=null)
			output.add(reorderingRequired);
		
		if(recovery!=null)
			output.add(recovery);
	
		if(tei!=null)
			output.add(tei);
	
		if(controlTEI!=null)
			output.add(controlTEI);
	
		if(nsapi!=null)
			output.add(nsapi);
	
		if(chargingID!=null)
			output.add(chargingID);
	
		if(endUserAddress!=null)
			output.add(endUserAddress);
	
		if(protocolConfigurationOption!=null)
			output.add(protocolConfigurationOption);
	
		if(ggsnAddressForSignaling!=null)
			output.add(ggsnAddressForSignaling);
	
		if(ggsnAddressForTraffic!=null)
			output.add(ggsnAddressForTraffic);
	
		if(alternateGgsnAddressForSignaling!=null)
			output.add(alternateGgsnAddressForSignaling);
	
		if(alternateGgsnAddressForTraffic!=null)
			output.add(alternateGgsnAddressForTraffic);
	
		if(qosProfile!=null)
			output.add(qosProfile);
	
		if(chargingGatewayAddress!=null)
			output.add(chargingGatewayAddress);
	
		if(alternateChargingGatewayAddress!=null)
			output.add(alternateChargingGatewayAddress);
	
		if(commonFlags!=null)
			output.add(commonFlags);
	
		if(apnRestriction!=null)
			output.add(apnRestriction);
	
		if(changeReportAction!=null)
			output.add(changeReportAction);
	
		if(bearerControlMode!=null)
			output.add(bearerControlMode);
	
		if(artp1!=null)
			output.add(artp1);
		
		if(reportingAction!=null)
			output.add(reportingAction);
		
		if(apnAMBR!=null)
			output.add(apnAMBR);
		
		if(ggsnBackoffTime!=null)
			output.add(ggsnBackoffTime);
		
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
		return this.cause;
	}

	@Override
	public void setCause(Cause cause) 
	{
		this.cause=cause;
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
	public GSNAddress getGGSNAddressForSignaling() 
	{
		return ggsnAddressForSignaling;
	}

	@Override
	public void setGGSNAddressForSignaling(GSNAddress address) 
	{
		this.ggsnAddressForSignaling=address;
	}

	@Override
	public GSNAddress getGGSNAddressForTraffic() 
	{
		return ggsnAddressForTraffic;
	}

	@Override
	public void setGGSNAddressForTraffic(GSNAddress address) 
	{
		this.ggsnAddressForTraffic=address;
	}

	@Override
	public GSNAddress getAlternateGGSNAddressForSignaling() 
	{
		return alternateGgsnAddressForSignaling;
	}

	@Override
	public void setAlternateGGSNAddressForSignaling(GSNAddress address) 
	{
		this.alternateGgsnAddressForSignaling=address;
	}

	@Override
	public GSNAddress getAlternateGGSNAddressForTraffic() 
	{
		return alternateGgsnAddressForTraffic;
	}

	@Override
	public void setAlternateGGSNAddressForTraffic(GSNAddress address) 
	{
		this.alternateGgsnAddressForTraffic=address;
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
	public APNRestriction getAPNRestriction() 
	{
		return apnRestriction;
	}

	@Override
	public void setAPNRestriction(APNRestriction restriction) 
	{
		this.apnRestriction=restriction;
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
	public ReorderingRequired getReorderingRequired() 
	{
		return this.reorderingRequired;
	}

	@Override
	public void setReorderingRequired(ReorderingRequired reorderingRequired) 
	{
		this.reorderingRequired=reorderingRequired;
	}

	@Override
	public ChargingID getChargingID() 
	{
		return this.chargingID;
	}

	@Override
	public void setChargingID(ChargingID chargingID) 
	{
		this.chargingID=chargingID;
	}

	@Override
	public ChargingGatewayAddress getChargingGatewayAddress() 
	{
		return this.chargingGatewayAddress;
	}

	@Override
	public void setChargingGatewayAddress(ChargingGatewayAddress address) 
	{
		this.chargingGatewayAddress=address;
	}

	@Override
	public ChargingGatewayAddress getAlternateChargingGatewayAddress() 
	{
		return alternateChargingGatewayAddress;
	}

	@Override
	public void setAlternateChargingGatewayAddress(ChargingGatewayAddress address) 
	{
		this.alternateChargingGatewayAddress=address;
	}

	@Override
	public ChangeReportAction getMSInfoChangeReportAction() 
	{
		return this.changeReportAction;
	}

	@Override
	public void setMSInfoChangeReportAction(ChangeReportAction action) 
	{
		this.changeReportAction=action;
	}

	@Override
	public BearerMode getBearerControlMode() 
	{
		return this.bearerControlMode;
	}

	@Override
	public void setBearerControlMode(BearerMode mode) 
	{
		this.bearerControlMode=mode;
	}

	@Override
	public CSGInformationReportingAction getCSGInformationReportingAction() 
	{
		return reportingAction;
	}

	@Override
	public void setCSGInformationReportingAction(CSGInformationReportingAction action) 
	{
		this.reportingAction=action;
	}

	@Override
	public GGSNBackoffTime getGGSNBackoffTime() 
	{
		return ggsnBackoffTime;
	}

	@Override
	public void setGGSNBackoffTime(GGSNBackoffTime time) 
	{
		this.ggsnBackoffTime=time;
	}
}