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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.NodeFeatures;

public class NodeFeaturesImpl extends AbstractTLV2 implements NodeFeatures 
{
	private Boolean pgwRestartNotification;
	private Boolean modifyAccessBearerRequest;
	private Boolean networkTriggeredServiceRestorationProcedure;
	private Boolean cellularInternetOfThings;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.NODE_FEATURES;
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
		if(pgwRestartNotification!=null && pgwRestartNotification)
			currByte|=(byte)0x01;
		
		if(modifyAccessBearerRequest!=null && modifyAccessBearerRequest)
			currByte|=(byte)0x02;
		
		if(networkTriggeredServiceRestorationProcedure!=null && networkTriggeredServiceRestorationProcedure)
			currByte|=(byte)0x04;
		
		if(cellularInternetOfThings!=null && cellularInternetOfThings)
			currByte|=(byte)0x08;		
		
		buffer.writeByte(currByte);
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte currByte=buffer.readByte();
		if((currByte & 0x01)!=0)
			pgwRestartNotification=true;
		else
			pgwRestartNotification=false;
		
		if((currByte & 0x02)!=0)
			modifyAccessBearerRequest=true;
		else
			modifyAccessBearerRequest=false;
		
		if((currByte & 0x04)!=0)
			networkTriggeredServiceRestorationProcedure=true;
		else
			networkTriggeredServiceRestorationProcedure=false;
		
		if((currByte & 0x08)!=0)
			cellularInternetOfThings=true;
		else
			cellularInternetOfThings=false;
	}

	public Boolean getPGWRestartNotification() 
	{
		return pgwRestartNotification;
	}

	public void setPGWRestartNotification(Boolean pgwRestartNotification) 
	{
		this.pgwRestartNotification = pgwRestartNotification;
	}

	public Boolean getModifyAccessBearerRequest() 
	{
		return modifyAccessBearerRequest;
	}

	public void setModifyAccessBearerRequest(Boolean modifyAccessBearerRequest) 
	{
		this.modifyAccessBearerRequest = modifyAccessBearerRequest;
	}

	public Boolean getNetworkTriggeredServiceRestorationProcedure() 
	{
		return networkTriggeredServiceRestorationProcedure;
	}

	public void setNetworkTriggeredServiceRestorationProcedure(Boolean networkTriggeredServiceRestorationProcedure) 
	{
		this.networkTriggeredServiceRestorationProcedure = networkTriggeredServiceRestorationProcedure;
	}

	public Boolean getCellularInternetOfThings() 
	{
		return cellularInternetOfThings;
	}

	public void setCellularInternetOfThings(Boolean cellularInternetOfThings) 
	{
		this.cellularInternetOfThings = cellularInternetOfThings;
	}
}