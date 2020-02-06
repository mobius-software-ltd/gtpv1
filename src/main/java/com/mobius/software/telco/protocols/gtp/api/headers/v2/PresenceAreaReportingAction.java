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


public interface PresenceAreaReportingAction extends TLV2
{
	public PRAAction getPRAAction();
	
	public void setPRAAction(PRAAction value);
	
	public Integer getPraIdentity();
	
	public void setPraIdentity(Integer value);
	
	public Boolean getInactivePRA();
	
	public void setInactivePRA(Boolean value);
	
	public List<CGI> getCGI();
	
	public void setCGI(List<CGI> cgi);
	
	public List<SAI> getSAI();
	
	public void setSAI(List<SAI> sai);
	
	public List<RAI> getRAI();
	
	public void setRAI(List<RAI> rai);
	
	public List<TAI> getTAI();
	
	public void setTAI(List<TAI> tai);

	public List<ECGI> getECGI();
	
	public void setECGI(List<ECGI> ecgi);
	
	public List<HomeENodeBIDWithTAC> getHomeENodeBID();
	
	public void setHomeENodeBID(List<HomeENodeBIDWithTAC> macroENodeBID);
	
	public List<MacroENodeBIDWithTAC> getMacroENodeBID();
	
	public void setMacroENodeBID(List<MacroENodeBIDWithTAC> macroENodeBID);
	
	public List<ExtendedMacroENodeBIDWithTAC> getExtendedMacroENodeBID();
	
	public void setExtendedMacroENodeBID(List<ExtendedMacroENodeBIDWithTAC> extendedMacroENodeBID);
}
