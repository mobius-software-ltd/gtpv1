package com.mobius.software.telco.protocols.gtp.api.bcontexts.v2;
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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.BearerFlags;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.BearerQos;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ChargingID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPSBearerID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;

public interface CreateSessionResponseBearerContextToBeCreated extends TLV2
{
	EPSBearerID getEPSBearerID();
	
	void setEPSBearerID(EPSBearerID bearerID);
	
	Cause getCause();
	
	void setCause(Cause cause);
	
	FTEID getS1USGWFTEID();
	
	void setS1USGWFTEID(FTEID fteid);
	
	FTEID getS4SGWFTEID();
	
	void setS4SGWFTEID(FTEID fteid);
	
	FTEID getS5S8UPGWFTEID();
	
	void setS5S8UPGWFTEID(FTEID fteid);
	
	FTEID getS12SGWFTEID();
	
	void setS12SGWFTEID(FTEID fteid);
	
	FTEID getS2bUPGWFTEID();
	
	void setS2bUPGWFTEID(FTEID fteid);
	
	FTEID getS2aPGWFTEID();
	
	void setS2aPGWFTEID(FTEID fteid);
	
	BearerQos getBearerQos();
	
	void setBearerQos(BearerQos bearerQos);
	
	ChargingID getChargingID();
	
	void setChargingID(ChargingID chargingID);
	
	BearerFlags getBearerFlags();
	
	void setBearerFlags(BearerFlags bearerFlags);
	
	FTEID getS11USGWFTEID();
	
	void setS11USGWFTEID(FTEID fteid);
}