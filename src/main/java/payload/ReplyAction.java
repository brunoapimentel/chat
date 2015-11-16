package payload;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.OutputHandler;
import network.Client;
import user.User;
import user.UserManager;

public class ReplyAction extends AbstractTargetedAction {

	public String nickname;

	@Override
	public void receive(String senderIp){
		User user = new User(senderIp, nickname);

		if (UserManager.insert(user)) {
			OutputHandler.out(user.getNickname() + " entrou na sala");
		}

	}
	
	@Override
	public void send() throws JsonProcessingException {
		Client.sendMessageToIp(targetIp, toJson());
	}
}
