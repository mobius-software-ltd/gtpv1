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
import com.mobius.software.telco.protocols.gtp.api.headers.ChargingGatewayAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.ChargingID;
import com.mobius.software.telco.protocols.gtp.api.headers.GSNAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.MBMSProtocolConfigurationOptions;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifierControlPane;
import com.mobius.software.telco.protocols.gtp.api.messages.CreateMBMSContextResponse;

public class CreateMBMSContextResponseImpl extends AbstractGTPMessage implements CreateMBMSContextResponse
{
	private Cause cause;
	private Recovery recovery;
	private TunnerEndpointIdentifierControlPane controlTEI;
	private ChargingID chargingID;
	private GSNAddress ggsnAddressForSignaling;
	private GSNAddress alternateGgsnAddressForSignaling;
	private ChargingGatewayAddress chargingGatewayAddress;
	private ChargingGatewayAddress alternateChargingGatewayAddress;
	private MBMSProtocolConfigurationOptions mbmsProtocolConfigurationOptions;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public MessageType getMessageType() 
	{
		return MessageType.CREATE_MBMS_CONTEXT_RESPONSE;
	}

	@Override
	public void applyTLV(TLV1 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case CAUSE:
				cause=(Cause)tlv;
				break;
			case RECOVERY:
				recovery=(Recovery)tlv;
				break;
			case TEICP:
				controlTEI=(TunnerEndpointIdentifierControlPane)tlv;
				break;
			case CHARGING_ID:
				chargingID=(ChargingID)tlv;
				break;
			case GSN_ADDRESS:
				if(ggsnAddressForSignaling==null)
					ggsnAddressForSignaling=(GSNAddress)tlv;
				else if(alternateGgsnAddressForSignaling==null)
					alternateGgsnAddressForSignaling=(GSNAddress)tlv;
				else
					throw new GTPParseException("Unknown TLV received,type:" + tlv.getElementType());
				break;
			case CHARGING_GATEWAY_ADDRESS:
				if(chargingGatewayAddress==null)
					chargingGatewayAddress=(ChargingGatewayAddress)tlv;
				else if(alternateChargingGatewayAddress==null)
					alternateChargingGatewayAddress=(ChargingGatewayAddress)tlv;
				else
					throw new GTPParseException("Unknown TLV received,type:" + tlv.getElementType());
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
		if(cause==null)
			throw new GTPParseException("cause is missing");
		
		output.add(cause);
	
		if(recovery!=null)
			output.add(recovery);
	
		if(controlTEI!=null)
			output.add(controlTEI);
	
		if(chargingID!=null)
			output.add(chargingID);
	
		if(ggsnAddressForSignaling!=null)
			output.add(ggsnAddressForSignaling);
	
		if(alternateGgsnAddressForSignaling!=null)
			output.add(alternateGgsnAddressForSignaling);
	
		if(chargingGatewayAddress!=null)
			output.add(chargingGatewayAddress);
	
		if(alternateChargingGatewayAddress!=null)
			output.add(alternateChargingGatewayAddress);
	
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
	public GSNAddress getAlternateGGSNAddressForSignaling() 
	{
		return alternateGgsnAddressForSignaling;
	}

	@Override
	public void setAlternateGGSNAddressForSignaling(GSNAddress address) 
	{
		this.alternateGgsnAddressForSignaling=address;
	}

	@Override
	public ChargingID getChargingID() 
	{
		return this.chargingID;
	}

	@Override
	public void setChargingID(ChargingID chargingID) 
	{
		this.chargingID=chargingID;
	}

	@Override
	public ChargingGatewayAddress getChargingGatewayAddress() 
	{
		return this.chargingGatewayAddress;
	}

	@Override
	public void setChargingGatewayAddress(ChargingGatewayAddress address) 
	{
		this.chargingGatewayAddress=address;
	}

	@Override
	public ChargingGatewayAddress getAlternateChargingGatewayAddress() 
	{
		return alternateChargingGatewayAddress;
	}

	@Override
	public void setAlternateChargingGatewayAddress(ChargingGatewayAddress address) 
	{
		this.alternateChargingGatewayAddress=address;
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