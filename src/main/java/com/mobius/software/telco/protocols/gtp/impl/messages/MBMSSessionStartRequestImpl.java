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
import com.mobius.software.telco.protocols.gtp.api.headers.APN;
import com.mobius.software.telco.protocols.gtp.api.headers.CommonFlags;
import com.mobius.software.telco.protocols.gtp.api.headers.EndUserAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.GSNAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.MBMS23GIndicator;
import com.mobius.software.telco.protocols.gtp.api.headers.MBMSFlowIdentifier;
import com.mobius.software.telco.protocols.gtp.api.headers.MBMSIPMulticastDistribution;
import com.mobius.software.telco.protocols.gtp.api.headers.MBMSServiceArea;
import com.mobius.software.telco.protocols.gtp.api.headers.MBMSSessionDuration;
import com.mobius.software.telco.protocols.gtp.api.headers.MBMSSessionIdentifier;
import com.mobius.software.telco.protocols.gtp.api.headers.MBMSSessionRepetionNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.MBMSTimeToDataTransfer;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.QosProfile;
import com.mobius.software.telco.protocols.gtp.api.headers.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.headers.TMGI;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifierControlPane;
import com.mobius.software.telco.protocols.gtp.api.messages.MBMSSessionStartRequest;

public class MBMSSessionStartRequestImpl extends AbstractGTPMessage implements MBMSSessionStartRequest
{
	private Recovery recovery;
	private TunnerEndpointIdentifierControlPane controlTEI;
	private EndUserAddress endUserAddress;
	private APN apn;
	private GSNAddress ggsnAddressForSignaling;
	private GSNAddress alternateGgsnAddressForSignaling;
	private QosProfile qosProfile;
	private CommonFlags commonFlags;
	private TMGI tmgi;
	private MBMSServiceArea mbmsServiceArea;
	private MBMSSessionIdentifier mbmsSessionIdentifier;
	private MBMS23GIndicator mbms23GIndicator;
	private MBMSSessionDuration mbmsSessionDuration;
	private MBMSSessionRepetionNumber mbmsSessionRepetionNumber;
	private MBMSTimeToDataTransfer mbmsTimeToDataTransfer;
	private MBMSFlowIdentifier mbmsFlowIdentifier;
	private MBMSIPMulticastDistribution mbmsIPMulticastDistribution;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public MessageType getMessageType() 
	{
		return MessageType.MBMS_SESSION_START_REQUEST;
	}

	@Override
	public void applyTLV(TLV1 tlv) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case RECOVERY:
				recovery=(Recovery)tlv;
				break;
			case TEICP:
				controlTEI=(TunnerEndpointIdentifierControlPane)tlv;
				break;
			case END_USER_ADDRESS:
				endUserAddress=(EndUserAddress)tlv;
				break;
			case APN:
				apn=(APN)tlv;
				break;
			case GSN_ADDRESS:
				if(ggsnAddressForSignaling==null)
					ggsnAddressForSignaling=(GSNAddress)tlv;
				else if(alternateGgsnAddressForSignaling==null)
					alternateGgsnAddressForSignaling=(GSNAddress)tlv;
				else
					throw new GTPParseException("Unknown TLV received,type:" + tlv.getElementType());
				break;
			case QOS_PROFILE:
				qosProfile=(QosProfile)tlv;
				break;
			case COMMON_FLAGS:
				commonFlags=(CommonFlags)tlv;
				break;
			case TMGI:
				tmgi=(TMGI)tlv;
				break;
			case MBMS_SERVICE_AREA:
				mbmsServiceArea=(MBMSServiceArea)tlv;
				break;
			case MBMS_SESSION_IDENTIFIER:
				mbmsSessionIdentifier=(MBMSSessionIdentifier)tlv;
				break;
			case MBMS_2G_3G_INDICATOR:
				mbms23GIndicator=(MBMS23GIndicator)tlv;
				break;
			case MBMS_SESSION_DURATION:
				mbmsSessionDuration=(MBMSSessionDuration)tlv;
				break;
			case MBMS_SESSION_REPETITION_NUMBER:
				mbmsSessionRepetionNumber=(MBMSSessionRepetionNumber)tlv;
				break;
			case MBMS_TIME_TO_DATA_TRANSFER:
				mbmsTimeToDataTransfer=(MBMSTimeToDataTransfer)tlv;
				break;
			case MBMS_FLOW_IDENTIFIER:
				mbmsFlowIdentifier=(MBMSFlowIdentifier)tlv;
				break;
			case MBMS_IP_MULTICAST_DISTRIBUTION:
				mbmsIPMulticastDistribution=(MBMSIPMulticastDistribution)tlv;
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
		if(recovery!=null)
			output.add(recovery);
	
		if(controlTEI!=null)
			output.add(controlTEI);
	
		if(endUserAddress==null)
			throw new GTPParseException("End user address is not set");
		
		output.add(endUserAddress);
	
		if(apn==null)
			throw new GTPParseException("apn is not set");
		
		output.add(apn);
	
		if(ggsnAddressForSignaling!=null)
			output.add(ggsnAddressForSignaling);
	
		if(alternateGgsnAddressForSignaling!=null)
			output.add(alternateGgsnAddressForSignaling);
	
		if(qosProfile==null)
			throw new GTPParseException("Qos Profile is not set");
		
		output.add(qosProfile);
	
		if(commonFlags==null)
			throw new GTPParseException("Common flags is not set");
	
		output.add(commonFlags);
	
		if(tmgi==null)
			throw new GTPParseException("TMGI is not set");
	
		output.add(tmgi);
	
		if(mbmsServiceArea==null)
			throw new GTPParseException("MBMS Service area is not set");
	
		output.add(mbmsServiceArea);
	
		if(mbmsSessionIdentifier!=null)
			output.add(mbmsSessionIdentifier);
	
		if(mbms23GIndicator==null)
			throw new GTPParseException("MBMS 2G 3G indicator is not set");
	
		output.add(mbms23GIndicator);
	
		if(mbmsSessionDuration==null)
			throw new GTPParseException("MBMS Session Duration is not set");
	
		output.add(mbmsSessionDuration);
	
		if(mbmsSessionRepetionNumber!=null)
			output.add(mbmsSessionRepetionNumber);
		
		if(mbmsTimeToDataTransfer==null)
			throw new GTPParseException("MBMS Time To Data Transfer is not set");
	
		output.add(mbmsTimeToDataTransfer);
	
		if(mbmsFlowIdentifier!=null)
			output.add(mbmsFlowIdentifier);
		
		if(mbmsIPMulticastDistribution!=null)
			output.add(mbmsIPMulticastDistribution);
		
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
	}

	@Override
	public TunnerEndpointIdentifierControlPane getControlPaneTEI() 
	{
		return controlTEI;
	}

	@Override
	public void setControlPageTEI(TunnerEndpointIdentifierControlPane tei) 
	{
		this.controlTEI=tei;
	}

	@Override
	public EndUserAddress getEndUserAddress() 
	{
		return endUserAddress;
	}

	@Override
	public void setEndUserAddress(EndUserAddress address)
	{
		this.endUserAddress=address;
	}

	@Override
	public APN getAPN() 
	{
		return apn;
	}

	@Override
	public void setAPN(APN apn)
	{
		this.apn=apn;
	}
	
	@Override
	public GSNAddress getGGSNAddressForSignaling() 
	{
		return ggsnAddressForSignaling;
	}

	@Override
	public void setGGSNAddressForSignaling(GSNAddress address) 
	{
		this.ggsnAddressForSignaling=address;
	}

	@Override
	public GSNAddress getAlternateGGSNAddressForSignaling() 
	{
		return alternateGgsnAddressForSignaling;
	}

	@Override
	public void setAlternateGGSNAddressForSignaling(GSNAddress address) 
	{
		this.alternateGgsnAddressForSignaling=address;
	}
	
	@Override
	public QosProfile getQosProfile() 
	{
		return qosProfile;
	}

	@Override
	public void setQosProfile(QosProfile profile) 
	{
		this.qosProfile=profile;
	}

	@Override
	public CommonFlags getCommonFlags() 
	{
		return commonFlags;
	}

	@Override
	public void setCommonFlags(CommonFlags commonFlags) 
	{
		this.commonFlags=commonFlags;
	}

	@Override
	public TMGI getTMGI() 
	{
		return this.tmgi;
	}

	@Override
	public void setTMGI(TMGI tmgi) 
	{
		this.tmgi=tmgi;
	}

	@Override
	public MBMSServiceArea getMBMSServiceArea() 
	{
		return this.mbmsServiceArea;
	}

	@Override
	public void setMBMSServiceArea(MBMSServiceArea serviceArea) 
	{
		this.mbmsServiceArea=serviceArea;
	}

	@Override
	public MBMSSessionIdentifier getMBMSSessionIdentifier() 
	{
		return this.mbmsSessionIdentifier;
	}

	@Override
	public void setMBMSSessionIdentifier(MBMSSessionIdentifier sessionIdentifier) 
	{
		this.mbmsSessionIdentifier=sessionIdentifier;
	}

	@Override
	public MBMS23GIndicator getMBMS23GIndicator() 
	{
		return mbms23GIndicator;
	}

	@Override
	public void setMBMS23GIndicator(MBMS23GIndicator mbms23gIndicator) 
	{
		this.mbms23GIndicator=mbms23gIndicator;
	}

	@Override
	public MBMSSessionDuration getMBMSSessionDuration() 
	{
		return this.mbmsSessionDuration;
	}

	@Override
	public void setMBMSSessionDuration(MBMSSessionDuration mbmsSessionDuration) 
	{
		this.mbmsSessionDuration=mbmsSessionDuration;
	}

	@Override
	public MBMSSessionRepetionNumber getMBMSSessionRepetionNumber() 
	{
		return this.mbmsSessionRepetionNumber;
	}

	@Override
	public void setMBMSSessionRepetionNumber(MBMSSessionRepetionNumber sessionRepetititionNumber) 
	{
		this.mbmsSessionRepetionNumber=sessionRepetititionNumber;
	}

	@Override
	public MBMSTimeToDataTransfer getMBMSTimeToDataTransfer() 
	{
		return this.mbmsTimeToDataTransfer;
	}

	@Override
	public void setMBMSTimeToDataTransfer(MBMSTimeToDataTransfer mbmsTimeToDataTransfer) 
	{
		this.mbmsTimeToDataTransfer=mbmsTimeToDataTransfer;
	}

	@Override
	public MBMSFlowIdentifier getMBMSFlowIdentifier() 
	{
		return this.mbmsFlowIdentifier;
	}

	@Override
	public void setMBMSFlowIdentifier(MBMSFlowIdentifier mbmsFlowIdentifier) 
	{
		this.mbmsFlowIdentifier=mbmsFlowIdentifier;
	}

	@Override
	public MBMSIPMulticastDistribution getMBMSIPMulticastDistribution() 
	{
		return this.mbmsIPMulticastDistribution;
	}

	@Override
	public void setMBMSIPMulticastDistribution(MBMSIPMulticastDistribution mbmsIPMulticastDistribution) 
	{
		this.mbmsIPMulticastDistribution=mbmsIPMulticastDistribution;
	}
}