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

import com.mobius.software.telco.protocols.gtp.api.exceptions.MissingArgumentException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ChangeReportActionType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ChangeReportingAction;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;

public class ChangeReportingActionImpl extends AbstractTLV2 implements ChangeReportingAction 
{
	private List<ChangeReportActionType> changeReportingActionType;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.CHANGE_REPORTING_ACTION;
	}

	@Override
	public Integer getLength() 
	{
		if(changeReportingActionType==null)
			return 0;
		
		return changeReportingActionType.size();
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(changeReportingActionType!=null)
		{
			for(ChangeReportActionType curr:changeReportingActionType)
			buffer.writeByte(curr.getValue() & 0x0FF);
		}
		else
			throw new MissingArgumentException("Change reporting action type is not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		changeReportingActionType=new ArrayList<ChangeReportActionType>(length);
		for(int i=0;i<length;i++)
			changeReportingActionType.add(ChangeReportActionType.fromInt(buffer.readByte() & 0x0FF));
	}

	@Override
	public List<ChangeReportActionType> getChangeReportActionType() 
	{
		return changeReportingActionType;
	}

	@Override
	public void setChangeReportActionType(List<ChangeReportActionType> value) 
	{
		this.changeReportingActionType=value;
	}
}