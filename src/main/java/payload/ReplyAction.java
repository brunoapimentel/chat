package payload;

import io.OutputHandler;
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
}
