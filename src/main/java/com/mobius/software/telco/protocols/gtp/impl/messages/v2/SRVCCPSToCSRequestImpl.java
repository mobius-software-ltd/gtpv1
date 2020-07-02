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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.AllocationRetentionPriority;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IPAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MEI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MMContextEUTRAN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MMContextUTRAN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MSISDN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PLMNID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.STNSR;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SVFlags;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ServiceAreaIdentifier;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SourceToTargetTransparentContainer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TEIDC;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TargetCellID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TargetRNCID;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.SRVCCPSToCSRequest;

public class SRVCCPSToCSRequestImpl extends AbstractGTP2Message implements SRVCCPSToCSRequest
{
	IMSI imsi;
	MEI mei;
	SVFlags svFlags;
	IPAddress sgsnAddressForControlPlane;
	TEIDC sgsnTEIDForControlPlane;
	MSISDN cmsisdn;
	STNSR stnSR;
	MMContextEUTRAN mmContextEUTRAN;
	MMContextUTRAN mmContextUTRAN;
	SourceToTargetTransparentContainer sourceToTargetTransparentContainer;
	TargetRNCID targetRNCID;
	TargetCellID targetCellID;
	ServiceAreaIdentifier sourceSAI;
	AllocationRetentionPriority allocationRetentionPriority;
	PLMNID anchorPLMNID;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.SRVCC_PS_TO_CS_REQUEST;
	}

	@Override
	public void applyTLV(TLV2 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case IMSI:
				imsi=(IMSI)tlv;
				break;
			case MSISDN:
				cmsisdn=(MSISDN)tlv;
				break;
			case MEI:
				mei=(MEI)tlv;
				break;
			case SV_FLAGS:
				svFlags=(SVFlags)tlv;
				break;
			case IP_ADDRESS:
				sgsnAddressForControlPlane=(IPAddress)tlv;
				break;
			case TEID_C:
				sgsnTEIDForControlPlane=(TEIDC)tlv;
				break;
			case STN_SR:
				stnSR=(STNSR)tlv;
				break;
			case MM_CONTEXT_FOR_EUTRAN:
				mmContextEUTRAN=(MMContextEUTRAN)tlv;
				break;
			case MM_CONTEXT_FOR_UTRAN:
				mmContextUTRAN=(MMContextUTRAN)tlv;
				break;
			case SOURCE_TO_TARGET_TRANSPARENT_CONTAINER:
				sourceToTargetTransparentContainer=(SourceToTargetTransparentContainer)tlv;
				break;
			case TARGET_RNC_ID:
				targetRNCID=(TargetRNCID)tlv;
				break;
			case TARGET_GLOBAL_CELL_ID:
				targetCellID=(TargetCellID)tlv;
				break;
			case SAI:
				sourceSAI=(ServiceAreaIdentifier)tlv;
				break;
			case ALLOCATION_RETENTION_PRIORITY:
				allocationRetentionPriority=(AllocationRetentionPriority)tlv;
				break;
			case PLMN_ID:
				anchorPLMNID=(PLMNID)tlv;
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
		
		if(mei!=null)
			output.add(mei);
		
		if(svFlags!=null)
			output.add(svFlags);
		
		if(sgsnAddressForControlPlane==null)
			throw new GTPParseException("SGSN Address not set");
		
		output.add(sgsnAddressForControlPlane);
		
		if(sgsnTEIDForControlPlane==null)
			throw new GTPParseException("SGSN TEID not set");
		
		output.add(sgsnTEIDForControlPlane);
		
		if(cmsisdn!=null)
			output.add(cmsisdn);
		
		if(stnSR!=null)
			output.add(stnSR);
		
		if(mmContextEUTRAN!=null)
			output.add(mmContextEUTRAN);
		
		if(mmContextUTRAN!=null)
			output.add(mmContextUTRAN);
		
		if(sourceToTargetTransparentContainer==null)
			throw new GTPParseException("Source to target transparent container not set");
		
		output.add(sourceToTargetTransparentContainer);
		
		if(targetRNCID!=null)
			output.add(targetRNCID);
		
		if(targetCellID!=null)
			output.add(targetCellID);
		
		if(sourceSAI!=null)
			output.add(sourceSAI);
		
		if(allocationRetentionPriority!=null)
			output.add(allocationRetentionPriority);
		
		if(anchorPLMNID!=null)
			output.add(anchorPLMNID);
		
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
		imsi.setInstance(0);
		this.imsi=imsi;
	}

	@Override
	public MSISDN getCMSISDN() 
	{
		return this.cmsisdn;
	}

	@Override
	public void setCMSISDN(MSISDN msisdn) 
	{
		msisdn.setInstance(0);
		this.cmsisdn=msisdn;
	}

	@Override
	public MEI getMEI() 
	{
		return mei;
	}

	@Override
	public void setMEI(MEI mei) 
	{
		mei.setInstance(0);
		this.mei=mei;
	}

	@Override
	public SVFlags getSVFlags() 
	{
		return svFlags;
	}

	@Override
	public void setSVFlags(SVFlags svFlags) 
	{
		svFlags.setInstance(0);
		this.svFlags=svFlags;
	}

	@Override
	public IPAddress getSGSNAddressForControlPlane() 
	{
		return sgsnAddressForControlPlane;
	}

	@Override
	public void setSGSNAddressForControlPlane(IPAddress address) 
	{		
		address.setInstance(0);
		this.sgsnAddressForControlPlane=address;
	}

	@Override
	public TEIDC getSGSNTEIDForControlPlane() 
	{
		return this.sgsnTEIDForControlPlane;
	}

	@Override
	public void setSGSNTEIDForControlPlane(TEIDC teidc) 
	{
		teidc.setInstance(0);
		this.sgsnTEIDForControlPlane=teidc;
	}

	@Override
	public STNSR getSTNSR() 
	{
		return stnSR;
	}

	@Override
	public void setSTNSR(STNSR stnSR) 
	{
		stnSR.setInstance(0);
		this.stnSR=stnSR;
	}

	@Override
	public MMContextEUTRAN getMMContextEUTRAN() 
	{
		return mmContextEUTRAN;
	}

	@Override
	public void setMMContextEUTRAN(MMContextEUTRAN mmContext) 
	{
		mmContext.setInstance(0);
		this.mmContextEUTRAN=mmContext;				
	}

	@Override
	public MMContextUTRAN getMMContextUTRAN() 
	{
		return this.mmContextUTRAN;
	}

	@Override
	public void setMMContextUTRAN(MMContextUTRAN mmContext) 
	{
		mmContext.setInstance(0);
		this.mmContextUTRAN=mmContext;
	}

	@Override
	public SourceToTargetTransparentContainer getSourceToTargetTransparentContainer() 
	{
		return this.sourceToTargetTransparentContainer;
	}

	@Override
	public void setSourceToTargetTransparentContainer(SourceToTargetTransparentContainer container) 
	{
		container.setInstance(0);
		this.sourceToTargetTransparentContainer=container;
	}

	@Override
	public TargetRNCID getTargetRNCID() 
	{
		return this.targetRNCID;
	}

	@Override
	public void setTargetRNCID(TargetRNCID targetRNCID) 
	{
		targetRNCID.setInstance(0);
		this.targetRNCID=targetRNCID;
	}

	@Override
	public TargetCellID getTargetCellID() 
	{
		return this.targetCellID;
	}

	@Override
	public void setTargetCellID(TargetCellID targetCellID) 
	{
		targetCellID.setInstance(0);
		this.targetCellID=targetCellID;
	}

	@Override
	public ServiceAreaIdentifier getSourceSAI() 
	{
		return this.sourceSAI;
	}

	@Override
	public void setSourceSAI(ServiceAreaIdentifier sourceSAI) 
	{
		sourceSAI.setInstance(0);
		this.sourceSAI=sourceSAI;
	}

	@Override
	public AllocationRetentionPriority getAllocationRetentionPriority() 
	{
		return allocationRetentionPriority;
	}

	@Override
	public void setAllocationRetentionPriority(AllocationRetentionPriority arp) 
	{
		arp.setInstance(0);
		this.allocationRetentionPriority=arp;
	}

	@Override
	public PLMNID getAnchorPLMNID() 
	{
		return anchorPLMNID;
	}

	@Override
	public void setAnchorPLMNID(PLMNID plmnID) 
	{
		plmnID.setInstance(0);
		this.anchorPLMNID=plmnID;
	}
}