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
import com.mobius.software.telco.protocols.gtp.api.headers.DirectTunnelFlags;
import com.mobius.software.telco.protocols.gtp.api.headers.ElementType;

public class DirectTunnelFlagsImpl extends AbstractTLV implements DirectTunnelFlags 
{
	private Boolean ei;
	private Boolean gcsi;
	private Boolean dti;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.DIRECT_TUNNEL_FLAGS;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return true;
	}

	@Override
	public Integer getLength() 
	{
		return 1;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		byte currByte=0;
		if(ei!=null && ei)
			currByte|=(byte)0x04;
		
		if(gcsi!=null && gcsi)
			currByte|=(byte)0x02;
		
		if(dti!=null && dti)
			currByte|=(byte)0x01;
		
		buffer.writeByte(currByte);
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte currByte=buffer.readByte();
		if((currByte & 0x04)!=0)
			ei=true;
		else
			ei=false;
		
		if((currByte & 0x02)!=0)
			gcsi=true;
		else
			gcsi=false;
		
		if((currByte & 0x01)!=0)
			dti=true;
		else
			dti=false;
	}

	@Override
	public Boolean getEI() 
	{
		return ei;
	}

	@Override
	public void setEI(Boolean value) 
	{
		this.ei=value;
	}

	@Override
	public Boolean getGCSI() 
	{
		return gcsi;
	}

	@Override
	public void setGCSI(Boolean value) 
	{
		this.gcsi=value;		
	}

	@Override
	public Boolean getDTI() 
	{
		return dti;
	}

	@Override
	public void setDTI(Boolean value) 
	{
		dti=value;
	}
}