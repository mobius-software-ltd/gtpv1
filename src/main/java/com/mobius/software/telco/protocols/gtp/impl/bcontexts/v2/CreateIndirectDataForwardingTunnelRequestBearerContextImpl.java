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

import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.CreateIndirectDataForwardingTunnelRequestBearerContext;
import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPSBearerID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.impl.headers.v2.BearerContextImpl;

public class CreateIndirectDataForwardingTunnelRequestBearerContextImpl extends BearerContextImpl implements CreateIndirectDataForwardingTunnelRequestBearerContext
{
	private EPSBearerID bearerID;
	private FTEID eNodeBDLFTEID;
	private FTEID sgwDLFTEID;
	private FTEID sgsnDLFTEID;
	private FTEID rncDLFTEID;
	private FTEID eNodeBULFTEID;
	private FTEID sgwULFTEID;
	private FTEID mmeDLFTEID;
	
	@Override
	public void applyTLV(TLV2 tlv) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case EPS_BEARER_ID:
				bearerID=(EPSBearerID)tlv;
				break;
			case FTEID:
				switch(tlv.getInstance())
				{
					case 0:
						eNodeBDLFTEID=(FTEID)tlv;
						break;
					case 1:
						sgwDLFTEID=(FTEID)tlv;
						break;
					case 2:
						sgsnDLFTEID=(FTEID)tlv;
						break;
					case 3:
						rncDLFTEID=(FTEID)tlv;
						break;
					case 4:
						eNodeBULFTEID=(FTEID)tlv;
						break;
					case 5:
						sgwULFTEID=(FTEID)tlv;
						break;
					case 6:
						mmeDLFTEID=(FTEID)tlv;
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
		
		if(eNodeBDLFTEID!=null)
			output.add(eNodeBDLFTEID);
		
		if(sgwDLFTEID!=null)
			output.add(sgwDLFTEID);
		
		if(sgsnDLFTEID!=null)
			output.add(sgsnDLFTEID);
		
		if(rncDLFTEID!=null)
			output.add(rncDLFTEID);
		
		if(eNodeBULFTEID!=null)
			output.add(eNodeBULFTEID);
		
		if(sgwULFTEID!=null)
			output.add(sgwULFTEID);
		
		if(mmeDLFTEID!=null)
			output.add(mmeDLFTEID);		
		
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
	public FTEID getENodeBDLFTEID() 
	{
		return eNodeBDLFTEID;
	}

	@Override
	public void setENodeBDLFTEID(FTEID fteid) 
	{
		fteid.setInstance(0);
		this.eNodeBDLFTEID=fteid;
	}

	@Override
	public FTEID getSGWDLFTEID() 
	{
		return sgwDLFTEID;
	}

	@Override
	public void setSGWDLFTEID(FTEID fteid) 
	{
		fteid.setInstance(1);
		this.sgwDLFTEID=fteid;
	}

	@Override
	public FTEID getSGSNDLFTEID() 
	{
		return sgsnDLFTEID;
	}

	@Override
	public void setSGSNDLFTEID(FTEID fteid) 
	{
		fteid.setInstance(2);
		this.sgsnDLFTEID=fteid;
	}

	@Override
	public FTEID getRNCDLFTEID() 
	{
		return rncDLFTEID;
	}

	@Override
	public void setRNCDLFTEID(FTEID fteid) 
	{
		fteid.setInstance(3);
		this.rncDLFTEID=fteid;
	}

	@Override
	public FTEID getENodeBULFTEID() 
	{
		return eNodeBULFTEID;
	}

	@Override
	public void setENodeBULFTEID(FTEID fteid) 
	{
		fteid.setInstance(4);
		this.eNodeBULFTEID=fteid;
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

	@Override
	public FTEID getMMEDLFTEID() 
	{
		return mmeDLFTEID;
	}

	@Override
	public void setMMEDLFTEID(FTEID fteid) 
	{
		fteid.setInstance(6);
		this.mmeDLFTEID=fteid;
	}
}