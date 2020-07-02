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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CSGInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Counter;
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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MonitoringEventInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PDNConnection;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RFSPIndex;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RatType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SCEFPDNConnection;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ServingPLMNRateControl;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TraceInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UETimezone;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.ContextResponse;

public class ContextResponseImpl extends AbstractGTP2Message implements ContextResponse
{
	Cause cause;
	IMSI imsi;
	MMContext mmContext;
	PDNConnection pdnConnection;
	FTEID senderFTEIDControlPlane;
	FTEID sgwAddressForControlPlane;
	FQDN sgwNodeName;
	Indication indication;
	TraceInformation traceInformation;
	IPAddress s101Address;
	IPAddress s102Address;
	RFSPIndex subscribedRFSPIndex;
	RFSPIndex rfspIndexInUse;
	UETimezone ueTimezone;
	LDN sgsnLDN;
	MDTConfiguration mdtConfiguration;
	FQDN sgsnNodeName;
	FQDN mmeNodeName;
	CSGInformation userCSGInformation;
	MonitoringEventInformation monitoringEventInformation;
	IntegerNumber ueUsageType;
	SCEFPDNConnection scefPDNConnection;
	RatType ratType;
	ServingPLMNRateControl servingPLMNControlRate;
	Counter moExceptionData;
	IntegerNumber remainingRunningServiceGapTimer;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.CONTEXT_RESPONSE;
	}

	@Override
	public void applyTLV(TLV2 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case CAUSE:
				cause=(Cause)tlv;
				break;
			case RAT_TYPE:
				ratType=(RatType)tlv;
				break;
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
			case UE_TIMEZONE:
				ueTimezone=(UETimezone)tlv;
				break;
			case LDN:
				sgsnLDN=(LDN)tlv;
				break;
			case MDT_CONFIGURATION:
				mdtConfiguration=(MDTConfiguration)tlv;
				break;
			case USER_CSG_INFORMATION:
				userCSGInformation=(CSGInformation)tlv;
				break;
			case MONITORING_EVENT_INFORMATION:
				monitoringEventInformation=(MonitoringEventInformation)tlv;
				break;
			case INTEGER_NUMBER:
				switch(tlv.getInstance())
				{
					case 0:
						ueUsageType=(IntegerNumber)tlv;
						break;
					case 1:
						remainingRunningServiceGapTimer=(IntegerNumber)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case SCEF_PDN_CONNECTION:
				scefPDNConnection=(SCEFPDNConnection)tlv;
				break;
			case SERVING_PLMN_RATE_CONTROL:
				servingPLMNControlRate=(ServingPLMNRateControl)tlv;
				break;
			case COUNTER:
				moExceptionData=(Counter)tlv;
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
		if(cause==null)
			throw new GTPParseException("Cause not set");
		
		output.add(cause);
				
		if(imsi!=null)
			output.add(imsi);
		
		if(mmContext==null)
			throw new GTPParseException("MM Context not set");
			
		output.add(mmContext);
		
		if(pdnConnection!=null)
			output.add(pdnConnection);
		
		if(senderFTEIDControlPlane!=null)
			output.add(senderFTEIDControlPlane);
		
		if(sgwAddressForControlPlane!=null)
			output.add(sgwAddressForControlPlane);
			
		if(sgwNodeName!=null)
			output.add(sgwNodeName);
		
		if(indication!=null)
			output.add(indication);
		
		if(traceInformation!=null)
			output.add(traceInformation);
			
		if(s101Address!=null)
			output.add(s101Address);
		
		if(s102Address!=null)
			output.add(s102Address);
		
		if(subscribedRFSPIndex!=null)
			output.add(subscribedRFSPIndex);
		
		if(rfspIndexInUse!=null)
			output.add(rfspIndexInUse);
		
		if(ueTimezone!=null)
			output.add(ueTimezone);
			
		if(sgsnLDN!=null)
			output.add(sgsnLDN);
			
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
			
		if(ratType!=null)
			output.add(ratType);
		
		if(servingPLMNControlRate!=null)
			output.add(servingPLMNControlRate);
		
		if(moExceptionData!=null)
			output.add(moExceptionData);
		
		if(remainingRunningServiceGapTimer!=null)
			output.add(remainingRunningServiceGapTimer);
		
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
	public void setUEUsageType(IntegerNumber usageType) 
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

	@Override
	public Cause getCause() 
	{
		return cause;
	}

	@Override
	public void setCause(Cause cause) 
	{
		cause.setInstance(0);
		this.cause=cause;
	}

	@Override
	public RatType getRatType() 
	{
		return ratType;
	}

	@Override
	public void setRatType(RatType ratType) 
	{
		ratType.setInstance(0);
		this.ratType=ratType;
	}

	@Override
	public Counter getMOExceptionDataCounter() 
	{
		return moExceptionData;
	}

	@Override
	public void setMOExceptionDataCounter(Counter counter) 
	{
		counter.setInstance(0);
		this.moExceptionData=counter;
	}

	@Override
	public IntegerNumber getRemainingRunningServiceGAP() 
	{
		return remainingRunningServiceGapTimer;
	}

	@Override
	public void setRemainingRunningServiceGAP(IntegerNumber counter) 
	{
		counter.setInstance(1);
		this.remainingRunningServiceGapTimer=counter;
	}
}