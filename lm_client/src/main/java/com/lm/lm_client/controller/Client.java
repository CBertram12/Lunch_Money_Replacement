package com.lm.lm_client.controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class Client extends Application 
{
    public static void main(String[] args) 
    {
    	launch(args);
    }

    @Override
    public void start(Stage primaryStage) 
    {
        try
		{
			MainController controller = new MainController(primaryStage);
			primaryStage.setOnCloseRequest(e ->
			{
				try
				{
					controller.stop();
				} 
				catch (IOException error)
				{
					// TODO Auto-generated catch block
					error.printStackTrace();
				}
			});
		} 
        catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
