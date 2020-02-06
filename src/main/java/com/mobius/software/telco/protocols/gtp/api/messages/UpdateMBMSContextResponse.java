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

import com.mobius.software.telco.protocols.gtp.api.headers.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.ChargingGatewayAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.ChargingID;
import com.mobius.software.telco.protocols.gtp.api.headers.GSNAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifierControlPane;

public interface UpdateMBMSContextResponse extends GTPMessage 
{
	Cause getCause();
	
	void setCause(Cause cause);
	
	Recovery getRecovery();
	
	void setRecovery(Recovery value);
	
	TunnerEndpointIdentifierControlPane getControlPaneTEI();
	
	void setControlPageTEI(TunnerEndpointIdentifierControlPane tei);
	
	ChargingID getChargingID();
	
	void setChargingID(ChargingID chargingID);
	
	GSNAddress getGGSNAddressForSignaling();
	
	void setGGSNAddressForSignaling(GSNAddress address);
	
	GSNAddress getAlternateGGSNAddressForSignaling();
	
	void setAlternateGGSNAddressForSignaling(GSNAddress address);
	
	ChargingGatewayAddress getChargingGatewayAddress();
	
	void setChargingGatewayAddress(ChargingGatewayAddress address);
	
	ChargingGatewayAddress getAlternateChargingGatewayAddress();
	
	void setAlternateChargingGatewayAddress(ChargingGatewayAddress address);
	
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);		
}