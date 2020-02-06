package com.mobius.software.telco.protocols.gtp.api.messages;
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

import com.mobius.software.telco.protocols.gtp.api.headers.APN;
import com.mobius.software.telco.protocols.gtp.api.headers.APNAMBR;
import com.mobius.software.telco.protocols.gtp.api.headers.APNRestriction;
import com.mobius.software.telco.protocols.gtp.api.headers.AdditionalTraceInfo;
import com.mobius.software.telco.protocols.gtp.api.headers.CNOperatorSelectionEntity;
import com.mobius.software.telco.protocols.gtp.api.headers.CSGInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.CamelChargingInformationContainer;
import com.mobius.software.telco.protocols.gtp.api.headers.ChargingCharacteristic;
import com.mobius.software.telco.protocols.gtp.api.headers.CommonFlags;
import com.mobius.software.telco.protocols.gtp.api.headers.CorrelationID;
import com.mobius.software.telco.protocols.gtp.api.headers.EndUserAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.EvolvedAllocationRetentionPriority1;
import com.mobius.software.telco.protocols.gtp.api.headers.ExtendedCommonFlags;
import com.mobius.software.telco.protocols.gtp.api.headers.GSNAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.IMEISV;
import com.mobius.software.telco.protocols.gtp.api.headers.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.MSISDN;
import com.mobius.software.telco.protocols.gtp.api.headers.MSTimezone;
import com.mobius.software.telco.protocols.gtp.api.headers.NSAPI;
import com.mobius.software.telco.protocols.gtp.api.headers.OMCIdentity;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.ProtocolConfigurationOption;
import com.mobius.software.telco.protocols.gtp.api.headers.QosProfile;
import com.mobius.software.telco.protocols.gtp.api.headers.RatType;
import com.mobius.software.telco.protocols.gtp.api.headers.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.RoutingAreaIdentity;
import com.mobius.software.telco.protocols.gtp.api.headers.SelectionMode;
import com.mobius.software.telco.protocols.gtp.api.headers.SignalingPriorityIndication;
import com.mobius.software.telco.protocols.gtp.api.headers.TFT;
import com.mobius.software.telco.protocols.gtp.api.headers.TraceReference;
import com.mobius.software.telco.protocols.gtp.api.headers.TraceType;
import com.mobius.software.telco.protocols.gtp.api.headers.TriggerID;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifier1;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifierControlPane;
import com.mobius.software.telco.protocols.gtp.api.headers.UserLocationInformation;

public interface CreatePdpContextRequest extends GTPMessage 
{
	IMSI getIMSI();
	
	void setIMSI(IMSI imsi);
	
	RoutingAreaIdentity getRAI();
	
	void setRAI(RoutingAreaIdentity rai);
	
	Recovery getRecovery();
	
	void setRecovery(Recovery recovery);
	
	SelectionMode getSelectionMode();
	
	void setSelectionMode(SelectionMode selectionMode);
	
	TunnerEndpointIdentifier1 getTEI();
	
	void setTEI(TunnerEndpointIdentifier1 tei);
	
	TunnerEndpointIdentifierControlPane getControlPaneTEI();
	
	void setControlPageTEI(TunnerEndpointIdentifierControlPane tei);
	
	NSAPI getNSAPI();
	
	void setNSAPI(NSAPI nsapi);
	
	NSAPI getLinkedNSAPI();
	
	void setLinkedNSAPI(NSAPI nsapi);
	
	ChargingCharacteristic getChargingCharacteristics();
	
	void setChargingCharacteristics(ChargingCharacteristic chargingCharacteristics);
	
	TraceReference getTraceReference();
	
	void setTraceReference(TraceReference reference);
	
	TraceType getTraceType();
	
	void setTraceType(TraceType traceType);
	
	EndUserAddress getEndUserAddress();
	
	void setEndUserAddress(EndUserAddress address);
	
	APN getAPN();
	
	void setAPN(APN apn);
	
	ProtocolConfigurationOption getProtocolConfigurationOption();
	
	void setProtocolConfigurationOption(ProtocolConfigurationOption option);
	
	GSNAddress getSGSNAddressForSignaling();
	
	void setSGSNAddressForSignaling(GSNAddress address);
	
	GSNAddress getSGSNAddressForTraffic();
	
	void setSGSNAddressForTraffic(GSNAddress address);
	
	MSISDN getMSISDN();
	
	void setMSISDN(MSISDN msisdn);
	
	QosProfile getQosProfile();
	
	void setQosProfile(QosProfile profile);
	
	TFT getTFT();
	
	void setTFT(TFT tft);
	
	TriggerID getTriggerID();
	
	void setTriggerID(TriggerID triggerID);
	
	OMCIdentity getOMCIdentity();
	
	void setOMCIdentity(OMCIdentity omcIdentity);
	
	CommonFlags getCommonFlags();
	
	void setCommonFlags(CommonFlags commonFlags);
	
	APNRestriction getAPNRestriction();
	
	void setAPNRestriction(APNRestriction restriction);
	
	RatType getRatType();
	
	void setRatType(RatType ratType);
	
	UserLocationInformation getUserLocationInformation();
	
	void setUserLocationInformation(UserLocationInformation locationInformation);
	
	MSTimezone getMSTimezone();
	
	void setMSTimezone(MSTimezone timezone);
	
	IMEISV getIMEISV();
	
	void setIMEISV(IMEISV imeiSV);
	
	CamelChargingInformationContainer getCamelChargingInformationContainer();
	
	void setCamelChargingInformationContainer(CamelChargingInformationContainer container);
	
	AdditionalTraceInfo getAdditionalTraceInfo();
	
	void setAdditionalTraceInfo(AdditionalTraceInfo additionalTraceInfo);
	
	CorrelationID getCorrelationID();
	
	void setCorrelationID(CorrelationID correlationID);
	
	EvolvedAllocationRetentionPriority1 getEvolvedAllocationRetentionPriority1();
	
	void setEvolvedAllocationRetentionPriority1(EvolvedAllocationRetentionPriority1 priority);
	
	ExtendedCommonFlags getExtendedCommonFlags();
	
	void setExtendedCommonFlags(ExtendedCommonFlags flags);
	
	CSGInformation getCSGInformation();
	
	void setCSGInformation(CSGInformation information);
	
	APNAMBR getAPNAMBR();
	
	void setAPNAMBR(APNAMBR apnambr);
	
	SignalingPriorityIndication getSignalingPriorityIndication();
	
	void setSignalingPriorityIndication(SignalingPriorityIndication indication);
	
	CNOperatorSelectionEntity getCNOperatorSelectionEntity();
	
	void setCNOperatorSelectionEntity(CNOperatorSelectionEntity entity);
	
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);
}