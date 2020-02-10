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

import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.ModifyBearerResponseBearerContextToBeModified;
import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.ModifyBearerResponseBearerContextToBeRemoved;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.APNRestriction;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CSGInformationReportingAction;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ChangeReportingAction;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ChargingID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPSBearerID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FQCSID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FQDN;
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

public interface ModifyBearerResponse extends GTP2Message
{
	Cause getCause();
	
	void setCause(Cause cause);
	
	MSISDN getMSISDN();
	
	void setMSISDN(MSISDN msisdn);

	EPSBearerID getEPSBearerID();	

	void setEPSBearerID(EPSBearerID epsBearerID); 
	
	APNRestriction getMaximumAPNRestriction();
	
	void setMaximumAPNRestriction(APNRestriction apnRestriction);
	
	ProtocolConfigurationOption getProtocolConfigurationOption();
	
	void setProtocolConfigurationOption(ProtocolConfigurationOption pco);
	
	List<ModifyBearerResponseBearerContextToBeModified> getBearerContextModified();
	
	void setBearerContextModified(List<ModifyBearerResponseBearerContextToBeModified> bearerContext);
	
	List<ModifyBearerResponseBearerContextToBeRemoved> getBearerContextMarkedForRemoved();
	
	void setBearerContextMarkedForRemoved(List<ModifyBearerResponseBearerContextToBeRemoved> bearerContext);
	
	ChangeReportingAction getChangeReportingAction();
	
	void setChangeReportingAction(ChangeReportingAction changeReportingAction);
	
	CSGInformationReportingAction getCSGInformationReportingAction();
	
	void setCSGInformationReportingAction(CSGInformationReportingAction action);
	
	HENBInformationReporting getHENBInformationReporting();
	
	void setHENBInformationReporting(HENBInformationReporting reporting);
	
	FQDN getChargingGatewayName();
	
	void setChargingGatewayName(FQDN name);
	
	IPAddress getChargingGatewayAddress();
	
	void setChargingGatewayAddress(IPAddress address);
	
	FQCSID getPGWFQCSID();
	
	void setPGWFQCSID(FQCSID fqcsid);
	
	FQCSID getSGWFQCSID();
	
	void setSGWFQCSID(FQCSID fqcsid);
	
	Recovery getRecovery();	

	void setRecovery(Recovery recovery); 
	
	LDN getSGWLDN();	

	void setSGWLDN(LDN ldn);	

	LDN getPGWLDN();

	void setPGWLDN(LDN ldn);
	
	Indication getIndication();
	
	void setIndication(Indication indication);
		
	PresenceAreaReportingAction getPresenceAreaReportingAction();
	
	void setPresenceAreaReportingAction(PresenceAreaReportingAction action);
		
	LoadControlInformation getPGWNodeLoadControlInformation();
	
	void setPGWNodeLoadControlInformation(LoadControlInformation loadInformation);
		
	LoadControlInformation getPGWAPNLoadControlInformation();
	
	void setPGWAPNLoadControlInformation(LoadControlInformation loadInformation);
		
	LoadControlInformation getSGWNodeLoadControlInformation();
	
	void setSGWNodeLoadControlInformation(LoadControlInformation loadInformation);
		
	OverloadControlInformation getPGWOverloadControlInformation();
	
	void setPGWOverloadControlInformation(OverloadControlInformation overloadInformation);
		
	OverloadControlInformation getSGWOverloadControlInformation();
	
	void setSGWOverloadControlInformation(OverloadControlInformation overloadInformation);
		
	ChargingID getPDNConnectionChargingID();
	
	void setPDNConnectionChargingID(ChargingID chargingID);
	
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);
}