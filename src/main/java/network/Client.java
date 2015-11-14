package network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import payload.AbstractAction;
import user.User;

public class Client {
	public static void main(String args[]) throws JsonProcessingException{
		User user = new User("192.168.0.1","Joao");
		ObjectMapper mapper = new ObjectMapper();
		
		
		System.out.println(mapper.writeValueAsString(user));
	}
	
	public static void sendBroadcastMessage(AbstractAction action){
		
	}
	
	public static void sendMulticastMessage(AbstractAction action){
		
	}
	
	public static void sendMessageToIp(AbstractAction action){
		sendMessage(action, "");
	}
	
	private static void sendMessage(AbstractAction action, String ip){
		try {
			Socket socket = new Socket(ip, 9000);
			
			DataOutputStream output = new DataOutputStream(socket.getOutputStream());
			output.writeUTF(action.send());
			
			output.close();
			
			socket.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
