package com.mobius.software.telco.protocols.gtp.impl.headers;
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
import com.mobius.software.telco.protocols.gtp.api.headers.CommonFlags;
import com.mobius.software.telco.protocols.gtp.api.headers.ElementType;

public class CommonFlagsImpl extends AbstractTLV implements CommonFlags 
{
	private Boolean dualAddressBearer;
	private Boolean upgradeQOSSupported;
	private Boolean nrsn;
	private Boolean noQOSNegotiated;
	private Boolean mbmsCountingInformation;
	private Boolean ranProceduresReady;
	private Boolean mbmsServiceType;
	private Boolean prohibitPayloadCompression;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.COMMON_FLAGS;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return true;
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
		if(dualAddressBearer!=null && dualAddressBearer)
			currByte|=(byte)0x80;
		
		if(upgradeQOSSupported!=null && upgradeQOSSupported)
			currByte|=(byte)0x40;
		
		if(nrsn!=null && nrsn)
			currByte|=(byte)0x20;
		
		if(noQOSNegotiated!=null && noQOSNegotiated)
			currByte|=(byte)0x10;
		
		if(mbmsCountingInformation!=null && mbmsCountingInformation)
			currByte|=(byte)0x08;
		
		if(ranProceduresReady!=null && ranProceduresReady)
			currByte|=(byte)0x04;
		
		if(mbmsServiceType!=null && mbmsServiceType)
			currByte|=(byte)0x02;
		
		if(prohibitPayloadCompression!=null && prohibitPayloadCompression)
			currByte|=(byte)0x01;
		
		buffer.writeByte(currByte);
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte currByte=buffer.readByte();
		if((currByte & 0x80)!=0)
			dualAddressBearer=true;
		else
			dualAddressBearer=false;
		
		if((currByte & 0x40)!=0)
			upgradeQOSSupported=true;
		else
			upgradeQOSSupported=false;
		
		if((currByte & 0x20)!=0)
			nrsn=true;
		else
			nrsn=false;
		
		if((currByte & 0x10)!=0)
			noQOSNegotiated=true;
		else
			noQOSNegotiated=false;
		
		if((currByte & 0x08)!=0)
			mbmsCountingInformation=true;
		else
			mbmsCountingInformation=false;
		
		if((currByte & 0x04)!=0)
			ranProceduresReady=true;
		else
			ranProceduresReady=false;
		
		if((currByte & 0x02)!=0)
			mbmsServiceType=true;
		else
			mbmsServiceType=false;
		
		if((currByte & 0x01)!=0)
			prohibitPayloadCompression=true;
		else
			prohibitPayloadCompression=false;
	}

	public Boolean getDualAddressBearer() 
	{
		return dualAddressBearer;
	}

	public void setDualAddressBearer(Boolean dualAddressBearer) 
	{
		this.dualAddressBearer = dualAddressBearer;
	}

	public Boolean getUpgradeQOSSupported() 
	{
		return upgradeQOSSupported;
	}

	public void setUpgradeQOSSupported(Boolean upgradeQOSSupported) 
	{
		this.upgradeQOSSupported = upgradeQOSSupported;
	}

	public Boolean getNRSN() 
	{
		return nrsn;
	}

	public void setNRSN(Boolean nrsn) 
	{
		this.nrsn = nrsn;
	}

	public Boolean getNoQOSNegotiated() 
	{
		return noQOSNegotiated;
	}

	public void setNoQOSNegotiated(Boolean noQOSNegotiated) 
	{
		this.noQOSNegotiated = noQOSNegotiated;
	}

	public Boolean getMBMSCountingInformation() 
	{
		return mbmsCountingInformation;
	}

	public void setMBMSCountingInformation(Boolean mbmsCountingInformation) 
	{
		this.mbmsCountingInformation = mbmsCountingInformation;
	}

	public Boolean getRANProceduresReady() 
	{
		return ranProceduresReady;
	}

	public void setRANProceduresReady(Boolean ranProceduresReady) 
	{
		this.ranProceduresReady = ranProceduresReady;
	}

	public Boolean getMBMSServiceType() 
	{
		return mbmsServiceType;
	}

	public void setMBMSServiceType(Boolean mbmsServiceType) 
	{
		this.mbmsServiceType = mbmsServiceType;
	}

	public Boolean getProhibitPayloadCompression() 
	{
		return prohibitPayloadCompression;
	}

	public void setProhibitPayloadCompression(Boolean prohibitPayloadCompression) 
	{
		this.prohibitPayloadCompression = prohibitPayloadCompression;
	}
}