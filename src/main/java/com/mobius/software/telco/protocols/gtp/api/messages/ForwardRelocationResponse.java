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

import com.mobius.software.telco.protocols.gtp.api.headers.AdditionalRABSetupInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.BSSContainer;
import com.mobius.software.telco.protocols.gtp.api.headers.BSSGPCause;
import com.mobius.software.telco.protocols.gtp.api.headers.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.ExtendedRanapCause;
import com.mobius.software.telco.protocols.gtp.api.headers.GSNAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.ListOfSetupPFC;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.RABSetupInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.RanapCause;
import com.mobius.software.telco.protocols.gtp.api.headers.SGSNNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifier2;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifierControlPane;
import com.mobius.software.telco.protocols.gtp.api.headers.UTRANTransparentContent;

public interface ForwardRelocationResponse extends GTPMessage 
{
	Cause getCause();
	
	void setCause(Cause cause);
	
	TunnerEndpointIdentifierControlPane getControlPaneTEI();
	
	void setControlPageTEI(TunnerEndpointIdentifierControlPane tei);
	
	TunnerEndpointIdentifier2 getTunnerEndpointIdentifier2();
	
	void setTunnerEndpointIdentifier2(TunnerEndpointIdentifier2 tei2);
	
	RanapCause getRanapCause();
	
	void setRanapCause(RanapCause ranapCause);
	
	GSNAddress getSGSNAddressForSignaling();
	
	void setSGSNAddressForSignaling(GSNAddress address);
	
	GSNAddress getSGSNAddressForTraffic();
	
	void setSGSNAddressForTraffic(GSNAddress address);
	
	UTRANTransparentContent getUTRANTransparentContent();
	
	void setUTRANTransparentContent(UTRANTransparentContent context);
	
	RABSetupInformation getRABSetupInformation();
	
	void setRABSetupInformation(RABSetupInformation rabSetupInformation);
	
	AdditionalRABSetupInformation getAdditionalRABSetupInformation();
	
	void setAdditionalRABSetupInformation(AdditionalRABSetupInformation rabSetupInformation);
	
	SGSNNumber getSGSNNumber();
	
	void setSGSNNumber(SGSNNumber sgsnNumber);
	
	BSSContainer getBSSContainer();
	
	void setBSSContainer(BSSContainer container);
	
	BSSGPCause getBSSGPCause();
	
	void setBSSGPCause(BSSGPCause bssGPCause);
	
	ListOfSetupPFC getListOfSetupPFC();
	
	void setListOfSetupPFC(ListOfSetupPFC list);
	
	ExtendedRanapCause getExtendedRanapCause();
	
	void setExtendedRanapCause(ExtendedRanapCause extendedRanapCause);
	
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);
}