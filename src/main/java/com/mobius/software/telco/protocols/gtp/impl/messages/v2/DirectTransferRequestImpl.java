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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EUTRANRoundTripDelay;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.HRPDSectorID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.HandoverIndicator;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PDNGWPMIPGRETunnelInfo;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.S101TransparentContainer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.S103GRETunnelInfo;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.S103HSGWIPAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SessionID2;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UnauthenticatedIMSI;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.DirectTransferRequest;

public class DirectTransferRequestImpl extends AbstractGTP2Message implements DirectTransferRequest
{
	IMSI sessionID;
	SessionID2 sessionID2;
	HRPDSectorID hrpdSectorID;
	S101TransparentContainer s101TransparentContainer;
	PDNGWPMIPGRETunnelInfo pdnGWPMIPGRETunnelInfo;
	S103GRETunnelInfo s103GRETunnelInfo;
	S103HSGWIPAddress s103HSGWIPAddress;
	HandoverIndicator handoverIndicator;
	EUTRANRoundTripDelay eutranRoundTripDelay;
	UnauthenticatedIMSI unauthenticatedIMSI;
	private Recovery recovery;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.DIRECT_TRANSFER_REQUEST;
	}

	@Override
	public void applyTLV(TLV2 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case IMSI:
				sessionID=(IMSI)tlv;
				break;
			case SESSION_ID_2:
				sessionID2=(SessionID2)tlv;
				break;
			case HRPD_SECTOR_ID:
				hrpdSectorID=(HRPDSectorID)tlv;
				break;
			case S101_TRANSPARENT_CONTAINER:
				s101TransparentContainer=(S101TransparentContainer)tlv;
				break;
			case PDN_GW_PMIP_GRE_TUNNEL_INFO:
				pdnGWPMIPGRETunnelInfo=(PDNGWPMIPGRETunnelInfo)tlv;
				break;
			case S103_GRE_TUNNEL_INFO:
				s103GRETunnelInfo=(S103GRETunnelInfo)tlv;
				break;
			case S103_HSGW_IP_ADDRESS:
				s103HSGWIPAddress=(S103HSGWIPAddress)tlv;
				break;
			case HANDOVER_INDICATOR:
				handoverIndicator=(HandoverIndicator)tlv;
				break;
			case EUTRAN_ROUND_TRIP_DELAY:
				eutranRoundTripDelay=(EUTRANRoundTripDelay)tlv;
				break;
			case UNATHENTICATION_IMSI:
				unauthenticatedIMSI=(UnauthenticatedIMSI)tlv;
				break;
			case RECOVERY:
				recovery=(Recovery)tlv;
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
		if(sessionID!=null)
			output.add(sessionID);
		
		if(sessionID2!=null)
			output.add(sessionID2);
		
		if(hrpdSectorID!=null)
			output.add(hrpdSectorID);
		
		if(s101TransparentContainer==null)
			throw new GTPParseException("S101 Transparent Container not set");
		
		output.add(s101TransparentContainer);
		
		if(pdnGWPMIPGRETunnelInfo!=null)
			output.add(pdnGWPMIPGRETunnelInfo);
		
		if(s103GRETunnelInfo!=null)
			output.add(s103GRETunnelInfo);
		
		if(s103HSGWIPAddress!=null)
			output.add(s103HSGWIPAddress);
		
		if(handoverIndicator!=null)
			output.add(handoverIndicator);
		
		if(eutranRoundTripDelay!=null)
			output.add(eutranRoundTripDelay);
		
		if(unauthenticatedIMSI!=null)
			output.add(unauthenticatedIMSI);
		
		if(recovery!=null)
			output.add(recovery);
		
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
	public Recovery getRecovery() 
	{
		return recovery;
	}

	@Override
	public void setRecovery(Recovery recovery) 
	{
		this.recovery=recovery;
		this.recovery.setInstance(0);
	}

	@Override
	public IMSI getSessionID() 
	{
		return this.sessionID;
	}

	@Override
	public void setSessionID(IMSI imsi) 
	{
		imsi.setInstance(0);
		this.sessionID=imsi;
	}

	@Override
	public SessionID2 getSessionID2() 
	{
		return this.sessionID2;
	}

	@Override
	public void setSessionID2(SessionID2 sessionID2) 
	{
		sessionID2.setInstance(0);
		this.sessionID2=sessionID2;
	}

	@Override
	public HRPDSectorID getHRPDSectorID() 
	{
		return this.hrpdSectorID;
	}

	@Override
	public void setHRPDSectorID(HRPDSectorID hrpdSectorID) 
	{
		hrpdSectorID.setInstance(0);
		this.hrpdSectorID=hrpdSectorID;
	}

	@Override
	public S101TransparentContainer getS101TransparentContainer() 
	{
		return this.s101TransparentContainer;
	}

	@Override
	public void setS101TransparentContainer(S101TransparentContainer s101Container) 
	{
		s101Container.setInstance(0);
		this.s101TransparentContainer=s101Container;
	}

	@Override
	public PDNGWPMIPGRETunnelInfo getPDNGWPMIPGRETunnelInfo() 
	{
		return this.pdnGWPMIPGRETunnelInfo;
	}

	@Override
	public void setPDNGWPMIPGRETunnelInfo(PDNGWPMIPGRETunnelInfo tunnelInfo) 
	{
		tunnelInfo.setInstance(0);
		this.pdnGWPMIPGRETunnelInfo=tunnelInfo;
	}

	@Override
	public S103GRETunnelInfo getS103GRETunnelInfo() 
	{
		return this.s103GRETunnelInfo;
	}

	@Override
	public void setS103GRETunnelInfo(S103GRETunnelInfo tunnelInfo) 
	{
		tunnelInfo.setInstance(0);
		this.s103GRETunnelInfo=tunnelInfo;
	}

	@Override
	public S103HSGWIPAddress getS103HSGWIPAddress() 
	{
		return this.s103HSGWIPAddress;
	}

	@Override
	public void setS103HSGWIPAddress(S103HSGWIPAddress ipAddress) 
	{
		ipAddress.setInstance(0);
		this.s103HSGWIPAddress=ipAddress;
	}

	@Override
	public HandoverIndicator getHandoverIndicator() 
	{
		return this.handoverIndicator;
	}

	@Override
	public void setHandoverIndicator(HandoverIndicator handoverIndicator) 
	{
		handoverIndicator.setInstance(0);
		this.handoverIndicator=handoverIndicator;
	}

	@Override
	public EUTRANRoundTripDelay getEUTRANRoundTripDelay() 
	{
		return this.eutranRoundTripDelay;
	}

	@Override
	public void setEUTRANRoundTripDelay(EUTRANRoundTripDelay roundTripDelay) 
	{
		roundTripDelay.setInstance(0);
		this.eutranRoundTripDelay=roundTripDelay;
	}

	@Override
	public UnauthenticatedIMSI getUnauthenticatedIMSI() 
	{
		return this.unauthenticatedIMSI;
	}

	@Override
	public void setUnauthenticatedIMSI(UnauthenticatedIMSI unauthenticatedIMSI) 
	{
		unauthenticatedIMSI.setInstance(0);
		this.unauthenticatedIMSI=unauthenticatedIMSI;
	}
}