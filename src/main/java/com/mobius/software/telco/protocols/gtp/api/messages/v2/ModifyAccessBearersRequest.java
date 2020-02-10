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

import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.ModifyAccessBearerRequestBearerContextToBeModified;
import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.ModifyAccessBearerRequestBearerContextToBeRemoved;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.DelayValue;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Indication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SecondaryRatUsageDataReport;

public interface ModifyAccessBearersRequest extends GTP2Message
{
	Indication getIndication();
	
	void setIndication(Indication indication);
	
	FTEID getSenderFTEIDControlPlane();
	
	void setSenderFTEIDControlPlane(FTEID fteid);
	
	DelayValue getDelayValue();
	
	void setDelayValue(DelayValue delayValue);
	
	List<ModifyAccessBearerRequestBearerContextToBeModified> getBearerContextToBeModified();
	
	void setBearerContextToBeModified(List<ModifyAccessBearerRequestBearerContextToBeModified> bearerContext);
	
	List<ModifyAccessBearerRequestBearerContextToBeRemoved> getBearerContextToBeRemoved();
	
	void setBearerContextToBeRemoved(List<ModifyAccessBearerRequestBearerContextToBeRemoved> bearerContext);
	
	Recovery getRecovery();
	
	void setRecovery(Recovery recovery);
	
	SecondaryRatUsageDataReport getSecondaryRatUsageDataReport();
	
	void setSecondaryRatUsageDataReport(SecondaryRatUsageDataReport secondaryRatUsageDataReport);
		
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);
}