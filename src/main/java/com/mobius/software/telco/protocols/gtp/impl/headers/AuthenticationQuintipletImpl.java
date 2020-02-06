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
import com.mobius.software.telco.protocols.gtp.api.headers.AuthenticationQuintiplet;
import com.mobius.software.telco.protocols.gtp.api.headers.ElementType;

public class AuthenticationQuintipletImpl extends AbstractTLV implements AuthenticationQuintiplet 
{
	private byte[] rand;
	private byte[] xres;
	private byte[] ck;
	private byte[] ik;
	private byte[] autn;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.AUTHENTICATION_QUINTIPLET;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=50;
		if(xres!=null)
			length+=xres.length;
		
		if(autn!=null)
			length+=autn.length;
		
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
		if(rand!=null)
		{
			if(rand.length==16)
				buffer.writeBytes(rand);
			else
				throw new MissingArgumentException("Rand has invalid length");
		}
		else
			throw new MissingArgumentException("Rand is not set");
		
		if(xres!=null)
		{
			buffer.writeByte(xres.length);
			buffer.writeBytes(xres);			
		}
		else
			throw new MissingArgumentException("XRES is not set");
		
		if(ck!=null)
		{
			if(ck.length==16)
				buffer.writeBytes(ck);
			else
				throw new MissingArgumentException("CK has invalid length");
		}
		else
			throw new MissingArgumentException("CK is not set");
		
		if(ik!=null)
		{
			if(ik.length==16)
				buffer.writeBytes(ik);
			else
				throw new MissingArgumentException("IK has invalid length");
		}
		else
			throw new MissingArgumentException("IK is not set");
		
		if(autn!=null)
		{
			buffer.writeByte(autn.length);
			buffer.writeBytes(autn);			
		}
		else
			throw new MissingArgumentException("AUTN is not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		rand=new byte[16];
		buffer.readBytes(rand);
		
		Integer xresLen=buffer.readByte() & 0x0FF;
		xres=new byte[xresLen];
		buffer.readBytes(xres);
		
		ck=new byte[16];
		buffer.readBytes(ck);
		
		ik=new byte[16];
		buffer.readBytes(ik);
		
		Integer autnLen=buffer.readByte() & 0x0FF;
		autn=new byte[autnLen];
		buffer.readBytes(autn);
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
	public byte[] getXRES() 
	{
		return xres;
	}

	@Override
	public void setXRES(byte[] xres) 
	{
		this.xres=xres;
	}

	@Override
	public byte[] getCK() 
	{
		return ck;
	}

	@Override
	public void setCK(byte[] ck) 
	{
		this.ck=ck;
	}

	@Override
	public byte[] getIK() 
	{
		return ik;
	}

	@Override
	public void setIK(byte[] ik) 
	{
		this.ik=ik;
	}

	@Override
	public byte[] getAUTN() 
	{
		return autn;
	}

	@Override
	public void setAUTN(byte[] autn) 
	{
		this.autn=autn;
	}
}