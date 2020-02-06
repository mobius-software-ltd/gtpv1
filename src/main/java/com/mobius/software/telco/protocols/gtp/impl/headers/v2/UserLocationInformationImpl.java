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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CGI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ECGI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ExtendedMacroENodeBID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.LAI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MacroENodeBID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RAI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SAI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TAI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UserLocationInformation;

public class UserLocationInformationImpl extends AbstractTLV2 implements UserLocationInformation
{
	private CGI cgi;
	private SAI sai;
	private RAI rai;
	private TAI tai;
	private ECGI ecgi;
	private LAI lai;
	private MacroENodeBID macroENodeBID;
	private ExtendedMacroENodeBID extendedMacroENodeBID;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.USER_LOCATION_INFORMATION;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=1;
		if(getHasCGI())
			length+=7;
		
		if(getHasSAI())
			length+=7;
		
		if(getHasRAI())
			length+=7;
		
		if(getHasTAI())
			length+=5;
		
		if(getHasECGI())
			length+=7;
		
		if(getHasLAI())
			length+=5;
		
		if(getHasMacroENodeBID())
			length+=6;
		
		if(getHasExtendedMacroENodeBID())
			length+=6;
		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws GTPParseException 
	{
		byte currByte=0;
		if(getHasCGI())
			currByte|=(byte)0x01;
		
		if(getHasSAI())
			currByte|=(byte)0x02;
		
		if(getHasRAI())
			currByte|=(byte)0x04;
		
		if(getHasTAI())
			currByte|=(byte)0x08;
		
		if(getHasECGI())
			currByte|=(byte)0x10;
		
		if(getHasLAI())
			currByte|=(byte)0x20;
		
		if(getHasMacroENodeBID())
			currByte|=(byte)0x40;
		
		if(getHasExtendedMacroENodeBID())
			currByte|=(byte)0x80;
		
		buffer.writeByte(currByte);
		
		if(getHasCGI())
			cgi.encode(buffer);
		
		if(getHasSAI())
			sai.encode(buffer);
		
		if(getHasRAI())
			rai.encode(buffer);
		
		if(getHasTAI())
			tai.encode(buffer);
		
		if(getHasECGI())
			ecgi.encode(buffer);
		
		if(getHasLAI())
			lai.encode(buffer);
		
		if(getHasMacroENodeBID())
			macroENodeBID.encode(buffer);
		
		if(getHasExtendedMacroENodeBID())
			extendedMacroENodeBID.encode(buffer);				
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) throws GTPParseException 
	{
		byte currByte=buffer.readByte();
		if((currByte & 0x01)!=0)
		{
			cgi=new CGIImpl();
			cgi.decode(buffer);
		}
		
		if((currByte & 0x02)!=0)
		{
			sai=new SAIImpl();
			sai.decode(buffer);
		}
		
		if((currByte & 0x04)!=0)
		{
			rai=new RAIImpl();
			rai.decode(buffer);
		}
		
		if((currByte & 0x08)!=0)
		{
			tai=new TAIImpl();
			tai.decode(buffer);
		}
		
		if((currByte & 0x10)!=0)
		{
			ecgi=new ECGIImpl();
			ecgi.decode(buffer);
		}
		
		if((currByte & 0x20)!=0)
		{
			lai=new LAIImpl();
			lai.decode(buffer);
		}
		
		if((currByte & 0x40)!=0)
		{
			macroENodeBID=new MacroENodeBIDImpl();
			macroENodeBID.decode(buffer);
		}
		
		if((currByte & 0x80)!=0)
		{
			extendedMacroENodeBID=new ExtendedMacroENodeBIDImpl();
			extendedMacroENodeBID.decode(buffer);
		}
	}

	@Override
	public Boolean getHasCGI() 
	{
		return cgi!=null;
	}

	@Override
	public Boolean getHasSAI() 
	{
		return sai!=null;
	}

	@Override
	public Boolean getHasRAI() 
	{
		return rai!=null;
	}

	@Override
	public Boolean getHasTAI() 
	{
		return tai!=null;
	}

	@Override
	public Boolean getHasECGI() 
	{
		return ecgi!=null;
	}

	@Override
	public Boolean getHasLAI() 
	{
		return lai!=null;
	}

	@Override
	public Boolean getHasMacroENodeBID() 
	{
		return macroENodeBID!=null;
	}

	@Override
	public Boolean getHasExtendedMacroENodeBID() 
	{
		return extendedMacroENodeBID!=null;
	}

	@Override
	public CGI getCGI() 
	{
		return cgi;
	}

	@Override
	public void setCGI(CGI cgi) 
	{
		this.cgi=cgi;		
	}

	@Override
	public SAI getSAI() 
	{
		return sai;
	}

	@Override
	public void setSAI(SAI sai) 
	{
		this.sai=sai;
	}

	@Override
	public RAI getRAI() 
	{
		return rai;
	}

	@Override
	public void setRAI(RAI rai) 
	{
		this.rai=rai;
	}

	@Override
	public TAI getTAI() 
	{
		return tai;
	}

	@Override
	public void setTAI(TAI tai) 
	{
		this.tai=tai;
	}

	@Override
	public ECGI getECGI() 
	{
		return ecgi;
	}

	@Override
	public void setECGI(ECGI ecgi) 
	{
		this.ecgi=ecgi;
	}

	@Override
	public LAI getLAI() 
	{
		return lai;
	}

	@Override
	public void setLAI(LAI lai) 
	{
		this.lai=lai;
	}

	@Override
	public MacroENodeBID getMacroENodeBID() 
	{
		return macroENodeBID;				
	}

	@Override
	public void setMacroENodeBID(MacroENodeBID macroENodeBID) 
	{
		this.macroENodeBID=macroENodeBID;
	}

	@Override
	public ExtendedMacroENodeBID getExtendedMacroENodeBID() 
	{
		return this.extendedMacroENodeBID;
	}

	@Override
	public void setExtendedMacroENodeBID(ExtendedMacroENodeBID extendedMacroENodeBID) 
	{
		this.extendedMacroENodeBID=extendedMacroENodeBID;
	}
}