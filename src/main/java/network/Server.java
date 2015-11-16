package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import action.ActionBuilder;
import io.OutputHandler;
import main.Application;
import payload.ReportAction;

public class Server implements Runnable {

	public void run() {
		OutputHandler.log("Starting server on port " + Application.getPort());

		
			DatagramSocket dsocket;
			try {
				dsocket = new DatagramSocket(Application.getPort());
			} catch (SocketException e) {
				OutputHandler.error("Erro ao abrir socket",e);
				Application.halt();
				return;
			}

			byte[] buffer = new byte[2048];

			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

			while (true) {
				try {
					dsocket.receive(packet);
				} catch (IOException e) {
					OutputHandler.error("Um erro ocorreu");
					e.printStackTrace();
				}

				String message = new String(buffer, 0, packet.getLength());
				OutputHandler.log("Received from " + packet.getAddress().getHostAddress() + ": " + message );
				
				try {
					ActionBuilder.buildFromJson(message).receive(packet.getAddress().getHostAddress());
				} catch (JsonParseException e) {
					reportError("JSON mal formado!");
				} catch (JsonMappingException e) {
					reportError("Não foi possível mapear o JSON!");
				} catch (IOException e) {
					OutputHandler.error("Um erro ocorreu", e);
				}			
			}
		

	}
	
	private void reportError(String message){
		ReportAction reportAction = new ReportAction();
		reportAction.message = message;
		
		try {
			reportAction.send();
		} catch (JsonProcessingException e) {
			OutputHandler.error("Um erro ocorreu", e);
		}
	}
}
