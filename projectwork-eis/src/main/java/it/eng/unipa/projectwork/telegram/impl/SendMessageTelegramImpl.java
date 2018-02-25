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
	
		
			if (update.hasMessage() && update.getMessage().hasText()) {
		
				   //splittiamo la striga per gestirla
					String[] messaggioInviato=update.getMessage().getText().split(" ");
					
					//Inizializzazione bot
				    if (update.getMessage().getText().equals("/start")) {
				    	
				    	 long chatIdTelegram= update.getMessage().getChat().getId();
				    	// String telegramUsername = update.getMessage().getChat().getUserName();
				    //	 System.out.println("Username: " + telegramUsername);
				    
				    	 //creo il messaggio di risposta
				    	 SendMessage welcomeMessage = new SendMessage();
				    	 welcomeMessage.setChatId(chatIdTelegram)
				    	 .setText("Benvenuto su AsteOnline Spa \nUtilizza il comando /username per associare il tuo account AsteOline!!"+ chatIdTelegram);
				    	
				    	 try
				    	 {
				    		 sendMessage(welcomeMessage);
				    	 }
				    	 catch(TelegramApiException e)
				    	 {
				    		 e.printStackTrace();
				    	 }
				    	 
				 
				    }//fine inizializzazione bot
				    
				    
				    //associazione utente/chatid, popoliamo il db
				    	if (messaggioInviato[0].equals("/username"))
				    {
				    	
					    	long chatIdTelegram= update.getMessage().getChat().getId();
					    	String telegramUsername = update.getMessage().getChat().getUserName();
					    	String usernameUtenteAsteOnline=messaggioInviato[1];
					    	
					    	
					    	 System.out.println("Username sul sito: " + usernameUtenteAsteOnline+"Chat ID: "+ chatIdTelegram);
					    
					    	 //TODO Inserire nel DB associazione UtenteAste con ChatId
					
				    	 
					    	 SendMessage associazioneMessage = new SendMessage();
					    	 associazioneMessage.setChatId(chatIdTelegram)
					    	 .setText("Ciao "+usernameUtenteAsteOnline+"!!"+" Hai associato il tuo account correttamente!!!");
					    	 
					    	 try
					    	 {
					    		 sendMessage(associazioneMessage);
					    	 }
					    	 catch(TelegramApiException e)
					    	 {
					    		 e.printStackTrace();
					    	 }
				    }//fine associazione
				   	
				    //rilancia bot
				    if (messaggioInviato[0].equals("/rilancia")) {
				    	
				    	long chatIdTelegram= update.getMessage().getChat().getId();
				    	
				    	 String telegramUsername = update.getMessage().getChat().getUserName();
				    	 
				    	 String auctionRilanciata=messaggioInviato[1];
				    	 String prezzoRilanciato=messaggioInviato[2];
				    	
				    	 System.out.println("Username: " + telegramUsername);
				    	 System.out.print("Chat ID: "+ chatIdTelegram);
				    	 System.out.print(" Auction rilanciata: "+ auctionRilanciata);
				    	 System.out.print(" Prezzo di rilancio"+ prezzoRilanciato+ " euro" );
				    	 
				    	 SendMessage rilanciaMessage = new SendMessage();
				    	 rilanciaMessage.setChatId(chatIdTelegram)
				    	 .setText("Hai rilanciato: " + prezzoRilanciato+" euro per l auction: "+ auctionRilanciata);
				    	 
				    	 try
				    	 {
				    		 sendMessage(rilanciaMessage);
				    	 }
				    	 catch(TelegramApiException e)
				    	 {
				    		 e.printStackTrace();
				    	 }
				    }//fine rilanciabot
	    
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
