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
import com.mobius.software.telco.protocols.gtp.api.headers.MBMSDistributionAcknowledgement;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifier1;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifierControlPane;
import com.mobius.software.telco.protocols.gtp.api.messages.MBMSSessionStartResponse;

public class MBMSSessionStartResponseImpl extends AbstractGTPMessage implements MBMSSessionStartResponse
{
	private Cause cause;
	private Recovery recovery;
	private TunnerEndpointIdentifier1 tei;
	private TunnerEndpointIdentifierControlPane controlTEI;
	private GSNAddress ggsnAddressForSignaling;
	private GSNAddress ggsnAddressForTraffic;
	private GSNAddress alternateGgsnAddressForTraffic;
	private MBMSDistributionAcknowledgement mbmsDistributionAcknowledgement;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public MessageType getMessageType() 
	{
		return MessageType.MBMS_SESSION_START_RESPONSE;
	}

	@Override
	public void applyTLV(TLV1 tlv) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case CAUSE:
				cause=(Cause)tlv;
				break;
			case RECOVERY:
				recovery=(Recovery)tlv;
				break;
			case TEID_1:
				tei=(TunnerEndpointIdentifier1)tlv;
				break;
			case TEICP:
				controlTEI=(TunnerEndpointIdentifierControlPane)tlv;
				break;
			case GSN_ADDRESS:
				if(ggsnAddressForSignaling==null)
					ggsnAddressForSignaling=(GSNAddress)tlv;
				else if(ggsnAddressForTraffic==null)
					ggsnAddressForTraffic=(GSNAddress)tlv;
				else if(alternateGgsnAddressForTraffic==null)
					alternateGgsnAddressForTraffic=(GSNAddress)tlv;
				else
					throw new GTPParseException("Unknown TLV received,type:" + tlv.getElementType());
				break;
			case MBMS_DISTRIBUTION_ACKNOWLEDGMENT:
				mbmsDistributionAcknowledgement=(MBMSDistributionAcknowledgement)tlv;
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
	
		if(recovery!=null)
			output.add(recovery);
	
		if(tei!=null)
			output.add(tei);
	
		if(controlTEI!=null)
			output.add(controlTEI);
	
		if(ggsnAddressForSignaling!=null)
			output.add(ggsnAddressForSignaling);
	
		if(ggsnAddressForTraffic!=null)
			output.add(ggsnAddressForTraffic);
	
		if(alternateGgsnAddressForTraffic!=null)
			output.add(alternateGgsnAddressForTraffic);
	
		if(mbmsDistributionAcknowledgement!=null)
			output.add(mbmsDistributionAcknowledgement);
	
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
		this.cause=cause;
	}

	@Override
	public Recovery getRecovery() 
	{
		return recovery;
	}

	@Override
	public void setRecovery(Recovery recovery) 
	{
		this.recovery=recovery;
	}

	@Override
	public TunnerEndpointIdentifier1 getTEI() 
	{
		return tei;
	}

	@Override
	public void setTEI(TunnerEndpointIdentifier1 tei) 
	{
		this.tei=tei;	
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
	public GSNAddress getGGSNAddressForTraffic() 
	{
		return ggsnAddressForTraffic;
	}

	@Override
	public void setGGSNAddressForTraffic(GSNAddress address) 
	{
		this.ggsnAddressForTraffic=address;
	}

	@Override
	public GSNAddress getAlternateGGSNAddressForTraffic() 
	{
		return alternateGgsnAddressForTraffic;
	}

	@Override
	public void setAlternateGGSNAddressForTraffic(GSNAddress address) 
	{
		this.alternateGgsnAddressForTraffic=address;
	}

	@Override
	public MBMSDistributionAcknowledgement getMBMSDistributionAcknowledgement() 
	{
		return mbmsDistributionAcknowledgement;
	}

	@Override
	public void setMBMSDistributionAcknowledgement(MBMSDistributionAcknowledgement acknowledgement) 
	{
		this.mbmsDistributionAcknowledgement=acknowledgement;
	}
}