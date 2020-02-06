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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.BearerQos;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ECGIList;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MBMSFlags;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MBMSFlowIdentifier;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MBMSIPMulticastDistribution;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MBMSServiceArea;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MBMSSessionDuration;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MBMSSessionIdentifier;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MBMSTimeToDataTransfer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TMGI;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.MBMSSessionStartRequest;

public class MBMSSessionStartRequestImpl extends AbstractGTP2Message implements MBMSSessionStartRequest
{
	FTEID senderFTEIDControlPlane;
	TMGI tmgi;
	MBMSSessionDuration mbmsSessionDuration;
	MBMSServiceArea mbmsServiceArea;
	MBMSSessionIdentifier mbmsSessionIdentifier;
	MBMSFlowIdentifier mbmsFlowIdentifier;
	BearerQos bearerQos;
	MBMSIPMulticastDistribution mbmsIPMulticastDistribution;
	Recovery recovery;
	MBMSTimeToDataTransfer mbmsTimeToDataTransfer;
	AbsoluteTimeOfMBMSDataTransfer mbmsDataTransferStart;
	MBMSFlags mbmsFlags;
	MBMSIPMulticastDistribution alternativeMBMSIPMulticastDistribution;
	ECGIList mbmsCellList;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.MBMS_SESSION_START_REQUEST;
	}

	@Override
	public void applyTLV(TLV2 tlv) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case FTEID:
				switch(tlv.getInstance())
				{
					case 0:
						senderFTEIDControlPlane=(FTEID)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case TMGI:
				tmgi=(TMGI)tlv;
				break;
			case MBMS_SESSION_DURATION:
				mbmsSessionDuration=(MBMSSessionDuration)tlv;
				break;
			case MBMS_SERVICE_AREA:
				mbmsServiceArea=(MBMSServiceArea)tlv;
				break;
			case MBMS_SESSION_IDENTIFIER:
				mbmsSessionIdentifier=(MBMSSessionIdentifier)tlv;
				break;
			case MBMS_FLOW_IDENTIFIER:
				mbmsFlowIdentifier=(MBMSFlowIdentifier)tlv;
				break;
			case BEARER_QOS:
				bearerQos=(BearerQos)tlv;
				break;
			case MBMS_IP_MULTICAST_DISTRIBUTION:
				switch(tlv.getInstance())
				{
					case 0:
						mbmsIPMulticastDistribution=(MBMSIPMulticastDistribution)tlv;
						break;
					case 1:
						alternativeMBMSIPMulticastDistribution=(MBMSIPMulticastDistribution)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case RECOVERY:
				recovery=(Recovery)tlv;
				break;
			case MBMS_TIME_TO_DATA_TRANSFER:
				mbmsTimeToDataTransfer=(MBMSTimeToDataTransfer)tlv;
				break;
			case ABSOLUTE_TIME_OF_MBMS_DATA_TRANSFER:
				mbmsDataTransferStart=(AbsoluteTimeOfMBMSDataTransfer)tlv;
				break;
			case MBMS_FLAGS:
				mbmsFlags=(MBMSFlags)tlv;
				break;			
			case ECGI_LIST:
				mbmsCellList=(ECGIList)tlv;				
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
		if(senderFTEIDControlPlane==null)
			throw new GTPParseException("sender FTEID CP not set");
		
		output.add(senderFTEIDControlPlane);
		
		if(tmgi==null)
			throw new GTPParseException("TMGI not set");
		
		output.add(tmgi);
		
		if(mbmsSessionDuration==null)
			throw new GTPParseException("MBMS Session Duration not set");
		
		output.add(mbmsSessionDuration);
		
		if(mbmsServiceArea==null)
			throw new GTPParseException("MBMS Service Area not set");
		
		output.add(mbmsServiceArea);
		
		if(mbmsSessionIdentifier!=null)
			output.add(mbmsSessionIdentifier);
		
		if(mbmsFlowIdentifier!=null)
			output.add(mbmsFlowIdentifier);
		
		if(bearerQos==null)
			throw new GTPParseException("Bearer QOS not set");
		
		output.add(bearerQos);
		
		if(mbmsIPMulticastDistribution==null)
			throw new GTPParseException("MBMS IP Multicast Distribution not set");
		
		output.add(mbmsIPMulticastDistribution);
		
		if(recovery!=null)
			output.add(recovery);
		
		if(mbmsTimeToDataTransfer!=null)
			output.add(mbmsTimeToDataTransfer);
		
		if(mbmsDataTransferStart!=null)
			output.add(mbmsDataTransferStart);
		
		if(mbmsFlags!=null)
			output.add(mbmsFlags);
		
		if(alternativeMBMSIPMulticastDistribution!=null)
			output.add(alternativeMBMSIPMulticastDistribution);
		
		if(mbmsCellList!=null)
			output.add(mbmsCellList);
		
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
	public FTEID getSenderFTEIDControlPlane() 
	{
		return senderFTEIDControlPlane;
	}

	@Override
	public void setSenderFTEIDControlPlane(FTEID fteid) 
	{
		fteid.setInstance(0);
		this.senderFTEIDControlPlane=fteid;
	}

	@Override
	public TMGI getTMGI() 
	{
		return tmgi;
	}

	@Override
	public void setTMGI(TMGI tmgi) 
	{
		tmgi.setInstance(0);
		this.tmgi=tmgi;
	}

	@Override
	public MBMSSessionDuration getMBMSSessionDuration() 
	{
		return mbmsSessionDuration;
	}

	@Override
	public void setMBMSSessionDuration(MBMSSessionDuration mbmsSessionDuration) 
	{
		mbmsSessionDuration.setInstance(0);
		this.mbmsSessionDuration=mbmsSessionDuration;
	}

	@Override
	public MBMSServiceArea getMBMSServiceArea() 
	{
		return this.mbmsServiceArea;
	}

	@Override
	public void setMBMSServiceArea(MBMSServiceArea mbmsServiceArea) 
	{
		mbmsServiceArea.setInstance(0);
		this.mbmsServiceArea=mbmsServiceArea;
	}

	@Override
	public MBMSSessionIdentifier getMBMSSessionIdentifier() 
	{
		return this.mbmsSessionIdentifier;
	}

	@Override
	public void setMBMSSessionIdentifier(MBMSSessionIdentifier mbmsSessionIdentifier) 
	{
		mbmsSessionIdentifier.setInstance(0);
		this.mbmsSessionIdentifier=mbmsSessionIdentifier;
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
	public BearerQos getBearerQos() 
	{
		return bearerQos;
	}

	@Override
	public void setBearerQos(BearerQos bearerQos) 
	{
		bearerQos.setInstance(0);
		this.bearerQos=bearerQos;
	}

	@Override
	public MBMSIPMulticastDistribution getMBMSIPMulticastDistribution() 
	{
		return mbmsIPMulticastDistribution;
	}

	@Override
	public void setMBMSIPMulticastDistribution(MBMSIPMulticastDistribution mbmsIPMulticastDistribution) 
	{
		mbmsIPMulticastDistribution.setInstance(0);
		this.mbmsIPMulticastDistribution=mbmsIPMulticastDistribution;
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
	public MBMSTimeToDataTransfer getMBMSTimeToDataTransfer() 
	{
		return this.mbmsTimeToDataTransfer;
	}

	@Override
	public void setMBMSTimeToDataTransfer(MBMSTimeToDataTransfer mbmsTimeToDataTransfer) 
	{
		mbmsTimeToDataTransfer.setInstance(0);
		this.mbmsTimeToDataTransfer=mbmsTimeToDataTransfer;
	}

	@Override
	public AbsoluteTimeOfMBMSDataTransfer getMBMSDataTransferStart() 
	{
		return this.mbmsDataTransferStart;
	}

	@Override
	public void setMBMSDataTransferStart(AbsoluteTimeOfMBMSDataTransfer mbmsDataTransferStart) 
	{
		mbmsDataTransferStart.setInstance(0);
		this.mbmsDataTransferStart=mbmsDataTransferStart;
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

	@Override
	public MBMSIPMulticastDistribution getAlternativeMBMSIPMulticastDistribution() 
	{
		return alternativeMBMSIPMulticastDistribution;
	}

	@Override
	public void setAlternativeMBMSIPMulticastDistribution(MBMSIPMulticastDistribution mbmsIPMulticastDistribution) 
	{
		mbmsIPMulticastDistribution.setInstance(1);
		this.mbmsIPMulticastDistribution=mbmsIPMulticastDistribution;
	}

	@Override
	public ECGIList getMBMSCellList() 
	{
		return mbmsCellList;
	}

	@Override
	public void setMBMSCellList(ECGIList mbmsCellList) 
	{
		mbmsCellList.setInstance(0);
		this.mbmsCellList=mbmsCellList;
	}
}