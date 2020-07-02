package com.mobius.software.telco.protocols.gtp.impl.messages.v2;
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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.AdditionalFlagsForSRVCC;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.AdditionalMMContextForSRVCC;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CSGID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CSGInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CSGMembershipIndication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FCause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FContainer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FQDN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TargetIdentification;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TraceInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UETimezone;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.ForwardRelocationRequest;

public class ForwardRelocationRequestImpl extends AbstractGTP2Message implements ForwardRelocationRequest
{
	IMSI imsi;
	FTEID senderFTEIDControlPlane;
	PDNConnection pdnConnection;
	FTEID sgwAddressForControlPlane;
	FQDN sgwNodeName;
	MMContext mmContext;
	Indication indication;
	FContainer eutranTransparentContainer;
	FContainer utranTransparentContainer;
	FContainer bssContainer;
	TargetIdentification targetIdentification;
	IPAddress s101Address;
	IPAddress s102Address;
	FCause s1APCause;
	FCause ranapCause;
	FCause bssGPCause;
	SourceIdentification sourceIdentification;
	PLMNID selectedPLMNID;
	Recovery recovery;
	TraceInformation traceInformation;
	RFSPIndex subscribedRFSPIndex;
	RFSPIndex rfspIndexInUse;
	CSGID csgID;
	CSGMembershipIndication csgMembershipIndication;
	UETimezone ueTimezone;
	ServingNetwork servingNetwork;
	LDN sgsnLDN;
	AdditionalMMContextForSRVCC additionalMMContextForSRVCC;
	AdditionalFlagsForSRVCC additionalFlagsForSRVCC;
	STNSR stnSR;
	MSISDN cMSISDN;
	MDTConfiguration mdtConfiguration;
	FQDN sgsnNodeName;
	FQDN mmeNodeName;
	CSGInformation userCSGInformation;
	MonitoringEventInformation monitoringEventInformation;
	IntegerNumber ueUsageType;
	SCEFPDNConnection scefPDNConnection;
	MSISDN msisdn;
	PortNumber sourceUDPPortNumber;
	ServingPLMNRateControl servingPLMNControlRate;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.FORWARD_RELOCATION_REQUEST;
	}

	@Override
	public void applyTLV(TLV2 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case IMSI:
				imsi=(IMSI)tlv;
				break;
			case FTEID:
				switch(tlv.getInstance())
				{
					case 0:
						senderFTEIDControlPlane=(FTEID)tlv;
						break;
					case 1:
						sgwAddressForControlPlane=(FTEID)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case PDN_CONNECTION:
				pdnConnection=(PDNConnection)tlv;
				break;
			case FQDN:
				switch(tlv.getInstance())
				{
					case 0:
						sgwNodeName=(FQDN)tlv;
						break;
					case 1:
						sgsnNodeName=(FQDN)tlv;
						break;
					case 2:
						mmeNodeName=(FQDN)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case MM_CONTEXT_EPS:
			case MM_CONTEXT_FOR_CS_TO_PS:
			case MM_CONTEXT_FOR_EUTRAN:
			case MM_CONTEXT_FOR_UTRAN:
			case MM_CONTEXT_GSM_AND_TRIPLETS:
			case MM_CONTEXT_GSM_CIPHER_AND_QUINTIPLETS:
			case MM_CONTEXT_UMTS_AND_QUINTIPLETS:
			case MM_CONTEXT_UMTS_CIPHER_AND_QUINTIPLETS:
			case MM_CONTEXT_UMTS_KEY_QUADRIPLETS_AND_QUINTIPLETS:
				mmContext=(MMContext)tlv;
				break;
			case INDICATION:
				indication=(Indication)tlv;
				break;
			case FCONTAINER:
				switch(tlv.getInstance())
				{
					case 0:
						eutranTransparentContainer=(FContainer)tlv;
						break;
					case 1:
						utranTransparentContainer=(FContainer)tlv;
						break;
					case 2:
						bssContainer=(FContainer)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case TARGET_IDENTIFICATION:
				targetIdentification=(TargetIdentification)tlv;
				break;
			case IP_ADDRESS:
				switch(tlv.getInstance())
				{
					case 0:
						s101Address=(IPAddress)tlv;
						break;
					case 1:
						s102Address=(IPAddress)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case FCAUSE:
				switch(tlv.getInstance())
				{
					case 0:
						s1APCause=(FCause)tlv;
						break;
					case 1:
						ranapCause=(FCause)tlv;
						break;
					case 2:
						bssGPCause=(FCause)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case SOURCE_IDENTIFICATION:
				sourceIdentification=(SourceIdentification)tlv;
				break;
			case PLMN_ID:
				selectedPLMNID=(PLMNID)tlv;
				break;
			case RECOVERY:
				recovery=(Recovery)tlv;
				break;
			case TRACE_INFORMATION:
				traceInformation=(TraceInformation)tlv;
				break;
			case RFSP_INDEX:
				switch(tlv.getInstance())
				{
					case 0:
						subscribedRFSPIndex=(RFSPIndex)tlv;
						break;
					case 1:
						rfspIndexInUse=(RFSPIndex)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case CSG_ID:
				csgID=(CSGID)tlv;
				break;
			case CSG_MEMBERSHIP_INDICATION:
				csgMembershipIndication=(CSGMembershipIndication)tlv;
				break;
			case UE_TIMEZONE:
				ueTimezone=(UETimezone)tlv;
				break;
			case SERVING_NETWORK:
				servingNetwork=(ServingNetwork)tlv;
				break;
			case LDN:
				sgsnLDN=(LDN)tlv;
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
			case MDT_CONFIGURATION:
				mdtConfiguration=(MDTConfiguration)tlv;
				break;
			case USER_CSG_INFORMATION:
				userCSGInformation=(CSGInformation)tlv;
				break;
			case MSISDN:
				switch(tlv.getInstance())
				{
					case 0:
						cMSISDN=(MSISDN)tlv;
						break;
					case 1:
						msisdn=(MSISDN)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case MONITORING_EVENT_INFORMATION:
				monitoringEventInformation=(MonitoringEventInformation)tlv;
				break;
			case INTEGER_NUMBER:
				ueUsageType=(IntegerNumber)tlv;
				break;
			case SCEF_PDN_CONNECTION:
				scefPDNConnection=(SCEFPDNConnection)tlv;
				break;
			case PORT_NUMBER:
				sourceUDPPortNumber=(PortNumber)tlv;
				break;
			case SERVING_PLMN_RATE_CONTROL:
				servingPLMNControlRate=(ServingPLMNRateControl)tlv;
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
	public List<TLV2> getTLVs() throws GTPParseException 
	{
		ArrayList<TLV2> output=new ArrayList<TLV2>();
		if(imsi!=null)
			output.add(imsi);
		
		if(senderFTEIDControlPlane==null)
			throw new GTPParseException("sender FTEID CP not set");
		
		output.add(senderFTEIDControlPlane);
		
		if(pdnConnection!=null)
			output.add(pdnConnection);
		
		if(sgwAddressForControlPlane!=null)
			output.add(sgwAddressForControlPlane);
			
		if(sgwNodeName!=null)
			output.add(sgwNodeName);
		
		if(mmContext==null)
			throw new GTPParseException("MM Context not set");
			
		output.add(mmContext);
		
		if(indication!=null)
			output.add(indication);
		
		if(eutranTransparentContainer!=null)
			output.add(eutranTransparentContainer);
		
		if(utranTransparentContainer!=null)
			output.add(utranTransparentContainer);
		
		if(bssContainer!=null)
			output.add(bssContainer);
		
		if(targetIdentification!=null)
			output.add(targetIdentification);
		
		if(s101Address!=null)
			output.add(s101Address);
		
		if(s102Address!=null)
			output.add(s102Address);
		
		if(s1APCause!=null)
			output.add(s1APCause);
		
		if(ranapCause!=null)
			output.add(ranapCause);
		
		if(bssGPCause!=null)
			output.add(bssGPCause);
		
		if(sourceIdentification!=null)
			output.add(sourceIdentification);
		
		if(selectedPLMNID!=null)
			output.add(selectedPLMNID);
		
		if(recovery!=null)
			output.add(recovery);
		
		if(traceInformation!=null)
			output.add(traceInformation);
			
		if(subscribedRFSPIndex!=null)
			output.add(subscribedRFSPIndex);
		
		if(rfspIndexInUse!=null)
			output.add(rfspIndexInUse);
		
		if(csgID!=null)
			output.add(csgID);
		
		if(csgMembershipIndication!=null)
			output.add(csgMembershipIndication);
		
		if(ueTimezone!=null)
			output.add(ueTimezone);
			
		if(servingNetwork!=null)
			output.add(servingNetwork);
		
		if(sgsnLDN!=null)
			output.add(sgsnLDN);
			
		if(additionalMMContextForSRVCC!=null)
			output.add(additionalMMContextForSRVCC);
		
		if(additionalFlagsForSRVCC!=null)
			output.add(additionalFlagsForSRVCC);
		
		if(stnSR!=null)
			output.add(stnSR);
		
		if(cMSISDN!=null)
			output.add(cMSISDN);
		
		if(mdtConfiguration!=null)
			output.add(mdtConfiguration);
		
		if(sgsnNodeName!=null)
			output.add(sgsnNodeName);
			
		if(mmeNodeName!=null)
			output.add(mmeNodeName);
			
		if(userCSGInformation!=null)
			output.add(userCSGInformation);
			
		if(monitoringEventInformation!=null)
			output.add(monitoringEventInformation);
			
		if(ueUsageType!=null)
			output.add(ueUsageType);
			
		if(scefPDNConnection!=null)
			output.add(scefPDNConnection);
			
		if(msisdn!=null)
			output.add(msisdn);
		
		if(sourceUDPPortNumber!=null)
			output.add(sourceUDPPortNumber);
		
		if(servingPLMNControlRate!=null)
			output.add(servingPLMNControlRate);
		
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
		return this.imsi;				
	}

	@Override
	public void setIMSI(IMSI imsi) 
	{
		imsi.setInstance(0);
		this.imsi=imsi;
	}

	@Override
	public FTEID getSenderFTEIDControlPlane() 
	{
		return senderFTEIDControlPlane;
	}

	@Override
	public void setSenderFTEIDControlPlane(FTEID fteid) 
	{
		fteid.setInstance(0);
		this.senderFTEIDControlPlane=fteid;
	}

	@Override
	public PDNConnection getPDNConnection() 
	{
		return pdnConnection;
	}

	@Override
	public void setPDNConection(PDNConnection connection) 
	{
		connection.setInstance(0);
		this.pdnConnection=connection;
	}

	@Override
	public FTEID getSGWAddressForControlPlane() 
	{
		return sgwAddressForControlPlane;
	}

	@Override
	public void setSGWAddressForControlPlane(FTEID fteid) 
	{
		fteid.setInstance(1);
		this.sgwAddressForControlPlane=fteid;
	}

	@Override
	public FQDN getSGWNodeName() 
	{
		return sgwNodeName;
	}

	@Override
	public void setSGWNodeName(FQDN sgwNodeName) 
	{
		sgwNodeName.setInstance(0);
		this.sgwNodeName=sgwNodeName;
	}

	@Override
	public MMContext getMMContext() 
	{
		return mmContext;
	}

	@Override
	public void setMMContext(MMContext context) 
	{
		context.setInstance(0);
		this.mmContext=context;
	}

	@Override
	public Indication getIndication() 
	{
		return this.indication;
	}

	@Override
	public void setIndication(Indication indication) 
	{
		indication.setInstance(0);
		this.indication=indication;
	}

	@Override
	public FContainer getEUTRANTransparentContainer() 
	{
		return this.eutranTransparentContainer;
	}

	@Override
	public void setEUTRANTransparentContainer(FContainer transparentContainer) 
	{
		transparentContainer.setInstance(0);
		this.eutranTransparentContainer=transparentContainer;
	}

	@Override
	public FContainer getUTRANTransparentContainer() 
	{
		return this.utranTransparentContainer;
	}

	@Override
	public void setUTRANTransparentContainer(FContainer transparentContainer) 
	{
		transparentContainer.setInstance(1);
		this.utranTransparentContainer=transparentContainer;
	}

	@Override
	public FContainer getBSSContainer() 
	{
		return bssContainer;
	}

	@Override
	public void setBSSContainer(FContainer bssContainer) 
	{
		bssContainer.setInstance(2);
		this.bssContainer=bssContainer;
	}

	@Override
	public TargetIdentification getTargetIdentification() 
	{
		return this.targetIdentification;		
	}

	@Override
	public void setTargetIdentification(TargetIdentification identification) 
	{
		identification.setInstance(0);
		this.targetIdentification=identification;
	}

	@Override
	public IPAddress getS101Address() 
	{
		return s101Address;
	}

	@Override
	public void setS101Address(IPAddress s101Address) 
	{
		s101Address.setInstance(0);
		this.s101Address=s101Address;
	}

	@Override
	public IPAddress getS102Address() 
	{
		return s102Address;
	}

	@Override
	public void setS102Address(IPAddress s102Address) 
	{
		s102Address.setInstance(1);
		this.s102Address=s102Address;
	}

	@Override
	public FCause getS1APCause() 
	{
		return s1APCause;
	}

	@Override
	public void setS1APCause(FCause cause) 
	{
		cause.setInstance(0);
		this.s1APCause=cause;
	}

	@Override
	public FCause getRANAPCause() 
	{
		return ranapCause;
	}

	@Override
	public void setRANAPCause(FCause cause) 
	{
		cause.setInstance(1);
		this.ranapCause=cause;
	}

	@Override
	public FCause getBSSGPCause() 
	{
		return bssGPCause;
	}

	@Override
	public void setBSSGPCause(FCause cause) 
	{
		cause.setInstance(2);
		this.bssGPCause=cause;
	}

	@Override
	public SourceIdentification getSourceIdentification() 
	{
		return sourceIdentification;
	}

	@Override
	public void setSourceIdentification(SourceIdentification identification) 
	{
		identification.setInstance(0);
		this.sourceIdentification=identification;
	}

	@Override
	public PLMNID getSelectedPLMNID() 
	{
		return selectedPLMNID;
	}

	@Override
	public void setSelectedPLMNID(PLMNID plmnID) 
	{
		plmnID.setInstance(0);
		this.selectedPLMNID=plmnID;
	}

	@Override
	public Recovery getRecovery() 
	{
		return this.recovery;
	}

	@Override
	public void setRecovery(Recovery recovery) 
	{
		recovery.setInstance(0);
		this.recovery=recovery;
	}

	@Override
	public TraceInformation getTraceInformation() 
	{
		return this.traceInformation;
	}

	@Override
	public void setTraceInformation(TraceInformation information) 
	{
		information.setInstance(0);
		this.traceInformation=information;
	}

	@Override
	public RFSPIndex getSubscribedRFSPIndex() 
	{
		return this.subscribedRFSPIndex;
	}

	@Override
	public void setSubscribedRFSPIndex(RFSPIndex index) 
	{
		index.setInstance(0);
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
		index.setInstance(1);
		this.rfspIndexInUse=index;
	}

	@Override
	public CSGID getCSGID() 
	{
		return this.csgID;
	}

	@Override
	public void setCSGID(CSGID csgID) 
	{
		csgID.setInstance(0);
		this.csgID=csgID;
	}

	@Override
	public CSGMembershipIndication getCSGMembershipIndication() 
	{
		return this.csgMembershipIndication;
	}

	@Override
	public void setCSGMembershipIndication(CSGMembershipIndication indication) 
	{
		indication.setInstance(0);
		this.csgMembershipIndication=indication;
	}

	@Override
	public UETimezone getUETimezone() 
	{
		return this.ueTimezone;
	}

	@Override
	public void setUETimezone(UETimezone ueTimezone) 
	{
		ueTimezone.setInstance(0);
		this.ueTimezone=ueTimezone;
	}

	@Override
	public ServingNetwork getServingNetwork() 
	{
		return this.servingNetwork;
	}

	@Override
	public void setServingNetwork(ServingNetwork servingNetwork) 
	{
		servingNetwork.setInstance(0);
		this.servingNetwork=servingNetwork;
	}

	@Override
	public LDN getSGSNLDN() 
	{
		return this.sgsnLDN;
	}

	@Override
	public void setSGSNLDN(LDN ldn) 
	{
		ldn.setInstance(0);
		this.sgsnLDN=ldn;
	}

	@Override
	public AdditionalMMContextForSRVCC getAdditionalMMContextForSRVCC() 
	{
		return this.additionalMMContextForSRVCC;
	}

	@Override
	public void setAdditionalMMContextForSRVCC(AdditionalMMContextForSRVCC mmContext) 
	{
		mmContext.setInstance(0);
		this.additionalMMContextForSRVCC=mmContext;
	}

	@Override
	public AdditionalFlagsForSRVCC getAdditionalFlagsForSRVCC() 
	{
		return this.additionalFlagsForSRVCC;
	}

	@Override
	public void setAdditionalFlagsForSRVCC(AdditionalFlagsForSRVCC flags) 
	{
		flags.setInstance(0);
		this.additionalFlagsForSRVCC=flags;
	}

	@Override
	public STNSR getSTNSR() 
	{
		return this.stnSR;
	}

	@Override
	public void setSTNSR(STNSR stnSR) 
	{
		stnSR.setInstance(0);
		this.stnSR=stnSR;
	}

	@Override
	public MSISDN getCMSISDN() 
	{
		return this.cMSISDN;
	}

	@Override
	public void setCMSISDN(MSISDN cmsisdn) 
	{
		cmsisdn.setInstance(0);
		this.cMSISDN=cmsisdn;
	}

	@Override
	public MDTConfiguration getMDTConfiguration() 
	{
		return this.mdtConfiguration;
	}

	@Override
	public void setMDTConfiguration(MDTConfiguration configuration) 
	{
		configuration.setInstance(0);
		this.mdtConfiguration=configuration;
	}

	@Override
	public FQDN getSGSNNodeName() 
	{
		return sgsnNodeName;
	}

	@Override
	public void setSGSNodeName(FQDN sgsnNodeName) 
	{
		sgsnNodeName.setInstance(1);
		this.sgsnNodeName=sgsnNodeName;
	}

	@Override
	public FQDN getMMENodeName() 
	{
		return this.mmeNodeName;
	}

	@Override
	public void setMMENodeName(FQDN mmeNodeName) 
	{
		mmeNodeName.setInstance(2);
		this.mmeNodeName=mmeNodeName;
	}

	@Override
	public CSGInformation getUserCSGInformation() 
	{
		return this.userCSGInformation;
	}

	@Override
	public void setUserCSGInformation(CSGInformation csgInformation) 
	{
		csgInformation.setInstance(0);
		this.userCSGInformation=csgInformation;
	}

	@Override
	public MonitoringEventInformation getMonitoringEventInformation() 
	{
		return this.monitoringEventInformation;
	}

	@Override
	public void setMonitoringEventInformation(MonitoringEventInformation eventInformation) 
	{
		eventInformation.setInstance(0);
		this.monitoringEventInformation=eventInformation;
	}

	@Override
	public IntegerNumber getUEUsageType() 
	{
		return this.ueUsageType;
	}

	@Override
	public void UEUsageType(IntegerNumber usageType) 
	{
		usageType.setInstance(0);
		this.ueUsageType=usageType;
	}

	@Override
	public SCEFPDNConnection getSCEFPDNConnection() 
	{
		return this.scefPDNConnection;
	}

	@Override
	public void setSCEFPDNConnection(SCEFPDNConnection connection) 
	{
		connection.setInstance(0);
		this.scefPDNConnection=connection;
	}

	@Override
	public MSISDN getMSISDN() 
	{
		return this.msisdn;
	}

	@Override
	public void setMSISDN(MSISDN msisdn) 
	{
		msisdn.setInstance(1);
		this.msisdn=msisdn;
	}

	@Override
	public PortNumber getSourceUDPPortNumber() 
	{
		return this.sourceUDPPortNumber;
	}

	@Override
	public void setSourceUDPPortNumber(PortNumber portNumber) 
	{
		portNumber.setInstance(0);
		this.sourceUDPPortNumber=portNumber;
	}

	@Override
	public ServingPLMNRateControl getServingPLMNControlRate() 
	{
		return this.servingPLMNControlRate;
	}

	@Override
	public void setServingPLMNControlRate(ServingPLMNRateControl plmnControlRate) 
	{
		plmnControlRate.setInstance(0);
		this.servingPLMNControlRate=plmnControlRate;
	}
}