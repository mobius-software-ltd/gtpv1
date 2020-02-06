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
import com.mobius.software.telco.protocols.gtp.api.headers.SignalingPriorityIndicationWithNSAPI;

public class SignalingPriorityIndicationWithNSAPIImpl extends AbstractTLV implements SignalingPriorityIndicationWithNSAPI 
{
	private Boolean lowPriorityIndication;
	private Integer nsapi;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.SIGNALING_PRIORITY_INDICATION_WITH_NSAPI;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return true;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=2;
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(nsapi!=null)
			buffer.writeByte(nsapi.byteValue() & 0x0F);
		else
			throw new MissingArgumentException("NSAPI is not set");
		
		if(lowPriorityIndication!=null && lowPriorityIndication)
			buffer.writeByte(1);
		else
			buffer.writeByte(0);				
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		nsapi=(buffer.readByte() & 0x0F);
		if((buffer.readByte() & 0x01)!=0)
			lowPriorityIndication=true;
		else
			lowPriorityIndication=false;
	}

	@Override
	public Boolean getLowPriorityIndication() 
	{
		return lowPriorityIndication;
	}

	@Override
	public void setLowPriorityIndication(Boolean value) 
	{
		this.lowPriorityIndication=value;
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