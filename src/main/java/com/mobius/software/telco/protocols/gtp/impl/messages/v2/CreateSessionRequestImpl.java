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

import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.CreateSessionRequestBearerContextToBeCreated;
import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.CreateSessionRequestBearerContextToBeRemoved;
import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.AMBR;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.APN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.APNRestriction;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.AdditionalProtocolConfigurationOption;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CNOperatorSelectionEntity;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CSGInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ChargingCharacteristic;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Counter;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPSBearerID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ExtendedProtocolConfigurationOptions;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FContainer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FQCSID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FQDN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IPAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Indication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IntegerNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.LDN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MEI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MSISDN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MappedUEUssageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MillisecondTimeStamp;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.NodeIdentifier;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.OverloadControlInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PDNAddressAllocation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PDNType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PRAInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PortNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ProtocolConfigurationOption;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RatType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RemoteUEContext;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SecondaryRatUsageDataReport;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SelectionMode;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ServingNetwork;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ServingPLMNRateControl;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SignalingPriorityIndication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TWANIdentifier;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TWANTimeStamp;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TraceInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TrustedWLANModeIndication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UETimezone;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UPFunctionSelectionIndicationFlags;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UserLocationInformation;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.CreateSessionRequest;

public class CreateSessionRequestImpl extends AbstractGTP2Message implements CreateSessionRequest
{
	IMSI imsi;
	MSISDN msisdn;
	MEI mei;
	UserLocationInformation userLocationInformation;
	ServingNetwork servingNetwork;
	RatType ratType;
	Indication indication;
	FTEID senderFTEIDControlPlane;
	FTEID pgwAddressForControlPlane;
	APN apn;
	SelectionMode selectionMode;
	PDNType pdnType;
	PDNAddressAllocation pdnAddressAllocation;
	APNRestriction maximumAPNRestriction;
	AMBR apnAMBR;
	EPSBearerID epsBearerID;
	TrustedWLANModeIndication trustedWLANModeIndication;
	ProtocolConfigurationOption protocolConfigurationOption;
	List<CreateSessionRequestBearerContextToBeCreated> bearerContextToBeCreated;
	List<CreateSessionRequestBearerContextToBeRemoved> bearerContextToBeRemoved;
	TraceInformation traceInformation;
	Recovery recovery;
	FQCSID mmeFQCSID;
	FQCSID sgwFQCSID;
	FQCSID epdgFQCSID;
	FQCSID twanFQCSID;
	UETimezone ueTimezone;
	CSGInformation userCSGInformation;
	ChargingCharacteristic chargingCharacteristic;
	LDN sgsnLDN;
	LDN sgwLDN;
	LDN epdgLDN;
	LDN twanLDN;
	SignalingPriorityIndication signalingPriorityIndication;
	IPAddress ueLocalIPAddress;
	PortNumber ueUDPPort;
	AdditionalProtocolConfigurationOption additionalProtocolConfigurationOption;
	IPAddress nbLocalIPAddress;
	PortNumber nbUDPPort;
	IPAddress sgsnIdentifier;
	TWANIdentifier twanIdentifier;
	IPAddress epdgIPAddress;
	CNOperatorSelectionEntity cnOperatorSelectionEntity;
	PRAInformation praInformation;
	OverloadControlInformation sgsnOverloadControlInformation;
	OverloadControlInformation sgwOverloadControlInformation;
	OverloadControlInformation pdgOverloadControlInformation;
	MillisecondTimeStamp originatorTimestamp;
	IntegerNumber maximumWaitTime;
	TWANIdentifier wlanLocationInfromation;
	TWANTimeStamp wlanLocationTimestamp;
	FContainer nbifomContainer;
	RemoteUEContext remoteUEContextConnected;
	NodeIdentifier aaaServerIdentifier;
	ExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions;
	ServingPLMNRateControl servingPLMNRateControl;
	Counter moExceptionDataCounter;
	PortNumber ueTCPPort;
	MappedUEUssageType mappedUEUssageType;
	UserLocationInformation userLocationInformationforSGW;
	FQDN sgwUNodeName;
	SecondaryRatUsageDataReport secondaryRatUsageDataReport;
	UPFunctionSelectionIndicationFlags upFunctionSelectionIndicationFlags;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.CREATE_SESSION_REQUEST;
	}

	@Override
	public void applyTLV(TLV2 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case IMSI:
				imsi=(IMSI)tlv;
				break;
			case MSISDN:
				msisdn=(MSISDN)tlv;
				break;
			case MEI:
				mei=(MEI)tlv;
				break;
			case USER_LOCATION_INFORMATION:
				switch(tlv.getInstance())
				{
					case 0:
						userLocationInformation=(UserLocationInformation)tlv;
						break;
					case 1:
						userLocationInformationforSGW=(UserLocationInformation)tlv;
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
						senderFTEIDControlPlane=(FTEID)tlv;
						break;
					case 1:
						pgwAddressForControlPlane=(FTEID)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case APN:
				apn=(APN)tlv;
				break;
			case SELECTION_MODE:
				selectionMode=(SelectionMode)tlv;
				break;
			case PDN_TYPE:
				pdnType=(PDNType)tlv;
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
			case TRUSTED_WLAN_MODE_INDICATION:
				trustedWLANModeIndication=(TrustedWLANModeIndication)tlv;
				break;
			case PROTOCOL_CONFIGURATION_OPTIONS:
				protocolConfigurationOption=(ProtocolConfigurationOption)tlv;
				break;
			case BEARER_CONTEXT:
				switch(tlv.getInstance())
				{
					case 0:
						if(bearerContextToBeCreated==null)
							bearerContextToBeCreated=new ArrayList<CreateSessionRequestBearerContextToBeCreated>();
						
						bearerContextToBeCreated.add((CreateSessionRequestBearerContextToBeCreated)tlv);
						break;
					case 1:
						if(bearerContextToBeRemoved==null)
							bearerContextToBeRemoved=new ArrayList<CreateSessionRequestBearerContextToBeRemoved>();
						
						bearerContextToBeRemoved.add((CreateSessionRequestBearerContextToBeRemoved)tlv);
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case TRACE_INFORMATION:
				traceInformation=(TraceInformation)tlv;
				break;
			case RECOVERY:
				recovery=(Recovery)tlv;
				break;
			case FQ_CSID:
				switch(tlv.getInstance())
				{
					case 0:
						mmeFQCSID=(FQCSID)tlv;
						break;
					case 1:
						sgwFQCSID=(FQCSID)tlv;
						break;
					case 2:
						epdgFQCSID=(FQCSID)tlv;
						break;
					case 3:
						twanFQCSID=(FQCSID)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case UE_TIMEZONE:
				ueTimezone=(UETimezone)tlv;
				break;
			case USER_CSG_INFORMATION:
				userCSGInformation=(CSGInformation)tlv;
				break;
			case CHARGING_CHARACTERISTICS:
				chargingCharacteristic=(ChargingCharacteristic)tlv;
				break;
			case LDN:
				switch(tlv.getInstance())
				{
					case 0:
						sgsnLDN=(LDN)tlv;
						break;
					case 1:
						sgwLDN=(LDN)tlv;
						break;
					case 2:
						epdgLDN=(LDN)tlv;
						break;
					case 3:
						twanLDN=(LDN)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case SIGNALING_PRIORITY_INDICATOR:
				signalingPriorityIndication=(SignalingPriorityIndication)tlv;
				break;
			case IP_ADDRESS:
				switch(tlv.getInstance())
				{
					case 0:
						ueLocalIPAddress=(IPAddress)tlv;
						break;
					case 1:
						nbLocalIPAddress=(IPAddress)tlv;
						break;
					case 2:
						sgsnIdentifier=(IPAddress)tlv;
						break;
					case 3:
						epdgIPAddress=(IPAddress)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case PORT_NUMBER:
				switch(tlv.getInstance())
				{
					case 0:
						ueUDPPort=(PortNumber)tlv;
						break;
					case 1:
						nbUDPPort=(PortNumber)tlv;
						break;
					case 2:
						ueTCPPort=(PortNumber)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case TWAN_IDENTIFIER:
				switch(tlv.getInstance())
				{
					case 0:
						twanIdentifier=(TWANIdentifier)tlv;
						break;
					case 1:
						wlanLocationInfromation=(TWANIdentifier)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case ADDITIONAL_PROTOCOL_CONFIGURATION_OPTIONS:
				additionalProtocolConfigurationOption=(AdditionalProtocolConfigurationOption)tlv;
				break;
			case CN_OPERATOR_SELECTION_ENTITY:
				cnOperatorSelectionEntity=(CNOperatorSelectionEntity)tlv;
				break;
			case PRESENCE_REPORTING_AREA_INFORMATION:
				praInformation=(PRAInformation)tlv;
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
					case 2:
						pdgOverloadControlInformation=(OverloadControlInformation)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case MILLISECONDS_TIMESTAMP:
				originatorTimestamp=(MillisecondTimeStamp)tlv;
				break;
			case INTEGER_NUMBER:
				maximumWaitTime=(IntegerNumber)tlv;
				break;
			case TWAN_IDENTIFIER_TIMESTAMP:
				wlanLocationTimestamp=(TWANTimeStamp)tlv;
				break;
			case FCONTAINER:
				nbifomContainer=(FContainer)tlv;
				break;
			case REMOTE_UE_CONTEXT:
				remoteUEContextConnected=(RemoteUEContext)tlv;
				break;
			case NODE_IDENTIFIER:
				aaaServerIdentifier=(NodeIdentifier)tlv;
				break;
			case EXTENDED_PROTOCOL_CONFIGURATION_OPTIONS:
				extendedProtocolConfigurationOptions=(ExtendedProtocolConfigurationOptions)tlv;
				break;
			case SERVING_PLMN_RATE_CONTROL:
				servingPLMNRateControl=(ServingPLMNRateControl)tlv;
				break;
			case COUNTER:
				moExceptionDataCounter=(Counter)tlv;
				break;
			case MAPPED_UE_USAGE_TYPE:
				mappedUEUssageType=(MappedUEUssageType)tlv;
				break;
			case FQDN:
				sgwUNodeName=(FQDN)tlv;
				break;
			case SECONDARY_RAT_DATA_USAGE_REPORT:
				secondaryRatUsageDataReport=(SecondaryRatUsageDataReport)tlv;
				break;
			case UP_FUNCTION_SELECTION_INDICATION_FLAGS:
				upFunctionSelectionIndicationFlags=(UPFunctionSelectionIndicationFlags)tlv;
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
		if(imsi!=null)
			output.add(imsi);
		
		if(msisdn!=null)
			output.add(msisdn);
		
		if(mei!=null)
			output.add(mei);
		
		if(userLocationInformation!=null)
			output.add(userLocationInformation);
		
		if(servingNetwork!=null)
			output.add(servingNetwork);
		
		if(ratType==null)
			throw new GTPParseException("Rat type not set");
		
		output.add(ratType);
		
		if(indication!=null)
			output.add(indication);
		
		if(senderFTEIDControlPlane==null)
			throw new GTPParseException("sender FTEID CP not set");
		
		output.add(senderFTEIDControlPlane);
		
		if(pgwAddressForControlPlane!=null)
			output.add(pgwAddressForControlPlane);
		
		if(apn==null)
			throw new GTPParseException("APN not set");
		
		output.add(apn);
		
		if(selectionMode!=null)
			output.add(selectionMode);
		
		if(pdnType!=null)
			output.add(pdnType);
		
		if(pdnAddressAllocation!=null)
			output.add(pdnAddressAllocation);
		
		if(maximumAPNRestriction!=null)
			output.add(maximumAPNRestriction);
		
		if(apnAMBR!=null)
			output.add(apnAMBR);
		
		if(epsBearerID!=null)
			output.add(epsBearerID);
			
		if(trustedWLANModeIndication!=null)
			output.add(trustedWLANModeIndication);
		
		if(protocolConfigurationOption!=null)
			output.add(protocolConfigurationOption);
		
		if(bearerContextToBeCreated==null || bearerContextToBeCreated.size()==0)
			throw new GTPParseException("Bearer Context to be created not set");
		
		output.addAll(bearerContextToBeCreated);
		
		if(bearerContextToBeRemoved!=null && bearerContextToBeRemoved.size()>0)
			output.addAll(bearerContextToBeRemoved);
		
		if(traceInformation!=null)
			output.add(traceInformation);
			
		if(recovery!=null)
			output.add(recovery);
		
		if(mmeFQCSID!=null)
			output.add(mmeFQCSID);
		
		if(sgwFQCSID!=null)
			output.add(sgwFQCSID);
		
		if(epdgFQCSID!=null)
			output.add(epdgFQCSID);
		
		if(twanFQCSID!=null)
			output.add(twanFQCSID);
		
		if(ueTimezone!=null)
			output.add(ueTimezone);
			
		if(userCSGInformation!=null)
			output.add(userCSGInformation);
			
		if(chargingCharacteristic!=null)
			output.add(chargingCharacteristic);
			
		if(sgsnLDN!=null)
			output.add(sgsnLDN);
			
		if(sgwLDN!=null)
			output.add(sgwLDN);
			
		if(epdgLDN!=null)
			output.add(epdgLDN);
			
		if(twanLDN!=null)
			output.add(twanLDN);
			
		if(signalingPriorityIndication!=null)
			output.add(signalingPriorityIndication);
		
		if(ueLocalIPAddress!=null)
			output.add(ueLocalIPAddress);
		
		if(ueUDPPort!=null)
			output.add(ueUDPPort);
		
		if(additionalProtocolConfigurationOption!=null)
			output.add(additionalProtocolConfigurationOption);
		
		if(nbLocalIPAddress!=null)
			output.add(nbLocalIPAddress);
		
		if(nbUDPPort!=null)
			output.add(nbUDPPort);
		
		if(sgsnIdentifier!=null)
			output.add(sgsnIdentifier);
		
		if(twanIdentifier!=null)
			output.add(twanIdentifier);
		
		if(epdgIPAddress!=null)
			output.add(epdgIPAddress);
		
		if(cnOperatorSelectionEntity!=null)
			output.add(cnOperatorSelectionEntity);
		
		if(praInformation!=null)
			output.add(praInformation);
		
		if(sgsnOverloadControlInformation!=null)
			output.add(sgsnOverloadControlInformation);
		
		if(sgwOverloadControlInformation!=null)
			output.add(sgwOverloadControlInformation);
		
		if(pdgOverloadControlInformation!=null)
			output.add(pdgOverloadControlInformation);
		
		if(originatorTimestamp!=null)
			output.add(originatorTimestamp);
		
		if(maximumWaitTime!=null)
			output.add(maximumWaitTime);
		
		if(wlanLocationInfromation!=null)
			output.add(wlanLocationInfromation);
		
		if(wlanLocationTimestamp!=null)
			output.add(wlanLocationTimestamp);
		
		if(nbifomContainer!=null)
			output.add(nbifomContainer);
		
		if(remoteUEContextConnected!=null)
			output.add(remoteUEContextConnected);
		
		if(aaaServerIdentifier!=null)
			output.add(aaaServerIdentifier);
		
		if(extendedProtocolConfigurationOptions!=null)
			output.add(extendedProtocolConfigurationOptions);
		
		if(servingPLMNRateControl!=null)
			output.add(servingPLMNRateControl);
		
		if(moExceptionDataCounter!=null)
			output.add(moExceptionDataCounter);
		
		if(ueTCPPort!=null)
			output.add(ueTCPPort);
		
		if(mappedUEUssageType!=null)
			output.add(mappedUEUssageType);
		
		if(userLocationInformationforSGW!=null)
			output.add(userLocationInformationforSGW);
		
		if(sgwUNodeName!=null)
			output.add(sgwUNodeName);
		
		if(secondaryRatUsageDataReport!=null)
			output.add(secondaryRatUsageDataReport);
		
		if(upFunctionSelectionIndicationFlags!=null)
			output.add(upFunctionSelectionIndicationFlags);
		
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
		imsi.setInstance(0);
		this.imsi=imsi;
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
	public MEI getMEI() 
	{
		return mei;
	}

	@Override
	public void setMEI(MEI mei) 
	{
		mei.setInstance(0);
		this.mei=mei;
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
	public APN getAPN() 
	{
		return this.apn;
	}

	@Override
	public void setAPN(APN apn) 
	{
		apn.setInstance(0);
		this.apn=apn;
	}

	@Override
	public SelectionMode getSelectionMode() 
	{
		return this.selectionMode;
	}

	@Override
	public void setSelectionMode(SelectionMode selectionMode) 
	{
		selectionMode.setInstance(0);
		this.selectionMode=selectionMode;
	}

	@Override
	public PDNType getPDNType() 
	{
		return pdnType;
	}

	@Override
	public void setPDNType(PDNType pdnType) 
	{
		pdnType.setInstance(0);
		this.pdnType=pdnType;
	}

	@Override
	public PDNAddressAllocation getPDNAddressAllocation() 
	{
		return this.pdnAddressAllocation;
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
		return maximumAPNRestriction;
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
		return apnAMBR;
	}

	@Override
	public void setAPNAMBR(AMBR ambr) 
	{
		ambr.setInstance(0);
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
	public TrustedWLANModeIndication getTrustedWLANModeIndication() 
	{
		return trustedWLANModeIndication;
	}

	@Override
	public void setTrustedWLANModeIndication(TrustedWLANModeIndication twmi) 
	{
		twmi.setInstance(0);
		this.trustedWLANModeIndication=twmi;
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
	public List<CreateSessionRequestBearerContextToBeCreated> getBearerContextToBeCreated() 
	{
		return bearerContextToBeCreated;
	}

	@Override
	public void setBearerContextToBeCreated(List<CreateSessionRequestBearerContextToBeCreated> bearerContext) 
	{
		if(bearerContext!=null)
			for(CreateSessionRequestBearerContextToBeCreated curr:bearerContext)
				curr.setInstance(0);
		
		this.bearerContextToBeCreated=bearerContext;
	}

	@Override
	public List<CreateSessionRequestBearerContextToBeRemoved> getBearerContextToBeRemoved() 
	{
		return bearerContextToBeRemoved;
	}

	@Override
	public void setBearerContextToBeRemoved(List<CreateSessionRequestBearerContextToBeRemoved> bearerContext) 
	{
		if(bearerContext!=null)
			for(CreateSessionRequestBearerContextToBeRemoved curr:bearerContext)
				curr.setInstance(1);
		
		this.bearerContextToBeRemoved=bearerContext;
	}

	@Override
	public TraceInformation getTraceInformation() 
	{
		return traceInformation;
	}

	@Override
	public void setTraceInformation(TraceInformation traceInformation) 
	{
		traceInformation.setInstance(0);
		this.traceInformation=traceInformation;
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
	public FQCSID getMMEFQCSID() 
	{
		return mmeFQCSID;
	}

	@Override
	public void setMMEFQCSID(FQCSID fqcsid) 
	{
		fqcsid.setInstance(0);
		this.mmeFQCSID=fqcsid;
	}

	@Override
	public FQCSID getSGWFQCSID() 
	{
		return sgwFQCSID;
	}

	@Override
	public void setSGWFQCSID(FQCSID fqcsid) 
	{
		fqcsid.setInstance(1);
		this.sgwFQCSID=fqcsid;
	}

	@Override
	public FQCSID getEPDGFQCSID() 
	{
		return epdgFQCSID;
	}

	@Override
	public void setEPDGFQCSID(FQCSID fqcsid) 
	{
		fqcsid.setInstance(2);
		this.epdgFQCSID=fqcsid;
	}

	@Override
	public FQCSID getTWANFQCSID() 
	{
		return twanFQCSID;
	}

	@Override
	public void setTWANFQCSID(FQCSID fqcsid) 
	{
		fqcsid.setInstance(3);
		this.twanFQCSID=fqcsid;
	}

	@Override
	public UETimezone getUETimezone() 
	{
		return ueTimezone;
	}

	@Override
	public void setUETimezone(UETimezone ueTimezone) 
	{
		ueTimezone.setInstance(0);
		this.ueTimezone=ueTimezone;
	}

	@Override
	public CSGInformation getUserCSGInformation() 
	{
		return userCSGInformation;
	}

	@Override
	public void setUserCSGInformation(CSGInformation csgInformation) 
	{
		csgInformation.setInstance(0);
		this.userCSGInformation=csgInformation;
	}

	@Override
	public ChargingCharacteristic getChargingCharacteristic() 
	{
		return chargingCharacteristic;
	}

	@Override
	public void setChargingCharacteristic(ChargingCharacteristic chargingCharacteristic) 
	{
		chargingCharacteristic.setInstance(0);
		this.chargingCharacteristic=chargingCharacteristic;
	}

	@Override
	public LDN getSGSNLDN() 
	{
		return sgsnLDN;
	}

	@Override
	public void setSGSNLDN(LDN ldn) 
	{
		ldn.setInstance(0);
		this.sgsnLDN=ldn;
	}

	@Override
	public LDN getSGWLDN() 
	{
		return sgwLDN;
	}

	@Override
	public void setSGWLDN(LDN ldn) 
	{
		ldn.setInstance(1);
		this.sgwLDN=ldn;
	}

	@Override
	public LDN getEPDGLDN() 
	{
		return epdgLDN;
	}

	@Override
	public void setEPDGLDN(LDN ldn) 
	{
		ldn.setInstance(2);
		this.epdgLDN=ldn;
	}

	@Override
	public LDN getTWANLDN() 
	{
		return twanLDN;
	}

	@Override
	public void setTWANLDN(LDN ldn) 
	{
		ldn.setInstance(3);
		this.twanLDN=ldn;
	}

	@Override
	public SignalingPriorityIndication getSignalingPriorityIndication() 
	{
		return signalingPriorityIndication;
	}

	@Override
	public void setSignalingPriorityIndication(SignalingPriorityIndication signalingPriorityIndication) 
	{
		signalingPriorityIndication.setInstance(0);
		this.signalingPriorityIndication=signalingPriorityIndication;
	}

	@Override
	public IPAddress getUELocalIPAddress() 
	{
		return ueLocalIPAddress;
	}

	@Override
	public void setUELocalIPAddress(IPAddress ueLocalIPAddress) 
	{
		ueLocalIPAddress.setInstance(0);
		this.ueLocalIPAddress=ueLocalIPAddress;
	}

	@Override
	public PortNumber getUEUDPPort() 
	{
		return ueUDPPort;
	}

	@Override
	public void setUEUDPPort(PortNumber ueUDPPort) 
	{
		ueUDPPort.setInstance(0);
		this.ueUDPPort=ueUDPPort;
	}

	@Override
	public AdditionalProtocolConfigurationOption getAdditionalProtocolConfigurationOption() 
	{
		return additionalProtocolConfigurationOption;
	}

	@Override
	public void setAdditionalProtocolConfigurationOption(AdditionalProtocolConfigurationOption additionalProtocolConfigurationOption) 
	{
		additionalProtocolConfigurationOption.setInstance(0);
		this.additionalProtocolConfigurationOption=additionalProtocolConfigurationOption;
	}

	@Override
	public IPAddress getNBLocalIPAddress() 
	{
		return nbLocalIPAddress;
	}

	@Override
	public void setNBLocalIPAddress(IPAddress nbLocalIPAddress) 
	{
		nbLocalIPAddress.setInstance(1);
		this.nbLocalIPAddress=nbLocalIPAddress;
	}

	@Override
	public PortNumber getNBUDPPort() 
	{
		return this.nbUDPPort;
	}

	@Override
	public void setNBUDPPort(PortNumber nbUDPPort) 
	{
		nbUDPPort.setInstance(1);
		this.nbUDPPort=nbUDPPort;
	}

	@Override
	public IPAddress getSGSNIdentifier() 
	{
		return sgsnIdentifier;
	}

	@Override
	public void setSGSNIdentifier(IPAddress sgsnIdentifier) 
	{
		sgsnIdentifier.setInstance(2);
		this.sgsnIdentifier=sgsnIdentifier;
	}

	@Override
	public TWANIdentifier getTWANIdentifier() 
	{
		return this.twanIdentifier;
	}

	@Override
	public void setTWANIdentifier(TWANIdentifier twanIdentifier) 
	{
		twanIdentifier.setInstance(0);
		this.twanIdentifier=twanIdentifier;
	}

	@Override
	public IPAddress getEPDGIPAddress() 
	{
		return epdgIPAddress;
	}

	@Override
	public void setEPDGIPAddress(IPAddress ePDGIPAddress) 
	{
		ePDGIPAddress.setInstance(3);
		this.epdgIPAddress=ePDGIPAddress;
	}

	@Override
	public CNOperatorSelectionEntity getCNOperatorSelectionEntity() 
	{
		return cnOperatorSelectionEntity;
	}

	@Override
	public void setCNOperatorSelectionEntity(CNOperatorSelectionEntity cnOperatorSelectionEntity) 
	{
		cnOperatorSelectionEntity.setInstance(0);
		this.cnOperatorSelectionEntity=cnOperatorSelectionEntity;
	}

	@Override
	public PRAInformation getPRAInformation() 
	{
		return praInformation;
	}

	@Override
	public void setPRAInformation(PRAInformation praInformation) 
	{
		praInformation.setInstance(0);
		this.praInformation=praInformation;
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
	public OverloadControlInformation getPDGOverloadControlInformation() 
	{
		return pdgOverloadControlInformation;
	}

	@Override
	public void setPDGOverloadControlInformation(OverloadControlInformation overloadControlInformation) 
	{
		overloadControlInformation.setInstance(2);
		this.pdgOverloadControlInformation=overloadControlInformation;
	}

	@Override
	public MillisecondTimeStamp getOriginatorTimestamp() 
	{
		return originatorTimestamp;
	}

	@Override
	public void setOriginatorTimestamp(MillisecondTimeStamp originatorTimestamp) 
	{
		originatorTimestamp.setInstance(0);
		this.originatorTimestamp=originatorTimestamp;
	}

	@Override
	public IntegerNumber getMaximumWaitTime() 
	{
		return maximumWaitTime;
	}

	@Override
	public void setMaximumWaitTime(IntegerNumber maximumWaitTime) 
	{
		maximumWaitTime.setInstance(0);
		this.maximumWaitTime=maximumWaitTime;
	}

	@Override
	public TWANIdentifier getWLANLocationInfromation() 
	{
		return wlanLocationInfromation;
	}

	@Override
	public void setWLANLocationInfromation(TWANIdentifier wlanLocationInfromation) 
	{
		wlanLocationInfromation.setInstance(1);
		this.wlanLocationInfromation=wlanLocationInfromation;
	}

	@Override
	public TWANTimeStamp getWLANLocationTimestamp() 
	{
		return wlanLocationTimestamp;
	}

	@Override
	public void setWLANLocationTimestamp(TWANTimeStamp wlanLocationTimestamp) 
	{
		wlanLocationTimestamp.setInstance(0);
		this.wlanLocationTimestamp=wlanLocationTimestamp;
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
	public RemoteUEContext getRemoteUEContextConnected() 
	{
		return remoteUEContextConnected;
	}

	@Override
	public void setRemoteUEContextConnected(RemoteUEContext remoteUEContext) 
	{
		remoteUEContext.setInstance(0);
		this.remoteUEContextConnected=remoteUEContext;
	}

	@Override
	public NodeIdentifier getAAAServerIdentifier() 
	{
		return aaaServerIdentifier;
	}

	@Override
	public void setAAAServerIdentifier(NodeIdentifier nodeIdentifier) 
	{
		nodeIdentifier.setInstance(0);
		this.aaaServerIdentifier=nodeIdentifier;
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
	public ServingPLMNRateControl getServingPLMNRateControl() 
	{
		return servingPLMNRateControl;
	}

	@Override
	public void setServingPLMNRateControl(ServingPLMNRateControl servingPLMNRateControl) 
	{
		servingPLMNRateControl.setInstance(0);
		this.servingPLMNRateControl=servingPLMNRateControl;
	}

	@Override
	public Counter getMOExceptionDataCounter() 
	{
		return moExceptionDataCounter;
	}

	@Override
	public void setMOExceptionDataCounter(Counter counter) 
	{
		counter.setInstance(0);
		this.moExceptionDataCounter=counter;
	}

	@Override
	public PortNumber getUETCPPort() 
	{
		return ueTCPPort;
	}

	@Override
	public void setUETCPPort(PortNumber ueTCPPort) 
	{
		ueTCPPort.setInstance(2);
		this.ueTCPPort=ueTCPPort;
	}

	@Override
	public MappedUEUssageType getMappedUEUssageType() 
	{
		return mappedUEUssageType;
	}

	@Override
	public void setMappedUEUssageType(MappedUEUssageType mappedUEUssageType) 
	{
		mappedUEUssageType.setInstance(0);
		this.mappedUEUssageType=mappedUEUssageType;
	}

	@Override
	public UserLocationInformation getUserLocationInformationforSGW() 
	{
		return userLocationInformationforSGW;
	}

	@Override
	public void setUserLocationInformationforSGW(UserLocationInformation uli) 
	{
		uli.setInstance(1);
		this.userLocationInformationforSGW=uli;
	}

	@Override
	public FQDN getSGWUNodeName() 
	{
		return sgwUNodeName;
	}

	@Override
	public void setSGWUNodeName(FQDN sgwUNodeName) 
	{
		sgwUNodeName.setInstance(0);
		this.sgwUNodeName=sgwUNodeName;
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
	public UPFunctionSelectionIndicationFlags getUPFunctionSelectionIndicationFlags() 
	{
		return upFunctionSelectionIndicationFlags;
	}

	@Override
	public void setUPFunctionSelectionIndicationFlags(UPFunctionSelectionIndicationFlags upFunctionSelectionIndicationFlags) 
	{
		upFunctionSelectionIndicationFlags.setInstance(0);
		this.upFunctionSelectionIndicationFlags=upFunctionSelectionIndicationFlags;
	}
}