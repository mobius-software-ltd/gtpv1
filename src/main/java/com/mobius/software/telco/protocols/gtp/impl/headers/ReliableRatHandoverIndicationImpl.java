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
import com.mobius.software.telco.protocols.gtp.api.headers.ReliableRatHandoverIndication;

public class ReliableRatHandoverIndicationImpl extends AbstractTLV implements ReliableRatHandoverIndication 
{
	private Boolean reliableIndication;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.RELIABLE_INTER_RAT_HANDOVER_INFO;
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
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(reliableIndication!=null && reliableIndication)
			buffer.writeByte(1);
		else
			buffer.writeByte(0);		
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		if((buffer.readByte() & 0x01)!=0)
			reliableIndication=true;
		else
			reliableIndication=false;		
	}

	@Override
	public Boolean getReliableIndication() 
	{
		return reliableIndication;
	}

	@Override
	public void setReliableIndication(Boolean value) 
	{
		this.reliableIndication=value;
	}
}