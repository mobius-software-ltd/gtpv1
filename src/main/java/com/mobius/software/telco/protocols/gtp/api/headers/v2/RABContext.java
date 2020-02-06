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
public interface RABContext extends TLV2
{
	public Integer getNSAPI();
	
	void setNSAPI(Integer nsapi);
	
	public void setDownGTPUSequence(Integer sequence);
	
	Integer getDownGTPUSequence();
	
	public void setUpGTPUSequence(Integer sequence);
	
	Integer getUpGTPUSequence();
	
	public void setDownPDCPSequence(Integer sequence);
	
	Integer getDownPDCPSequence();
	
	public void setUpPDCPSequence(Integer sequence);
	
	Integer getUpPDCPSequence();
}
