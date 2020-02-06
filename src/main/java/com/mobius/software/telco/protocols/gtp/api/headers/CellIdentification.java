package com.mobius.software.telco.protocols.gtp.api.headers;
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
public interface CellIdentification extends TLV1 
{
	String getTargetMCC();
	
	void setTargetMCC(String mcc);
	
	String getTargetMNC();
	
	void setTargetMNC(String mnc);
	
	Integer getTargetLAC();
	
	void setTargetLAC(Integer lac);
	
	Integer getTargetRAC();
	
	void setTargetRAC(Integer rac);
	
	Integer getTargetCI();
	
	void setTargetCI(Integer ci);
	
	String getSourceMCC();
	
	void setSourceMCC(String mcc);
	
	String getSourceMNC();
	
	void setSourceMNC(String mnc);
	
	Integer getSourceLAC();
	
	void setSourceLAC(Integer lac);
	
	Integer getSourceRAC();
	
	void setSourceRAC(Integer rac);
	
	Integer getSourceCI();
	
	void setSourceCI(Integer ci);
	
	Integer getSourceRNCID();
	
	void setSourceRNCID(Integer rncID);		
}
