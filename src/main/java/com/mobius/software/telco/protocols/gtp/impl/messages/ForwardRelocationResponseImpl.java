package com.mobius.software.telco.protocols.gtp.impl.messages;
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
import com.mobius.software.telco.protocols.gtp.api.headers.AdditionalRABSetupInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.BSSContainer;
import com.mobius.software.telco.protocols.gtp.api.headers.BSSGPCause;
import com.mobius.software.telco.protocols.gtp.api.headers.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.ExtendedRanapCause;
import com.mobius.software.telco.protocols.gtp.api.headers.GSNAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.ListOfSetupPFC;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.RABSetupInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.RanapCause;
import com.mobius.software.telco.protocols.gtp.api.headers.SGSNNumber;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifier2;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifierControlPane;
import com.mobius.software.telco.protocols.gtp.api.headers.UTRANTransparentContent;
import com.mobius.software.telco.protocols.gtp.api.messages.ForwardRelocationResponse;

public class ForwardRelocationResponseImpl extends AbstractGTPMessage implements ForwardRelocationResponse
{
	private Cause cause;
	private TunnerEndpointIdentifierControlPane controlTEI;
	private TunnerEndpointIdentifier2 tei2;
	private RanapCause ranapCause;
	private GSNAddress sgsnAddressForSignaling;
	private GSNAddress sgsnAddressForTraffic;
	private UTRANTransparentContent utranTransparentContent;
	private RABSetupInformation rabSetupInformation;
	private AdditionalRABSetupInformation additionalRABSetupInformation;
	private SGSNNumber sgsnNumber;
	private BSSContainer bssContainer;
	private BSSGPCause bssGPCause;
	private ListOfSetupPFC listOfSetupPFC;
	private ExtendedRanapCause extendedRanapCause;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public MessageType getMessageType() 
	{
		return MessageType.FORWARD_RELOCATION_RESPONSE;
	}

	@Override
	public void applyTLV(TLV1 tlv) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case CAUSE:
				cause=(Cause)tlv;
				break;
			case TEICP:
				controlTEI=(TunnerEndpointIdentifierControlPane)tlv;
				break;
			case TEID_2:
				tei2=(TunnerEndpointIdentifier2)tlv;
				break;
			case RANAP_CAUSE:
				ranapCause=(RanapCause)tlv;
				break;
			case GSN_ADDRESS:
				if(sgsnAddressForSignaling==null)
					sgsnAddressForSignaling=(GSNAddress)tlv;
				else if(sgsnAddressForTraffic==null)
					sgsnAddressForTraffic=(GSNAddress)tlv;
				else
					throw new GTPParseException("Unknown TLV received,type:" + tlv.getElementType());
				break;
			case UTRAN_TRANSPARENT_CONTAINER:
				utranTransparentContent=(UTRANTransparentContent)tlv;
				break;
			case RAB_SETUP_INFORMATION:
				rabSetupInformation=(RABSetupInformation)tlv;
				break;
			case ADDITIONAL_RAB_SETUP_INFORMATION:
				additionalRABSetupInformation=(AdditionalRABSetupInformation)tlv;
				break;
			case SGSN_NUMBER:
				sgsnNumber=(SGSNNumber)tlv;
				break;
			case BSS_CONTAINER:
				bssContainer=(BSSContainer)tlv;
				break;
			case BSSGP_CAUSE:
				bssGPCause=(BSSGPCause)tlv;
				break;
			case LIST_OF_SETUP_PFCs:
				listOfSetupPFC=(ListOfSetupPFC)tlv;
				break;
			case EXTENDED_RANAP_CAUSE:
				extendedRanapCause=(ExtendedRanapCause)tlv;
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
	public List<TLV1> getTLVs() throws GTPParseException 
	{
		ArrayList<TLV1> output=new ArrayList<TLV1>();
		
		if(cause==null)
			throw new GTPParseException("Cause is missing");
		
		output.add(cause);
	
		if(controlTEI!=null)
			output.add(controlTEI);
	
		if(tei2!=null)
			output.add(tei2);
	
		if(ranapCause!=null)
			output.add(ranapCause);
	
		if(sgsnAddressForSignaling!=null)
			output.add(sgsnAddressForSignaling);
	
		if(sgsnAddressForTraffic!=null)
			output.add(sgsnAddressForTraffic);
	
		if(utranTransparentContent!=null)
			output.add(utranTransparentContent);
	
		if(rabSetupInformation!=null)
			output.add(rabSetupInformation);
	
		if(additionalRABSetupInformation!=null)
			output.add(additionalRABSetupInformation);
	
		if(sgsnNumber!=null)
			output.add(sgsnNumber);
		
		if(bssContainer!=null)
			output.add(bssContainer);
	
		if(bssGPCause!=null)
			output.add(bssGPCause);
	
		if(listOfSetupPFC!=null)
			output.add(listOfSetupPFC);
	
		if(extendedRanapCause!=null)
			output.add(extendedRanapCause);	
		
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
	public Cause getCause() 
	{
		return cause;
	}

	@Override
	public void setCause(Cause cause) 
	{
		this.cause=cause;
	}

	@Override
	public TunnerEndpointIdentifierControlPane getControlPaneTEI() 
	{
		return this.controlTEI;
	}

	@Override
	public void setControlPageTEI(TunnerEndpointIdentifierControlPane tei) 
	{
		this.controlTEI=tei;
	}

	@Override
	public TunnerEndpointIdentifier2 getTunnerEndpointIdentifier2() 
	{
		return this.tei2;
	}

	@Override
	public void setTunnerEndpointIdentifier2(TunnerEndpointIdentifier2 tei2) 
	{
		this.tei2=tei2;
	}
	
	@Override
	public RanapCause getRanapCause() 
	{
		return this.ranapCause;
	}

	@Override
	public void setRanapCause(RanapCause ranapCause) 
	{
		this.ranapCause=ranapCause;
	}

	@Override
	public GSNAddress getSGSNAddressForSignaling() 
	{
		return sgsnAddressForSignaling;
	}

	@Override
	public void setSGSNAddressForSignaling(GSNAddress address) 
	{
		this.sgsnAddressForSignaling=address;		
	}

	@Override
	public GSNAddress getSGSNAddressForTraffic() 
	{
		return sgsnAddressForTraffic;
	}

	@Override
	public void setSGSNAddressForTraffic(GSNAddress address) 
	{
		this.sgsnAddressForTraffic=address;		
	}

	@Override
	public UTRANTransparentContent getUTRANTransparentContent() 
	{
		return utranTransparentContent;
	}

	@Override
	public void setUTRANTransparentContent(UTRANTransparentContent context) 
	{
		this.utranTransparentContent=context;
	}

	@Override
	public RABSetupInformation getRABSetupInformation() 
	{
		return rabSetupInformation;
	}

	@Override
	public void setRABSetupInformation(RABSetupInformation rabSetupInformation) 
	{
		this.rabSetupInformation=rabSetupInformation;
	}

	@Override
	public AdditionalRABSetupInformation getAdditionalRABSetupInformation() 
	{
		return this.additionalRABSetupInformation;
	}

	@Override
	public void setAdditionalRABSetupInformation(AdditionalRABSetupInformation additionalRABSetupInformation) 
	{
		this.additionalRABSetupInformation=additionalRABSetupInformation;		
	}

	@Override
	public SGSNNumber getSGSNNumber() 
	{
		return this.sgsnNumber;
	}

	@Override
	public void setSGSNNumber(SGSNNumber sgsnNumber) 
	{
		this.sgsnNumber=sgsnNumber;
	}

	@Override
	public BSSContainer getBSSContainer() 
	{
		return this.bssContainer;
	}

	@Override
	public void setBSSContainer(BSSContainer container) 
	{
		this.bssContainer=container;
	}

	@Override
	public BSSGPCause getBSSGPCause() 
	{
		return this.bssGPCause;
	}

	@Override
	public void setBSSGPCause(BSSGPCause bssGPCause) 
	{
		this.bssGPCause=bssGPCause;
	}

	@Override
	public ListOfSetupPFC getListOfSetupPFC() 
	{
		return this.listOfSetupPFC;
	}

	@Override
	public void setListOfSetupPFC(ListOfSetupPFC listOfSetupPFC) 
	{
		this.listOfSetupPFC=listOfSetupPFC;
	}

	@Override
	public ExtendedRanapCause getExtendedRanapCause() 
	{
		return this.extendedRanapCause;
	}

	@Override
	public void setExtendedRanapCause(ExtendedRanapCause extendedRanapCause) 
	{
		this.extendedRanapCause=extendedRanapCause;
	}
}