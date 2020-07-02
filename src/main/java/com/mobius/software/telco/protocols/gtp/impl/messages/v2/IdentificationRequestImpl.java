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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CompleteRequestMessage;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GUTI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.HopCounter;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IPAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PLMNID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PTMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PTMSISignature;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PortNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UserLocationInformation;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.IdentificationRequest;

public class IdentificationRequestImpl extends AbstractGTP2Message implements IdentificationRequest
{
	GUTI guti;
	UserLocationInformation rai;
	PTMSI ptmsi;
	PTMSISignature ptmsiSignature;
	CompleteRequestMessage completeAttachRequestMessage;
	IPAddress addressForControlPane;
	PortNumber udpSourcePort;
	HopCounter hopCounter;
	PLMNID targetPLMNID;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.IDENTIFICATION_REQUEST;
	}

	@Override
	public void applyTLV(TLV2 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
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
				completeAttachRequestMessage=(CompleteRequestMessage)tlv;
				break;
			case IP_ADDRESS:
				addressForControlPane=(IPAddress)tlv;
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
			case HOP_COUNTER:
				hopCounter=(HopCounter)tlv;
				break;
			case PLMN_ID:
				targetPLMNID=(PLMNID)tlv;
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
		if(guti!=null)
			output.add(guti);
		
		if(rai!=null)
			output.add(rai);
		
		if(ptmsi!=null)
			output.add(ptmsi);
		
		if(ptmsiSignature!=null)
			output.add(ptmsiSignature);
		
		if(completeAttachRequestMessage!=null)
			output.add(completeAttachRequestMessage);
		
		if(addressForControlPane!=null)
			output.add(addressForControlPane);
		
		if(udpSourcePort!=null)
			output.add(udpSourcePort);
		
		if(hopCounter!=null)
			output.add(hopCounter);
		
		if(targetPLMNID!=null)
			output.add(targetPLMNID);
		
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
	public CompleteRequestMessage getCompleteAttachRequestMessage() 
	{
		return completeAttachRequestMessage;
	}

	@Override
	public void setCompleteAttachRequestMessage(CompleteRequestMessage message) 
	{
		message.setInstance(0);
		this.completeAttachRequestMessage=message;
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
	public IPAddress getAddressForControlPane() 
	{
		return addressForControlPane;
	}

	@Override
	public void setAddressForControlPane(IPAddress addressForControlPane) 
	{
		addressForControlPane.setInstance(0);
		this.addressForControlPane=addressForControlPane;
	}
}