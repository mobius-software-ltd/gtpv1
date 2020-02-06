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
import com.mobius.software.telco.protocols.gtp.api.headers.TMGI;
import com.mobius.software.telco.protocols.gtp.api.utils.StringFunctions;

public class TMGIImpl extends AbstractTLV implements TMGI 
{
	private String mcc;
	private String mnc;
	private Integer serviceID;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.TMGI;
	}

	@Override
	public Integer getLength() 
	{
		return 6;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return true;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(serviceID==null)
			throw new MissingArgumentException("Service ID is not set");
		
		if(mcc==null)
			throw new MissingArgumentException("MCC is not set");
		
		if(mnc==null)
			throw new MissingArgumentException("MNC is not set");
		
		buffer.writeByte((serviceID>>16) & 0x0FF);
		buffer.writeShort(serviceID.shortValue());
		if(mnc.length()==3)
		{
			String value=mcc+mnc.charAt(2) + mnc.substring(0,2);
			buffer.writeBytes(StringFunctions.octetsToBytes(value));
		}
		else
		{
			buffer.writeBytes(StringFunctions.octetsToBytes(mcc));
			buffer.writeBytes(StringFunctions.octetsToBytes(mnc));
		}			
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		serviceID=(buffer.readByte() <<16) & 0x0FF0000;
		serviceID|=buffer.readShort() & 0x0FFFF;		
		
		byte[] mccmnc=new byte[3];
		buffer.readBytes(mccmnc);
		if((mccmnc[1] & 0xF0)==0xF0)
		{
			byte[] mccBytes=new byte[2];
			System.arraycopy(mccmnc, 0, mccBytes, 0, 2);
			mcc=StringFunctions.octetsToHex(mccBytes);
			byte[] mncBytes=new byte[1];
			mncBytes[0]=mccmnc[2];
			mnc=StringFunctions.octetsToHex(mncBytes);
		}
		else
		{
			String value=StringFunctions.octetsToHex(mccmnc);
			mcc=value.substring(0,3);
			mnc=value.substring(3);
			mnc=mnc.substring(1) + mnc.charAt(0);
		}		
	}

	@Override
	public String getMCC() 
	{
		return this.mcc;
	}

	@Override
	public void setMCC(String mcc) 
	{
		this.mcc=mcc;
	}

	@Override
	public String getMNC() 
	{
		return this.mnc;
	}

	@Override
	public void setMNC(String mnc) 
	{
		this.mnc=mnc;
	}

	@Override
	public Integer getMBMSServiceID() 
	{
		return this.serviceID;
	}

	@Override
	public void setMBMSServiceID(Integer serviceID) 
	{
		this.serviceID=serviceID;
	}
}
