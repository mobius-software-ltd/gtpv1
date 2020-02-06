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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SecondaryRatType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SecondaryRatUsageDataReport;

public class SecondaryRATUsageDataReportImpl extends AbstractTLV2 implements SecondaryRatUsageDataReport 
{
	private Long startSeconds;
	private Long endSeconds;
	private Long usageDataUL;
	private Long usageDataDL;
	private SecondaryRatType secondaryRatType;
	private Integer ebi;
	private Boolean irsgw;
	private Boolean irpgw;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.SECONDARY_RAT_DATA_USAGE_REPORT;
	}

	@Override
	public Integer getLength() 
	{
		return 27;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		byte currValue=0;
		if(irsgw!=null && irsgw)
			currValue|=0x02;
		
		if(irpgw!=null && irpgw)
			currValue|=0x01;
		
		buffer.writeByte(currValue);
		
		if(secondaryRatType!=null)
			buffer.writeByte(secondaryRatType.getValue() & 0x0FF);			
		else
			throw new MissingArgumentException("Secondary Rat Type is not set");
		
		if(ebi!=null)
			buffer.writeByte(ebi.byteValue() & 0x0F);			
		else
			throw new MissingArgumentException("EBI is not set");
		
		if(startSeconds!=null)
			buffer.writeInt(startSeconds.intValue());			
		else
			throw new MissingArgumentException("Start Seconds is not set");	
		
		if(endSeconds!=null)
			buffer.writeInt(endSeconds.intValue());			
		else
			throw new MissingArgumentException("End Seconds is not set");	
		
		if(usageDataDL!=null)
			buffer.writeLong(usageDataDL);			
		else
			throw new MissingArgumentException("Usage Data DL is not set");	
		
		if(usageDataUL!=null)
			buffer.writeLong(usageDataUL);			
		else
			throw new MissingArgumentException("Usage Data UL is not set");	
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte currByte=buffer.readByte();
		if((currByte & 0x02)!=0)
			irsgw=true;
		else
			irsgw=false;
		
		if((currByte & 0x01)!=0)
			irpgw=true;
		else
			irpgw=false;
		
		secondaryRatType=SecondaryRatType.fromInt(buffer.readByte() & 0x0FF);
		ebi=buffer.readByte() & 0x0F;
		startSeconds=buffer.readUnsignedInt();	
		endSeconds=buffer.readUnsignedInt();	
		usageDataDL=buffer.readLong();
		usageDataUL=buffer.readLong();
	}

	@Override
	public Long getStartSecond() 
	{
		return this.startSeconds;
	}

	@Override
	public void setStartSeconds(Long seconds) 
	{
		this.startSeconds=seconds;
	}

	@Override
	public Long getEndSecond() 
	{
		return this.endSeconds;
	}

	@Override
	public void setEndSecond(Long seconds) 
	{
		this.endSeconds=seconds;
	}

	@Override
	public Long getUsageDataUL() 
	{
		return this.usageDataUL;
	}

	@Override
	public void setUsageDataUL(Long usage) 
	{
		this.usageDataUL=usage;
	}

	@Override
	public Long getUsageDataDL() 
	{
		return this.usageDataDL;
	}

	@Override
	public void setUsageDataDL(Long usage) 
	{
		this.usageDataDL=usage;
	}

	@Override
	public SecondaryRatType getSecondaryRatType() 
	{
		return secondaryRatType;
	}

	@Override
	public void setSecondaryRatType(SecondaryRatType ratType) 
	{
		this.secondaryRatType=ratType;
	}

	@Override
	public Integer getEBI() 
	{
		return ebi;
	}

	@Override
	public void setEBI(Integer value) 
	{
		this.ebi=value;
	}

	@Override
	public Boolean getIRSGW() 
	{
		return irsgw;
	}

	@Override
	public void setIRSGW(Boolean value) 
	{
		this.irsgw=value;
	}

	@Override
	public Boolean getIRPGW() 
	{
		return irpgw;
	}

	@Override
	public void setIRPGW(Boolean value) 
	{
		this.irpgw=value;
	}
}