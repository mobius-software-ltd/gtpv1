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

import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.CreateBearerResponseBearerContextToBeCreated;
import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPSBearerID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ExtendedProtocolConfigurationOptions;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ProtocolConfigurationOption;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RANNASCause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.impl.headers.v2.BearerContextImpl;

public class CreateBearerResponseBearerContextToBeCreatedImpl extends BearerContextImpl implements CreateBearerResponseBearerContextToBeCreated
{
	private EPSBearerID bearerID;
	private Cause cause;
	private FTEID s1UeNodeBFTEID;
	private FTEID s1USGWFTEID;
	private FTEID s5S8USGWFTEID;
	private FTEID s5S8UPGWFTEID;
	private FTEID s12RNCFTEID;
	private FTEID s12SGWFTEID;
	private FTEID s4SGSNFTEID;
	private FTEID s4SGWFTEID;
	private FTEID s2bUePDGFTEID;
	private FTEID s2bPGWFTEID;
	private FTEID s2aUTWANFTEID;
	private FTEID s2aPGWFTEID;
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
						s1UeNodeBFTEID=(FTEID)tlv;
						break;
					case 1:
						s1USGWFTEID=(FTEID)tlv;
						break;
					case 2:
						s5S8USGWFTEID=(FTEID)tlv;
						break;
					case 3:
						s5S8UPGWFTEID=(FTEID)tlv;
						break;
					case 4:
						s12RNCFTEID=(FTEID)tlv;
						break;
					case 5:
						s12SGWFTEID=(FTEID)tlv;
						break;
					case 6:
						s4SGSNFTEID=(FTEID)tlv;
						break;
					case 7:
						s4SGWFTEID=(FTEID)tlv;
						break;
					case 8:
						s2bUePDGFTEID=(FTEID)tlv;
						break;
					case 9:
						s2bPGWFTEID=(FTEID)tlv;
						break;
					case 10:
						s2aUTWANFTEID=(FTEID)tlv;
						break;
					case 11:
						s2aPGWFTEID=(FTEID)tlv;
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
		
		if(s1UeNodeBFTEID!=null)
			output.add(s1UeNodeBFTEID);
		
		if(s1USGWFTEID!=null)
			output.add(s1USGWFTEID);
		
		if(s5S8USGWFTEID!=null)
			output.add(s5S8USGWFTEID);
		
		if(s5S8UPGWFTEID!=null)
			output.add(s5S8UPGWFTEID);
		
		if(s12RNCFTEID!=null)
			output.add(s12RNCFTEID);
		
		if(s12SGWFTEID!=null)
			output.add(s12SGWFTEID);
		
		if(s4SGSNFTEID!=null)
			output.add(s4SGSNFTEID);
		
		if(s4SGWFTEID!=null)
			output.add(s4SGWFTEID);
		
		if(s2bUePDGFTEID!=null)
			output.add(s2bUePDGFTEID);
		
		if(s2bPGWFTEID!=null)
			output.add(s2bPGWFTEID);
		
		if(s2aUTWANFTEID!=null)
			output.add(s2aUTWANFTEID);
		
		if(s2aPGWFTEID!=null)
			output.add(s2aPGWFTEID);
		
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
	public FTEID getS1UENodeBFTEID() 
	{
		return s1UeNodeBFTEID;
	}

	@Override
	public void setS1UENodeBFTEID(FTEID fteid) 
	{
		fteid.setInstance(0);
		this.s1UeNodeBFTEID=fteid;
	}

	@Override
	public FTEID getS1USGWFTEID() 
	{
		return s1USGWFTEID;
	}

	@Override
	public void setS1USGWFTEID(FTEID fteid) 
	{
		fteid.setInstance(1);
		this.s1USGWFTEID=fteid;
	}

	@Override
	public FTEID getS5S8USGWFTEID() 
	{
		return s5S8USGWFTEID;
	}

	@Override
	public void setS5S8USGWFTEID(FTEID fteid) 
	{
		fteid.setInstance(2);
		this.s5S8USGWFTEID=fteid;
	}

	@Override
	public FTEID getS5S8UPGWFTEID() 
	{
		return s5S8UPGWFTEID;
	}

	@Override
	public void setS5S8UPGWFTEID(FTEID fteid) 
	{
		fteid.setInstance(3);
		this.s5S8UPGWFTEID=fteid;
	}

	@Override
	public FTEID getS12RNCFTEID() 
	{
		return s12RNCFTEID;
	}

	@Override
	public void setS12RNCFTEID(FTEID fteid) 
	{
		fteid.setInstance(4);
		this.s12RNCFTEID=fteid;
	}

	@Override
	public FTEID getS12SGWFTEID() 
	{
		return s12SGWFTEID;
	}

	@Override
	public void setS12SGWFTEID(FTEID fteid) 
	{
		fteid.setInstance(5);
		this.s12SGWFTEID=fteid;
	}

	@Override
	public FTEID getS4SGSNFTEID() 
	{
		return s4SGSNFTEID;
	}

	@Override
	public void setS4SGSNFTEID(FTEID fteid) 
	{
		fteid.setInstance(6);
		this.s4SGSNFTEID=fteid;
	}

	@Override
	public FTEID getS4SGWFTEID() 
	{
		return s4SGWFTEID;
	}

	@Override
	public void setS4SGWFTEID(FTEID fteid) 
	{
		fteid.setInstance(7);
		this.s4SGWFTEID=fteid;
	}

	@Override
	public FTEID getS2bUePDGFTEID() 
	{		
		return s2bUePDGFTEID;
	}

	@Override
	public void setS2bUePDGFTEID(FTEID fteid) 
	{
		fteid.setInstance(8);
		this.s2bUePDGFTEID=fteid;
	}

	@Override
	public FTEID getS2bPGWFTEID() 
	{		
		return s2bPGWFTEID;
	}

	@Override
	public void setS2bPGWFTEID(FTEID fteid) 
	{
		fteid.setInstance(9);
		this.s2bPGWFTEID=fteid;
	}

	@Override
	public FTEID getS2aUTWANFTEID() 
	{
		return s2aUTWANFTEID;				
	}

	@Override
	public void setS2aUTWANFTEID(FTEID fteid) 
	{
		fteid.setInstance(10);
		this.s2aUTWANFTEID=fteid;
	}

	@Override
	public FTEID getS2aPGWFTEID() 
	{
		return s2aPGWFTEID;				
	}

	@Override
	public void setS2aPGWFTEID(FTEID fteid) 
	{
		fteid.setInstance(11);
		this.s2aPGWFTEID=fteid;
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
	public ExtendedProtocolConfigurationOptions getExtendedProtocolConfigurationOptions() 
	{
		return this.extendedProtocolConfigurationOption;
	}

	@Override
	public void setExtendedProtocolConfigurationOptions(ExtendedProtocolConfigurationOptions epco) 
	{
		epco.setInstance(0);
		this.extendedProtocolConfigurationOption=epco;				
	}
}