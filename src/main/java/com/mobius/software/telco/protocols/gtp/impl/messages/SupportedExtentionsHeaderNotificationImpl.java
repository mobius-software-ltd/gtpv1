package com.mobius.software.telco.protocols.gtp.impl.messages;
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

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.ExtentionHeaderList;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.messages.SupportedExtentionHeadersNotification;
import com.mobius.software.telco.protocols.gtp.impl.headers.ExtentionHeaderListImpl;

public class SupportedExtentionsHeaderNotificationImpl extends AbstractGTPMessage implements SupportedExtentionHeadersNotification
{
	private ExtentionHeaderList extentionHeadersList;
	
	@Override
	public MessageType getMessageType() 
	{
		return MessageType.SUPPORTED_EXTENSION_HEADERS_NOTIFICATION;
	}

	@Override
	public void applyTLV(TLV1 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case EXTENTION_HEADER_TYPE_LIST:
				extentionHeadersList=(ExtentionHeaderListImpl)tlv;
				break;
			default:
				if(ignoreUnknown==null || !ignoreUnknown)
					throw new GTPParseException("Unknown TLV received,type:" + tlv.getElementType());
		}
	}

	@Override
	public List<TLV1> getTLVs() throws GTPParseException 
	{
		ArrayList<TLV1> output=new ArrayList<TLV1>();
		if(extentionHeadersList==null)
			throw new GTPParseException("extention header is missing");
		
		output.add(extentionHeadersList);
		return output;
	}

	public ExtentionHeaderList getExtentionHeadersList() 
	{
		return extentionHeadersList;
	}

	public void setExtentionHeadersList(ExtentionHeaderList extentionHeadersList) 
	{
		this.extentionHeadersList = extentionHeadersList;
	}	
}