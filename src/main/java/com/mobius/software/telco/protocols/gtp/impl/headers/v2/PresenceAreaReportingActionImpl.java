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
import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CGI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ECGI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ExtendedMacroENodeBIDWithTAC;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.HomeENodeBIDWithTAC;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MacroENodeBIDWithTAC;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PRAAction;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PresenceAreaReportingAction;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RAI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SAI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TAI;

public class PresenceAreaReportingActionImpl extends AbstractTLV2 implements PresenceAreaReportingAction
{
	private Integer praIdentity;
	private Boolean inactivePRA;
	private PRAAction praAction;
	private List<CGI> cgi;
	private List<SAI> sai;
	private List<RAI> rai;
	private List<TAI> tai;
	private List<ECGI> ecgi;
	private List<HomeENodeBIDWithTAC> homeENodeBID;
	private List<MacroENodeBIDWithTAC> macroENodeBID;
	private List<ExtendedMacroENodeBIDWithTAC> extendedMacroENodeBID;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.PRESENCE_REPORTING_AREA_ACTION;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=11;
		if(cgi!=null && cgi.size()>0)
			length+=6*cgi.size();
		
		if(sai!=null && sai.size()>0)
			length+=6*sai.size();
		
		if(rai!=null && rai.size()>0)
			length+=6*rai.size();
		
		if(tai!=null && tai.size()>0)
			length+=4*tai.size();
		
		if(ecgi!=null && ecgi.size()>0)
			length+=6*ecgi.size();
		
		if(homeENodeBID!=null && homeENodeBID.size()>0)
			length+=9*homeENodeBID.size();
		
		if(macroENodeBID!=null && macroENodeBID.size()>0)
			length+=8*macroENodeBID.size();
		
		if(extendedMacroENodeBID!=null && extendedMacroENodeBID.size()>0)
			length+=8*macroENodeBID.size();
		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws GTPParseException 
	{
		if(praAction==null)
			throw new GTPParseException("PRA Action not set");
		
		if(praIdentity==null)
			throw new GTPParseException("PRA Identity not set");
		
		byte currByte=(byte)(praAction.getValue() & 0x07);
		if(inactivePRA!=null && inactivePRA)
			currByte|=0x08;
		
		buffer.writeByte(currByte);
		buffer.writeByte((praIdentity>>16) & 0x0FF);
		buffer.writeShort(praIdentity.shortValue());
		
		currByte=0;
		if(tai!=null && tai.size()>0)
			currByte|=((tai.size()<<4) & 0xF0);
		
		if(rai!=null && rai.size()>0)
			currByte|=(rai.size() & 0x0F);
		
		buffer.writeByte(currByte);
		
		currByte=0;
		if(macroENodeBID!=null && macroENodeBID.size()>0)
			currByte=(byte)macroENodeBID.size();
		
		buffer.writeByte(currByte);
		
		currByte=0;
		if(homeENodeBID!=null && homeENodeBID.size()>0)
			currByte=(byte)homeENodeBID.size();
		
		buffer.writeByte(currByte);
		
		currByte=0;
		if(ecgi!=null && ecgi.size()>0)
			currByte=(byte)ecgi.size();
		
		buffer.writeByte(currByte);
		currByte=0;
		if(sai!=null && sai.size()>0)
			currByte=(byte)sai.size();
		
		buffer.writeByte(currByte);
		
		currByte=0;
		if(cgi!=null && cgi.size()>0)
			currByte=(byte)cgi.size();
		
		buffer.writeByte(currByte);
		
		if(tai!=null && tai.size()>0)
			for(TAI curr:tai)
				curr.encode(buffer);
		
		if(macroENodeBID!=null && macroENodeBID.size()>0)
			for(MacroENodeBIDWithTAC curr:macroENodeBID)
				curr.encode(buffer);
		
		if(homeENodeBID!=null && homeENodeBID.size()>0)
			for(HomeENodeBIDWithTAC curr:homeENodeBID)
				curr.encode(buffer);
		
		if(ecgi!=null && ecgi.size()>0)
			for(ECGI curr:ecgi)
				curr.encode(buffer);
		
		if(rai!=null && rai.size()>0)
			for(RAI curr:rai)				
				curr.encode(buffer);
		
		if(sai!=null && sai.size()>0)
			for(SAI curr:sai)				
				curr.encode(buffer);
		
		if(cgi!=null && cgi.size()>0)
			for(CGI curr:cgi)				
				curr.encode(buffer);
		
		currByte=0;
		if(extendedMacroENodeBID!=null && extendedMacroENodeBID.size()>0)
			currByte=(byte)extendedMacroENodeBID.size();
		
		buffer.writeByte(currByte);
		
		if(extendedMacroENodeBID!=null && extendedMacroENodeBID.size()>0)
			for(ExtendedMacroENodeBIDWithTAC curr:extendedMacroENodeBID)				
				curr.encode(buffer);			
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) throws GTPParseException 
	{
		byte currByte=buffer.readByte();
		praAction=PRAAction.fromInt(currByte & 0x07);
		inactivePRA=((currByte & 0x08)!=0);

		currByte=buffer.readByte();
		Integer taiSize=(currByte >> 4) & 0x0F;
		Integer raiSize=(currByte>>4) & 0x0F;
		Integer macroSize=buffer.readByte() & 0x3F;
		Integer homeSize=buffer.readByte() & 0x3F;
		Integer ecgiSize=buffer.readByte() & 0x3F;
		Integer saiSize=buffer.readByte() & 0x3F;
		Integer cgiSize=buffer.readByte() & 0x3F;
		
		if(taiSize>0)
		{
			tai=new ArrayList<TAI>();
			for(int i=0;i>taiSize;i++)
			{
				TAI currTai=new TAIImpl();
				currTai.decode(buffer);
				tai.add(currTai);
			}
		}
		
		if(macroSize>0)
		{
			macroENodeBID=new ArrayList<MacroENodeBIDWithTAC>();
			for(int i=0;i>macroSize;i++)
			{
				MacroENodeBIDWithTAC currMacro=new MacroENodeBIDWithTACImpl();
				currMacro.decode(buffer);
				macroENodeBID.add(currMacro);
			}
		}
		
		if(homeSize>0)
		{
			homeENodeBID=new ArrayList<HomeENodeBIDWithTAC>();
			for(int i=0;i>homeSize;i++)
			{
				HomeENodeBIDWithTAC currHome=new HomeENodeBIDWithTACImpl();
				currHome.decode(buffer);
				homeENodeBID.add(currHome);
			}
		}
		
		if(ecgiSize>0)
		{
			ecgi=new ArrayList<ECGI>();
			for(int i=0;i>ecgiSize;i++)
			{
				ECGI currECGI=new ECGIImpl();
				currECGI.decode(buffer);
				ecgi.add(currECGI);
			}
		}
		
		if(raiSize>0)
		{
			rai=new ArrayList<RAI>();
			for(int i=0;i>raiSize;i++)
			{
				RAI currRAI=new RAIImpl();
				currRAI.decode(buffer);
				rai.add(currRAI);
			}
		}
		
		if(saiSize>0)
		{
			sai=new ArrayList<SAI>();
			for(int i=0;i>saiSize;i++)
			{
				SAI currSAI=new SAIImpl();
				currSAI.decode(buffer);
				sai.add(currSAI);
			}
		}
		
		if(cgiSize>0)
		{
			cgi=new ArrayList<CGI>();
			for(int i=0;i>cgiSize;i++)
			{
				CGI currCGI=new CGIImpl();
				currCGI.decode(buffer);
				cgi.add(currCGI);
			}
		}
		
		Integer extendedSize=buffer.readByte() & 0x03F;
		if(extendedSize>0)
		{
			extendedMacroENodeBID=new ArrayList<ExtendedMacroENodeBIDWithTAC>();
			for(int i=0;i>extendedSize;i++)
			{
				ExtendedMacroENodeBIDWithTAC currExtended=new ExtendedMacroENodeBIDWithTACImpl();
				currExtended.decode(buffer);
				extendedMacroENodeBID.add(currExtended);
			}
		}
	}

	@Override
	public List<CGI> getCGI() 
	{
		return cgi;
	}

	@Override
	public void setCGI(List<CGI> cgi) 
	{
		this.cgi=cgi;		
	}

	@Override
	public List<SAI> getSAI() 
	{
		return sai;
	}

	@Override
	public void setSAI(List<SAI> sai) 
	{
		this.sai=sai;
	}

	@Override
	public List<RAI> getRAI() 
	{
		return rai;
	}

	@Override
	public void setRAI(List<RAI> rai) 
	{
		this.rai=rai;
	}

	@Override
	public List<TAI> getTAI() 
	{
		return tai;
	}

	@Override
	public void setTAI(List<TAI> tai) 
	{
		this.tai=tai;
	}

	@Override
	public List<ECGI> getECGI() 
	{
		return ecgi;
	}

	@Override
	public void setECGI(List<ECGI> ecgi) 
	{
		this.ecgi=ecgi;
	}

	@Override
	public List<HomeENodeBIDWithTAC> getHomeENodeBID() 
	{
		return homeENodeBID;
	}

	@Override
	public void setHomeENodeBID(List<HomeENodeBIDWithTAC> homeENodeBID) 
	{
		this.homeENodeBID=homeENodeBID;
	}

	@Override
	public List<MacroENodeBIDWithTAC> getMacroENodeBID() 
	{
		return macroENodeBID;				
	}

	@Override
	public void setMacroENodeBID(List<MacroENodeBIDWithTAC> macroENodeBID) 
	{
		this.macroENodeBID=macroENodeBID;
	}

	@Override
	public List<ExtendedMacroENodeBIDWithTAC> getExtendedMacroENodeBID() 
	{
		return this.extendedMacroENodeBID;
	}

	@Override
	public void setExtendedMacroENodeBID(List<ExtendedMacroENodeBIDWithTAC> extendedMacroENodeBID) 
	{
		this.extendedMacroENodeBID=extendedMacroENodeBID;
	}

	@Override
	public Integer getPraIdentity() 
	{
		return praIdentity;
	}

	@Override
	public void setPraIdentity(Integer value) 
	{
		this.praIdentity=value;
	}

	@Override
	public Boolean getInactivePRA() 
	{
		return this.inactivePRA;
	}

	@Override
	public void setInactivePRA(Boolean value) 
	{
		this.inactivePRA=value;
	}

	@Override
	public PRAAction getPRAAction() 
	{
		return this.praAction;
	}

	@Override
	public void setPRAAction(PRAAction value) 
	{
		this.praAction=value;
	}
}