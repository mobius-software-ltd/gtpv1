package com.mobius.software.telco.protocols.gtp.impl.headers.v2;
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
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import io.netty.buffer.ByteBuf;

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.CivicAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.NetworkIdentity;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TWANIdentifier;
import com.mobius.software.telco.protocols.gtp.api.utils.StringFunctions;

public class TWANIdentifierImpl extends AbstractTLV2 implements TWANIdentifier
{
	public String ssid;
	public String bssid;
	public CivicAddress civicAddress;
	public NetworkIdentity plmnID;
	public String operatorName;
	Inet4Address v4Address; 
	Inet6Address v6Address;
	String fqdn;
	String circuitID;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.TWAN_IDENTIFIER;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=1;
		if(ssid!=null)
			length+=1+(ssid.length()+1)/2;
		
		if(getHasBSSID())
			length+=7;
		
		if(getHasCivicAddress())
			length+=1+civicAddress.getLength();
		
		if(getHasPLMNID())
			length+=3;
		
		if(operatorName!=null)
			length+=1+operatorName.getBytes().length;
		
		if(v4Address!=null)
			length+=6;
		else if(v6Address!=null)
			length+=18;
		else if(fqdn!=null)
			length+=fqdn.getBytes().length+2;
		
		if(circuitID!=null)
			length+=1+(circuitID.length()+1)/2;
		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws GTPParseException 
	{
		byte currByte=0;
		if(getHasBSSID())
			currByte|=(byte)0x01;
		
		if(getHasCivicAddress())
			currByte|=(byte)0x02;
		
		if(getHasPLMNID())
			currByte|=(byte)0x04;
		
		if(getHasOperatorName())
			currByte|=(byte)0x08;
		
		if(getHasLogicalAccessID())
			currByte|=(byte)0x10;
		
		buffer.writeByte(currByte);
		
		byte[] hex=StringFunctions.hexToBytes(ssid);
		buffer.writeByte(hex.length);
		buffer.writeBytes(hex);
		
		if(getHasBSSID())
		{
			hex=StringFunctions.hexToBytes(bssid);
			buffer.writeByte(hex.length);
			buffer.writeBytes(hex);
		}
		
		if(getHasCivicAddress())
		{
			buffer.writeByte(civicAddress.getLength());
			civicAddress.encode(buffer);
		}
		
		if(getHasPLMNID())
			plmnID.encode(buffer);
		
		if(getHasOperatorName())
		{
			hex=operatorName.getBytes();
			buffer.writeByte(hex.length);
			buffer.writeBytes(hex);
		}
		
		if(v4Address!=null)
		{
			buffer.writeByte(0);
			buffer.writeByte(4);
			buffer.writeBytes(v4Address.getAddress());
		}
		else if(v6Address!=null)
		{
			buffer.writeByte(0);
			buffer.writeByte(16);
			buffer.writeBytes(v6Address.getAddress());
		}
		else if(fqdn!=null)
		{
			hex=fqdn.getBytes();
			buffer.writeByte(0);
			buffer.writeByte(hex.length);
			buffer.writeBytes(hex);
		}
		
		if(circuitID!=null)
		{
			hex=StringFunctions.hexToBytes(circuitID);
			buffer.writeByte(hex.length);
			buffer.writeBytes(hex);
		}			
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) throws GTPParseException 
	{
		byte currByte=buffer.readByte();
		byte[] hex=new byte[buffer.readByte()];
		buffer.readBytes(hex);
		ssid=StringFunctions.bytesToHex(hex);
		
		if((currByte & 0x01)!=0)
		{
			hex=new byte[6];
			buffer.readBytes(hex);
			bssid=StringFunctions.bytesToHex(hex);
		}
		
		if((currByte & 0x02)!=0)
		{
			Integer addressLength=buffer.readByte() & 0x0FF;
			civicAddress=new CivicAddressImpl();
			civicAddress.decode(buffer, addressLength);
		}
		
		if((currByte & 0x04)!=0)
		{
			plmnID=new NetworkIdentityImpl();
			plmnID.decode(buffer);
		}
		
		if((currByte & 0x08)!=0)
		{
			hex=new byte[buffer.readByte()];
			buffer.readBytes(hex);
			operatorName=new String(hex);			
		}
		
		if((currByte & 0x10)!=0)
		{
			Integer relayType=buffer.readByte() & 0x01;
			Integer relayLength=buffer.readByte() & 0x0FF;
			if(relayType==0)
			{
				try
				{
					if(relayLength==4)
					{
						byte[] data=new byte[4];
						buffer.readBytes(data);
						v4Address=(Inet4Address)InetAddress.getByAddress(data);		
					}
					
					if(relayLength==16)
					{
						byte[] data=new byte[16];
						buffer.readBytes(data);
						v6Address=(Inet6Address)InetAddress.getByAddress(data);		
					}
				}
				catch(UnknownHostException ex)
				{
					throw new GTPParseException(ex.getMessage());
				}
			}
			else if(relayType==1)
			{
				byte[] data=new byte[buffer.readByte() & 0x0FF];
				buffer.readBytes(data);
				fqdn=new String(data);
			}
			
			hex=new byte[buffer.readByte()];
			buffer.readBytes(hex);
			circuitID=StringFunctions.bytesToHex(hex);
		}		
	}

	@Override
	public Boolean getHasBSSID() 
	{
		return bssid!=null;
	}

	@Override
	public Boolean getHasCivicAddress() 
	{
		return civicAddress!=null;
	}

	@Override
	public Boolean getHasPLMNID() 
	{
		return plmnID!=null;
	}

	@Override
	public Boolean getHasOperatorName() 
	{
		return operatorName!=null;
	}

	@Override
	public Boolean getHasLogicalAccessID() 
	{
		return v4Address!=null || v6Address!=null || fqdn!=null;
	}

	@Override
	public String getSSID() 
	{
		return ssid;
	}

	@Override
	public void setSSID(String ssid) 
	{
		this.ssid=ssid;
	}

	@Override
	public String getBSSID() 
	{
		return bssid;
	}

	@Override
	public void setBSSID(String bssid) 
	{
		this.bssid=bssid;
	}

	@Override
	public CivicAddress getCivicAddress() 
	{
		return civicAddress;
	}

	@Override
	public void setCivicAddress(CivicAddress civicAddress) 
	{
		this.civicAddress=civicAddress;
	}

	@Override
	public NetworkIdentity getPLMNID() 
	{
		return plmnID;
	}

	@Override
	public void setPLMNID(NetworkIdentity identity) 
	{
		this.plmnID=identity;
	}

	@Override
	public String getOperatorName() 
	{
		return operatorName;
	}

	@Override
	public void setOperatorName(String operatorName) 
	{
		this.operatorName=operatorName;
	}

	@Override
	public Inet4Address getV4Address() 
	{
		return this.v4Address;
	}

	@Override
	public Inet6Address getV6Address() 
	{
		return this.v6Address;
	}

	@Override
	public void setV4Address(Inet4Address address) 
	{
		this.v4Address=address;
	}

	@Override
	public void setV6Address(Inet6Address address) 
	{
		this.v6Address=address;
	}

	@Override
	public String getFQDN() 
	{
		return fqdn;
	}

	@Override
	public void setFQDN(String fqdn) 
	{
		this.fqdn=fqdn;
	}

	@Override
	public String getCircuitID() 
	{
		return this.circuitID;
	}

	@Override
	public void setCircuitID(String cirquitID) 
	{
		this.circuitID=cirquitID;		
	}
}