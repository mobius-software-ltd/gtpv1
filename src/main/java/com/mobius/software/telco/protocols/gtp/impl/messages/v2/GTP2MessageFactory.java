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
import io.netty.buffer.ByteBuf;

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.messages.GenericGTPMessage;

public class GTP2MessageFactory 
{
	public static GenericGTPMessage decode(byte header,ByteBuf buffer) throws GTPParseException
	{
		GenericGTPMessage gtpMessage=null;
		GTP2MessageType messageType=GTP2MessageType.fromInt(buffer.readByte());
		System.out.println("MESSAGE:" + messageType);
		switch(messageType)
		{
			case ECHO_REQUEST:
				gtpMessage=new EchoRequestImpl();				
				break;
			case ECHO_RESPONSE:
				gtpMessage=new EchoResponseImpl();
				break;
			case VERSION_NOT_SUPPORTED:
				gtpMessage=new VersionNotSupportedImpl();
				break;
			case ALERT_MME_ACKNOWLEDGE:
				gtpMessage=new AlertMMEAcknowledgeImpl();
				break;
			case ALERT_MME_NOTIFICATION:
				gtpMessage=new AlertMMENotificationImpl();
				break;
			case BEARER_RESOURCE_COMMAND:
				gtpMessage=new BearerResourceCommandImpl();
				break;
			case BEARER_RESOURCE_FAILURE_INDICATION:
				gtpMessage=new BearerResourceFailureIndicationImpl();
				break;
			case CHANGE_NOTIFICATION_REQUEST:
				gtpMessage=new ChangeNotificationRequestImpl();
				break;
			case CHANGE_NOTIFICATION_RESPONSE:
				gtpMessage=new ChangeNotificationResponseImpl();
				break;
			case CONFIGURATION_TRANSFER_TUNNEL:
				gtpMessage=new ConfigurationTransferTunnelImpl();
				break;
			case CONTEXT_ACKNOWLEDGE:
				gtpMessage=new ContextAcknowledgeImpl();				
				break;
			case CONTEXT_REQUEST:
				gtpMessage=new ContextRequestImpl();
				break;
			case CONTEXT_RESPONSE:
				gtpMessage=new ContextResponseImpl();
				break;
			case CREATE_BEARER_REQUEST:		
				gtpMessage=new CreateBearerRequestImpl();
				break;
			case CREATE_BEARER_RESPONSE:
				gtpMessage=new CreateBearerResponseImpl();
				break;
			case CREATE_FORWARDING_TUNNEL_REQUEST:
				gtpMessage=new CreateForwardingTunnelRequestImpl();
				break;
			case CREATE_FORWARDING_TUNNEL_RESPONSE:
				gtpMessage=new CreateForwardingTunnelResponseImpl();
				break;
			case CREATE_INDIRECT_DATA_FORWARDING_TUNNEL_REQUEST:
				gtpMessage=new CreateIndirectDataForwardingTunnelRequestImpl();
				break;
			case CREATE_INDIRECT_DATA_FORWARDING_TUNNEL_RESPONSE:
				gtpMessage=new CreateIndirectDataForwardingTunnelResponseImpl();
				break;
			case CREATE_SESSION_REQUEST:
				gtpMessage=new CreateSessionRequestImpl();
				break;
			case CREATE_SESSION_RESPONSE:
				gtpMessage=new CreateSessionResponseImpl();
				break;
			case CS_PAGING_INDICATION:
				gtpMessage=new CSPagingIndicationImpl();
				break;
			case DELETE_BEARER_COMMAND:
				gtpMessage=new DeleteBearerCommandImpl();
				break;
			case DELETE_BEARER_FAILURE_INDICATION:
				gtpMessage=new DeleteBearerFailureIndicationImpl();
				break;
			case DELETE_BEARER_REQUEST:
				gtpMessage=new DeleteBearerRequestImpl();
				break;
			case DELETE_BEARER_RESPONSE:
				gtpMessage=new DeleteBearerResponseImpl();
				break;
			case DELETE_INDIRECT_DATA_FORWARDING_TUNNEL_REQUEST:
				gtpMessage=new DeleteIndirectDataForwardingTunnelRequestImpl();
				break;
			case DELETE_INDIRECT_DATA_FORWARDING_TUNNEL_RESPONSE:
				gtpMessage=new DeleteIndirectDataForwardingTunnelResponseImpl();
				break;
			case DELETE_PDN_CONNECTION_SET_REQUEST:
				gtpMessage=new DeletePDNConnectionSetRequestImpl();
				break;
			case DELETE_PDN_CONNECTION_SET_RESPONSE:
				gtpMessage=new DeletePDNConnectionSetResponseImpl();
				break;
			case DELETE_SESSION_REQUEST:
				gtpMessage=new DeleteSessionRequestImpl();
				break;
			case DELETE_SESSION_RESPONSE:
				gtpMessage=new DeleteSessionResponseImpl();
				break;
			case DETACH_ACKNOWLEDGE:
				gtpMessage=new DetachAcknowledgeImpl();
				break;
			case DETACH_NOTIFICATION:
				gtpMessage=new DetachNotificationImpl();
				break;
			case DIRECT_TRANSFER_REQUEST:
				gtpMessage=new DirectTransferRequestImpl();
				break;
			case DIRECT_TRANSFER_RESPOSE:
				gtpMessage=new DirectTransferResponseImpl();
				break;
			case DOWNLINK_DATA_FAILURE_INDICATION:
				gtpMessage=new DownlinkDataNotificationFailureIndicationImpl();
				break;
			case DOWNLINK_DATA_NOTIFICATION:
				gtpMessage=new DownlinkDataNotificationImpl();
				break;
			case DOWNLINK_DATA_NOTIFICATION_ACKNOWLEDGE:
				gtpMessage=new DownlinkDataNotificationAcknowledgeImpl();
				break;
			case FORWARD_ACCESS_CONTEXT_ACKNOWLEDGE:
				gtpMessage=new ForwardAccessContextAcknowledgeImpl();
				break;
			case FORWARD_ACCESS_CONTEXT_NOTIFICATION:
				gtpMessage=new ForwardAccessContextNotificationImpl();
				break;
			case FORWARD_RELOCATION_COMPLETE_ACKNOWLEDGE:
				gtpMessage=new ForwardRelocationCompleteAcknowledgeImpl();
				break;
			case FORWARD_RELOCATION_COMPLETE_NOTIFICATION:
				gtpMessage=new ForwardRelocationCompleteNotificationImpl();
				break;
			case FORWARD_RELOCATION_REQUEST:
				gtpMessage=new ForwardRelocationRequestImpl();
				break;
			case FORWARD_RELOCATION_RESPONSE:
				gtpMessage=new ForwardRelocationResponseImpl();
				break;
			case IDENTIFICATION_REQUEST:
				gtpMessage=new IdentificationRequestImpl();
				break;
			case IDENTIFICATION_RESPONSE:
				gtpMessage=new IdentificationResponseImpl();
				break;
			case ISR_STATUS_INDICATION:
				gtpMessage=new ISRStatusIndicationImpl();
				break;
			case MBMS_SESSION_START_REQUEST:
				gtpMessage=new MBMSSessionStartRequestImpl();
				break;
			case MBMS_SESSION_START_RESPONSE:
				gtpMessage=new MBMSSessionStartResponseImpl();
				break;
			case MBMS_SESSION_STOP_REQUEST:
				gtpMessage=new MBMSSessionStopRequestImpl();
				break;
			case MBMS_SESSION_STOP_RESPONSE:
				gtpMessage=new MBMSSessionStopResponseImpl();
				break;
			case MBMS_SESSION_UPDATE_REQUEST:
				gtpMessage=new MBMSSessionUpdateRequestImpl();
				break;
			case MBMS_SESSION_UPDATE_RESPONSE:
				gtpMessage=new MBMSSessionUpdateResponseImpl();
				break;
			case MODIFY_ACCESS_BEARER_REQUEST:
				gtpMessage=new ModifyAccessBearersRequestImpl();
				break;
			case MODIFY_ACCESS_BEARER_RESPONSE:
				gtpMessage=new ModifyAccessBearersResponseImpl();
				break;
			case MODIFY_BEARER_COMMAND:
				gtpMessage=new ModifyBearerCommandImpl();
				break;
			case MODIFY_BEARER_FAILURE_INDICATION:
				gtpMessage=new ModifyBearerFailureIndicationImpl();
				break;
			case MODIFY_BEARER_REQUEST:
				gtpMessage=new ModifyBearerRequestImpl();
				break;
			case MODIFY_BEARER_RESPONSE:
				gtpMessage=new ModifyBearerResponseImpl();
				break;
			case NOTIFICATION_REQUEST:
				gtpMessage=new NotificationRequestImpl();
				break;
			case NOTIFICATION_RESPONSE:
				gtpMessage=new NotificationResponseImpl();
				break;
			case PGW_DOWNLINK_TRIGGERING_ACKNOWLEDGE:
				gtpMessage=new PGWDownlinkTriggeringAcknowledgeImpl();
				break;
			case PGW_DOWNLINK_TRIGGERING_INDICATION:
				gtpMessage=new PGWDownlinkTriggeringNotificationImpl();
				break;
			case PGW_RESTART_NOTIFICATION:
				gtpMessage=new PGWRestartNotificationImpl();
				break;
			case PGW_RESTART_NOTIFICATION_ACKNOWLEDGE:
				gtpMessage=new PGWRestartNotificationAcknowledgeImpl();
				break;
			case RAN_INFORMATION_RELAY:
				gtpMessage=new RANInformationRelayImpl();
				break;
			case RELEASE_ACCESS_BEARER_REQUEST:
				gtpMessage=new ReleaseAccessBearersRequestImpl();
				break;
			case RELEASE_ACCESS_BEARER_RESPONSE:
				gtpMessage=new ReleaseAccessBearersResponseImpl();
				break;
			case RELOCATION_CANCEL_REQUEST:
				gtpMessage=new RelocationCancelRequestImpl();
				break;
			case RELOCATION_CANCEL_RESPONSE:
				gtpMessage=new RelocationCancelResponseImpl();
				break;
			case REMOTE_UE_REPORT_ACKNOWLEDGE:
				gtpMessage=new RemoteUEReportAcknowledgeImpl();
				break;
			case REMOTE_UE_REPORT_NOTIFICATION:
				gtpMessage=new RemoteUEReportNotificationImpl();
				break;
			case RESUME_ACKNOWLEDGE:
				gtpMessage=new ResumeAcknowledgeImpl();
				break;
			case RESUME_NOTIFICATION:
				gtpMessage=new ResumeNotificationImpl();
				break;
			case RIM_INFORMATION_TRANSFER:
				gtpMessage=new RIMInformationTransferImpl();
				break;
			case SRVCC_CS_TO_PS_CANCEL_ACKNOWLEDGE:
				gtpMessage=new SRVCCCSToPSCancelAcknowledgeImpl();
				break;
			case SRVCC_CS_TO_PS_CANCEL_NOTIFICATION:
				gtpMessage=new SRVCCCSToPSCancelNotificationImpl();
				break;
			case SRVCC_CS_TO_PS_COMPLETE_ACKNOWLEDGE:
				gtpMessage=new SRVCCCSToPSCompleteAcknowledgeImpl();
				break;
			case SRVCC_CS_TO_PS_COMPLETE_NOTIFICATION:
				gtpMessage=new SRVCCCSToPSCompleteNotificationImpl();
				break;
			case SRVCC_CS_TO_PS_REQUEST:
				gtpMessage=new SRVCCCSToPSRequestImpl();
				break;
			case SRVCC_CS_TO_PS_RESPONSE:
				gtpMessage=new SRVCCCSToPSResponseImpl();
				break;
			case SRVCC_PS_TO_CS_CANCEL_ACKNOWLEDGE:
				gtpMessage=new SRVCCPSToCSCancelAcknowledgeImpl();
				break;
			case SRVCC_PS_TO_CS_CANCEL_NOTIFICATION:
				gtpMessage=new SRVCCPSToCSCancelNotificationImpl();
				break;
			case SRVCC_PS_TO_CS_COMPLETE_ACKNOWLEDGE:
				gtpMessage=new SRVCCPSToCSCompleteAcknowledgeImpl();
				break;
			case SRVCC_PS_TO_CS_COMPLETE_NOTIFICATION:
				gtpMessage=new SRVCCPSToCSCompleteNotificationImpl();
				break;
			case SRVCC_PS_TO_CS_REQUEST:
				gtpMessage=new SRVCCPSToCSRequestImpl();
				break;
			case SRVCC_PS_TO_CS_RESPONSE:
				gtpMessage=new SRVCCPSToCSResponseImpl();
				break;
			case STOP_PAGING_INDICATION:
				gtpMessage=new StopPagingIndicationImpl();
				break;
			case SUSPEND_ACKNOWLEDGE:
				gtpMessage=new SuspendAcknowledgeImpl();
				break;
			case SUSPEND_NOTIFICATION:
				gtpMessage=new SuspendNotificationImpl();
				break;
			case TRACE_SESSION_ACTIVATION:
				gtpMessage=new TraceSessionActivationImpl();
				break;
			case TRACE_SESSION_DEACTIVATION:
				gtpMessage=new TraceSessionDeactivationImpl();
				break;
			case UE_ACTIVITY_ACKNOWLEDGE:
				gtpMessage=new UEActivityAcknowledgeImpl();
				break;
			case UE_ACTIVITY_NOTIFICATION:
				gtpMessage=new UEActivityNotificationImpl();
				break;
			case UE_REGISTRATION_QUERY_REQUEST:
				gtpMessage=new UERegistrationQueryRequestImpl();
				break;
			case UE_REGISTRATION_QUERY_RESPONSE:
				gtpMessage=new UERegistrationQueryResponseImpl();
				break;
			case UNKNOWN:
				break;
			case UPDATE_BEARER_REQUEST:
				gtpMessage=new UpdateBearerRequestImpl();
				break;
			case UPDATE_PDN_CONNECTION_SET_REQUEST:
				gtpMessage=new UpdatePDNConnectionSetRequestImpl();
				break;
			case UPDATE_PDN_CONNECTION_SET_RESPONSE:
				gtpMessage=new UpdatePDNConnectionSetResponseImpl();
				break;
			case UPDDATE_BEARER_RESPONSE:
				gtpMessage=new UpdateBearerResponseImpl();
				break;
			default:
				break;			
		}
		
		gtpMessage.applyHeaderByte((byte)(header & 0x0F));
		gtpMessage.decode(buffer);
		return gtpMessage;
	}
}
