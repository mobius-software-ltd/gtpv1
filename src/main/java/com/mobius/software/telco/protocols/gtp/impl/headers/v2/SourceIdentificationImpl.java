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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RNCID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SourceIdentification;

public class SourceIdentificationImpl extends AbstractTLV2 implements SourceIdentification
{
	private CellID targetCellID;
	private CellID sourceCellID;
	private RNCID sourceRNCID;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.SOURCE_IDENTIFICATION;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=8;
		if(sourceRNCID!=null)
		{
			if(sourceRNCID.getExtendedRNCID()!=null)
				length+=10;
			else
				length+=8;
		}
		else if(sourceCellID!=null)
			length+=8;
		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws GTPParseException 
	{
		if(targetCellID==null)
			throw new GTPParseException("Target Cell ID not cell");
		
		if(sourceCellID==null && sourceRNCID==null)
			throw new GTPParseException("Source Cell ID and RNC ID not cell");
		
		targetCellID.decode(buffer);
		if(sourceCellID!=null)
		{
			buffer.writeByte(0);
			sourceCellID.encode(buffer);
		} 
		else if(sourceRNCID!=null)
		{
			buffer.writeByte(1);
			sourceRNCID.encode(buffer);
		} 		
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) throws GTPParseException 
	{
		targetCellID=new CellIDImpl();
		targetCellID.decode(buffer);
		
		byte sourceType=buffer.readByte();
		if(sourceType==0)
		{
			sourceRNCID=new RNCIDImpl();
			sourceRNCID.decode(buffer);
			if(length==19)
				sourceRNCID.setExtendedRNCID(buffer.readUnsignedShort());			
		}
		else
		{
			sourceCellID=new CellIDImpl();
			sourceCellID.decode(buffer);
		}
	}

	@Override
	public CellID getTargetCellID() 
	{
		return this.targetCellID;
	}

	@Override
	public void setTargetCellID(CellID cellID) 
	{
		this.targetCellID=cellID;
	}

	@Override
	public CellID getSourceCellID() 
	{
		return this.sourceCellID;
	}

	@Override
	public void setSourceCellID(CellID cellID) 
	{
		this.sourceCellID=cellID;
	}

	@Override
	public RNCID getSourceRNCID() 
	{
		return this.sourceRNCID;
	}

	@Override
	public void setSourceRNCID(RNCID rncID) 
	{
		this.sourceRNCID=rncID;
	}
}