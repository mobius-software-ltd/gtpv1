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
import com.mobius.software.telco.protocols.gtp.api.headers.EnhancedNSAPI;
import com.mobius.software.telco.protocols.gtp.api.headers.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.MBMSProtocolConfigurationOptions;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifierControlPane;
import com.mobius.software.telco.protocols.gtp.api.messages.DeleteMBMSContextRequest;

public class DeleteMBMSContextRequestImpl extends AbstractGTPMessage implements DeleteMBMSContextRequest
{
	private IMSI imsi;
	private TunnerEndpointIdentifierControlPane controlTEI;
	private EndUserAddress endUserAddress;
	private APN apn;
	private MBMSProtocolConfigurationOptions protocolConfigurationOptions;
	private EnhancedNSAPI enhancedNSAPI;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public MessageType getMessageType() 
	{
		return MessageType.DELETE_MBMS_CONTEXT_REQUEST;
	}

	@Override
	public void applyTLV(TLV1 tlv) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case IMSI:
				imsi=(IMSI)tlv;
				break;
			case TEICP:
				controlTEI=(TunnerEndpointIdentifierControlPane)tlv;
				break;
			case END_USER_ADDRESS:
				endUserAddress=(EndUserAddress)tlv;
				break;
			case APN:
				apn=(APN)tlv;
				break;
			case MBMS_PROTOCOL_CONFIGURATION_OPTION:
				protocolConfigurationOptions=(MBMSProtocolConfigurationOptions)tlv;
				break;
			case ENHANCED_NSAPI:
				enhancedNSAPI=(EnhancedNSAPI)tlv;
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
		if(imsi!=null)
			output.add(imsi);
		
		if(controlTEI!=null)
			output.add(controlTEI);
	
		if(endUserAddress!=null)
			output.add(endUserAddress);
	
		if(apn!=null)
			output.add(apn);
	
		if(protocolConfigurationOptions!=null)
			output.add(protocolConfigurationOptions);
	
		if(enhancedNSAPI!=null)
			output.add(enhancedNSAPI);
		
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
	public MBMSProtocolConfigurationOptions getMBMSProtocolConfigurationOptions() 
	{
		return this.protocolConfigurationOptions;
	}

	@Override
	public void setMBMSProtocolConfigurationOptions(MBMSProtocolConfigurationOptions protocolConfigurationOptions) 
	{
		this.protocolConfigurationOptions=protocolConfigurationOptions;
	}

	@Override
	public EnhancedNSAPI getEnhancedNSAPI() 
	{
		return enhancedNSAPI;
	}

	@Override
	public void setEnhancedNSAPI(EnhancedNSAPI enhancedNSAPI) 
	{
		this.enhancedNSAPI=enhancedNSAPI;	
	}
}