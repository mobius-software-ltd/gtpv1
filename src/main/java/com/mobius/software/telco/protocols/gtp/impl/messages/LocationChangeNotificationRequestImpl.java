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
import com.mobius.software.telco.protocols.gtp.api.headers.CSGInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.ExtendedCommonFlags;
import com.mobius.software.telco.protocols.gtp.api.headers.IMEISV;
import com.mobius.software.telco.protocols.gtp.api.headers.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.NSAPI;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.RatType;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.headers.UserLocationInformation;
import com.mobius.software.telco.protocols.gtp.api.messages.LocationChangeNotificationRequest;

public class LocationChangeNotificationRequestImpl extends AbstractGTPMessage implements LocationChangeNotificationRequest
{
	private IMSI imsi;
	private NSAPI linkedNSAPI;
	private RatType ratType;
	private UserLocationInformation userLocationInformation;
	private IMEISV imeisv;
	private ExtendedCommonFlags extendedCommonFlags;
	private CSGInformation userCSGInformation;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public MessageType getMessageType() 
	{
		return MessageType.MS_INFO_CHANGE_NOTIFICATION_REQUEST;
	}

	@Override
	public void applyTLV(TLV1 tlv) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case IMSI:
				imsi=(IMSI)tlv;
				break;
			case NSAPI:
				linkedNSAPI=(NSAPI)tlv;
				break;
			case RAT_TYPE:
				ratType=(RatType)tlv;
				break;
			case USER_LOCATION_INFORMATION:
				userLocationInformation=(UserLocationInformation)tlv;
				break;
			case IMEI_SV:
				imeisv=(IMEISV)tlv;
				break;
			case EXTENDED_COMMON_FLAGS:
				extendedCommonFlags=(ExtendedCommonFlags)tlv;
				break;
			case USER_CSG_INFORMATION:
				userCSGInformation=(CSGInformation)tlv;
				break;
			case PRIVATE_EXTENTION:
				if(privateExtentions==null)
					privateExtentions=new ArrayList<PrivateExtention>();
				
				privateExtentions.add((PrivateExtention)tlv);
				break;
			default:
				throw new GTPParseException("Unknown TLV received,type:" + tlv.getElementType());
		}
	}

	@Override
	public List<TLV1> getTLVs() throws GTPParseException 
	{
		ArrayList<TLV1> output=new ArrayList<TLV1>();
		if(imsi!=null)
			output.add(imsi);
		
		if(linkedNSAPI!=null)
			output.add(linkedNSAPI);
	
		if(ratType==null)
			throw new GTPParseException("rat type is missing");
		
		output.add(ratType);
	
		if(userLocationInformation!=null)
			output.add(userLocationInformation);
	
		if(imeisv!=null)
			output.add(imeisv);
	
		if(extendedCommonFlags!=null)
			output.add(extendedCommonFlags);
		
		if(userCSGInformation!=null)
			output.add(userCSGInformation);
		
		if(privateExtentions!=null)
		{
			for(PrivateExtention curr:privateExtentions)
				output.add(curr);
		}
		
		return output;
	}

	@Override
	public List<PrivateExtention> getPrivateExtentions() 
	{
		return privateExtentions;
	}

	@Override
	public void setPrivateExtentions(List<PrivateExtention> privateExtention) 
	{
		this.privateExtentions=privateExtention;
	}

	@Override
	public IMSI getIMSI() 
	{
		return imsi;
	}

	@Override
	public void setIMSI(IMSI imsi) 
	{
		this.imsi=imsi;
	}

	@Override
	public NSAPI getLinkedNSAPI() 
	{
		return linkedNSAPI;
	}

	@Override
	public void setLinkedNSAPI(NSAPI nsapi) 
	{
		this.linkedNSAPI=nsapi;
	}

	@Override
	public RatType getRatType() 
	{
		return this.ratType;
	}

	@Override
	public void setRatType(RatType ratType) 
	{
		this.ratType=ratType;
	}

	@Override
	public UserLocationInformation getUserLocationInformation() 
	{
		return userLocationInformation;
	}

	@Override
	public void setUserLocationInformation(UserLocationInformation locationInformation) 
	{
		this.userLocationInformation=locationInformation;	
	}

	@Override
	public IMEISV getIMEISV() 
	{
		return this.imeisv;
	}

	@Override
	public void setIMEISV(IMEISV imeiSV) 
	{
		this.imeisv=imeiSV;
	}

	@Override
	public ExtendedCommonFlags getExtendedCommonFlags() 
	{
		return extendedCommonFlags;
	}

	@Override
	public void setExtendedCommonFlags(ExtendedCommonFlags flags) 
	{
		this.extendedCommonFlags=flags;
	}

	@Override
	public CSGInformation getCSGInformation() 
	{
		return this.userCSGInformation;
	}

	@Override
	public void setCSGInformation(CSGInformation information) 
	{
		this.userCSGInformation=information;
	}
}