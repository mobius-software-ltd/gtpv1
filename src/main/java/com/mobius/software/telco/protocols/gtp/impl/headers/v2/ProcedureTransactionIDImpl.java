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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ProcedureTransactionID;

public class ProcedureTransactionIDImpl extends AbstractTLV2 implements ProcedureTransactionID 
{
	private Integer pti;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.PROCEDURE_TRANSACTION_ID;
	}

	@Override
	public Integer getLength() 
	{
		return 1;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(pti!=null)
			buffer.writeInt(pti.byteValue());			
		else
			throw new MissingArgumentException("PTI is not set");			
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		pti=buffer.readByte() & 0x0FF;			
	}

	@Override
	public Integer getPTI() 
	{
		return pti;
	}

	@Override
	public void setPTI(Integer pti) 
	{
		this.pti=pti;
	}
}