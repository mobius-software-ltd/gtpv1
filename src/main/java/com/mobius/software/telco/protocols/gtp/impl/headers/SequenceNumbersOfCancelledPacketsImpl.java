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
import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;

import com.mobius.software.telco.protocols.gtp.api.exceptions.MissingArgumentException;
import com.mobius.software.telco.protocols.gtp.api.headers.ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.SequenceNumbersOfCancelledPackets;

public class SequenceNumbersOfCancelledPacketsImpl extends AbstractTLV implements SequenceNumbersOfCancelledPackets 
{
	private List<Integer> sequences;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.SEQUENCE_NUMBER_OF_CANCELLED_PACKETS;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=0;
		
		if(sequences!=null)
			length=sequences.size()*2;
		
		return length;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return true;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(sequences!=null)
		{
			for(int i=0;i<sequences.size();i++)
				buffer.writeShort(sequences.get(i));
		}
		else
			throw new MissingArgumentException("sequences are not set");		
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		sequences=new ArrayList<Integer>();
		int records=length/2;
		for(int i=0;i<records;i++)
			sequences.add(buffer.readUnsignedShort());		
	}

	@Override
	public List<Integer> getRecords() 
	{
		return sequences;
	}

	@Override
	public void setRecords(List<Integer> value) 
	{
		this.sequences=value;
	}
}
