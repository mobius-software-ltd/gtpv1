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

import com.mobius.software.telco.protocols.gtp.api.headers.v2.CompleteRequestMessage;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GUTI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.HopCounter;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IPAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PLMNID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PTMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PTMSISignature;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PortNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UserLocationInformation;

public interface IdentificationRequest extends GTP2Message
{
	GUTI getGUTI();
	
	void setGUTI(GUTI guti);
	
	UserLocationInformation getRAI();
	
	void setRAI(UserLocationInformation rai);
	
	PTMSI getPTMSI();
	
	void setPTMSI(PTMSI ptmsi);
	
	PTMSISignature getPTMSISignature();
	
	void setPTMSISignature(PTMSISignature ptmsiSignature);
	
	CompleteRequestMessage getCompleteAttachRequestMessage();
	
	void setCompleteAttachRequestMessage(CompleteRequestMessage message);
	
	IPAddress getAddressForControlPane();
	
	void setAddressForControlPane(IPAddress addressForControlPane);
	
	PortNumber getUDPSourcePort();
	
	void setUDPSourcePort(PortNumber udpSourcePort);
	
	HopCounter getHopCounter();
	
	void setHopCounter(HopCounter hopCount);
		
	PLMNID getTargetPLMNID();
	
	void setTargetPLMNID(PLMNID targetPLMNID);
	
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);
}