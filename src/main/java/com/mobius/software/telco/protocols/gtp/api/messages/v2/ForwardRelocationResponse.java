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

import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.ForwardRelocationResponseBearerContext;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FCause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FContainer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FQDN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Indication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.LDN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.NodeIdentifier;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.NodeNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;

public interface ForwardRelocationResponse extends GTP2Message
{
	Cause getCause();
	
	void setCause(Cause cause);
	
	FTEID getSenderFTEIDControlPlane();
	
	void setSenderFTEIDControlPlane(FTEID fteid);
	
	Indication getIndication();
	
	void setIndication(Indication indication);
	
	List<ForwardRelocationResponseBearerContext> getListOfSetupBearers();
	
	void setListOfSetupBearers(List<ForwardRelocationResponseBearerContext> bearerContext);
	
	List<ForwardRelocationResponseBearerContext> getListOfRABs();
	
	void setListOfSetupRABs(List<ForwardRelocationResponseBearerContext> bearerContext);
	
	List<ForwardRelocationResponseBearerContext> getListOfSetupPFCs();
	
	void setListOfSetupPFCs(List<ForwardRelocationResponseBearerContext> bearerContext);
	
	FCause getS1APCause();
	
	void setS1APCause(FCause cause);
	
	FCause getRANAPCause();
	
	void setRANAPCause(FCause cause);
	
	FCause getBSSGPCause();
	
	void setBSSGPCause(FCause cause);
	
	FContainer getEUTRANTransparentContainer();
	
	void setEUTRANTransparentContainer(FContainer transparentContainer);
		
	FContainer getUTRANTransparentContainer();
	
	void setUTRANTransparentContainer(FContainer transparentContainer);
		
	FContainer getBSSContainer();
	
	void setBSSContainer(FContainer bssContainer);
		
	LDN getSGSNLDN();
	
	void setSGSNLDN(LDN ldn);
	
	NodeNumber getSGSNNodeNumber();
	
	void setSGSNNodeNumber(NodeNumber nodeNumber);
	
	FQDN getSGSNNodeName();
	
	void setSGSNodeName(FQDN sgsnNodeName);
		
	FQDN getMMENodeName();
	
	void setMMENodeName(FQDN mmeNodeName);	
	
	NodeIdentifier getSGSNIdentifier();
	
	void setSGSIdentifier(NodeIdentifier sgsnIdentifier);
		
	NodeIdentifier getMMEIdentifier();
	
	void setMMEIdentifier(NodeIdentifier mmeIdentifier);	
	
	NodeNumber getMMENodeNumberForMTSMS();
	
	void setMMENodeNumberForMTSMS(NodeNumber nodeNumber);
	
	NodeIdentifier getSGSNIdentifierForMTSMS();
	
	void setSGSIdentifierForMTSMS(NodeIdentifier sgsnIdentifier);
		
	NodeIdentifier getMMEIdentifierForMTSMS();
	
	void setMMEIdentifierForMTSMS(NodeIdentifier mmeIdentifier);	
	
	List<ForwardRelocationResponseBearerContext> getSetupBearersForSCEFPDNConnections();
	
	void setSetupBearersForSCEFPDNConnections(List<ForwardRelocationResponseBearerContext> bearerContext);
	
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);
}