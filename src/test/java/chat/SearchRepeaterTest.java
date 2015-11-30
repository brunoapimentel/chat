package chat;

import automated.SearchRepeater;
import junit.framework.TestCase;
import main.Application;
import user.User;
import user.UserManager;

public class SearchRepeaterTest extends TestCase {
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		User luke = new User("192.168.0.10", "Luke");
		UserManager.insert(luke);
		
		Application.initConfigForTests();
	}
	
	public void testRemoveIdleUsers() throws InterruptedException{
		Thread searcher = new Thread(new SearchRepeater());

		searcher.start();
		
		long startTimestamp = System.currentTimeMillis();
		
		while(System.currentTimeMillis() < startTimestamp + SearchRepeater.MAX_IDLE_TIME + 1000){
			
		}
		
		assertTrue(UserManager.listIsEmpty());
	}
}
