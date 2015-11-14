package payload;

import iohandler.OutputHandler;
import network.Server;
import user.User;
import user.UserManager;

public class WhisperAction extends AbstractAction{
	public String address;
	public String content;
	
	@Override
	public void receive() {
		User user = UserManager.findByAddress(address);
		
		//TODO tratar user not found
		
		OutputHandler.out(user.getNickname() + " sussura para " + Server.getNickname() + ": " + content);
	}
	
}
