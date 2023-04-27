package com.lm.lm_library;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Card extends StackPane
{
	private final CardContent cardContent;
	
    public Card(CardContent cardContent)
    {
    	this.cardContent = cardContent;
    	
        Rectangle border = new Rectangle(150, 250);
        border.setFill(Color.WHITE);
        border.setStroke(Color.BLACK);

        BorderPane contentPane = new BorderPane();
        contentPane.setMinWidth(150);
        contentPane.setMaxWidth(150);
        contentPane.setMinHeight(250);
        contentPane.setMaxHeight(250);

        Label title = new Label(cardContent.getTitle());
        title.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        title.setWrapText(true);
        title.setMaxWidth(140);

        Text description = new Text(cardContent.getDescription());
        description.setFont(Font.font("Arial", 12));
        description.setWrappingWidth(140);

        ScrollPane scrollPane = new ScrollPane(description);
        scrollPane.setMinViewportWidth(100);
        scrollPane.setMinViewportHeight(100);
        scrollPane.setMaxHeight(100);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: white;");

        HBox topContentHbox = new HBox();
        topContentHbox.setSpacing(20);
        if (cardContent.getNumber().isPresent())
        {
            Label numberLabel = new Label(String.valueOf(cardContent.getNumber().get()));
            numberLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            numberLabel.setTextFill(Color.BLACK);
            topContentHbox.getChildren().add(numberLabel);
        }
        
        setMargin(topContentHbox, new Insets(0, 8, 0, 10));
        
        topContentHbox.getChildren().add(title);
        
        contentPane.setTop(topContentHbox);
        contentPane.setBottom(scrollPane);
        
        getChildren().addAll(border, contentPane);
    }
    
    public CardContent getCardContent()
    {
    	return cardContent;
    }
}