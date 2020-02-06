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
import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;

import com.mobius.software.telco.protocols.gtp.api.exceptions.MissingArgumentException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PRAIdentity;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PRAInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PRAType;

public class PRAInformationImpl extends AbstractTLV2 implements PRAInformation 
{
	private List<PRAIdentity> praIdentities;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.PRESENCE_REPORTING_AREA_INFORMATION;
	}

	@Override
	public Integer getLength() 
	{
		if(praIdentities==null)
			return 0;
		
		return praIdentities.size()*4;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(praIdentities==null)
			throw new MissingArgumentException("PRA Identities are not set");
		
		for(int i=0;i<praIdentities.size()-1;i++)
		{
			buffer.writeByte(praIdentities.get(i).getPRAType().getValue() | 0x04);
			buffer.writeByte((praIdentities.get(i).getPRAIdentity()>>16) & 0x0FF);
			buffer.writeShort(praIdentities.get(i).getPRAIdentity().shortValue());
		}
		
		buffer.writeByte(praIdentities.get(praIdentities.size()-1).getPRAType().getValue());
		buffer.writeByte((praIdentities.get(praIdentities.size()-1).getPRAIdentity()>>16) & 0x0FF);
		buffer.writeShort(praIdentities.get(praIdentities.size()-1).getPRAIdentity().shortValue());
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		praIdentities=new ArrayList<PRAIdentity>();
		while(length>0)
		{
			byte currByte=buffer.readByte();
			if((currByte&0x04)!=0)
				currByte-=0x04;
			
			PRAIdentity identity=new PRAIdentityImpl();
			PRAType type=PRAType.fromInt(currByte & 0x0FF);
			Integer praIdentity=(buffer.readByte()<<16) | 0x0FF0000;
			praIdentity+=buffer.readShort();
			identity.setPRAIdentity(praIdentity);
			identity.setPRAType(type);
			praIdentities.add(identity);
		}
	}

	@Override
	public List<PRAIdentity> getPRAIdentity() 
	{
		return praIdentities;
	}

	@Override
	public void setPRAIdentity(List<PRAIdentity> value) 
	{
		this.praIdentities=value;
	}
}