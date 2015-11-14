package automated;

import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.OutputHandler;
import main.Application;
import payload.SayAction;
import user.User;
import user.UserManager;

public class Robot implements Runnable {
	
	private String[] messages = {
		"Olá gatinha!",
		"Como você é tchuca!",
		"Ooo lá em casa hein?"
	};
	
	private String getRandomMessage(){
		int random = getRandomNumber(messages.length) - 1;
		
		if(messages[random] != null){
			return messages[random];
		}
		
		return getRandomMessage();
	}
	
	private User getRandomUser(){
		if(UserManager.listIsEmpty()){
			return null;
		}
		
		int random = getRandomNumber(UserManager.getNumberOfUsers()) - 1;
		
		return UserManager.getUserAtIndex(random);
	}
	
	private int getRandomNumber(int max){
		return (int) Math.ceil((Math.random() * max * max) / max);
	}

	public void run() {
		User user;
		SayAction action = new SayAction();
		
		while(true){
			user = getRandomUser();
			
			if(user != null){
				action.target = user.getAddress();
				action.content = getRandomMessage();
				
				try {
					OutputHandler.log("Sending message: '" + getRandomMessage() + "' to " + user.getAddress());
					action.send();
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
				Application.halt();
			}
		}
		
	}
}
