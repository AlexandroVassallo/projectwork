/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package it.eng.unipa.projectwork.web.websocket;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import it.eng.unipa.projectwork.channel.websocket.WSChannel;
import it.eng.unipa.projectwork.channel.websocket.WSChannelContainer;

@Stateless
@ServerEndpoint("/websocket/auction")
public class AuctionWebSocket {

	@EJB
	WSChannelContainer wsChannelContainer;
	
	
    @OnMessage
    public String sayHello(String name) {
        System.out.println("Say hello to '" + name + "'");
        return ("Hello " + name + " from websocket endpoint");
    }

    @OnOpen
    public void helloOnOpen(Session session) {
    	String username = "giacompa";
    	if(session.getUserPrincipal()!=null){
    		username = session.getUserPrincipal().getName();
    	}
        System.out.println("WebSocket opened: " + session.getId());
        System.out.println(wsChannelContainer);
        wsChannelContainer.add(new WSChannel(session, username));
    }
    
    @OnClose
    public void helloOnClose(CloseReason reason) {
        System.out.println("Closing a WebSocket due to " + reason.getReasonPhrase());
    }
}
