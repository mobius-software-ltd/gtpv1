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
import com.mobius.software.telco.protocols.gtp.api.headers.LocalHomeNetworkIDWithNSAPI;

public class LocalHomeNetworkIDWithNSAPIImpl extends AbstractTLV implements LocalHomeNetworkIDWithNSAPI 
{
	private String localHomeNetworkID;
	private Integer nsapi;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.LOCAL_HOME_NETWORK_ID_WITH_NSAPI;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return true;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=1;
		if(localHomeNetworkID!=null)
			length+=localHomeNetworkID.getBytes().length;
		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(nsapi!=null)
			buffer.writeByte(nsapi.byteValue() & 0x0F);
		else
			throw new MissingArgumentException("NSAPI is not set");
		
		if(localHomeNetworkID!=null)
			buffer.writeBytes(localHomeNetworkID.getBytes());					
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		nsapi=(buffer.readByte() & 0x0F);
		if(length>1)
		{
			byte[] data=new byte[length-1];
			buffer.readBytes(data);
			localHomeNetworkID=new String(data);
		}
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

	@Override
	public String getLocalHomeNetworkID() 
	{
		return localHomeNetworkID;
	}

	@Override
	public void setLocalHomeNetworkID(String value) 
	{
		this.localHomeNetworkID=value;
	}
}