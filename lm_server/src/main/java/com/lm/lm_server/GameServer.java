package com.lm.lm_server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.lm.lm_library.Message;

public class GameServer 
{
    private static final int PORT = 5000;
    
    private static List<Socket> clientSocketsList;

    public GameServer()
    {
    	clientSocketsList = new ArrayList<>();
    	
        try (ServerSocket serverSocket = new ServerSocket(PORT)) 
        {
            System.out.println("Server started, waiting for connections...");

            while (true) 
            {
                try
                {
                	Socket clientSocket = serverSocket.accept();
                    new Thread(new ClientConnectionHandler(this, clientSocket)).start();
                    clientSocketsList.add(clientSocket);
                } 
                catch (IOException e) 
                {
                    System.err.println("Error in client connection: " + e.getMessage());
                }
            }
        }
        catch (IOException e) 
        {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }
    
    public void sendMessageAllClients(Message message)
    {
    	for (Socket clientSocket : clientSocketsList)
    	{
			try
			{
				OutputStreamWriter outputStream = new OutputStreamWriter(clientSocket.getOutputStream());
				outputStream.write(message.toTransmissionString());
				outputStream.flush();
				System.out.println("Sending to all clients");
			} 
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }

	public void removeSocket(Socket clientSocket)
	{
		clientSocketsList.remove(clientSocket);
	}
}
