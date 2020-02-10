package com.mobius.software.telco.protocols.gtp.impl.bcontexts.v2;
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

import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.UpdateBearerRequestBearerContextToBeModified;
import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.AdditionalProtocolConfigurationOption;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.BearerFlags;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.BearerQos;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPSBearerID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ExtendedProtocolConfigurationOptions;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MaximumPacketLossRate;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ProtocolConfigurationOption;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TFT;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.impl.headers.v2.BearerContextImpl;

public class UpdateBearerRequestBearerContextToBeModifiedImpl extends BearerContextImpl implements UpdateBearerRequestBearerContextToBeModified
{
	private EPSBearerID bearerID;
	private TFT tft;
	private BearerQos bearerQos;
	private BearerFlags bearerFlags;
	private ProtocolConfigurationOption protocolConfigurationOption;
	private AdditionalProtocolConfigurationOption additionalProtocolConfigurationOption;
	private ExtendedProtocolConfigurationOptions extendedProtocolConfigurationOption;
	private MaximumPacketLossRate maximumPacketLossRate;
	
	@Override
	public void applyTLV(TLV2 tlv) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case EPS_BEARER_ID:
				bearerID=(EPSBearerID)tlv;
				break;
			case BEARER_TFT:
				tft=(TFT)tlv;
				break;
			case BEARER_QOS:
				bearerQos=(BearerQos)tlv;
				break;
			case BEARER_FLAGS:
				bearerFlags=(BearerFlags)tlv;
				break;
			case PROTOCOL_CONFIGURATION_OPTIONS:
				protocolConfigurationOption=(ProtocolConfigurationOption)tlv;
				break;
			case ADDITIONAL_PROTOCOL_CONFIGURATION_OPTIONS:
				additionalProtocolConfigurationOption=(AdditionalProtocolConfigurationOption)tlv;
				break;
			case EXTENDED_PROTOCOL_CONFIGURATION_OPTIONS:
				extendedProtocolConfigurationOption=(ExtendedProtocolConfigurationOptions)tlv;
				break;
			case MAXIMUM_PACKET_LOSS_RATE:
				maximumPacketLossRate=(MaximumPacketLossRate)tlv;
				break;
			default:
				throw new GTPParseException("Unknown TLV received,type:" + tlv.getElementType());
		}
	}

	@Override
	public List<TLV2> getTLVs() throws GTPParseException 
	{
		ArrayList<TLV2> output=new ArrayList<TLV2>();
		if(bearerID==null)
			throw new GTPParseException("Bearer ID not set");
		
		output.add(bearerID);
		
		if(tft!=null)
			output.add(tft);
		
		if(bearerQos!=null)
			output.add(bearerQos);
		
		if(bearerFlags!=null)
			output.add(bearerFlags);
		
		if(protocolConfigurationOption!=null)
			output.add(protocolConfigurationOption);
		
		if(additionalProtocolConfigurationOption!=null)
			output.add(additionalProtocolConfigurationOption);
		
		if(extendedProtocolConfigurationOption!=null)
			output.add(extendedProtocolConfigurationOption);
		
		if(maximumPacketLossRate!=null)
			output.add(maximumPacketLossRate);
		
		return output;
	}
	
	@Override
	public EPSBearerID getEPSBearerID() 
	{
		return bearerID;
	}

	@Override
	public void setEPSBearerID(EPSBearerID bearerID) 
	{
		bearerID.setInstance(0);
		this.bearerID=bearerID;
	}

	@Override
	public TFT getTFT() 
	{
		return tft;
	}

	@Override
	public void setTFT(TFT tft) 
	{
		tft.setInstance(0);
		this.tft=tft;
	}
	
	@Override
	public BearerQos getBearerQos() 
	{
		return bearerQos;
	}

	@Override
	public void setBearerQos(BearerQos bearerQos) 
	{
		bearerQos.setInstance(0);
		this.bearerQos=bearerQos;
	}

	@Override
	public BearerFlags getBearerFlags() 
	{
		return bearerFlags;
	}

	@Override
	public void setBearerFlags(BearerFlags bearerFlags) 
	{
		bearerFlags.setInstance(0);
		this.bearerFlags=bearerFlags;
	}

	@Override
	public ProtocolConfigurationOption getProtocolConfigurationOption() 
	{
		return protocolConfigurationOption;
	}

	@Override
	public void setProtocolConfigurationOption(ProtocolConfigurationOption pco) 
	{
		pco.setInstance(0);
		this.protocolConfigurationOption=pco;
	}

	@Override
	public AdditionalProtocolConfigurationOption getAdditionalProtocolConfigurationOption() 
	{
		return additionalProtocolConfigurationOption;
	}

	@Override
	public void setAdditionalProtocolConfigurationOption(AdditionalProtocolConfigurationOption additionalProtocolConfigurationOption) 
	{
		additionalProtocolConfigurationOption.setInstance(0);
		this.additionalProtocolConfigurationOption=additionalProtocolConfigurationOption;
	}

	@Override
	public ExtendedProtocolConfigurationOptions getExtendedProtocolConfigurationOption() 
	{
		return this.extendedProtocolConfigurationOption;
	}

	@Override
	public void setExtendedProtocolConfigurationOption(ExtendedProtocolConfigurationOptions epco) 
	{
		epco.setInstance(0);
		this.extendedProtocolConfigurationOption=epco;				
	}

	@Override
	public MaximumPacketLossRate getMaximumPacketLossRate() 
	{
		return maximumPacketLossRate;
	}

	@Override
	public void setMaximumPacketLossRate(MaximumPacketLossRate maximumPacketLossRate) 
	{
		maximumPacketLossRate.setInstance(0);
		this.maximumPacketLossRate=maximumPacketLossRate;
	}
}