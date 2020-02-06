package com.mobius.software.telco.protocols.gtp.api.messages.v2;
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

import com.mobius.software.telco.protocols.gtp.api.headers.v2.EUTRANRoundTripDelay;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.HRPDSectorID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.HandoverIndicator;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PDNGWPMIPGRETunnelInfo;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.S101TransparentContainer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.S103GRETunnelInfo;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.S103HSGWIPAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SessionID2;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UnauthenticatedIMSI;

public interface DirectTransferRequest extends GTP2Message 
{
	IMSI getSessionID();
	
	void setSessionID(IMSI imsi);
	
	SessionID2 getSessionID2();
	
	void setSessionID2(SessionID2 sessionID2);
	
	HRPDSectorID getHRPDSectorID();
	
	void setHRPDSectorID(HRPDSectorID hrpdSectorID);
	
	S101TransparentContainer getS101TransparentContainer();
	
	void setS101TransparentContainer(S101TransparentContainer s101Container);
	
	PDNGWPMIPGRETunnelInfo getPDNGWPMIPGRETunnelInfo();
	
	void setPDNGWPMIPGRETunnelInfo(PDNGWPMIPGRETunnelInfo tunnelInfo);
	
	S103GRETunnelInfo getS103GRETunnelInfo();
	
	void setS103GRETunnelInfo(S103GRETunnelInfo tunnelInfo);
	
	S103HSGWIPAddress getS103HSGWIPAddress();
	
	void setS103HSGWIPAddress(S103HSGWIPAddress ipAddress);
	
	HandoverIndicator getHandoverIndicator();
	
	void setHandoverIndicator(HandoverIndicator handoverIndicator);
	
	EUTRANRoundTripDelay getEUTRANRoundTripDelay();
	
	void setEUTRANRoundTripDelay(EUTRANRoundTripDelay roundTripDelay);
	
	UnauthenticatedIMSI getUnauthenticatedIMSI();
	
	void setUnauthenticatedIMSI(UnauthenticatedIMSI unauthenticatedIMSI);
	
	Recovery getRecovery();
	
	void setRecovery(Recovery value);
	
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);		
}