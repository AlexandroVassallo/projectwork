package org.projectwork.channel.telegram;


import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import it.eng.unipa.projectwork.channel.AbstractChannelContainer;
import org.projectwork.channel.telegram.TelegramChannel;
import it.eng.unipa.projectwork.email.SendMail;
import it.eng.unipa.projectwork.model.User;
import it.eng.unipa.projectwork.service.UserService;
import it.eng.unipa.projectwork.telegram.SendMessageTelegram;


@Singleton
@Startup
@DependsOn(value="MultiChannelContainer")
public class TelegramChannelContainer extends AbstractChannelContainer<TelegramChannel>
{

	
	@EJB
	SendMessageTelegram sendMessage;
	@EJB
	UserService userService;
	
	@Override
	public void add(String username, Long auctionOid) {
		User user = userService.getUser(username);
		String email = user.getEmail();
		String chat_id=user.getChatId();
		
		super.add(new TelegramChannel(username, chat_id));
		super.add(username, auctionOid);
	}
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}
	
}