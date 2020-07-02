package com.mobius.software.telco.protocols.gtp.impl.messages.v2;
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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FQCSID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.DeletePDNConnectionSetRequest;

public class DeletePDNConnectionSetRequestImpl extends AbstractGTP2Message implements DeletePDNConnectionSetRequest
{
	FQCSID mmeFQCSID;
	FQCSID sgwFQCSID;
	FQCSID pgwFQCSID;
	FQCSID epdgFQCSID;
	FQCSID twanFQCSID;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.DELETE_PDN_CONNECTION_SET_REQUEST;
	}

	@Override
	public void applyTLV(TLV2 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case FQ_CSID:
				switch(tlv.getInstance())
				{
					case 0:
						mmeFQCSID=(FQCSID)tlv;
						break;
					case 1:
						sgwFQCSID=(FQCSID)tlv;
						break;
					case 2:
						pgwFQCSID=(FQCSID)tlv;
						break;
					case 3:
						epdgFQCSID=(FQCSID)tlv;
						break;
					case 4:
						twanFQCSID=(FQCSID)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
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
	public List<TLV2> getTLVs() throws GTPParseException 
	{
		ArrayList<TLV2> output=new ArrayList<TLV2>();
		if(mmeFQCSID!=null)
			output.add(mmeFQCSID);
		
		if(sgwFQCSID!=null)
			output.add(sgwFQCSID);
		
		if(pgwFQCSID!=null)
			output.add(pgwFQCSID);
		
		if(epdgFQCSID!=null)
			output.add(epdgFQCSID);
		
		if(twanFQCSID!=null)
			output.add(twanFQCSID);
		
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
	public FQCSID getMMEFQCSID() 
	{
		return mmeFQCSID;
	}

	@Override
	public void setMMEFQCSID(FQCSID fqcsid) 
	{
		fqcsid.setInstance(0);
		this.mmeFQCSID=fqcsid;
	}

	@Override
	public FQCSID getSGWFQCSID() 
	{
		return sgwFQCSID;
	}

	@Override
	public void setSGWFQCSID(FQCSID fqcsid) 
	{
		fqcsid.setInstance(1);
		this.sgwFQCSID=fqcsid;
	}

	@Override
	public FQCSID getPGWFQCSID() 
	{
		return pgwFQCSID;
	}

	@Override
	public void setPGWFQCSID(FQCSID fqcsid) 
	{
		fqcsid.setInstance(2);
		this.pgwFQCSID=fqcsid;
	}

	@Override
	public FQCSID getEPDGFQCSID() 
	{
		return epdgFQCSID;
	}

	@Override
	public void setEPDGFQCSID(FQCSID fqcsid) 
	{
		fqcsid.setInstance(3);
		this.epdgFQCSID=fqcsid;
	}

	@Override
	public FQCSID getTWANFQCSID() 
	{
		return twanFQCSID;
	}

	@Override
	public void setTWANFQCSID(FQCSID fqcsid) 
	{
		fqcsid.setInstance(4);
		this.twanFQCSID=fqcsid;
	}
}