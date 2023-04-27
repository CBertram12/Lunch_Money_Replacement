package com.lm.lm_server;

public class Server 
{
    public static void main(String[] args) 
    {
    	try
    	{
    		new GameServer();
    	}
    	catch (Exception e)
    	{
    		System.err.println("LM Server has crashed: " + e.getMessage());
    	}
    }
}
