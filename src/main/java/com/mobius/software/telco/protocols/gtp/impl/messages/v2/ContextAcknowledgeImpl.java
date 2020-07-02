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

import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.ContextAcknowledgeBearerContext;
import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Indication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.NodeIdentifier;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.NodeNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.ContextAcknowledge;

public class ContextAcknowledgeImpl extends AbstractGTP2Message implements ContextAcknowledge
{
	Cause cause;
	Indication indication;
	FTEID forwardingFTEID;
	List<ContextAcknowledgeBearerContext> bearerContext;
	NodeNumber sgsnNodeNumber;
	NodeNumber mtNumberForMTSMS;
	NodeIdentifier sgsnIdentifierForMTSMS;
	NodeIdentifier mmeIdentifierForMTSMS;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.CONTEXT_ACKNOWLEDGE;
	}

	@Override
	public void applyTLV(TLV2 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case CAUSE:
				cause=(Cause)tlv;
				break;
			case FTEID:
				switch(tlv.getInstance())
				{
					case 0:
						forwardingFTEID=(FTEID)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case NODE_NUMBER:
				switch(tlv.getInstance())
				{
					case 0:
						sgsnNodeNumber=(NodeNumber)tlv;
						break;
					case 1:
						sgsnNodeNumber=(NodeNumber)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case NODE_IDENTIFIER:
				switch(tlv.getInstance())
				{
					case 0:
						sgsnIdentifierForMTSMS=(NodeIdentifier)tlv;
						break;
					case 1:
						mmeIdentifierForMTSMS=(NodeIdentifier)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case INDICATION:
				indication=(Indication)tlv;
				break;
			case BEARER_CONTEXT:
				if(bearerContext==null)
					bearerContext=new ArrayList<ContextAcknowledgeBearerContext>();
				
				bearerContext.add((ContextAcknowledgeBearerContext)tlv);
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
				
		if(indication!=null)
			output.add(indication);
		
		if(forwardingFTEID!=null)
			output.add(forwardingFTEID);
		
		if(bearerContext!=null && bearerContext.size()>0)
			output.addAll(bearerContext);
		
		if(sgsnNodeNumber!=null)
			output.add(sgsnNodeNumber);
		
		if(mtNumberForMTSMS!=null)
			output.add(mtNumberForMTSMS);
		
		if(sgsnIdentifierForMTSMS!=null)
			output.add(sgsnIdentifierForMTSMS);
		
		if(mmeIdentifierForMTSMS!=null)
			output.add(mmeIdentifierForMTSMS);
		
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
	public FTEID getForwardingFTEID() 
	{
		return this.forwardingFTEID;
	}

	@Override
	public void setForwardingFTEID(FTEID forwardingFTEID) 
	{
		forwardingFTEID.setInstance(0);
		this.forwardingFTEID=forwardingFTEID;
	}

	@Override
	public List<ContextAcknowledgeBearerContext> getBearerContext() 
	{
		return this.bearerContext;
	}

	@Override
	public void setBearerContext(List<ContextAcknowledgeBearerContext> bearerContext) 
	{
		if(bearerContext!=null)
			for(ContextAcknowledgeBearerContext curr:bearerContext)
				curr.setInstance(0);	
		
		this.bearerContext=bearerContext;
	}

	@Override
	public NodeNumber getSGSNNodeNumber() 
	{
		return this.sgsnNodeNumber;
	}

	@Override
	public void setSGSNNodeNumber(NodeNumber sgsnNodeNumber) 
	{
		sgsnNodeNumber.setInstance(0);
		this.sgsnNodeNumber=sgsnNodeNumber;
	}

	@Override
	public NodeNumber getMTNumberForMTSMS() 
	{
		return this.mtNumberForMTSMS;
	}

	@Override
	public void setMTNumberForMTSMS(NodeNumber mtNumberForMTSMS) 
	{
		mtNumberForMTSMS.setInstance(1);
		this.mtNumberForMTSMS=mtNumberForMTSMS;
	}

	@Override
	public NodeIdentifier getSGSNIdentifierForMTSMS() 
	{
		return this.sgsnIdentifierForMTSMS;
	}

	@Override
	public void setSGSNIdentifierForMTSMS(NodeIdentifier sgsnIdentifierForMTSMS) 
	{
		sgsnIdentifierForMTSMS.setInstance(0);
		this.sgsnIdentifierForMTSMS=sgsnIdentifierForMTSMS;
	}

	@Override
	public NodeIdentifier getMMEIdentifierForMTSMS() 
	{
		return mmeIdentifierForMTSMS;
	}

	@Override
	public void setMMEIdentifierForMTSMS(NodeIdentifier mmeIdentifierForMTSMS) 
	{
		mmeIdentifierForMTSMS.setInstance(1);
		this.mmeIdentifierForMTSMS=mmeIdentifierForMTSMS;
	}
}