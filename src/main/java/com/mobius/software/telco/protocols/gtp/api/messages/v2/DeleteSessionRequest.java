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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPSBearerID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ExtendedProtocolConfigurationOptions;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IPAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Indication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.NodeType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.OverloadControlInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PortNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ProtocolConfigurationOption;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RANNASCause;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SecondaryRatUsageDataReport;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TWANIdentifier;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TWANTimeStamp;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UETimezone;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ULITimestamp;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UserLocationInformation;

public interface DeleteSessionRequest extends GTP2Message
{
	Cause getCause();
	
	void setCause(Cause cause);
	
	EPSBearerID getEPSBearerID();
	
	void setEPSBearerID(EPSBearerID epsBearerID);
	
	UserLocationInformation getUserLocationInformation();
	
	void setUserLocationInformation(UserLocationInformation uli);
	
	Indication getIndication();
	
	void setIndication(Indication indication);
	
	ProtocolConfigurationOption getProtocolConfigurationOption();
	
	void setProtocolConfigurationOption(ProtocolConfigurationOption pco);
	
	NodeType getOriginatingNode();
	
	void setOriginatingNode(NodeType originatingNode);
	
	FTEID getSenderFTEIDControlPlane();
	
	void setSenderFTEIDControlPlane(FTEID fteid);
	
	UETimezone getUETimezone();
	
	void setUETimezone(UETimezone ueTimezone);
	
	ULITimestamp getULITimestamp();
	
	void setULITimestamp(ULITimestamp uliTimestamp);
	
	RANNASCause getRANNASCause();
	
	void setRANNASCause(RANNASCause ranNasCause);
	
	TWANIdentifier getTWANIdentifier();
	
	void setTWANIdentifier(TWANIdentifier twanIdentifier);
		
	TWANTimeStamp getTWANTimeStamp();
	
	void setTWANTimeStamp(TWANTimeStamp twanTimeStamp);
	
	OverloadControlInformation getSGSNOverloadControlInformation();
	
	void setSGSNOverloadControlInformation(OverloadControlInformation overloadControlInformation);
		
	OverloadControlInformation getSGWOverloadControlInformation();
	
	void setSGWOverloadControlInformation(OverloadControlInformation overloadControlInformation);
		
	OverloadControlInformation getPDGOverloadControlInformation();
	
	void setPDGOverloadControlInformation(OverloadControlInformation overloadControlInformation);
		
	TWANIdentifier getWLANLocationInfromation();
	
	void setWLANLocationInfromation(TWANIdentifier wlanLocationInfromation);
		
	TWANTimeStamp getWLANLocationTimestamp();
	
	void setWLANLocationTimestamp(TWANTimeStamp wlanLocationTimestamp);
		
	IPAddress getUELocalIPAddress();
	
	void setUELocalIPAddress(IPAddress ueLocalIPAddress);
		
	PortNumber getUEUDPPort();
	
	void setUEUDPPort(PortNumber ueUDPPort);
		
	ExtendedProtocolConfigurationOptions getExtendedProtocolConfigurationOptions();
	
	void setExtendedProtocolConfigurationOptions(ExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions);
	
	PortNumber getUETCPPort();
	
	void setUETCPPort(PortNumber ueUDPPort);
		
	SecondaryRatUsageDataReport getSecondaryRatUsageDataReport();
	
	void setSecondaryRatUsageDataReport(SecondaryRatUsageDataReport secondaryRatUsageDataReport);
		
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);
}