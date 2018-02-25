package it.eng.unipa.projectwork.telegram.impl;

import it.eng.unipa.projectwork.telegram.SendMessageTelegram;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class SendMessageTelegramImpl extends TelegramLongPollingBot implements SendMessageTelegram{

	
	
	
	

	@Override
	public String getBotUsername() {

		return"AsteOnlineEngBot";
	}

	@Override
	public void onUpdateReceived(Update update) 
	{

		if (update.hasMessage() && update.getMessage().hasText()) 
		{

		    if (update.getMessage().getText().equals("/start")) 
			{
		    	 long chatIdTelegram= update.getMessage().getChat().getId();
		    	 String telegramUsername = update.getMessage().getChat().getUserName();
		 
		    	 System.out.println("Username: " + telegramUsername);
		    	 
		    	 SendMessage welcomeMessage = new SendMessage();
		    	 welcomeMessage.setChatId(chatIdTelegram)
		    	 .setText("Benvenuto su AsteOnline Spa ");
		    	 
		    	 try
		    	 {
		    		 sendMessage(welcomeMessage);
		    	 }
		    	 catch(TelegramApiException e)
		    	 {
		    		 e.printStackTrace();
		    	 }
		    }
		}
	}
		

	

	@Override
	public String getBotToken() {
	
		return "540859545:AAG79hoC16EZbYD4imqErquAiqM5Ih2kKuU";
	}

	
	@Override
	public void sendMessageTelegram() {
		// TODO Auto-generated method stub
		
	}
	

}
