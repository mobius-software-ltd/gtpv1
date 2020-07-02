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

import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.UpdateBearerRequestBearerContextToBeModified;
import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.AMBR;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CSGInformationReportingAction;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ChangeReportingAction;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FContainer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FQCSID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.HENBInformationReporting;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Indication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.LoadControlInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.OverloadControlInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PresenceAreaReportingAction;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ProcedureTransactionID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ProtocolConfigurationOption;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.UpdateBearerRequest;

public class UpdateBearerRequestImpl extends AbstractGTP2Message implements UpdateBearerRequest
{
	private List<UpdateBearerRequestBearerContextToBeModified> bearerContext;
	private ProcedureTransactionID procedureTransactionID;
	private ProtocolConfigurationOption protocolConfigurationOption;
	private AMBR apnAMBR;
	private ChangeReportingAction changeReportingAction;
	private CSGInformationReportingAction csgInformationReportingAction;
	private HENBInformationReporting heNBInformationReporting;
	private PresenceAreaReportingAction presenceAreaReportingAction;
	private Indication indication;
	private FQCSID pgwFQCSID;
	private FQCSID sgwFQCSID;
	private LoadControlInformation pgwNodeLoadControlInformation;
	private LoadControlInformation pgwAPNLoadControlInformation;
	private LoadControlInformation sgwNodeLoadControlInformation;
	private OverloadControlInformation pgwOverloadControlInformation;
	private OverloadControlInformation sgwOverloadControlInformation;
	private FContainer nbifomContainer;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.UPDATE_BEARER_REQUEST;
	}

	@Override
	public void applyTLV(TLV2 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case PROCEDURE_TRANSACTION_ID:
				procedureTransactionID=(ProcedureTransactionID)tlv;
				break;
			case CHANGE_REPORTING_ACTION:
				changeReportingAction=(ChangeReportingAction)tlv;
				break;
			case CSG_INFORMATION_REPORTING_ACTION:
				csgInformationReportingAction=(CSGInformationReportingAction)tlv;
				break;
			case HENB_INFORMATION_REPORTING:
				heNBInformationReporting=(HENBInformationReporting)tlv;
				break;
			case AMBR:
				apnAMBR=(AMBR)tlv;
				break;
			case PROTOCOL_CONFIGURATION_OPTIONS:
				protocolConfigurationOption=(ProtocolConfigurationOption)tlv;
				break;
			case BEARER_CONTEXT:
				switch(tlv.getInstance())
				{
					case 0:
						if(bearerContext==null)
							bearerContext=new ArrayList<UpdateBearerRequestBearerContextToBeModified>();
						
						bearerContext.add((UpdateBearerRequestBearerContextToBeModified)tlv);
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case FQ_CSID:
				switch(tlv.getInstance())
				{
					case 0:
						pgwFQCSID=(FQCSID)tlv;
						break;
					case 1:
						sgwFQCSID=(FQCSID)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case INDICATION:
				indication=(Indication)tlv;
				break;
			case PRESENCE_REPORTING_AREA_ACTION:
				presenceAreaReportingAction=(PresenceAreaReportingAction)tlv;
				break;
			case LOAD_CONTROL_INFORMATION:
				switch(tlv.getInstance())
				{
					case 0:
						pgwNodeLoadControlInformation=(LoadControlInformation)tlv;
						break;
					case 1:
						pgwAPNLoadControlInformation=(LoadControlInformation)tlv;
						break;
					case 2:
						sgwNodeLoadControlInformation=(LoadControlInformation)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case OVERLOAD_CONTROL_INFORMATION:
				switch(tlv.getInstance())
				{
					case 0:
						pgwOverloadControlInformation=(OverloadControlInformation)tlv;
						break;
					case 1:
						sgwOverloadControlInformation=(OverloadControlInformation)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case FCONTAINER:
				nbifomContainer=(FContainer)tlv;
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
		if(procedureTransactionID!=null)
			output.add(procedureTransactionID);
		
		if(apnAMBR==null)
			throw new GTPParseException("APN AMBR not set");
		
		output.add(apnAMBR);
			
		if(protocolConfigurationOption!=null)
			output.add(protocolConfigurationOption);
		
		if(bearerContext==null || bearerContext.size()==0)
			throw new GTPParseException("Bearer Context not set");
		
		output.addAll(bearerContext);
		
		if(pgwFQCSID!=null)
			output.add(pgwFQCSID);
		
		if(sgwFQCSID!=null)
			output.add(sgwFQCSID);
		
		if(changeReportingAction!=null)
			output.add(changeReportingAction);
		
		if(csgInformationReportingAction!=null)
			output.add(csgInformationReportingAction);
		
		if(heNBInformationReporting!=null)
			output.add(heNBInformationReporting);
		
		if(presenceAreaReportingAction!=null)
			output.add(presenceAreaReportingAction);
		
		if(indication!=null)
			output.add(indication);
		
		if(pgwNodeLoadControlInformation!=null)
			output.add(pgwNodeLoadControlInformation);
		
		if(pgwAPNLoadControlInformation!=null)
			output.add(pgwAPNLoadControlInformation);
		
		if(sgwNodeLoadControlInformation!=null)
			output.add(sgwNodeLoadControlInformation);
		
		if(pgwOverloadControlInformation!=null)
			output.add(pgwOverloadControlInformation);
		
		if(sgwOverloadControlInformation!=null)
			output.add(sgwOverloadControlInformation);
		
		if(nbifomContainer!=null)
			output.add(nbifomContainer);
		
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
	public ChangeReportingAction getChangeReportingAction() 
	{
		return changeReportingAction;
	}

	@Override
	public void setChangeReportingAction(ChangeReportingAction changeReportingAction) 
	{
		changeReportingAction.setInstance(0);
		this.changeReportingAction=changeReportingAction;
	}

	@Override
	public CSGInformationReportingAction getCSGInformationReportingAction() 
	{
		return csgInformationReportingAction;
	}

	@Override
	public void setCSGInformationReportingAction(CSGInformationReportingAction action) 
	{
		action.setInstance(0);
		this.csgInformationReportingAction=action;
	}

	@Override
	public HENBInformationReporting getHENBInformationReporting() 
	{
		return heNBInformationReporting;
	}

	@Override
	public void setHENBInformationReporting(HENBInformationReporting reporting) 
	{
		reporting.setInstance(0);
		this.heNBInformationReporting=reporting;
	}

	@Override
	public AMBR getAPNAMBR() 
	{
		return apnAMBR;
	}

	@Override
	public void setAPNAMBR(AMBR apnAMBR) 
	{
		apnAMBR.setInstance(0);
		this.apnAMBR=apnAMBR;
	}

	@Override
	public ProtocolConfigurationOption getProtocolConfigurationOption() 
	{
		return this.protocolConfigurationOption;
	}

	@Override
	public void setProtocolConfigurationOption(ProtocolConfigurationOption pco) 
	{
		pco.setInstance(0);
		this.protocolConfigurationOption=pco;
	}

	@Override
	public FQCSID getPGWFQCSID() 
	{
		return this.pgwFQCSID;
	}

	@Override
	public void setPGWFQCSID(FQCSID fqcsid) 
	{
		fqcsid.setInstance(0);
		this.pgwFQCSID=fqcsid;
	}

	@Override
	public FQCSID getSGWFQCSID() 
	{
		return this.sgwFQCSID;
	}

	@Override
	public void setSGWFQCSID(FQCSID fqcsid) 
	{
		fqcsid.setInstance(1);
		this.sgwFQCSID=fqcsid;
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
	public PresenceAreaReportingAction getPresenceAreaReportingAction() 
	{
		return this.presenceAreaReportingAction;
	}

	@Override
	public void setPresenceAreaReportingAction(PresenceAreaReportingAction action) 
	{
		action.setInstance(0);
		this.presenceAreaReportingAction=action;
	}

	@Override
	public LoadControlInformation getPGWNodeLoadControlInformation() 
	{		
		return this.pgwNodeLoadControlInformation;
	}

	@Override
	public void setPGWNodeLoadControlInformation(LoadControlInformation loadInformation) 
	{
		loadInformation.setInstance(0);
		this.pgwNodeLoadControlInformation=loadInformation;
	}

	@Override
	public LoadControlInformation getPGWAPNLoadControlInformation() 
	{
		return this.pgwAPNLoadControlInformation;
	}

	@Override
	public void setPGWAPNLoadControlInformation(LoadControlInformation loadInformation) 
	{
		loadInformation.setInstance(1);
		this.pgwAPNLoadControlInformation=loadInformation;
	}

	@Override
	public LoadControlInformation getSGWNodeLoadControlInformation() 
	{
		return this.sgwNodeLoadControlInformation;
	}

	@Override
	public void setSGWNodeLoadControlInformation(LoadControlInformation loadInformation) 
	{
		loadInformation.setInstance(2);
		this.sgwNodeLoadControlInformation=loadInformation;
	}

	@Override
	public OverloadControlInformation getPGWOverloadControlInformation() 
	{
		return this.pgwOverloadControlInformation;
	}

	@Override
	public void setPGWOverloadControlInformation(OverloadControlInformation overloadInformation) 
	{
		overloadInformation.setInstance(0);
		this.pgwOverloadControlInformation=overloadInformation;
	}

	@Override
	public OverloadControlInformation getSGWOverloadControlInformation() 
	{
		return sgwOverloadControlInformation;
	}

	@Override
	public void setSGWOverloadControlInformation(OverloadControlInformation overloadInformation) 
	{
		overloadInformation.setInstance(1);
		this.sgwOverloadControlInformation=overloadInformation;
	}

	@Override
	public FContainer getNBIFOMContainer() 
	{
		return this.nbifomContainer;
	}

	@Override
	public void setNBIFOMContainer(FContainer nbifomContainer) 
	{
		nbifomContainer.setInstance(0);
		this.nbifomContainer=nbifomContainer;
	}

	@Override
	public ProcedureTransactionID getProcedureTransactionID() 
	{
		return procedureTransactionID;
	}

	@Override
	public void setProcedureTransactionID(ProcedureTransactionID transactionID) 
	{
		transactionID.setInstance(0);
		this.procedureTransactionID=transactionID;
	}

	@Override
	public List<UpdateBearerRequestBearerContextToBeModified> getBearerContext() 
	{
		return bearerContext;
	}

	@Override
	public void setBearerContext(List<UpdateBearerRequestBearerContextToBeModified> bearerContext) 
	{
		if(bearerContext!=null)
			for(UpdateBearerRequestBearerContextToBeModified curr:bearerContext)
				curr.setInstance(0);
		
		this.bearerContext=bearerContext;
	}
}