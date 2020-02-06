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

import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPSBearerID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.HopCounter;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IPAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.NodeType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PTMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PortNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UserLocationInformation;

public interface SuspendNotification extends GTP2Message
{
	IMSI getIMSI();
	
	void setIMSI(IMSI imsi);
	
	UserLocationInformation getRAI();
	
	void setRAI(UserLocationInformation rai);
	
	EPSBearerID getLinkedEPSBearerID();
	
	void setLinkedEPSBearerID(EPSBearerID ebi);
	
	PTMSI getPTMSI();
	
	void setPTMSI(PTMSI ptmsi);
	
	NodeType getOriginatingNode();
	
	void setOriginatingNode(NodeType originatingNode);
	
	IPAddress getAddressForControlPane();
	
	void setAddressForControlPane(IPAddress address);
	
	PortNumber getUDPSourcePort();
	
	void setUDPSourcePort(PortNumber udpSourcePort);
	
	HopCounter getHopCounter();
	
	void setHopCounter(HopCounter hopCount);
		
	FTEID getSenderFTEIDControlPlane();
	
	void setSenderFTEIDControlPlane(FTEID fteid);
	
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);
}