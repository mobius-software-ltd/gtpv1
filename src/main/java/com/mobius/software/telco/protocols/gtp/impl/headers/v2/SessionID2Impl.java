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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SessionID2;

public class SessionID2Impl extends AbstractOctetStreamTLV implements SessionID2 
{
	private String mei;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.SESSION_ID_2;
	}

	@Override
	public String getMEI() 
	{
		return mei;
	}

	@Override
	public void setMEI(String mei) 
	{
		this.mei=mei;
	}

	@Override
	public String getOctetString() 
	{
		return mei;
	}

	@Override
	public void setOctetString(String value) 
	{
		this.mei=value;
	}
}