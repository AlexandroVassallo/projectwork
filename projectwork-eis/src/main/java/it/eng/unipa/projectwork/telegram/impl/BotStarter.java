package it.eng.unipa.projectwork.telegram.impl;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class BotStarter{
	public static void main(String[] args)
	{
		//inizializziamo le api
				ApiContextInitializer.init();
				//creazione dell oggetto telegrambotsapi
				TelegramBotsApi botsApi= new TelegramBotsApi();
				
				//registrazione bot
				try
				{
					botsApi.registerBot(new SendMessageTelegramImpl());
				}
				catch(TelegramApiException e)
				{
					e.printStackTrace();
				}
					
	}
	

}
