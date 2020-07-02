package com.mobius.software.telco.protocols.gtp.impl.messages.v2;
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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CSGInformationReportingAction;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ChangeReportingAction;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MEI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PresenceAreaReportingAction;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.ChangeNotificationResponse;

public class ChangeNotificationResponseImpl extends AbstractGTP2Message implements ChangeNotificationResponse
{
	private IMSI imsi;
	private MEI mei;
	private Cause cause;
	private ChangeReportingAction changeReportingAction;
	private CSGInformationReportingAction csgInformationReportingAction;
	private PresenceAreaReportingAction presenceAreaReportingAction;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.CHANGE_NOTIFICATION_RESPONSE;
	}

	@Override
	public void applyTLV(TLV2 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case IMSI:
				imsi=(IMSI)tlv;
				break;
			case CAUSE:
				cause=(Cause)tlv;
				break;
			case CHANGE_REPORTING_ACTION:
				changeReportingAction=(ChangeReportingAction)tlv;
				break;
			case CSG_INFORMATION_REPORTING_ACTION:
				csgInformationReportingAction=(CSGInformationReportingAction)tlv;
				break;
			case MEI:
				mei=(MEI)tlv;
				break;
			case PRESENCE_REPORTING_AREA_ACTION:
				presenceAreaReportingAction=(PresenceAreaReportingAction)tlv;
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
	public List<TLV2> getTLVs() throws GTPParseException 
	{
		ArrayList<TLV2> output=new ArrayList<TLV2>();
		if(imsi!=null)
			output.add(imsi);
		
		if(mei!=null)
			output.add(mei);
			
		if(cause==null)
			throw new GTPParseException("Cause not set");
		
		output.add(cause);
		
		if(changeReportingAction!=null)
			output.add(changeReportingAction);
		
		if(csgInformationReportingAction!=null)
			output.add(csgInformationReportingAction);
		
		if(presenceAreaReportingAction!=null)
			output.add(presenceAreaReportingAction);
		
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
	public Cause getCause() 
	{
		return cause;
	}

	@Override
	public void setCause(Cause cause) 
	{
		cause.setInstance(0);
		this.cause=cause;
	}

	@Override
	public ChangeReportingAction getChangeReportingAction() 
	{
		return changeReportingAction;
	}

	@Override
	public void setChangeReportingAction(ChangeReportingAction changeReportingAction) 
	{
		changeReportingAction.setInstance(0);
		this.changeReportingAction=changeReportingAction;
	}

	@Override
	public CSGInformationReportingAction getCSGInformationReportingAction() 
	{
		return csgInformationReportingAction;
	}

	@Override
	public void setCSGInformationReportingAction(CSGInformationReportingAction action) 
	{
		action.setInstance(0);
		this.csgInformationReportingAction=action;
	}

	@Override
	public MEI getMEI() 
	{
		return mei;
	}

	@Override
	public void setMEI(MEI mei) 
	{
		mei.setInstance(0);
		this.mei=mei;
	}

	@Override
	public PresenceAreaReportingAction getPresenceAreaReportingAction() 
	{
		return this.presenceAreaReportingAction;
	}

	@Override
	public void setPresenceAreaReportingAction(PresenceAreaReportingAction action) 
	{
		action.setInstance(0);
		this.presenceAreaReportingAction=action;
	}

	@Override
	public IMSI getIMSI() 
	{
		return this.imsi;
	}

	@Override
	public void setIMSI(IMSI imsi) 
	{
		imsi.setInstance(0);
		this.imsi=imsi;
	}
}