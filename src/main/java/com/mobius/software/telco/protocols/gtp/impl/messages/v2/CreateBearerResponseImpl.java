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

import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.CreateBearerResponseBearerContextToBeCreated;
import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FContainer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FQCSID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IPAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.OverloadControlInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PRAInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PortNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ProtocolConfigurationOption;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TWANIdentifier;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TWANTimeStamp;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UETimezone;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UserLocationInformation;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.CreateBearerResponse;

public class CreateBearerResponseImpl extends AbstractGTP2Message implements CreateBearerResponse
{
	Cause cause;
	List<CreateBearerResponseBearerContextToBeCreated> bearerContext;
	Recovery recovery;
	FQCSID mmeFQCSID;
	FQCSID sgwFQCSID;
	FQCSID epdgFQCSID;
	FQCSID twanFQCSID;
	ProtocolConfigurationOption protocolConfigurationOption;
	UETimezone ueTimezone;
	UserLocationInformation userLocationInformation;
	TWANIdentifier twanIdentifier;
	OverloadControlInformation sgsnOverloadControlInformation;
	OverloadControlInformation sgwOverloadControlInformation;
	PRAInformation praInformation;
	IPAddress sgsnIdentifier;
	OverloadControlInformation pdgOverloadControlInformation;
	TWANIdentifier wlanLocationInfromation;
	TWANTimeStamp wlanLocationTimestamp;
	IPAddress ueLocalIPAddress;
	PortNumber ueUDPPort;
	FContainer nbifomContainer;
	PortNumber ueTCPPort;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.CREATE_BEARER_RESPONSE;
	}

	@Override
	public void applyTLV(TLV2 tlv) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case CAUSE:
				cause=(Cause)tlv;
				break;
			case USER_LOCATION_INFORMATION:
				switch(tlv.getInstance())
				{
					case 0:
						userLocationInformation=(UserLocationInformation)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case PROTOCOL_CONFIGURATION_OPTIONS:
				protocolConfigurationOption=(ProtocolConfigurationOption)tlv;
				break;
			case BEARER_CONTEXT:
				switch(tlv.getInstance())
				{
					case 0:
						if(bearerContext==null)
							bearerContext=new ArrayList<CreateBearerResponseBearerContextToBeCreated>();
						
						bearerContext.add((CreateBearerResponseBearerContextToBeCreated)tlv);
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case RECOVERY:
				recovery=(Recovery)tlv;
				break;
			case FQ_CSID:
				switch(tlv.getInstance())
				{
					case 0:
						mmeFQCSID=(FQCSID)tlv;
						break;
					case 1:
						sgwFQCSID=(FQCSID)tlv;
						break;
					case 2:
						epdgFQCSID=(FQCSID)tlv;
						break;
					case 3:
						twanFQCSID=(FQCSID)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case UE_TIMEZONE:
				ueTimezone=(UETimezone)tlv;
				break;
			case IP_ADDRESS:
				switch(tlv.getInstance())
				{
					case 0:
						ueLocalIPAddress=(IPAddress)tlv;
						break;
					case 1:
						sgsnIdentifier=(IPAddress)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case PORT_NUMBER:
				switch(tlv.getInstance())
				{
					case 0:
						ueUDPPort=(PortNumber)tlv;
						break;
					case 1:
						ueTCPPort=(PortNumber)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case TWAN_IDENTIFIER:
				switch(tlv.getInstance())
				{
					case 0:
						twanIdentifier=(TWANIdentifier)tlv;
						break;
					case 1:
						wlanLocationInfromation=(TWANIdentifier)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case PRESENCE_REPORTING_AREA_INFORMATION:
				praInformation=(PRAInformation)tlv;
				break;
			case OVERLOAD_CONTROL_INFORMATION:
				switch(tlv.getInstance())
				{
					case 0:
						sgsnOverloadControlInformation=(OverloadControlInformation)tlv;
						break;
					case 1:
						sgwOverloadControlInformation=(OverloadControlInformation)tlv;
						break;
					case 2:
						pdgOverloadControlInformation=(OverloadControlInformation)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case TWAN_IDENTIFIER_TIMESTAMP:
				wlanLocationTimestamp=(TWANTimeStamp)tlv;
				break;
			case FCONTAINER:
				nbifomContainer=(FContainer)tlv;
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
		if(cause!=null)
			output.add(cause);
		
		if(bearerContext==null || bearerContext.size()==0)
			throw new GTPParseException("Bearer Context not set");
		
		output.addAll(bearerContext);
		
		if(recovery!=null)
			output.add(recovery);
		
		if(mmeFQCSID!=null)
			output.add(mmeFQCSID);
		
		if(sgwFQCSID!=null)
			output.add(sgwFQCSID);
		
		if(epdgFQCSID!=null)
			output.add(epdgFQCSID);
		
		if(twanFQCSID!=null)
			output.add(twanFQCSID);
		
		if(protocolConfigurationOption!=null)
			output.add(protocolConfigurationOption);
		
		if(ueTimezone!=null)
			output.add(ueTimezone);
			
		if(userLocationInformation!=null)
			output.add(userLocationInformation);
		
		if(twanIdentifier!=null)
			output.add(twanIdentifier);
		
		if(sgsnOverloadControlInformation!=null)
			output.add(sgsnOverloadControlInformation);
		
		if(sgwOverloadControlInformation!=null)
			output.add(sgwOverloadControlInformation);
		
		if(praInformation!=null)
			output.add(praInformation);
		
		if(sgsnIdentifier!=null)
			output.add(sgsnIdentifier);
		
		if(pdgOverloadControlInformation!=null)
			output.add(pdgOverloadControlInformation);
		
		if(wlanLocationInfromation!=null)
			output.add(wlanLocationInfromation);
		
		if(wlanLocationTimestamp!=null)
			output.add(wlanLocationTimestamp);
		
		if(ueLocalIPAddress!=null)
			output.add(ueLocalIPAddress);
		
		if(ueUDPPort!=null)
			output.add(ueUDPPort);
		
		if(nbifomContainer!=null)
			output.add(nbifomContainer);
		
		if(ueTCPPort!=null)
			output.add(ueTCPPort);
		
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
	public UserLocationInformation getUserLocationInformation() 
	{
		return userLocationInformation;
	}

	@Override
	public void setUserLocationInformation(UserLocationInformation uli) 
	{
		uli.setInstance(0);
		this.userLocationInformation=uli;
	}

	@Override
	public ProtocolConfigurationOption getProtocolConfigurationOption() 
	{
		return protocolConfigurationOption;
	}

	@Override
	public void setProtocolConfigurationOption(ProtocolConfigurationOption pco) 
	{
		pco.setInstance(0);
		this.protocolConfigurationOption=pco;
	}

	@Override
	public List<CreateBearerResponseBearerContextToBeCreated> getBearerContext() 
	{
		return bearerContext;
	}

	@Override
	public void setBearerContext(List<CreateBearerResponseBearerContextToBeCreated> bearerContext) 
	{
		if(bearerContext!=null)
			for(CreateBearerResponseBearerContextToBeCreated curr:bearerContext)
				curr.setInstance(0);
		
		this.bearerContext=bearerContext;
	}

	@Override
	public Recovery getRecovery() 
	{
		return recovery;
	}

	@Override
	public void setRecovery(Recovery recovery) 
	{
		recovery.setInstance(0);
		this.recovery=recovery;
	}

	@Override
	public FQCSID getMMEFQCSID() 
	{
		return mmeFQCSID;
	}

	@Override
	public void setMMEFQCSID(FQCSID fqcsid) 
	{
		fqcsid.setInstance(0);
		this.mmeFQCSID=fqcsid;
	}

	@Override
	public FQCSID getSGWFQCSID() 
	{
		return sgwFQCSID;
	}

	@Override
	public void setSGWFQCSID(FQCSID fqcsid) 
	{
		fqcsid.setInstance(1);
		this.sgwFQCSID=fqcsid;
	}

	@Override
	public FQCSID getEPDGFQCSID() 
	{
		return epdgFQCSID;
	}

	@Override
	public void setEPDGFQCSID(FQCSID fqcsid) 
	{
		fqcsid.setInstance(2);
		this.epdgFQCSID=fqcsid;
	}

	@Override
	public FQCSID getTWANFQCSID() 
	{
		return twanFQCSID;
	}

	@Override
	public void setTWANFQCSID(FQCSID fqcsid) 
	{
		fqcsid.setInstance(3);
		this.twanFQCSID=fqcsid;
	}

	@Override
	public UETimezone getUETimezone() 
	{
		return ueTimezone;
	}

	@Override
	public void setUETimezone(UETimezone ueTimezone) 
	{
		ueTimezone.setInstance(0);
		this.ueTimezone=ueTimezone;
	}

	@Override
	public IPAddress getUELocalIPAddress() 
	{
		return ueLocalIPAddress;
	}

	@Override
	public void setUELocalIPAddress(IPAddress ueLocalIPAddress) 
	{
		ueLocalIPAddress.setInstance(0);
		this.ueLocalIPAddress=ueLocalIPAddress;
	}

	@Override
	public PortNumber getUEUDPPort() 
	{
		return ueUDPPort;
	}

	@Override
	public void setUEUDPPort(PortNumber ueUDPPort) 
	{
		ueUDPPort.setInstance(0);
		this.ueUDPPort=ueUDPPort;
	}

	@Override
	public IPAddress getSGSNIdentifier() 
	{
		return sgsnIdentifier;
	}

	@Override
	public void setSGSNIdentifier(IPAddress sgsnIdentifier) 
	{
		sgsnIdentifier.setInstance(2);
		this.sgsnIdentifier=sgsnIdentifier;
	}

	@Override
	public TWANIdentifier getTWANIdentifier() 
	{
		return this.twanIdentifier;
	}

	@Override
	public void setTWANIdentifier(TWANIdentifier twanIdentifier) 
	{
		twanIdentifier.setInstance(0);
		this.twanIdentifier=twanIdentifier;
	}

	@Override
	public PRAInformation getPRAInformation() 
	{
		return praInformation;
	}

	@Override
	public void setPRAInformation(PRAInformation praInformation) 
	{
		praInformation.setInstance(0);
		this.praInformation=praInformation;
	}

	@Override
	public OverloadControlInformation getSGSNOverloadControlInformation() 
	{
		return sgsnOverloadControlInformation;
	}

	@Override
	public void setSGSNOverloadControlInformation(OverloadControlInformation overloadControlInformation) 
	{
		overloadControlInformation.setInstance(0);
		this.sgsnOverloadControlInformation=overloadControlInformation;
	}

	@Override
	public OverloadControlInformation getSGWOverloadControlInformation() 
	{
		return sgwOverloadControlInformation;
	}

	@Override
	public void setSGWOverloadControlInformation(OverloadControlInformation overloadControlInformation) 
	{
		overloadControlInformation.setInstance(1);
		this.sgwOverloadControlInformation=overloadControlInformation;
	}

	@Override
	public OverloadControlInformation getPDGOverloadControlInformation() 
	{
		return pdgOverloadControlInformation;
	}

	@Override
	public void setPDGOverloadControlInformation(OverloadControlInformation overloadControlInformation) 
	{
		overloadControlInformation.setInstance(2);
		this.pdgOverloadControlInformation=overloadControlInformation;
	}

	@Override
	public TWANIdentifier getWLANLocationInfromation() 
	{
		return wlanLocationInfromation;
	}

	@Override
	public void setWLANLocationInfromation(TWANIdentifier wlanLocationInfromation) 
	{
		wlanLocationInfromation.setInstance(1);
		this.wlanLocationInfromation=wlanLocationInfromation;
	}

	@Override
	public TWANTimeStamp getWLANLocationTimestamp() 
	{
		return wlanLocationTimestamp;
	}

	@Override
	public void setWLANLocationTimestamp(TWANTimeStamp wlanLocationTimestamp) 
	{
		wlanLocationTimestamp.setInstance(0);
		this.wlanLocationTimestamp=wlanLocationTimestamp;
	}

	@Override
	public FContainer getNBIFOMContainer() 
	{
		return nbifomContainer;
	}

	@Override
	public void setNBIFOMContainer(FContainer nbifomContainer) 
	{
		nbifomContainer.setInstance(0);
		this.nbifomContainer=nbifomContainer;
	}

	@Override
	public PortNumber getUETCPPort() 
	{
		return ueTCPPort;
	}

	@Override
	public void setUETCPPort(PortNumber ueTCPPort) 
	{
		ueTCPPort.setInstance(2);
		this.ueTCPPort=ueTCPPort;
	}
}