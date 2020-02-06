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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RemoteUserID;
import com.mobius.software.telco.protocols.gtp.api.utils.StringFunctions;

public class RemoteUserIDImpl extends AbstractTLV2 implements RemoteUserID 
{
	private String imei;
	private String imsi;
	private String msisdn;

	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.REMOTE_USER_ID;
	}
	
	@Override
	public Integer getLength() 
	{
		Integer length=1;
		if(imei!=null && imei.length()>0)
			length=(imei.length()+1)/2 + 1;
		
		if(msisdn!=null && msisdn.length()>0)
			length=(msisdn.length()+1)/2 + 1;
		
		if(imsi!=null)
			length=(imsi.length()+1)/2 + 1;
		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws GTPParseException 
	{
		if(imsi==null)
			throw new GTPParseException("imsi not set");
		
		byte currByte=0;
		if(msisdn!=null && msisdn.length()>0)
			currByte|=0x01;
		
		if(imei!=null && imei.length()>0)
			currByte|=0x02;
		
		buffer.writeByte(currByte);
		
		buffer.writeByte((imsi.length()+1)/2);
		byte[] data=StringFunctions.octetsToBytes(imsi);
		buffer.writeBytes(data);
		
		if(msisdn!=null && msisdn.length()>0)
		{
			buffer.writeByte((msisdn.length()+1)/2);
			data=StringFunctions.octetsToBytes(msisdn);
			buffer.writeBytes(data);
		}
		
		if(imei!=null && imei.length()>0)
		{
			buffer.writeByte((imei.length()+1)/2);
			data=StringFunctions.octetsToBytes(imei);
			buffer.writeBytes(data);
		}
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) throws GTPParseException 
	{
		byte currByte=buffer.readByte();
		boolean hasMSISDN=(currByte & 0x01)!=0;
		boolean hasIMEI=(currByte & 0x02)!=0;
		
		int imsiLength=buffer.readByte() & 0x0FF;
		byte[] data=new byte[imsiLength];		
		buffer.readBytes(data);
		imsi=StringFunctions.octetsToHex(data);
		
		if(hasMSISDN)
		{
			imsiLength=buffer.readByte() & 0x0FF;
			data=new byte[imsiLength];		
			buffer.readBytes(data);
			msisdn=StringFunctions.octetsToHex(data);			
		}
		
		if(hasIMEI)
		{
			imsiLength=buffer.readByte() & 0x0FF;
			data=new byte[imsiLength];		
			buffer.readBytes(data);
			imei=StringFunctions.octetsToHex(data);			
		}
	}
	
	@Override
	public String getIMSI() 
	{
		return imsi;
	}

	@Override
	public void setIMSI(String imsi) 
	{
		this.imsi=imsi;
	}

	@Override
	public String getMSISDN() 
	{
		return msisdn;
	}

	@Override
	public void setMSISDN(String msisdn) 
	{
		this.msisdn=msisdn;
	}

	@Override
	public String getIMEI() 
	{
		return imei;
	}

	@Override
	public void setIMEI(String imei) 
	{
		this.imei=imei;
	}
}