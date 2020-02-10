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

import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.CreateIndirectDataForwardingTunnelResponseBearerContext;
import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPSBearerID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.impl.headers.v2.BearerContextImpl;

public class CreateIndirectDataForwardingTunnelResponseBearerContextImpl extends BearerContextImpl implements CreateIndirectDataForwardingTunnelResponseBearerContext
{
	private EPSBearerID bearerID;
	private Cause cause;
	private FTEID s1USGWDLFTEID;
	private FTEID s12USGWDLFTEID;
	private FTEID s4USGWDLFTEID;
	private FTEID sgwDLFTEID;
	private FTEID s1USGWULFTEID;
	private FTEID sgwULFTEID;
	
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
						s1USGWDLFTEID=(FTEID)tlv;
						break;
					case 1:
						s12USGWDLFTEID=(FTEID)tlv;
						break;
					case 2:
						s4USGWDLFTEID=(FTEID)tlv;
						break;
					case 3:
						sgwDLFTEID=(FTEID)tlv;
						break;
					case 4:
						s1USGWULFTEID=(FTEID)tlv;
						break;
					case 5:
						sgwULFTEID=(FTEID)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
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
		
		output.add(bearerID);
		
		if(s1USGWDLFTEID!=null)
			output.add(s1USGWDLFTEID);
		
		if(s12USGWDLFTEID!=null)
			output.add(s12USGWDLFTEID);
		
		if(s4USGWDLFTEID!=null)
			output.add(s4USGWDLFTEID);
		
		if(sgwDLFTEID!=null)
			output.add(sgwDLFTEID);
		
		if(s1USGWULFTEID!=null)
			output.add(s1USGWULFTEID);
		
		if(sgwULFTEID!=null)
			output.add(sgwULFTEID);
		
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
	public FTEID getS1USGWDLFTEID() 
	{
		return s1USGWDLFTEID;
	}

	@Override
	public void setS1USGWDLFTEID(FTEID fteid) 
	{
		fteid.setInstance(0);
		this.s1USGWDLFTEID=fteid;
	}

	@Override
	public FTEID getS12USGWDLFTEID() 
	{
		return s12USGWDLFTEID;
	}

	@Override
	public void setS12USGWDLFTEID(FTEID fteid) 
	{
		fteid.setInstance(1);
		this.s12USGWDLFTEID=fteid;
	}

	@Override
	public FTEID getS4USGWDLFTEID() 
	{
		return s4USGWDLFTEID;
	}

	@Override
	public void setS4USGWDLFTEID(FTEID fteid) 
	{
		fteid.setInstance(2);
		this.s4USGWDLFTEID=fteid;
	}

	@Override
	public FTEID getSGWDLFTEID() 
	{
		return sgwDLFTEID;
	}

	@Override
	public void setSGWDLFTEID(FTEID fteid) 
	{
		fteid.setInstance(3);
		this.sgwDLFTEID=fteid;
	}

	@Override
	public FTEID getS1USGWULFTEID() 
	{
		return s1USGWULFTEID;
	}

	@Override
	public void setS1USGWULFTEID(FTEID fteid) 
	{
		fteid.setInstance(4);
		this.s1USGWULFTEID=fteid;
	}

	@Override
	public FTEID getSGWULFTEID() 
	{
		return sgwULFTEID;
	}

	@Override
	public void setSGWULFTEID(FTEID fteid) 
	{
		fteid.setInstance(5);
		this.sgwULFTEID=fteid;
	}
}