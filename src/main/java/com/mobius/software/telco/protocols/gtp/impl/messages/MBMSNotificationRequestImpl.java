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
import com.mobius.software.telco.protocols.gtp.api.headers.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.MBMSProtocolConfigurationOptions;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.NSAPI;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifierControlPane;
import com.mobius.software.telco.protocols.gtp.api.messages.MBMSNotificationRequest;

public class MBMSNotificationRequestImpl extends AbstractGTPMessage implements MBMSNotificationRequest
{
	private IMSI imsi;
	private TunnerEndpointIdentifierControlPane controlTEI;
	private NSAPI nsapi;
	private EndUserAddress endUserAddress;
	private APN apn;
	private GSNAddress ggsnAddressForSignaling;
	private MBMSProtocolConfigurationOptions mbmsProtocolConfigurationOptions;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public MessageType getMessageType() 
	{
		return MessageType.MBMS_NOTIFICATION_REQUEST;
	}

	@Override
	public void applyTLV(TLV1 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case IMSI:
				imsi=(IMSI)tlv;
				break;
			case TEICP:
				controlTEI=(TunnerEndpointIdentifierControlPane)tlv;
				break;
			case NSAPI:
				nsapi=(NSAPI)tlv;
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
			case MBMS_PROTOCOL_CONFIGURATION_OPTION:
				mbmsProtocolConfigurationOptions=(MBMSProtocolConfigurationOptions)tlv;
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
		
		if(controlTEI==null)
			throw new GTPParseException("Control TEI is missing");
		
		output.add(controlTEI);
	
		if(nsapi==null)
			throw new GTPParseException("NSAPI is missing");
		
		output.add(nsapi);
	
		if(endUserAddress==null)
			throw new GTPParseException("End User Address is missing");
		
		output.add(endUserAddress);
	
		if(apn==null)
			throw new GTPParseException("APN is missing");
		
		output.add(apn);
	
		if(ggsnAddressForSignaling==null)
			throw new GTPParseException("ggsn address for signaling is missing");
		
		output.add(ggsnAddressForSignaling);
	
		if(mbmsProtocolConfigurationOptions!=null)
			output.add(mbmsProtocolConfigurationOptions);
	
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
		this.imsi=imsi;
	}

	@Override
	public TunnerEndpointIdentifierControlPane getControlPaneTEI() 
	{
		return this.controlTEI;
	}

	@Override
	public void setControlPageTEI(TunnerEndpointIdentifierControlPane tei) 
	{
		this.controlTEI=tei;
	}

	@Override
	public NSAPI getNSAPI() 
	{
		return this.nsapi;
	}

	@Override
	public void setNSAPI(NSAPI nsapi) 
	{
		this.nsapi=nsapi;
	}

	@Override
	public EndUserAddress getEndUserAddress() 
	{
		return this.endUserAddress;
	}

	@Override
	public void setEndUserAddress(EndUserAddress endUserAddress) 
	{
		this.endUserAddress=endUserAddress;
	}

	@Override
	public APN getAPN() 
	{
		return this.apn;
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
	public MBMSProtocolConfigurationOptions getMBMSProtocolConfigurationOptions() 
	{
		return this.mbmsProtocolConfigurationOptions;
	}

	@Override
	public void setMBMSProtocolConfigurationOptions(MBMSProtocolConfigurationOptions mbmsProtocolConfigurationOptions) 
	{
		this.mbmsProtocolConfigurationOptions=mbmsProtocolConfigurationOptions;
	}
}