package com.mobius.software.telco.protocols.gtp.impl.messages;

import static org.junit.Assert.assertTrue;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.pkts.PacketHandler;
import io.pkts.Pcap;
import io.pkts.buffer.Buffer;
import io.pkts.packet.Packet;
import io.pkts.packet.UDPPacket;
import io.pkts.protocol.Protocol;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

import com.mobius.software.telco.protocols.gtp.api.messages.GenericGTPMessage;
import com.mobius.software.telco.protocols.gtp.api.utils.StringFunctions;

public class EncodingTest 
{
	@Test
	public void testEncodingDecoding() throws FileNotFoundException, IOException
	{
		File folder = new File(this.getClass().getClassLoader().getResource("samples").getFile());
		File[] listOfFiles = folder.listFiles();
		for(File currFile:listOfFiles)
		{
			System.out.println("FILE:" + currFile.getAbsolutePath());
			Pcap pcap = Pcap.openStream(currFile.getAbsoluteFile());
			pcap.loop(new PacketHandler() 
			{
				@Override
				public boolean nextPacket(Packet packet) throws IOException 
				{
					if (packet.hasProtocol(Protocol.UDP)) 
					{
						UDPPacket udpPacket = (UDPPacket) packet.getPacket(Protocol.UDP);
						Buffer buffer = udpPacket.getPayload();						
	                    if (buffer != null) 
	                    {
	                    	byte[] original=buffer.getArray();
	                    	ByteBuf nettyBuffer=Unpooled.wrappedBuffer(original);
	                    	byte[] destination=new byte[original.length];
	                    	ByteBuf destBuffer=Unpooled.wrappedBuffer(destination);
	                        destBuffer.resetWriterIndex();
	                        GenericGTPMessage message=MessageFactory.decode(nettyBuffer);
	                        message.encode(destBuffer);
	                        byte[] destination2=new byte[original.length];
	                    	ByteBuf dest2Buffer=Unpooled.wrappedBuffer(destination2);
	                        dest2Buffer.resetWriterIndex();
	                        message.encodeOriginal(dest2Buffer);
	                        
	                        Boolean isRegularEqual=Arrays.equals(original, destination);
	                        Boolean isOriginalEqual=Arrays.equals(original, destination2);
	                        
	                        System.out.println("PACKET TYPE:" + message.getClass().getCanonicalName());
	                        System.out.println("Original    :" + StringFunctions.bytesToHex(original));
	                        System.out.println("Destination :" + StringFunctions.bytesToHex(destination));
	                        System.out.println("Destination2:" + StringFunctions.bytesToHex(destination2));
	                        if(!isRegularEqual && isOriginalEqual)
	                        	System.out.println("Packet TLVs are received in invalid order");
	                        
	                    	assertTrue(isOriginalEqual || isRegularEqual);
	                    }
	                }
	                return true;
				}
			});
		}

		//those that are gtp read , try to decode 
		//then encode back
		//and compare byte array		
	}
}
