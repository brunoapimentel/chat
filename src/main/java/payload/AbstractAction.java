package payload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import exception.UnknownUserException;

public abstract class AbstractAction {
	public String action;
	
	public AbstractAction() {
		action = this.getClass().getName().replace("Action", "").replace("payload.", "").toLowerCase();
	}
	
	public abstract void receive(String senderIp) throws UnknownUserException;
	
	public abstract void send() throws JsonProcessingException;
	
	protected String toJson() throws JsonProcessingException{
		return new ObjectMapper().writeValueAsString(this);
	}
}
