package org.projectwork.channel.telegram;


import it.eng.unipa.projectwork.channel.AbstractChannel;
import it.eng.unipa.projectwork.channel.event.AuctionEvent;


public class TelegramChannel extends AbstractChannel{


	public TelegramChannel(String username, String chatId) {
		super(username, chatId);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void sendAuctionEvent(AuctionEvent auctionEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}
	
}