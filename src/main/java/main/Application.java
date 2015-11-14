package main;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.fasterxml.jackson.core.JsonProcessingException;

import automated.Robot;
import automated.SearchController;
import io.InputHandler;
import network.Server;

public class Application {
	
	private static Thread client;
	private static Thread server;
	private static Thread searcher;
	
	
	public static void main(String args[]) throws JsonProcessingException{
		//client = new Thread(new InputHandler());
		client = new Thread(new Robot());
		server = new Thread(new Server());
		searcher = new Thread(new SearchController());
		
		Config.init(args);
		
		client.start();
		server.start();
		searcher.start();
	}
	
	public static void halt(){
		client.interrupt();
		server.interrupt();
		searcher.interrupt();
		
		System.err.println("Application halted!");
	}
	
	
	public static int getPort(){
		return Config.port;
	}
	
	public static String getNickname(){
		return Config.nickname;
	}
	
	public static String getIp(){
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			halt();
		}
		return null;
	}
	
	public static class Config{
		private static int port;
		private static String nickname;
		
		private static void init(String[] args){
			if(args.length == 1){
				Config.port = Integer.parseInt(args[0]);
				Config.nickname = "Vader";
			}
			
			if(Config.port == 0){
				Config.port = 9000;
			}
			
			if(Config.nickname == null){
				Config.nickname = "Vader";
			}
		}
		
	}
}
