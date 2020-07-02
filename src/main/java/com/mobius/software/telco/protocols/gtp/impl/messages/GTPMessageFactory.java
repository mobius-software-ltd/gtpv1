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
import io.netty.buffer.ByteBuf;

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.ProtocolType;
import com.mobius.software.telco.protocols.gtp.api.messages.GenericGTPMessage;

public class GTPMessageFactory 
{
	public static GenericGTPMessage decode(byte header,ByteBuf buffer,Boolean ignoreUnknown) throws GTPParseException
	{
		GenericGTPMessage gtpMessage=null;
		ProtocolType packetType=ProtocolType.fromInt((header >>4) & 0x01);
		MessageType messageType=MessageType.fromInt(buffer.readByte());
		switch(messageType)
		{
			case ECHO_REQUEST:
				switch(packetType)
				{
					case GTP_TAG:
						gtpMessage=new GTPTagEchoRequestImpl();
						break;
					case GTP:
					default:
						gtpMessage=new EchoRequestImpl();
						break;				
				}				
				break;
			case ECHO_RESPONSE:
				switch(packetType)
				{
					case GTP_TAG:
						gtpMessage=new GTPTagEchoResponseImpl();
						break;
					case GTP:
					default:
						gtpMessage=new EchoResponseImpl();
						break;				
				};
				break;
			case VERSION_NOT_SUPPORTED:
				switch(packetType)
				{
					case GTP_TAG:
						gtpMessage=new GTPTagVersionNotSupportedImpl();
						break;
					case GTP:
					default:
						gtpMessage=new VersionNotSupportedImpl();
						break;				
				};
				break;
			case NODE_ALIVE_REQUEST:
				switch(packetType)
				{
					case GTP:
						throw new GTPParseException("Invalid packet type for Node Alive Request");						
					case GTP_TAG:
					default:
						gtpMessage=new NodeAliveRequestImpl();
						break;				
				};
				break;
			case NODE_ALIVE_RESPONSE:
				switch(packetType)
				{
					case GTP:
						throw new GTPParseException("Invalid packet type for Node Alive Response");						
					case GTP_TAG:
					default:
						gtpMessage=new NodeAliveResponseImpl();
						break;				
				};
				break;
			case REDIRECTION_REQUEST:
				switch(packetType)
				{
					case GTP:
						throw new GTPParseException("Invalid packet type for Redirection Request");						
					case GTP_TAG:
					default:
						gtpMessage=new RedirectionRequestImpl();
						break;				
				};
				break;
			case REDIRECTION_RESPONSE:
				switch(packetType)
				{
					case GTP:
						throw new GTPParseException("Invalid packet type for Redirection Response");						
					case GTP_TAG:
					default:
						gtpMessage=new RedirectionResponseImpl();
						break;
				};
				break;
			case CREATE_PDP_CONTEXT_REQUEST:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Create PDP Context Request");						
					case GTP:
					default:
						gtpMessage=new CreatePdpContextRequestImpl();
						break;
				};
				break;
			case CREATE_PDP_CONTEXT_RESPONSE:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Create PDP Context Response");						
					case GTP:
					default:
						gtpMessage=new CreatePdpContextResponseImpl();
						break;
				};
				break;
			case UPDATE_PDP_CONTEXT_REQUEST:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Update PDP Context Request");						
					case GTP:
					default:
						gtpMessage=new UpdatePdpContextRequestImpl();
						break;
				};
				break;
			case UPDATE_PDP_CONTEXT_RESPONSE:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Update PDP Context Response");						
					case GTP:
					default:
						gtpMessage=new UpdatePdpContextResponseImpl();
						break;
				};
				break;
			case DELETE_PDP_CONTEXT_REQUEST:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Delete PDP Context Request");						
					case GTP:
					default:
						gtpMessage=new DeletePdpContextRequestImpl();
						break;
				};
				break;
			case DELETE_PDP_CONTEXT_RESPONSE:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Delete PDP Context Response");						
					case GTP:
					default:
						gtpMessage=new DeletePdpContextResponseImpl();
						break;
				};
				break;
			case INITIATE_PDP_CONTEXT_ACTIVATION_REQUEST:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Initiate PDP Context Activation Request");						
					case GTP:
					default:
						gtpMessage=new InitiatePdpContextActivationRequestImpl();
						break;
				};
				break;
			case INITIATE_PDP_CONTEXT_ACTIVATION_RESPONSE:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Initiate PDP Context Activation Response");						
					case GTP:
					default:
						gtpMessage=new InitiatePdpContextActivationResponseImpl();
						break;
				};
				break;
			case ERROR_INDICATION:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Error Indication");						
					case GTP:
					default:
						gtpMessage=new ErrorIndicationImpl();
						break;
				};
				break;
			case PDU_NOTIFICATION_REQUEST:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Pdu Notification Request");						
					case GTP:
					default:
						gtpMessage=new PduNotificationRequestImpl();
						break;
				};
				break;
			case PDU_NOTIFICATION_RESPONSE:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Pdu Notification Response");						
					case GTP:
					default:
						gtpMessage=new PduNotificationResponseImpl();
						break;
				};
				break;
			case PDU_NOTIFICATION_REJECT_REQUEST:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Pdu Notification Reject Request");						
					case GTP:
					default:
						gtpMessage=new PduNotificationRejectRequestImpl();
						break;
				};
				break;
			case PDU_NOTIFICATION_REJECT_RESPONSE:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Pdu Notification Reject Response");						
					case GTP:
					default:
						gtpMessage=new PduNotificationRejectResponseImpl();
						break;
				};
				break;
			case SUPPORTED_EXTENSION_HEADERS_NOTIFICATION:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Supported Extention Headers Notification");						
					case GTP:
					default:
						gtpMessage=new SupportedExtentionsHeaderNotificationImpl();
						break;
				};
				break;
			case SEND_ROUTING_INFORMATION_FOR_GPRS_REQUEST:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Send Routing Info For GPRS Request");						
					case GTP:
					default:
						gtpMessage=new SendRoutingInfoForGPRSRequestImpl();
						break;
				};
				break;
			case SEND_ROUTING_INFORMATION_FOR_GPRS_RESPONSE:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Send Routing Info For GPRS Response");						
					case GTP:
					default:
						gtpMessage=new SendRoutingInfoForGPRSResponseImpl();
						break;
				};
				break;
			case FAILURE_REPORT_REQUEST:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Failure Report Request");						
					case GTP:
					default:
						gtpMessage=new FailureReportRequestImpl();
						break;
				};
				break;
			case FAILURE_REPORT_RESPONSE:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Failure Report Response");						
					case GTP:
					default:
						gtpMessage=new FailureReportResponseImpl();
						break;
				};
				break;
			case NOTE_MS_GPRS_PRESENT_REQUEST:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Note MS GPRS Present Request");						
					case GTP:
					default:
						gtpMessage=new NoteMSGPRSPresentRequestImpl();
						break;
				};
				break;
			case NOTE_MS_GPRS_PRESENT_RESPONSE:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Note MS GPRS Present Response");						
					case GTP:
					default:
						gtpMessage=new NoteMSGPRSPresentResponseImpl();
						break;
				};
				break;
			case IDENTIFICATION_REQUEST:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Identification Request");						
					case GTP:
					default:
						gtpMessage=new IdentificationRequestImpl();
						break;
				};
				break;
			case IDENTIFICATION_RESPONSE:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Identification Response");						
					case GTP:
					default:
						gtpMessage=new IdentificationResponseImpl();
						break;
				};
				break;
			case SGSN_CONTEXT_REQUEST:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for SGSN Context Request");						
					case GTP:
					default:
						gtpMessage=new SGSNContextRequestImpl();
						break;
				};
				break;
			case SGSN_CONTEXT_RESPONSE:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for SGSN Context Response");						
					case GTP:
					default:
						gtpMessage=new SGSNContextResponseImpl();
						break;
				};
				break;
			case SGSN_CONTEXT_ACKNOWLEDGE:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for SGSN Context Acknowledge");						
					case GTP:
					default:
						gtpMessage=new SGSNContextAcknowledgeImpl();
						break;
				};
				break;
			case FORWARD_RELOCATION_REQUEST:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Forward Relocation Request");						
					case GTP:
					default:
						gtpMessage=new ForwardRelocationRequestImpl();
						break;
				};
				break;
			case FORWARD_RELOCATION_RESPONSE:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Forward Relocation Response");						
					case GTP:
					default:
						gtpMessage=new ForwardRelocationResponseImpl();
						break;
				};
				break;
			case FORWARD_RELOCATION_COMPLETE:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Forward Relocation Complete");						
					case GTP:
					default:
						gtpMessage=new ForwardRelocationCompleteImpl();
						break;
				};
				break;
			case FORWARD_RELOCATION_COMPLETE_ACKNOWLEDGMENT:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Forward Relocation Complete Acknowledgement");						
					case GTP:
					default:
						gtpMessage=new ForwardRelocationCompleteAcknowledgementImpl();
						break;
				};
				break;
			case RELOCATION_CANCEL_REQUEST:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Relocation Cancel Request");						
					case GTP:
					default:
						gtpMessage=new RelocationCancelRequestImpl();
						break;
				};
				break;
			case RELOCATION_CANCEL_RESPONSE:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Relocation Cancel Response");						
					case GTP:
					default:
						gtpMessage=new RelocationCancelResponseImpl();
						break;
				};
				break;
			case FORWARD_SRNS_CONTEXT:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Forward SRNS Context");						
					case GTP:
					default:
						gtpMessage=new ForwardSRNSContentRequestImpl();
						break;
				};
				break;
			case FORWARD_SRNS_CONTEXT_ACKNOWLEDGMENT:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Forward SRNS Context Acknowledgement");						
					case GTP:
					default:
						gtpMessage=new ForwardSRNSContentAcknowledgementImpl();
						break;
				};
				break;
			case RAN_INFORMATION_RELAY:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for RAN Information Relay");						
					case GTP:
					default:
						gtpMessage=new RANInformationRelayImpl();
						break;
				};
				break;
			case MBMS_NOTIFICATION_REQUEST:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for MBMS Notification Request");						
					case GTP:
					default:
						gtpMessage=new MBMSNotificationRequestImpl();
						break;
				};
				break;
			case MBMS_NOTIFICATION_RESPONSE:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for MBMS Notification Response");						
					case GTP:
					default:
						gtpMessage=new MBMSNotificationResponseImpl();
						break;
				};
				break;
			case MBMS_NOTIFICATION_REJECT_REQUEST:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for MBMS Notification Reject Request");						
					case GTP:
					default:
						gtpMessage=new MBMSNotificationRejectRequestImpl();
						break;
				};
				break;
			case MBMS_NOTIFICATION_REJECT_RESPONSE:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for MBMS Notification Reject Response");						
					case GTP:
					default:
						gtpMessage=new MBMSNotificationResponseImpl();
						break;
				};
				break;
			case CREATE_MBMS_CONTEXT_REQUEST:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Create MBMS Context Request");						
					case GTP:
					default:
						gtpMessage=new CreateMBMSContextRequestImpl();
						break;
				};
				break;
			case CREATE_MBMS_CONTEXT_RESPONSE:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Create MBMS Context Response");						
					case GTP:
					default:
						gtpMessage=new CreateMBMSContextResponseImpl();
						break;
				};
				break;
			case UPDATE_MBMS_CONTEXT_REQUEST:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Update MBMS Context Request");						
					case GTP:
					default:
						gtpMessage=new UpdateMBMSContextRequestImpl();
						break;
				};
				break;
			case UPDATE_MBMS_CONTEXT_RESPONSE:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Update MBMS Context Response");						
					case GTP:
					default:
						gtpMessage=new UpdateMBMSContextResponseImpl();
						break;
				};
				break;
			case DELETE_MBMS_CONTEXT_REQUEST:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Delete MBMS Context Request");						
					case GTP:
					default:
						gtpMessage=new DeleteMBMSContextRequestImpl();
						break;
				};
				break;
			case DELETE_MBMS_CONTEXT_RESPONSE:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for Delete MBMS Context Response");						
					case GTP:
					default:
						gtpMessage=new DeleteMBMSContextResponseImpl();
						break;
				};
				break;
			case MBMS_REGISTRATION_REQUEST:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for MBMS Registration Request");						
					case GTP:
					default:
						gtpMessage=new MBMSRegistrationRequestImpl();
						break;
				};
				break;
			case MBMS_REGISTRATION_RESPONSE:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for MBMS Registration Response");						
					case GTP:
					default:
						gtpMessage=new MBMSRegistrationResponseImpl();
						break;
				};
				break;
			case MBMS_DEREGISTRATION_REQUEST:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for MBMS Deregistration Request");						
					case GTP:
					default:
						gtpMessage=new MBMSDeregistrationRequestImpl();
						break;
				};
				break;
			case MBMS_DEREGISTRATION_RESPONSE:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for MBMS Deregistration Response");						
					case GTP:
					default:
						gtpMessage=new MBMSDeregistrationResponseImpl();
						break;
				};
				break;
			case MBMS_SESSION_START_REQUEST:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for MBMS Session Start Request");						
					case GTP:
					default:
						gtpMessage=new MBMSSessionStartRequestImpl();
						break;
				};
				break;
			case MBMS_SESSION_START_RESPONSE:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for MBMS Session Start Response");						
					case GTP:
					default:
						gtpMessage=new MBMSSessionStartResponseImpl();
						break;
				};
				break;
			case MBMS_SESSION_STOP_REQUEST:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for MBMS Session Stop Request");						
					case GTP:
					default:
						gtpMessage=new MBMSSessionStopRequestImpl();
						break;
				};
				break;
			case MBMS_SESSION_STOP_RESPONSE:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for MBMS Session Stop Response");						
					case GTP:
					default:
						gtpMessage=new MBMSSessionStopResponseImpl();
						break;
				};
				break;
			case MBMS_SESSION_UPDATE_REQUEST:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for MBMS Session Update Request");						
					case GTP:
					default:
						gtpMessage=new MBMSSessionUpdateRequestImpl();
						break;
				};
				break;
			case MBMS_SESSION_UPDATE_RESPONSE:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for MBMS Session Update Response");						
					case GTP:
					default:
						gtpMessage=new MBMSSessionUpdateResponseImpl();
						break;
				};
				break;
			case MS_INFO_CHANGE_NOTIFICATION_REQUEST:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for MS Info Change Notification Request");						
					case GTP:
					default:
						gtpMessage=new LocationChangeNotificationRequestImpl();
						break;
				};
				break;
			case MS_INFO_CHANGE_NOTIFICATION_RESPONSE:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for MS Info Change Notification Response");						
					case GTP:
					default:
						gtpMessage=new LocationChangeNotificationResponseImpl();
						break;
				};
				break;
			case DATA_RECORD_TRANSFER_REQUEST:
				switch(packetType)
				{
					case GTP:
						throw new GTPParseException("Invalid packet type for Data Record Transfer Request");						
					case GTP_TAG:
					default:
						gtpMessage=new DataRecordTransferRequestImpl();
						break;
				};
			case DATA_RECORD_TRANSFER_RESPONSE:
				switch(packetType)
				{
					case GTP:
						throw new GTPParseException("Invalid packet type for Data Record Transfer Response");						
					case GTP_TAG:
					default:
						gtpMessage=new DataRecordTransferResponseImpl();
						break;
				};
				break;
			case END_MARKER:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for End Marker");						
					case GTP:
					default:
						gtpMessage=new EndMarkerImpl();
						break;
				};
				break;
			case G_PDU:
				switch(packetType)
				{
					case GTP_TAG:
						throw new GTPParseException("Invalid packet type for GPDU");						
					case GTP:
					default:
						gtpMessage=new GTPUMessage();
						break;
				};
				break;
			case UNKNOWN:
				return null;
		}
		
		gtpMessage.applyHeaderByte((byte)(header & 0x0F));
		gtpMessage.decode(buffer,ignoreUnknown);
		return gtpMessage;
	}
}
