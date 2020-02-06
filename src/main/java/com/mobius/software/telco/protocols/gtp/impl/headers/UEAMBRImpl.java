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
import com.mobius.software.telco.protocols.gtp.api.headers.UEAMBR;

public class UEAMBRImpl extends AbstractTLV implements UEAMBR 
{
	private Long subscribedUplink;
	private Long subscribedDownlink;
	private Long authorizedUplink;
	private Long authorizedDownlink;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.UE_AMBR;
	}

	@Override
	public Integer getLength() 
	{
		if(authorizedDownlink!=null && authorizedUplink!=null)
			return 16;
		
		return 8;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return true;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(subscribedUplink!=null)
			buffer.writeInt(subscribedUplink.intValue());
		else
			throw new MissingArgumentException("Subscribed Uplink is not set");
		
		if(subscribedDownlink!=null)
			buffer.writeInt(subscribedDownlink.intValue());
		else
			throw new MissingArgumentException("Subscribed Downlink is not set");
		
		if(authorizedUplink!=null && authorizedDownlink!=null)
		{
			buffer.writeInt(authorizedUplink.intValue());
			buffer.writeInt(authorizedDownlink.intValue());
		}
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		subscribedUplink=buffer.readUnsignedInt();
		subscribedDownlink=buffer.readUnsignedInt();
		
		if(length>8)
		{
			authorizedUplink=buffer.readUnsignedInt();
			authorizedDownlink=buffer.readUnsignedInt();
		}
	}

	@Override
	public Long getSubscribedUplink() 
	{
		return subscribedUplink;
	}

	@Override
	public void setSubscribedUplink(Long uplink) 
	{
		this.subscribedUplink=uplink;
	}
	
	@Override
	public Long getSubscribedDownlink() 
	{
		return subscribedDownlink;
	}

	@Override
	public void setSubscribedDownlink(Long downlink) 
	{
		this.subscribedDownlink=downlink;
	}
	
	@Override
	public Long getAuthorizedUplink() 
	{
		return authorizedUplink;
	}

	@Override
	public void setAuthorizedUplink(Long uplink) 
	{
		this.authorizedUplink=uplink;
	}
	
	@Override
	public Long getAuthorizedDownlink() 
	{
		return authorizedDownlink;
	}

	@Override
	public void setAuthorizedDownlink(Long downlink) 
	{
		this.authorizedDownlink=downlink;
	}
}