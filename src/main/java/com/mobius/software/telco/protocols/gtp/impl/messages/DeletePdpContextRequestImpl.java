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
import com.mobius.software.telco.protocols.gtp.api.headers.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.ExtendedCommonFlags;
import com.mobius.software.telco.protocols.gtp.api.headers.MSTimezone;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.NSAPI;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.ProtocolConfigurationOption;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.headers.TeardownIndicator;
import com.mobius.software.telco.protocols.gtp.api.headers.ULITimestamp;
import com.mobius.software.telco.protocols.gtp.api.headers.UserLocationInformation;
import com.mobius.software.telco.protocols.gtp.api.messages.DeletePdpContextRequest;

public class DeletePdpContextRequestImpl extends AbstractGTPMessage implements DeletePdpContextRequest
{
	private Cause cause;
	private TeardownIndicator teardownIndicator;
	private NSAPI nsapi;
	private ProtocolConfigurationOption protocolConfigurationOption;
	private UserLocationInformation userLocationInformation;
	private MSTimezone msTimezone;
	private ExtendedCommonFlags extendedCommonFlags;
	private ULITimestamp uliTimestamp;
	
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public MessageType getMessageType() 
	{
		return MessageType.DELETE_PDP_CONTEXT_REQUEST;
	}

	@Override
	public void applyTLV(TLV1 tlv) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case CAUSE:
				cause=(Cause)tlv;
				break;
			case TEARDOWN_IND:
				teardownIndicator=(TeardownIndicator)tlv;
				break;
			case NSAPI:
				nsapi=(NSAPI)tlv;
				break;
			case PROTOCOL_CONFIGURATION_OPTIONS:
				protocolConfigurationOption=(ProtocolConfigurationOption)tlv;
				break;
			case USER_LOCATION_INFORMATION:
				userLocationInformation=(UserLocationInformation)tlv;
				break;
			case MS_TIMEZONE:
				msTimezone=(MSTimezone)tlv;
				break;
			case EXTENDED_COMMON_FLAGS:
				extendedCommonFlags=(ExtendedCommonFlags)tlv;
				break;
			case ULI_TIMESTAMP:
				uliTimestamp=(ULITimestamp)tlv;
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
		if(cause!=null)
			output.add(cause);
		
		if(teardownIndicator!=null)
			output.add(teardownIndicator);
		
		if(nsapi==null)
			throw new GTPParseException("nsapi is missing");
		
		output.add(nsapi);
	
		if(protocolConfigurationOption!=null)
			output.add(protocolConfigurationOption);
	
		if(userLocationInformation!=null)
			output.add(userLocationInformation);
	
		if(msTimezone!=null)
			output.add(msTimezone);
	
		if(extendedCommonFlags!=null)
			output.add(extendedCommonFlags);
		
		if(uliTimestamp!=null)
			output.add(uliTimestamp);
		
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
	public NSAPI getNSAPI() 
	{
		return nsapi;
	}

	@Override
	public void setNSAPI(NSAPI nsapi) 
	{
		this.nsapi=nsapi;
	}

	@Override
	public ProtocolConfigurationOption getProtocolConfigurationOption() 
	{
		return protocolConfigurationOption;
	}

	@Override
	public void setProtocolConfigurationOption(ProtocolConfigurationOption option) 
	{
		this.protocolConfigurationOption=option;
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
	public MSTimezone getMSTimezone() 
	{
		return msTimezone;
	}

	@Override
	public void setMSTimezone(MSTimezone timezone) 
	{
		this.msTimezone=timezone;
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
	public Cause getCause() 
	{
		return cause;
	}

	@Override
	public void setCause(Cause cause) 
	{
		this.cause=cause;
	}

	@Override
	public TeardownIndicator getTeardownIndicator() 
	{
		return this.teardownIndicator;
	}

	@Override
	public void setTeardownIndicator(TeardownIndicator teardownIndicator) 
	{
		this.teardownIndicator=teardownIndicator;
	}

	@Override
	public ULITimestamp getULITimestamp() 
	{
		return this.uliTimestamp;
	}

	@Override
	public void setULITimestamp(ULITimestamp timestamp) 
	{
		this.uliTimestamp=timestamp;
	}
}