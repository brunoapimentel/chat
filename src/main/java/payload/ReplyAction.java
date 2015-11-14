package payload;

import com.fasterxml.jackson.core.JsonProcessingException;

import iohandler.OutputHandler;
import user.User;
import user.UserManager;

public class ReplyAction extends AbstractAction{
	public String address;
	public String nickname;
	
	@Override
	public void receive() {
		if(UserManager.findByAddress(address) != null){
			User user = new User(address, nickname);
			UserManager.replace(user);
			
			OutputHandler.out(user.getNickname() + " entrou na sala");
		}
		
	}

	@Override
	public String send() throws JsonProcessingException {
		return toJson();
	}
	
}
