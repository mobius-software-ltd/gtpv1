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
public interface UserLocationInformation extends TLV2
{
	public Boolean getHasCGI();
	
	public Boolean getHasSAI();
	
	public Boolean getHasRAI();
	
	public Boolean getHasTAI();
	
	public Boolean getHasECGI();
	
	public Boolean getHasLAI();
	
	public Boolean getHasMacroENodeBID();
	
	public Boolean getHasExtendedMacroENodeBID();
	
	public CGI getCGI();
	
	public void setCGI(CGI cgi);
	
	public SAI getSAI();
	
	public void setSAI(SAI sai);
	
	public RAI getRAI();
	
	public void setRAI(RAI rai);
	
	public TAI getTAI();
	
	public void setTAI(TAI tai);

	public ECGI getECGI();
	
	public void setECGI(ECGI ecgi);
	
	public LAI getLAI();
	
	public void setLAI(LAI lai);
	
	public MacroENodeBID getMacroENodeBID();
	
	public void setMacroENodeBID(MacroENodeBID macroENodeBID);
	
	public ExtendedMacroENodeBID getExtendedMacroENodeBID();
	
	public void setExtendedMacroENodeBID(ExtendedMacroENodeBID extendedMacroENodeBID);
}
