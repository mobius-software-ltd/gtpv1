package com.mobius.software.telco.protocols.gtp.api;
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

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.messages.GTPMessage;
import com.mobius.software.telco.protocols.gtp.api.messages.GTPTagMessage;

public interface GTPServer 
{
	void sendMessage(GTPMessage message,InetSocketAddress address) throws GTPParseException;
	
	void sendMessage(GTPTagMessage message,InetSocketAddress address) throws GTPParseException;
	
	void start(String host,Integer port,Integer poolSize,Boolean useEpoll);
	
	void stop();
	
	void setListener(GTPListener listener);
	
	void setIgnoreUnknown(Boolean ignoreUnknown);
}
