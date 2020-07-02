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

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.ExtentionHeader;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;

public interface GTPMessage extends GenericGTPMessage
{
	MessageType getMessageType();
	
	Boolean getExtentionHeaderFlag();
	
	Boolean getNPDUFlag();
	
	Integer getNPDUNumber();
	
	void setNPDUNumber(Integer npdu);
	
	Boolean getSequenceNumberFlag();
	
	Integer getSequenceNumber();
	
	void setSequenceNumber(Integer sequenceNumber);
	
	Long getTEID();
	
	void setTEID(Long value);
	
	List<ExtentionHeader> getExtentionHeaders();
	
	void setExtentionHeaders(List<ExtentionHeader> headers);
	
	List<TLV1> getTLVs() throws GTPParseException;
	
	void applyTLV(TLV1 tlv,Boolean ignoreUnknown) throws GTPParseException;
}
