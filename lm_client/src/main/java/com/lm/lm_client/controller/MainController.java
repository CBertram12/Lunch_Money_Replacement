package com.lm.lm_client.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Optional;

import com.lm.lm_client.abstraction.ServerInputHandler;
import com.lm.lm_client.abstraction.ServerOutputHandler;
import com.lm.lm_client.presentation.MainModel;
import com.lm.lm_client.presentation.MainPresentation;
import com.lm.lm_library.Card;
import com.lm.lm_library.Message;
import com.lm.lm_library.Operation;

import javafx.stage.Stage;

public class MainController
{
	private MainModel model;
	private ServerOutputHandler serverOutputHandler;
	private ServerInputHandler serverInputHandler;
	private Thread serverOutputHandlerThread;
	private Thread serverInputHandlerThread;
	private Socket socket;
	
	public MainController(Stage primaryStage) throws IOException
	{
		this.model = new MainModel();
		new MainPresentation(this, primaryStage);
		
		InetAddress serverAddress = InetAddress.getByName("18.221.105.205");
		int serverPort = 5000;
		
		socket = new Socket(serverAddress, serverPort);
		
		OutputStreamWriter output = new OutputStreamWriter(socket.getOutputStream());
		InputStreamReader input = new InputStreamReader(socket.getInputStream());
		
		this.serverOutputHandler = new ServerOutputHandler(output);
		this.serverInputHandler = new ServerInputHandler(input);
				
		this.serverOutputHandlerThread = new Thread(serverOutputHandler);
		this.serverInputHandlerThread = new Thread(serverInputHandler);
		
		serverOutputHandlerThread.start();
		serverInputHandlerThread.start();
		
		setAbstractionMappings();
	}

	private void setAbstractionMappings()
	{
		serverInputHandler.tableDisplayCardProperty().addListener((x, o, n) -> 
		{
			if (n != null)
			{
				model.showCard(n);
			}
		});
	}

	public void showCard(Card card)
	{
		System.out.println("Card click");
		Message showCardMessage = new Message("Player", Operation.SHOW_CARD, Optional.of(card.getCardContent()));
		serverOutputHandler.addMessage(showCardMessage);
	}

	public MainModel getModel()
	{
		return model;
	}

	public void stop() throws IOException
	{
		socket.close();
		serverInputHandlerThread.interrupt();
		serverOutputHandlerThread.interrupt();
	}
}
