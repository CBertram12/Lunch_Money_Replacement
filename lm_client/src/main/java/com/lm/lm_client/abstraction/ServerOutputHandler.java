package com.lm.lm_client.abstraction;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.lm.lm_library.Message;

public class ServerOutputHandler implements Runnable
{
	private BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>();
	private OutputStreamWriter output;

	public ServerOutputHandler(OutputStreamWriter output)
	{
		this.output = output;
	}

	@Override
	public void run()
	{
		try
		{
			while (true)
			{
				Message message = messageQueue.take();
				
				// Send the message to the server
				output.write(message.toTransmissionString());
				output.flush();
			}
		} 
		catch (InterruptedException | IOException e)
		{
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	public void addMessage(Message message)
	{
		messageQueue.add(message);
		System.out.println("Message added");
	}
}
