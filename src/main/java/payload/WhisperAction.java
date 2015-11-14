package payload;

import exception.UnknownUserException;
import io.OutputHandler;
import main.Application;
import user.User;
import user.UserManager;

public class WhisperAction extends AbstractTargetedAction{

	public String content;
	
	@Override
	public void receive(String senderIp) throws UnknownUserException {
		User user = UserManager.findByAddress(senderIp);
		
		if(user == null){
			throw new UnknownUserException();
		}
		
		OutputHandler.out(user.getNickname() + " sussura para " + Application.getNickname() + ": " + content);
	}
}
