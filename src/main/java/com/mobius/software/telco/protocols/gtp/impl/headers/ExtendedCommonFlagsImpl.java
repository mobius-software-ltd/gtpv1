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
import com.mobius.software.telco.protocols.gtp.api.headers.ExtendedCommonFlags;

public class ExtendedCommonFlagsImpl extends AbstractTLV implements ExtendedCommonFlags 
{
	private Boolean pcri;
	private Boolean vb;
	private Boolean retLoc;
	private Boolean cpsr;
	private Boolean ccrsi;
	private Boolean unauthenticatedIMSI;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.EXTENDED_COMMON_FLAGS;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return true;
	}

	@Override
	public Integer getLength() 
	{
		return 1;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		byte currByte=0;
		if(pcri!=null && pcri)
			currByte|=(byte)0x20;
		
		if(vb!=null && vb)
			currByte|=(byte)0x10;
		
		if(retLoc!=null && retLoc)
			currByte|=(byte)0x08;
		
		if(cpsr!=null && cpsr)
			currByte|=(byte)0x04;
		
		if(ccrsi!=null && ccrsi)
			currByte|=(byte)0x02;
		
		if(unauthenticatedIMSI!=null && unauthenticatedIMSI)
			currByte|=(byte)0x01;
		
		buffer.writeByte(currByte);
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte currByte=buffer.readByte();
		if((currByte & 0x20)!=0)
			pcri=true;
		else
			pcri=false;
		
		if((currByte & 0x10)!=0)
			vb=true;
		else
			vb=false;
		
		if((currByte & 0x08)!=0)
			retLoc=true;
		else
			retLoc=false;
		
		if((currByte & 0x04)!=0)
			cpsr=true;
		else
			cpsr=false;
		
		if((currByte & 0x02)!=0)
			ccrsi=true;
		else
			ccrsi=false;
		
		if((currByte & 0x01)!=0)
			unauthenticatedIMSI=true;
		else
			unauthenticatedIMSI=false;
	}

	public Boolean getPCRI() 
	{
		return pcri;
	}

	public void setPCRI(Boolean pcri) 
	{
		this.pcri = pcri;
	}

	public Boolean getVB() 
	{
		return vb;
	}

	public void setVB(Boolean vb) 
	{
		this.vb = vb;
	}

	public Boolean getRetLoc() 
	{
		return retLoc;
	}

	public void setRetLoc(Boolean retLoc) 
	{
		this.retLoc = retLoc;
	}

	public Boolean getCPSR() 
	{
		return cpsr;
	}

	public void setCPSR(Boolean cpsr) 
	{
		this.cpsr = cpsr;
	}

	public Boolean getCCRSI() 
	{
		return ccrsi;
	}

	public void setCCRSI(Boolean ccrsi) 
	{
		this.ccrsi = ccrsi;
	}

	public Boolean getUnauthenticatedIMSI() 
	{
		return unauthenticatedIMSI;
	}

	public void setUnauthenticatedIMSI(Boolean unathenticatedIMSI) 
	{
		this.unauthenticatedIMSI = unathenticatedIMSI;
	}
}