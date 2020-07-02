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
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
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
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifierControlPane;
import com.mobius.software.telco.protocols.gtp.api.headers.UEAMBR;
import com.mobius.software.telco.protocols.gtp.api.headers.UENetworkCapability;
import com.mobius.software.telco.protocols.gtp.api.messages.SGSNContextResponse;

public class SGSNContextResponseImpl extends AbstractGTPMessage implements SGSNContextResponse
{
	private Cause cause;
	private IMSI imsi;
	private TunnerEndpointIdentifierControlPane teicp;
	private RABContext rabContext;
	private RadioPrioritySMS radioPrioritySMS;
	private RadioPriority radioPriority;
	private PacketFlowID packetFlowID;
	private ChargingCharacteristic chargingCharacteristic;
	private RadioPriorityLCS radioPriorityLCS;
	private MMContext mmContext;
	private PDPContext pdpContext;
	private GSNAddress sgsnAddressForSignaling;
	private PDPContextPrioritization pdpContextPrioritization;
	private MBMSUEContext mbmsEUContext;
	private RFSPIndex subscribedRFSPIndex;
	private RFSPIndex rfspIndexInUse;
	private FQDN collocatedGGSN_PGWFQDN;
	private EvolvedAllocationRetentionPriority2 evolvedAllocationRetentionPriority2;
	private ExtendedCommonFlags extendedCommonFlags;
	private UENetworkCapability ueNetworkCapability;
	private UEAMBR ueAMBR;
	private APNAMBRWithNSAPI apnAMBRWithNSAPI;
	private SignalingPriorityIndicationWithNSAPI signalingPriorityIndicationWithNSAPI;
	private HigherThen16MbpsBitrate higherThen16MbpsBitrate;
	private SelectionModeWithNSAPI selectionModeWithNSAPI;
	private LocalHomeNetworkIDWithNSAPI localHomeNetworkWithNSAPI;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public MessageType getMessageType() 
	{
		return MessageType.SGSN_CONTEXT_RESPONSE;
	}

	@Override
	public void applyTLV(TLV1 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case CAUSE:
				cause=(Cause)tlv;
				break;
			case IMSI:
				imsi=(IMSI)tlv;
				break;
			case TEICP:
				teicp=(TunnerEndpointIdentifierControlPane)tlv;
				break;
			case RAB_CONTEXT:
				rabContext=(RABContext)tlv;
				break;
			case RADIO_PRIORITY_SMS:
				radioPrioritySMS=(RadioPrioritySMS)tlv;
				break;
			case RADIO_PRIORITY:
				radioPriority=(RadioPriority)tlv;
				break;
			case PACKET_FLOW_ID:
				packetFlowID=(PacketFlowID)tlv;
				break;
			case CHARGING_CHARACTERISTICS:
				chargingCharacteristic=(ChargingCharacteristic)tlv;
				break;
			case RADIO_PRIORITY_LCS:
				radioPriorityLCS=(RadioPriorityLCS)tlv;
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
			case PDP_CONTEXT_PRIORITIZATION:
				pdpContextPrioritization=(PDPContextPrioritization)tlv;
				break;				
			case MBMS_UE_CONTEXT:
				mbmsEUContext=(MBMSUEContext)tlv;
				break;
			case RFSP_INDEX:
				if(subscribedRFSPIndex==null)
					subscribedRFSPIndex=(RFSPIndex)tlv;
				else if(rfspIndexInUse==null)
					rfspIndexInUse=(RFSPIndex)tlv;
				else
					throw new GTPParseException("Unknown TLV received,type:" + tlv.getElementType());
				break;
			case FQDN:
				collocatedGGSN_PGWFQDN=(FQDN)tlv;
				break;
			case EVOLVED_ALLOCATION_RETENTION_PRIORITY_2:
				evolvedAllocationRetentionPriority2=(EvolvedAllocationRetentionPriority2)tlv;
				break;
			case EXTENDED_COMMON_FLAGS:
				extendedCommonFlags=(ExtendedCommonFlags)tlv;
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
				higherThen16MbpsBitrate=(HigherThen16MbpsBitrate)tlv;
				break;
			case SELECTION_MODE_WITH_NSAPI:
				selectionModeWithNSAPI=(SelectionModeWithNSAPI)tlv;
				break;
			case LOCAL_HOME_NETWORK_ID_WITH_NSAPI:
				localHomeNetworkWithNSAPI=(LocalHomeNetworkIDWithNSAPI)tlv;
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
		if(cause==null)
			throw new GTPParseException("cause is missing");
		
		output.add(cause);
	
		if(imsi!=null)
			output.add(imsi);
		
		if(teicp!=null)
			output.add(teicp);
	
		if(rabContext!=null)
			output.add(rabContext);
	
		if(radioPrioritySMS!=null)
			output.add(radioPrioritySMS);
	
		if(radioPriority!=null)
			output.add(radioPriority);
	
		if(packetFlowID!=null)
			output.add(packetFlowID);
	
		if(chargingCharacteristic!=null)
			output.add(chargingCharacteristic);
	
		if(radioPriorityLCS!=null)
			output.add(radioPriorityLCS);
	
		if(mmContext!=null)
			output.add(mmContext);
	
		if(pdpContext!=null)
			output.add(pdpContext);
	
		if(sgsnAddressForSignaling!=null)
			output.add(sgsnAddressForSignaling);
	
		if(pdpContextPrioritization!=null)
			output.add(pdpContextPrioritization);
	
		if(mbmsEUContext!=null)
			output.add(mbmsEUContext);
	
		if(subscribedRFSPIndex!=null)
			output.add(subscribedRFSPIndex);
	
		if(rfspIndexInUse!=null)
			output.add(rfspIndexInUse);
	
		if(collocatedGGSN_PGWFQDN!=null)
			output.add(collocatedGGSN_PGWFQDN);
	
		if(evolvedAllocationRetentionPriority2!=null)
			output.add(evolvedAllocationRetentionPriority2);
	
		if(extendedCommonFlags!=null)
			output.add(extendedCommonFlags);
	
		if(ueNetworkCapability!=null)
			output.add(ueNetworkCapability);
	
		if(ueAMBR!=null)
			output.add(ueAMBR);
		
		if(apnAMBRWithNSAPI!=null)
			output.add(apnAMBRWithNSAPI);
		
		if(signalingPriorityIndicationWithNSAPI!=null)
			output.add(signalingPriorityIndicationWithNSAPI);
		
		if(higherThen16MbpsBitrate!=null)
			output.add(higherThen16MbpsBitrate);
		
		if(selectionModeWithNSAPI!=null)
			output.add(selectionModeWithNSAPI);
		
		if(localHomeNetworkWithNSAPI!=null)
			output.add(localHomeNetworkWithNSAPI);
		
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
	public Cause getCause() 
	{
		return this.cause;
	}

	@Override
	public void setCause(Cause cause) 
	{
		this.cause=cause;
	}

	@Override
	public IMSI getIMSI() 
	{
		return this.imsi;
	}

	@Override
	public void setIMSI(IMSI imsi) 
	{
		this.imsi=imsi;
	}

	@Override
	public TunnerEndpointIdentifierControlPane getControlPaneTEI() 
	{
		return this.teicp;
	}

	@Override
	public void setControlPageTEI(TunnerEndpointIdentifierControlPane tei) 
	{
		this.teicp=tei;
	}

	@Override
	public RABContext getRABContext() 
	{
		return this.rabContext;
	}

	@Override
	public void setRABContext(RABContext rabContext) 
	{
		this.rabContext=rabContext;
	}

	@Override
	public RadioPrioritySMS getRadioPrioritySMS() 
	{
		return this.radioPrioritySMS;
	}

	@Override
	public void setRadioPrioritySMS(RadioPrioritySMS radioPrioritySMS) 
	{
		this.radioPrioritySMS=radioPrioritySMS;
	}

	@Override
	public RadioPriority getRadioPriority() 
	{
		return this.radioPriority;
	}

	@Override
	public void setRadioPriority(RadioPriority radioPriority) 
	{
		this.radioPriority=radioPriority;
	}

	@Override
	public PacketFlowID getPacketFlowID() 
	{
		return this.packetFlowID;
	}

	@Override
	public void setPacketFlowID(PacketFlowID flowID) 
	{
		this.packetFlowID=flowID;
	}

	@Override
	public ChargingCharacteristic getChargingCharacteristics() 
	{
		return this.chargingCharacteristic;
	}

	@Override
	public void setChargingCharacteristics(ChargingCharacteristic characteristic) 
	{
		this.chargingCharacteristic=characteristic;
	}

	@Override
	public RadioPriorityLCS getRadioPriorityLCS() 
	{
		return this.radioPriorityLCS;
	}

	@Override
	public void setRadioPriorityLCS(RadioPriorityLCS radioPriorityLCS) 
	{
		this.radioPriorityLCS=radioPriorityLCS;
	}

	@Override
	public MMContext getMMContext() 
	{
		return this.mmContext;
	}

	@Override
	public void setMMContext(MMContext context) 
	{
		this.mmContext=context;
	}

	@Override
	public PDPContext getPDPContext() 
	{
		return this.pdpContext;
	}

	@Override
	public void setPDPContext(PDPContext context) 
	{
		this.pdpContext=context;
	}

	@Override
	public GSNAddress getSGSNAddressForSignaling() 
	{
		return this.sgsnAddressForSignaling;
	}

	@Override
	public void setSGSNAddressForSignaling(GSNAddress address) 
	{
		this.sgsnAddressForSignaling=address;
	}

	@Override
	public PDPContextPrioritization getPDPContextPrioritization() 
	{
		return this.pdpContextPrioritization;
	}

	@Override
	public void setPDPContextPrioritization(PDPContextPrioritization prioritization) 
	{
		this.pdpContextPrioritization=prioritization;
	}

	@Override
	public MBMSUEContext getMBMSUEContext() 
	{
		return this.mbmsEUContext;
	}

	@Override
	public void setMBMSUEContext(MBMSUEContext context) 
	{
		this.mbmsEUContext=context;
	}

	@Override
	public RFSPIndex getSubscribedRFSPIndex() 
	{
		return this.subscribedRFSPIndex;
	}

	@Override
	public void setSubscribedRFSPIndex(RFSPIndex index) 
	{
		this.subscribedRFSPIndex=index;
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
	public void setCollocatedGGSN_PGWFQDN(FQDN value) 
	{
		this.collocatedGGSN_PGWFQDN=value;
	}

	@Override
	public EvolvedAllocationRetentionPriority2 getEvolvedAllocationRetentionPriority2() 
	{
		return this.evolvedAllocationRetentionPriority2;
	}

	@Override
	public void setEvolvedAllocationRetentionPriority2(EvolvedAllocationRetentionPriority2 priority) 	
	{
		this.evolvedAllocationRetentionPriority2=priority;
	}

	@Override
	public ExtendedCommonFlags getExtendedCommonFlags() 
	{
		return this.extendedCommonFlags;
	}

	@Override
	public void setExtendedCommonFlags(ExtendedCommonFlags flags) 
	{
		this.extendedCommonFlags=flags;		
	}

	@Override
	public UENetworkCapability getUENetworkCapability() 
	{
		return this.ueNetworkCapability;
	}

	@Override
	public void setUENetworkCapability(UENetworkCapability capability) 
	{
		this.ueNetworkCapability=capability;
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
	public void setSignalingPriorityIndicationWithNSAPI(SignalingPriorityIndicationWithNSAPI priority) 
	{
		this.signalingPriorityIndicationWithNSAPI=priority;
	}

	@Override
	public HigherThen16MbpsBitrate getHigherThen16MbpsBitrate() 
	{
		return this.higherThen16MbpsBitrate;
	}

	@Override
	public void setHigherThen16MbpsBitrate(HigherThen16MbpsBitrate value) 
	{
		this.higherThen16MbpsBitrate=value;
	}

	@Override
	public SelectionModeWithNSAPI getSelectionModeWithNSAPI() 
	{
		return this.selectionModeWithNSAPI;
	}

	@Override
	public void setSelectionModeWithNSAPI(SelectionModeWithNSAPI selectionMode) 
	{
		this.selectionModeWithNSAPI=selectionMode;
	}

	@Override
	public LocalHomeNetworkIDWithNSAPI getLocalHomeNetworkIDWithNSAPI() 
	{
		return this.localHomeNetworkWithNSAPI;
	}

	@Override
	public void setLocalHomeNetworkIDWithNSAPI(LocalHomeNetworkIDWithNSAPI localHomeNetwork) 
	{
		this.localHomeNetworkWithNSAPI=localHomeNetwork;
	}
}