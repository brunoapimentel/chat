package io;

import java.util.Scanner;

import action.ActionBuilder;
import payload.AbstractAction;
import user.User;
import user.UserManager;

public class InputHandler implements Runnable {

	public void run() {
		Scanner scanner = new Scanner(System.in);

		boolean keepAlive = true;

		OutputHandler.log("Type in the desired action (or 'exit'): ");

		while (keepAlive) {
			if (scanner.hasNext()) {
				keepAlive = readInput(scanner.nextLine());
			}
		}
		
		scanner.close();
	}

	public boolean readInput(String input) {
		if (input.equals("exit")) {
			OutputHandler.log("Application terminated");

			return false;
		}

		OutputHandler.log("User input: " + input);
		
		String[] parts = input.split("|");

		User user = UserManager.findByAddress(parts[0]);

		if(user == null){
			OutputHandler.log("Usuário não encontrado. A mensagem não será enviada");
		}
		
		try {
			OutputHandler.log("Sending message: '" + parts[1] + "' to " + user.getAddress());
			AbstractAction action = ActionBuilder.buildFromJson(input);

			if (action.getClass().getName().equals("payload.WhisperAction")) {
				action.setTargetIp(user.getAddress());
				action.send();
			}
			
			action.send();

		} catch (Exception e) {
			OutputHandler.error("Um erro ocorreu", e);
		}

		return true;
	}

}
