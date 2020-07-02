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

import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.CreateSessionResponseBearerContextToBeCreated;
import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.CreateSessionResponseBearerContextToBeRemoved;
import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.AMBR;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.APNRestriction;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.AdditionalProtocolConfigurationOption;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CSGInformationReportingAction;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ChangeReportingAction;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ChargingID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPCTimer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPSBearerID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ExtendedProtocolConfigurationOptions;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FContainer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FQCSID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FQDN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.HENBInformationReporting;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IPAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IPV4ConfigurationParameters;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Indication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.LDN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.LoadControlInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.OverloadControlInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PDNAddressAllocation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PresenceAreaReportingAction;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ProtocolConfigurationOption;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.CreateSessionResponse;

public class CreateSessionResponseImpl extends AbstractGTP2Message implements CreateSessionResponse
{
	private Cause cause;
	private ChangeReportingAction changeReportingAction;
	private CSGInformationReportingAction csgInformationReportingAction;
	private HENBInformationReporting heNBInformationReporting;
	private FTEID senderFTEIDControlPlane;
	private FTEID pgwAddressForControlPlane;
	private PDNAddressAllocation pdnAddressAllocation;
	private APNRestriction maximumAPNRestriction;
	private AMBR apnAMBR;
	private EPSBearerID epsBearerID;
	private ProtocolConfigurationOption protocolConfigurationOption;
	private List<CreateSessionResponseBearerContextToBeCreated> bearerContextCreated;
	private List<CreateSessionResponseBearerContextToBeRemoved> bearerContextMarkedForRemoved;
	private Recovery recovery;
	private FQDN chargingGatewayName;
	private IPAddress chargingGatewayAddress;
	private FQCSID pgwFQCSID;
	private FQCSID sgwFQCSID;
	private LDN sgwLDN;
	private LDN pgwLDN;
	private EPCTimer pgwBackoffTime;
	private AdditionalProtocolConfigurationOption additionalProtocolConfigurationOption;
	private IPV4ConfigurationParameters trustedWLANIPv4Parameters;
	private Indication indication;
	private PresenceAreaReportingAction presenceAreaReportingAction;
	private LoadControlInformation pgwNodeLoadControlInformation;
	private LoadControlInformation pgwAPNLoadControlInformation;
	private LoadControlInformation sgwNodeLoadControlInformation;
	private OverloadControlInformation pgwOverloadControlInformation;
	private OverloadControlInformation sgwOverloadControlInformation;
	private FContainer nbifomContainer;
	private ChargingID pdnConnectionChargingID;
	private ExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.CREATE_SESSION_RESPONSE;
	}

	@Override
	public void applyTLV(TLV2 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case CAUSE:
				cause=(Cause)tlv;
				break;
			case CHANGE_REPORTING_ACTION:
				changeReportingAction=(ChangeReportingAction)tlv;
				break;
			case CSG_INFORMATION_REPORTING_ACTION:
				csgInformationReportingAction=(CSGInformationReportingAction)tlv;
				break;
			case HENB_INFORMATION_REPORTING:
				heNBInformationReporting=(HENBInformationReporting)tlv;
				break;
			case FTEID:
				switch(tlv.getInstance())
				{
					case 0:
						senderFTEIDControlPlane=(FTEID)tlv;
						break;
					case 1:
						pgwAddressForControlPlane=(FTEID)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case PDN_ADDRESS_ALLOCATION:
				pdnAddressAllocation=(PDNAddressAllocation)tlv;
				break;
			case APN_RESTRICTION:
				maximumAPNRestriction=(APNRestriction)tlv;
				break;
			case AMBR:
				apnAMBR=(AMBR)tlv;
				break;
			case EPS_BEARER_ID:
				epsBearerID=(EPSBearerID)tlv;
				break;
			case PROTOCOL_CONFIGURATION_OPTIONS:
				protocolConfigurationOption=(ProtocolConfigurationOption)tlv;
				break;
			case BEARER_CONTEXT:
				switch(tlv.getInstance())
				{
					case 0:
						if(bearerContextCreated==null)
							bearerContextCreated=new ArrayList<CreateSessionResponseBearerContextToBeCreated>();
						
						bearerContextCreated.add((CreateSessionResponseBearerContextToBeCreated)tlv);
						break;
					case 1:
						if(bearerContextMarkedForRemoved==null)
							bearerContextMarkedForRemoved=new ArrayList<CreateSessionResponseBearerContextToBeRemoved>();
						
						bearerContextMarkedForRemoved.add((CreateSessionResponseBearerContextToBeRemoved)tlv);
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case RECOVERY:
				recovery=(Recovery)tlv;
				break;
			case FQDN:
				chargingGatewayName=(FQDN)tlv;
				break;
			case IP_ADDRESS:
				chargingGatewayAddress=(IPAddress)tlv;
				break;
			case FQ_CSID:
				switch(tlv.getInstance())
				{
					case 0:
						pgwFQCSID=(FQCSID)tlv;
						break;
					case 1:
						sgwFQCSID=(FQCSID)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case LDN:
				switch(tlv.getInstance())
				{
					case 0:
						pgwLDN=(LDN)tlv;
						break;
					case 1:
						sgwLDN=(LDN)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case EPC_TIMER:
				pgwBackoffTime=(EPCTimer)tlv;
				break;
			case ADDITIONAL_PROTOCOL_CONFIGURATION_OPTIONS:
				additionalProtocolConfigurationOption=(AdditionalProtocolConfigurationOption)tlv;
				break;
			case IPV4_CONFIGURATION_PARAMETERS:
				trustedWLANIPv4Parameters=(IPV4ConfigurationParameters)tlv;
				break;
			case INDICATION:
				indication=(Indication)tlv;
				break;
			case PRESENCE_REPORTING_AREA_ACTION:
				presenceAreaReportingAction=(PresenceAreaReportingAction)tlv;
				break;
			case LOAD_CONTROL_INFORMATION:
				switch(tlv.getInstance())
				{
					case 0:
						pgwNodeLoadControlInformation=(LoadControlInformation)tlv;
						break;
					case 1:
						pgwAPNLoadControlInformation=(LoadControlInformation)tlv;
						break;
					case 2:
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
						pgwOverloadControlInformation=(OverloadControlInformation)tlv;
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
			case CHARGING_ID:
				pdnConnectionChargingID=(ChargingID)tlv;
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
		if(cause==null)
			throw new GTPParseException("Cause not set");
		
		output.add(cause);
		
		if(changeReportingAction!=null)
			output.add(changeReportingAction);
		
		if(csgInformationReportingAction!=null)
			output.add(csgInformationReportingAction);
		
		if(heNBInformationReporting!=null)
			output.add(heNBInformationReporting);
		
		if(senderFTEIDControlPlane!=null)
			output.add(senderFTEIDControlPlane);
		
		if(pgwAddressForControlPlane!=null)
			output.add(pgwAddressForControlPlane);
		
		if(pdnAddressAllocation!=null)
			output.add(pdnAddressAllocation);
		
		if(maximumAPNRestriction!=null)
			output.add(maximumAPNRestriction);
		
		if(apnAMBR!=null)
			output.add(apnAMBR);
		
		if(epsBearerID!=null)
			output.add(epsBearerID);
			
		if(protocolConfigurationOption!=null)
			output.add(protocolConfigurationOption);
		
		if(bearerContextCreated==null || bearerContextCreated.size()==0)
			throw new GTPParseException("Bearer Context created not set");
		
		output.addAll(bearerContextCreated);
		
		if(bearerContextMarkedForRemoved!=null && bearerContextMarkedForRemoved.size()>0)
			output.addAll(bearerContextMarkedForRemoved);
		
		if(recovery!=null)
			output.add(recovery);
		
		if(chargingGatewayName!=null)
			output.add(chargingGatewayName);
		
		if(chargingGatewayAddress!=null)
			output.add(chargingGatewayAddress);
		
		if(pgwFQCSID!=null)
			output.add(pgwFQCSID);
		
		if(sgwFQCSID!=null)
			output.add(sgwFQCSID);
		
		if(pgwLDN!=null)
			output.add(pgwLDN);
			
		if(sgwLDN!=null)
			output.add(sgwLDN);
			
		if(pgwBackoffTime!=null)
			output.add(pgwBackoffTime);
		
		if(additionalProtocolConfigurationOption!=null)
			output.add(additionalProtocolConfigurationOption);
		
		if(trustedWLANIPv4Parameters!=null)
			output.add(trustedWLANIPv4Parameters);
		
		if(indication!=null)
			output.add(indication);
		
		if(presenceAreaReportingAction!=null)
			output.add(presenceAreaReportingAction);
		
		if(pgwNodeLoadControlInformation!=null)
			output.add(pgwNodeLoadControlInformation);
		
		if(pgwAPNLoadControlInformation!=null)
			output.add(pgwAPNLoadControlInformation);
		
		if(sgwNodeLoadControlInformation!=null)
			output.add(sgwNodeLoadControlInformation);
		
		if(pgwOverloadControlInformation!=null)
			output.add(pgwOverloadControlInformation);
		
		if(sgwOverloadControlInformation!=null)
			output.add(sgwOverloadControlInformation);
		
		if(nbifomContainer!=null)
			output.add(nbifomContainer);
		
		if(pdnConnectionChargingID!=null)
			output.add(pdnConnectionChargingID);
		
		if(extendedProtocolConfigurationOptions!=null)
			output.add(extendedProtocolConfigurationOptions);
		
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
	public ChangeReportingAction getChangeReportingAction() 
	{
		return changeReportingAction;
	}

	@Override
	public void setChangeReportingAction(ChangeReportingAction changeReportingAction) 
	{
		changeReportingAction.setInstance(0);
		this.changeReportingAction=changeReportingAction;
	}

	@Override
	public CSGInformationReportingAction getCSGInformationReportingAction() 
	{
		return csgInformationReportingAction;
	}

	@Override
	public void setCSGInformationReportingAction(CSGInformationReportingAction action) 
	{
		action.setInstance(0);
		this.csgInformationReportingAction=action;
	}

	@Override
	public HENBInformationReporting getHENBInformationReporting() 
	{
		return heNBInformationReporting;
	}

	@Override
	public void setHENBInformationReporting(HENBInformationReporting reporting) 
	{
		reporting.setInstance(0);
		this.heNBInformationReporting=reporting;
	}

	@Override
	public FTEID getSenderFTEIDControlPlane() 
	{
		return senderFTEIDControlPlane;
	}

	@Override
	public void setSenderFTEIDControlPlane(FTEID fteid) 
	{
		fteid.setInstance(0);
		this.senderFTEIDControlPlane=fteid;
	}

	@Override
	public FTEID getPGWAddressForControlPlane() 
	{
		return pgwAddressForControlPlane;
	}

	@Override
	public void setPGWAddressForControlPlane(FTEID fteid) 
	{
		fteid.setInstance(1);
		this.pgwAddressForControlPlane=fteid;
	}

	@Override
	public PDNAddressAllocation getPDNAddressAllocation() 
	{
		return pdnAddressAllocation;
	}

	@Override
	public void setPDNAddressAllocation(PDNAddressAllocation pdnAddressAllocation) 
	{
		pdnAddressAllocation.setInstance(0);
		this.pdnAddressAllocation=pdnAddressAllocation;
	}

	@Override
	public APNRestriction getMaximumAPNRestriction() 
	{
		return this.maximumAPNRestriction;
	}

	@Override
	public void setMaximumAPNRestriction(APNRestriction apnRestriction) 
	{
		apnRestriction.setInstance(0);
		this.maximumAPNRestriction=apnRestriction;
	}

	@Override
	public AMBR getAPNAMBR() 
	{
		return this.apnAMBR;
	}

	@Override
	public void setAPNAMBR(AMBR ambr) 
	{
		apnAMBR.setInstance(0);
		this.apnAMBR=ambr;
	}

	@Override
	public EPSBearerID getEPSBearerID() 
	{
		return epsBearerID;
	}

	@Override
	public void setEPSBearerID(EPSBearerID epsBearerID) 
	{
		epsBearerID.setInstance(0);
		this.epsBearerID=epsBearerID;
	}

	@Override
	public ProtocolConfigurationOption getProtocolConfigurationOption() 
	{
		return this.protocolConfigurationOption;
	}

	@Override
	public void setProtocolConfigurationOption(ProtocolConfigurationOption pco) 
	{
		pco.setInstance(0);
		this.protocolConfigurationOption=pco;
	}

	@Override
	public List<CreateSessionResponseBearerContextToBeCreated> getBearerContextCreated() 
	{
		return this.bearerContextCreated;
	}

	@Override
	public void setBearerContextCreated(List<CreateSessionResponseBearerContextToBeCreated> bearerContext) 
	{
		if(bearerContext!=null)
			for(CreateSessionResponseBearerContextToBeCreated curr:bearerContext)
				curr.setInstance(0);
		
		this.bearerContextCreated=bearerContext;
	}

	@Override
	public List<CreateSessionResponseBearerContextToBeRemoved> getBearerContextMarkedForRemoved() 
	{
		return this.bearerContextMarkedForRemoved;
	}

	@Override
	public void setBearerContextMarkedForRemoved(List<CreateSessionResponseBearerContextToBeRemoved> bearerContext) 
	{
		if(bearerContext!=null)
			for(CreateSessionResponseBearerContextToBeRemoved curr:bearerContext)
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
	public FQDN getChargingGatewayName() 
	{
		return chargingGatewayName;
	}

	@Override
	public void setChargingGatewayName(FQDN name) 
	{
		name.setInstance(0);
		this.chargingGatewayName=name;
	}

	@Override
	public IPAddress getChargingGatewayAddress() 
	{
		return chargingGatewayAddress;
	}

	@Override
	public void setChargingGatewayAddress(IPAddress address) 
	{
		address.setInstance(0);
		this.chargingGatewayAddress=address;
	}

	@Override
	public FQCSID getPGWFQCSID() 
	{
		return this.pgwFQCSID;
	}

	@Override
	public void setPGWFQCSID(FQCSID fqcsid) 
	{
		fqcsid.setInstance(0);
		this.pgwFQCSID=fqcsid;
	}

	@Override
	public FQCSID getSGWFQCSID() 
	{
		return this.sgwFQCSID;
	}

	@Override
	public void setSGWFQCSID(FQCSID fqcsid) 
	{
		fqcsid.setInstance(1);
		this.sgwFQCSID=fqcsid;
	}

	@Override
	public LDN getSGWLDN() 
	{
		return this.sgwLDN;
	}

	@Override
	public void setSGWLDN(LDN ldn) 
	{
		ldn.setInstance(0);
		this.sgwLDN=ldn;
	}

	@Override
	public LDN getPGWLDN() 
	{
		return pgwLDN;
	}

	@Override
	public void setPGWLDN(LDN ldn) 
	{
		ldn.setInstance(1);
		this.pgwLDN=ldn;
	}

	@Override
	public EPCTimer getPGWBackoffTime() 
	{
		return pgwBackoffTime;
	}

	@Override
	public void setPGWBackoffTime(EPCTimer epcTimer) 
	{
		epcTimer.setInstance(0);
		this.pgwBackoffTime=epcTimer;
	}

	@Override
	public AdditionalProtocolConfigurationOption getAdditionalProtocolConfigurationOption() 
	{
		return this.additionalProtocolConfigurationOption;
	}

	@Override
	public void setAdditionalProtocolConfigurationOption(AdditionalProtocolConfigurationOption additionalProtocolConfigurationOption) 
	{
		additionalProtocolConfigurationOption.setInstance(0);
		this.additionalProtocolConfigurationOption=additionalProtocolConfigurationOption;
	}

	@Override
	public IPV4ConfigurationParameters getTrustedWLANIPv4Parameters() 
	{
		return this.trustedWLANIPv4Parameters;
	}

	@Override
	public void setTrustedWLANIPv4Parameters(IPV4ConfigurationParameters parameters) 
	{
		trustedWLANIPv4Parameters.setInstance(0);
		this.trustedWLANIPv4Parameters=parameters;
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
	public PresenceAreaReportingAction getPresenceAreaReportingAction() 
	{
		return this.presenceAreaReportingAction;
	}

	@Override
	public void setPresenceAreaReportingAction(PresenceAreaReportingAction action) 
	{
		action.setInstance(0);
		this.presenceAreaReportingAction=action;
	}

	@Override
	public LoadControlInformation getPGWNodeLoadControlInformation() 
	{		
		return this.pgwNodeLoadControlInformation;
	}

	@Override
	public void setPGWNodeLoadControlInformation(LoadControlInformation loadInformation) 
	{
		loadInformation.setInstance(0);
		this.pgwNodeLoadControlInformation=loadInformation;
	}

	@Override
	public LoadControlInformation getPGWAPNLoadControlInformation() 
	{
		return this.pgwAPNLoadControlInformation;
	}

	@Override
	public void setPGWAPNLoadControlInformation(LoadControlInformation loadInformation) 
	{
		loadInformation.setInstance(1);
		this.pgwAPNLoadControlInformation=loadInformation;
	}

	@Override
	public LoadControlInformation getSGWNodeLoadControlInformation() 
	{
		return this.sgwNodeLoadControlInformation;
	}

	@Override
	public void setSGWNodeLoadControlInformation(LoadControlInformation loadInformation) 
	{
		loadInformation.setInstance(2);
		this.sgwNodeLoadControlInformation=loadInformation;
	}

	@Override
	public OverloadControlInformation getPGWOverloadControlInformation() 
	{
		return this.pgwOverloadControlInformation;
	}

	@Override
	public void setPGWOverloadControlInformation(OverloadControlInformation overloadInformation) 
	{
		overloadInformation.setInstance(0);
		this.pgwOverloadControlInformation=overloadInformation;
	}

	@Override
	public OverloadControlInformation getSGWOverloadControlInformation() 
	{
		return sgwOverloadControlInformation;
	}

	@Override
	public void setSGWOverloadControlInformation(OverloadControlInformation overloadInformation) 
	{
		overloadInformation.setInstance(1);
		this.sgwOverloadControlInformation=overloadInformation;
	}

	@Override
	public FContainer getNBIFOMContainer() 
	{
		return this.nbifomContainer;
	}

	@Override
	public void setNBIFOMContainer(FContainer nbifomContainer) 
	{
		nbifomContainer.setInstance(0);
		this.nbifomContainer=nbifomContainer;
	}

	@Override
	public ChargingID getPDNConnectionChargingID() 
	{
		return this.pdnConnectionChargingID;
	}

	@Override
	public void setPDNConnectionChargingID(ChargingID chargingID) 
	{
		chargingID.setInstance(0);
		this.pdnConnectionChargingID=chargingID;
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
}