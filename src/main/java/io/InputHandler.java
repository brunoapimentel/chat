package io;

import java.util.Scanner;

import action.ActionBuilder;

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
		
		String[] args = input.split(" && ");
		
		try {
			ActionBuilder.buildFromJson(args[0]).send();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

}
