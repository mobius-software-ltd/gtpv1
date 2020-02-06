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
import com.mobius.software.telco.protocols.gtp.api.headers.ENodeBID;
import com.mobius.software.telco.protocols.gtp.api.headers.ENodeBType;
import com.mobius.software.telco.protocols.gtp.api.headers.ElementType;
import com.mobius.software.telco.protocols.gtp.api.utils.StringFunctions;

public class ENodeBIDImpl extends AbstractTLV implements ENodeBID 
{
	private String mcc;
	private String mnc;
	private ENodeBType nodeType;
	private Integer enodeBID;
	private Integer tac;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.ENODEB_ID;
	}

	@Override
	public Integer getLength() 
	{
		return 6;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return false;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(nodeType==null)
			throw new MissingArgumentException("Node type is not set");
		
		if(mcc==null)
			throw new MissingArgumentException("MCC is not set");
		
		if(mnc==null)
			throw new MissingArgumentException("MNC is not set");
		
		if(enodeBID==null)
			throw new MissingArgumentException("ENodeB ID is not set");
		
		if(tac==null)
			throw new MissingArgumentException("TAC is not set");
		
		buffer.writeByte(nodeType.getValue());
		
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
		
		if(nodeType==ENodeBType.MACRO)
		{
			buffer.writeByte((enodeBID>>16) & 0x0F);
			buffer.writeShort(enodeBID);
		}
		else
			buffer.writeInt(enodeBID & 0x0FFFFFFF);
		
		buffer.writeShort(tac.shortValue());		
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		nodeType=ENodeBType.fromInt(buffer.readByte() & 0x0FF);
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
		
		if(nodeType==ENodeBType.MACRO)
			enodeBID=((buffer.readByte()<<16) & 0x0F0000) | buffer.readUnsignedShort();
		else
			enodeBID=buffer.readInt() & 0x0FFFFFFF;
		
		tac=buffer.readShort() & 0x0FFFF;		
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
	public ENodeBType getENodeBType() 
	{
		return nodeType;
	}

	@Override
	public void setENodeBType(ENodeBType eNodeBType) 
	{
		this.nodeType=eNodeBType;
	}

	@Override
	public Integer eNodeBID() 
	{
		return enodeBID;
	}

	@Override
	public void setENodeBID(Integer eNodeBID) 
	{
		this.enodeBID=eNodeBID;
	}

	@Override
	public Integer getTAC() 
	{
		return tac;
	}

	@Override
	public void setTAC(Integer tac) 
	{
		this.tac=tac;
	}
}
