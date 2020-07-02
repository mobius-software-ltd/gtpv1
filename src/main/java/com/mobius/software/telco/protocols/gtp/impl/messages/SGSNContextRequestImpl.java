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
import com.mobius.software.telco.protocols.gtp.api.headers.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.MSValidated;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.PTMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.PTMSISignature;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.RatType;
import com.mobius.software.telco.protocols.gtp.api.headers.RoutingAreaIdentity;
import com.mobius.software.telco.protocols.gtp.api.headers.SGSNNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.TLLI;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifierControlPane;
import com.mobius.software.telco.protocols.gtp.api.messages.SGSNContextRequest;

public class SGSNContextRequestImpl extends AbstractGTPMessage implements SGSNContextRequest
{
	private IMSI imsi;
	private RoutingAreaIdentity rai;
	private TLLI tlli;
	private PTMSI ptmsi;
	private PTMSISignature ptmsiSignature;
	private MSValidated msValidated;
	private TunnerEndpointIdentifierControlPane teicp;
	private GSNAddress sgsnAddressForSignaling;
	private GSNAddress alternateSGSNAddressForSignaling;
	private SGSNNumber sgsnNumber;
	private RatType ratType;
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
			case IMSI:
				imsi=(IMSI)tlv;
				break;
			case RAI:
				rai=(RoutingAreaIdentity)tlv;
				break;
			case TLLI:
				tlli=(TLLI)tlv;
				break;
			case PTMSI:
				ptmsi=(PTMSI)tlv;
				break;
			case PTMSI_SIGNATURE:
				ptmsiSignature=(PTMSISignature)tlv;
				break;
			case MS_VALIDATED:
				msValidated=(MSValidated)tlv;
				break;
			case TEICP:
				teicp=(TunnerEndpointIdentifierControlPane)tlv;
				break;
			case GSN_ADDRESS:
				if(sgsnAddressForSignaling==null)
					sgsnAddressForSignaling=(GSNAddress)tlv;
				else if(alternateSGSNAddressForSignaling==null)
					alternateSGSNAddressForSignaling=(GSNAddress)tlv;
				else
					throw new GTPParseException("Unknown TLV received,type:" + tlv.getElementType());
				break;
			case SGSN_NUMBER:
				sgsnNumber=(SGSNNumber)tlv;
				break;
			case RAT_TYPE:
				ratType=(RatType)tlv;
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
		if(imsi!=null)
			output.add(imsi);
		
		if(rai==null)
			throw new GTPParseException("rai is missing");
		
		output.add(rai);
		
		if(tlli!=null)
			output.add(tlli);
		
		if(ptmsi!=null)
			output.add(ptmsi);
		
		if(ptmsiSignature!=null)
			output.add(ptmsiSignature);
		
		if(msValidated!=null)
			output.add(msValidated);
		
		if(teicp==null)
			throw new GTPParseException("TEICP is missing");
			
		output.add(teicp);
		
		if(sgsnAddressForSignaling==null)
			throw new GTPParseException("SGSN Address for signalling is missing");
			
		output.add(sgsnAddressForSignaling);
		
		if(alternateSGSNAddressForSignaling!=null)
			output.add(alternateSGSNAddressForSignaling);
		
		if(sgsnNumber!=null)
			output.add(sgsnNumber);
		
		if(ratType!=null)
			output.add(ratType);
		
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

	@Override
	public IMSI getIMSI() 
	{
		return imsi;
	}

	@Override
	public void setIMSI(IMSI imsi) 
	{
		this.imsi=imsi;
	}

	@Override
	public TLLI getTLLI() 
	{
		return this.tlli;
	}

	@Override
	public void setTLLI(TLLI tlli) 
	{
		this.tlli=tlli;
	}

	@Override
	public MSValidated getMSValidated() 
	{
		return this.msValidated;
	}

	@Override
	public void setMSValidated(MSValidated validate) 
	{
		this.msValidated=validate;
	}

	@Override
	public TunnerEndpointIdentifierControlPane getTEICP() 
	{
		return teicp;
	}

	@Override
	public void setTEICP(TunnerEndpointIdentifierControlPane teicp) 
	{
		this.teicp=teicp;
	}

	@Override
	public GSNAddress getAlternateSGSNAddressForSignaling() 
	{
		return alternateSGSNAddressForSignaling;
	}

	@Override
	public void setAlternateSGSNAddressForSignaling(GSNAddress address) 
	{
		this.alternateSGSNAddressForSignaling=address;
	}

	@Override
	public SGSNNumber getSGSNNumber() 
	{
		return this.sgsnNumber;
	}

	@Override
	public void setSGSNNumber(SGSNNumber sgsnNumber) 
	{
		this.sgsnNumber=sgsnNumber;		
	}

	@Override
	public RatType getRatType() 
	{
		return ratType;
	}

	@Override
	public void setRatType(RatType ratType) 
	{
		this.ratType=ratType;
	}
}