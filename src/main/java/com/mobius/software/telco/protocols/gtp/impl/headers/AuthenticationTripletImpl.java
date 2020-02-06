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
import com.mobius.software.telco.protocols.gtp.api.headers.AuthenticationTriplet;
import com.mobius.software.telco.protocols.gtp.api.headers.ElementType;

public class AuthenticationTripletImpl extends AbstractTLV implements AuthenticationTriplet 
{
	private byte[] rand;
	private byte[] sres;
	private byte[] kc;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.AUTHENTICATION_TRIPLET;
	}

	@Override
	public Integer getLength() 
	{
		return 28;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return false;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(rand!=null)
		{
			if(rand.length==16)
				buffer.writeBytes(rand);
			else
				throw new MissingArgumentException("Rand has invalid length");
		}
		else
			throw new MissingArgumentException("Rand is not set");
		
		if(sres!=null)
		{
			if(sres.length==4)
				buffer.writeBytes(sres);
			else
				throw new MissingArgumentException("SRES has invalid length");
		}
		else
			throw new MissingArgumentException("SRES is not set");
		
		if(kc!=null)
		{
			if(kc.length==8)
				buffer.writeBytes(kc);
			else
				throw new MissingArgumentException("Kc has invalid length");
		}
		else
			throw new MissingArgumentException("Kc is not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		rand=new byte[16];
		buffer.readBytes(rand);
		
		sres=new byte[4];
		buffer.readBytes(sres);
		
		kc=new byte[8];
		buffer.readBytes(kc);
	}

	@Override
	public byte[] getRand() 
	{
		return rand;
	}

	@Override
	public void setRand(byte[] rand) 
	{
		this.rand=rand;
	}

	@Override
	public byte[] getSRES() 
	{
		return sres;
	}

	@Override
	public void setSRES(byte[] sres) 
	{
		this.sres=sres;
	}

	@Override
	public byte[] getKc() 
	{
		return kc;
	}

	@Override
	public void setKc(byte[] kc) 
	{
		this.kc=kc;
	}
}