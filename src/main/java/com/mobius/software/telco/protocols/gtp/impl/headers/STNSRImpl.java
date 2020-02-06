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
import com.mobius.software.telco.protocols.gtp.api.headers.NumberPlan;
import com.mobius.software.telco.protocols.gtp.api.headers.STNSR;
import com.mobius.software.telco.protocols.gtp.api.headers.TypeOfNetwork;
import com.mobius.software.telco.protocols.gtp.api.utils.StringFunctions;

public class STNSRImpl extends AbstractTLV implements STNSR 
{
	private String msisdn;
	private TypeOfNetwork ton;
	private NumberPlan np;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.STN_SR;
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
		String value=getMSISDN();
		if(value!=null)
			length+=(value.length()+1)/2;
		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		byte firstByte=0;
		if(getNP()==null)
			throw new MissingArgumentException("NP not set");
		
		if(getTON()==null)
			throw new MissingArgumentException("TON not set");
		
		firstByte|=(byte)((getTON().getValue() <<4) & 0x70);
		firstByte|=getNP().getValue()&0x0F;
		
		buffer.writeByte(firstByte);
		if(getMSISDN()!=null)
			buffer.writeBytes(StringFunctions.octetsToBytes(getMSISDN()));
		else
			throw new MissingArgumentException("MSISDN not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte[] data;
		if(length!=null)
			data=new byte[length-1];
		else
			data=new byte[getLength()-1];
		
		byte firstByte=buffer.readByte();
		np=NumberPlan.fromInt((firstByte>>4) & 0x07);
		ton=TypeOfNetwork.fromInt(firstByte & 0x0F);
		
		buffer.readBytes(data);
		setMSISDN(StringFunctions.octetsToHex(data));
	}

	@Override
	public String getMSISDN() 
	{
		return msisdn;
	}

	public void setMSISDN(String msisdn) 
	{	
		this.msisdn = msisdn;
	}

	public TypeOfNetwork getTON() 
	{
		return ton;
	}

	public void setTON(TypeOfNetwork ton) 
	{
		this.ton = ton;
	}

	public NumberPlan getNP() 
	{
		return np;
	}

	public void setNP(NumberPlan np) 
	{
		this.np = np;
	}	
}