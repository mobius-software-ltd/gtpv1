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
import com.mobius.software.telco.protocols.gtp.api.headers.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.GSNAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.MSNotReachableReason;
import com.mobius.software.telco.protocols.gtp.api.headers.MapCause;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.messages.SendRoutingInfoForGPRSResponse;

public class SendRoutingInfoForGPRSResponseImpl extends AbstractGTPMessage implements SendRoutingInfoForGPRSResponse
{
	private Cause cause;
	private IMSI imsi;
	private MapCause mapCause;
	private MSNotReachableReason reason;
	private GSNAddress gsnAddress;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public MessageType getMessageType() 
	{
		return MessageType.SEND_ROUTING_INFORMATION_FOR_GPRS_RESPONSE;
	}

	@Override
	public void applyTLV(TLV1 tlv) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case CAUSE:
				cause=(Cause)tlv;
				break;
			case IMSI:
				imsi=(IMSI)tlv;
				break;
			case MAP_CAUSE:
				mapCause=(MapCause)tlv;
				break;
			case MS_NOT_REACHABLE_REASON:
				reason=(MSNotReachableReason)tlv;
				break;
			case GSN_ADDRESS:
				gsnAddress=(GSNAddress)tlv;
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
	public List<TLV1> getTLVs() throws GTPParseException 
	{
		ArrayList<TLV1> output=new ArrayList<TLV1>();
		if(cause==null)
			throw new GTPParseException("cause is missing");
		
		output.add(cause);
		
		if(imsi==null)
			throw new GTPParseException("imsi is missing");
		
		output.add(imsi);
		
		if(mapCause!=null)
			output.add(mapCause);
	
		if(reason!=null)
			output.add(reason);
	
		if(gsnAddress!=null)
			output.add(gsnAddress);
		
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
		this.cause=cause;
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
	public MapCause getMapCause() 
	{
		return mapCause;
	}

	@Override
	public void setMapCause(MapCause mapCause) 
	{
		this.mapCause=mapCause;
	}

	@Override
	public MSNotReachableReason getMSNotReachableReason() 
	{
		return this.reason;
	}

	@Override
	public void setMSNotReachableReason(MSNotReachableReason reason) 
	{
		this.reason=reason;
	}

	@Override
	public GSNAddress getGSNAddress() 
	{
		return gsnAddress;
	}

	@Override
	public void setGSNAddress(GSNAddress gsnAddress) 
	{
		this.gsnAddress=gsnAddress;
	}
}