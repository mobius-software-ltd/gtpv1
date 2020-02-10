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

import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.ModifyBearerRequestBearerContextToBeModified;
import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPSBearerID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.impl.headers.v2.BearerContextImpl;

public class ModifyBearerRequestBearerContextToBeModifiedImpl extends BearerContextImpl implements ModifyBearerRequestBearerContextToBeModified
{
	private EPSBearerID bearerID;
	private FTEID s1UeNodeBFTEID;
	private FTEID s5S8USGWFTEID;
	private FTEID s12RNCFTEID;
	private FTEID s4SGSNFTEID;
	private FTEID s11MMEFTEID;
	
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
						s1UeNodeBFTEID=(FTEID)tlv;
						break;
					case 1:
						s5S8USGWFTEID=(FTEID)tlv;
						break;
					case 2:
						s12RNCFTEID=(FTEID)tlv;
						break;
					case 3:
						s4SGSNFTEID=(FTEID)tlv;
						break;
					case 4:
						s11MMEFTEID=(FTEID)tlv;
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
		
		if(s1UeNodeBFTEID!=null)
			output.add(s1UeNodeBFTEID);
		
		if(s5S8USGWFTEID!=null)
			output.add(s5S8USGWFTEID);
		
		if(s12RNCFTEID!=null)
			output.add(s12RNCFTEID);
		
		if(s4SGSNFTEID!=null)
			output.add(s4SGSNFTEID);
		
		if(s11MMEFTEID!=null)
			output.add(s11MMEFTEID);
		
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
		return s1UeNodeBFTEID;
	}

	@Override
	public void setS1UENodeBFTEID(FTEID fteid) 
	{
		fteid.setInstance(0);
		this.s1UeNodeBFTEID=fteid;
	}

	@Override
	public FTEID getS5S8USGWFTEID() 
	{
		return s5S8USGWFTEID;
	}

	@Override
	public void setS5S8USGWFTEID(FTEID fteid) 
	{
		fteid.setInstance(1);
		this.s5S8USGWFTEID=fteid;
	}

	@Override
	public FTEID getS12RNCFTEID() 
	{
		return s12RNCFTEID;
	}

	@Override
	public void setS12RNCFTEID(FTEID fteid) 
	{
		fteid.setInstance(2);
		this.s12RNCFTEID=fteid;
	}

	@Override
	public FTEID getS4SGSNFTEID() 
	{
		return s4SGSNFTEID;
	}

	@Override
	public void setS4SGSNFTEID(FTEID fteid) 
	{
		fteid.setInstance(3);
		this.s4SGSNFTEID=fteid;
	}

	@Override
	public FTEID getS11MMEFTEID() 
	{
		return s11MMEFTEID;				
	}

	@Override
	public void setS11MMEFTEID(FTEID fteid) 
	{
		fteid.setInstance(4);
		this.s11MMEFTEID=fteid;
	}
}