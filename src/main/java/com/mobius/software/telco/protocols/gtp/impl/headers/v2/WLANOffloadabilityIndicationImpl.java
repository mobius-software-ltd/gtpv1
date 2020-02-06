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

import com.mobius.software.telco.protocols.gtp.api.exceptions.MissingArgumentException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.WLANOffloadabilityIndication;

public class WLANOffloadabilityIndicationImpl extends AbstractTLV2 implements WLANOffloadabilityIndication 
{
	private Boolean utranIndication;
	private Boolean eutranIndication;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.WLAN_OFFLOADABILITY_INDICATION;
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
		if(utranIndication!=null && utranIndication)
			currByte|=(byte)0x01;
		
		if(eutranIndication!=null && eutranIndication)
			currByte|=(byte)0x02;
		
		buffer.writeByte(currByte);
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte currByte=buffer.readByte();
		if((currByte & 0x01)!=0)
			utranIndication=true;
		else
			utranIndication=false;
		
		if((currByte & 0x02)!=0)
			eutranIndication=true;
		else
			eutranIndication=false;
	}

	public Boolean getUTRANIndication() 
	{
		return utranIndication;
	}

	public void setUTRANIndication(Boolean value) 
	{
		this.utranIndication = value;
	}

	public Boolean getEUTRANIndication() 
	{
		return eutranIndication;
	}

	public void setEUTRANIndication(Boolean value) 
	{
		this.eutranIndication = value;
	}
}