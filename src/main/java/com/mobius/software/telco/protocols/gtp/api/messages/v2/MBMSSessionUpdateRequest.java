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

import com.mobius.software.telco.protocols.gtp.api.headers.v2.AbsoluteTimeOfMBMSDataTransfer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.BearerQos;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ECGIList;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MBMSFlowIdentifier;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MBMSServiceArea;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MBMSSessionDuration;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MBMSSessionIdentifier;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MBMSTimeToDataTransfer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TMGI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;

public interface MBMSSessionUpdateRequest extends GTP2Message
{
	MBMSServiceArea getMBMSServiceArea();
	
	void setMBMSServiceArea(MBMSServiceArea mbmsServiceArea);
	
	TMGI getTMGI();
	
	void setTMGI(TMGI tmgi);
	
	FTEID getSenderFTEIDControlPlane();
	
	void setSenderFTEIDControlPlane(FTEID fteid);
	
	MBMSSessionDuration getMBMSSessionDuration();
	
	void setMBMSSessionDuration(MBMSSessionDuration mbmsSessionDuration);
	
	BearerQos getBearerQos();
	
	void setBearerQos(BearerQos bearerQos);
	
	MBMSSessionIdentifier getMBMSSessionIdentifier();
	
	void setMBMSSessionIdentifier(MBMSSessionIdentifier mbmsSessionIdentifier);
	
	MBMSFlowIdentifier getMBMSFlowIdentifier();
	
	void setMBMSFlowIdentifier(MBMSFlowIdentifier mbmsFlowIdentifier);
	
	MBMSTimeToDataTransfer getMBMSTimeToDataTransfer();
	
	void setMBMSTimeToDataTransfer(MBMSTimeToDataTransfer mbmsTimeToDataTransfer);
	
	AbsoluteTimeOfMBMSDataTransfer getMBMSDataTransferUpdate();
	
	void setMBMSDataTransferUpdate(AbsoluteTimeOfMBMSDataTransfer mbmsDataTransferUpdate);
	
	ECGIList getMBMSCellList();
	
	void setMBMSCellList(ECGIList mbmsCellList);
	
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);
}