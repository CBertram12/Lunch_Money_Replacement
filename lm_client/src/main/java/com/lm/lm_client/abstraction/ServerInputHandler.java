package com.lm.lm_client.abstraction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.lm.lm_library.Card;
import com.lm.lm_library.Message;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ServerInputHandler implements Runnable
{
	private ObjectProperty<Card> tableDisplayCardProperty = new SimpleObjectProperty<>();
	private BufferedReader input;

	public ServerInputHandler(InputStreamReader inputStream)
	{
		this.input = new BufferedReader(inputStream);
	}

	@Override
	public void run()
	{
		try
		{
			while (true)
			{
				String messageString = input.readLine();
				System.out.println("Client Received: " + messageString);
				handleMessage(new Message(messageString));
			}
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	public void handleMessage(Message message)
	{
		switch (message.getOperation())
		{
		case SHOW_CARD:
			tableDisplayCardProperty.set(new Card(message.getCardContent().get()));
			break;
		default:
			break;
		}
	}

	public ObjectProperty<Card> tableDisplayCardProperty()
	{
		return tableDisplayCardProperty;
	}
}
