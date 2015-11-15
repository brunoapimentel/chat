package main;

import com.fasterxml.jackson.core.JsonProcessingException;

import automated.Robot;
import automated.SearchController;
import io.InputHandler;
import io.OutputHandler;
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
		
		if(! Config.init(args) ){
			return;
		}
		
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
		return Config.ip;
	}
	
	public static String getBroadcastIp(){
		String[] ipParts = Application.getIp().split("\\.");
		
		return ipParts[0] + "." + ipParts[1] + "." + ipParts[2] + ".255";
	}
	
	public static int getLogLevel(){
		return Config.logLevel;
	}
	
	public static class Config{
		private static String ip;
		private static int port;
		private static String nickname;
		private static int logLevel;
		
		private static boolean init(String[] args){

			if(args.length >= 1){
				Config.ip = args[0];
			}else{
				OutputHandler.log("É necessário informar um ip");
				return false;
			}
			
			if(args.length >= 2){
				Config.nickname = args[1];
			}else{
				Config.nickname = "Vader";
			}
			
			if(args.length >= 3){
				Config.logLevel = Integer.parseInt(args[2]);
			}else{
				Config.logLevel = OutputHandler.VERBOSE;
			}
			
			if(args.length >= 4){
				Config.port = Integer.parseInt(args[3]);
			}else{
				Config.port = 9000;
			}
			
			return true;
		}
		
	}
}
