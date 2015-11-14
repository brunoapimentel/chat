package chat;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import junit.framework.TestCase;
import junit.framework.TestResult;
import payload.WhisperAction;
import user.User;
import user.UserManager;

public class ActionTest extends TestCase{
	private WhisperAction whisperAction;
	
	@Override
	protected void setUp() throws Exception {
		setUpUsers();
		setUpActions();
	}
	
	private void setUpUsers(){
		User user;
		
		user = new User("192.168.0.2", "Vader");
		
		UserManager.replace(user);
	}
	
	private void setUpActions(){
		User vader = UserManager.findByAddress("192.168.0.2");
		
		whisperAction = new WhisperAction();
		whisperAction.address = vader.getAddress();
		whisperAction.content = "Greetings!";
	}
	
	@Test
	public void testSend() throws JsonProcessingException{
		assertEquals("{\"action\":\"whisper\",\"address\":\"192.168.0.2\",\"content\":\"Greetings!\"}", whisperAction.send());
	
	}
	
	@Test
	private void testReceive(){
		//assertEquals("", whisperAction.receive());
	}
}
