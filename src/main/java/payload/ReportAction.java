package payload;

import iohandler.OutputHandler;
import user.User;
import user.UserManager;

public class ReportAction extends AbstractAction {
	public String message;

	@Override
	public void receive() {
		User user = UserManager.findByAddress(senderAddress);

		OutputHandler.out(user.getNickname() + " reportou um erro: " + message);

	}

}
