package com.mobius.software.telco.protocols.gtp.impl.headers.v2;
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
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.BearerContext;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;

public abstract class BearerContextImpl extends AbstractTLV2 implements BearerContext 
{
	private List<TLV2> originalTLVs=new ArrayList<TLV2>();
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.BEARER_CONTEXT;
	}
	
	@Override
	public Integer getLength() 
	{
		int length=0;
		List<TLV2> tlv=null;
		try
		{
			tlv=getTLVs();
		}
		catch(Exception ex)
		{
			return 0;
		}
		
		for(TLV2 currTLV:tlv)
			length+=currTLV.getLength() + 4;
		
		return length;
	}

	@Override
	public void writeValue(ByteBuf buffer) throws GTPParseException 
	{
		List<TLV2> tlv;
		if(originalTLVs!=null)
			tlv=originalTLVs;
		else
			tlv=getTLVs();
		
		for(TLV2 currTLV:tlv)
			currTLV.encode(buffer);		
	}
	
	@Override
	protected void readValue(ByteBuf buffer, Integer length) throws GTPParseException 
	{
		while(length>0)
		{
			TLV2 currTLV=TLV2Factory.decode(null, buffer);
			if(currTLV!=null)
			{
				applyTLV(currTLV);
				originalTLVs.add(currTLV);
			}
			
			length-=currTLV.getLength() + 4;
		}
	}
	
	@Override
	public abstract void applyTLV(TLV2 tlv) throws GTPParseException;

	@Override
	public abstract List<TLV2> getTLVs() throws GTPParseException;
}