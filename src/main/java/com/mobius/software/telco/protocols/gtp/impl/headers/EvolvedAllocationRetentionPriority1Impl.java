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
import com.mobius.software.telco.protocols.gtp.api.headers.EvolvedAllocationRetentionPriority1;
import com.mobius.software.telco.protocols.gtp.api.headers.PriorityLevel;

public class EvolvedAllocationRetentionPriority1Impl extends AbstractTLV implements EvolvedAllocationRetentionPriority1 
{
	private PriorityLevel priorityLevel;
	
	private Boolean preemptionVulnerability;
	
	private Boolean preemptionCapability;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.EVOLVED_ALLOCATION_RETENTION_PRIORITY_1;
	}

	@Override
	public Integer getLength() 
	{
		return 1;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return true;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		byte value=0;
		if(preemptionCapability!=null && preemptionCapability)
			value|=0x40;
		
		if(priorityLevel!=null)
			value|=(priorityLevel.getValue()<<2) & 0x3C;
			
		if(preemptionVulnerability!=null && preemptionVulnerability)
			value|=0x01;
		
		buffer.writeByte(value);
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte value=buffer.readByte();
		if((value&0x40)!=0)
			preemptionCapability=true;
		else
			preemptionCapability=false;
		
		priorityLevel=PriorityLevel.fromInt((value>>2)&0x0F);
		
		if((value&0x01)!=0)
			preemptionVulnerability=true;
		else
			preemptionVulnerability=false;
	}

	public PriorityLevel getPriorityLevel() 
	{
		return priorityLevel;
	}

	public void setPriorityLevel(PriorityLevel priorityLevel) 
	{
		this.priorityLevel = priorityLevel;
	}

	public Boolean getPreemptionVulnerability() 
	{
		return preemptionVulnerability;
	}

	public void setPreemptionVulnerability(Boolean preemptionVulnerability) 
	{
		this.preemptionVulnerability = preemptionVulnerability;
	}

	public Boolean getPreemptionCapability() 
	{
		return preemptionCapability;
	}

	public void setPreemptionCapability(Boolean preemptionCapability) 
	{
		this.preemptionCapability = preemptionCapability;
	}		
}