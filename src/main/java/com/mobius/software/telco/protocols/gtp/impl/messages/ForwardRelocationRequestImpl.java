package com.mobius.software.telco.protocols.gtp.impl.messages;
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

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
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
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
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
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.headers.TargetIdentification;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifierControlPane;
import com.mobius.software.telco.protocols.gtp.api.headers.UEAMBR;
import com.mobius.software.telco.protocols.gtp.api.headers.UENetworkCapability;
import com.mobius.software.telco.protocols.gtp.api.headers.UTRANTransparentContent;
import com.mobius.software.telco.protocols.gtp.api.headers.XIDParameters;
import com.mobius.software.telco.protocols.gtp.api.messages.ForwardRelocationRequest;

public class ForwardRelocationRequestImpl extends AbstractGTPMessage implements ForwardRelocationRequest
{
	private IMSI imsi;
	private TunnerEndpointIdentifierControlPane controlTEI;
	private RanapCause ranapCause;
	private PacketFlowID packetFlowID;
	private ChargingCharacteristic chargingCharacteristic;
	private MMContext mmContext;
	private PDPContext pdpContext;
	private GSNAddress sgsnAddressForSignaling;
	private TargetIdentification targetIndication;
	private UTRANTransparentContent utranTransparentContent;
	private PDPContextPrioritization pdpContextPrioritization;
	private MBMSUEContext mbmsUEContext;
	private SelectedPLMNID selectedPLMNID;
	private BSSContainer bssContainer;
	private CellIdentification cellIdentification;
	private BSSGPCause bssGPCause;
	private XIDParameters xidParameters;
	private DirectTunnelFlags directTunnelFlags;
	private ReliableRatHandoverIndication indication;
	private RFSPIndex subscribedFRSPIndex;
	private RFSPIndex rfspIndexInUse;
	private FQDN collocatedGGSN_PGWFQDN;
	private EvolvedAllocationRetentionPriority2 priority;
	private ExtendedCommonFlags extendedCommonFlags;
	private CSGID csgID;
	private CSGMembershipIndication csgMembershipIndication;
	private UENetworkCapability ueNetworkCapability;
	private UEAMBR ueAMBR;
	private APNAMBRWithNSAPI apnAMBRWithNSAPI;
	private SignalingPriorityIndicationWithNSAPI signalingPriorityIndicationWithNSAPI;
	private HigherThen16MbpsBitrate bitrate;
	private AdditionalMMContextForSRVCC additionalMMContextForSRVCC;
	private AdditionalFlagsForSRVCC additionalFlagsForSRVCC;
	private STNSR stnSR;
	private CMSISDN cMSISDN;
	private ExtendedRanapCause extendedRanapCause;
	private ENodeBID eNodeBID;
	private SelectionModeWithNSAPI selectionModeWithNSAPI;
	
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public MessageType getMessageType() 
	{
		return MessageType.FORWARD_RELOCATION_REQUEST;
	}

	@Override
	public void applyTLV(TLV1 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case IMSI:
				imsi=(IMSI)tlv;
				break;
			case TEICP:
				controlTEI=(TunnerEndpointIdentifierControlPane)tlv;
				break;
			case RANAP_CAUSE:
				ranapCause=(RanapCause)tlv;
				break;
			case PACKET_FLOW_ID:
				packetFlowID=(PacketFlowID)tlv;
				break;
			case CHARGING_CHARACTERISTICS:
				chargingCharacteristic=(ChargingCharacteristic)tlv;
				break;
			case MM_CONTEXT:
				mmContext=(MMContext)tlv;
				break;
			case PDP_CONTEXT:
				pdpContext=(PDPContext)tlv;
				break;
			case GSN_ADDRESS:
				sgsnAddressForSignaling=(GSNAddress)tlv;
				break;
			case TARGET_IDENTIFICATION:
				targetIndication=(TargetIdentification)tlv;
				break;
			case UTRAN_TRANSPARENT_CONTAINER:
				utranTransparentContent=(UTRANTransparentContent)tlv;
				break;
			case PDP_CONTEXT_PRIORITIZATION:
				pdpContextPrioritization=(PDPContextPrioritization)tlv;
				break;
			case MBMS_UE_CONTEXT:
				mbmsUEContext=(MBMSUEContext)tlv;
				break;
			case SELECTED_PLMN_ID:
				selectedPLMNID=(SelectedPLMNID)tlv;
				break;
			case BSS_CONTAINER:
				bssContainer=(BSSContainer)tlv;
				break;
			case CELL_IDENTIFICATION:
				cellIdentification=(CellIdentification)tlv;
				break;
			case BSSGP_CAUSE:
				bssGPCause=(BSSGPCause)tlv;
				break;
			case PS_HANDOVER_XID_PARAMETERS:
				xidParameters=(XIDParameters)tlv;
				break;
			case DIRECT_TUNNEL_FLAGS:
				directTunnelFlags=(DirectTunnelFlags)tlv;
				break;
			case RELIABLE_INTER_RAT_HANDOVER_INFO:
				indication=(ReliableRatHandoverIndication)tlv;
				break;
			case RFSP_INDEX:
				if(subscribedFRSPIndex==null)
					subscribedFRSPIndex=(RFSPIndex)tlv;
				else if(rfspIndexInUse==null)
					rfspIndexInUse=(RFSPIndex)tlv;
				else
					throw new GTPParseException("Unknown TLV received,type:" + tlv.getElementType());						
				break;
			case FQDN:
				collocatedGGSN_PGWFQDN=(FQDN)tlv;
				break;
			case EVOLVED_ALLOCATION_RETENTION_PRIORITY_2:
				priority=(EvolvedAllocationRetentionPriority2)tlv;
				break;
			case EXTENDED_COMMON_FLAGS:
				extendedCommonFlags=(ExtendedCommonFlags)tlv;
				break;
			case CSG_ID:
				csgID=(CSGID)tlv;
				break;
			case CSG_MEMBERSHIP_INDICATION:
				csgMembershipIndication=(CSGMembershipIndication)tlv;
				break;
			case UE_NETWORK_CAPABILITY:
				ueNetworkCapability=(UENetworkCapability)tlv;
				break;
			case UE_AMBR:
				ueAMBR=(UEAMBR)tlv;
				break;
			case APN_AMBR_WITH_NSAPI:
				apnAMBRWithNSAPI=(APNAMBRWithNSAPI)tlv;
				break;
			case SIGNALING_PRIORITY_INDICATION_WITH_NSAPI:
				signalingPriorityIndicationWithNSAPI=(SignalingPriorityIndicationWithNSAPI)tlv;
				break;
			case HIGHER_BITRATES_THEN_16_MBPS_FLAG:
				bitrate=(HigherThen16MbpsBitrate)tlv;
				break;
			case ADDITIONAL_MM_CONTEXT_FOR_SRVCC:
				additionalMMContextForSRVCC=(AdditionalMMContextForSRVCC)tlv;
				break;
			case ADDITIONAL_FLAGS_FOR_SRVCC:
				additionalFlagsForSRVCC=(AdditionalFlagsForSRVCC)tlv;
				break;
			case STN_SR:
				stnSR=(STNSR)tlv;
				break;
			case C_MSISDN:
				cMSISDN=(CMSISDN)tlv;
				break;
			case EXTENDED_RANAP_CAUSE:
				extendedRanapCause=(ExtendedRanapCause)tlv;
				break;
			case ENODEB_ID:
				eNodeBID=(ENodeBID)tlv;
				break;
			case SELECTION_MODE_WITH_NSAPI:
				selectionModeWithNSAPI=(SelectionModeWithNSAPI)tlv;
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
	public List<TLV1> getTLVs() throws GTPParseException 
	{
		ArrayList<TLV1> output=new ArrayList<TLV1>();
		if(imsi!=null)
			output.add(imsi);
		
		if(controlTEI==null)
			throw new GTPParseException("Control TEI is missing");
		
		output.add(controlTEI);
	
		if(ranapCause==null)
			throw new GTPParseException("Ranap Cause is missing");
		
		output.add(ranapCause);
	
		if(packetFlowID!=null)
			output.add(packetFlowID);
		
		if(chargingCharacteristic!=null)
			output.add(chargingCharacteristic);
	
		if(mmContext==null)
			throw new GTPParseException("MM Context is missing");
		
		output.add(mmContext);
	
		if(pdpContext!=null)
			output.add(pdpContext);
	
		if(sgsnAddressForSignaling==null)
			throw new GTPParseException("sgsn address for signaling is missing");
		
		output.add(sgsnAddressForSignaling);
	
		if(targetIndication==null)
			throw new GTPParseException("Target indication is missing");
		
		output.add(targetIndication);
	
		if(utranTransparentContent==null)
			throw new GTPParseException("UTRAN Transparent content is missing");
		
		output.add(utranTransparentContent);
	
		if(pdpContextPrioritization!=null)
			output.add(pdpContextPrioritization);
	
		if(mbmsUEContext!=null)
			output.add(mbmsUEContext);
	
		if(selectedPLMNID!=null)
			output.add(selectedPLMNID);
	
		if(bssContainer!=null)
			output.add(bssContainer);
	
		if(cellIdentification!=null)
			output.add(cellIdentification);
	
		if(bssGPCause!=null)
			output.add(bssGPCause);
	
		if(xidParameters!=null)
			output.add(xidParameters);
	
		if(directTunnelFlags!=null)
			output.add(directTunnelFlags);
	
		if(indication!=null)
			output.add(directTunnelFlags);
	
		if(subscribedFRSPIndex!=null)
			output.add(subscribedFRSPIndex);
	
		if(rfspIndexInUse!=null)
			output.add(rfspIndexInUse);
	
		if(collocatedGGSN_PGWFQDN!=null)
			output.add(collocatedGGSN_PGWFQDN);
		
		if(priority!=null)
			output.add(priority);
		
		if(extendedCommonFlags!=null)
			output.add(extendedCommonFlags);
		
		if(csgID!=null)
			output.add(csgID);
		
		if(csgMembershipIndication!=null)
			output.add(csgMembershipIndication);
		
		if(ueNetworkCapability!=null)
			output.add(ueNetworkCapability);
		
		if(ueAMBR!=null)
			output.add(ueAMBR);	
		
		if(apnAMBRWithNSAPI!=null)
			output.add(apnAMBRWithNSAPI);	
		
		if(signalingPriorityIndicationWithNSAPI!=null)
			output.add(signalingPriorityIndicationWithNSAPI);	
		
		if(bitrate!=null)
			output.add(bitrate);	
		
		if(additionalMMContextForSRVCC!=null)
			output.add(additionalMMContextForSRVCC);	
		
		if(additionalFlagsForSRVCC!=null)
			output.add(additionalFlagsForSRVCC);	
		
		if(stnSR!=null)
			output.add(stnSR);	
		
		if(cMSISDN!=null)
			output.add(cMSISDN);	
		
		if(extendedRanapCause!=null)
			output.add(extendedRanapCause);	
		
		if(eNodeBID!=null)
			output.add(eNodeBID);	
		
		if(selectionModeWithNSAPI!=null)
			output.add(selectionModeWithNSAPI);	
		
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
		this.imsi=imsi;
	}

	@Override
	public TunnerEndpointIdentifierControlPane getControlPaneTEI() 
	{
		return this.controlTEI;
	}

	@Override
	public void setControlPageTEI(TunnerEndpointIdentifierControlPane tei) 
	{
		this.controlTEI=tei;
	}

	@Override
	public RanapCause getRanapCause() 
	{
		return this.ranapCause;
	}

	@Override
	public void setRanapCause(RanapCause ranapCause) 
	{
		this.ranapCause=ranapCause;
	}

	@Override
	public PacketFlowID getPacketFlowID() 
	{
		return this.packetFlowID;
	}

	@Override
	public void setPacketFlowID(PacketFlowID packetFlowID) 
	{
		this.packetFlowID=packetFlowID;
	}

	@Override
	public ChargingCharacteristic getChargingCharacteristic() 
	{
		return this.chargingCharacteristic;
	}

	@Override
	public void setChargingCharacteristic(ChargingCharacteristic chargingCharacteristic) 
	{
		this.chargingCharacteristic=chargingCharacteristic;
	}

	@Override
	public MMContext getMMContext() 
	{
		return this.mmContext;
	}

	@Override
	public void setMMContext(MMContext mmContext) 
	{
		this.mmContext=mmContext;
	}

	@Override
	public PDPContext getPDPContext() 
	{
		return this.pdpContext;
	}

	@Override
	public void setPDPContext(PDPContext pdpContext) 
	{
		this.pdpContext=pdpContext;
	}

	@Override
	public GSNAddress getSGSNAddressForSignaling() 
	{
		return sgsnAddressForSignaling;
	}

	@Override
	public void setSGSNAddressForSignaling(GSNAddress address) 
	{
		this.sgsnAddressForSignaling=address;		
	}

	@Override
	public TargetIdentification getTargetIdentification() 
	{
		return this.targetIndication;
	}

	@Override
	public void setTargetIdentification(TargetIdentification targetIdentification) 
	{
		this.targetIndication=targetIdentification;
	}

	@Override
	public UTRANTransparentContent getUTRANTransparentContent() 
	{
		return utranTransparentContent;
	}

	@Override
	public void setUTRANTransparentContent(UTRANTransparentContent context) 
	{
		this.utranTransparentContent=context;
	}

	@Override
	public PDPContextPrioritization getPDPContextPrioritization() 
	{
		return pdpContextPrioritization;
	}

	@Override
	public void setPDPContextPrioritization(PDPContextPrioritization pdpContextPrioritization) 
	{
		this.pdpContextPrioritization=pdpContextPrioritization;
	}

	@Override
	public MBMSUEContext getMBMSUEContext() 
	{
		return this.mbmsUEContext;
	}

	@Override
	public void setMBMSUEContext(MBMSUEContext ueContext) 
	{
		this.mbmsUEContext=ueContext;		
	}

	@Override
	public SelectedPLMNID getSelectedPLMNID() 
	{
		return this.selectedPLMNID;
	}

	@Override
	public void setSelectedPLMNID(SelectedPLMNID selectedPLMNID) 
	{
		this.selectedPLMNID=selectedPLMNID;
	}

	@Override
	public BSSContainer getBSSContainer() 
	{
		return this.bssContainer;
	}

	@Override
	public void setBSSContainer(BSSContainer container) 
	{
		this.bssContainer=container;
	}

	@Override
	public CellIdentification getCellIdentification() 
	{
		return this.cellIdentification;
	}

	@Override
	public void setCellIdentification(CellIdentification cellIdentification) 
	{
		this.cellIdentification=cellIdentification;
	}

	@Override
	public BSSGPCause getBSSGPCause() 
	{
		return this.bssGPCause;
	}

	@Override
	public void setBSSGPCause(BSSGPCause bssGPCause) 
	{
		this.bssGPCause=bssGPCause;
	}

	@Override
	public XIDParameters getPSHandoverXIDParameters() 
	{
		return this.xidParameters;
	}

	@Override
	public void setPSHandoverXIDParameters(XIDParameters parameters) 
	{
		xidParameters=parameters;
	}

	@Override
	public DirectTunnelFlags getDirectTunnelFlags() 
	{
		return this.directTunnelFlags;
	}

	@Override
	public void setDirectTunnelFlags(DirectTunnelFlags directTunnelFlags) 
	{
		this.directTunnelFlags=directTunnelFlags;
	}

	@Override
	public ReliableRatHandoverIndication getReliableRatHandoverIndication() 
	{
		return this.indication;
	}

	@Override
	public void setReliableRatHandoverIndication(ReliableRatHandoverIndication reliableRatHandoverIndication) 
	{
		this.indication=reliableRatHandoverIndication;
	}

	@Override
	public RFSPIndex getSubscribedRFSPIndex() 
	{
		return this.subscribedFRSPIndex;
	}

	@Override
	public void setSubscribedRFSPIndex(RFSPIndex index) 
	{
		this.subscribedFRSPIndex=index;
	}

	@Override
	public RFSPIndex getRFSPIndexInUse() 
	{
		return this.rfspIndexInUse;
	}

	@Override
	public void setRFSPIndexInUse(RFSPIndex index) 
	{
		this.rfspIndexInUse=index;
	}

	@Override
	public FQDN getCollocatedGGSN_PGWFQDN() 
	{
		return this.collocatedGGSN_PGWFQDN;
	}

	@Override
	public void setCollocatedGGSN_PGWFQDN(FQDN fqdn) 
	{
		this.collocatedGGSN_PGWFQDN=fqdn;
	}

	@Override
	public EvolvedAllocationRetentionPriority2 getEvolvedAllocationRetentionPriority2() 
	{
		return this.priority;
	}

	@Override
	public void setEvolvedAllocationRetentionPriority2(EvolvedAllocationRetentionPriority2 priority) 
	{
		this.priority=priority;
	}

	@Override
	public ExtendedCommonFlags getExtendedCommonFlags() 
	{
		return extendedCommonFlags;
	}

	@Override
	public void setExtendedCommonFlags(ExtendedCommonFlags extendedCommonFlags) 
	{
		this.extendedCommonFlags=extendedCommonFlags;
	}

	@Override
	public CSGID getCSGID() 
	{
		return this.csgID;
	}

	@Override
	public void setCSGID(CSGID csgID) 
	{
		this.csgID=csgID;
	}

	@Override
	public CSGMembershipIndication getCSGMembershipIndication() 
	{
		return this.csgMembershipIndication;				
	}

	@Override
	public void setCSGMembershipIndication(CSGMembershipIndication csgMembershipIndication) 
	{
		this.csgMembershipIndication=csgMembershipIndication;
	}

	@Override
	public UENetworkCapability getUENetworkCapability() 
	{
		return this.ueNetworkCapability;
	}

	@Override
	public void setUENetworkCapability(UENetworkCapability ueNetworkCapability) 
	{
		this.ueNetworkCapability=ueNetworkCapability;
	}

	@Override
	public UEAMBR getUEAMBR() 
	{
		return this.ueAMBR;
	}

	@Override
	public void setUEAMBR(UEAMBR ueAMBR) 
	{
		this.ueAMBR=ueAMBR;
	}

	@Override
	public APNAMBRWithNSAPI getAPNAMBRWithNSAPI() 
	{
		return this.apnAMBRWithNSAPI;
	}

	@Override
	public void setAPNAMBRWithNSAPI(APNAMBRWithNSAPI apnAMBRWithNSAPI) 
	{
		this.apnAMBRWithNSAPI=apnAMBRWithNSAPI;
	}

	@Override
	public SignalingPriorityIndicationWithNSAPI getSignalingPriorityIndicationWithNSAPI() 
	{
		return this.signalingPriorityIndicationWithNSAPI;
	}

	@Override
	public void setSignalingPriorityIndicationWithNSAPI(SignalingPriorityIndicationWithNSAPI indication) 
	{
		this.signalingPriorityIndicationWithNSAPI=indication;
	}

	@Override
	public HigherThen16MbpsBitrate getHigherThen16MbpsBitrate() 
	{
		return this.bitrate;
	}

	@Override
	public void setHigherThen16MbpsBitrate(HigherThen16MbpsBitrate bitrate) 
	{
		this.bitrate=bitrate;
	}

	@Override
	public AdditionalMMContextForSRVCC getAdditionalMMContextForSRVCC() 
	{
		return this.additionalMMContextForSRVCC;
	}

	@Override
	public void setAdditionalMMContextForSRVCC(AdditionalMMContextForSRVCC context) 
	{
		this.additionalMMContextForSRVCC=context;
	}

	@Override
	public AdditionalFlagsForSRVCC getAdditionalFlagsForSRVCC() 
	{
		return this.additionalFlagsForSRVCC;
	}

	@Override
	public void setAdditionalFlagsForSRVCC(AdditionalFlagsForSRVCC additionalFlagsForSRVCC) 
	{
		this.additionalFlagsForSRVCC=additionalFlagsForSRVCC;
	}

	@Override
	public STNSR getSTNSR() 
	{
		return this.stnSR;
	}

	@Override
	public void setSTNSR(STNSR stnSR) 
	{
		this.stnSR=stnSR;
	}

	@Override
	public CMSISDN getCMSISDN() 
	{
		return this.cMSISDN;
	}

	@Override
	public void setCMSISDN(CMSISDN cmisdn) 
	{
		this.cMSISDN=cmisdn;
	}

	@Override
	public ExtendedRanapCause getExtendedRanapCause() 
	{
		return this.extendedRanapCause;
	}

	@Override
	public void setExtendedRanapCause(ExtendedRanapCause extendedRanapCause) 
	{
		this.extendedRanapCause=extendedRanapCause;
	}

	@Override
	public ENodeBID getENodeBID() 
	{
		return this.eNodeBID;
	}

	@Override
	public void setENodeBID(ENodeBID eNodeBID) 
	{
		this.eNodeBID=eNodeBID;
	}

	@Override
	public SelectionModeWithNSAPI getSelectionModeWithNSAPI() 
	{
		return this.selectionModeWithNSAPI;
	}

	@Override
	public void setSelectionModeWithNSAPI(SelectionModeWithNSAPI selectionModeWithNSAPI) 
	{
		this.selectionModeWithNSAPI=selectionModeWithNSAPI;
	}
}