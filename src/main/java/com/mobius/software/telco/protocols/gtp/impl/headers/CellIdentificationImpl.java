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
import com.mobius.software.telco.protocols.gtp.api.headers.CellIdentification;
import com.mobius.software.telco.protocols.gtp.api.headers.ElementType;
import com.mobius.software.telco.protocols.gtp.api.utils.StringFunctions;

public class CellIdentificationImpl extends AbstractTLV implements CellIdentification 
{
	private String targetMcc;
	private String targetMnc;
	private Integer targetLac;
	private Integer targetRac;
	private Integer targetCI;	
	
	private String sourceMcc;
	private String sourceMnc;
	private Integer sourceLac;
	private Integer sourceRac;
	private Integer sourceCI;
	private Integer sourceRncID;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.CELL_IDENTIFICATION;
	}

	@Override
	public Integer getLength() 
	{
		return 17;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return true;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(targetMcc==null)
			throw new MissingArgumentException("Target MCC is not set");
		
		if(targetMnc==null)
			throw new MissingArgumentException("Target MNC is not set");
		
		if(targetLac==null)
			throw new MissingArgumentException("Target LAC is not set");
		
		if(targetRac==null)
			throw new MissingArgumentException("Target RAC is not set");
		
		if(targetCI==null)
			throw new MissingArgumentException("Target rncID is not set");
		
		if(sourceMcc==null)
			throw new MissingArgumentException("Source MCC is not set");
		
		if(sourceMnc==null)
			throw new MissingArgumentException("Source MNC is not set");
		
		if(sourceLac==null)
			throw new MissingArgumentException("Source LAC is not set");
		
		if(sourceRac==null)
			throw new MissingArgumentException("Source RAC is not set");
		
		if(sourceCI==null && sourceRncID==null)
			throw new MissingArgumentException("Source CI And RncID are not set");
		
		if(targetMnc.length()==3)
		{
			String value=targetMcc+targetMnc.charAt(2) + targetMnc.substring(0,2);
			buffer.writeBytes(StringFunctions.octetsToBytes(value));
		}
		else
		{
			buffer.writeBytes(StringFunctions.octetsToBytes(targetMcc));
			buffer.writeBytes(StringFunctions.octetsToBytes(targetMnc));
		}
		buffer.writeShort(targetLac.shortValue());
		buffer.writeByte(targetRac.byteValue());
		buffer.writeShort(targetCI.shortValue() & 0x0FFF);	
		
		if(sourceRncID!=null)
			buffer.writeByte(1);
		else
			buffer.writeByte(0);
		
		if(sourceMnc.length()==3)
		{
			String value=sourceMcc+sourceMnc.charAt(2) + sourceMnc.substring(0,2);
			buffer.writeBytes(StringFunctions.octetsToBytes(value));
		}
		else
		{
			buffer.writeBytes(StringFunctions.octetsToBytes(sourceMcc));
			buffer.writeBytes(StringFunctions.octetsToBytes(sourceMnc));
		}
		buffer.writeShort(sourceLac.shortValue());
		buffer.writeByte(sourceRac.byteValue());
		
		if(sourceRncID!=null)
			buffer.writeShort(sourceRncID.shortValue() & 0x0FFFF);
		else
			buffer.writeShort(sourceCI.shortValue() & 0x0FFFF);
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte[] mccmnc=new byte[3];
		buffer.readBytes(mccmnc);
		if((mccmnc[1] & 0xF0)==0xF0)
		{
			byte[] mccBytes=new byte[2];
			System.arraycopy(mccmnc, 0, mccBytes, 0, 2);
			targetMcc=StringFunctions.octetsToHex(mccBytes);
			byte[] mncBytes=new byte[1];
			mncBytes[0]=mccmnc[2];
			targetMnc=StringFunctions.octetsToHex(mncBytes);
		}
		else
		{
			String value=StringFunctions.octetsToHex(mccmnc);
			targetMcc=value.substring(0,3);
			targetMnc=value.substring(3);
			targetMnc=targetMnc.substring(1) + targetMnc.charAt(0);
		}
		
		targetLac=buffer.readShort() & 0x0FFFF;
		targetRac=buffer.readByte() & 0x0FF;
		targetCI=buffer.readShort() & 0x0FFF;
		
		Boolean isRNCID=(buffer.readByte()==0x01);
		mccmnc=new byte[3];
		buffer.readBytes(mccmnc);
		if((mccmnc[1] & 0xF0)==0xF0)
		{
			byte[] mccBytes=new byte[2];
			System.arraycopy(mccmnc, 0, mccBytes, 0, 2);
			sourceMcc=StringFunctions.octetsToHex(mccBytes);
			byte[] mncBytes=new byte[1];
			mncBytes[0]=mccmnc[2];
			sourceMnc=StringFunctions.octetsToHex(mncBytes);
		}
		else
		{
			String value=StringFunctions.octetsToHex(mccmnc);
			sourceMcc=value.substring(0,3);
			sourceMnc=value.substring(3);
			sourceMnc=sourceMnc.substring(1) + sourceMnc.charAt(0);
		}
		sourceLac=buffer.readShort() & 0x0FFFF;
		sourceRac=buffer.readByte() & 0x0FF;
		if(isRNCID)
			sourceRncID=buffer.readShort() & 0x0FFFF;
		else
			sourceCI=buffer.readShort() & 0x0FFFF;
	}

	@Override
	public String getTargetMCC() 
	{
		return this.targetMcc;
	}

	@Override
	public void setTargetMCC(String mcc) 
	{
		this.targetMcc=mcc;
	}

	@Override
	public String getTargetMNC() 
	{
		return this.targetMnc;
	}

	@Override
	public void setTargetMNC(String mnc) 
	{
		this.targetMnc=mnc;
	}

	@Override
	public Integer getTargetLAC() 
	{
		return this.targetLac;
	}

	@Override
	public void setTargetLAC(Integer lac) 
	{
		this.targetLac=lac;
	}

	@Override
	public Integer getTargetRAC() 
	{
		return this.targetRac;
	}

	@Override
	public void setTargetRAC(Integer rac) 
	{
		this.targetRac=rac;
	}
	
	@Override
	public Integer getTargetCI() 
	{
		return this.targetCI;
	}

	@Override
	public void setTargetCI(Integer ci) 
	{
		this.targetCI=ci;
	}
	
	@Override
	public String getSourceMCC() 
	{
		return this.sourceMcc;
	}

	@Override
	public void setSourceMCC(String mcc) 
	{
		this.sourceMcc=mcc;
	}

	@Override
	public String getSourceMNC() 
	{
		return this.sourceMnc;
	}

	@Override
	public void setSourceMNC(String mnc) 
	{
		this.sourceMnc=mnc;
	}

	@Override
	public Integer getSourceLAC() 
	{
		return this.sourceLac;
	}

	@Override
	public void setSourceLAC(Integer lac) 
	{
		this.sourceLac=lac;
	}

	@Override
	public Integer getSourceRAC() 
	{
		return this.sourceRac;
	}

	@Override
	public void setSourceRAC(Integer rac) 
	{
		this.sourceRac=rac;
	}
	
	@Override
	public Integer getSourceRNCID() 
	{
		return this.sourceRncID;
	}

	@Override
	public void setSourceRNCID(Integer rncID) 
	{
		this.sourceRncID=rncID;
	}
	
	@Override
	public Integer getSourceCI() 
	{
		return this.sourceCI;
	}

	@Override
	public void setSourceCI(Integer ci) 
	{
		this.sourceCI=ci;
	}
}