package com.mobius.software.telco.protocols.gtp.impl.messages.v2;
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
import java.util.ArrayList;
import java.util.List;

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CSGInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Counter;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPSBearerID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IPAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Indication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MEI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PresenceAreaReportingAction;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RatType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SecondaryRatUsageDataReport;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UserLocationInformation;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.ChangeNotificationRequest;

public class ChangeNotificationRequestImpl extends AbstractGTP2Message implements ChangeNotificationRequest
{
	IMSI imsi;
	MEI mei;
	Indication indication;
	RatType ratType;
	UserLocationInformation userLocationInformation;
	CSGInformation userCSGInformation;
	IPAddress pgwIPAddress;
	EPSBearerID lbi;
	Counter moExceptionDataCounter;
	SecondaryRatUsageDataReport secondaryRatUsageDataReport;
	PresenceAreaReportingAction praAction;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.CHANGE_NOTIFICATION_REQUEST;
	}

	@Override
	public void applyTLV(TLV2 tlv) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case IMSI:
				imsi=(IMSI)tlv;
				break;
			case MEI:
				mei=(MEI)tlv;
				break;
			case USER_LOCATION_INFORMATION:
				switch(tlv.getInstance())
				{
					case 0:
						userLocationInformation=(UserLocationInformation)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case RAT_TYPE:
				ratType=(RatType)tlv;
				break;
			case INDICATION:
				indication=(Indication)tlv;
				break;
			case EPS_BEARER_ID:
				lbi=(EPSBearerID)tlv;
				break;
			case PRESENCE_REPORTING_AREA_ACTION:
				praAction=(PresenceAreaReportingAction)tlv;
				break;
			case USER_CSG_INFORMATION:
				userCSGInformation=(CSGInformation)tlv;
				break;
			case IP_ADDRESS:
				switch(tlv.getInstance())
				{
					case 0:
						pgwIPAddress=(IPAddress)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case COUNTER:
				moExceptionDataCounter=(Counter)tlv;
				break;
			case SECONDARY_RAT_DATA_USAGE_REPORT:
				secondaryRatUsageDataReport=(SecondaryRatUsageDataReport)tlv;
				break;
			case PRIVATE_EXTENTION:
				if(privateExtentions==null)
					privateExtentions=new ArrayList<PrivateExtention>();
				
				privateExtentions.add((PrivateExtention)tlv);
				break;
			default:
				throw new GTPParseException("Unknown TLV received,type:" + tlv.getElementType());
		}
	}

	@Override
	public List<TLV2> getTLVs() throws GTPParseException 
	{
		ArrayList<TLV2> output=new ArrayList<TLV2>();
		if(imsi!=null)
			output.add(imsi);
		
		if(mei!=null)
			output.add(mei);
		
		if(indication!=null)
			output.add(indication);
		
		if(ratType==null)
			throw new GTPParseException("Rat type not set");
		
		output.add(ratType);
		
		if(userLocationInformation!=null)
			output.add(userLocationInformation);
		
		if(userCSGInformation!=null)
			output.add(userCSGInformation);
			
		if(pgwIPAddress!=null)
			output.add(pgwIPAddress);
		
		if(lbi!=null)
			output.add(lbi);
			
		if(praAction!=null)
			output.add(praAction);
		
		if(moExceptionDataCounter!=null)
			output.add(moExceptionDataCounter);
		
		if(secondaryRatUsageDataReport!=null)
			output.add(secondaryRatUsageDataReport);
		
		if(privateExtentions!=null)
		{
			for(PrivateExtention curr:privateExtentions)
				output.add(curr);
		}
		
		return output;
	}

	@Override
	public List<PrivateExtention> getPrivateExtentions() 
	{
		return privateExtentions;
	}

	@Override
	public void setPrivateExtentions(List<PrivateExtention> privateExtention) 
	{
		this.privateExtentions=privateExtention;
	}

	@Override
	public IMSI getIMSI() 
	{
		return imsi;
	}

	@Override
	public void setIMSI(IMSI imsi) 
	{
		imsi.setInstance(0);
		this.imsi=imsi;
	}

	@Override
	public MEI getMEI() 
	{
		return mei;
	}

	@Override
	public void setMEI(MEI mei) 
	{
		mei.setInstance(0);
		this.mei=mei;
	}

	@Override
	public UserLocationInformation getUserLocationInformation() 
	{
		return userLocationInformation;
	}

	@Override
	public void setUserLocationInformation(UserLocationInformation uli) 
	{
		uli.setInstance(0);
		this.userLocationInformation=uli;
	}

	@Override
	public RatType getRatType() 
	{
		return ratType;
	}

	@Override
	public void setRatType(RatType ratType) 
	{
		ratType.setInstance(0);
		this.ratType=ratType;
	}

	@Override
	public Indication getIndication() 
	{
		return this.indication;
	}

	@Override
	public void setIndication(Indication indication) 
	{
		indication.setInstance(0);
		this.indication=indication;
	}

	@Override
	public EPSBearerID getLBI() 
	{
		return lbi;
	}

	@Override
	public void setLBI(EPSBearerID lbi) 
	{
		lbi.setInstance(0);
		this.lbi=lbi;
	}

	@Override
	public CSGInformation getUserCSGInformation() 
	{
		return userCSGInformation;
	}

	@Override
	public void setUserCSGInformation(CSGInformation csgInformation) 
	{
		csgInformation.setInstance(0);
		this.userCSGInformation=csgInformation;
	}

	@Override
	public IPAddress getPGWIPAddress() 
	{
		return pgwIPAddress;
	}

	@Override
	public void setPGWIPAddress(IPAddress pgwIPAddress) 
	{
		pgwIPAddress.setInstance(0);
		this.pgwIPAddress=pgwIPAddress;
	}

	@Override
	public Counter getMOExceptionDataCounter() 
	{
		return moExceptionDataCounter;
	}

	@Override
	public void setMOExceptionDataCounter(Counter counter) 
	{
		counter.setInstance(0);
		this.moExceptionDataCounter=counter;
	}

	@Override
	public SecondaryRatUsageDataReport getSecondaryRatUsageDataReport() 
	{
		return secondaryRatUsageDataReport;
	}

	@Override
	public void setSecondaryRatUsageDataReport(SecondaryRatUsageDataReport secondaryRatUsageDataReport) 
	{
		secondaryRatUsageDataReport.setInstance(0);
		this.secondaryRatUsageDataReport=secondaryRatUsageDataReport;
	}

	@Override
	public PresenceAreaReportingAction getPresenceAreaReportingAction() 
	{
		return praAction;
	}

	@Override
	public void setPresenceAreaReportingAction(PresenceAreaReportingAction action) 
	{
		action.setInstance(0);
		this.praAction=action;
	}
}