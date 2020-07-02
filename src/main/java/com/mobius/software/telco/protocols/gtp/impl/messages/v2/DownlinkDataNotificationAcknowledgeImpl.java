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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Throttling;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.DelayValue;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPCTimer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IntegerNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.DownlinkDataNotificationAcknowledge;

public class DownlinkDataNotificationAcknowledgeImpl extends AbstractGTP2Message implements DownlinkDataNotificationAcknowledge
{
	Cause cause;
	DelayValue dataNotificationDelay;
	Recovery recovery;
	Throttling lowPriorityTrafficThrottling;
	IMSI imsi;
	EPCTimer dlBufferDuration;
	IntegerNumber dlBufferingSuggestedPacketCount;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.DOWNLINK_DATA_NOTIFICATION_ACKNOWLEDGE;
	}

	@Override
	public void applyTLV(TLV2 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case CAUSE:
				cause=(Cause)tlv;
				break;
			case IMSI:
				imsi=(IMSI)tlv;
				break;
			case ALLOCATION_RETENTION_PRIORITY:
				lowPriorityTrafficThrottling=(Throttling)tlv;
				break;
			case INDICATION:
				dlBufferDuration=(EPCTimer)tlv;
				break;
			case RECOVERY:
				switch(tlv.getInstance())
				{
					case 0:
						recovery=(Recovery)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case EPS_BEARER_ID:
				dataNotificationDelay=(DelayValue)tlv;
				break;
			case LOAD_CONTROL_INFORMATION:
				switch(tlv.getInstance())
				{
					case 0:
						dlBufferingSuggestedPacketCount=(IntegerNumber)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
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
		if(cause==null)
			throw new GTPParseException("Cause is not set");
		
		output.add(cause);
		
		if(dataNotificationDelay!=null)
			output.add(dataNotificationDelay);
			
		if(recovery!=null)
			output.add(recovery);
		
		if(lowPriorityTrafficThrottling!=null)
			output.add(lowPriorityTrafficThrottling);
		
		if(imsi!=null)
			output.add(imsi);
		
		if(dlBufferDuration!=null)
			output.add(dlBufferDuration);
		
		if(dlBufferingSuggestedPacketCount!=null)
			output.add(dlBufferingSuggestedPacketCount);
		
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
	public IMSI getIMSI() 
	{
		return imsi;
	}

	@Override
	public void setIMSI(IMSI imsi) 
	{
		imsi.setInstance(0);
		this.imsi=imsi;
	}

	@Override
	public Throttling getLowPriorityTrafficThrottling() 
	{
		return this.lowPriorityTrafficThrottling;
	}

	@Override
	public void setLowPriorityTrafficThrottling(Throttling lowPriorityTrafficThrottling) 
	{
		lowPriorityTrafficThrottling.setInstance(0);
		this.lowPriorityTrafficThrottling=lowPriorityTrafficThrottling;
	}

	@Override
	public EPCTimer getDLBufferDuration() 
	{
		return this.dlBufferDuration;
	}

	@Override
	public void setDLBufferDuration(EPCTimer dlBufferDuration) 
	{
		dlBufferDuration.setInstance(0);
		this.dlBufferDuration=dlBufferDuration;
	}

	@Override
	public Recovery getRecovery() 
	{
		return this.recovery;
	}

	@Override
	public void setRecovery(Recovery recovery) 
	{
		recovery.setInstance(0);
		this.recovery=recovery;
	}

	@Override
	public DelayValue getDataNotificationDelay() 
	{
		return dataNotificationDelay;
	}

	@Override
	public void setDataNotificationDelay(DelayValue dataNotificationDelay) 
	{
		dataNotificationDelay.setInstance(0);
		this.dataNotificationDelay=dataNotificationDelay;
	}

	@Override
	public IntegerNumber getDLBufferingSuggestedPacketCount() 
	{
		return this.dlBufferingSuggestedPacketCount;
	}

	@Override
	public void setDLBufferingSuggestedPacketCount(IntegerNumber dlBufferingSuggestedPacketCount) 
	{
		dlBufferingSuggestedPacketCount.setInstance(0);
		this.dlBufferingSuggestedPacketCount=dlBufferingSuggestedPacketCount;
	}
}