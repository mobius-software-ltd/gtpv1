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
import com.mobius.software.telco.protocols.gtp.api.headers.ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.IMEISV;

public class IMEISVImpl extends AbstractOctetStreamTLV implements IMEISV 
{
	private String imeisv;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.IMEI_SV;
	}

	@Override
	public Integer getLength() 
	{
		return 8;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return true;
	}

	@Override
	public String getIMEISV() 
	{
		return imeisv;
	}

	@Override
	public void setIMEISV(String imeisv) 
	{
		this.imeisv=imeisv;
	}

	@Override
	public String getOctetString() 
	{
		return imeisv;
	}

	@Override
	public void setOctetString(String value) 
	{
		this.imeisv=value;
	}
}