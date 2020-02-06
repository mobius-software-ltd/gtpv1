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
import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;

import io.netty.buffer.ByteBuf;

public class TLV2Factory 
{
	public static TLV2 decode(ByteBuf buffer) throws GTPParseException
	{
		int type=(buffer.readByte() & 0x0FF);
		GTP2ElementType elementType=GTP2ElementType.fromInt(type);
		TLV2 result=null;
		switch(elementType)
		{
			case ABSOLUTE_TIME_OF_MBMS_DATA_TRANSFER:
				result=new AbsoluteTimeOfMBMSDataTransferImpl();
				break;
			case ACTION_INDICATOR:
				result=new ActionIndicationImpl();
				break;
			case ADDITIONAL_FLAGS_FOR_SRVCC:
				result=new AdditionalFlagsForSRVCCImpl();
				break;
			case ADDITIONAL_MM_CONTEXT_FOR_SRVCC:
				result=new AdditionalMMContextForSRVCCImpl();
				break;
			case ADDITIONAL_PROTOCOL_CONFIGURATION_OPTIONS:
				result=new AdditionalProtocolConfigurationOptionImpl();
				break;
			case ALLOCATION_RETENTION_PRIORITY:
				result=new AllocationRetentionPriorityImpl();
				break;
			case AMBR:
				result=new AMBRImpl();
				break;
			case APN:
				result=new APNImpl();
				break;
			case APN_AND_RELATIVE_CAPACITY:
				result=new APNAndRelativeCapacityImpl();
				break;
			case APN_RESTRICTION:
				result=new APNRestrictionIImpl();
				break;
			case BEARER_CONTEXT:
				result=new BearerContextImpl();
				break;
			case BEARER_FLAGS:
				result=new BearerFlagsImpl();
				break;
			case BEARER_QOS:
				result=new BearerQOSImpl();
				break;
			case BEARER_TFT:
				result=new TFTImpl();
				break;
			case CAUSE:
				result=new CauseImpl();
				break;
			case CHANGE_REPORTING_ACTION:
				result=new ChangeReportingActionImpl();
				break;
			case CHANGE_TO_REPORT_FLAGS:
				result=new ChangeToReportFlagsImpl();
				break;
			case CHANNEL_NEEDED:
				result=new ChannelNeededImpl();
				break;
			case CHARGING_CHARACTERISTICS:
				result=new ChargingCharacteristicImpl();
				break;
			case CHARGING_ID:
				result=new ChargingIDImpl();
				break;
			case CIOT_OPTIMIZATION_SUPPORT_INDICATION:
				result=new CIoTOptimizationsSupportIndicationIndicationImpl();
				break;
			case CN_OPERATOR_SELECTION_ENTITY:
				result=new CNOperatorSelectorEntityImpl();
				break;
			case COMPLETE_REQUEST_MESSAGE:
				result=new CompleteRequestMessageImpl();
				break;
			case COUNTER:
				result=new CounterImpl();
				break;
			case CSG_ID:
				result=new CSGIDImpl();
				break;
			case CSG_INFORMATION_REPORTING_ACTION:
				result=new CSGInformationReportingActionImpl();
				break;
			case CSG_MEMBERSHIP_INDICATION:
				result=new CSGMembershipIndicationImpl();
				break;
			case DELAY_VALUE:
				result=new DelayValueImpl();
				break;
			case DETACH_TYPE:
				result=new DetachTypeImpl();
				break;
			case ECGI_LIST:
				result=new ECGIListImpl();
				break;
			case EMLPP_PRIORITY:
				result=new EMLPPriorityPImpl();
				break;
			case EPC_TIMER:
				result=new EPCTimerImpl();
				break;
			case EPS_BEARER_ID:
				result=new EPSBearerIDImpl();
				break;
			case EUTRAN_ROUND_TRIP_DELAY:
				result=new EUTRANRoundTripDelayImpl();
				break;
			case EXTENDED_PROTOCOL_CONFIGURATION_OPTIONS:
				result=new ExtendedProtocolConfigurationOptionsImpl();
				break;
			case EXTENTION:
				//CURRENTLY NOT SUPPORTED
				break;
			case FCAUSE:
				result=new FCauseImpl();
				break;
			case FCONTAINER:
				result=new FContainerImpl();
				break;
			case FLOW_QOS:
				result=new FlowQOSImpl();
				break;
			case FQDN:
				result=new FQDNImpl();
				break;
			case FQ_CSID:
				result=new FQCSIDImpl();
				break;
			case FTEID:
				result=new FTEIDImpl();
				break;
			case GLOBAL_CN_ID:
				result=new GlobalCNIDImpl();
				break;
			case GUTI:
				result=new GUTIImpl();
				break;
			case HANDOVER_INDICATOR:
				result=new HandoverIndicatorImpl();
				break;
			case HEADER_COMPRESSION_CONFIGURATION:
				result=new HeaderCompressionConfigurationImpl();
				break;
			case HENB_INFORMATION_REPORTING:
				result=new HENBInformationReportingImpl();
				break;
			case HOP_COUNTER:
				result=new HopCounterImpl();
				break;
			case HRPD_SECTOR_ID:
				result=new HRPDSectorIDImpl();
				break;
			case IMSI:
				result=new IMSIImpl();
				break;
			case INDICATION:
				result=new IndicationImpl();
				break;
			case INTEGER_NUMBER:
				result=new IntegerNumberImpl();
				break;
			case IPV4_CONFIGURATION_PARAMETERS:
				result=new IPV4ConfigurationParametersImpl();
				break;
			case IP_ADDRESS:
				result=new IPAddressImpl();
				break;
			case LDN:
				result=new LDNImpl();
				break;
			case LOAD_CONTROL_INFORMATION:
				result=new LoadControlInformationImpl();
				break;
			case MAPPED_UE_USAGE_TYPE:
				result=new MappedUEUsageTypeImpl();
				break;
			case MAXIMUM_PACKET_LOSS_RATE:
				result=new MaximumPacketLossImpl();
				break;
			case MBMS_DISTRIBUTION_ACKNOWLEDGMENT:
				result=new MBMSDistributionAcknowledgmentImpl();
				break;
			case MBMS_FLAGS:
				result=new MBMSFlagsImpl();
				break;
			case MBMS_FLOW_IDENTIFIER:
				result=new MBMSFlowIdentifierImpl();
				break;
			case MBMS_IP_MULTICAST_DISTRIBUTION:
				result=new MBMSIPMulticastDistributionImpl();
				break;
			case MBMS_SERVICE_AREA:
				result=new MBMSServiceAreaImpl();
				break;
			case MBMS_SESSION_DURATION:
				result=new MBMSSessionDurationImpl();
				break;
			case MBMS_SESSION_IDENTIFIER:
				result=new MBMSSessionIdentifierImpl();
				break;
			case MBMS_TIME_TO_DATA_TRANSFER:
				result=new MBMSTimeToDataTransferImpl();
				break;
			case MDT_CONFIGURATION:
				result=new MDTConfigurationImpl();
				break;
			case MEI:
				result=new MEIImpl();
				break;
			case METRIC:
				result=new MetricImpl();
				break;
			case MILLISECONDS_TIMESTAMP:
				result=new MillisecondTimeStampImpl();
				break;
			case MM_CONTEXT_EPS:
				result=new MMContextEPSImpl();
				break;
			case MM_CONTEXT_FOR_EUTRAN:
				result=new MMContextEUTRANImpl();
				break;
			case MM_CONTEXT_FOR_CS_TO_PS:
				result=new MMContextCSToPSImpl();
				break;
			case MM_CONTEXT_FOR_UTRAN:
				result=new MMContextUTRANImpl();
				break;
			case MM_CONTEXT_GSM_AND_TRIPLETS:
				result=new MMContextGSMAndTripletsImpl();
				break;
			case MM_CONTEXT_GSM_CIPHER_AND_QUINTIPLETS:
				result=new MMContextGSMCipherAndQuintipletsImpl();
				break;
			case MM_CONTEXT_UMTS_AND_QUINTIPLETS:
				result=new MMContextUMTSAndQuintipletsImpl();
				break;
			case MM_CONTEXT_UMTS_CIPHER_AND_QUINTIPLETS:
				result=new MMContextUMTSCipherAndQuintipletsImpl();
				break;
			case MM_CONTEXT_UMTS_KEY_QUADRIPLETS_AND_QUINTIPLETS:
				result=new MMContextUMTSQuadripletsAndQuintipletsImpl();
				break;
			case MONITORING_EVENT_INFORMATION:
				result=new MonitoringEventInformationImpl();
				break;
			case MSISDN:
				result=new MSISDNImpl();
				break;
			case NODE_FEATURES:
				result=new NodeFeaturesImpl();
				break;
			case NODE_IDENTIFIER:
				result=new NodeIdentityImpl();
				break;
			case NODE_NUMBER:
				result=new NodeNumberImpl();
				break;
			case NODE_TYPE:
				result=new NodeTypeImpl();
				break;
			case OVERLOAD_CONTROL_INFORMATION:
				result=new OverloadControlInformationImpl();
				break;
			case PACKET_FLOW_ID:
				result=new PacketFlowIDImpl();
				break;
			case PAGING_AND_SERVICE_INFORMATION:
				result=new PagingAndServiceInformationImpl();
				break;
			case PDN_ADDRESS_ALLOCATION:
				result=new PDNAddressAllocationImpl();
				break;
			case PDN_CONNECTION:
				result=new PDNConnectionImpl();
				break;
			case PDN_GW_PMIP_GRE_TUNNEL_INFO:
				result=new PDNGWPMIPGRETunnelInfoImpl();
				break;
			case PDN_TYPE:
				result=new PDNTypeImpl();
				break;
			case PDU_NUMBERS:
				result=new PDUNumbersImpl();
				break;
			case PLMN_ID:
				result=new PLMNIDImpl();
				break;
			case PORT_NUMBER:
				result=new PortNumberImpl();
				break;
			case PRESENCE_REPORTING_AREA_ACTION:
				result=new PresenceAreaReportingActionImpl();
				break;
			case PRESENCE_REPORTING_AREA_INFORMATION:
				result=new PRAInformationImpl();
				break;
			case PRIVATE_EXTENTION:
				result=new GenericPrivateExtentionImpl();
				break;
			case PROCEDURE_TRANSACTION_ID:
				result=new ProcedureTransactionIDImpl();
				break;
			case PROTOCOL_CONFIGURATION_OPTIONS:
				result=new ProtocolConfigurationOptionImpl();
				break;
			case PTMSI:
				result=new PTMSIImpl();
				break;
			case PTMSI_SIGNATURE:
				result=new PTMSISignatureImpl();				
				break;
			case RAB_CONTEXT:
				result=new RABContextImpl();
				break;
			case RAN_NAS_CAUSE:
				result=new RANNASCauseImpl();
				break;
			case RAT_TYPE:
				result=new RatTypeImpl();
				break;
			case RECOVERY:
				result=new RecoveryImpl();
				break;
			case REMOTE_UE_CONTEXT:
				result=new RemoteUEContextImpl();
				break;
			case REMOTE_UE_IP_INFORMATION:
				result=new RemoteUEIPInformationImpl();
				break;
			case REMOTE_USER_ID:
				result=new RemoteUserIDImpl();
				break;
			case RFSP_INDEX:
				result=new RFSPIndexImpl();
				break;
			case RIM_ROUTING_ADDRESS:
				result=new RIMRoutingAddressImpl();
				break;
			case S101_TRANSPARENT_CONTAINER:
				result=new S101TransparentContainerImpl();
				break;
			case S103_GRE_TUNNEL_INFO:
				result=new S103GRETunnelInfoImpl();
				break;
			case S103_HSGW_IP_ADDRESS:
				result=new S103HSGWIPAddressImpl();
				break;
			case S103_PDN_DATA_FORWARDING_INFO:
				result=new S103PDNDataForwardingInfoImpl();
				break;
			case S1_U_PDN_DATA_FORWARDING_INFO:
				result=new S1UDataForwardingImpl();
				break;
			case SAI:
				result=new ServiceAreaIdentifierImpl();
				break;
			case SCEF_PDN_CONNECTION:
				result=new SCEFPDNConnectionImpl();
				break;
			case SECONDARY_RAT_DATA_USAGE_REPORT:
				result=new SecondaryRATUsageDataReportImpl();
				break;
			case SELECTION_MODE:
				result=new SelectionModeImpl();
				break;
			case SEQUENCE_NUMBER:
				result=new SequenceNumberImpl();
				break;
			case SERVICE_INDICATOR:
				result=new ServiceIndicatorImpl();
				break;
			case SERVING_NETWORK:
				result=new ServingNetworkImpl();
				break;				
			case SERVING_PLMN_RATE_CONTROL:
				result=new ServingPLMNRateControlImpl();
				break;
			case SESSION_ID_2:
				result=new SessionID2Impl();
				break;
			case SIGNALING_PRIORITY_INDICATOR:
				result=new SignalingPriorityIndicationImpl();
				break;
			case SOURCE_IDENTIFICATION:
				result=new SourceIdentificationImpl();
				break;
			case SOURCE_RNC_PDCP_CONTEXT_INFO:
				result=new SourceRNCPDCPContextInfoImpl();
				break;
			case SOURCE_TO_TARGET_TRANSPARENT_CONTAINER:
				result=new SourceToTargetTransparentContainerImpl();
				break;
			case SRVCC_CAUSE:
				result=new SRVCCCauseImpl();
				break;
			case STN_SR:
				result=new STNSRImpl();
				break;
			case SV_FLAGS:
				result=new SVFlagsImpl();
				break;
			case S_121_TRANSPARENT_CONTAINER:
				result=new S121TransparentContainerImpl();
				break;
			case TARGET_GLOBAL_CELL_ID:
				result=new TargetCellIDImpl();
				break;
			case TARGET_IDENTIFICATION:
				result=new TargetIdentificationImpl();
				break;
			case TARGET_RNC_ID:
				result=new TargetRNCIDImpl();
				break;
			case TARGET_TO_SOURCE_TRANSPARENT_CONTAINER:
				result=new TargetToSourceTransparentContainerImpl();
				break;
			case TEID_C:
				result=new TEIDCImpl();
				break;
			case THROTTLING:
				result=new ThrottlingImpl();
				break;
			case TMGI:
				result=new TMGIImpl();
				break;
			case TMSI:
				result=new TMSIImpl();
				break;
			case TRACE_INFORMATION:
				result=new TraceInformationImpl();
				break;
			case TRACE_REFERENCE:
				result=new TraceReferenceImpl();
				break;
			case TRAFFIC_AGGREGATION_DESCRIPTION:
				result=new TrafficAggregationDescriptorImpl();
				break;
			case TRANSACTION_IDENTIFIER:
				result=new TransactionIdentifierImpl();
				break;
			case TRUSTED_WLAN_MODE_INDICATION:
				result=new TrustedWLANModeIndicationImpl();
				break;
			case TWAN_IDENTIFIER:
				result=new TWANIdentifierImpl();
				break;
			case TWAN_IDENTIFIER_TIMESTAMP:
				result=new TWANTimeStampImpl();
				break;
			case UE_TIMEZONE:
				result=new UETimezoneImpl();
				break;
			case ULI_TIMESTAMP:
				result=new ULITimeStampImpl();
				break;
			case UNATHENTICATION_IMSI:
				result=new UnauthenticatedIMSIImpl();
				break;
			case UNKNOWN:
				return null;
			case UP_FUNCTION_SELECTION_INDICATION_FLAGS:
				result=new UPFunctionSelectionIndicationFlagsImpl();
				break;
			case USER_CSG_INFORMATION:
				result=new CSGInformationImpl();
				break;
			case USER_LOCATION_INFORMATION:
				result=new UserLocationInformationImpl();
				break;
			case WLAN_OFFLOADABILITY_INDICATION:
				result=new WLANOffloadabilityIndicationImpl();
				break;
			default:
				break;			
		}
		
		if(result!=null)
			result.decode(buffer);
		
		return result;
	}
}