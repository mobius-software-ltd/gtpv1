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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MBMSFlags;

public class MBMSFlagsImpl extends AbstractTLV2 implements MBMSFlags 
{
	private Boolean sessionReeestablishmentIndication;
	private Boolean bearerContextReleaseIndication;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.MBMS_FLAGS;
	}

	@Override
	public Integer getLength() 
	{
		return 1;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		byte currByte=0;
		if(sessionReeestablishmentIndication!=null && sessionReeestablishmentIndication)
			currByte|=(byte)0x01;
		
		if(bearerContextReleaseIndication!=null && bearerContextReleaseIndication)
			currByte|=(byte)0x02;
		
		buffer.writeByte(currByte);
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte currByte=buffer.readByte();
		if((currByte & 0x01)!=0)
			sessionReeestablishmentIndication=true;
		else
			sessionReeestablishmentIndication=false;
		
		if((currByte & 0x02)!=0)
			bearerContextReleaseIndication=true;
		else
			bearerContextReleaseIndication=false;		
	}

	@Override
	public Boolean getBearerContextReleaseIndication() 
	{
		return bearerContextReleaseIndication;
	}

	@Override
	public void setBearerContextReleaseIndication(Boolean value) 
	{
		this.bearerContextReleaseIndication=value;
	}

	@Override
	public Boolean getSessionReeestablishmentIndication() 
	{
		return sessionReeestablishmentIndication;
	}

	@Override
	public void setSessionReeestablishmentIndication(Boolean value) 
	{
		this.sessionReeestablishmentIndication=value;
	}
}