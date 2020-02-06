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
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifierControlPane;

public class TunnelEndpointIdentifierControlPaneImpl extends AbstractTLV implements TunnerEndpointIdentifierControlPane 
{
	private Long tunnelEndpointIdentifier;
	
	@Override
	public Long getTunnelEndpointIdentifier() 
	{
		return tunnelEndpointIdentifier;
	}

	@Override
	public void setTunnelEndpointIdentifier(Long data) 
	{
		this.tunnelEndpointIdentifier=data;
	}

	@Override
	public ElementType getElementType() 
	{
		return ElementType.TEICP;
	}
	
	@Override
	public Integer getLength() 
	{
		return 4;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return false;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(tunnelEndpointIdentifier!=null)
			buffer.writeInt(tunnelEndpointIdentifier.intValue());
		else
			throw new MissingArgumentException("Tunnel Endpoint Identifier is not set");
	}
	
	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		tunnelEndpointIdentifier=buffer.readUnsignedInt();
	}
}
