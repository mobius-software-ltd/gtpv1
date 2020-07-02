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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.AbsoluteTimeOfMBMSDataTransfer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MBMSFlags;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MBMSFlowIdentifier;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.MBMSSessionStopRequest;

public class MBMSSessionStopRequestImpl extends AbstractGTP2Message implements MBMSSessionStopRequest
{
	MBMSFlowIdentifier mbmsFlowIdentifier;
	AbsoluteTimeOfMBMSDataTransfer mbmsDataTransferStop;
	MBMSFlags mbmsFlags;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.MBMS_SESSION_STOP_REQUEST;
	}

	@Override
	public void applyTLV(TLV2 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case MBMS_FLOW_IDENTIFIER:
				mbmsFlowIdentifier=(MBMSFlowIdentifier)tlv;
				break;
			case ABSOLUTE_TIME_OF_MBMS_DATA_TRANSFER:
				mbmsDataTransferStop=(AbsoluteTimeOfMBMSDataTransfer)tlv;
				break;
			case MBMS_FLAGS:
				mbmsFlags=(MBMSFlags)tlv;
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
		if(mbmsFlowIdentifier!=null)
			output.add(mbmsFlowIdentifier);
		
		if(mbmsDataTransferStop!=null)
			output.add(mbmsDataTransferStop);
		
		if(mbmsFlags!=null)
			output.add(mbmsFlags);
		
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
	public MBMSFlowIdentifier getMBMSFlowIdentifier() 
	{
		return mbmsFlowIdentifier;
	}

	@Override
	public void setMBMSFlowIdentifier(MBMSFlowIdentifier mbmsFlowIdentifier) 
	{
		mbmsFlowIdentifier.setInstance(0);
		this.mbmsFlowIdentifier=mbmsFlowIdentifier;
	}

	@Override
	public AbsoluteTimeOfMBMSDataTransfer getMBMSDataTransferStop() 
	{
		return this.mbmsDataTransferStop;
	}

	@Override
	public void setMBMSDataTransferStop(AbsoluteTimeOfMBMSDataTransfer mbmsDataTransferStop) 
	{
		mbmsDataTransferStop.setInstance(0);
		this.mbmsDataTransferStop=mbmsDataTransferStop;
	}

	@Override
	public MBMSFlags getMBMSFlags() 
	{
		return mbmsFlags;
	}

	@Override
	public void setMBMSFlags(MBMSFlags mbmsFlags) 
	{
		mbmsFlags.setInstance(0);
		this.mbmsFlags=mbmsFlags;
	}
}