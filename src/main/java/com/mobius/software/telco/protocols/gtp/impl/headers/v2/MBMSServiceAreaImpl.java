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

import java.util.ArrayList;
import java.util.List;

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MBMSServiceArea;

public class MBMSServiceAreaImpl extends AbstractTLV2 implements MBMSServiceArea 
{
	private List<Integer> serviceArea;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.MBMS_SERVICE_AREA;
	}

	@Override
	public Integer getLength() 
	{
		if(serviceArea==null)
			return 1;
		
		return serviceArea.size()*2 + 1;
	}

	@Override
	public List<Integer> getServiceArea() 
	{
		return serviceArea;
	}

	@Override
	public void setServiceArea(List<Integer> serviceArea) 
	{
		this.serviceArea=serviceArea;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws GTPParseException 
	{
		if(serviceArea==null || serviceArea.size()==0)
			throw new GTPParseException("service area is not set");
		else
		{
			buffer.writeByte(serviceArea.size());
			for(int i=0;i<serviceArea.size();i++)
				buffer.writeShort(serviceArea.get(i)-1);
		}
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) throws GTPParseException 
	{
		int arrLength=buffer.readByte() & 0x0FF;
		serviceArea=new ArrayList<Integer>(arrLength);
		for(int i=0;i<arrLength;i++)
			serviceArea.add(buffer.readUnsignedShort() + 1);
	}
}