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

import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.ModifyBearerRequestBearerContextToBeModified;
import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.ModifyBearerRequestBearerContextToBeRemoved;
import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.AMBR;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CNOperatorSelectionEntity;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CSGInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Counter;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.DelayValue;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FQCSID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IPAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Indication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.LDN;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MEI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.OverloadControlInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PRAInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PortNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RatType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SecondaryRatUsageDataReport;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ServingNetwork;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ServingPLMNRateControl;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TWANIdentifier;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TWANTimeStamp;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UETimezone;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UserLocationInformation;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.ModifyBearerRequest;

public class ModifyBearerRequestImpl extends AbstractGTP2Message implements ModifyBearerRequest
{
	MEI mei;
	UserLocationInformation userLocationInformation;
	ServingNetwork servingNetwork;
	RatType ratType;
	Indication indication;
	FTEID senderFTEIDControlPlane;
	AMBR apnAMBR;
	DelayValue delayValue;
	List<ModifyBearerRequestBearerContextToBeModified> bearerContextToBeModified;
	List<ModifyBearerRequestBearerContextToBeRemoved> bearerContextToBeRemoved;
	Recovery recovery;
	UETimezone ueTimezone;
	FQCSID mmeFQCSID;
	FQCSID sgwFQCSID;
	CSGInformation userCSGInformation;
	IPAddress ueLocalIPAddress;
	PortNumber ueUDPPort;
	LDN sgsnLDN;
	LDN sgwLDN;
	IPAddress nbLocalIPAddress;
	PortNumber nbUDPPort;
	IPAddress sgsnIdentifier;
	CNOperatorSelectionEntity cnOperatorSelectionEntity;
	PRAInformation praInformation;
	OverloadControlInformation sgsnOverloadControlInformation;
	OverloadControlInformation sgwOverloadControlInformation;
	OverloadControlInformation pdgOverloadControlInformation;
	ServingPLMNRateControl servingPLMNRateControl;
	Counter moExceptionDataCounter;
	IMSI imsi;
	UserLocationInformation userLocationInformationforSGW;
	TWANIdentifier wlanLocationInfromation;
	TWANTimeStamp wlanLocationTimestamp;
	SecondaryRatUsageDataReport secondaryRatUsageDataReport;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.MODIFY_BEARER_REQUEST;
	}

	@Override
	public void applyTLV(TLV2 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case IMSI:
				imsi=(IMSI)tlv;
				break;
			case DELAY_VALUE:
				delayValue=(DelayValue)tlv;
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
					case 1:
						userLocationInformationforSGW=(UserLocationInformation)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case SERVING_NETWORK:
				servingNetwork=(ServingNetwork)tlv;
				break;
			case RAT_TYPE:
				ratType=(RatType)tlv;
				break;
			case INDICATION:
				indication=(Indication)tlv;
				break;
			case FTEID:
				switch(tlv.getInstance())
				{
					case 0:
						senderFTEIDControlPlane=(FTEID)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case AMBR:
				apnAMBR=(AMBR)tlv;
				break;
			case BEARER_CONTEXT:
				switch(tlv.getInstance())
				{
					case 0:
						if(bearerContextToBeModified==null)
							bearerContextToBeModified=new ArrayList<ModifyBearerRequestBearerContextToBeModified>();
						
						bearerContextToBeModified.add((ModifyBearerRequestBearerContextToBeModified)tlv);
						break;
					case 1:
						if(bearerContextToBeRemoved==null)
							bearerContextToBeRemoved=new ArrayList<ModifyBearerRequestBearerContextToBeRemoved>();
						
						bearerContextToBeRemoved.add((ModifyBearerRequestBearerContextToBeRemoved)tlv);
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case RECOVERY:
				recovery=(Recovery)tlv;
				break;
			case FQ_CSID:
				switch(tlv.getInstance())
				{
					case 0:
						mmeFQCSID=(FQCSID)tlv;
						break;
					case 1:
						sgwFQCSID=(FQCSID)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case UE_TIMEZONE:
				ueTimezone=(UETimezone)tlv;
				break;
			case USER_CSG_INFORMATION:
				userCSGInformation=(CSGInformation)tlv;
				break;
			case LDN:
				switch(tlv.getInstance())
				{
					case 0:
						sgsnLDN=(LDN)tlv;
						break;
					case 1:
						sgwLDN=(LDN)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case IP_ADDRESS:
				switch(tlv.getInstance())
				{
					case 1:
						ueLocalIPAddress=(IPAddress)tlv;
						break;
					case 0:
						nbLocalIPAddress=(IPAddress)tlv;
						break;
					case 2:
						sgsnIdentifier=(IPAddress)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case PORT_NUMBER:
				switch(tlv.getInstance())
				{
					case 1:
						ueUDPPort=(PortNumber)tlv;
						break;
					case 0:
						nbUDPPort=(PortNumber)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case TWAN_IDENTIFIER:
				switch(tlv.getInstance())
				{
					case 0:
						wlanLocationInfromation=(TWANIdentifier)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case CN_OPERATOR_SELECTION_ENTITY:
				cnOperatorSelectionEntity=(CNOperatorSelectionEntity)tlv;
				break;
			case PRESENCE_REPORTING_AREA_INFORMATION:
				praInformation=(PRAInformation)tlv;
				break;
			case OVERLOAD_CONTROL_INFORMATION:
				switch(tlv.getInstance())
				{
					case 0:
						sgsnOverloadControlInformation=(OverloadControlInformation)tlv;
						break;
					case 1:
						sgwOverloadControlInformation=(OverloadControlInformation)tlv;
						break;
					case 2:
						pdgOverloadControlInformation=(OverloadControlInformation)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case TWAN_IDENTIFIER_TIMESTAMP:
				wlanLocationTimestamp=(TWANTimeStamp)tlv;
				break;
			case SERVING_PLMN_RATE_CONTROL:
				servingPLMNRateControl=(ServingPLMNRateControl)tlv;
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
				if(ignoreUnknown==null || !ignoreUnknown)
					throw new GTPParseException("Unknown TLV received,type:" + tlv.getElementType());
		}
	}

	@Override
	public List<TLV2> getTLVs() throws GTPParseException 
	{
		ArrayList<TLV2> output=new ArrayList<TLV2>();
		if(mei!=null)
			output.add(mei);
		
		if(userLocationInformation!=null)
			output.add(userLocationInformation);
		
		if(servingNetwork!=null)
			output.add(servingNetwork);
		
		if(ratType!=null)
			output.add(ratType);
		
		if(indication!=null)
			output.add(indication);
		
		if(senderFTEIDControlPlane==null)
			throw new GTPParseException("sender FTEID CP not set");
		
		output.add(senderFTEIDControlPlane);
		
		if(apnAMBR!=null)
			output.add(apnAMBR);
		
		if(delayValue!=null)
			output.add(delayValue);
			
		if(bearerContextToBeModified==null || bearerContextToBeModified.size()==0)
			throw new GTPParseException("Bearer Context to be modified not set");
		
		output.addAll(bearerContextToBeModified);
		
		if(bearerContextToBeRemoved!=null && bearerContextToBeRemoved.size()>0)
			output.addAll(bearerContextToBeRemoved);
		
		if(recovery!=null)
			output.add(recovery);
		
		if(ueTimezone!=null)
			output.add(ueTimezone);
			
		if(mmeFQCSID!=null)
			output.add(mmeFQCSID);
		
		if(sgwFQCSID!=null)
			output.add(sgwFQCSID);
		
		if(userCSGInformation!=null)
			output.add(userCSGInformation);
			
		if(ueLocalIPAddress!=null)
			output.add(ueLocalIPAddress);
		
		if(ueUDPPort!=null)
			output.add(ueUDPPort);
		
		if(sgsnLDN!=null)
			output.add(sgsnLDN);
			
		if(sgwLDN!=null)
			output.add(sgwLDN);
			
		if(nbLocalIPAddress!=null)
			output.add(nbLocalIPAddress);
		
		if(nbUDPPort!=null)
			output.add(nbUDPPort);
		
		if(sgsnIdentifier!=null)
			output.add(sgsnIdentifier);
		
		if(cnOperatorSelectionEntity!=null)
			output.add(cnOperatorSelectionEntity);
		
		if(praInformation!=null)
			output.add(praInformation);
		
		if(sgsnOverloadControlInformation!=null)
			output.add(sgsnOverloadControlInformation);
		
		if(sgwOverloadControlInformation!=null)
			output.add(sgwOverloadControlInformation);
		
		if(pdgOverloadControlInformation!=null)
			output.add(pdgOverloadControlInformation);
		
		if(servingPLMNRateControl!=null)
			output.add(servingPLMNRateControl);
		
		if(moExceptionDataCounter!=null)
			output.add(moExceptionDataCounter);
				
		if(imsi!=null)
			output.add(imsi);
		
		if(userLocationInformationforSGW!=null)
			output.add(userLocationInformationforSGW);
		
		if(wlanLocationInfromation!=null)
			output.add(wlanLocationInfromation);
		
		if(wlanLocationTimestamp!=null)
			output.add(wlanLocationTimestamp);
		
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
	public ServingNetwork getServingNetwork() 
	{
		return servingNetwork;
	}

	@Override
	public void setServingNetwork(ServingNetwork servingNetwork) 
	{
		servingNetwork.setInstance(0);
		this.servingNetwork=servingNetwork;
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
	public FTEID getSenderFTEIDControlPlane() 
	{
		return this.senderFTEIDControlPlane;
	}

	@Override
	public void setSenderFTEIDControlPlane(FTEID fteid) 
	{
		fteid.setInstance(0);
		this.senderFTEIDControlPlane=fteid;
	}

	@Override
	public AMBR getAPNAMBR() 
	{
		return apnAMBR;
	}

	@Override
	public void setAPNAMBR(AMBR ambr) 
	{
		ambr.setInstance(0);
		this.apnAMBR=ambr;
	}

	@Override
	public List<ModifyBearerRequestBearerContextToBeModified> getBearerContextToBeModified() 
	{
		return bearerContextToBeModified;
	}

	@Override
	public void setBearerContextToBeModified(List<ModifyBearerRequestBearerContextToBeModified> bearerContext) 
	{
		if(bearerContext!=null)
			for(ModifyBearerRequestBearerContextToBeModified curr:bearerContext)
				curr.setInstance(0);
		
		this.bearerContextToBeModified=bearerContext;
	}

	@Override
	public List<ModifyBearerRequestBearerContextToBeRemoved> getBearerContextToBeRemoved() 
	{
		return bearerContextToBeRemoved;
	}

	@Override
	public void setBearerContextToBeRemoved(List<ModifyBearerRequestBearerContextToBeRemoved> bearerContext) 
	{
		if(bearerContext!=null)
			for(ModifyBearerRequestBearerContextToBeRemoved curr:bearerContext)
				curr.setInstance(0);
		
		this.bearerContextToBeRemoved=bearerContext;
	}

	@Override
	public Recovery getRecovery() 
	{
		return recovery;
	}

	@Override
	public void setRecovery(Recovery recovery) 
	{
		recovery.setInstance(0);
		this.recovery=recovery;
	}

	@Override
	public FQCSID getMMEFQCSID() 
	{
		return mmeFQCSID;
	}

	@Override
	public void setMMEFQCSID(FQCSID fqcsid) 
	{
		fqcsid.setInstance(0);
		this.mmeFQCSID=fqcsid;
	}

	@Override
	public FQCSID getSGWFQCSID() 
	{
		return sgwFQCSID;
	}

	@Override
	public void setSGWFQCSID(FQCSID fqcsid) 
	{
		fqcsid.setInstance(1);
		this.sgwFQCSID=fqcsid;
	}

	@Override
	public UETimezone getUETimezone() 
	{
		return ueTimezone;
	}

	@Override
	public void setUETimezone(UETimezone ueTimezone) 
	{
		ueTimezone.setInstance(0);
		this.ueTimezone=ueTimezone;
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
	public LDN getSGSNLDN() 
	{
		return sgsnLDN;
	}

	@Override
	public void setSGSNLDN(LDN ldn) 
	{
		ldn.setInstance(0);
		this.sgsnLDN=ldn;
	}

	@Override
	public LDN getSGWLDN() 
	{
		return sgwLDN;
	}

	@Override
	public void setSGWLDN(LDN ldn) 
	{
		ldn.setInstance(1);
		this.sgwLDN=ldn;
	}

	@Override
	public IPAddress getUELocalIPAddress() 
	{
		return ueLocalIPAddress;
	}

	@Override
	public void setUELocalIPAddress(IPAddress ueLocalIPAddress) 
	{
		ueLocalIPAddress.setInstance(1);
		this.ueLocalIPAddress=ueLocalIPAddress;
	}

	@Override
	public PortNumber getUEUDPPort() 
	{
		return ueUDPPort;
	}

	@Override
	public void setUEUDPPort(PortNumber ueUDPPort) 
	{
		ueUDPPort.setInstance(1);
		this.ueUDPPort=ueUDPPort;
	}

	@Override
	public IPAddress getNBLocalIPAddress() 
	{
		return nbLocalIPAddress;
	}

	@Override
	public void setNBLocalIPAddress(IPAddress nbLocalIPAddress) 
	{
		nbLocalIPAddress.setInstance(0);
		this.nbLocalIPAddress=nbLocalIPAddress;
	}

	@Override
	public PortNumber getNBUDPPort() 
	{
		return this.nbUDPPort;
	}

	@Override
	public void setNBUDPPort(PortNumber nbUDPPort) 
	{
		nbUDPPort.setInstance(0);
		this.nbUDPPort=nbUDPPort;
	}

	@Override
	public IPAddress getSGSNIdentifier() 
	{
		return sgsnIdentifier;
	}

	@Override
	public void setSGSNIdentifier(IPAddress sgsnIdentifier) 
	{
		sgsnIdentifier.setInstance(2);
		this.sgsnIdentifier=sgsnIdentifier;
	}

	@Override
	public CNOperatorSelectionEntity getCNOperatorSelectionEntity() 
	{
		return cnOperatorSelectionEntity;
	}

	@Override
	public void setCNOperatorSelectionEntity(CNOperatorSelectionEntity cnOperatorSelectionEntity) 
	{
		cnOperatorSelectionEntity.setInstance(0);
		this.cnOperatorSelectionEntity=cnOperatorSelectionEntity;
	}

	@Override
	public PRAInformation getPRAInformation() 
	{
		return praInformation;
	}

	@Override
	public void setPRAInformation(PRAInformation praInformation) 
	{
		praInformation.setInstance(0);
		this.praInformation=praInformation;
	}

	@Override
	public OverloadControlInformation getSGSNOverloadControlInformation() 
	{
		return sgsnOverloadControlInformation;
	}

	@Override
	public void setSGSNOverloadControlInformation(OverloadControlInformation overloadControlInformation) 
	{
		overloadControlInformation.setInstance(0);
		this.sgsnOverloadControlInformation=overloadControlInformation;
	}

	@Override
	public OverloadControlInformation getSGWOverloadControlInformation() 
	{
		return sgwOverloadControlInformation;
	}

	@Override
	public void setSGWOverloadControlInformation(OverloadControlInformation overloadControlInformation) 
	{
		overloadControlInformation.setInstance(1);
		this.sgwOverloadControlInformation=overloadControlInformation;
	}

	@Override
	public OverloadControlInformation getPDGOverloadControlInformation() 
	{
		return pdgOverloadControlInformation;
	}

	@Override
	public void setPDGOverloadControlInformation(OverloadControlInformation overloadControlInformation) 
	{
		overloadControlInformation.setInstance(2);
		this.pdgOverloadControlInformation=overloadControlInformation;
	}

	@Override
	public TWANIdentifier getWLANLocationInfromation() 
	{
		return wlanLocationInfromation;
	}

	@Override
	public void setWLANLocationInfromation(TWANIdentifier wlanLocationInfromation) 
	{
		wlanLocationInfromation.setInstance(1);
		this.wlanLocationInfromation=wlanLocationInfromation;
	}

	@Override
	public TWANTimeStamp getWLANLocationTimestamp() 
	{
		return wlanLocationTimestamp;
	}

	@Override
	public void setWLANLocationTimestamp(TWANTimeStamp wlanLocationTimestamp) 
	{
		wlanLocationTimestamp.setInstance(0);
		this.wlanLocationTimestamp=wlanLocationTimestamp;
	}

	@Override
	public ServingPLMNRateControl getServingPLMNRateControl() 
	{
		return servingPLMNRateControl;
	}

	@Override
	public void setServingPLMNRateControl(ServingPLMNRateControl servingPLMNRateControl) 
	{
		servingPLMNRateControl.setInstance(0);
		this.servingPLMNRateControl=servingPLMNRateControl;
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
	public UserLocationInformation getUserLocationInformationforSGW() 
	{
		return userLocationInformationforSGW;
	}

	@Override
	public void setUserLocationInformationforSGW(UserLocationInformation uli) 
	{
		uli.setInstance(1);
		this.userLocationInformationforSGW=uli;
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
	public DelayValue getDelayValue() 
	{
		return this.delayValue;
	}

	@Override
	public void setDelayValue(DelayValue delayValue) 
	{
		delayValue.setInstance(0);
		this.delayValue=delayValue;
	}
}