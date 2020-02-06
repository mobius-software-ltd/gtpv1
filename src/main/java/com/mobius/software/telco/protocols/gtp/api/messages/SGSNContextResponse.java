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

import com.mobius.software.telco.protocols.gtp.api.headers.APNAMBRWithNSAPI;
import com.mobius.software.telco.protocols.gtp.api.headers.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.ChargingCharacteristic;
import com.mobius.software.telco.protocols.gtp.api.headers.EvolvedAllocationRetentionPriority2;
import com.mobius.software.telco.protocols.gtp.api.headers.ExtendedCommonFlags;
import com.mobius.software.telco.protocols.gtp.api.headers.FQDN;
import com.mobius.software.telco.protocols.gtp.api.headers.GSNAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.HigherThen16MbpsBitrate;
import com.mobius.software.telco.protocols.gtp.api.headers.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.LocalHomeNetworkIDWithNSAPI;
import com.mobius.software.telco.protocols.gtp.api.headers.MBMSUEContext;
import com.mobius.software.telco.protocols.gtp.api.headers.MMContext;
import com.mobius.software.telco.protocols.gtp.api.headers.PDPContext;
import com.mobius.software.telco.protocols.gtp.api.headers.PDPContextPrioritization;
import com.mobius.software.telco.protocols.gtp.api.headers.PacketFlowID;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.RABContext;
import com.mobius.software.telco.protocols.gtp.api.headers.RFSPIndex;
import com.mobius.software.telco.protocols.gtp.api.headers.RadioPriority;
import com.mobius.software.telco.protocols.gtp.api.headers.RadioPriorityLCS;
import com.mobius.software.telco.protocols.gtp.api.headers.RadioPrioritySMS;
import com.mobius.software.telco.protocols.gtp.api.headers.SelectionModeWithNSAPI;
import com.mobius.software.telco.protocols.gtp.api.headers.SignalingPriorityIndicationWithNSAPI;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifierControlPane;
import com.mobius.software.telco.protocols.gtp.api.headers.UEAMBR;
import com.mobius.software.telco.protocols.gtp.api.headers.UENetworkCapability;

public interface SGSNContextResponse extends GTPMessage 
{
	Cause getCause();
	
	void setCause(Cause cause);
	
	IMSI getIMSI();
	
	void setIMSI(IMSI imsi);
	
	TunnerEndpointIdentifierControlPane getControlPaneTEI();
	
	void setControlPageTEI(TunnerEndpointIdentifierControlPane tei);
	
	RABContext getRABContext();
	
	void setRABContext(RABContext rabContext);
	
	RadioPrioritySMS getRadioPrioritySMS();
	
	void setRadioPrioritySMS(RadioPrioritySMS radioPrioritySMS);
	
	RadioPriority getRadioPriority();
	
	void setRadioPriority(RadioPriority radioPriority);
	
	PacketFlowID getPacketFlowID();
	
	void setPacketFlowID(PacketFlowID flowID);
	
	ChargingCharacteristic getChargingCharacteristics();
	
	void setChargingCharacteristics(ChargingCharacteristic characteristic);
	
	RadioPriorityLCS getRadioPriorityLCS();
	
	void setRadioPriorityLCS(RadioPriorityLCS radioPriorityLCS);
	
	MMContext getMMContext();
	
	void setMMContext(MMContext context);
	
	PDPContext getPDPContext();
	
	void setPDPContext(PDPContext context);
	
	GSNAddress getSGSNAddressForSignaling();
	
	void setSGSNAddressForSignaling(GSNAddress address);
	
	PDPContextPrioritization getPDPContextPrioritization();
	
	void setPDPContextPrioritization(PDPContextPrioritization prioritization);
	
	MBMSUEContext getMBMSUEContext();
	
	void setMBMSUEContext(MBMSUEContext context);
	
	RFSPIndex getSubscribedRFSPIndex();
	
	void setSubscribedRFSPIndex(RFSPIndex index);
	
	RFSPIndex getRFSPIndexInUse();
	
	void setRFSPIndexInUse(RFSPIndex index);
	
	FQDN getCollocatedGGSN_PGWFQDN();
	
	void setCollocatedGGSN_PGWFQDN(FQDN value);
	
	EvolvedAllocationRetentionPriority2 getEvolvedAllocationRetentionPriority2();
	
	void setEvolvedAllocationRetentionPriority2(EvolvedAllocationRetentionPriority2 priority);
	
	ExtendedCommonFlags getExtendedCommonFlags();
	
	void setExtendedCommonFlags(ExtendedCommonFlags flags);
	
	UENetworkCapability getUENetworkCapability();
	
	void setUENetworkCapability(UENetworkCapability capability);
	
	UEAMBR getUEAMBR();
	
	void setUEAMBR(UEAMBR ueAMBR);
	
	APNAMBRWithNSAPI getAPNAMBRWithNSAPI();
	
	void setAPNAMBRWithNSAPI(APNAMBRWithNSAPI apnAMBRWithNSAPI);
	
	SignalingPriorityIndicationWithNSAPI getSignalingPriorityIndicationWithNSAPI();
	
	void setSignalingPriorityIndicationWithNSAPI(SignalingPriorityIndicationWithNSAPI priority);
	
	HigherThen16MbpsBitrate getHigherThen16MbpsBitrate();
	
	void setHigherThen16MbpsBitrate(HigherThen16MbpsBitrate value);
	
	SelectionModeWithNSAPI getSelectionModeWithNSAPI();
	
	void setSelectionModeWithNSAPI(SelectionModeWithNSAPI selectionMode);
	
	LocalHomeNetworkIDWithNSAPI getLocalHomeNetworkIDWithNSAPI();
	
	void setLocalHomeNetworkIDWithNSAPI(LocalHomeNetworkIDWithNSAPI localHomeNetwork);
	
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);		
}
