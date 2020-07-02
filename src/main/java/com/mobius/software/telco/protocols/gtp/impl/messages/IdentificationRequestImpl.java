package com.mobius.software.telco.protocols.gtp.impl.messages;
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
import com.mobius.software.telco.protocols.gtp.api.headers.GSNAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.HopCounter;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.PTMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.PTMSISignature;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.RoutingAreaIdentity;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.messages.IdentificationRequest;

public class IdentificationRequestImpl extends AbstractGTPMessage implements IdentificationRequest
{
	private RoutingAreaIdentity rai;
	private PTMSI ptmsi;
	private PTMSISignature ptmsiSignature;
	private GSNAddress sgsnAddressForSignaling;
	private HopCounter hopCounter;
	
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public MessageType getMessageType() 
	{
		return MessageType.IDENTIFICATION_REQUEST;
	}

	@Override
	public void applyTLV(TLV1 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case RAI:
				rai=(RoutingAreaIdentity)tlv;
				break;
			case PTMSI:
				ptmsi=(PTMSI)tlv;
				break;
			case PTMSI_SIGNATURE:
				ptmsiSignature=(PTMSISignature)tlv;
				break;
			case GSN_ADDRESS:
				sgsnAddressForSignaling=(GSNAddress)tlv;
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
	public List<TLV1> getTLVs() throws GTPParseException 
	{
		ArrayList<TLV1> output=new ArrayList<TLV1>();
		if(rai==null)
			throw new GTPParseException("rai is missing");
		
		output.add(rai);
		
		if(ptmsi==null)
			throw new GTPParseException("PTMSI is missing");
		
		output.add(ptmsi);
		
		if(ptmsiSignature!=null)
			output.add(ptmsiSignature);
		
		if(sgsnAddressForSignaling!=null)
			output.add(sgsnAddressForSignaling);
		
		if(hopCounter!=null)
			output.add(hopCounter);
		
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
	public RoutingAreaIdentity getRAI() 
	{
		return rai;
	}

	@Override
	public void setRAI(RoutingAreaIdentity rai) 
	{
		this.rai=rai;
	}

	@Override
	public PTMSI getPTMSI() 
	{
		return ptmsi;
	}

	@Override
	public void setPTMSI(PTMSI ptmsi) 
	{
		this.ptmsi=ptmsi;
	}

	@Override
	public PTMSISignature getPTMSISignature() 
	{
		return ptmsiSignature;
	}

	@Override
	public void setPTMSISignature(PTMSISignature signature) 
	{
		this.ptmsiSignature=signature;
	}

	@Override
	public GSNAddress getSGSNAddressForSignaling() 
	{
		return sgsnAddressForSignaling;
	}

	@Override
	public void setSGSNAddressForSignaling(GSNAddress address) 
	{
		this.sgsnAddressForSignaling=address;
	}

	@Override
	public HopCounter getHopCounter() 
	{
		return hopCounter;
	}

	@Override
	public void setHopCounter(HopCounter hopCounter) 
	{
		this.hopCounter=hopCounter;	
	}
}