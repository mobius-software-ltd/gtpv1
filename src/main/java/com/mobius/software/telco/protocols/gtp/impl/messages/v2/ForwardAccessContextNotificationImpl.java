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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FContainer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PDUNumbers;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RABContext;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SourceRNCPDCPContextInfo;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.ForwardAccessContextNotification;

public class ForwardAccessContextNotificationImpl extends AbstractGTP2Message implements ForwardAccessContextNotification
{
	RABContext rabContext;
	SourceRNCPDCPContextInfo sourceRNCPDCPContextInfo;
	PDUNumbers pduNumbers;
	FContainer eutranTransparentContainer;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.FORWARD_ACCESS_CONTEXT_NOTIFICATION;
	}

	@Override
	public void applyTLV(TLV2 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case RAB_CONTEXT:
				rabContext=(RABContext)tlv;
				break;
			case SOURCE_RNC_PDCP_CONTEXT_INFO:
				sourceRNCPDCPContextInfo=(SourceRNCPDCPContextInfo)tlv;
				break;
			case PDU_NUMBERS:
				pduNumbers=(PDUNumbers)tlv;
				break;
			case FCONTAINER:
				eutranTransparentContainer=(FContainer)tlv;
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
		if(rabContext!=null)
			output.add(rabContext);
		
		if(sourceRNCPDCPContextInfo!=null)
			output.add(sourceRNCPDCPContextInfo);
		
		if(pduNumbers!=null)
			output.add(pduNumbers);
		
		if(eutranTransparentContainer!=null)
			output.add(eutranTransparentContainer);
		
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
	public RABContext getRABContext() 
	{
		return this.rabContext;
	}

	@Override
	public void setRABContext(RABContext rabContext) 
	{
		rabContext.setInstance(0);
		this.rabContext=rabContext;
	}

	@Override
	public SourceRNCPDCPContextInfo getSourceRNCPDCPContextInfo() 
	{
		return this.sourceRNCPDCPContextInfo;
	}

	@Override
	public void setSourceRNCPDCPContextInfo(SourceRNCPDCPContextInfo contextInfo) 
	{
		contextInfo.setInstance(0);
		sourceRNCPDCPContextInfo=contextInfo;
	}

	@Override
	public PDUNumbers getPDUNumbers() 
	{
		return this.pduNumbers;
	}

	@Override
	public void setPDUNumbers(PDUNumbers pduNumbers) 
	{
		pduNumbers.setInstance(0);
		this.pduNumbers=pduNumbers;
	}

	@Override
	public FContainer getEUTRANTransparentContainer() 
	{
		return eutranTransparentContainer;
	}

	@Override
	public void setEUTRANTransparentContainer(FContainer container) 
	{
		container.setInstance(0);
		this.eutranTransparentContainer=container;
	}
}