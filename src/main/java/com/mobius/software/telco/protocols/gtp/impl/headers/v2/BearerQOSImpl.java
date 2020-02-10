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
import com.mobius.software.telco.protocols.gtp.api.headers.PriorityLevel;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.BearerQos;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;

public class BearerQOSImpl extends AbstractTLV2 implements BearerQos 
{
	Boolean pvi;
	Boolean pci;
	PriorityLevel level;
	Integer label;
	Long maximumBitrateforUL;
	Long maximumBitrateforDL;
	Long guaranteedBitrateforUL;
	Long guaranteedBitrateforDL;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.BEARER_QOS;
	}



	@Override
	public Integer getLength() 
	{
		return 22;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws GTPParseException 
	{
		if(level==null)
			throw new GTPParseException("Priority level not set");
		
		if(label==null)
			throw new GTPParseException("Label not set");
		
		if(maximumBitrateforUL==null)
			throw new GTPParseException("Maximum bitrate for uplink not set");
		
		if(maximumBitrateforDL==null)
			throw new GTPParseException("Maximum bitrate for downlink not set");
		
		if(guaranteedBitrateforUL==null)
			throw new GTPParseException("Guaranteed bitrate for uplink not set");
		
		if(guaranteedBitrateforDL==null)
			throw new GTPParseException("Guaranteed bitrate for downlink not set");
				
		byte currByte=0;
		if(pvi!=null && pvi)
			currByte|=0x01;
		
		if(pci!=null && pci)
			currByte|=0x40;
		
		currByte|=((level.getValue()<<3) & 0x3C);
		buffer.writeByte(currByte);
		buffer.writeByte(label);
		
		buffer.writeByte((byte)((maximumBitrateforUL>>32) & 0x0FF));
		buffer.writeInt(maximumBitrateforUL.intValue());
		
		buffer.writeByte((byte)((maximumBitrateforDL>>32) & 0x0FF));
		buffer.writeInt(maximumBitrateforDL.intValue());
		
		buffer.writeByte((byte)((guaranteedBitrateforUL>>32) & 0x0FF));
		buffer.writeInt(guaranteedBitrateforUL.intValue());
		
		buffer.writeByte((byte)((guaranteedBitrateforDL>>32) & 0x0FF));
		buffer.writeInt(guaranteedBitrateforDL.intValue());
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) throws GTPParseException 
	{
		byte currByte=buffer.readByte();
		pvi=((currByte&0x01)!=0);
		pci=((currByte&0x40)!=0);
		
		level=PriorityLevel.fromInt((currByte>>3) & 0x07);
		label=buffer.readByte() & 0x0FF;
		
		maximumBitrateforUL=((buffer.readByte()<<32) & 0x0FF00000000L) + buffer.readUnsignedInt();
		maximumBitrateforDL=((buffer.readByte()<<32) & 0x0FF00000000L) + buffer.readUnsignedInt();
		guaranteedBitrateforUL=((buffer.readByte()<<32) & 0x0FF00000000L) + buffer.readUnsignedInt();
		guaranteedBitrateforDL=((buffer.readByte()<<32) & 0x0FF00000000L) + buffer.readUnsignedInt();
	}

	@Override
	public Boolean getPVI() 
	{
		return pvi;
	}

	@Override
	public void setPVI(Boolean value) 
	{
		this.pvi=value;
	}

	@Override
	public Boolean getPCI() 
	{
		return pci;
	}

	@Override
	public void setPCI(Boolean value) 
	{
		this.pci=value;
	}

	@Override
	public PriorityLevel getLevel() 
	{
		return level;
	}

	@Override
	public void setLevel(PriorityLevel level) 
	{
		this.level=level;
	}

	@Override
	public Integer getLabel() 
	{
		return label;
	}

	@Override
	public void setLabel(Integer label) 
	{
		this.label=label;
	}

	@Override
	public Long getMaximumBitrateforUL() 
	{
		return maximumBitrateforUL;
	}

	@Override
	public void setMaximumBitrateforUL(Long value) 
	{
		this.maximumBitrateforUL=value;
	}

	@Override
	public Long getMaximumBitrateforDL() 
	{
		return maximumBitrateforDL;
	}

	@Override
	public void setMaximumBitrateforDL(Long value) 
	{
		this.maximumBitrateforDL=value;
	}

	@Override
	public Long getGuaranteedBitrateforUL() 
	{
		return this.guaranteedBitrateforUL;
	}

	@Override
	public void setGuaranteedBitrateforUL(Long value) 
	{
		this.guaranteedBitrateforUL=value;
	}

	@Override
	public Long getGuaranteedBitrateforDL() 
	{
		return this.guaranteedBitrateforDL;
	}

	@Override
	public void setGuaranteedBitrateforDL(Long value) 
	{
		this.guaranteedBitrateforDL=value;
	}
}