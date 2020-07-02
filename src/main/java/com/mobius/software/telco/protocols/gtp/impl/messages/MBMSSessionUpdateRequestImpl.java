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
import com.mobius.software.telco.protocols.gtp.api.headers.APN;
import com.mobius.software.telco.protocols.gtp.api.headers.EndUserAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.GSNAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.MBMSFlowIdentifier;
import com.mobius.software.telco.protocols.gtp.api.headers.MBMSServiceArea;
import com.mobius.software.telco.protocols.gtp.api.headers.MBMSSessionDuration;
import com.mobius.software.telco.protocols.gtp.api.headers.MBMSSessionIdentifier;
import com.mobius.software.telco.protocols.gtp.api.headers.MBMSSessionRepetionNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.headers.TMGI;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifierControlPane;
import com.mobius.software.telco.protocols.gtp.api.messages.MBMSSessionUpdateRequest;

public class MBMSSessionUpdateRequestImpl extends AbstractGTPMessage implements MBMSSessionUpdateRequest
{
	private TunnerEndpointIdentifierControlPane controlTEI;
	private EndUserAddress endUserAddress;
	private APN apn;
	private GSNAddress ggsnAddressForSignaling;
	private TMGI tmgi;
	private MBMSSessionDuration mbmsSessionDuration;
	private MBMSServiceArea mbmsServiceArea;
	private MBMSSessionIdentifier mbmsSessionIdentifier;
	private MBMSSessionRepetionNumber mbmsSessionRepetionNumber;
	private MBMSFlowIdentifier mbmsFlowIdentifier;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public MessageType getMessageType() 
	{
		return MessageType.MBMS_SESSION_UPDATE_REQUEST;
	}

	@Override
	public void applyTLV(TLV1 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case TEICP:
				controlTEI=(TunnerEndpointIdentifierControlPane)tlv;
				break;
			case END_USER_ADDRESS:
				endUserAddress=(EndUserAddress)tlv;
				break;
			case APN:
				apn=(APN)tlv;
				break;
			case GSN_ADDRESS:
				ggsnAddressForSignaling=(GSNAddress)tlv;
				break;
			case TMGI:
				tmgi=(TMGI)tlv;
				break;
			case MBMS_SESSION_DURATION:
				mbmsSessionDuration=(MBMSSessionDuration)tlv;
				break;
			case MBMS_SERVICE_AREA:
				mbmsServiceArea=(MBMSServiceArea)tlv;
				break;
			case MBMS_SESSION_IDENTIFIER:
				mbmsSessionIdentifier=(MBMSSessionIdentifier)tlv;
				break;
			case MBMS_SESSION_REPETITION_NUMBER:
				mbmsSessionRepetionNumber=(MBMSSessionRepetionNumber)tlv;
				break;
			case MBMS_FLOW_IDENTIFIER:
				mbmsFlowIdentifier=(MBMSFlowIdentifier)tlv;
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
		if(controlTEI!=null)
			output.add(controlTEI);
	
		if(endUserAddress==null)
			throw new GTPParseException("End user address is not set");
		
		output.add(endUserAddress);
	
		if(apn==null)
			throw new GTPParseException("apn is not set");
		
		output.add(apn);
	
		if(ggsnAddressForSignaling!=null)
			output.add(ggsnAddressForSignaling);
	
		if(tmgi==null)
			throw new GTPParseException("TMGI is not set");
	
		output.add(tmgi);
	
		if(mbmsSessionDuration==null)
			throw new GTPParseException("MBMS Session Duration is not set");
	
		output.add(mbmsSessionDuration);
	
		if(mbmsServiceArea==null)
			throw new GTPParseException("MBMS Service area is not set");
	
		output.add(mbmsServiceArea);
	
		if(mbmsSessionIdentifier!=null)
			output.add(mbmsSessionIdentifier);
	
		if(mbmsSessionRepetionNumber!=null)
			output.add(mbmsSessionRepetionNumber);
		
		if(mbmsFlowIdentifier!=null)
			output.add(mbmsFlowIdentifier);
		
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
	public TunnerEndpointIdentifierControlPane getControlPaneTEI() 
	{
		return controlTEI;
	}

	@Override
	public void setControlPageTEI(TunnerEndpointIdentifierControlPane tei) 
	{
		this.controlTEI=tei;
	}

	@Override
	public EndUserAddress getEndUserAddress() 
	{
		return endUserAddress;
	}

	@Override
	public void setEndUserAddress(EndUserAddress address)
	{
		this.endUserAddress=address;
	}

	@Override
	public APN getAPN() 
	{
		return apn;
	}

	@Override
	public void setAPN(APN apn)
	{
		this.apn=apn;
	}
	
	@Override
	public GSNAddress getGGSNAddressForSignaling() 
	{
		return ggsnAddressForSignaling;
	}

	@Override
	public void setGGSNAddressForSignaling(GSNAddress address) 
	{
		this.ggsnAddressForSignaling=address;
	}

	@Override
	public TMGI getTMGI() 
	{
		return this.tmgi;
	}

	@Override
	public void setTMGI(TMGI tmgi) 
	{
		this.tmgi=tmgi;
	}

	@Override
	public MBMSSessionDuration getMBMSSessionDuration() 
	{
		return this.mbmsSessionDuration;
	}

	@Override
	public void setMBMSSessionDuration(MBMSSessionDuration mbmsSessionDuration) 
	{
		this.mbmsSessionDuration=mbmsSessionDuration;
	}

	@Override
	public MBMSServiceArea getMBMSServiceArea() 
	{
		return this.mbmsServiceArea;
	}

	@Override
	public void setMBMSServiceArea(MBMSServiceArea serviceArea) 
	{
		this.mbmsServiceArea=serviceArea;
	}

	@Override
	public MBMSSessionIdentifier getMBMSSessionIdentifier() 
	{
		return this.mbmsSessionIdentifier;
	}

	@Override
	public void setMBMSSessionIdentifier(MBMSSessionIdentifier sessionIdentifier) 
	{
		this.mbmsSessionIdentifier=sessionIdentifier;
	}

	@Override
	public MBMSSessionRepetionNumber getMBMSSessionRepetionNumber() 
	{
		return this.mbmsSessionRepetionNumber;
	}

	@Override
	public void setMBMSSessionRepetionNumber(MBMSSessionRepetionNumber sessionRepetititionNumber) 
	{
		this.mbmsSessionRepetionNumber=sessionRepetititionNumber;
	}

	@Override
	public MBMSFlowIdentifier getMBMSFlowIdentifier() 
	{
		return this.mbmsFlowIdentifier;
	}

	@Override
	public void setMBMSFlowIdentifier(MBMSFlowIdentifier mbmsFlowIdentifier) 
	{
		this.mbmsFlowIdentifier=mbmsFlowIdentifier;
	}	
}