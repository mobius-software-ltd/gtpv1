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

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FlowQOS;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;

public class FlowQOSImpl extends AbstractTLV2 implements FlowQOS 
{
	private Integer qci;
	private Long ulRate;
	private Long dlRate;
	private Long guaranteedUlRate;
	private Long guaranteedDlRate;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.FLOW_QOS;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=21;		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws GTPParseException 
	{
		if(qci!=null)
			buffer.writeByte((byte)(qci & 0x0FF));			
		else
			throw new GTPParseException("qci note set");
		
		if(ulRate!=null)
		{
			buffer.writeByte((byte)((ulRate>>32) & 0x0FF));
			buffer.writeInt(ulRate.intValue());
		}			
		else
			throw new GTPParseException("ul rate note set");
		
		if(dlRate!=null)
		{
			buffer.writeByte((byte)((dlRate>>32) & 0x0FF));
			buffer.writeInt(dlRate.intValue());
		}			
		else
			throw new GTPParseException("dl rate note set");
		
		if(guaranteedUlRate!=null)
		{
			buffer.writeByte((byte)((guaranteedUlRate>>32) & 0x0FF));
			buffer.writeInt(guaranteedUlRate.intValue());
		}			
		else
			throw new GTPParseException("guaranteed ul rate note set");
		
		if(guaranteedDlRate!=null)
		{
			buffer.writeByte((byte)((guaranteedDlRate>>32) & 0x0FF));
			buffer.writeInt(guaranteedDlRate.intValue());
		}			
		else
			throw new GTPParseException("guaranteed dl rate note set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		qci=buffer.readByte() & 0x0FF;
		ulRate=((buffer.readByte()<<32) & 0x0FF00000000L);
		dlRate=((buffer.readByte()<<32) & 0x0FF00000000L);
		guaranteedUlRate=((buffer.readByte()<<32) & 0x0FF00000000L);
		guaranteedDlRate=((buffer.readByte()<<32) & 0x0FF00000000L);
	}

	@Override
	public Integer getQCI() 
	{
		return qci;
	}

	@Override
	public void setQCI(Integer qci) 
	{
		this.qci=qci;
	}

	@Override
	public Long getMaximumBitrateUL() 
	{
		return ulRate;
	}

	@Override
	public void setMaximumBitrateUL(Long rate) 
	{
		this.ulRate=rate;
	}

	@Override
	public Long getMaximumBitrateDL() 
	{
		return dlRate;
	}

	@Override
	public void setMaximumBitrateDL(Long rate) 
	{
		this.dlRate=rate;
	}

	@Override
	public Long getGuaranteedBitrateUL() 
	{
		return this.guaranteedUlRate;
	}

	@Override
	public void setGuaranteedBitrateUL(Long rate) 
	{
		this.guaranteedUlRate=rate;
	}

	@Override
	public Long getGuaranteedBitrateDL() 
	{
		return this.guaranteedDlRate;
	}

	@Override
	public void setGuaranteedBitrateDL(Long rate) 
	{
		this.guaranteedDlRate=rate;
	}
}