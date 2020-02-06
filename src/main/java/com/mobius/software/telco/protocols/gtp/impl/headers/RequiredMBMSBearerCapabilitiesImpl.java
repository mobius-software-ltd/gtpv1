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
import com.mobius.software.telco.protocols.gtp.api.headers.RequiredMBMSBearerCapabilities;

public class RequiredMBMSBearerCapabilitiesImpl extends AbstractTLV implements RequiredMBMSBearerCapabilities 
{
	private String capabilities;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.REQUIRED_MBMS_BEARER_CAPABILITIES;
	}

	@Override
	public Integer getLength() 
	{
		if(capabilities==null)
			return 0;
		
		return capabilities.getBytes().length;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return true;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(capabilities!=null)
			buffer.writeBytes(capabilities.getBytes());
		else
			throw new MissingArgumentException("Capabilities are not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte[] data=new byte[length];
		buffer.readBytes(data);
		capabilities=new String(data);
	}

	@Override
	public String getCapabilities() 
	{
		return capabilities;
	}

	@Override
	public void setCapabilities(String capabilities) 
	{
		this.capabilities=capabilities;
	}
}