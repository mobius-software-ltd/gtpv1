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

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.PriorityLevel;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.AllocationRetentionPriority;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;

public class AllocationRetentionPriorityImpl extends AbstractTLV2 implements AllocationRetentionPriority 
{
	Boolean pvi;
	Boolean pci;
	PriorityLevel level;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.ALLOCATION_RETENTION_PRIORITY;
	}



	@Override
	public Integer getLength() 
	{
		return 1;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws GTPParseException 
	{
		if(level==null)
			throw new GTPParseException("Priority level not set");
		
		byte currByte=0;
		if(pvi!=null && pvi)
			currByte|=0x01;
		
		if(pci!=null && pci)
			currByte|=0x40;
		
		currByte|=((level.getValue()<<2) & 0x3C);
		buffer.writeByte(currByte);		
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) throws GTPParseException 
	{
		byte currByte=buffer.readByte();
		pvi=((currByte&0x01)!=0);
		pci=((currByte&0x40)!=0);
		
		level=PriorityLevel.fromInt((currByte>>2) & 0x0F);		
	}

	@Override
	public Boolean getPVI() 
	{
		return pvi;
	}

	@Override
	public void setPVI(Boolean value) 
	{
		this.pvi=value;
	}

	@Override
	public Boolean getPCI() 
	{
		return pci;
	}

	@Override
	public void setPCI(Boolean value) 
	{
		this.pci=value;
	}

	@Override
	public PriorityLevel getLevel() 
	{
		return level;
	}

	@Override
	public void setLevel(PriorityLevel level) 
	{
		this.level=level;
	}
}