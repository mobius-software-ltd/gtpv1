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

import com.mobius.software.telco.protocols.gtp.api.headers.v2.AdditionalFlagsForSRVCC;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.AdditionalMMContextForSRVCC;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CSGID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CSGInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CSGMembershipIndication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FCause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FContainer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FQDN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IPAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Indication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IntegerNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.LDN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MDTConfiguration;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MMContext;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MSISDN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MonitoringEventInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PDNConnection;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PLMNID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PortNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RFSPIndex;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SCEFPDNConnection;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.STNSR;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ServingNetwork;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ServingPLMNRateControl;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SourceIdentification;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TargetIdentification;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TraceInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UETimezone;

public interface ForwardRelocationRequest extends GTP2Message
{
	IMSI getIMSI();
	
	void setIMSI(IMSI imsi);
	
	FTEID getSenderFTEIDControlPlane();
	
	void setSenderFTEIDControlPlane(FTEID fteid);
	
	PDNConnection getPDNConnection();
	
	void setPDNConection(PDNConnection connection);
	
	FTEID getSGWAddressForControlPlane();
	
	void setSGWAddressForControlPlane(FTEID fteid);
	
	FQDN getSGWNodeName();
	
	void setSGWNodeName(FQDN sgwNodeName);
		
	MMContext getMMContext();
	
	void setMMContext(MMContext context);
	
	Indication getIndication();
	
	void setIndication(Indication indication);
	
	FContainer getEUTRANTransparentContainer();
	
	void setEUTRANTransparentContainer(FContainer transparentContainer);
		
	FContainer getUTRANTransparentContainer();
	
	void setUTRANTransparentContainer(FContainer transparentContainer);
		
	FContainer getBSSContainer();
	
	void setBSSContainer(FContainer bssContainer);
		
	TargetIdentification getTargetIdentification();
	
	void setTargetIdentification(TargetIdentification identification);
	
	IPAddress getS101Address();
	
	void setS101Address(IPAddress s101Address);
		
	IPAddress getS102Address();
	
	void setS102Address(IPAddress s102Address);
	
	FCause getS1APCause();
	
	void setS1APCause(FCause cause);
	
	FCause getRANAPCause();
	
	void setRANAPCause(FCause cause);
	
	FCause getBSSGPCause();
	
	void setBSSGPCause(FCause cause);
	
	SourceIdentification getSourceIdentification();
	
	void setSourceIdentification(SourceIdentification identification);
	
	PLMNID getSelectedPLMNID();
	
	void setSelectedPLMNID(PLMNID plmnID);
	
	Recovery getRecovery();
	
	void setRecovery(Recovery recovery);
	
	TraceInformation getTraceInformation();
	
	void setTraceInformation(TraceInformation information);
	
	RFSPIndex getSubscribedRFSPIndex();
	
	void setSubscribedRFSPIndex(RFSPIndex index);
	
	RFSPIndex getRFSPIndexInUse();
	
	void setRFSPIndexInUse(RFSPIndex index);
	
	CSGID getCSGID();
	
	void setCSGID(CSGID csgID);
	
	CSGMembershipIndication getCSGMembershipIndication();
	
	void setCSGMembershipIndication(CSGMembershipIndication indication);
	
	UETimezone getUETimezone();
	
	void setUETimezone(UETimezone ueTimezone);
	
	ServingNetwork getServingNetwork();
	
	void setServingNetwork(ServingNetwork servingNetwork);
	
	LDN getSGSNLDN();
	
	void setSGSNLDN(LDN ldn);
	
	AdditionalMMContextForSRVCC getAdditionalMMContextForSRVCC();
	
	void setAdditionalMMContextForSRVCC(AdditionalMMContextForSRVCC mmContext);
	
	AdditionalFlagsForSRVCC getAdditionalFlagsForSRVCC();
	
	void setAdditionalFlagsForSRVCC(AdditionalFlagsForSRVCC flags);
	
	STNSR getSTNSR();
	
	void setSTNSR(STNSR stnSR);
	
	MSISDN getCMSISDN();
	
	void setCMSISDN(MSISDN cmsisdn);
	
	MDTConfiguration getMDTConfiguration();
	
	void setMDTConfiguration(MDTConfiguration configuration);
	
	FQDN getSGSNNodeName();
	
	void setSGSNodeName(FQDN sgsnNodeName);
		
	FQDN getMMENodeName();
	
	void setMMENodeName(FQDN mmeNodeName);	
	
	CSGInformation getUserCSGInformation();
	
	void setUserCSGInformation(CSGInformation csgInformation);
		
	MonitoringEventInformation getMonitoringEventInformation();
	
	void setMonitoringEventInformation(MonitoringEventInformation eventInformation);
	
	IntegerNumber getUEUsageType();
	
	void UEUsageType(IntegerNumber usageType);
	
	SCEFPDNConnection getSCEFPDNConnection();
	
	void setSCEFPDNConnection(SCEFPDNConnection connection);
	
	MSISDN getMSISDN();
	
	void setMSISDN(MSISDN msisdn);
	
	PortNumber getSourceUDPPortNumber();
	
	void setSourceUDPPortNumber(PortNumber portNumber);
	
	ServingPLMNRateControl getServingPLMNControlRate();
	
	void setServingPLMNControlRate(ServingPLMNRateControl plmnControlRate);
	
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);
}