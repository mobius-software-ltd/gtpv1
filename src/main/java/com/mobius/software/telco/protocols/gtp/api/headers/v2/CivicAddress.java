package com.mobius.software.telco.protocols.gtp.api.headers.v2;
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
import java.util.List;

import io.netty.buffer.ByteBuf;

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;

public interface CivicAddress 
{
	Integer getLength();
	
	String getCountryCode();
	
	void setCountryCode(String cc);
	
	List<CivicAddressElement> getElements();
	
	void setElements(List<CivicAddressElement> elements);
	
	void encode(ByteBuf buffer) throws GTPParseException;
	
	void decode(ByteBuf buffer,Integer length) throws GTPParseException;
}
