package com.mobius.software.telco.protocols.gtp.impl.headers;
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
import io.netty.buffer.ByteBuf;

import com.mobius.software.telco.protocols.gtp.api.exceptions.MissingArgumentException;
import com.mobius.software.telco.protocols.gtp.api.headers.DelayClass;
import com.mobius.software.telco.protocols.gtp.api.headers.DeliveryOfErroneousSDU;
import com.mobius.software.telco.protocols.gtp.api.headers.DeliveryOrder;
import com.mobius.software.telco.protocols.gtp.api.headers.ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.MeanThroughtput;
import com.mobius.software.telco.protocols.gtp.api.headers.PeakThroughtput;
import com.mobius.software.telco.protocols.gtp.api.headers.PrecendenceClass;
import com.mobius.software.telco.protocols.gtp.api.headers.PriorityLevel;
import com.mobius.software.telco.protocols.gtp.api.headers.QOSReliability;
import com.mobius.software.telco.protocols.gtp.api.headers.QosProfile;
import com.mobius.software.telco.protocols.gtp.api.headers.ResidualBER;
import com.mobius.software.telco.protocols.gtp.api.headers.SDUErrorRate;
import com.mobius.software.telco.protocols.gtp.api.headers.SignalingIndicator;
import com.mobius.software.telco.protocols.gtp.api.headers.SourceStatisticsDescriptor;
import com.mobius.software.telco.protocols.gtp.api.headers.TrafficClass;

public class QOSProfileImpl extends AbstractTLV implements QosProfile 
{
	private Integer allocationPriority;
	private QOSReliability qosReliability;
	private DelayClass delayClass;
	private PrecendenceClass precendenceClass;
	private PeakThroughtput peakThroughtput;
	private MeanThroughtput meanThroughtput;
	private DeliveryOfErroneousSDU deliveryOfErroneousSDU;
	private DeliveryOrder deliveryOrder;
	private TrafficClass trafficClass;
	private Integer maximumSDUSize;
	private Long maximumBitRateUplink;
	private Long maximumBitRateDownlink;
	private Long guaranteedBitRateUplink;
	private Long guaranteedBitRateDownlink;
	private Integer transferDelay;
	private PriorityLevel trafficHandlingPriority;
    private ResidualBER residualBER;
	private SDUErrorRate sduErrorRate;
	private SourceStatisticsDescriptor sourceStatisticsDescriptor;
	private SignalingIndicator signalingIndicator;
	
	private byte[] ignoredBytes;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.QOS_PROFILE;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return true;
	}

	@Override
	public Integer getLength() 
	{
		if(ignoredBytes!=null && ignoredBytes.length>0)
			return 13+ignoredBytes.length;
		
		if(signalingIndicator!=null || sourceStatisticsDescriptor!=null)
			return 13;
		
		if(guaranteedBitRateDownlink!=null)
			return 12;
		
		if(guaranteedBitRateUplink!=null)
			return 11;
		
		if(transferDelay!=null || trafficHandlingPriority!=null)
			return 10;
		
		if(residualBER!=null || sduErrorRate!=null)
			return 9;
		
		if(maximumBitRateDownlink!=null)
			return 8;
		
		if(maximumBitRateUplink!=null)
			return 7;
		
		if(maximumSDUSize!=null)
			return 6;
		
		if(trafficClass!=null || deliveryOrder!=null || deliveryOfErroneousSDU!=null)
			return 5;
		
		return 4;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		int length=getLength();
		buffer.writeByte(allocationPriority);
		byte currByte=0;
		if(delayClass!=null)
			currByte|=((delayClass.getValue()<<3) & 0x38);
		if(qosReliability!=null)
			currByte|=(qosReliability.getValue() & 0x07);
		buffer.writeByte(currByte);
		
		currByte=0;
		if(peakThroughtput!=null)
			currByte|=((peakThroughtput.getValue()<<4) & 0x0F0);
		if(precendenceClass!=null)
			currByte|=(precendenceClass.getValue() & 0x07);
		buffer.writeByte(currByte);
		
		currByte=0;
		if(meanThroughtput!=null)
			currByte|=(meanThroughtput.getValue() & 0x1F);
		buffer.writeByte(currByte);
		
		if(length==4)
			return;
		
		currByte=0;
		if(trafficClass!=null)
			currByte|=((trafficClass.getValue()<<5) & 0xE0);
		
		if(deliveryOrder!=null)
			currByte|=((deliveryOrder.getValue()<<3) & 0x18);
		
		if(deliveryOfErroneousSDU!=null)
			currByte|=(deliveryOfErroneousSDU.getValue() & 0x07);
		
		buffer.writeByte(currByte);
		
		if(length==5)
			return;
		
		currByte=0;
		if(maximumSDUSize!=null)
		{
			if(maximumSDUSize<=1500)
				currByte=(byte)(maximumSDUSize/10);
			else if(maximumSDUSize==1502)
				currByte=(byte)151;
			else if(maximumSDUSize==1510)
				currByte=(byte)152;
			else if(maximumSDUSize==1520)
				currByte=(byte)153;
		}
		
		buffer.writeByte(currByte);
		
		if(length==6)
			return;
		
		currByte=0;
		if(maximumBitRateUplink!=null)
		{
			if(maximumBitRateUplink==0)
				currByte=(byte)0xFF;
			else if(maximumBitRateUplink>8640)
				currByte=(byte)254;
			else if(maximumBitRateUplink>=576)
				currByte=(byte)((maximumBitRateUplink-576)/64 + 128);
			else if(maximumBitRateUplink>=64)
				currByte=(byte)((maximumBitRateUplink-64)/8 + 64);
			else
				currByte=maximumBitRateUplink.byteValue();
		}
		
		buffer.writeByte(currByte);
		
		if(length==7)
			return;
		
		currByte=0;
		if(maximumBitRateDownlink!=null)
		{
			if(maximumBitRateDownlink==0)
				currByte=(byte)0xFF;
			else if(maximumBitRateDownlink>8640)
				currByte=(byte)254;
			else if(maximumBitRateDownlink>=576)
				currByte=(byte)((maximumBitRateDownlink-576)/64 + 128);
			else if(maximumBitRateDownlink>=64)
				currByte=(byte)((maximumBitRateDownlink-64)/8 + 64);
			else
				currByte=maximumBitRateDownlink.byteValue();
		}
		
		buffer.writeByte(currByte);
		
		if(length==8)
			return;
		
		currByte=0;
		if(residualBER!=null)
			currByte|=((residualBER.getValue()<<4) & 0xF0);
		
		if(sduErrorRate!=null)
			currByte|=(sduErrorRate.getValue() & 0x0F);
		
		buffer.writeByte(currByte);
		
		if(length==9)
			return;
		
		currByte=0;
		if(transferDelay!=null)
		{
			if(transferDelay==-1)
				currByte|=0xFC;
			else if(transferDelay>=1000)
				currByte|=((((transferDelay-1000)/100 + 32)<<2) & 0xFC);
			else if(transferDelay>=200)
				currByte|=((((transferDelay-200)/50 + 16)<<2) & 0xFC);
			else
				currByte|=(((transferDelay/10)<<2) & 0xFC);
		}
		
		if(trafficHandlingPriority!=null)
			currByte|=(trafficHandlingPriority.getValue() & 0x03);
		
		buffer.writeByte(currByte);
		
		if(length==10)
			return;
		
		currByte=0;
		if(guaranteedBitRateUplink!=null)
		{
			if(guaranteedBitRateUplink==0)
				currByte=(byte)0xFF;
			else if(guaranteedBitRateUplink>8640)
				currByte=(byte)254;
			else if(guaranteedBitRateUplink>=576)
				currByte=(byte)((guaranteedBitRateUplink-576)/64 + 128);
			else if(guaranteedBitRateUplink>=64)
				currByte=(byte)((guaranteedBitRateUplink-64)/8 + 64);
			else
				currByte=guaranteedBitRateUplink.byteValue();
		}
		
		buffer.writeByte(currByte);
		
		if(length==11)
			return;
		
		currByte=0;
		if(guaranteedBitRateDownlink!=null)
		{
			if(guaranteedBitRateDownlink==0)
				currByte=(byte)0xFF;
			else if(guaranteedBitRateDownlink>8640)
				currByte=(byte)254;
			else if(guaranteedBitRateDownlink>=576)
				currByte=(byte)((guaranteedBitRateDownlink-576)/64 + 128);
			else if(guaranteedBitRateDownlink>=64)
				currByte=(byte)((guaranteedBitRateDownlink-64)/8 + 64);
			else
				currByte=guaranteedBitRateDownlink.byteValue();
		}
		
		buffer.writeByte(currByte);
		
		if(length==12)
			return;
		
		currByte=0;
		if(signalingIndicator!=null)
			currByte|=((signalingIndicator.getValue()<<4) & 0x10);
		
		if(sourceStatisticsDescriptor!=null)
			currByte|=(sourceStatisticsDescriptor.getValue() & 0x0F);
		
		buffer.writeByte(currByte);
		
		if(length>13)
			buffer.writeBytes(ignoredBytes);					
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		allocationPriority=buffer.readByte() & 0x0FF;
		
		byte currByte=buffer.readByte();
		delayClass=DelayClass.fromInt((currByte>>3)&0x07);
		qosReliability=QOSReliability.fromInt(currByte&0x07);
		
		currByte=buffer.readByte();
		peakThroughtput=PeakThroughtput.fromInt((currByte>>4) & 0x0F);
		precendenceClass=PrecendenceClass.fromInt(currByte & 0x07);
		
		currByte=buffer.readByte();
		meanThroughtput=MeanThroughtput.fromInt(currByte&0x1F);
		
		if(length>4)
		{
			currByte=buffer.readByte();
			trafficClass=TrafficClass.fromInt((currByte>>5)&0x07);
			deliveryOrder=DeliveryOrder.fromInt((currByte>>3)&0x03);
			deliveryOfErroneousSDU=DeliveryOfErroneousSDU.fromInt(currByte&0x07);
		}
		
		if(length>5)
		{
			maximumSDUSize=buffer.readByte() & 0x0FF;
			if(maximumSDUSize==153)
				maximumSDUSize=1520;
			else if(maximumSDUSize==152)
				maximumSDUSize=1510;
			else if(maximumSDUSize==151)
				maximumSDUSize=1502;
			else
				maximumSDUSize=(maximumSDUSize & 0x0FF)*10;			
		}
		
		if(length>6)
		{
			maximumBitRateUplink=Long.valueOf(buffer.readByte() & 0x0FF);
			if(maximumBitRateUplink==255)
				maximumBitRateUplink=0L;
			else if(maximumBitRateUplink>=128)
				maximumBitRateUplink=576+64*(maximumBitRateUplink-128);
			else if(maximumBitRateUplink>=64)
				maximumBitRateUplink=64+8*(maximumBitRateUplink-64);
		}
		
		if(length>7)
		{
			maximumBitRateDownlink=Long.valueOf(buffer.readByte() & 0x0FF);
			if(maximumBitRateDownlink==255)
				maximumBitRateDownlink=0L;
			else if(maximumBitRateDownlink>=128)
				maximumBitRateDownlink=576+64*(maximumBitRateDownlink-128);
			else if(maximumBitRateDownlink>=64)
				maximumBitRateDownlink=64+8*(maximumBitRateDownlink-64);
		}
		
		if(length>8)
		{
			currByte=buffer.readByte();
			residualBER=ResidualBER.fromInt((currByte>>4)&0x0F);
			sduErrorRate=SDUErrorRate.fromInt(currByte&0x0F);
		}
		
		if(length>9)
		{
			currByte=buffer.readByte();
			transferDelay=(currByte>>2)&0x3F;
			if(transferDelay>62)
				transferDelay=-1;
			else if(transferDelay>=32)
				transferDelay=1000+100*(transferDelay-32);
			else if(transferDelay>=16)
				transferDelay=200+50*(transferDelay-16);
			else
				transferDelay=transferDelay*10;
			
			trafficHandlingPriority=PriorityLevel.fromInt(currByte & 0x03);
		}
		
		if(length>10)
		{
			guaranteedBitRateUplink=Long.valueOf(buffer.readByte() & 0x0FF);
			if(guaranteedBitRateUplink==255)
				guaranteedBitRateUplink=0L;
			else if(guaranteedBitRateUplink>=128)
				guaranteedBitRateUplink=576+64*(guaranteedBitRateUplink-128);
			else if(guaranteedBitRateUplink>=64)
				guaranteedBitRateUplink=64+8*(guaranteedBitRateUplink-64);
		}
		
		if(length>11)
		{
			guaranteedBitRateDownlink=Long.valueOf(buffer.readByte() & 0x0FF);
			if(guaranteedBitRateDownlink==255)
				guaranteedBitRateDownlink=0L;
			else if(guaranteedBitRateDownlink>=128)
				guaranteedBitRateDownlink=576+64*(guaranteedBitRateDownlink-128);
			else if(guaranteedBitRateDownlink>=64)
				guaranteedBitRateDownlink=64+8*(guaranteedBitRateDownlink-64);
		}
		
		if(length>12)
		{
			currByte=buffer.readByte();
			sourceStatisticsDescriptor=SourceStatisticsDescriptor.fromInt(currByte & 0x0F);
			signalingIndicator=SignalingIndicator.fromInt((currByte>>4) & 0x01);
		}

		if(length>13)
		{
			ignoredBytes=new byte[length-13];			
			buffer.readBytes(ignoredBytes);
		}
	}

	@Override
	public Integer getAllocationPriority()
	{
		return allocationPriority;
	}
	
	@Override
	public void setAllocationPriority(Integer priority)
	{
		this.allocationPriority=priority;
	}
	
	@Override
	public QOSReliability getQosReliability()
	{
		return qosReliability;
	}
	
	@Override
	public void setQOSReliability(QOSReliability reliability)
	{
		this.qosReliability=reliability;
	}
	
	@Override
	public DelayClass getDelayClass()
	{
		return delayClass;
	}
	
	@Override
	public void setDelayClass(DelayClass delayClass)
	{
		this.delayClass=delayClass;
	}
	
	@Override
	public PrecendenceClass getPrecendenceClass()
	{
		return precendenceClass;
	}
	
	@Override
	public void setPrecendenceClass(PrecendenceClass precendenceClass)
	{
		this.precendenceClass=precendenceClass;
	}
	
	@Override
	public PeakThroughtput getPeakThroughtput()
	{
		return this.peakThroughtput;
	}
	
	@Override
	public void setPeakThroughtput(PeakThroughtput peakThroughtput)
	{
		this.peakThroughtput=peakThroughtput;
	}

	@Override
	public MeanThroughtput getMeanThroughtput()
	{
		return this.meanThroughtput;
	}
	
	@Override
	public void setMeanThroughtput(MeanThroughtput meanThroughtput)
	{
		this.meanThroughtput=meanThroughtput;
	}
	
	@Override
	public DeliveryOfErroneousSDU getDeliveryOfErroneousSDU()
	{
		return this.deliveryOfErroneousSDU;
	}
	
	@Override
	public void setDeliveryOfErroneousSDU(DeliveryOfErroneousSDU deliveryOfErroneousSDU)
	{
		this.deliveryOfErroneousSDU=deliveryOfErroneousSDU;
	}
	
	@Override
	public DeliveryOrder getDeliveryOrder()
	{
		return this.deliveryOrder;
	}
	
	@Override
	public void setDeliveryOrder(DeliveryOrder deliveryOrder)
	{
		this.deliveryOrder=deliveryOrder;
	}

	@Override
	public TrafficClass getTrafficClass()
	{
		return this.trafficClass;
	}
	
	@Override
	public void setTrafficClass(TrafficClass trafficClass)
	{
		this.trafficClass=trafficClass;
	}

	//from 0 to 150 the value is real/10 , 151=1502 , 152=1510, 153=1520
	@Override
	public Integer getMaximumSDUSize()
	{
		return this.maximumSDUSize;
	}
	
	@Override
	public void setMaximumSDUSize(Integer maximumSDU)
	{
		this.maximumSDUSize=maximumSDU;
	}
	
	//255 - 0KBPS, 1-63 (in kbps),64-127(64+8kbps units),128-254(576+64kbps units),higher then  8640 kbps should use extended
	//extended: 1-74(8600+100kbps units),75-186(16mbps+1mbps units),187-250(128mbps+2mbps units), higher then 256mbps - use extended 2
	//extended2: 1-61(256mbps+4mbps units),62-161(500mbps+10mbps units),162-246(1600mbps+100mbps units)
	@Override
	public Long getMaximumBitRateUplink()
	{
		return this.maximumBitRateUplink;
	}
	
	@Override
	public void setMaximumBitRateUplink(Long value)
	{
		this.maximumBitRateUplink=value;
	}
	
	@Override
	public Long getMaximumBitRateDownlink()
	{
		return this.maximumBitRateDownlink;
	}
	
	@Override
	public void setMaximumBitRateDownlink(Long value)
	{
		this.maximumBitRateDownlink=value;
	}
	
	@Override
	public Long getGuaranteedBitRateUplink()
	{
		return this.guaranteedBitRateUplink;
	}
	
	@Override
	public void setGuaranteedBitRateUplink(Long value)
	{
		this.guaranteedBitRateUplink=value;
	}
	
	@Override
	public Long getGuaranteedBitRateDownlink()
	{
		return this.guaranteedBitRateDownlink;
	}
	
	@Override
	public void setGuaranteedBitRateDownlink(Long value)
	{
		this.guaranteedBitRateDownlink=value;
	}
	
	//1-15 (in 10ms units),16-31(200+50ms units),32-62(1000ms+100ms units) 
	@Override
	public Integer getTransferDelay()
	{
		return this.transferDelay;
	}
		
	@Override
	public void setTransferDelay(Integer value)
	{
		this.transferDelay=value;
	}
		
	@Override
	public PriorityLevel getTrafficHandlingPriority()
	{
		return this.trafficHandlingPriority;
	}
	
	@Override
	public void setTrafficHandlingPriority(PriorityLevel level)
	{
		this.trafficHandlingPriority=level;
	}
	
	@Override
	public ResidualBER getResidualBER()
	{
		return this.residualBER;
	}
	
	@Override
	public void setResidualBER(ResidualBER value)
	{
		this.residualBER=value;
	}
	
	@Override
	public SDUErrorRate getSDUErrorRate()
	{
		return this.sduErrorRate;
	}
	
	@Override
	public void setSDUErrorRate(SDUErrorRate value)
	{
		this.sduErrorRate=value;
	}
	
	@Override
	public SourceStatisticsDescriptor getSourceStatisticsDescriptor()
	{
		return this.sourceStatisticsDescriptor;
	}
	
	@Override
	public void setSourceStatisticsDescriptor(SourceStatisticsDescriptor value)
	{
		this.sourceStatisticsDescriptor=value;
	}
	
	@Override
	public SignalingIndicator getSignalingIndicator()
	{
		return this.signalingIndicator;
	}
	
	@Override
	public void setSignalingIndicator(SignalingIndicator value)
	{
		this.signalingIndicator=value;
	}
}