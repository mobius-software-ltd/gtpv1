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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IntegerNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MMContext;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MonitoringEventInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TraceInformation;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.IdentificationResponse;

public class IdentificationResponseImpl extends AbstractGTP2Message implements IdentificationResponse
{
	Cause cause;
	IMSI imsi;
	MMContext mmContext;
	TraceInformation traceInformation;
	IntegerNumber ueUsageType;
	MonitoringEventInformation monitoringEventInformation;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.IDENTIFICATION_RESPONSE;
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
			case MM_CONTEXT_EPS:
			case MM_CONTEXT_FOR_CS_TO_PS:
			case MM_CONTEXT_FOR_EUTRAN:
			case MM_CONTEXT_FOR_UTRAN:
			case MM_CONTEXT_GSM_AND_TRIPLETS:
			case MM_CONTEXT_GSM_CIPHER_AND_QUINTIPLETS:
			case MM_CONTEXT_UMTS_AND_QUINTIPLETS:
			case MM_CONTEXT_UMTS_CIPHER_AND_QUINTIPLETS:
			case MM_CONTEXT_UMTS_KEY_QUADRIPLETS_AND_QUINTIPLETS:
				mmContext=(MMContext)tlv;
				break;
			case TRACE_INFORMATION:
				traceInformation=(TraceInformation)tlv;
				break;
			case MONITORING_EVENT_INFORMATION:
				monitoringEventInformation=(MonitoringEventInformation)tlv;
				break;
			case INTEGER_NUMBER:
				switch(tlv.getInstance())
				{
					case 0:
						ueUsageType=(IntegerNumber)tlv;
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
			throw new GTPParseException("Cause not set");
		
		output.add(cause);
				
		if(imsi!=null)
			output.add(imsi);
		
		if(mmContext!=null)
			output.add(mmContext);
		
		if(traceInformation!=null)
			output.add(traceInformation);
			
		if(ueUsageType!=null)
			output.add(ueUsageType);
			
		if(monitoringEventInformation!=null)
			output.add(monitoringEventInformation);
			
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
		return this.imsi;				
	}

	@Override
	public void setIMSI(IMSI imsi) 
	{
		imsi.setInstance(0);
		this.imsi=imsi;
	}

	@Override
	public MMContext getMMContext() 
	{
		return mmContext;
	}

	@Override
	public void setMMContext(MMContext context) 
	{
		context.setInstance(0);
		this.mmContext=context;
	}

	@Override
	public TraceInformation getTraceInformation() 
	{
		return this.traceInformation;
	}

	@Override
	public void setTraceInformation(TraceInformation information) 
	{
		information.setInstance(0);
		this.traceInformation=information;
	}

	@Override
	public MonitoringEventInformation getMonitoringEventInformation() 
	{
		return this.monitoringEventInformation;
	}

	@Override
	public void setMonitoringEventInformation(MonitoringEventInformation eventInformation) 
	{
		eventInformation.setInstance(0);
		this.monitoringEventInformation=eventInformation;
	}

	@Override
	public IntegerNumber getUEUsageType() 
	{
		return this.ueUsageType;
	}

	@Override
	public void setUEUsageType(IntegerNumber usageType) 
	{
		usageType.setInstance(0);
		this.ueUsageType=usageType;
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
}