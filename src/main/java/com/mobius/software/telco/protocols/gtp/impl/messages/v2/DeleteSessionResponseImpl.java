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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ExtendedProtocolConfigurationOptions;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Indication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.LoadControlInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.OverloadControlInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ProtocolConfigurationOption;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.DeleteSessionResponse;

public class DeleteSessionResponseImpl extends AbstractGTP2Message implements DeleteSessionResponse
{
	private Cause cause;
	private Recovery recovery;
	private ProtocolConfigurationOption protocolConfigurationOption;
	private Indication indication;
	private LoadControlInformation pgwNodeLoadControlInformation;
	private LoadControlInformation pgwAPNLoadControlInformation;
	private LoadControlInformation sgwNodeLoadControlInformation;
	private OverloadControlInformation pgwOverloadControlInformation;
	private OverloadControlInformation sgwOverloadControlInformation;
	private ExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.DELETE_SESSION_RESPONSE;
	}

	@Override
	public void applyTLV(TLV2 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case CAUSE:
				cause=(Cause)tlv;
				break;
			case RECOVERY:
				recovery=(Recovery)tlv;
				break;
			case PROTOCOL_CONFIGURATION_OPTIONS:
				protocolConfigurationOption=(ProtocolConfigurationOption)tlv;
				break;
			case INDICATION:
				indication=(Indication)tlv;
				break;
			case LOAD_CONTROL_INFORMATION:
				switch(tlv.getInstance())
				{
					case 0:
						pgwNodeLoadControlInformation=(LoadControlInformation)tlv;
						break;
					case 1:
						pgwAPNLoadControlInformation=(LoadControlInformation)tlv;
						break;
					case 2:
						sgwNodeLoadControlInformation=(LoadControlInformation)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case OVERLOAD_CONTROL_INFORMATION:
				switch(tlv.getInstance())
				{
					case 0:
						pgwOverloadControlInformation=(OverloadControlInformation)tlv;
						break;
					case 1:
						sgwOverloadControlInformation=(OverloadControlInformation)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case EXTENDED_PROTOCOL_CONFIGURATION_OPTIONS:
				extendedProtocolConfigurationOptions=(ExtendedProtocolConfigurationOptions)tlv;
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
			throw new GTPParseException("Cause not set");
		
		output.add(cause);
		
		if(recovery!=null)
			output.add(recovery);
			
		if(protocolConfigurationOption!=null)
			output.add(protocolConfigurationOption);
		
		if(indication!=null)
			output.add(indication);
		
		if(pgwNodeLoadControlInformation!=null)
			output.add(pgwNodeLoadControlInformation);
		
		if(pgwAPNLoadControlInformation!=null)
			output.add(pgwAPNLoadControlInformation);
		
		if(sgwNodeLoadControlInformation!=null)
			output.add(sgwNodeLoadControlInformation);
		
		if(pgwOverloadControlInformation!=null)
			output.add(pgwOverloadControlInformation);
		
		if(sgwOverloadControlInformation!=null)
			output.add(sgwOverloadControlInformation);
		
		if(extendedProtocolConfigurationOptions!=null)
			output.add(extendedProtocolConfigurationOptions);
		
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
	public ProtocolConfigurationOption getProtocolConfigurationOption() 
	{
		return this.protocolConfigurationOption;
	}

	@Override
	public void setProtocolConfigurationOption(ProtocolConfigurationOption pco) 
	{
		pco.setInstance(0);
		this.protocolConfigurationOption=pco;
	}

	@Override
	public Indication getIndication() 
	{
		return this.indication;
	}

	@Override
	public void setIndication(Indication indication) 
	{
		indication.setInstance(0);
		this.indication=indication;
	}

	@Override
	public LoadControlInformation getPGWNodeLoadControlInformation() 
	{		
		return this.pgwNodeLoadControlInformation;
	}

	@Override
	public void setPGWNodeLoadControlInformation(LoadControlInformation loadInformation) 
	{
		loadInformation.setInstance(0);
		this.pgwNodeLoadControlInformation=loadInformation;
	}

	@Override
	public LoadControlInformation getPGWAPNLoadControlInformation() 
	{
		return this.pgwAPNLoadControlInformation;
	}

	@Override
	public void setPGWAPNLoadControlInformation(LoadControlInformation loadInformation) 
	{
		loadInformation.setInstance(1);
		this.pgwAPNLoadControlInformation=loadInformation;
	}

	@Override
	public LoadControlInformation getSGWNodeLoadControlInformation() 
	{
		return this.sgwNodeLoadControlInformation;
	}

	@Override
	public void setSGWNodeLoadControlInformation(LoadControlInformation loadInformation) 
	{
		loadInformation.setInstance(2);
		this.sgwNodeLoadControlInformation=loadInformation;
	}

	@Override
	public OverloadControlInformation getPGWOverloadControlInformation() 
	{
		return this.pgwOverloadControlInformation;
	}

	@Override
	public void setPGWOverloadControlInformation(OverloadControlInformation overloadInformation) 
	{
		overloadInformation.setInstance(0);
		this.pgwOverloadControlInformation=overloadInformation;
	}

	@Override
	public OverloadControlInformation getSGWOverloadControlInformation() 
	{
		return sgwOverloadControlInformation;
	}

	@Override
	public void setSGWOverloadControlInformation(OverloadControlInformation overloadInformation) 
	{
		overloadInformation.setInstance(1);
		this.sgwOverloadControlInformation=overloadInformation;
	}

	@Override
	public ExtendedProtocolConfigurationOptions getExtendedProtocolConfigurationOptions() 
	{
		return extendedProtocolConfigurationOptions;
	}

	@Override
	public void setExtendedProtocolConfigurationOptions(ExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions) 
	{
		extendedProtocolConfigurationOptions.setInstance(0);
		this.extendedProtocolConfigurationOptions=extendedProtocolConfigurationOptions;
	}
}