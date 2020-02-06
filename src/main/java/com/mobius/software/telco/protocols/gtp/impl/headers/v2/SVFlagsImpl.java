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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SVFlags;

public class SVFlagsImpl extends AbstractTLV2 implements SVFlags 
{
	private Boolean emergencyIndicator;
	private Boolean imsCentralizedService;
	private Boolean sessionTransferIndicator;
	private Boolean vSRVCCflag;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.SV_FLAGS;
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
		if(emergencyIndicator!=null && emergencyIndicator)
			currByte|=(byte)0x01;
		
		if(imsCentralizedService!=null && imsCentralizedService)
			currByte|=(byte)0x02;
		
		if(sessionTransferIndicator!=null && sessionTransferIndicator)
			currByte|=(byte)0x04;
		
		if(vSRVCCflag!=null && vSRVCCflag)
			currByte|=(byte)0x08;
		
		buffer.writeByte(currByte);
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte currByte=buffer.readByte();
		if((currByte & 0x01)!=0)
			emergencyIndicator=true;
		else
			emergencyIndicator=false;
		
		if((currByte & 0x02)!=0)
			imsCentralizedService=true;
		else
			imsCentralizedService=false;
		
		if((currByte & 0x04)!=0)
			sessionTransferIndicator=true;
		else
			sessionTransferIndicator=false;
		
		if((currByte & 0x08)!=0)
			vSRVCCflag=true;
		else
			vSRVCCflag=false;
	}

	@Override
	public Boolean getVSRVCCFlag() 
	{
		return this.vSRVCCflag;
	}

	@Override
	public void setVSRVCCFlag(Boolean value) 
	{
		this.vSRVCCflag=value;
	}

	@Override
	public Boolean getSessionTransferIndicator() 
	{
		return this.sessionTransferIndicator;
	}

	@Override
	public void setSessionTransferIndicator(Boolean value) 
	{
		this.sessionTransferIndicator=value;
	}

	@Override
	public Boolean getIMSCentralizedService() 
	{
		return imsCentralizedService;
	}

	@Override
	public void setIMSCentralizedService(Boolean value) 
	{
		this.imsCentralizedService=value;
	}

	@Override
	public Boolean getEmergencyIndicator() 
	{
		return emergencyIndicator;
	}

	@Override
	public void setEmergencyIndicator(Boolean value) 
	{
		this.emergencyIndicator=value;
	}
}