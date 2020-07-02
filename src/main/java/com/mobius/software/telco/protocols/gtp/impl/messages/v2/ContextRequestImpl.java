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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CIoTOptimizationsSupportIndication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CompleteRequestMessage;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FQDN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GUTI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.HopCounter;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Indication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.LDN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.NodeIdentifier;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.NodeNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PLMNID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PTMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PTMSISignature;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PortNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RatType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UserLocationInformation;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.ContextRequest;

public class ContextRequestImpl extends AbstractGTP2Message implements ContextRequest
{
	IMSI imsi;
	GUTI guti;
	UserLocationInformation rai;
	PTMSI ptmsi;
	PTMSISignature ptmsiSignature;
	CompleteRequestMessage completeTAURequestMessage;
	FTEID senderFTEIDControlPlane;
	PortNumber udpSourcePort;
	RatType ratType;
	Indication indication;
	HopCounter hopCounter;
	PLMNID targetPLMNID;
	LDN sgsnLDN;
	FQDN sgsnNodeName;
	FQDN mmeNodeName;
	NodeNumber nodeNumber;
	NodeIdentifier sgsnNodeIdentifier;
	NodeIdentifier mmeNodeIdentifier;
	CIoTOptimizationsSupportIndication cioTOptimizationsSupportIndication;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.CONTEXT_REQUEST;
	}

	@Override
	public void applyTLV(TLV2 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case IMSI:
				imsi=(IMSI)tlv;
				break;
			case GUTI:
				guti=(GUTI)tlv;
				break;
			case USER_LOCATION_INFORMATION:
				switch(tlv.getInstance())
				{
					case 0:
						rai=(UserLocationInformation)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case PTMSI:
				ptmsi=(PTMSI)tlv;
				break;
			case PTMSI_SIGNATURE:
				ptmsiSignature=(PTMSISignature)tlv;
				break;
			case COMPLETE_REQUEST_MESSAGE:
				completeTAURequestMessage=(CompleteRequestMessage)tlv;
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
			case PORT_NUMBER:
				switch(tlv.getInstance())
				{
					case 0:
						udpSourcePort=(PortNumber)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case RAT_TYPE:
				ratType=(RatType)tlv;
				break;
			case INDICATION:
				indication=(Indication)tlv;
				break;
			case HOP_COUNTER:
				hopCounter=(HopCounter)tlv;
				break;
			case PLMN_ID:
				targetPLMNID=(PLMNID)tlv;
				break;
			case LDN:
				switch(tlv.getInstance())
				{
					case 0:
						sgsnLDN=(LDN)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
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
			case NODE_NUMBER:
				nodeNumber=(NodeNumber)tlv;
				break;
			case NODE_IDENTIFIER:
				switch(tlv.getInstance())
				{
					case 0:
						sgsnNodeIdentifier=(NodeIdentifier)tlv;
						break;
					case 1:
						mmeNodeIdentifier=(NodeIdentifier)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case CIOT_OPTIMIZATION_SUPPORT_INDICATION:
				cioTOptimizationsSupportIndication=(CIoTOptimizationsSupportIndication)tlv;
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
		
		if(guti!=null)
			output.add(guti);
		
		if(rai!=null)
			output.add(rai);
		
		if(ptmsi!=null)
			output.add(ptmsi);
		
		if(ptmsiSignature!=null)
			output.add(ptmsiSignature);
		
		if(completeTAURequestMessage!=null)
			output.add(completeTAURequestMessage);
		
		if(senderFTEIDControlPlane!=null)
			output.add(senderFTEIDControlPlane);
		
		if(udpSourcePort!=null)
			output.add(udpSourcePort);
		
		if(ratType!=null)
			output.add(ratType);
		
		if(indication!=null)
			output.add(indication);
		
		if(hopCounter!=null)
			output.add(hopCounter);
		
		if(targetPLMNID!=null)
			output.add(targetPLMNID);
		
		if(sgsnLDN!=null)
			output.add(sgsnLDN);
		
		if(sgsnNodeName!=null)
			output.add(sgsnNodeName);
		
		if(mmeNodeName!=null)
			output.add(mmeNodeName);
			
		if(nodeNumber!=null)
			output.add(nodeNumber);
		
		if(sgsnNodeIdentifier!=null)
			output.add(sgsnNodeIdentifier);
		
		if(mmeNodeIdentifier!=null)
			output.add(mmeNodeIdentifier);
		
		if(cioTOptimizationsSupportIndication!=null)
			output.add(cioTOptimizationsSupportIndication);
			
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
	public UserLocationInformation getRAI() 
	{
		return rai;
	}

	@Override
	public void setRAI(UserLocationInformation rai) 
	{
		rai.setInstance(0);
		this.rai=rai;
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
	public FTEID getSenderFTEIDControlPlane() 
	{
		return this.senderFTEIDControlPlane;
	}

	@Override
	public void setSenderFTEIDControlPlane(FTEID fteid) 
	{
		fteid.setInstance(0);
		this.senderFTEIDControlPlane=fteid;
	}

	@Override
	public LDN getSGSNLDN() 
	{
		return sgsnLDN;
	}

	@Override
	public void setSGSNLDN(LDN ldn) 
	{
		ldn.setInstance(0);
		this.sgsnLDN=ldn;
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

	@Override
	public PTMSI getPTMSI() 
	{
		return ptmsi;
	}

	@Override
	public void setPTMSI(PTMSI ptmsi) 
	{
		ptmsi.setInstance(0);
		this.ptmsi=ptmsi;
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
	public CompleteRequestMessage getCompleteTAURequestMessage() 
	{
		return completeTAURequestMessage;
	}

	@Override
	public void setCompleteTAURequestMessage(CompleteRequestMessage message) 
	{
		message.setInstance(0);
		this.completeTAURequestMessage=message;
	}

	@Override
	public PortNumber getUDPSourcePort() 
	{
		return this.udpSourcePort;
	}

	@Override
	public void setUDPSourcePort(PortNumber udpSourcePort) 
	{
		udpSourcePort.setInstance(0);
		this.udpSourcePort=udpSourcePort;
	}

	@Override
	public HopCounter getHopCounter() 
	{
		return hopCounter;
	}

	@Override
	public void setHopCounter(HopCounter hopCount) 
	{
		hopCount.setInstance(0);
		this.hopCounter=hopCount;
	}

	@Override
	public PLMNID getTargetPLMNID() 
	{
		return this.targetPLMNID;
	}

	@Override
	public void setTargetPLMNID(PLMNID targetPLMNID) 
	{
		targetPLMNID.setInstance(0);
		this.targetPLMNID=targetPLMNID;
	}

	@Override
	public FQDN getSGSNNodeName() 
	{
		return sgsnNodeName;
	}

	@Override
	public void setSGSNNodeName(FQDN sgsnNodeName) 
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
	public void setMMENNodeName(FQDN mmeNodeName) 
	{
		mmeNodeName.setInstance(1);
		this.mmeNodeName=mmeNodeName;
	}

	@Override
	public NodeNumber getNodeNumber() 
	{
		return this.nodeNumber;
	}

	@Override
	public void setNodeNumber(NodeNumber nodeNumber) 
	{
		nodeNumber.setInstance(0);
		this.nodeNumber=nodeNumber;
	}

	@Override
	public NodeIdentifier getSGSNNodeIdentifier() 
	{
		return this.sgsnNodeIdentifier;
	}

	@Override
	public void setSGSNNodeIdentifier(NodeIdentifier nodeIdentifier) 
	{
		nodeIdentifier.setInstance(0);
		this.sgsnNodeIdentifier=nodeIdentifier;
		
	}

	@Override
	public NodeIdentifier getMMENodeIdentifier() 
	{
		return this.mmeNodeIdentifier;
	}

	@Override
	public void setMMENodeIdentifier(NodeIdentifier mmeNodeIdentifier) 
	{
		mmeNodeIdentifier.setInstance(1);
		this.mmeNodeIdentifier=mmeNodeIdentifier;
		
	}

	@Override
	public CIoTOptimizationsSupportIndication getCIoTOptimizationsSupportIndication() 
	{
		return this.cioTOptimizationsSupportIndication;
	}

	@Override
	public void setCIoTOptimizationsSupportIndication(CIoTOptimizationsSupportIndication indication) 
	{
		indication.setInstance(0);
		this.cioTOptimizationsSupportIndication=indication;
	}
}