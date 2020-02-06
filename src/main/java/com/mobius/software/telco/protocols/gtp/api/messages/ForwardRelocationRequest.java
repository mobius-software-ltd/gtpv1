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
import com.mobius.software.telco.protocols.gtp.api.headers.AdditionalFlagsForSRVCC;
import com.mobius.software.telco.protocols.gtp.api.headers.AdditionalMMContextForSRVCC;
import com.mobius.software.telco.protocols.gtp.api.headers.BSSContainer;
import com.mobius.software.telco.protocols.gtp.api.headers.BSSGPCause;
import com.mobius.software.telco.protocols.gtp.api.headers.CMSISDN;
import com.mobius.software.telco.protocols.gtp.api.headers.CSGID;
import com.mobius.software.telco.protocols.gtp.api.headers.CSGMembershipIndication;
import com.mobius.software.telco.protocols.gtp.api.headers.CellIdentification;
import com.mobius.software.telco.protocols.gtp.api.headers.ChargingCharacteristic;
import com.mobius.software.telco.protocols.gtp.api.headers.DirectTunnelFlags;
import com.mobius.software.telco.protocols.gtp.api.headers.ENodeBID;
import com.mobius.software.telco.protocols.gtp.api.headers.EvolvedAllocationRetentionPriority2;
import com.mobius.software.telco.protocols.gtp.api.headers.ExtendedCommonFlags;
import com.mobius.software.telco.protocols.gtp.api.headers.ExtendedRanapCause;
import com.mobius.software.telco.protocols.gtp.api.headers.FQDN;
import com.mobius.software.telco.protocols.gtp.api.headers.GSNAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.HigherThen16MbpsBitrate;
import com.mobius.software.telco.protocols.gtp.api.headers.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.MBMSUEContext;
import com.mobius.software.telco.protocols.gtp.api.headers.MMContext;
import com.mobius.software.telco.protocols.gtp.api.headers.PDPContext;
import com.mobius.software.telco.protocols.gtp.api.headers.PDPContextPrioritization;
import com.mobius.software.telco.protocols.gtp.api.headers.PacketFlowID;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.RFSPIndex;
import com.mobius.software.telco.protocols.gtp.api.headers.RanapCause;
import com.mobius.software.telco.protocols.gtp.api.headers.ReliableRatHandoverIndication;
import com.mobius.software.telco.protocols.gtp.api.headers.STNSR;
import com.mobius.software.telco.protocols.gtp.api.headers.SelectedPLMNID;
import com.mobius.software.telco.protocols.gtp.api.headers.SelectionModeWithNSAPI;
import com.mobius.software.telco.protocols.gtp.api.headers.SignalingPriorityIndicationWithNSAPI;
import com.mobius.software.telco.protocols.gtp.api.headers.TargetIdentification;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifierControlPane;
import com.mobius.software.telco.protocols.gtp.api.headers.UEAMBR;
import com.mobius.software.telco.protocols.gtp.api.headers.UENetworkCapability;
import com.mobius.software.telco.protocols.gtp.api.headers.UTRANTransparentContent;
import com.mobius.software.telco.protocols.gtp.api.headers.XIDParameters;

public interface ForwardRelocationRequest extends GTPMessage 
{
	IMSI getIMSI();
	
	void setIMSI(IMSI imsi);
	
	TunnerEndpointIdentifierControlPane getControlPaneTEI();
	
	void setControlPageTEI(TunnerEndpointIdentifierControlPane tei);
	
	RanapCause getRanapCause();
	
	void setRanapCause(RanapCause ranapCause);
	
	PacketFlowID getPacketFlowID();
	
	void setPacketFlowID(PacketFlowID packetFlowID);
	
	ChargingCharacteristic getChargingCharacteristic();
	
	void setChargingCharacteristic(ChargingCharacteristic chargingCharacteristic);
	
	MMContext getMMContext();
	
	void setMMContext(MMContext mmContext);
	
	PDPContext getPDPContext();
	
	void setPDPContext(PDPContext pdpContext);
	
	GSNAddress getSGSNAddressForSignaling();
	
	void setSGSNAddressForSignaling(GSNAddress address);
	
	TargetIdentification getTargetIdentification();
	
	void setTargetIdentification(TargetIdentification targetIdentification);
	
	UTRANTransparentContent getUTRANTransparentContent();
	
	void setUTRANTransparentContent(UTRANTransparentContent context);
	
	PDPContextPrioritization getPDPContextPrioritization();
	
	void setPDPContextPrioritization(PDPContextPrioritization pdpContextPrioritization);
	
	MBMSUEContext getMBMSUEContext();
	
	void setMBMSUEContext(MBMSUEContext ueContext);
	
	SelectedPLMNID getSelectedPLMNID();
	
	void setSelectedPLMNID(SelectedPLMNID selectedPLMNID);
	
	BSSContainer getBSSContainer();
	
	void setBSSContainer(BSSContainer container);
	
	CellIdentification getCellIdentification();
	
	void setCellIdentification(CellIdentification cellIdentification);
	
	BSSGPCause getBSSGPCause();
	
	void setBSSGPCause(BSSGPCause bssGPCause);
	
	XIDParameters getPSHandoverXIDParameters();
	
	void setPSHandoverXIDParameters(XIDParameters parameters);
	
	DirectTunnelFlags getDirectTunnelFlags();
	
	void setDirectTunnelFlags(DirectTunnelFlags directTunnelFlags);
	
	ReliableRatHandoverIndication getReliableRatHandoverIndication();
	
	void setReliableRatHandoverIndication(ReliableRatHandoverIndication reliableRatHandoverIndication);
	
	RFSPIndex getSubscribedRFSPIndex();
	
	void setSubscribedRFSPIndex(RFSPIndex index);
	
	RFSPIndex getRFSPIndexInUse();
	
	void setRFSPIndexInUse(RFSPIndex index);
	
	FQDN getCollocatedGGSN_PGWFQDN();
	
	void setCollocatedGGSN_PGWFQDN(FQDN fqdn);
	
	EvolvedAllocationRetentionPriority2 getEvolvedAllocationRetentionPriority2();
	
	void setEvolvedAllocationRetentionPriority2(EvolvedAllocationRetentionPriority2 priority);
	
	ExtendedCommonFlags getExtendedCommonFlags();
	
	void setExtendedCommonFlags(ExtendedCommonFlags extendedCommonFlags);
	
	CSGID getCSGID();
	
	void setCSGID(CSGID csgID);
	
	CSGMembershipIndication getCSGMembershipIndication();
	
	void setCSGMembershipIndication(CSGMembershipIndication csgMembershipIndication);
	
	UENetworkCapability getUENetworkCapability();
	
	void setUENetworkCapability(UENetworkCapability ueNetworkCapability);
	
	UEAMBR getUEAMBR();
	
	void setUEAMBR(UEAMBR ueAMBR);
	
	APNAMBRWithNSAPI getAPNAMBRWithNSAPI();
	
	void setAPNAMBRWithNSAPI(APNAMBRWithNSAPI apnAMBRWithNSAPI);
	
	SignalingPriorityIndicationWithNSAPI getSignalingPriorityIndicationWithNSAPI();
	
	void setSignalingPriorityIndicationWithNSAPI(SignalingPriorityIndicationWithNSAPI indication);
	
	HigherThen16MbpsBitrate getHigherThen16MbpsBitrate();
	
	void setHigherThen16MbpsBitrate(HigherThen16MbpsBitrate bitrate);
	
	AdditionalMMContextForSRVCC getAdditionalMMContextForSRVCC();
	
	void setAdditionalMMContextForSRVCC(AdditionalMMContextForSRVCC context);
	
	AdditionalFlagsForSRVCC getAdditionalFlagsForSRVCC();
	
	void setAdditionalFlagsForSRVCC(AdditionalFlagsForSRVCC additionalFlagsForSRVCC);
	
	STNSR getSTNSR();
	
	void setSTNSR(STNSR stnSR);
	
	CMSISDN getCMSISDN();
	
	void setCMSISDN(CMSISDN cmisdn);
	
	ExtendedRanapCause getExtendedRanapCause();
	
	void setExtendedRanapCause(ExtendedRanapCause extendedRanapCause);
	
	ENodeBID getENodeBID();
	
	void setENodeBID(ENodeBID eNodeBID);
	
	SelectionModeWithNSAPI getSelectionModeWithNSAPI();
	
	void setSelectionModeWithNSAPI(SelectionModeWithNSAPI selectionModeWithNSAPI);
	
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);
}