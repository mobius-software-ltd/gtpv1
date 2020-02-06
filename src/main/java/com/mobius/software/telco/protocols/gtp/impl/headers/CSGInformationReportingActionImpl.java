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
import com.mobius.software.telco.protocols.gtp.api.headers.CSGInformationReportingAction;
import com.mobius.software.telco.protocols.gtp.api.headers.ElementType;

public class CSGInformationReportingActionImpl extends AbstractTLV implements CSGInformationReportingAction 
{
	private Boolean uciuhc;
	private Boolean ucishc;
	private Boolean ucicsg;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.CSG_INFORMATION_REPORTING_ACTION;
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
		if(uciuhc!=null && uciuhc)
			currByte|=(byte)0x04;
		
		if(ucishc!=null && ucishc)
			currByte|=(byte)0x02;
		
		if(ucicsg!=null && ucicsg)
			currByte|=(byte)0x01;
		
		buffer.writeByte(currByte);
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte currByte=buffer.readByte();
		if((currByte & 0x04)!=0)
			uciuhc=true;
		else
			uciuhc=false;
		
		if((currByte & 0x02)!=0)
			ucishc=true;
		else
			ucishc=false;
		
		if((currByte & 0x01)!=0)
			ucicsg=true;
		else
			ucicsg=false;
	}

	@Override
	public Boolean getUCIUHC() 
	{
		return uciuhc;
	}

	@Override
	public void setUCIUHC(Boolean value) 
	{
		this.uciuhc=value;
	}

	@Override
	public Boolean getUCISHC() 
	{
		return ucishc;
	}

	@Override
	public void setUCISHC(Boolean value) 
	{
		this.ucishc=value;		
	}

	@Override
	public Boolean getUCICSG() 
	{
		return ucicsg;
	}

	@Override
	public void setUCICSG(Boolean value) 
	{
		ucicsg=value;
	}
}