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

import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.ModifyBearerResponseBearerContextToBeModified;
import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.ModifyBearerResponseBearerContextToBeRemoved;
import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.APNRestriction;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CSGInformationReportingAction;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ChangeReportingAction;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ChargingID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPSBearerID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FQCSID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FQDN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.HENBInformationReporting;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IPAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Indication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.LDN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.LoadControlInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MSISDN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.OverloadControlInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PresenceAreaReportingAction;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ProtocolConfigurationOption;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.ModifyBearerResponse;

public class ModifyBearerResponseImpl extends AbstractGTP2Message implements ModifyBearerResponse
{
	private Cause cause;
	private MSISDN msisdn;
	private EPSBearerID epsBearerID;
	private APNRestriction maximumAPNRestriction;
	private ProtocolConfigurationOption protocolConfigurationOption;
	private List<ModifyBearerResponseBearerContextToBeModified> bearerContextModified;
	private List<ModifyBearerResponseBearerContextToBeRemoved> bearerContextMarkedForRemoved;
	private ChangeReportingAction changeReportingAction;
	private CSGInformationReportingAction csgInformationReportingAction;
	private HENBInformationReporting heNBInformationReporting;
	private FQDN chargingGatewayName;
	private IPAddress chargingGatewayAddress;
	private FQCSID pgwFQCSID;
	private FQCSID sgwFQCSID;
	private Recovery recovery;
	private LDN sgwLDN;
	private LDN pgwLDN;
	private Indication indication;
	private PresenceAreaReportingAction presenceAreaReportingAction;
	private LoadControlInformation pgwNodeLoadControlInformation;
	private LoadControlInformation pgwAPNLoadControlInformation;
	private LoadControlInformation sgwNodeLoadControlInformation;
	private OverloadControlInformation pgwOverloadControlInformation;
	private OverloadControlInformation sgwOverloadControlInformation;
	private ChargingID pdnConnectionChargingID;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.MODIFY_BEARER_RESPONSE;
	}

	@Override
	public void applyTLV(TLV2 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case CAUSE:
				cause=(Cause)tlv;
				break;
			case MSISDN:
				msisdn=(MSISDN)tlv;
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
			case APN_RESTRICTION:
				maximumAPNRestriction=(APNRestriction)tlv;
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
						if(bearerContextModified==null)
							bearerContextModified=new ArrayList<ModifyBearerResponseBearerContextToBeModified>();
						
						bearerContextModified.add((ModifyBearerResponseBearerContextToBeModified)tlv);
						break;
					case 1:
						if(bearerContextMarkedForRemoved==null)
							bearerContextMarkedForRemoved=new ArrayList<ModifyBearerResponseBearerContextToBeRemoved>();
						
						bearerContextMarkedForRemoved.add((ModifyBearerResponseBearerContextToBeRemoved)tlv);
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
			case CHARGING_ID:
				pdnConnectionChargingID=(ChargingID)tlv;
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
		
		if(msisdn!=null)
			output.add(msisdn);
		
		if(epsBearerID!=null)
			output.add(epsBearerID);
			
		if(maximumAPNRestriction!=null)
			output.add(maximumAPNRestriction);
		
		if(protocolConfigurationOption!=null)
			output.add(protocolConfigurationOption);
		
		if(bearerContextModified!=null && bearerContextModified.size()>0)
			output.addAll(bearerContextModified);
		
		if(bearerContextMarkedForRemoved!=null && bearerContextMarkedForRemoved.size()>0)
			output.addAll(bearerContextMarkedForRemoved);
		
		if(changeReportingAction!=null)
			output.add(changeReportingAction);
		
		if(csgInformationReportingAction!=null)
			output.add(csgInformationReportingAction);
		
		if(heNBInformationReporting!=null)
			output.add(heNBInformationReporting);
		
		if(chargingGatewayName!=null)
			output.add(chargingGatewayName);
		
		if(chargingGatewayAddress!=null)
			output.add(chargingGatewayAddress);
		
		if(pgwFQCSID!=null)
			output.add(pgwFQCSID);
		
		if(sgwFQCSID!=null)
			output.add(sgwFQCSID);
		
		if(recovery!=null)
			output.add(recovery);
		
		if(pgwLDN!=null)
			output.add(pgwLDN);
			
		if(sgwLDN!=null)
			output.add(sgwLDN);
			
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
		
		if(pdnConnectionChargingID!=null)
			output.add(pdnConnectionChargingID);
		
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
	public List<ModifyBearerResponseBearerContextToBeModified> getBearerContextModified() 
	{
		return this.bearerContextModified;
	}

	@Override
	public void setBearerContextModified(List<ModifyBearerResponseBearerContextToBeModified> bearerContext) 
	{
		if(bearerContext!=null)
			for(ModifyBearerResponseBearerContextToBeModified curr:bearerContext)
				curr.setInstance(0);
		
		this.bearerContextModified=bearerContext;
	}

	@Override
	public List<ModifyBearerResponseBearerContextToBeRemoved> getBearerContextMarkedForRemoved() 
	{
		return this.bearerContextMarkedForRemoved;
	}

	@Override
	public void setBearerContextMarkedForRemoved(List<ModifyBearerResponseBearerContextToBeRemoved> bearerContext) 
	{
		if(bearerContext!=null)
			for(ModifyBearerResponseBearerContextToBeRemoved curr:bearerContext)
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
	public MSISDN getMSISDN() 
	{
		return this.msisdn;
	}

	@Override
	public void setMSISDN(MSISDN msisdn) 
	{
		msisdn.setInstance(0);
		this.msisdn=msisdn;
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
}