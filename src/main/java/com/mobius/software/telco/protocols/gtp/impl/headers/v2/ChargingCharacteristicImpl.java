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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ChargingCharacteristic;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;

public class ChargingCharacteristicImpl extends AbstractTLV2 implements ChargingCharacteristic 
{
	private Boolean normalCharging;
	private Boolean prepaidCharging;
	private Boolean flatRateCharging;
	private Boolean hotBillingCharging;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.CHARGING_CHARACTERISTICS;
	}

	@Override
	public Integer getLength() 
	{
		return 2;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(normalCharging==null)
			throw new MissingArgumentException("Normal charging is not set");
		
		if(prepaidCharging==null)
			throw new MissingArgumentException("Prepaid charging is not set");
		
		if(flatRateCharging==null)
			throw new MissingArgumentException("Flat rate charging is not set");
		
		if(hotBillingCharging==null)
			throw new MissingArgumentException("Hot billing charging is not set");
		
		byte value=0;
		if(normalCharging)
			value|=0x08;
		
		if(prepaidCharging)
			value|=0x04;
		
		if(flatRateCharging)
			value|=0x02;
		
		if(hotBillingCharging)
			value|=0x01;
		
		buffer.writeByte(value);
		buffer.writeByte(0);
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte value=buffer.readByte();
		normalCharging=(value & 0x08)!=0;
		prepaidCharging=(value & 0x04)!=0;
		flatRateCharging=(value & 0x02)!=0;
		hotBillingCharging=(value & 0x01)!=0;
		buffer.readByte();
	}

	@Override
	public Boolean getNormalCharging() 
	{
		return this.normalCharging;
	}

	@Override
	public void setNormalCharging(Boolean normalCharging) 
	{
		this.normalCharging=normalCharging;		
	}

	@Override
	public Boolean getPrepaidCharging() 
	{
		return this.prepaidCharging;
	}

	@Override
	public void setPrepaidCharging(Boolean prepaidCharging) 
	{
		this.prepaidCharging=prepaidCharging;		
	}

	@Override
	public Boolean getFlatRateCharging() 
	{
		return this.flatRateCharging;
	}

	@Override
	public void setFlatRateCharging(Boolean flatRateCharging) 
	{
		this.flatRateCharging=flatRateCharging;		
	}

	@Override
	public Boolean getHotBillingCharging() 
	{
		return hotBillingCharging;
	}

	@Override
	public void setHotBillingCharging(Boolean hotBillingCharging) 
	{
		this.hotBillingCharging=hotBillingCharging;		
	}
}
