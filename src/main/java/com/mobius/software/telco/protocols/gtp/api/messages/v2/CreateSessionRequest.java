package com.mobius.software.telco.protocols.gtp.api.messages.v2;
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
import java.util.List;

import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.CreateSessionRequestBearerContextToBeCreated;
import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.CreateSessionRequestBearerContextToBeRemoved;
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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TWANIdentifier;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TWANTimeStamp;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TraceInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TrustedWLANModeIndication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UETimezone;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UPFunctionSelectionIndicationFlags;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UserLocationInformation;

public interface CreateSessionRequest extends GTP2Message
{
	IMSI getIMSI();
	
	void setIMSI(IMSI imsi);
	
	MSISDN getMSISDN();
	
	void setMSISDN(MSISDN msisdn);
	
	MEI getMEI();
	
	void setMEI(MEI mei);
	
	UserLocationInformation getUserLocationInformation();
	
	void setUserLocationInformation(UserLocationInformation uli);
	
	ServingNetwork getServingNetwork();
	
	void setServingNetwork(ServingNetwork servingNetwork);
	
	RatType getRatType();
	
	void setRatType(RatType ratType);
	
	Indication getIndication();
	
	void setIndication(Indication indication);
	
	FTEID getSenderFTEIDControlPlane();
	
	void setSenderFTEIDControlPlane(FTEID fteid);
	
	FTEID getPGWAddressForControlPlane();
	
	void setPGWAddressForControlPlane(FTEID fteid);
	
	APN getAPN();
	
	void setAPN(APN apn);
	
	SelectionMode getSelectionMode();
	
	void setSelectionMode(SelectionMode selectionMode);
	
	PDNType getPDNType();
	
	void setPDNType(PDNType pdnType);
	
	PDNAddressAllocation getPDNAddressAllocation();
	
	void setPDNAddressAllocation(PDNAddressAllocation pdnAddressAllocation);
	
	APNRestriction getMaximumAPNRestriction();
	
	void setMaximumAPNRestriction(APNRestriction apnRestriction);
	
	AMBR getAPNAMBR();
	
	void setAPNAMBR(AMBR ambr);
	
	EPSBearerID getEPSBearerID();
	
	void setEPSBearerID(EPSBearerID epsBearerID);
	
	TrustedWLANModeIndication getTrustedWLANModeIndication();
	
	void setTrustedWLANModeIndication(TrustedWLANModeIndication twmi);
	
	ProtocolConfigurationOption getProtocolConfigurationOption();
	
	void setProtocolConfigurationOption(ProtocolConfigurationOption pco);
	
	List<CreateSessionRequestBearerContextToBeCreated> getBearerContextToBeCreated();
	
	void setBearerContextToBeCreated(List<CreateSessionRequestBearerContextToBeCreated> bearerContext);
	
	List<CreateSessionRequestBearerContextToBeRemoved> getBearerContextToBeRemoved();
	
	void setBearerContextToBeRemoved(List<CreateSessionRequestBearerContextToBeRemoved> bearerContext);
	
	TraceInformation getTraceInformation();
	
	void setTraceInformation(TraceInformation traceInformation);
	
	Recovery getRecovery();
	
	void setRecovery(Recovery recovery);
	
	FQCSID getMMEFQCSID();
	
	void setMMEFQCSID(FQCSID fqcsid);
	
	FQCSID getSGWFQCSID();
	
	void setSGWFQCSID(FQCSID fqcsid);
	
	FQCSID getEPDGFQCSID();
	
	void setEPDGFQCSID(FQCSID fqcsid);
	
	FQCSID getTWANFQCSID();
	
	void setTWANFQCSID(FQCSID fqcsid);
	
	UETimezone getUETimezone();
	
	void setUETimezone(UETimezone ueTimezone);
	
	CSGInformation getUserCSGInformation();
	
	void setUserCSGInformation(CSGInformation csgInformation);
	
	ChargingCharacteristic getChargingCharacteristic();
	
	void setChargingCharacteristic(ChargingCharacteristic chargingCharacteristic);
	
	LDN getSGSNLDN();
	
	void setSGSNLDN(LDN ldn);
	
	LDN getSGWLDN();
	
	void setSGWLDN(LDN ldn);
	
	LDN getEPDGLDN();
	
	void setEPDGLDN(LDN ldn);
	
	LDN getTWANLDN();
	
	void setTWANLDN(LDN ldn);
	
	SignalingPriorityIndication getSignalingPriorityIndication();
	
	void setSignalingPriorityIndication(SignalingPriorityIndication signalingPriorityIndication);
		
	IPAddress getUELocalIPAddress();
	
	void setUELocalIPAddress(IPAddress ueLocalIPAddress);
		
	PortNumber getUEUDPPort();
	
	void setUEUDPPort(PortNumber ueUDPPort);
		
	AdditionalProtocolConfigurationOption getAdditionalProtocolConfigurationOption();
	
	void setAdditionalProtocolConfigurationOption(AdditionalProtocolConfigurationOption additionalProtocolConfigurationOption);
		
	IPAddress getNBLocalIPAddress();
	
	void setNBLocalIPAddress(IPAddress nbLocalIPAddress);
		
	PortNumber getNBUDPPort();
	
	void setNBUDPPort(PortNumber nbUDPPort);
		
	IPAddress getSGSNIdentifier();
	
	void setSGSNIdentifier(IPAddress sgsnIdentifier);
		
	TWANIdentifier getTWANIdentifier();
	
	void setTWANIdentifier(TWANIdentifier twanIdentifier);
		
	IPAddress getEPDGIPAddress();
	
	void setEPDGIPAddress(IPAddress ePDGIPAddress);
		
	CNOperatorSelectionEntity getCNOperatorSelectionEntity();
	
	void setCNOperatorSelectionEntity(CNOperatorSelectionEntity cnOperatorSelectionEntity);
		
	PRAInformation getPRAInformation();
	
	void setPRAInformation(PRAInformation praInformation);
		
	OverloadControlInformation getSGSNOverloadControlInformation();
	
	void setSGSNOverloadControlInformation(OverloadControlInformation overloadControlInformation);
		
	OverloadControlInformation getSGWOverloadControlInformation();
	
	void setSGWOverloadControlInformation(OverloadControlInformation overloadControlInformation);
		
	OverloadControlInformation getPDGOverloadControlInformation();
	
	void setPDGOverloadControlInformation(OverloadControlInformation overloadControlInformation);
		
	MillisecondTimeStamp getOriginatorTimestamp();
	
	void setOriginatorTimestamp(MillisecondTimeStamp originatorTimestamp);
		
	IntegerNumber getMaximumWaitTime();
	
	void setMaximumWaitTime(IntegerNumber maximumWaitTime);
		
	TWANIdentifier getWLANLocationInfromation();
	
	void setWLANLocationInfromation(TWANIdentifier wlanLocationInfromation);
		
	TWANTimeStamp getWLANLocationTimestamp();
	
	void setWLANLocationTimestamp(TWANTimeStamp wlanLocationTimestamp);
		
	FContainer getNBIFOMContainer();
	
	void setNBIFOMContainer(FContainer nbifomContainer);
		
	RemoteUEContext getRemoteUEContextConnected();
	
	void setRemoteUEContextConnected(RemoteUEContext remoteUEContext);
	
	NodeIdentifier getAAAServerIdentifier();
	
	void setAAAServerIdentifier(NodeIdentifier nodeIdentifier);
	
	ExtendedProtocolConfigurationOptions getExtendedProtocolConfigurationOptions();
	
	void setExtendedProtocolConfigurationOptions(ExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions);
	
	ServingPLMNRateControl getServingPLMNRateControl();
	
	void setServingPLMNRateControl(ServingPLMNRateControl servingPLMNRateControl);
	
	Counter getMOExceptionDataCounter();
	
	void setMOExceptionDataCounter(Counter counter);
	
	PortNumber getUETCPPort();
	
	void setUETCPPort(PortNumber ueTCPPort);
		
	MappedUEUssageType getMappedUEUssageType();
	
	void setMappedUEUssageType(MappedUEUssageType mappedUEUssageType);
		
	UserLocationInformation getUserLocationInformationforSGW();
	
	void setUserLocationInformationforSGW(UserLocationInformation uli);
		
	FQDN getSGWUNodeName();
	
	void setSGWUNodeName(FQDN sgwUNodeName);
		
	SecondaryRatUsageDataReport getSecondaryRatUsageDataReport();
	
	void setSecondaryRatUsageDataReport(SecondaryRatUsageDataReport secondaryRatUsageDataReport);
		
	UPFunctionSelectionIndicationFlags getUPFunctionSelectionIndicationFlags();
	
	void setUPFunctionSelectionIndicationFlags(UPFunctionSelectionIndicationFlags upFunctionSelectionIndicationFlags);
		
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);
}