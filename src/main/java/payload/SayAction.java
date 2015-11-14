package payload;

import com.fasterxml.jackson.core.JsonProcessingException;

public class SayAction extends AbstractAction {
	public String target;
	public String content;
	
	@Override
	public void receive() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String send() throws JsonProcessingException {
		return toJson();
		
	}
	
	
}
