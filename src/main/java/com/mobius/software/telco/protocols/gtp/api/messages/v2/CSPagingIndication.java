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

import com.mobius.software.telco.protocols.gtp.api.headers.v2.ChannelNeeded;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EMLPPPriority;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FQDN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GlobalCNID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ServiceIndicator;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UserLocationInformation;

public interface CSPagingIndication extends GTP2Message
{
	IMSI getIMSI();
	
	void setIMSI(IMSI imsi);
	
	FQDN getVLRName();
	
	void setVLRName(FQDN vlrName);
	
	TMSI getTMSI();
	
	void setTMSI(TMSI tmsi);
	
	UserLocationInformation getLocationAreaIdentifier();
	
	void setLocationAreaIdentifier(UserLocationInformation locationAreaIdentifier);
	
	GlobalCNID getGlobalCNID();
	
	void setGlobalCNID(GlobalCNID globalCNID);
	
	ChannelNeeded getChannelNeeded();
	
	void setChannelNeeded(ChannelNeeded channelNeeded);
	
	EMLPPPriority getEMLPPPriority();
	
	void setEMLPPPriority(EMLPPPriority eMLPPPriority);
	
	ServiceIndicator getServiceIndicator();
	
	void setServiceIndicator(ServiceIndicator serviceIndicator);
	
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);
}