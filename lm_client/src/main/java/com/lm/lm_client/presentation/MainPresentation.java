package com.lm.lm_client.presentation;

import com.lm.lm_client.controller.MainController;
import com.lm.lm_library.Card;
import com.lm.lm_library.CardContent;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainPresentation
{

	public MainPresentation(MainController controller, Stage primaryStage)
	{
		BorderPane root = new BorderPane();

        HBox cardBox = new HBox(10);
        cardBox.setAlignment(Pos.BOTTOM_CENTER);

        for (CardContent cardImage : CardContent.values()) 
        {
            Card card = new Card(cardImage);
            cardBox.getChildren().add(card);
            card.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->
            {
            	controller.showCard(card);
            });
        }
        
        controller.getModel().playCardProperty().addListener((x, o, n) ->
        {
        	if (n != null)
        	{
        		root.setCenter(n);
        	}
        });
        
        root.setBottom(cardBox);

        Scene scene = new Scene(root, 850, 850);

        primaryStage.setTitle("Card Game");
        primaryStage.setScene(scene);
        primaryStage.show();
	}

}
