package com.mobius.software.telco.protocols.gtp.impl;
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
import java.net.InetSocketAddress;

import org.apache.log4j.Logger;

import com.mobius.software.telco.protocols.gtp.api.messages.GenericGTPMessage;
import com.mobius.software.telco.protocols.gtp.impl.messages.MessageFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.util.ReferenceCountUtil;

@Sharable
public class GTPPacketHandler extends SimpleChannelInboundHandler<DatagramPacket>
{
	private final static Logger logger = Logger.getLogger(GTPPacketHandler.class);  
	
	private GTPServerImpl server;
	
	public GTPPacketHandler(GTPServerImpl server)
	{
		this.server=server;
	}
	
	@Override
	public void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) 
	{
		try
		{			
			try
			{
				InetSocketAddress address = packet.sender();
				GenericGTPMessage message=MessageFactory.decode(packet.content(),server.getIgnoreUnknown());
				server.packetReceived(message, address);
			}
			catch(Exception ex)
			{
				logger.error("An error occured while processing incoming packet," + ex.getMessage(),ex);
			}
		}
		finally
		{
			ReferenceCountUtil.release(packet);			
		}
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) 
	{
		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) 
	{
		
	}
}