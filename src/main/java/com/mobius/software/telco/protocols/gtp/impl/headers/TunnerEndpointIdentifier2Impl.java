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
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifier2;

public class TunnerEndpointIdentifier2Impl extends AbstractTLV implements TunnerEndpointIdentifier2 
{
	private Integer nsapi;
	
	private Long tunnelEndpointIdentifier;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.TEID_2;
	}

	@Override
	public Integer getLength() 
	{
		return 5;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return false;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(nsapi!=null)
			buffer.writeByte(nsapi.byteValue() & 0x0F);
		else
			throw new MissingArgumentException("NSAPI is not set");
		
		if(tunnelEndpointIdentifier!=null)
			buffer.writeInt(tunnelEndpointIdentifier.intValue());
		else
			throw new MissingArgumentException("Tunnel Endpoint Identifier is not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		nsapi=(buffer.readByte() & 0x0F);
		tunnelEndpointIdentifier=buffer.readUnsignedInt();
	}
	
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
	public Integer getNSAPI() 
	{
		return nsapi;
	}

	@Override
	public void setNSAPI(Integer nsapi) 
	{
		this.nsapi=nsapi;
	}
}