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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CNOperatorSelectionEntity;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SelectionEntity;

public class CNOperatorSelectorEntityImpl extends AbstractTLV2 implements CNOperatorSelectionEntity 
{
	private SelectionEntity selectionEntity;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.CN_OPERATOR_SELECTION_ENTITY;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=1;
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(selectionEntity!=null)
			buffer.writeByte(selectionEntity.getValue() & 0x03);
		else
			buffer.writeByte(0);				
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		selectionEntity=SelectionEntity.fromInt((buffer.readByte() & 0x03));		
	}

	@Override
	public SelectionEntity getSelectionEntity() 
	{
		return selectionEntity;
	}

	@Override
	public void setSelectionEntity(SelectionEntity value) 
	{
		this.selectionEntity=value;
	}
}