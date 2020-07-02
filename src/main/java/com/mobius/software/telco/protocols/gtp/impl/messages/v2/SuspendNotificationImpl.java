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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPSBearerID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.HopCounter;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IPAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.NodeType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PTMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PortNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UserLocationInformation;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.SuspendNotification;

public class SuspendNotificationImpl extends AbstractGTP2Message implements SuspendNotification
{
	IMSI imsi;
	UserLocationInformation rai;
	EPSBearerID linkedEPSBearerID;
	PTMSI ptmsi;
	NodeType originatingNode;
	IPAddress addressForControlPane;
	PortNumber udpSourcePort;
	HopCounter hopCounter;
	FTEID senderFTEIDControlPlane;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.SUSPEND_NOTIFICATION;
	}

	@Override
	public void applyTLV(TLV2 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case IMSI:
				imsi=(IMSI)tlv;
				break;
			case EPS_BEARER_ID:
				linkedEPSBearerID=(EPSBearerID)tlv;
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
			case NODE_TYPE:
				originatingNode=(NodeType)tlv;
				break;
			case IP_ADDRESS:
				addressForControlPane=(IPAddress)tlv;
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
			case HOP_COUNTER:
				hopCounter=(HopCounter)tlv;
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
		
		if(rai!=null)
			output.add(rai);
		
		if(linkedEPSBearerID!=null)
			output.add(linkedEPSBearerID);
		
		if(ptmsi!=null)
			output.add(ptmsi);
		
		if(originatingNode!=null)
			output.add(originatingNode);
		
		if(addressForControlPane!=null)
			output.add(addressForControlPane);
		
		if(udpSourcePort!=null)
			output.add(udpSourcePort);
		
		if(hopCounter!=null)
			output.add(hopCounter);
		
		if(senderFTEIDControlPlane!=null)
			output.add(senderFTEIDControlPlane);
		
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
	public EPSBearerID getLinkedEPSBearerID() 
	{
		return linkedEPSBearerID;
	}

	@Override
	public void setLinkedEPSBearerID(EPSBearerID linkedEPSBearerID) 
	{
		linkedEPSBearerID.setInstance(0);
		this.linkedEPSBearerID=linkedEPSBearerID;
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
	public NodeType getOriginatingNode() 
	{
		return originatingNode;
	}

	@Override
	public void setOriginatingNode(NodeType originatingNode) 
	{
		originatingNode.setInstance(0);
		this.originatingNode=originatingNode;
	}

	@Override
	public IPAddress getAddressForControlPane() 
	{
		return addressForControlPane;
	}

	@Override
	public void setAddressForControlPane(IPAddress address) 
	{
		address.setInstance(0);
		this.addressForControlPane=address;
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
}