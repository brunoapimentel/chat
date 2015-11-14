package payload;

import com.fasterxml.jackson.core.JsonProcessingException;

import iohandler.OutputHandler;
import user.User;
import user.UserManager;

public class LeaveAction extends AbstractAction {

	public String address;
	
	@Override
	public void receive() {
		User user = UserManager.findByAddress(address);
		
		UserManager.removeByAddress(address);
		
		OutputHandler.out(user.getNickname() + " saiu da sala.");
	}

	@Override
	public String send() throws JsonProcessingException {
		return toJson();
	}

}
