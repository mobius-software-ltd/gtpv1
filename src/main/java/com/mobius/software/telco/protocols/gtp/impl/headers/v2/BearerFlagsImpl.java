package com.mobius.software.telco.protocols.gtp.impl.headers.v2;
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
import io.netty.buffer.ByteBuf;

import com.mobius.software.telco.protocols.gtp.api.exceptions.MissingArgumentException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.BearerFlags;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;

public class BearerFlagsImpl extends AbstractTLV2 implements BearerFlags 
{
	private Boolean prohibitPayloadCompression;
	private Boolean voiceBearer;
	private Boolean vSRVCCIndicator;
	private Boolean activityStatusIndicator;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.BEARER_FLAGS;
	}

	@Override
	public Integer getLength() 
	{
		return 1;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		byte currValue=0;
		if(prohibitPayloadCompression!=null && prohibitPayloadCompression)
			currValue|=0x01;
		
		if(voiceBearer!=null && voiceBearer)
			currValue|=0x02;
		
		if(vSRVCCIndicator !=null && vSRVCCIndicator)
			currValue|=0x04;
		
		if(activityStatusIndicator!=null && activityStatusIndicator)
			currValue|=0x08;
		
		buffer.writeByte(currValue);		
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte currByte=buffer.readByte();
		if((currByte & 0x01)!=0)
			prohibitPayloadCompression=true;
		else
			prohibitPayloadCompression=false;
		
		if((currByte & 0x02)!=0)
			voiceBearer=true;
		else
			voiceBearer=false;
		
		if((currByte & 0x04)!=0)
			vSRVCCIndicator=true;
		else
			vSRVCCIndicator=false;
		
		if((currByte & 0x08)!=0)
			activityStatusIndicator=true;
		else
			activityStatusIndicator=false;
	}

	@Override
	public Boolean getProhibitPayloadCompression() 
	{
		return prohibitPayloadCompression;
	}

	@Override
	public void setProhibitPayloadCompression(Boolean value) 
	{
		this.prohibitPayloadCompression=value;
	}

	@Override
	public Boolean getVoiceBearer() 
	{
		return this.voiceBearer;
	}

	@Override
	public void setVoiceBearer(Boolean value) 
	{
		this.voiceBearer=value;
	}

	@Override
	public Boolean getVSRVCCIndicator() 
	{
		return this.vSRVCCIndicator;
	}

	@Override
	public void setVSRVCCIndicator(Boolean value) 
	{
		this.vSRVCCIndicator=value;
	}

	@Override
	public Boolean getActivityStatusIndicator() 
	{
		return activityStatusIndicator;
	}

	@Override
	public void setActivityStatusIndicator(Boolean value) 
	{
		this.activityStatusIndicator=value;
	}
}