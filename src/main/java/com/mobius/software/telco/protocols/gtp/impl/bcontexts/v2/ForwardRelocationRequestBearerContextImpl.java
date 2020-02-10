package com.mobius.software.telco.protocols.gtp.impl.bcontexts.v2;
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

import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.ForwardRelocationRequestBearerContext;
import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.BearerFlags;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.BearerQos;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPSBearerID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FContainer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TFT;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TransactionIdentifier;
import com.mobius.software.telco.protocols.gtp.impl.headers.v2.BearerContextImpl;

public class ForwardRelocationRequestBearerContextImpl extends BearerContextImpl implements ForwardRelocationRequestBearerContext
{
	private EPSBearerID bearerID;
	private TFT tft;
	private FTEID sgwFTEID;
	private FTEID s5S8PGWFTEID;
	private BearerQos bearerQos;
	private FContainer bssContainer;
	private TransactionIdentifier transactionIdentifier;
	private BearerFlags bearerFlags;
	private FTEID s11SGWFTEID;
	
	@Override
	public void applyTLV(TLV2 tlv) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case EPS_BEARER_ID:
				bearerID=(EPSBearerID)tlv;
				break;
			case BEARER_TFT:
				tft=(TFT)tlv;
				break;
			case FTEID:
				switch(tlv.getInstance())
				{
					case 0:
						sgwFTEID=(FTEID)tlv;
						break;
					case 1:
						s5S8PGWFTEID=(FTEID)tlv;
						break;
					case 2:
						s11SGWFTEID=(FTEID)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}	
				break;
			case BEARER_QOS:
				bearerQos=(BearerQos)tlv;
				break;
			case FCONTAINER:
				bssContainer=(FContainer)tlv;
				break;
			case TRANSACTION_IDENTIFIER:
				transactionIdentifier=(TransactionIdentifier)tlv;
				break;
			case BEARER_FLAGS:
				bearerFlags=(BearerFlags)tlv;
				break;
			default:
				throw new GTPParseException("Unknown TLV received,type:" + tlv.getElementType());
		}
	}

	@Override
	public List<TLV2> getTLVs() throws GTPParseException 
	{
		ArrayList<TLV2> output=new ArrayList<TLV2>();
		if(bearerID==null)
			throw new GTPParseException("Bearer ID not set");
		
		output.add(bearerID);
		
		if(tft!=null)
			output.add(tft);
		
		if(sgwFTEID==null)
			throw new GTPParseException("SGW FTEID not set");
		
		output.add(sgwFTEID);
		
		if(s5S8PGWFTEID!=null)
			output.add(s5S8PGWFTEID);
		
		if(bearerQos==null)
			throw new GTPParseException("Bearer QOS not set");
		
		output.add(bearerQos);
		
		if(bssContainer!=null)
			output.add(bssContainer);
		
		if(transactionIdentifier!=null)
			output.add(transactionIdentifier);
		
		if(bearerFlags!=null)
			output.add(bearerFlags);
		
		if(s11SGWFTEID!=null)
			output.add(s11SGWFTEID);
		
		return output;
	}
	
	@Override
	public EPSBearerID getEPSBearerID() 
	{
		return bearerID;
	}

	@Override
	public void setEPSBearerID(EPSBearerID bearerID) 
	{
		bearerID.setInstance(0);
		this.bearerID=bearerID;
	}

	@Override
	public TFT getTFT() 
	{
		return tft;
	}

	@Override
	public void setTFT(TFT tft) 
	{
		tft.setInstance(0);
		this.tft=tft;
	}

	@Override
	public FTEID getSGWFTEID() 
	{
		return sgwFTEID;
	}

	@Override
	public void setSGWFTEID(FTEID fteid) 
	{
		fteid.setInstance(0);
		this.sgwFTEID=fteid;
	}

	@Override
	public FTEID getS5S8PGWFTEID() 
	{
		return s5S8PGWFTEID;
	}

	@Override
	public void setS5S8PGWFTEID(FTEID fteid) 
	{
		fteid.setInstance(1);
		this.s5S8PGWFTEID=fteid;
	}
	
	@Override
	public BearerQos getBearerQos() 
	{
		return bearerQos;
	}

	@Override
	public void setBearerQos(BearerQos bearerQos) 
	{
		bearerQos.setInstance(0);
		this.bearerQos=bearerQos;
	}

	@Override
	public FContainer getBSSContainer() 
	{
		return bssContainer;
	}

	@Override
	public void setBSSContainer(FContainer bssContainer) 
	{
		bssContainer.setInstance(0);
		this.bssContainer=bssContainer;
	}

	@Override
	public TransactionIdentifier getTransactionIdentifier() 
	{
		return transactionIdentifier;
	}

	@Override
	public void setTransactionIdentifier(TransactionIdentifier transactionIdentifier) 
	{
		transactionIdentifier.setInstance(0);
		this.transactionIdentifier=transactionIdentifier;
	}

	@Override
	public BearerFlags getBearerFlags() 
	{
		return bearerFlags;
	}

	@Override
	public void setBearerFlags(BearerFlags bearerFlags) 
	{
		bearerFlags.setInstance(0);
		this.bearerFlags=bearerFlags;
	}

	@Override
	public FTEID getS11SGWFTEID() 
	{
		return s11SGWFTEID;
	}

	@Override
	public void setS11SGWFTEID(FTEID fteid) 
	{
		fteid.setInstance(2);
		this.s11SGWFTEID=fteid;
	}
}