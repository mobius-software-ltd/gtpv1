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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IPAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SRVCCCause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TEIDC;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TargetToSourceTransparentContainer;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.SRVCCCSToPSResponse;

public class SRVCCCSToPSResponseImpl extends AbstractGTP2Message implements SRVCCCSToPSResponse
{
	Cause cause;
	SRVCCCause srvccRejectCause;
	IPAddress sgsnAddressForControlPlane;
	TEIDC sgsnTEIDForControlPlane;
	TargetToSourceTransparentContainer targetToSourceTransparentContainer;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.SRVCC_PS_TO_CS_RESPONSE;
	}

	@Override
	public void applyTLV(TLV2 tlv) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case CAUSE:
				cause=(Cause)tlv;
				break;
			case SRVCC_CAUSE:
				srvccRejectCause=(SRVCCCause)tlv;
				break;
			case IP_ADDRESS:
				sgsnAddressForControlPlane=(IPAddress)tlv;
				break;
			case TEID_C:
				sgsnTEIDForControlPlane=(TEIDC)tlv;
				break;
			case TARGET_TO_SOURCE_TRANSPARENT_CONTAINER:
				targetToSourceTransparentContainer=(TargetToSourceTransparentContainer)tlv;
				break;
			case PRIVATE_EXTENTION:
				if(privateExtentions==null)
					privateExtentions=new ArrayList<PrivateExtention>();
				
				privateExtentions.add((PrivateExtention)tlv);
				break;
			default:
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
		
		if(srvccRejectCause!=null)
			output.add(srvccRejectCause);
		
		if(sgsnAddressForControlPlane!=null)
			output.add(sgsnAddressForControlPlane);
		
		if(sgsnTEIDForControlPlane!=null)
			output.add(sgsnTEIDForControlPlane);
		
		if(targetToSourceTransparentContainer!=null)
			output.add(targetToSourceTransparentContainer);
		
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
		return cause;
	}

	@Override
	public void setCause(Cause cause) 
	{
		cause.setInstance(0);
		this.cause=cause;
	}

	@Override
	public SRVCCCause getSRVCCRejectCause() 
	{
		return this.srvccRejectCause;
	}

	@Override
	public void setSRVCCRejectCause(SRVCCCause srvccRejectCause) 
	{
		srvccRejectCause.setInstance(0);
		this.srvccRejectCause=srvccRejectCause;
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
}