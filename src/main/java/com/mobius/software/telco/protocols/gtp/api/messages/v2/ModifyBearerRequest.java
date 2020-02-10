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

import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.ModifyBearerRequestBearerContextToBeModified;
import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.ModifyBearerRequestBearerContextToBeRemoved;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.AMBR;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CNOperatorSelectionEntity;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CSGInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Counter;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.DelayValue;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FQCSID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IPAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Indication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.LDN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MEI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.OverloadControlInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PRAInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PortNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RatType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SecondaryRatUsageDataReport;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ServingNetwork;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ServingPLMNRateControl;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TWANIdentifier;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TWANTimeStamp;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UETimezone;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UserLocationInformation;

public interface ModifyBearerRequest extends GTP2Message
{
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
	
	AMBR getAPNAMBR();
	
	void setAPNAMBR(AMBR ambr);
	
	DelayValue getDelayValue();
	
	void setDelayValue(DelayValue delayValue);
	
	List<ModifyBearerRequestBearerContextToBeModified> getBearerContextToBeModified();
	
	void setBearerContextToBeModified(List<ModifyBearerRequestBearerContextToBeModified> bearerContext);
	
	List<ModifyBearerRequestBearerContextToBeRemoved> getBearerContextToBeRemoved();
	
	void setBearerContextToBeRemoved(List<ModifyBearerRequestBearerContextToBeRemoved> bearerContext);
	
	Recovery getRecovery();
	
	void setRecovery(Recovery recovery);
	
	UETimezone getUETimezone();
	
	void setUETimezone(UETimezone ueTimezone);
	
	FQCSID getMMEFQCSID();
	
	void setMMEFQCSID(FQCSID fqcsid);
	
	FQCSID getSGWFQCSID();
	
	void setSGWFQCSID(FQCSID fqcsid);
	
	CSGInformation getUserCSGInformation();
	
	void setUserCSGInformation(CSGInformation csgInformation);
	
	IPAddress getUELocalIPAddress();
	
	void setUELocalIPAddress(IPAddress ueLocalIPAddress);
		
	PortNumber getUEUDPPort();
	
	void setUEUDPPort(PortNumber ueUDPPort);
		
	LDN getSGSNLDN();
	
	void setSGSNLDN(LDN ldn);
	
	LDN getSGWLDN();
	
	void setSGWLDN(LDN ldn);
	
	IPAddress getNBLocalIPAddress();
	
	void setNBLocalIPAddress(IPAddress nbLocalIPAddress);
		
	PortNumber getNBUDPPort();
	
	void setNBUDPPort(PortNumber nbUDPPort);
		
	IPAddress getSGSNIdentifier();
	
	void setSGSNIdentifier(IPAddress sgsnIdentifier);
		
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
		
	ServingPLMNRateControl getServingPLMNRateControl();
	
	void setServingPLMNRateControl(ServingPLMNRateControl servingPLMNRateControl);
	
	Counter getMOExceptionDataCounter();
	
	void setMOExceptionDataCounter(Counter counter);
	
	IMSI getIMSI();
	
	void setIMSI(IMSI imsi);
	
	UserLocationInformation getUserLocationInformationforSGW();
	
	void setUserLocationInformationforSGW(UserLocationInformation uli);
		
	TWANIdentifier getWLANLocationInfromation();
	
	void setWLANLocationInfromation(TWANIdentifier wlanLocationInfromation);
		
	TWANTimeStamp getWLANLocationTimestamp();
	
	void setWLANLocationTimestamp(TWANTimeStamp wlanLocationTimestamp);
		
	SecondaryRatUsageDataReport getSecondaryRatUsageDataReport();
	
	void setSecondaryRatUsageDataReport(SecondaryRatUsageDataReport secondaryRatUsageDataReport);
		
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);
}