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
import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.exceptions.MissingArgumentException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CivicAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CivicAddressElement;

public class CivicAddressImpl implements CivicAddress 
{
	private String cc;
	private List<CivicAddressElement> elements;
	
	@Override
	public void encode(ByteBuf buffer) throws GTPParseException 
	{
		if(cc==null)
			throw new MissingArgumentException("CC is not set");
		
		buffer.writeBytes(cc.getBytes());
		for(CivicAddressElement curr:elements)
			curr.encode(buffer);		
	}

	@Override
	public void decode(ByteBuf buffer,Integer length) 
	{
		byte[] cc=new byte[2];
		buffer.readBytes(cc);
		this.cc=new String(cc);
		length-=2;
		elements=new ArrayList<CivicAddressElement>();
		while(length>0)
		{
			CivicAddressElementImpl curr=new CivicAddressElementImpl();
			curr.decode(buffer);
			length-=curr.getLength();
			elements.add(curr);
		}
	}

	@Override
	public Integer getLength() 
	{
		Integer length=2;
		if(elements!=null)
			for(CivicAddressElement curr:elements)
				length+=curr.getLength();
		
		return length;
	}

	@Override
	public String getCountryCode() 
	{
		return cc;
	}

	@Override
	public void setCountryCode(String cc) 
	{
		this.cc=cc;
	}

	@Override
	public List<CivicAddressElement> getElements() 
	{
		return elements;
	}

	@Override
	public void setElements(List<CivicAddressElement> elements) 
	{
		this.elements=elements;
	}
}