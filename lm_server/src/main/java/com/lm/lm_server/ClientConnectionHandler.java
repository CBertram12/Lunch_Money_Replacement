package com.lm.lm_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.lm.lm_library.Message;

public class ClientConnectionHandler implements Runnable
{
	private final GameServer server;
	
	private Socket clientSocket;
	private BufferedReader input;
	
	public ClientConnectionHandler(GameServer gameServer, Socket clientSocket) throws IOException
	{
		this.server = gameServer;
		//TODO: Close
		this.clientSocket = clientSocket;
		this.input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}

	@Override
	public void run()
	{
		System.out.println("Client connected.");

		while (clientSocket.isConnected())
		{
			try
			{
				// Read the message from the client
				//TODO: Protect thread
				String messageString = input.readLine();
				System.out.println("Received message: " + messageString);
				
				if (messageString == null)
				{
					clientSocket.close();
					server.removeSocket(clientSocket);
					break;
				}
				decideMessageAction(new Message(messageString));
			} 
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}
	}
	private void decideMessageAction(Message message)
	{
		switch (message.getOperation())
		{
		case SHOW_CARD:
			server.sendMessageAllClients(message);
			break;
		default:
			break;
		}
	}
}
