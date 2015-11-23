package main;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import com.fasterxml.jackson.core.JsonProcessingException;

import automated.Robot;
import automated.SearchRepeater;
import io.OutputHandler;
import network.Server;
import user.User;

public class Application {

	private static Thread client;
	private static Thread server;
	private static Thread searcher;

	public static void main(String args[]) throws JsonProcessingException {
		//client = new Thread(new InputHandler());
		 client = new Thread(new Robot());
		server = new Thread(new Server());
		searcher = new Thread(new SearchRepeater());

		if (!Config.init(args)) {
			return;
		}

		client.start();
		server.start();
		searcher.start();
	}

	public static void halt() {
		client.interrupt();
		server.interrupt();
		searcher.interrupt();

		System.err.println("Application halted!");
	}

	public static int getPort() {
		return Config.port;
	}

	public static User getUser() {
		return Config.user;
	}

	public static String getBroadcastIp() {
		if (Config.broadcastAddress == null) {
			try {
				Config.broadcastAddress = Config.findBroadcastAddress();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		return Config.broadcastAddress;
	}

	public static int getLogLevel() {
		return Config.logLevel;
	}

	public static class Config {
		private static User user;
		private static int port;
		private static int logLevel;
		private static String broadcastAddress;

		private static boolean init(String[] args) {

			Config.user = new User();

			if (args.length >= 1) {
				Config.user.setAddress(args[0]);
			} else {
				OutputHandler.log("É necessário informar um ip");
				return false;
			}

			if (args.length >= 2) {
				Config.user.setNickname(args[1]);
			} else {
				Config.user.setNickname("Vader");
			}

			if (args.length >= 3) {
				Config.logLevel = Integer.parseInt(args[2]);
			} else {
				Config.logLevel = OutputHandler.VERBOSE;
			}

			if (args.length >= 4) {
				Config.port = Integer.parseInt(args[3]);
			} else {
				Config.port = 9000;
			}

			return true;
		}

		private static String findBroadcastAddress() throws SocketException {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			
			while (interfaces.hasMoreElements()) {
				NetworkInterface networkInterface = interfaces.nextElement();
				
				if (networkInterface.isLoopback())
					continue;
				
				for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
					if(interfaceAddress.getBroadcast() == null)
						continue;
					
					if(user.getAddress().equals( interfaceAddress.getAddress().getHostAddress() )){
						return interfaceAddress.getBroadcast().getHostAddress();
					}
				}
			}
			
			return "";
		}

	}
}
