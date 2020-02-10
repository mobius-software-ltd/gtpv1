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

import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.UpdateBearerResponseBearerContextToBeModified;
import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPSBearerID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ExtendedProtocolConfigurationOptions;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ProtocolConfigurationOption;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RANNASCause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.impl.headers.v2.BearerContextImpl;

public class UpdateBearerResponseBearerContextToBeModifiedImpl extends BearerContextImpl implements UpdateBearerResponseBearerContextToBeModified
{
	private EPSBearerID bearerID;
	private Cause cause;
	private FTEID s4SGSNFTEID;
	private FTEID s12RNCFTEID;
	private ProtocolConfigurationOption protocolConfigurationOption;
	private RANNASCause ranNASCause;
	private ExtendedProtocolConfigurationOptions extendedProtocolConfigurationOption;
	
	@Override
	public void applyTLV(TLV2 tlv) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case EPS_BEARER_ID:
				bearerID=(EPSBearerID)tlv;
				break;
			case CAUSE:
				cause=(Cause)tlv;
				break;
			case FTEID:
				switch(tlv.getInstance())
				{
					case 0:
						s4SGSNFTEID=(FTEID)tlv;
						break;
					case 1:
						s12RNCFTEID=(FTEID)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}	
				break;
			case PROTOCOL_CONFIGURATION_OPTIONS:
				protocolConfigurationOption=(ProtocolConfigurationOption)tlv;
				break;
			case RAN_NAS_CAUSE:
				ranNASCause=(RANNASCause)tlv;
				break;
			case EXTENDED_PROTOCOL_CONFIGURATION_OPTIONS:
				extendedProtocolConfigurationOption=(ExtendedProtocolConfigurationOptions)tlv;
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
		
		if(cause==null)
			throw new GTPParseException("Cause not set");
		
		output.add(cause);
		
		if(s4SGSNFTEID!=null)
			output.add(s4SGSNFTEID);
		
		if(s12RNCFTEID!=null)
			output.add(s12RNCFTEID);
		
		if(protocolConfigurationOption!=null)
			output.add(protocolConfigurationOption);
		
		if(ranNASCause!=null)
			output.add(ranNASCause);
		
		if(extendedProtocolConfigurationOption!=null)
			output.add(extendedProtocolConfigurationOption);
		
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
	public Cause getCause() 
	{
		return cause;
	}

	@Override
	public void setCause(Cause cause) 
	{
		cause.setInstance(0);
		this.cause=cause;
	}

	@Override
	public FTEID getS4SGSNFTEID() 
	{
		return s4SGSNFTEID;
	}

	@Override
	public void setS4SGSNFTEID(FTEID fteid) 
	{
		fteid.setInstance(0);
		this.s4SGSNFTEID=fteid;
	}

	@Override
	public FTEID getS12RNCFTEID() 
	{
		return s12RNCFTEID;
	}

	@Override
	public void setS12RNCFTEID(FTEID fteid) 
	{
		fteid.setInstance(1);
		this.s12RNCFTEID=fteid;
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
	public RANNASCause getRANNASCause() 
	{
		return ranNASCause;
	}

	@Override
	public void setRANNASCause(RANNASCause ranNASCause) 
	{
		ranNASCause.setInstance(0);
		this.ranNASCause=ranNASCause;
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
}