package chat;

import junit.framework.TestCase;
import user.User;
import user.UserManager;

public class UserManagerTest extends TestCase {
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		User luke = new User("192.168.0.10", "Luke");
		UserManager.insert(luke);

		User r2d2 = new User("192.168.0.11", "R2D2");
		UserManager.insert(r2d2);
	}
	
	public void testTimestamp(){
		User user = UserManager.findByAddress("192.168.0.10");
		
		long timestamp = user.getTimestamp();
		
		System.out.println("Insert timestamp: " + timestamp);
		
		UserManager.updateTimestamp("192.168.0.10");
		
		user = UserManager.findByAddress("192.168.0.10");
		
		System.out.println("New timestamp: " + user.getTimestamp());
		
		assertFalse(user.getTimestamp() == timestamp);
	}
}
