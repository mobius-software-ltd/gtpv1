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

import com.mobius.software.telco.protocols.gtp.api.headers.v2.GUTI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IPAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MEI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MMContextForCSToPS;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PTMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PTMSISignature;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TEIDC;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TargetIdentification;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TargetToSourceTransparentContainer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UserLocationInformation;

public interface SRVCCCSToPSRequest extends GTP2Message
{
	IMSI getIMSI();
	
	void setIMSI(IMSI imsi);
	
	MEI getMEI();
	
	void setMEI(MEI mei);
	
	IPAddress getMSCAddressForControlPlane();
	
	void setMSCAddressForControlPlane(IPAddress address);
	
	TEIDC getMSCTEIDForControlPlane();
	
	void setMSCTEIDForControlPlane(TEIDC teidc);
	
	TargetToSourceTransparentContainer getTargetToSourceTransparentContainer();
	
	void setTargetToSourceTransparentContainer(TargetToSourceTransparentContainer container);
	
	TargetIdentification getTargetIdentification();
	
	void setTargetIdentification(TargetIdentification targetIdentification);
	
	PTMSI getPTMSI();
	
	void setPTMSI(PTMSI ptmsi);
	
	UserLocationInformation getSourceRAI();
	
	void setSourceRAI(UserLocationInformation sourceRAI);
	
	PTMSISignature getPTMSISignature();
	
	void setPTMSISignature(PTMSISignature signature);
	
	GUTI getGUTI();
	
	void setGUTI(GUTI guti);
	
	MMContextForCSToPS getMMContextForCSToPS();
	
	void setMMContextForCSToPS(MMContextForCSToPS mmContext);
	
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);
}