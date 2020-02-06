package com.mobius.software.telco.protocols.gtp.api.headers;
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
public interface QosProfile extends TLV1  
{
	Integer getAllocationPriority();
	
	void setAllocationPriority(Integer priority);
	
	QOSReliability getQosReliability();
	
	void setQOSReliability(QOSReliability reliability);
	
	DelayClass getDelayClass();
	
	void setDelayClass(DelayClass delayClass);
	
	PrecendenceClass getPrecendenceClass();
	
	void setPrecendenceClass(PrecendenceClass precendenceClass);
	
	PeakThroughtput getPeakThroughtput();
	
	void setPeakThroughtput(PeakThroughtput peakThroughtput);

	MeanThroughtput getMeanThroughtput();
	
	void setMeanThroughtput(MeanThroughtput meanThroughtput);
	
	DeliveryOfErroneousSDU getDeliveryOfErroneousSDU();
	
	void setDeliveryOfErroneousSDU(DeliveryOfErroneousSDU deliveryOfErroneousSDU);
	
	DeliveryOrder getDeliveryOrder();
	
	void setDeliveryOrder(DeliveryOrder deliveryOrder);
	
	TrafficClass getTrafficClass();
	
	void setTrafficClass(TrafficClass trafficClass);

	//from 0 to 150 the value is real/10 , 151=1502 , 152=1510, 153=1520
	Integer getMaximumSDUSize();
	
	void setMaximumSDUSize(Integer maximumSDU);
	
	//255 - 0KBPS, 1-63 (in kbps),64-127(64+8kbps units),128-254(576+64kbps units),higher then  8640 kbps should use extended
	//extended: 1-74(8600+100kbps units),75-186(16mbps+1mbps units),187-250(128mbps+2mbps units), higher then 256mbps - use extended 2
	//extended2: 1-61(256mbps+4mbps units),62-161(500mbps+10mbps units),162-246(1600mbps+100mbps units)
	Long getMaximumBitRateUplink();
	
	void setMaximumBitRateUplink(Long value);
	
	Long getMaximumBitRateDownlink();
	
	void setMaximumBitRateDownlink(Long value);
	
	Long getGuaranteedBitRateUplink();
	
	void setGuaranteedBitRateUplink(Long value);
	
	Long getGuaranteedBitRateDownlink();
	
	void setGuaranteedBitRateDownlink(Long value);
	
	PriorityLevel getTrafficHandlingPriority();
	
	void setTrafficHandlingPriority(PriorityLevel level);
	
	//1-15 (in 10ms units),16-31(200+50ms units),32-62(1000ms+100ms units) 
	Integer getTransferDelay();
		
	void setTransferDelay(Integer value);
		
	ResidualBER getResidualBER();
	
	void setResidualBER(ResidualBER value);
	
	SDUErrorRate getSDUErrorRate();
	
	void setSDUErrorRate(SDUErrorRate value);
	
	SourceStatisticsDescriptor getSourceStatisticsDescriptor();
	
	void setSourceStatisticsDescriptor(SourceStatisticsDescriptor value);
	
	SignalingIndicator getSignalingIndicator();
	
	void setSignalingIndicator(SignalingIndicator value);	
}