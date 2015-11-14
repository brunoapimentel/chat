package payload;

import exception.UnknownUserException;
import io.OutputHandler;
import user.User;
import user.UserManager;

public class ReportAction extends AbstractTargetedAction {
	public String message;
	
	@Override
	public void receive(String senderIp) throws UnknownUserException {
		User user = UserManager.findByAddress(senderIp);
		
		if(user == null){
			throw new UnknownUserException();
		}

		OutputHandler.out(user.getNickname() + " reportou um erro: " + message);	
	}

}
