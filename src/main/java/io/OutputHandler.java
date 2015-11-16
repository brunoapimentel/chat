package io;

import java.text.SimpleDateFormat;
import java.util.Date;

import main.Application;

public class OutputHandler {
	public static final int VERBOSE = 3;
	public static final int PRINT_TRACE = 2;
	public static final int ONLY_ERRORS = 1;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	public static void out(String output){
		System.out.println(getTimestamp() + " " + output);
	}
	
	public static void log(String log){
		if(Application.getLogLevel() < VERBOSE){
			return;
		}
		
		System.out.println("LOG "+ getTimestamp() +": " + log);
	}
	
	public static void error(String log){
		if(Application.getLogLevel() < ONLY_ERRORS){
			return;
		}
		
		System.out.println("LOG "+ getTimestamp()  +": " + log);
	}
	
	public static void error(String log, Exception e){
		if(Application.getLogLevel() < ONLY_ERRORS){
			return;
		}
		
		if(Application.getLogLevel() >= PRINT_TRACE){
			e.printStackTrace();
		}
		
		System.out.println("LOG "+ getTimestamp()  +": " + log);
	}
	
	private static String getTimestamp(){
		return dateFormat.format(new Date());
	}
	
}
