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
import com.mobius.software.telco.protocols.gtp.api.headers.MBMSSessionIdentifier;

public class MBMSSessionIdentifierImpl extends AbstractTLV implements MBMSSessionIdentifier 
{
	private Integer sessionIdentifier;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.MBMS_SESSION_IDENTIFIER;
	}

	@Override
	public Integer getLength() 
	{
		return 1;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return true;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(sessionIdentifier!=null)
			buffer.writeByte(sessionIdentifier & 0x0FF);
		else
			throw new MissingArgumentException("Session Identifier is not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		sessionIdentifier=buffer.readByte() & 0x0FF;
	}

	@Override
	public Integer getSessionIdentifier() 
	{
		return sessionIdentifier;
	}

	@Override
	public void setSessionIdentifier(Integer sessionIdentifier) 
	{
		this.sessionIdentifier=sessionIdentifier;
	}
}