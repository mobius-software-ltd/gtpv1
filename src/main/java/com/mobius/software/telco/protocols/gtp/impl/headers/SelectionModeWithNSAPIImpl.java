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
import com.mobius.software.telco.protocols.gtp.api.headers.SelectionModeType;
import com.mobius.software.telco.protocols.gtp.api.headers.SelectionModeWithNSAPI;

public class SelectionModeWithNSAPIImpl extends AbstractTLV implements SelectionModeWithNSAPI 
{
	private SelectionModeType modeType;
	private Integer nsapi;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.SELECTION_MODE_WITH_NSAPI;
	}

	@Override
	public Integer getLength() 
	{
		return 2;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return true;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(nsapi!=null)
			buffer.writeByte(nsapi.byteValue() & 0x0F);
		else
			throw new MissingArgumentException("NSAPI is not set");
		
		if(modeType!=null)
			buffer.writeByte(modeType.getValue() | 0xFC);
		else
			throw new MissingArgumentException("Selection Mode is not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		nsapi=(buffer.readByte() & 0x0F);
		modeType=SelectionModeType.fromInt(buffer.readByte() & 0x03);
	}

	@Override
	public SelectionModeType getSelectionMode() 
	{
		return modeType;
	}

	@Override
	public void setSelectionMode(SelectionModeType value) 
	{
		this.modeType=value;				
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
