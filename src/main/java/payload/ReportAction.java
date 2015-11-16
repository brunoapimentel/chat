package payload;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.OutputHandler;
import main.Application;
import network.Client;
import user.UnknownUser;
import user.User;
import user.UserManager;

public class ReportAction extends AbstractTargetedAction {
	public String message;
	
	@Override
	public void receive(String senderIp){
		User user = UserManager.findByAddress(senderIp);
		
		if(user == null){
			user = new UnknownUser(senderIp);
		}

		OutputHandler.out(user.getNickname() + " reportou um erro: " + message);	
	}

	@Override
	public void send() throws JsonProcessingException {
		Client.sendMessageToIp(targetIp, toJson());
		
		OutputHandler.out(Application.getUser().getNickname() + " reportou um erro: " + message);
	}
	
}
