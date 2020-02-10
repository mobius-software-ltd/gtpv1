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

import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.ForwardRelocationResponseBearerContext;
import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FCause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FContainer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FQDN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Indication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.LDN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.NodeIdentifier;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.NodeNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.ForwardRelocationResponse;

public class ForwardRelocationResponseImpl extends AbstractGTP2Message implements ForwardRelocationResponse
{
	Cause cause;
	FTEID senderFTEIDControlPlane;
	Indication indication;
	List<ForwardRelocationResponseBearerContext> listOfSetupBearers;
	List<ForwardRelocationResponseBearerContext> listOfSetupRABs;
	List<ForwardRelocationResponseBearerContext> listOfSetupPFCs;
	FCause s1APCause;
	FCause ranapCause;
	FCause bssGPCause;
	FContainer eutranTransparentContainer;
	FContainer utranTransparentContainer;
	FContainer bssContainer;
	LDN sgsnLDN;
	FQDN sgsnNodeName;
	FQDN mmeNodeName;
	NodeNumber sgsnNumber;
	NodeIdentifier sgsnIdentifier;
	NodeIdentifier mmeIdentifier;
	NodeNumber mmeNumber;
	NodeIdentifier sgsnIdentifierForMTSMS;
	NodeIdentifier mmeIdentifierForMTSMS;
	List<ForwardRelocationResponseBearerContext> setupBearersForSCEFPDNConnections;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.FORWARD_RELOCATION_RESPONSE;
	}

	@Override
	public void applyTLV(TLV2 tlv) throws GTPParseException 
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
						senderFTEIDControlPlane=(FTEID)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case BEARER_CONTEXT:
				switch(tlv.getInstance())
				{
					case 0:
						if(listOfSetupBearers==null)
							listOfSetupBearers=new ArrayList<ForwardRelocationResponseBearerContext>();
							
						listOfSetupBearers.add((ForwardRelocationResponseBearerContext)tlv);
						break;
					case 1:
						if(listOfSetupRABs==null)
							listOfSetupRABs=new ArrayList<ForwardRelocationResponseBearerContext>();
							
						listOfSetupRABs.add((ForwardRelocationResponseBearerContext)tlv);
						break;
					case 2:
						if(listOfSetupPFCs==null)
							listOfSetupPFCs=new ArrayList<ForwardRelocationResponseBearerContext>();
							
						listOfSetupPFCs.add((ForwardRelocationResponseBearerContext)tlv);
						break;
					case 3:
						if(setupBearersForSCEFPDNConnections==null)
							setupBearersForSCEFPDNConnections=new ArrayList<ForwardRelocationResponseBearerContext>();
							
						setupBearersForSCEFPDNConnections.add((ForwardRelocationResponseBearerContext)tlv);
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
			case FQDN:
				switch(tlv.getInstance())
				{
					case 0:
						sgsnNodeName=(FQDN)tlv;
						break;
					case 1:
						mmeNodeName=(FQDN)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
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
			case NODE_NUMBER:
				switch(tlv.getInstance())
				{
					case 0:
						sgsnNumber=(NodeNumber)tlv;
						break;
					case 1:
						mmeNumber=(NodeNumber)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case NODE_IDENTIFIER:
				switch(tlv.getInstance())
				{
					case 0:
						sgsnIdentifier=(NodeIdentifier)tlv;
						break;
					case 1:
						mmeIdentifier=(NodeIdentifier)tlv;
						break;
					case 2:
						sgsnIdentifierForMTSMS=(NodeIdentifier)tlv;
						break;
					case 3:
						mmeIdentifierForMTSMS=(NodeIdentifier)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case LDN:
				sgsnLDN=(LDN)tlv;
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
		
		if(senderFTEIDControlPlane!=null)
			output.add(senderFTEIDControlPlane);
		
		if(indication!=null)
			output.add(indication);
		
		if(listOfSetupBearers!=null && listOfSetupBearers.size()>0)
			output.addAll(listOfSetupBearers);
		
		if(listOfSetupRABs!=null && listOfSetupRABs.size()>0)
			output.addAll(listOfSetupRABs);
		
		if(listOfSetupPFCs!=null && listOfSetupPFCs.size()>0)
			output.addAll(listOfSetupPFCs);
		
		if(s1APCause!=null)
			output.add(s1APCause);
		
		if(ranapCause!=null)
			output.add(ranapCause);
		
		if(bssGPCause!=null)
			output.add(bssGPCause);
		
		if(eutranTransparentContainer!=null)
			output.add(eutranTransparentContainer);
		
		if(utranTransparentContainer!=null)
			output.add(utranTransparentContainer);
		
		if(bssContainer!=null)
			output.add(bssContainer);
		
		if(sgsnLDN!=null)
			output.add(sgsnLDN);
			
		if(sgsnNodeName!=null)
			output.add(sgsnNodeName);
			
		if(mmeNodeName!=null)
			output.add(mmeNodeName);
			
		if(sgsnNumber!=null)
			output.add(sgsnNumber);
			
		if(sgsnIdentifier!=null)
			output.add(sgsnIdentifier);
			
		if(mmeIdentifier!=null)
			output.add(mmeIdentifier);
			
		if(mmeNumber!=null)
			output.add(mmeNumber);
			
		if(sgsnIdentifierForMTSMS!=null)
			output.add(sgsnIdentifierForMTSMS);
			
		if(mmeIdentifierForMTSMS!=null)
			output.add(mmeIdentifierForMTSMS);
			
		if(setupBearersForSCEFPDNConnections!=null && setupBearersForSCEFPDNConnections.size()>0)
			output.addAll(setupBearersForSCEFPDNConnections);
			
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
		cause.setInstance(0);
		this.cause=cause;
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
	public FQDN getSGSNNodeName() 
	{
		return sgsnNodeName;
	}

	@Override
	public void setSGSNodeName(FQDN sgsnNodeName) 
	{
		sgsnNodeName.setInstance(0);
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
		mmeNodeName.setInstance(1);
		this.mmeNodeName=mmeNodeName;
	}

	@Override
	public List<ForwardRelocationResponseBearerContext> getListOfSetupBearers() 
	{
		return listOfSetupBearers;
	}

	@Override
	public void setListOfSetupBearers(List<ForwardRelocationResponseBearerContext> bearerContext) 
	{
		if(bearerContext!=null)
			for(ForwardRelocationResponseBearerContext curr:bearerContext)
				curr.setInstance(0);
		
		this.listOfSetupBearers=bearerContext;
	}

	@Override
	public List<ForwardRelocationResponseBearerContext> getListOfRABs() 
	{
		return listOfSetupRABs;
	}

	@Override
	public void setListOfSetupRABs(List<ForwardRelocationResponseBearerContext> bearerContext) 
	{
		if(bearerContext!=null)
			for(ForwardRelocationResponseBearerContext curr:bearerContext)
				curr.setInstance(1);
		
		this.listOfSetupRABs=bearerContext;
	}

	@Override
	public List<ForwardRelocationResponseBearerContext> getListOfSetupPFCs() 
	{
		return listOfSetupPFCs;
	}

	@Override
	public void setListOfSetupPFCs(List<ForwardRelocationResponseBearerContext> bearerContext) 
	{
		if(bearerContext!=null)
			for(ForwardRelocationResponseBearerContext curr:bearerContext)
				curr.setInstance(2);
		
		this.listOfSetupPFCs=bearerContext;
	}

	@Override
	public NodeNumber getSGSNNodeNumber() 
	{
		return this.sgsnNumber;
	}

	@Override
	public void setSGSNNodeNumber(NodeNumber nodeNumber) 
	{
		nodeNumber.setInstance(0);
		this.sgsnNumber=nodeNumber;
	}

	@Override
	public NodeNumber getMMENodeNumberForMTSMS() 
	{
		return this.mmeNumber;
	}

	@Override
	public void setMMENodeNumberForMTSMS(NodeNumber nodeNumber) 
	{
		nodeNumber.setInstance(1);
		this.mmeNumber=nodeNumber;
	}

	@Override
	public List<ForwardRelocationResponseBearerContext> getSetupBearersForSCEFPDNConnections() 
	{
		return this.setupBearersForSCEFPDNConnections;
	}

	@Override
	public void setSetupBearersForSCEFPDNConnections(List<ForwardRelocationResponseBearerContext> bearerContext) 
	{
		if(bearerContext!=null)
			for(ForwardRelocationResponseBearerContext curr:bearerContext)
				curr.setInstance(3);
		
		this.setupBearersForSCEFPDNConnections=bearerContext;
	}

	@Override
	public NodeIdentifier getSGSNIdentifier() 
	{
		return this.sgsnIdentifier;
	}

	@Override
	public void setSGSIdentifier(NodeIdentifier sgsnIdentifier) 
	{
		sgsnIdentifier.setInstance(0);
		this.sgsnIdentifier=sgsnIdentifier;
	}

	@Override
	public NodeIdentifier getMMEIdentifier() 
	{
		return this.mmeIdentifier;
	}

	@Override
	public void setMMEIdentifier(NodeIdentifier mmeIdentifier) 
	{
		mmeIdentifier.setInstance(1);
		this.mmeIdentifier=mmeIdentifier;
	}

	@Override
	public NodeIdentifier getSGSNIdentifierForMTSMS() 
	{
		return this.sgsnIdentifierForMTSMS;
	}

	@Override
	public void setSGSIdentifierForMTSMS(NodeIdentifier sgsnIdentifier) 
	{
		sgsnIdentifier.setInstance(2);
		this.sgsnIdentifierForMTSMS=sgsnIdentifier;
	}

	@Override
	public NodeIdentifier getMMEIdentifierForMTSMS() 
	{
		return this.mmeIdentifierForMTSMS;
	}

	@Override
	public void setMMEIdentifierForMTSMS(NodeIdentifier mmeIdentifier) 
	{
		mmeIdentifier.setInstance(3);
		this.mmeIdentifierForMTSMS=mmeIdentifier;
	}
}