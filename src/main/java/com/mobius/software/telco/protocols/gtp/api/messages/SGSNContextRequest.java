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

import com.mobius.software.telco.protocols.gtp.api.headers.GSNAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.HopCounter;
import com.mobius.software.telco.protocols.gtp.api.headers.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.MSValidated;
import com.mobius.software.telco.protocols.gtp.api.headers.PTMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.PTMSISignature;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.RatType;
import com.mobius.software.telco.protocols.gtp.api.headers.RoutingAreaIdentity;
import com.mobius.software.telco.protocols.gtp.api.headers.SGSNNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.TLLI;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifierControlPane;

public interface SGSNContextRequest extends GTPMessage 
{
	IMSI getIMSI();
	
	void setIMSI(IMSI imsi);
	
	RoutingAreaIdentity getRAI();
	
	void setRAI(RoutingAreaIdentity rai);
	
	TLLI getTLLI();
	
	void setTLLI(TLLI tlli);
	
	PTMSI getPTMSI();
	
	void setPTMSI(PTMSI ptmsi);
	
	PTMSISignature getPTMSISignature();
	
	void setPTMSISignature(PTMSISignature signature);
	
	MSValidated getMSValidated();
	
	void setMSValidated(MSValidated validate);
	
	TunnerEndpointIdentifierControlPane getTEICP();
	
	void setTEICP(TunnerEndpointIdentifierControlPane teicp);
	
	GSNAddress getSGSNAddressForSignaling();
	
	void setSGSNAddressForSignaling(GSNAddress address);

	GSNAddress getAlternateSGSNAddressForSignaling();
	
	void setAlternateSGSNAddressForSignaling(GSNAddress address);
	
	SGSNNumber getSGSNNumber();
	
	void setSGSNNumber(SGSNNumber sgsnNumber);
	
	RatType getRatType();
	
	void setRatType(RatType ratType);
	
	HopCounter getHopCounter();
	
	void setHopCounter(HopCounter hopCounter);
	
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);
}