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

import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.ContextAcknowledgeBearerContext;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Indication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.NodeIdentifier;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.NodeNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;

public interface ContextAcknowledge extends GTP2Message
{
	Cause getCause();
	
	void setCause(Cause cause);
	
	Indication getIndication();
	
	void setIndication(Indication indication);		
	
	FTEID getForwardingFTEID();
	
	void setForwardingFTEID(FTEID forwardingFTEID);
	
	List<ContextAcknowledgeBearerContext> getBearerContext();
	
	void setBearerContext(List<ContextAcknowledgeBearerContext> bearerContext);
	
	NodeNumber getSGSNNodeNumber();
	
	void setSGSNNodeNumber(NodeNumber sgsnNodeNumber);
	
	NodeNumber getMTNumberForMTSMS();
	
	void setMTNumberForMTSMS(NodeNumber mtNumberForMTSMS);
	
	NodeIdentifier getSGSNIdentifierForMTSMS();
	
	void setSGSNIdentifierForMTSMS(NodeIdentifier sgsnIdentifierForMTSMS);
	
	NodeIdentifier getMMEIdentifierForMTSMS();
	
	void setMMEIdentifierForMTSMS(NodeIdentifier mmeIdentifierForMTSMS);
	
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);
}