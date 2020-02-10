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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.BearerQos;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPSBearerID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FContainer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TFT;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TransactionIdentifier;

public interface ContextResponseBearerContext extends TLV2
{
	EPSBearerID getEPSBearerID();
	
	void setEPSBearerID(EPSBearerID bearerID);
	
	TFT getTFT();
	
	void setTFT(TFT tft);
	
	FTEID getSGWFTEID();
	
	void setSGWFTEID(FTEID fteid);
	
	FTEID getS5S8PGWFTEID();
	
	void setS5S8PGWFTEID(FTEID fteid);
	
	BearerQos getBearerQos();
	
	void setBearerQos(BearerQos bearerQos);
	
	FContainer getBSSContainer();
	
	void setBSSContainer(FContainer bssContainer);
	
	TransactionIdentifier getTransactionIdentifier();
	
	void setTransactionIdentifier(TransactionIdentifier transactionIdentifier);
	
	FTEID getS11SGWFTEID();
	
	void setS11SGWFTEID(FTEID fteid);		
}