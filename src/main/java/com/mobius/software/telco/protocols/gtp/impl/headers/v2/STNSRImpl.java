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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.NumberPlan;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.STNSR;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TypeOfNetwork;
import com.mobius.software.telco.protocols.gtp.api.utils.StringFunctions;

public class STNSRImpl extends AbstractTLV2 implements STNSR 
{
	private String address;
	private TypeOfNetwork ton;
	private NumberPlan np;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.STN_SR;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=1;
		String value=address;
		if(value!=null)
			length=(value.length()+1)/2;
		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(ton==null)
			throw new MissingArgumentException("Type of network not set");
		
		if(np==null)
			throw new MissingArgumentException("Number plan not set");
		
		byte currByte=(byte)(np.getValue() & 0x0F);
		currByte|=(byte)((ton.getValue() & 0x7)<<4);
		buffer.writeByte(currByte);
		
		if(address!=null)
			buffer.writeBytes(StringFunctions.octetsToBytes(address));
		else
			throw new MissingArgumentException("Address not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte currByte=buffer.readByte();
		ton=TypeOfNetwork.fromInt((currByte>>4) & 0x07);
		np=NumberPlan.fromInt(currByte & 0x0F);
		byte[] data;
		if(length!=null)
			data=new byte[length-1];
		else
			data=new byte[getLength()-1];
		
		buffer.readBytes(data);
	}

	@Override
	public String getAddress() 
	{
		return address;
	}

	@Override
	public void setAddress(String address) 
	{
		this.address=address;
	}

	@Override
	public TypeOfNetwork getTypeOfNetwork() 
	{
		return this.ton;
	}

	@Override
	public void setTypeOfNetwork(TypeOfNetwork ton) 
	{
		this.ton=ton;
	}

	@Override
	public NumberPlan getNumberPlan() 
	{
		return this.np;
	}

	@Override
	public void setNumberPlan(NumberPlan np) 
	{
		this.np=np;
	}
}