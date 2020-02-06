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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CellID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ExtendedMacroENodeBIDWithTAC;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ExtendedMacroNGNodeBID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GNodeBID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.HomeENodeBIDWithTAC;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MacroENodeBIDWithTAC;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.NGNodeBID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RNCID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TargetIdentification;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TargetType;

public class TargetIdentificationImpl extends AbstractTLV2 implements TargetIdentification
{
	private TargetType targetType;
	private RNCID rncID;
	private MacroENodeBIDWithTAC macroENodeBID;
	private CellID cellID;
	private HomeENodeBIDWithTAC homeENodeBID;
	private ExtendedMacroENodeBIDWithTAC extendedMacroENodeBID;
	private GNodeBID gNodeBID;
	private NGNodeBID ngNodeBID;
	private ExtendedMacroNGNodeBID extendedMacroNGNodeBID;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.TARGET_IDENTIFICATION;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=0;
		if(rncID!=null)
		{
			if(rncID.getExtendedRNCID()!=null)
				length+=10;
			else
				length+=8;
		}
		else if(macroENodeBID!=null)
			length+=8;
		else if(cellID!=null)
			length+=8;
		else if(homeENodeBID!=null)
			length+=9;
		else if(extendedMacroENodeBID!=null)
			length+=8;
		else if(gNodeBID!=null)
			length+=11;
		else if(ngNodeBID!=null)
			length+=9;
		else if(extendedMacroENodeBID!=null)
			length+=9;
		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws GTPParseException 
	{
		if(rncID!=null)
		{
			buffer.writeByte(0);
			rncID.encode(buffer);
		} 
		else if(macroENodeBID!=null)
		{
			buffer.writeByte(1);
			macroENodeBID.encode(buffer);
		} 
		else if(cellID!=null)
		{
			buffer.writeByte(2);
			cellID.encode(buffer);
		} 
		else if(homeENodeBID!=null)
		{
			buffer.writeByte(3);
			homeENodeBID.encode(buffer);
		} 
		else if(extendedMacroENodeBID!=null)
		{
			buffer.writeByte(4);
			extendedMacroENodeBID.encode(buffer);
		}
		else if(gNodeBID!=null)
		{
			buffer.writeByte(5);
			gNodeBID.encode(buffer);
		}
		else if(ngNodeBID!=null)
		{
			buffer.writeByte(6);
			ngNodeBID.encode(buffer);
		}
		else if(extendedMacroNGNodeBID!=null)
		{
			buffer.writeByte(7);
			extendedMacroNGNodeBID.encode(buffer);
		}			
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) throws GTPParseException 
	{
		targetType=TargetType.fromInt(buffer.readByte() & 0x0FF);
		switch(targetType)
		{
			case RNCID:
				rncID=new RNCIDImpl();
				rncID.decode(buffer);
				if(length==11)
					rncID.setExtendedRNCID(buffer.readUnsignedShort());
				break;
			case CELLID:
				cellID=new CellIDImpl();
				cellID.decode(buffer);
				break;
			case EXTENDED_MACRO_ENODEBID:
				extendedMacroENodeBID=new ExtendedMacroENodeBIDWithTACImpl();
				extendedMacroENodeBID.decode(buffer);
				break;
			case EXTENDED_MACRO_NG_ENODEBID:
				extendedMacroNGNodeBID=new ExtendedMacroNGNodeBIDImpl();
				extendedMacroNGNodeBID.decode(buffer);
				break;
			case GNODEBID:
				gNodeBID=new GNodeBIDImpl();
				gNodeBID.decode(buffer);
				break;
			case HOME_ENODEBID:
				homeENodeBID=new HomeENodeBIDWithTACImpl();
				homeENodeBID.decode(buffer);
				break;
			case MACRO_ENODEBID:
				macroENodeBID=new MacroENodeBIDWithTACImpl();
				macroENodeBID.decode(buffer);
				break;
			case MACRO_NG_ENODEBID:
				ngNodeBID=new NGNodeBIDImpl();
				ngNodeBID.decode(buffer);
				break;
			case UNKNOWN:
				break;
			default:
				break;			
		}
	}

	@Override
	public HomeENodeBIDWithTAC getHomeENodeBID() 
	{
		return homeENodeBID;
	}

	@Override
	public void setHomeENodeBID(HomeENodeBIDWithTAC homeENodeBID) 
	{
		this.targetType=TargetType.HOME_ENODEBID;
		this.homeENodeBID=homeENodeBID;
	}

	@Override
	public MacroENodeBIDWithTAC getMacroENodeBID() 
	{
		return macroENodeBID;				
	}

	@Override
	public void setMacroENodeBID(MacroENodeBIDWithTAC macroENodeBID) 
	{
		this.targetType=TargetType.MACRO_ENODEBID;
		this.macroENodeBID=macroENodeBID;
	}

	@Override
	public ExtendedMacroENodeBIDWithTAC getExtendedMacroENodeBID() 
	{
		return this.extendedMacroENodeBID;
	}

	@Override
	public void setExtendedMacroENodeBID(ExtendedMacroENodeBIDWithTAC extendedMacroENodeBID) 
	{
		this.targetType=TargetType.EXTENDED_MACRO_ENODEBID;
		this.extendedMacroENodeBID=extendedMacroENodeBID;
	}

	@Override
	public RNCID getRNCID() 
	{
		return rncID;
	}

	@Override
	public void setRNCID(RNCID rncID) 
	{
		this.targetType=TargetType.RNCID;
		this.rncID=rncID;
	}

	@Override
	public CellID getCellID() 
	{
		return this.cellID;
	}

	@Override
	public void setCellID(CellID cellID) 
	{
		this.targetType=TargetType.CELLID;
		this.cellID=cellID;
	}

	@Override
	public GNodeBID getGNodeBID() 
	{
		return gNodeBID;
	}

	@Override
	public void setGNodeBID(GNodeBID gNodeBID) 
	{
		this.targetType=TargetType.GNODEBID;
		this.gNodeBID=gNodeBID;
	}

	@Override
	public NGNodeBID getNGNodeBID() 
	{
		return ngNodeBID;
	}

	@Override
	public void setNGNodeBID(NGNodeBID ngNodeBID) 
	{
		this.targetType=TargetType.MACRO_NG_ENODEBID;
		this.ngNodeBID=ngNodeBID;
	}

	@Override
	public ExtendedMacroNGNodeBID getExtendedMacroNGNodeBID() 
	{
		return extendedMacroNGNodeBID;
	}

	@Override
	public void setExtendedMacroNGNodeBID(ExtendedMacroNGNodeBID extendedMacroNGNodeBID) 
	{
		this.targetType=TargetType.EXTENDED_MACRO_NG_ENODEBID;
		this.extendedMacroNGNodeBID=extendedMacroNGNodeBID;
	}

	@Override
	public TargetType getTargetType() 
	{
		return targetType;
	}
}