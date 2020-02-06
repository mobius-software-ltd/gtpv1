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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ChannelNeeded;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EMLPPPriority;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FQDN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GlobalCNID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ServiceIndicator;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UserLocationInformation;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.CSPagingIndication;

public class CSPagingIndicationImpl extends AbstractGTP2Message implements CSPagingIndication
{
	IMSI imsi;
	FQDN vlrName;
	TMSI tmsi;
	UserLocationInformation locationAreaIdentifier;
	GlobalCNID globalCNID;
	ChannelNeeded channelNeeded;
	EMLPPPriority eMLPPPriority;
	ServiceIndicator serviceIndicator;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.CS_PAGING_INDICATION;
	}

	@Override
	public void applyTLV(TLV2 tlv) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case IMSI:
				imsi=(IMSI)tlv;
				break;
			case USER_LOCATION_INFORMATION:
				switch(tlv.getInstance())
				{
					case 0:
						locationAreaIdentifier=(UserLocationInformation)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case FQDN:
				vlrName=(FQDN)tlv;
				break;
			case TMSI:
				tmsi=(TMSI)tlv;
				break;
			case GLOBAL_CN_ID:
				switch(tlv.getInstance())
				{
					case 0:
						globalCNID=(GlobalCNID)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case CHANNEL_NEEDED:
				switch(tlv.getInstance())
				{
					case 0:
						channelNeeded=(ChannelNeeded)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case EMLPP_PRIORITY:
				eMLPPPriority=(EMLPPPriority)tlv;
				break;
			case SERVICE_INDICATOR:
				serviceIndicator=(ServiceIndicator)tlv;
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
		if(imsi==null)
			throw new GTPParseException("IMSI not set");
		
		output.add(imsi);
		
		if(vlrName==null)
			throw new GTPParseException("VLR Name not set");
		
		output.add(vlrName);
		
		if(tmsi!=null)
			output.add(tmsi);
		
		if(locationAreaIdentifier!=null)
			output.add(locationAreaIdentifier);
		
		if(globalCNID!=null)
			output.add(globalCNID);
			
		if(channelNeeded!=null)
			output.add(channelNeeded);
		
		if(eMLPPPriority!=null)
			output.add(eMLPPPriority);
		
		if(serviceIndicator!=null)
			output.add(serviceIndicator);
		
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
		imsi.setInstance(0);
		this.imsi=imsi;
	}

	@Override
	public FQDN getVLRName() 
	{
		return vlrName;
	}

	@Override
	public void setVLRName(FQDN vlrName) 
	{
		vlrName.setInstance(0);
		this.vlrName=vlrName;
	}

	@Override
	public TMSI getTMSI() 
	{
		return this.tmsi;
	}

	@Override
	public void setTMSI(TMSI tmsi) 
	{
		tmsi.setInstance(0);
		this.tmsi=tmsi;
	}

	@Override
	public UserLocationInformation getLocationAreaIdentifier() 
	{
		return this.locationAreaIdentifier;
	}

	@Override
	public void setLocationAreaIdentifier(UserLocationInformation locationAreaIdentifier) 
	{
		locationAreaIdentifier.setInstance(0);
		this.locationAreaIdentifier=locationAreaIdentifier;
	}

	@Override
	public GlobalCNID getGlobalCNID() 
	{
		return this.globalCNID;
	}

	@Override
	public void setGlobalCNID(GlobalCNID globalCNID) 
	{
		globalCNID.setInstance(0);
		this.globalCNID=globalCNID;
	}

	@Override
	public ChannelNeeded getChannelNeeded() 
	{
		return this.channelNeeded;
	}

	@Override
	public void setChannelNeeded(ChannelNeeded channelNeeded) 
	{
		channelNeeded.setInstance(0);
		this.channelNeeded=channelNeeded;
	}

	@Override
	public EMLPPPriority getEMLPPPriority() 
	{
		return this.eMLPPPriority;
	}

	@Override
	public void setEMLPPPriority(EMLPPPriority eMLPPPriority) 
	{
		eMLPPPriority.setInstance(0);
		this.eMLPPPriority=eMLPPPriority;
	}

	@Override
	public ServiceIndicator getServiceIndicator() 
	{
		return this.serviceIndicator;
	}

	@Override
	public void setServiceIndicator(ServiceIndicator serviceIndicator) 
	{
		serviceIndicator.setInstance(0);
		this.serviceIndicator=serviceIndicator;
	}
}