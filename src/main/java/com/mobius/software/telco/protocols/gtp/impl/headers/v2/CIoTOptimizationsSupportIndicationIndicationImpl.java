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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CIoTOptimizationsSupportIndication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;

public class CIoTOptimizationsSupportIndicationIndicationImpl extends AbstractTLV2 implements CIoTOptimizationsSupportIndication 
{
	private Boolean sgiNonIPPDNIndication;
	private Boolean scefNonIPPDNSupportIndication;
	private Boolean attachWithoutPDNSupportIndication;
	private Boolean ipHeaderCompressionSupportIndication;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.CIOT_OPTIMIZATION_SUPPORT_INDICATION;
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
		if(sgiNonIPPDNIndication!=null && sgiNonIPPDNIndication)
			currByte|=(byte)0x01;
		
		if(scefNonIPPDNSupportIndication!=null && scefNonIPPDNSupportIndication)
			currByte|=(byte)0x02;
		
		if(attachWithoutPDNSupportIndication!=null && attachWithoutPDNSupportIndication)
			currByte|=(byte)0x04;
		
		if(ipHeaderCompressionSupportIndication!=null && ipHeaderCompressionSupportIndication)
			currByte|=(byte)0x08;
		
		buffer.writeByte(currByte);
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte currByte=buffer.readByte();
		if((currByte & 0x01)!=0)
			sgiNonIPPDNIndication=true;
		else
			sgiNonIPPDNIndication=false;
		
		if((currByte & 0x02)!=0)
			scefNonIPPDNSupportIndication=true;
		else
			scefNonIPPDNSupportIndication=false;
		
		if((currByte & 0x04)!=0)
			attachWithoutPDNSupportIndication=true;
		else
			attachWithoutPDNSupportIndication=false;
		
		if((currByte & 0x08)!=0)
			ipHeaderCompressionSupportIndication=true;
		else
			ipHeaderCompressionSupportIndication=false;
	}

	@Override
	public Boolean getIPHeaderCompressionSupportIndication() 
	{
		return this.ipHeaderCompressionSupportIndication;
	}

	@Override
	public void setIPHeaderCompressionSupportIndication(Boolean value) 
	{
		this.ipHeaderCompressionSupportIndication=value;
	}

	@Override
	public Boolean getAttachWithoutPDNSupportIndication() 
	{
		return this.attachWithoutPDNSupportIndication;
	}

	@Override
	public void setAttachWithoutPDNSupportIndication(Boolean value) 
	{
		this.attachWithoutPDNSupportIndication=value;
	}

	@Override
	public Boolean getSCEFNonIPPDNSupportIndication() 
	{
		return scefNonIPPDNSupportIndication;
	}

	@Override
	public void setSCEFNonIPPDNSupportIndication(Boolean value) 
	{
		this.scefNonIPPDNSupportIndication=value;
	}

	@Override
	public Boolean getSGiNonIPPDNSupportIndication() 
	{
		return sgiNonIPPDNIndication;
	}

	@Override
	public void setSGiNonIPPDNSupportIndication(Boolean value) 
	{
		this.sgiNonIPPDNIndication=value;
	}
}