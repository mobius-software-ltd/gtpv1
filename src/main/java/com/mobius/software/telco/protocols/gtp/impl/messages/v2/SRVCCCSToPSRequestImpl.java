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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PTMSISignature;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IPAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MEI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MMContextForCSToPS;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GUTI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UserLocationInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TargetToSourceTransparentContainer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TEIDC;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PTMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TargetIdentification;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.SRVCCCSToPSRequest;

public class SRVCCCSToPSRequestImpl extends AbstractGTP2Message implements SRVCCCSToPSRequest
{
	IMSI imsi;
	MEI mei;
	IPAddress mscAddressForControlPlane;
	TEIDC mscTEIDForControlPlane;
	TargetToSourceTransparentContainer targetToSourceTransparentContainer;
	TargetIdentification targetIdentification;
	PTMSI ptmsi;
	UserLocationInformation sourceRAI;
	PTMSISignature ptmsiSignature;
	GUTI guti;
	MMContextForCSToPS mmContextForCSToPS;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.SRVCC_CS_TO_PS_REQUEST;
	}

	@Override
	public void applyTLV(TLV2 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case IMSI:
				imsi=(IMSI)tlv;
				break;
			case MEI:
				mei=(MEI)tlv;
				break;
			case IP_ADDRESS:
				mscAddressForControlPlane=(IPAddress)tlv;
				break;
			case TEID_C:
				mscTEIDForControlPlane=(TEIDC)tlv;
				break;
			case MM_CONTEXT_FOR_CS_TO_PS:
				mmContextForCSToPS=(MMContextForCSToPS)tlv;
				break;
			case SOURCE_TO_TARGET_TRANSPARENT_CONTAINER:
				targetToSourceTransparentContainer=(TargetToSourceTransparentContainer)tlv;
				break;
			case TARGET_RNC_ID:
				targetIdentification=(TargetIdentification)tlv;
				break;
			case TARGET_GLOBAL_CELL_ID:
				ptmsi=(PTMSI)tlv;
				break;
			case SAI:
				sourceRAI=(UserLocationInformation)tlv;
				break;
			case ALLOCATION_RETENTION_PRIORITY:
				ptmsiSignature=(PTMSISignature)tlv;
				break;
			case PLMN_ID:
				guti=(GUTI)tlv;
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
		
		if(mscAddressForControlPlane==null)
			throw new GTPParseException("MSC Address not set");
		
		output.add(mscAddressForControlPlane);
		
		if(mscTEIDForControlPlane==null)
			throw new GTPParseException("MSC TEID not set");
		
		output.add(mscTEIDForControlPlane);
		
		if(targetToSourceTransparentContainer==null)
			throw new GTPParseException("Target to source transparent container not set");
		
		output.add(targetToSourceTransparentContainer);
		
		if(targetIdentification==null)
			throw new GTPParseException("Target identification not set");
		
		output.add(targetIdentification);
		
		if(ptmsi!=null)
			output.add(ptmsi);
		
		if(sourceRAI!=null)
			output.add(sourceRAI);
		
		if(ptmsiSignature!=null)
			output.add(ptmsiSignature);
		
		if(guti!=null)
			output.add(guti);
		
		if(mmContextForCSToPS==null)
			throw new GTPParseException("MM context for CS to PS not set");
		
		output.add(mmContextForCSToPS);
		
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
	public IPAddress getMSCAddressForControlPlane() 
	{
		return mscAddressForControlPlane;
	}

	@Override
	public void setMSCAddressForControlPlane(IPAddress address) 
	{		
		address.setInstance(0);
		this.mscAddressForControlPlane=address;
	}

	@Override
	public TEIDC getMSCTEIDForControlPlane() 
	{
		return this.mscTEIDForControlPlane;
	}

	@Override
	public void setMSCTEIDForControlPlane(TEIDC teidc) 
	{
		teidc.setInstance(0);
		this.mscTEIDForControlPlane=teidc;
	}

	@Override
	public MMContextForCSToPS getMMContextForCSToPS() 
	{
		return mmContextForCSToPS;
	}

	@Override
	public void setMMContextForCSToPS(MMContextForCSToPS mmContext) 
	{
		mmContext.setInstance(0);
		this.mmContextForCSToPS=mmContext;				
	}

	@Override
	public TargetToSourceTransparentContainer getTargetToSourceTransparentContainer() 
	{
		return this.targetToSourceTransparentContainer;
	}

	@Override
	public void setTargetToSourceTransparentContainer(TargetToSourceTransparentContainer container) 
	{
		container.setInstance(0);
		this.targetToSourceTransparentContainer=container;
	}

	@Override
	public TargetIdentification getTargetIdentification() 
	{
		return this.targetIdentification;
	}

	@Override
	public void setTargetIdentification(TargetIdentification targetIdentification) 
	{
		targetIdentification.setInstance(0);
		this.targetIdentification=targetIdentification;
	}

	@Override
	public PTMSI getPTMSI() 
	{
		return this.ptmsi;
	}

	@Override
	public void setPTMSI(PTMSI ptmsi) 
	{
		ptmsi.setInstance(0);
		this.ptmsi=ptmsi;
	}

	@Override
	public UserLocationInformation getSourceRAI() 
	{
		return this.sourceRAI;
	}

	@Override
	public void setSourceRAI(UserLocationInformation sourceRAI) 
	{
		sourceRAI.setInstance(0);
		this.sourceRAI=sourceRAI;
	}

	@Override
	public PTMSISignature getPTMSISignature() 
	{
		return ptmsiSignature;
	}

	@Override
	public void setPTMSISignature(PTMSISignature ptmsiSignature) 
	{
		ptmsiSignature.setInstance(0);
		this.ptmsiSignature=ptmsiSignature;
	}

	@Override
	public GUTI getGUTI() 
	{
		return guti;
	}

	@Override
	public void setGUTI(GUTI guti) 
	{
		guti.setInstance(0);
		this.guti=guti;
	}
}