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
	
	public static void sendBroadcastMessage(String json){
		
	}
	
	public static void sendMulticastMessage(String json){
		
	}
	
	public static void sendMessageToIp(String ip, String json){
		try {
			Socket socket = new Socket(ip, 9000);
			
			DataOutputStream output = new DataOutputStream(socket.getOutputStream());
			output.writeUTF(json);
			
			output.close();
			
			socket.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
