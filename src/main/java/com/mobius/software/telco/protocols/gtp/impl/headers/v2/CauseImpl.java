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
import com.mobius.software.telco.protocols.gtp.api.exceptions.MissingArgumentException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CauseType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;

public class CauseImpl extends AbstractTLV2 implements Cause 
{
	private CauseType cause;
	private GTP2ElementType offendingIEElementType;
	private Integer offendingIEInstance;
	private Boolean pdnConnectionIEError;
	private Boolean bearerContextIEError;
	private Boolean causeSource;
	private byte spareByte;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.CAUSE;
	}

	@Override
	public Integer getLength() 
	{
		if(offendingIEElementType!=null)
			return 6;
		
		return 2;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(cause!=null)
			buffer.writeByte(cause.getValue() & 0x0FF);
		else
			throw new MissingArgumentException("Cause is not set");
		
		byte currValue=spareByte;
		if(causeSource!=null && causeSource)
			currValue|=0x01;
		
		if(bearerContextIEError!=null && bearerContextIEError)
			currValue|=0x02;
		
		if(pdnConnectionIEError!=null && pdnConnectionIEError)
			currValue|=0x04;
		
		buffer.writeByte(currValue);
		
		if(offendingIEElementType!=null)
		{
			buffer.writeByte(offendingIEElementType.getValue());
			buffer.writeShort(0);
			if(offendingIEInstance!=null)
				buffer.writeByte(offendingIEInstance);
			else
				buffer.writeByte(0);
		}
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) throws GTPParseException 
	{
		cause=CauseType.fromInt(buffer.readByte() & 0x0FF);
		byte currByte=buffer.readByte();
		spareByte=(byte)(currByte & 0xF8);
		causeSource=((currByte & 0x01) !=0);
		bearerContextIEError=((currByte & 0x02) !=0);
		pdnConnectionIEError=((currByte & 0x04) !=0);
		
		if(length==6)
		{
			offendingIEElementType=GTP2ElementType.fromInt(buffer.readByte() & 0x0FF);
			buffer.readShort();
			offendingIEInstance=buffer.readByte() & 0x0FF;
		}
	}

	public CauseType getCauseType() 
	{
		return cause;
	}

	public void setCauseType(CauseType cause) 
	{
		this.cause = cause;
	}

	public GTP2ElementType getOffendingIEElementType() 
	{
		return offendingIEElementType;
	}

	public void setOffendingIEElementType(GTP2ElementType offendingIEElementType) 
	{
		this.offendingIEElementType = offendingIEElementType;
	}

	public Integer getOffendingIEInstance() 
	{
		return offendingIEInstance;
	}

	public void setOffendingIEInstance(Integer offendingIEInstance) 
	{
		this.offendingIEInstance = offendingIEInstance;
	}

	public Boolean getPDNConnectionIEError() 
	{
		return pdnConnectionIEError;
	}

	public void setPDNConnectionIEError(Boolean pdnConnectionIEError) 
	{
		this.pdnConnectionIEError = pdnConnectionIEError;
	}

	public Boolean getBearerContextIEError() 
	{
		return bearerContextIEError;
	}

	public void setBearerContextIEError(Boolean bearerContextIEError) 
	{
		this.bearerContextIEError = bearerContextIEError;
	}

	public Boolean getCauseSource() 
	{
		return causeSource;
	}

	public void setCauseSource(Boolean causeSource) 
	{
		this.causeSource = causeSource;
	}		
}