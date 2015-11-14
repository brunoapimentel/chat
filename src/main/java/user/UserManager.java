package user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserManager {
	private static List<User> users = new ArrayList<User>();
	
	public static List<User> getList(){
		return users;
	}
	
	public static void replace(User user){
		removeByAddress(user.getAddress());
		
		users.add(user);
	}
	
	public static User findByAddress(String address){
		Iterator<User> i = users.iterator();
		
		while(i.hasNext()){
			User user = i.next();
			
			if(user.getAddress().equals(address)){
				return user;
			}
		}
		
		return null;
	}
	
	public static boolean removeByAddress(String address){
		User user = findByAddress(address);
		
		if(user != null){
			users.remove(user);
			return true;
		}
		
		return false;
	}
}
