package io;

import java.util.Scanner;

import action.ActionBuilder;
import payload.AbstractAction;
import payload.AbstractTargetedAction;
import user.User;
import user.UserManager;

public class InputHandler implements Runnable{

	public void run() {
		Scanner scanner = new Scanner(System.in);
		
		boolean keepAlive = true;
		
		OutputHandler.log("Type in the desired action (or 'exit'): ");
		
		while(keepAlive){
			if(scanner.hasNext()){
				keepAlive = readInput(scanner.nextLine());
			}
		}
	}
	
	public boolean readInput(String input){
		if(input.equals("exit")){
			OutputHandler.log("Application terminated");
			
			return false;
		}
		
		OutputHandler.log("User input: " + input);
		
		User user = UserManager.getUserAtIndex(0);
		
		try {
			OutputHandler.log("Sending message: '" + input + "' to " + user.getAddress());
			AbstractAction action = ActionBuilder.buildFromJson(input);
			
			if(action.getClass().getName().equals("payload.WhisperAction")){
				AbstractTargetedAction targetedAction = (AbstractTargetedAction) action;
				targetedAction.setTargetIp(user.getAddress());
				targetedAction.send();
			}else{
				action.send();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

}
