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

import com.mobius.software.telco.protocols.gtp.api.exceptions.MissingArgumentException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SelectionMode;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SelectionModeType;

public class SelectionModeImpl extends AbstractTLV2 implements SelectionMode 
{
	private SelectionModeType modeType;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.SELECTION_MODE;
	}

	@Override
	public Integer getLength() 
	{
		return 1;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(modeType!=null)
			buffer.writeByte(modeType.getValue() & 0x03);
		else
			throw new MissingArgumentException("Selection Mode is not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
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

}
