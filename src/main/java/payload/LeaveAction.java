package payload;

import com.fasterxml.jackson.core.JsonProcessingException;

import exception.UnknownUserException;
import io.OutputHandler;
import network.Client;
import user.User;
import user.UserManager;

public class LeaveAction extends AbstractAction {

	public String address;
	
	@Override
	public void receive(String senderIp) throws UnknownUserException {
		User user = UserManager.findByAddress(senderIp);
		
		if(user == null){
			throw new UnknownUserException();
		}
		
		UserManager.removeByAddress(senderIp);
		
		OutputHandler.out(user.getNickname() + " saiu da sala.");
	}

	@Override
	public void send() throws JsonProcessingException {
		Client.sendMulticastMessage(toJson());
	}

}
