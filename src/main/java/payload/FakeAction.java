package payload;

import com.fasterxml.jackson.core.JsonProcessingException;

import exception.UnknownUserException;

public class FakeAction extends AbstractAction{


	@Override
	public void receive(String senderIp) throws UnknownUserException {
		
		
	}

	@Override
	public void send() throws JsonProcessingException {

	}

}
