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
import com.mobius.software.telco.protocols.gtp.api.headers.RABContext;

public class RABContextImpl extends AbstractTLV implements RABContext 
{
	private Integer nsapi;
	private Integer downGTPUsequence;
	private Integer upGTPUsequence;
	private Integer downPDCPsequence;
	private Integer upPDCPsequence;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.RAB_CONTEXT;
	}

	@Override
	public Integer getLength() 
	{
		return 9;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return false;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(nsapi!=null)
			buffer.writeByte(nsapi.byteValue() & 0x0F);
		else
			throw new MissingArgumentException("NSAPI is not set");
		
		if(downGTPUsequence!=null)
			buffer.writeShort(downGTPUsequence);
		else
			throw new MissingArgumentException("Down GTPU sequence is not set");
		
		if(upGTPUsequence!=null)
			buffer.writeShort(upGTPUsequence);
		else
			throw new MissingArgumentException("Up GTPU sequence is not set");
	
		if(downPDCPsequence!=null)
			buffer.writeShort(downPDCPsequence);
		else
			throw new MissingArgumentException("Down PDCP sequence is not set");
		
		if(upPDCPsequence!=null)
			buffer.writeShort(upPDCPsequence);
		else
			throw new MissingArgumentException("Up PDCP sequence is not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		nsapi=(buffer.readByte() & 0x0F);
		downGTPUsequence=buffer.readUnsignedShort();
		upGTPUsequence=buffer.readUnsignedShort();
		downPDCPsequence=buffer.readUnsignedShort();
		upPDCPsequence=buffer.readUnsignedShort();
	}

	@Override
	public Integer getNSAPI() 
	{
		return nsapi;
	}

	@Override
	public void setNSAPI(Integer nsapi) 
	{
		this.nsapi=nsapi;
	}

	@Override
	public void setDownGTPUSequence(Integer sequence) 
	{
		this.downGTPUsequence=sequence;
	}

	@Override
	public Integer getDownGTPUSequence() 
	{
		return downGTPUsequence;
	}

	@Override
	public void setUpGTPUSequence(Integer sequence) 
	{
		this.upGTPUsequence=sequence;
	}

	@Override
	public Integer getUpGTPUSequence() 
	{
		return upGTPUsequence;
	}

	@Override
	public void setDownPDCPSequence(Integer sequence) 
	{
		this.downPDCPsequence=sequence;
	}

	@Override
	public Integer getDownPDCPSequence() 
	{
		return this.downPDCPsequence;
	}

	@Override
	public void setUpPDCPSequence(Integer sequence) 
	{
		this.upPDCPsequence=sequence;
	}

	@Override
	public Integer getUpPDCPSequence() 
	{
		return this.upPDCPsequence;
	}
}