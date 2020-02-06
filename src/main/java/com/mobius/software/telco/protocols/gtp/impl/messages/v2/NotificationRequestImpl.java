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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.HandoverIndicator;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SessionID2;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.NotificationRequest;

public class NotificationRequestImpl extends AbstractGTP2Message implements NotificationRequest
{
	IMSI sessionID;
	SessionID2 sessionID2;
	HandoverIndicator handoverIndicator;
	private Recovery recovery;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.NOTIFICATION_REQUEST;
	}

	@Override
	public void applyTLV(TLV2 tlv) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case IMSI:
				sessionID=(IMSI)tlv;
				break;
			case SESSION_ID_2:
				sessionID2=(SessionID2)tlv;
				break;
			case HANDOVER_INDICATOR:
				handoverIndicator=(HandoverIndicator)tlv;
				break;
			case RECOVERY:
				recovery=(Recovery)tlv;
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
	public List<TLV2> getTLVs() throws GTPParseException 
	{
		ArrayList<TLV2> output=new ArrayList<TLV2>();
		if(sessionID!=null)
			output.add(sessionID);
		
		if(sessionID2!=null)
			output.add(sessionID2);
		
		if(handoverIndicator==null)
			throw new GTPParseException("Cause not set");
		
		output.add(handoverIndicator);
		if(recovery!=null)
			output.add(recovery);
		
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
	public Recovery getRecovery() 
	{
		return recovery;
	}

	@Override
	public void setRecovery(Recovery recovery) 
	{
		this.recovery=recovery;
		this.recovery.setInstance(0);
	}

	@Override
	public IMSI getSessionID() 
	{
		return this.sessionID;
	}

	@Override
	public void setSessionID(IMSI imsi) 
	{
		imsi.setInstance(0);
		this.sessionID=imsi;
	}

	@Override
	public SessionID2 getSessionID2() 
	{
		return this.sessionID2;
	}

	@Override
	public void setSessionID2(SessionID2 sessionID2) 
	{
		sessionID2.setInstance(0);
		this.sessionID2=sessionID2;
	}

	@Override
	public HandoverIndicator getHandoverIndicator() 
	{
		return this.handoverIndicator;
	}

	@Override
	public void setHandoverIndicator(HandoverIndicator handoverIndicator) 
	{
		handoverIndicator.setInstance(0);
		this.handoverIndicator=handoverIndicator;
	}
}