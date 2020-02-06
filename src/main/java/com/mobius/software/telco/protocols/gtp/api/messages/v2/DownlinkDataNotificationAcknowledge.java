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

import com.mobius.software.telco.protocols.gtp.api.headers.v2.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.DelayValue;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPCTimer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IntegerNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Throttling;

public interface DownlinkDataNotificationAcknowledge extends GTP2Message
{
	Cause getCause();
	
	void setCause(Cause cause);
	
	DelayValue getDataNotificationDelay();
	
	void setDataNotificationDelay(DelayValue notificationDelay);
	
	Recovery getRecovery();
	
	void setRecovery(Recovery recovery);
	
	Throttling getLowPriorityTrafficThrottling();
	
	void setLowPriorityTrafficThrottling(Throttling imsi);
	
	IMSI getIMSI();
	
	void setIMSI(IMSI imsi);
	
	EPCTimer getDLBufferDuration();
	
	void setDLBufferDuration(EPCTimer indication);
	
	IntegerNumber getDLBufferingSuggestedPacketCount();
	
	void setDLBufferingSuggestedPacketCount(IntegerNumber count);
		
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);
}