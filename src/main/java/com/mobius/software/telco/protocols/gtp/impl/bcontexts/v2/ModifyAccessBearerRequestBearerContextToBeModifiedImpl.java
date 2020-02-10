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

import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.ModifyAccessBearerRequestBearerContextToBeModified;
import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPSBearerID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.impl.headers.v2.BearerContextImpl;

public class ModifyAccessBearerRequestBearerContextToBeModifiedImpl extends BearerContextImpl implements ModifyAccessBearerRequestBearerContextToBeModified
{
	private EPSBearerID bearerID;
	private FTEID s1UENodeBFTEID;
	private FTEID s11UMMEFTEID;
	
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
						s1UENodeBFTEID=(FTEID)tlv;
						break;
					case 1:
						s11UMMEFTEID=(FTEID)tlv;
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
		
		if(s1UENodeBFTEID!=null)
			output.add(s1UENodeBFTEID);
		
		if(s11UMMEFTEID!=null)
			output.add(s11UMMEFTEID);
		
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
	public FTEID getS1UENodeBFTEID() 
	{
		return s1UENodeBFTEID;
	}

	@Override
	public void setS1UENodeBFTEID(FTEID fteid) 
	{
		s1UENodeBFTEID.setInstance(0);
		this.s1UENodeBFTEID=fteid;
	}

	@Override
	public FTEID getS11UMMEFTEID() 
	{
		return s11UMMEFTEID;
	}

	@Override
	public void setS11UMMEFTEID(FTEID fteid) 
	{
		fteid.setInstance(1);
		this.s11UMMEFTEID=fteid;
	}
}