package com.mobius.software.telco.protocols.gtp.impl.headers;
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

import com.mobius.software.telco.protocols.gtp.api.exceptions.MissingArgumentException;
import com.mobius.software.telco.protocols.gtp.api.headers.CMSISDN;
import com.mobius.software.telco.protocols.gtp.api.headers.ElementType;
import com.mobius.software.telco.protocols.gtp.api.utils.StringFunctions;

public class CMSISDNImpl extends AbstractTLV implements CMSISDN 
{
	private String msisdn;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.C_MSISDN;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return true;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=0;
		String value=getMSISDN();
		if(value!=null)
			length+=(value.length()+1)/2;
		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(getMSISDN()!=null)
			buffer.writeBytes(StringFunctions.octetsToBytes(getMSISDN()));
		else
			throw new MissingArgumentException("MSISDN not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte[] data;
		if(length!=null)
			data=new byte[length-1];
		else
			data=new byte[getLength()-1];
		
		buffer.readBytes(data);
		setMSISDN(StringFunctions.octetsToHex(data));
	}

	@Override
	public String getMSISDN() 
	{
		return msisdn;
	}

	public void setMSISDN(String msisdn) 
	{	
		this.msisdn = msisdn;
	}
}