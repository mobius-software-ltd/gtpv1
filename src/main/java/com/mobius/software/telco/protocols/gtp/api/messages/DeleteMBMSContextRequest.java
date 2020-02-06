package com.mobius.software.telco.protocols.gtp.api.messages;
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

import com.mobius.software.telco.protocols.gtp.api.headers.APN;
import com.mobius.software.telco.protocols.gtp.api.headers.EndUserAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.EnhancedNSAPI;
import com.mobius.software.telco.protocols.gtp.api.headers.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.MBMSProtocolConfigurationOptions;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifierControlPane;

public interface DeleteMBMSContextRequest extends GTPMessage 
{
	IMSI getIMSI();
	
	void setIMSI(IMSI imsi);
	
	TunnerEndpointIdentifierControlPane getControlPaneTEI();
	
	void setControlPageTEI(TunnerEndpointIdentifierControlPane tei);
	
	EndUserAddress getEndUserAddress();
	
	void setEndUserAddress(EndUserAddress address);
	
	APN getAPN();
	
	void setAPN(APN apn);
	
	MBMSProtocolConfigurationOptions getMBMSProtocolConfigurationOptions();
	
	void setMBMSProtocolConfigurationOptions(MBMSProtocolConfigurationOptions mbmsProtocolConfigurationOptions);
	
	EnhancedNSAPI getEnhancedNSAPI();
	
	void setEnhancedNSAPI(EnhancedNSAPI enhancedNSAPI);
	
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);
}