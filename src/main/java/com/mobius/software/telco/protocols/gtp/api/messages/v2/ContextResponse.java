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

import com.mobius.software.telco.protocols.gtp.api.headers.v2.CSGInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Counter;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FQDN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IPAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Indication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IntegerNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.LDN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MDTConfiguration;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MMContext;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MonitoringEventInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PDNConnection;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RFSPIndex;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RatType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SCEFPDNConnection;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ServingPLMNRateControl;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TraceInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UETimezone;

public interface ContextResponse extends GTP2Message
{
	Cause getCause();
	
	void setCause(Cause cause);
	
	IMSI getIMSI();
	
	void setIMSI(IMSI imsi);
	
	MMContext getMMContext();
	
	void setMMContext(MMContext mmContext);
	
	PDNConnection getPDNConnection();
	
	void setPDNConection(PDNConnection pdnConnection);
	
	FTEID getSenderFTEIDControlPlane();
	
	void setSenderFTEIDControlPlane(FTEID senderFTEID);
	
	FTEID getSGWAddressForControlPlane();
	
	void setSGWAddressForControlPlane(FTEID sgwFTEID);
	
	FQDN getSGWNodeName();
	
	void setSGWNodeName(FQDN sgwNodeName);
		
	Indication getIndication();
	
	void setIndication(Indication indication);		
	
	TraceInformation getTraceInformation();
	
	void setTraceInformation(TraceInformation traceInformation);		
	
	IPAddress getS101Address();
	
	void setS101Address(IPAddress ipAddress);
	
	IPAddress getS102Address();
	
	void setS102Address(IPAddress ipAddress);
	
	RFSPIndex getSubscribedRFSPIndex();
	
	void setSubscribedRFSPIndex(RFSPIndex subscribedIndex);
	
	RFSPIndex getRFSPIndexInUse();
	
	void setRFSPIndexInUse(RFSPIndex rfspIndexInUse);
	
	UETimezone getUETimezone();
	
	void setUETimezone(UETimezone ueTimezone);
	
	LDN getSGSNLDN();
	
	void setSGSNLDN(LDN ldn);
	
	MDTConfiguration getMDTConfiguration();
	
	void setMDTConfiguration(MDTConfiguration mdtConfiguration);
	
	FQDN getSGSNNodeName();
	
	void setSGSNodeName(FQDN sgsnNodeName);	
	
	FQDN getMMENodeName();
	
	void setMMENodeName(FQDN mmeNodeName);	
	
	CSGInformation getUserCSGInformation();
	
	void setUserCSGInformation(CSGInformation csgInformation);
		
	MonitoringEventInformation getMonitoringEventInformation();
	
	void setMonitoringEventInformation(MonitoringEventInformation monitoringEventInformation);	
	
	IntegerNumber getUEUsageType();
	
	void setUEUsageType(IntegerNumber usageType);
	
	SCEFPDNConnection getSCEFPDNConnection();
	
	void setSCEFPDNConnection(SCEFPDNConnection connection);
	
	RatType getRatType();
	
	void setRatType(RatType ratType);
		
	ServingPLMNRateControl getServingPLMNControlRate();
	
	void setServingPLMNControlRate(ServingPLMNRateControl servingPLMNControlRate);	
	
	Counter getMOExceptionDataCounter();
	
	void setMOExceptionDataCounter(Counter counter);
	
	IntegerNumber getRemainingRunningServiceGAP();
	
	void setRemainingRunningServiceGAP(IntegerNumber counter);
	
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);
}