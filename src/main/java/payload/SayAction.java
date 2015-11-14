package payload;

import com.fasterxml.jackson.core.JsonProcessingException;

import exception.UnknownUserException;
import io.OutputHandler;
import main.Application;
import network.Client;
import user.User;
import user.UserManager;

public class SayAction extends AbstractAction {
	public String target;
	public String content;
	
	@Override
	public void receive(String senderIp) throws UnknownUserException  {
		User user = UserManager.findByAddress(senderIp);
		
		if(user == null){
			throw new UnknownUserException();
		}
		
		if(target == null){
			OutputHandler.out(user.getNickname() + " diz à todos: " + content);
		}else{
			User targetUser;
			
			if(target == Application.getIp()){
				targetUser = new User("", "VOCÊ");
			}else{
				targetUser = UserManager.findByAddress(target);
			}
			
			if(targetUser == null){
				throw new UnknownUserException();
			}
			
			OutputHandler.out(user.getNickname() + " diz à "+ targetUser.getNickname() +": " + content);
		}
	}
	
	@Override
	public void send() throws JsonProcessingException{
		Client.sendMulticastMessage(toJson());		
	}

}
