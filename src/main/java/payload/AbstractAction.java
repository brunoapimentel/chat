package payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractAction {
	public String action;
	
	@JsonIgnore
	public String senderAddress;
	
	public AbstractAction() {
		action = this.getClass().getName().replace("Action", "").replace("payload.", "").toLowerCase();
	}
	
	public abstract void receive();
	
	public String send() throws JsonProcessingException{
		return toJson();
	};
	
	protected String toJson() throws JsonProcessingException{
		return new ObjectMapper().writeValueAsString(this);
	}
}
