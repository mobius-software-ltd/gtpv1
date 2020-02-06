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
public interface Cause extends TLV2
{
	public CauseType getCauseType();
	
	public void setCauseType(CauseType value);
	
	public GTP2ElementType getOffendingIEElementType();
	
	public void setOffendingIEElementType(GTP2ElementType elementType);
	
	public Integer getOffendingIEInstance();
	
	public void setOffendingIEInstance(Integer value);
	
	public Boolean getPDNConnectionIEError();
	
	public void setPDNConnectionIEError(Boolean value);
	
	public Boolean getBearerContextIEError();
	
	public void setBearerContextIEError(Boolean value);
	
	public Boolean getCauseSource();
	
	public void setCauseSource(Boolean value);
}
