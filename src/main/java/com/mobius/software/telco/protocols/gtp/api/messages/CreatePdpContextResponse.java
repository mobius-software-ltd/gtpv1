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
import com.mobius.software.telco.protocols.gtp.api.headers.NSAPI;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.ProtocolConfigurationOption;
import com.mobius.software.telco.protocols.gtp.api.headers.QosProfile;
import com.mobius.software.telco.protocols.gtp.api.headers.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.ReorderingRequired;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifier1;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifierControlPane;

public interface CreatePdpContextResponse extends GTPMessage 
{
	Cause getCause();
	
	void setCause(Cause cause);
	
	ReorderingRequired getReorderingRequired();
	
	void setReorderingRequired(ReorderingRequired reorderingRequired);
	
	TunnerEndpointIdentifier1 getTEI();
	
	void setTEI(TunnerEndpointIdentifier1 tei);
	
	TunnerEndpointIdentifierControlPane getControlPaneTEI();
	
	void setControlPageTEI(TunnerEndpointIdentifierControlPane tei);
	
	NSAPI getNSAPI();
	
	void setNSAPI(NSAPI nsapi);
	
	ChargingID getChargingID();
	
	void setChargingID(ChargingID chargingID);
	
	EndUserAddress getEndUserAddress();
	
	void setEndUserAddress(EndUserAddress address);
	
	ProtocolConfigurationOption getProtocolConfigurationOption();
	
	void setProtocolConfigurationOption(ProtocolConfigurationOption option);
	
	GSNAddress getGGSNAddressForSignaling();
	
	void setGGSNAddressForSignaling(GSNAddress address);
	
	GSNAddress getGGSNAddressForTraffic();
	
	void setGGSNAddressForTraffic(GSNAddress address);
	
	GSNAddress getAlternateGGSNAddressForSignaling();
	
	void setAlternateGGSNAddressForSignaling(GSNAddress address);
	
	GSNAddress getAlternateGGSNAddressForTraffic();
	
	void setAlternateGGSNAddressForTraffic(GSNAddress address);
	
	QosProfile getQosProfile();
	
	void setQosProfile(QosProfile profile);
	
	ChargingGatewayAddress getChargingGatewayAddress();
	
	void setChargingGatewayAddress(ChargingGatewayAddress address);
	
	ChargingGatewayAddress getAlternateChargingGatewayAddress();
	
	void setAlternateChargingGatewayAddress(ChargingGatewayAddress address);
	
	CommonFlags getCommonFlags();
	
	void setCommonFlags(CommonFlags commonFlags);
	
	APNRestriction getAPNRestriction();
	
	void setAPNRestriction(APNRestriction restriction);
	
	ChangeReportAction getMSInfoChangeReportAction();
	
	void setMSInfoChangeReportAction(ChangeReportAction action);
	
	BearerMode getBearerControlMode();
	
	void setBearerControlMode(BearerMode mode);
	
	EvolvedAllocationRetentionPriority1 getEvolvedAllocationRetentionPriority1();
	
	void setEvolvedAllocationRetentionPriority1(EvolvedAllocationRetentionPriority1 priority);
	
	CSGInformationReportingAction getCSGInformationReportingAction();
	
	void setCSGInformationReportingAction(CSGInformationReportingAction action);
	
	APNAMBR getAPNAMBR();
	
	void setAPNAMBR(APNAMBR apnambr);
	
	GGSNBackoffTime getGGSNBackoffTime();
	
	void setGGSNBackoffTime(GGSNBackoffTime time);
	
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);
	
	Recovery getRecovery();
	
	void setRecovery(Recovery value);
}
