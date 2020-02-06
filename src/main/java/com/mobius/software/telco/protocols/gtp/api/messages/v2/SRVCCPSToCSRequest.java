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

import com.mobius.software.telco.protocols.gtp.api.headers.v2.AllocationRetentionPriority;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IPAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MEI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MMContextEUTRAN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MMContextUTRAN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MSISDN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PLMNID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.STNSR;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SVFlags;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ServiceAreaIdentifier;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SourceToTargetTransparentContainer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TEIDC;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TargetCellID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TargetRNCID;

public interface SRVCCPSToCSRequest extends GTP2Message
{
	IMSI getIMSI();
	
	void setIMSI(IMSI imsi);
	
	MEI getMEI();
	
	void setMEI(MEI mei);
	
	SVFlags getSVFlags();
	
	void setSVFlags(SVFlags svFlags);
	
	IPAddress getSGSNAddressForControlPlane();
	
	void setSGSNAddressForControlPlane(IPAddress address);
	
	TEIDC getSGSNTEIDForControlPlane();
	
	void setSGSNTEIDForControlPlane(TEIDC teidc);
	
	MSISDN getCMSISDN();
	
	void setCMSISDN(MSISDN msisdn);
	
	STNSR getSTNSR();
	
	void setSTNSR(STNSR stnSR);
	
	MMContextEUTRAN getMMContextEUTRAN();
	
	void setMMContextEUTRAN(MMContextEUTRAN mmContext);
	
	MMContextUTRAN getMMContextUTRAN();
	
	void setMMContextUTRAN(MMContextUTRAN mmContext);
	
	SourceToTargetTransparentContainer getSourceToTargetTransparentContainer();
	
	void setSourceToTargetTransparentContainer(SourceToTargetTransparentContainer container);
	
	TargetRNCID getTargetRNCID();
	
	void setTargetRNCID(TargetRNCID targetRNCID);
	
	TargetCellID getTargetCellID();
	
	void setTargetCellID(TargetCellID targetCellID);
	
	ServiceAreaIdentifier getSourceSAI();
	
	void setSourceSAI(ServiceAreaIdentifier sourceSAI);
	
	AllocationRetentionPriority getAllocationRetentionPriority();
	
	void setAllocationRetentionPriority(AllocationRetentionPriority arp);
	
	PLMNID getAnchorPLMNID();
	
	void setAnchorPLMNID(PLMNID plmnID);
	
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);
}