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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TMGI;

public class TMGIImpl extends AbstractOctetStreamTLV implements TMGI 
{
	private String tmgi;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.TMGI;
	}

	@Override
	public String getTMGI() 
	{
		return tmgi;
	}

	@Override
	public void setTMGI(String tmgi) 
	{
		this.tmgi=tmgi;
	}

	@Override
	public String getOctetString() 
	{
		return tmgi;
	}

	@Override
	public void setOctetString(String value) 
	{
		this.tmgi=value;
	}
}