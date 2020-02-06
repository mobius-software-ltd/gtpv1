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
import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;

import com.mobius.software.telco.protocols.gtp.api.exceptions.MissingArgumentException;
import com.mobius.software.telco.protocols.gtp.api.headers.ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.ExtentionHeaderList;
import com.mobius.software.telco.protocols.gtp.api.headers.ExtentionHeaderType;

public class ExtentionHeaderListImpl extends AbstractTLV implements ExtentionHeaderList 
{
	private List<ExtentionHeaderType> extentionHeaderTypeList;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.EXTENTION_HEADER_TYPE_LIST;
	}

	@Override
	public Integer getLength() 
	{
		if(extentionHeaderTypeList!=null)
			return extentionHeaderTypeList.size();
		
		return 0;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return true;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(extentionHeaderTypeList!=null)
		{
			for(ExtentionHeaderType currType:extentionHeaderTypeList)
				buffer.writeByte(currType.getValue() & 0x0FF);
		}
		else
			throw new MissingArgumentException("extention header type list is not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		extentionHeaderTypeList=new ArrayList<ExtentionHeaderType>();
		for(int i=0;i<length;i++)
			extentionHeaderTypeList.add(ExtentionHeaderType.fromInt(buffer.readByte() & 0x0FF));
	}

	public List<ExtentionHeaderType> getExtentionHeaderTypeList() 
	{
		return extentionHeaderTypeList;
	}

	public void setExtentionHeaderTypeList(List<ExtentionHeaderType> extentionHeaderTypeList) 
	{
		this.extentionHeaderTypeList = extentionHeaderTypeList;
	}
}