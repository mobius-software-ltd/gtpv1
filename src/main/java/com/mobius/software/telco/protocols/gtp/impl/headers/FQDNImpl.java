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
import com.mobius.software.telco.protocols.gtp.api.headers.ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.FQDN;

public class FQDNImpl extends AbstractTLV implements FQDN 
{
	private String fqdn;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.FQDN;
	}

	@Override
	public Integer getLength() 
	{
		if(fqdn==null)
			return 0;
		
		return fqdn.getBytes().length;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return true;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(fqdn!=null)
			buffer.writeBytes(fqdn.getBytes());
		else
			throw new MissingArgumentException("FQDN is not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte[] data=new byte[length];
		buffer.readBytes(data);
		fqdn=new String(data);
	}

	@Override
	public String getFQDN() 
	{
		return fqdn;
	}

	@Override
	public void setFQDN(String fqdn) 
	{
		this.fqdn=fqdn;
	}
}