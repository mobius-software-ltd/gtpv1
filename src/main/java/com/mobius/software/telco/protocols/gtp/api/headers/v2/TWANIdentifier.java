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
import java.net.Inet4Address;
import java.net.Inet6Address;


public interface TWANIdentifier extends TLV2
{
	public Boolean getHasBSSID();
	
	public Boolean getHasCivicAddress();
	
	public Boolean getHasPLMNID();
	
	public Boolean getHasOperatorName();
	
	public Boolean getHasLogicalAccessID();
	
	public String getSSID();
	
	public void setSSID(String ssid);
	
	public String getBSSID();
	
	public void setBSSID(String bssid);
	
	public CivicAddress getCivicAddress();
	
	public void setCivicAddress(CivicAddress civicAddress);
	
	public NetworkIdentity getPLMNID();
	
	public void setPLMNID(NetworkIdentity identity);

	public String getOperatorName();
	
	public void setOperatorName(String operatorName);
	
	Inet4Address getV4Address(); 
	
	Inet6Address getV6Address();
	
	void setV4Address(Inet4Address address);
	
	void setV6Address(Inet6Address address); 
	
	String getFQDN();
	
	void setFQDN(String fqdn);
	
	String getCircuitID();
	
	void setCircuitID(String cirquitID);
}
