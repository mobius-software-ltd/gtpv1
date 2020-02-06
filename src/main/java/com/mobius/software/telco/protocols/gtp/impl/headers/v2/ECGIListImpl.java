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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ECGI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ECGIList;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;

public class ECGIListImpl extends AbstractTLV2 implements ECGIList 
{
	private List<ECGI> ecgi;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.ECGI_LIST;
	}

	@Override
	public Integer getLength() 
	{
		if(ecgi==null)
			return 2;
		
		return ecgi.size()*7 + 2;
	}

	@Override
	public List<ECGI> getECGI() 
	{
		return ecgi;
	}

	@Override
	public void setECGI(List<ECGI> ecgi) 
	{
		this.ecgi=ecgi;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws GTPParseException 
	{
		if(ecgi==null || ecgi.size()==0)
			throw new GTPParseException("ECGI is not set");
		else
		{
			buffer.writeShort(ecgi.size());
			for(int i=0;i<ecgi.size();i++)
				ecgi.get(i).encode(buffer);
		}
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) throws GTPParseException 
	{
		int arrLength=buffer.readUnsignedShort();
		ecgi=new ArrayList<ECGI>(arrLength);
		for(int i=0;i<arrLength;i++)
		{
			ECGI curr=new ECGIImpl();
			curr.decode(buffer);
			ecgi.add(curr);
		}
	}
}