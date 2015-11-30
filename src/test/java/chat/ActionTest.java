package chat;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import action.ActionBuilder;
import junit.framework.TestCase;
import payload.LeaveAction;
import user.User;
import user.UserManager;

public class ActionTest extends TestCase{
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		User luke = new User("192.168.0.10","Luke");
		UserManager.insert(luke);
		
		User r2d2 = new User("192.168.0.11","R2D2");
		UserManager.insert(r2d2);
	}
	
	public void testLeave() throws JsonParseException, JsonMappingException, IOException{
		LeaveAction action = (LeaveAction) ActionBuilder.buildFromJson("{\"action\":\"leave\"}");
		action.receive("192.168.0.11");
		
		assertNull(UserManager.findByAddress("192.168.0.11"));
	}

}
