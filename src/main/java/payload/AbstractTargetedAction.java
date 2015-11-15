package payload;

import com.fasterxml.jackson.core.JsonProcessingException;

import network.Client;

public abstract class AbstractTargetedAction extends AbstractAction {
	protected String targetIp;
	
	public AbstractTargetedAction() {
		super();
	}
	
	public AbstractTargetedAction(String targetIp) {
		super();
		this.targetIp = targetIp;
	}
	
	@Override
	public void send() throws JsonProcessingException {
		Client.sendMessageToIp(targetIp, toJson());
	}
	
	public void setTargetIp(String targetIp){
		this.targetIp = targetIp;
	}
}
