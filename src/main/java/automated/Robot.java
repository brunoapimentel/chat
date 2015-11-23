package automated;

import java.util.concurrent.TimeUnit;

import action.ActionBuilder;
import io.OutputHandler;
import main.Application;
import payload.AbstractAction;
import user.User;
import user.UserManager;

public class Robot implements Runnable {
	
	private String[] messages = {
		"{\"action\":\"whisper\",\"content\":\"Olá gatinha\",\"target\":\"RANDOM\"}!",
		"{\"action\":\"say\",\"content\":\"Sinta o poder do lado negro da Força!\"}!",
		"{\"action\":\"say\",\"content\":\"A Força é poderosa em você!\",\"target\":\"RANDOM\"}!",
		"{\"action\":\"whisper\",\"content\":\"Eu acho sua falta de fé algo perturbador!\",\"target\":\"RANDOM\"}!",
		"{\"action\":\"whisper\",\"content\":\"Vai me deixar no vácuo mesmo?\",\"target\":\"RANDOM\"}!",
		"{\"action\":\"say\",\"content\":\"Preciso lustrar a armadura...\"}!",
		"{\"action\":\"say\",\"content\":\"Que sala mais tediosa...\"}!",
		"{\"action\":\"say\",\"content\":\"As multas da Estrela da Morte já passaram de R$ 2.000,00!\",\"target\":\"RANDOM\"}!",
		"{\"action\":\"say\",\"content\":\"Zuom! *ativa o sabre de luz*\"}!",
		"{\"action\":\"say\",\"content\":\"Não... Eu sou seu pai!\", \"target\":\"RANDOM\"}!",
		"{\"action\":\"say\",\"content\":\"Daqui a pouco tô saindo... Jogar um Battlefront :)\", \"target\":\"RANDOM\"}!",
		"{\"action\":\"say\",\"content\":\"Consegue imaginar porque eu nunca como feijão?\", \"target\":\"RANDOM\"}!",
	};
	
	private String getRandomAction(){
		int random = getRandomNumber(messages.length) - 1;
		
		if(messages[random] != null){
			return messages[random];
		}
		
		return getRandomAction();
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

		while(true){
			user = getRandomUser();
			
			if(user != null){
				String json = getRandomAction();
				
				json = json.replace("RANDOM", user.getAddress());
				
				try {
					AbstractAction action = ActionBuilder.buildFromJson(json);
					
					if(action.getClass().getName().equals("payload.WhisperAction")){
						action.setTargetIp(user.getAddress());
					}
					
					action.send();
					
					
				} catch (Exception e) {
					OutputHandler.error("Um erro ocorreu", e);
				}
			}
			
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				OutputHandler.error("Um erro ocorreu", e);
				Application.halt();
			}
		}
		
	}
}
