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

import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.ModifyBearerCommandBearerContextToBeModified;
import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.BearerQos;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPSBearerID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.impl.headers.v2.BearerContextImpl;

public class ModifyBearerCommandBearerContextToBeModifiedImpl extends BearerContextImpl implements ModifyBearerCommandBearerContextToBeModified
{
	private EPSBearerID bearerID;
	private BearerQos bearerQos;
	
	@Override
	public void applyTLV(TLV2 tlv) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case EPS_BEARER_ID:
				bearerID=(EPSBearerID)tlv;
				break;
			case BEARER_QOS:
				bearerQos=(BearerQos)tlv;
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
		
		if(bearerQos!=null)
			output.add(bearerQos);		
		
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
}