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
import com.mobius.software.telco.protocols.gtp.api.headers.GSNAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.MBMSFlowIdentifier;
import com.mobius.software.telco.protocols.gtp.api.headers.MBMSServiceArea;
import com.mobius.software.telco.protocols.gtp.api.headers.MBMSSessionDuration;
import com.mobius.software.telco.protocols.gtp.api.headers.MBMSSessionIdentifier;
import com.mobius.software.telco.protocols.gtp.api.headers.MBMSSessionRepetionNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.TMGI;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifierControlPane;

public interface MBMSSessionUpdateRequest extends GTPMessage 
{
	TunnerEndpointIdentifierControlPane getControlPaneTEI();
	
	void setControlPageTEI(TunnerEndpointIdentifierControlPane tei);
	
	EndUserAddress getEndUserAddress();
	
	void setEndUserAddress(EndUserAddress address);
	
	APN getAPN();
	
	void setAPN(APN apn);
	
	GSNAddress getGGSNAddressForSignaling();
	
	void setGGSNAddressForSignaling(GSNAddress address);
	
	TMGI getTMGI();
	
	void setTMGI(TMGI tmgi);
	
	MBMSSessionDuration getMBMSSessionDuration();
	
	void setMBMSSessionDuration(MBMSSessionDuration mbmsSessionDuration);
	
	MBMSServiceArea getMBMSServiceArea();
	
	void setMBMSServiceArea(MBMSServiceArea serviceArea);
	
	MBMSSessionIdentifier getMBMSSessionIdentifier();
	
	void setMBMSSessionIdentifier(MBMSSessionIdentifier sessionIdentifier);
	
	MBMSSessionRepetionNumber getMBMSSessionRepetionNumber();
	
	void setMBMSSessionRepetionNumber(MBMSSessionRepetionNumber sessionRepetititionNumber);
	
	MBMSFlowIdentifier getMBMSFlowIdentifier();
	
	void setMBMSFlowIdentifier(MBMSFlowIdentifier mbmsFlowIdentifier);
	
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);		
}
