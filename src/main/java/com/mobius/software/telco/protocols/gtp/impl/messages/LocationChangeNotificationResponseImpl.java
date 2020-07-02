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
import com.mobius.software.telco.protocols.gtp.api.headers.CSGInformationReportingAction;
import com.mobius.software.telco.protocols.gtp.api.headers.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.ChangeReportAction;
import com.mobius.software.telco.protocols.gtp.api.headers.IMEISV;
import com.mobius.software.telco.protocols.gtp.api.headers.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.NSAPI;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.messages.LocationChangeNotificationResponse;

public class LocationChangeNotificationResponseImpl extends AbstractGTPMessage implements LocationChangeNotificationResponse
{
	private IMSI imsi;
	private Cause cause;
	private NSAPI linkedNSAPI;
	private IMEISV imeisv;
	private ChangeReportAction changeReportAction;
	private CSGInformationReportingAction csgReportAction;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public MessageType getMessageType() 
	{
		return MessageType.MS_INFO_CHANGE_NOTIFICATION_RESPONSE;
	}

	@Override
	public void applyTLV(TLV1 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case IMSI:
				imsi=(IMSI)tlv;
				break;
			case CAUSE:
				cause=(Cause)tlv;
				break;
			case NSAPI:
				linkedNSAPI=(NSAPI)tlv;
				break;
			case IMEI_SV:
				imeisv=(IMEISV)tlv;
				break;
			case MS_INFO_CHANGE_REPORTING_ACTION:
				changeReportAction=(ChangeReportAction)tlv;
				break;
			case CSG_INFORMATION_REPORTING_ACTION:
				csgReportAction=(CSGInformationReportingAction)tlv;
				break;
			case PRIVATE_EXTENTION:
				if(privateExtentions==null)
					privateExtentions=new ArrayList<PrivateExtention>();
				
				privateExtentions.add((PrivateExtention)tlv);
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
		if(imsi!=null)
			output.add(imsi);
		
		if(cause==null)
			throw new GTPParseException("cause is missing");
		
		output.add(cause);
	
		if(linkedNSAPI!=null)
			output.add(linkedNSAPI);
	
		if(imeisv!=null)
			output.add(imeisv);
	
		if(changeReportAction!=null)
			output.add(changeReportAction);
		
		if(csgReportAction!=null)
			output.add(csgReportAction);
		
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
	public Cause getCause() 
	{
		return this.cause;
	}

	@Override
	public void setCause(Cause cause) 
	{
		this.cause=cause;
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
	public ChangeReportAction getChangeReportAction() 
	{
		return this.changeReportAction;
	}

	@Override
	public void setChangeReportAction(ChangeReportAction action) 
	{
		this.changeReportAction=action;
	}

	@Override
	public CSGInformationReportingAction getCSGInformationReportingAction() 
	{
		return this.csgReportAction;
	}

	@Override
	public void setCSGInformationReportingAction(CSGInformationReportingAction csgInformationReportingAction) 
	{
		this.csgReportAction=csgInformationReportingAction;
	}
}