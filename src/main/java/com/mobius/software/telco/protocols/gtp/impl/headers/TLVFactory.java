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
import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;

import io.netty.buffer.ByteBuf;

public class TLVFactory 
{
	public static TLV1 decode(ByteBuf buffer) throws GTPParseException
	{
		int type=(buffer.readByte() & 0x0FF);
		ElementType elementType=ElementType.fromInt(type);
		TLV1 result=null;
		switch(elementType)
		{
			case TEID_1:
				result=new TunnerEndpointIdentifier1Impl();
				break;
			case TRACE_TYPE:
				result=new TraceTypeImpl();
				break;
			case TEICP:
				result=new TunnelEndpointIdentifierControlPaneImpl();
				break;
			case TRACE_REFERENCE:
				result=new TraceReferenceImpl();
				break;
			case SELECTION_MODE:
				result=new SelectionModeImpl();
				break;
			case RAI:
				result=new RoutingAreaIdentityImpl();
				break;
			case RECOVERY:
				result=new RecoveryImpl();
				break;
			case PRIVATE_EXTENTION:
				result=new GenericPrivateExtentionImpl();
				break;
			case NSAPI:
				result=new NSAPIImpl();
				break;
			case IMSI:
				result=new IMSIImpl();
				break;
			case CAUSE:
				result=new CauseImpl();
				break;
			case CHARGING_CHARACTERISTICS:
				result=new ChargingCharacteristicImpl();
				break;
			case CHARGING_GATEWAY_ADDRESS:
				result=new ChargingGatewayAddressImpl();
				break;
			case ADDRESS_OF_RECOMMENDED_NODE:
				result=new AddressOfRecommendedNodeImpl();
				break;
			case END_USER_ADDRESS:
				result=new EndUserAddressImpl();
				break;
			case APN:
				result=new APNImpl();
				break;
			case PROTOCOL_CONFIGURATION_OPTIONS:
				result=new ProtocolConfigurationOptionImpl();
				break;
			case GSN_ADDRESS:
				result=new GSNAddressImpl();
				break;
			case MSISDN:
				result=new MSISDNImpl();
				break;
			case QOS_PROFILE:
				result=new QOSProfileImpl();
				break;
			case TRAFFIC_FLOW_TEMPLATE:
				result=new TFTImpl();
				break;
			case TRIGGER_ID:
				result=new TriggerIDImpl();
				break;
			case OMC_IDENTITY:
				result=new OMCIdentityImpl();
				break;
			case COMMON_FLAGS:
				result=new CommonFlagsImpl();
				break;
			case APN_RESTRICTION:
				result=new APNRestrictionIImpl();
				break;
			case RAT_TYPE:
				result=new RatTypeImpl();
				break;
			case USER_LOCATION_INFORMATION:
				result=new UserLocationInformationImpl();
				break;
			case MS_TIMEZONE:
				result=new MSTimezoneImpl();
				break;
			case IMEI_SV:
				result=new IMEISVImpl();
				break;
			case CAMEL_CHARGING_INFORMATION_CONTAINER:
				result=new CamelChargingInformationContainerImpl();
				break;
			case ADDITIONAL_TRACE_INFO:
				result=new AdditionalTraceInfoImpl();
				break;
			case CORRELATION_ID:
				result=new CorrelationIDImpl();
				break;
			case EVOLVED_ALLOCATION_RETENTION_PRIORITY_1:
				result=new EvolvedAllocationRetentionPriority1Impl();
				break;
			case EVOLVED_ALLOCATION_RETENTION_PRIORITY_2:
				result=new EvolvedAllocationRetentionPriority2Impl();
				break;
			case EXTENDED_COMMON_FLAGS:
				result=new ExtendedCommonFlagsImpl();
				break;
			case USER_CSG_INFORMATION:
				result=new CSGInformationImpl();
				break;
			case CSG_ID:
				result=new CSGIDImpl();
				break;
			case APN_AMBR:
				result=new APNAMBRImpl();
				break;
			case APN_AMBR_WITH_NSAPI:
				result=new APNAMBRWithNSAPIImpl();
				break;
			case SIGNALING_PRIORITY_INDICATION:
				result=new SignalingPriorityIndicationImpl();
				break;
			case SIGNALING_PRIORITY_INDICATION_WITH_NSAPI:
				result=new SignalingPriorityIndicationWithNSAPIImpl();
				break;
			case CN_OPERATOR_SELECTION_ENTITY:
				result=new CNOperatorSelectorEntityImpl();
				break;	
			case REORDERING_REQUIRED:
				result=new ReorderingRequiredImpl();
				break;
			case CHARGING_ID:
				result=new ChargingIDImpl();
				break;
			case MS_INFO_CHANGE_REPORTING_ACTION:
				result=new ChangeReportActionImpl();
				break;
			case BEARER_CONTROL_MODE:
				result=new BearerModeImpl();
				break;
			case CSG_INFORMATION_REPORTING_ACTION:
				result=new CSGInformationReportingActionImpl();
				break;
			case GGSN_BACK_OFF_TIME:
				result=new GGSNBackoffTimeImpl();
				break;
			case DIRECT_TUNNEL_FLAGS:
				result=new DirectTunnelFlagsImpl();
				break;	
			case TEARDOWN_IND:
				result=new TeardownIndicatorImpl();
				break;
			case ULI_TIMESTAMP:
				result=new ULITimestampImpl();
				break;
			case EXTENTION_HEADER_TYPE_LIST:
				result=new ExtentionHeaderListImpl();
				break;
			case MAP_CAUSE:
				result=new MapCauseImpl();
				break;
			case MS_NOT_REACHABLE_REASON:
				result=new MSNotReachableReasonImpl();
				break;
			case PTMSI:
				result=new PTMSIImpl();
				break;
			case PTMSI_SIGNATURE:
				result=new PTMSISignatureImpl();
				break;
			case HOP_COUNTER:
				result=new HopCounterImpl();
				break;
			case AUTHENTICATION_TRIPLET:
				result=new AuthenticationTripletImpl();
				break;
			case AUTHENTICATION_QUINTIPLET:
				result=new AuthenticationQuintipletImpl();
				break;
			case TLLI:
				result=new TLLIImpl();
				break;
			case MS_VALIDATED:
				result=new MSValidatedImpl();
				break;
			case SGSN_NUMBER:
				result=new SGSNNumberImpl();
				break;
			case RAB_CONTEXT:
				result=new RABContextImpl();
				break;
			case RADIO_PRIORITY_SMS:
				result=new RadioPrioritySMSImpl();
				break;
			case RADIO_PRIORITY:
				result=new RadioPriorityImpl();
				break;
			case RADIO_PRIORITY_LCS:
				result=new RadioPriorityLCSImpl();
				break;
			case PACKET_FLOW_ID:
				result=new PacketFlowIDImpl();
				break;
			case MM_CONTEXT:
				result=new MMContextImpl();
				break;
			case PDP_CONTEXT:
				result=new PDPContextImpl();
				break;
			case PDP_CONTEXT_PRIORITIZATION:
				result=new PDPContextPrioritizationImpl();
				break;
			case MBMS_UE_CONTEXT:
				result=new MBMSUEContextImpl();
				break;
			case RFSP_INDEX:
				result=new RFSPIndexImpl();
				break;
			case FQDN:
				result=new FQDNImpl();
				break;
			case UE_NETWORK_CAPABILITY:
				result=new UENetworkCapabilityImpl();
				break;
			case UE_AMBR:
				result=new UEAMBRImpl();
				break;
			case HIGHER_BITRATES_THEN_16_MBPS_FLAG:
				result=new HigherThen16MbpsBitrateImpl();
				break;
			case SELECTION_MODE_WITH_NSAPI:
				result=new SelectionModeWithNSAPIImpl();
				break;
			case LOCAL_HOME_NETWORK_ID_WITH_NSAPI:
				result=new LocalHomeNetworkIDWithNSAPIImpl();
				break;
			case RANAP_CAUSE:
				result=new RanapCauseImpl();
				break;
			case TARGET_IDENTIFICATION:
				result=new TargetIdentificationImpl();
				break;
			case UTRAN_TRANSPARENT_CONTAINER:
				result=new UTRANTransparentContentImpl();
				break;
			case SELECTED_PLMN_ID:
				result=new SelectedPLMNImpl();
				break;
			case BSS_CONTAINER:
				result=new BSSContainerImpl();
				break;
			case CELL_IDENTIFICATION:
				result=new CellIdentificationImpl();
				break;
			case BSSGP_CAUSE:
				result=new BSSGPCauseImpl();
				break;
			case PS_HANDOVER_XID_PARAMETERS:
				result=new XIDParametersImpl();
				break;
			case RELIABLE_INTER_RAT_HANDOVER_INFO:
				result=new ReliableRatHandoverIndicationImpl();
				break;
			case CSG_MEMBERSHIP_INDICATION:
				result=new CSGMembershipIndicationImpl();
				break;
			case ADDITIONAL_MM_CONTEXT_FOR_SRVCC:
				result=new AdditionalMMContextForSRVCCImpl();
				break;
			case ADDITIONAL_FLAGS_FOR_SRVCC:
				result=new AdditionalFlagsForSRVCCImpl();
				break;
			case STN_SR:
				result=new STNSRImpl();
				break;
			case C_MSISDN:
				result=new CMSISDNImpl();
				break;
			case ENODEB_ID:
				result=new ENodeBIDImpl();
				break;
			case EXTENDED_RANAP_CAUSE:
				result=new ExtendedRanapCauseImpl();
				break;
			case TEID_2:
				result=new TunnerEndpointIdentifier2Impl();
				break;
			case RAB_SETUP_INFORMATION:
				result=new RABSetupInformationImpl();
				break;
			case ADDITIONAL_RAB_SETUP_INFORMATION:
				result=new AdditionalRABSetupInformationImpl();
				break;
			case LIST_OF_SETUP_PFCs:
				result=new ListOfSetupPFCImpl();
				break;
			case SOURCE_RNC_PDCP_CONTEXT_INFO:
				result=new SourceRNCPDPContextInfoImpl();
				break;
			case PDU_NUMBERS:
				result=new PDUNumbersImpl();
				break;
			case RAN_TRANSPARENT_CONTAINER:
				result=new RANTransparentContainerImpl();
				break;
			case RIM_ROUTING_ADDRESS:
				result=new RIMRoutingAddressImpl();
				break;
			case RIM_ROUTING_ADDRESS_DISCRIMITATOR:
				result=new RIMRoutingAddressDiscriminatorImpl();
				break;	
			case MBMS_PROTOCOL_CONFIGURATION_OPTION:
				result=new MBMSProtocolConfigurationOptionsImpl();
				break;
			case ADDITIONAL_MBMS_TRACE_INFO:
				result=new AdditionalMBMSTraceInfoImpl();
				break;
			case ENHANCED_NSAPI:
				result=new EnhancedNSAPIImpl();
				break;
			case TMGI:
				result=new TMGIImpl();
				break;
			case REQUIRED_MBMS_BEARER_CAPABILITIES:
				result=new RequiredMBMSBearerCapabilitiesImpl();
				break;
			case MBMS_SERVICE_AREA:
				result=new MBMSServiceAreaImpl();
				break;
			case MBMS_SESSION_IDENTIFIER:
				result=new MBMSSessionIdentifierImpl();
				break;
			case MBMS_2G_3G_INDICATOR:
				result=new MBMS23GIndicatorImpl();
				break;
			case MBMS_SESSION_DURATION:
				result=new MBMSSessionDurationImpl();
				break;
			case MBMS_SESSION_REPETITION_NUMBER:
				result=new MBMSSessionRepetitionNumberImpl();
				break;
			case MBMS_TIME_TO_DATA_TRANSFER:
				result=new MBMSTimeToDataTransferImpl();
				break;
			case MBMS_FLOW_IDENTIFIER:
				result=new MBMSFlowIdentifierImpl();
				break;
			case MBMS_IP_MULTICAST_DISTRIBUTION:
				result=new MBMSIPMulticastDistributionImpl();
				break;
			case MBMS_DISTRIBUTION_ACKNOWLEDGMENT:
				result=new MBMSDistributionAcknowledgmentImpl();
				break;
			case PACKET_TRANSFER_COMMAND:
				result=new PacketTransferCommandImpl();
				break;
			case DATA_RECORD_PACKET:
				result=new DataRecordPacketImpl();
				break;
			case REQUESTS_RESPONDED:
				result=new RequestsRespondedImpl();
				break;
			case SEQUENCE_NUMBER_OF_CANCELLED_PACKETS:
				result=new SequenceNumbersOfCancelledPacketsImpl();
				break;
			case SEQUENCE_NUMBER_OF_RELEASED_PACKETS:
				result=new SequenceNumbersOfReleasedPacketsImpl();
				break;
			case UNKNOWN:
				return null;
		}
		
		result.decode(buffer);
		return result;
	}
}