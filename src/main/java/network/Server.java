package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;

import action.ActionBuilder;
import exception.UnknownUserException;
import io.OutputHandler;
import main.Application;
import payload.ReplyAction;
import payload.ReportAction;

public class Server implements Runnable {

	public void run() {
		OutputHandler.log("Starting server on port " + Application.getPort());

		try {
			int port = Application.getPort();

			DatagramSocket dsocket = new DatagramSocket(port);

			byte[] buffer = new byte[2048];

			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

			while (true) {
				dsocket.receive(packet);

				String message = new String(buffer, 0, packet.getLength());
				OutputHandler.log("Received from " + packet.getAddress().getHostName() + ": " + message );
				
				ActionBuilder.buildFromJson(message).receive(packet.getAddress().getHostAddress());			
			}
		} catch (UnknownUserException e) {
			OutputHandler.log("Recebido mensagem de um usuário desconhecido:");
			e.printStackTrace();
		} catch (JsonParseException e) {
			reportError("JSON mal formado");
		} catch (Exception e) {
			OutputHandler.log("Um erro ocorreu:");
			e.printStackTrace();
		}

	}
	
	private void reportError(String message){
		ReportAction reportAction = new ReportAction();
		reportAction.message = message;
		
		try {
			reportAction.send();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
