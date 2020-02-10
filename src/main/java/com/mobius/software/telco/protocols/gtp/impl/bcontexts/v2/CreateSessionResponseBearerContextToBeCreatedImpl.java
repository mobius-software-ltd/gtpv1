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

import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.CreateSessionResponseBearerContextToBeCreated;
import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.BearerFlags;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.BearerQos;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ChargingID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPSBearerID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.impl.headers.v2.BearerContextImpl;

public class CreateSessionResponseBearerContextToBeCreatedImpl extends BearerContextImpl implements CreateSessionResponseBearerContextToBeCreated
{
	private EPSBearerID bearerID;
	private Cause cause;
	private FTEID s1USGWFTEID;
	private FTEID s4SGWFTEID;
	private FTEID s5S8UPGWFTEID;
	private FTEID s12SGWFTEID;
	private FTEID s2bUPGWFTEID;
	private FTEID s2aPGWFTEID;
	private BearerQos bearerQos;	
	private ChargingID chargingID;
	private BearerFlags bearerFlags;
	private FTEID s11USGWFTEID;
	
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
						s1USGWFTEID=(FTEID)tlv;
						break;
					case 1:
						s4SGWFTEID=(FTEID)tlv;
						break;
					case 2:
						s5S8UPGWFTEID=(FTEID)tlv;
						break;
					case 3:
						s12SGWFTEID=(FTEID)tlv;
						break;
					case 4:
						s2bUPGWFTEID=(FTEID)tlv;
						break;
					case 5:
						s2aPGWFTEID=(FTEID)tlv;
						break;
					case 6:
						s11USGWFTEID=(FTEID)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}	
				break;
			case BEARER_QOS:
				bearerQos=(BearerQos)tlv;
				break;
			case CHARGING_ID:
				chargingID=(ChargingID)tlv;
				break;
			case BEARER_FLAGS:
				bearerFlags=(BearerFlags)tlv;
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
		
		if(s1USGWFTEID!=null)
			output.add(s1USGWFTEID);
		
		if(s4SGWFTEID!=null)
			output.add(s4SGWFTEID);
		
		if(s5S8UPGWFTEID!=null)
			output.add(s5S8UPGWFTEID);
		
		if(s12SGWFTEID!=null)
			output.add(s12SGWFTEID);
		
		if(s2bUPGWFTEID!=null)
			output.add(s2bUPGWFTEID);
		
		if(s2aPGWFTEID!=null)
			output.add(s2aPGWFTEID);
		
		if(bearerQos!=null)
			output.add(bearerQos);
		
		if(chargingID!=null)
			output.add(chargingID);
		
		if(bearerFlags!=null)
			output.add(bearerFlags);
		
		if(s11USGWFTEID!=null)
			output.add(s11USGWFTEID);
		
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
	public FTEID getS1USGWFTEID() 
	{
		return s1USGWFTEID;
	}

	@Override
	public void setS1USGWFTEID(FTEID fteid) 
	{
		fteid.setInstance(0);
		this.s1USGWFTEID=fteid;
	}

	@Override
	public FTEID getS4SGWFTEID() 
	{
		return s4SGWFTEID;
	}

	@Override
	public void setS4SGWFTEID(FTEID fteid) 
	{
		fteid.setInstance(1);
		this.s4SGWFTEID=fteid;
	}

	@Override
	public FTEID getS5S8UPGWFTEID() 
	{
		return s5S8UPGWFTEID;
	}

	@Override
	public void setS5S8UPGWFTEID(FTEID fteid) 
	{
		fteid.setInstance(2);
		this.s5S8UPGWFTEID=fteid;
	}

	@Override
	public FTEID getS12SGWFTEID() 
	{
		return s12SGWFTEID;
	}

	@Override
	public void setS12SGWFTEID(FTEID fteid) 
	{
		fteid.setInstance(3);
		this.s12SGWFTEID=fteid;
	}

	@Override
	public FTEID getS2bUPGWFTEID() 
	{		
		return s2bUPGWFTEID;
	}

	@Override
	public void setS2bUPGWFTEID(FTEID fteid) 
	{
		fteid.setInstance(4);
		this.s2bUPGWFTEID=fteid;
	}

	@Override
	public FTEID getS2aPGWFTEID() 
	{
		return s2aPGWFTEID;				
	}

	@Override
	public void setS2aPGWFTEID(FTEID fteid) 
	{
		fteid.setInstance(5);
		this.s2aPGWFTEID=fteid;
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
	public ChargingID getChargingID() 
	{
		return chargingID;
	}

	@Override
	public void setChargingID(ChargingID chargingID) 
	{
		chargingID.setInstance(0);
		this.chargingID=chargingID;
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
	public FTEID getS11USGWFTEID() 
	{
		return s11USGWFTEID;				
	}

	@Override
	public void setS11USGWFTEID(FTEID fteid) 
	{
		fteid.setInstance(6);
		this.s11USGWFTEID=fteid;
	}
}