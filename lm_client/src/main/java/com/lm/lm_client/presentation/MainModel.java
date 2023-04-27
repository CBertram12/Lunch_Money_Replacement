package com.lm.lm_client.presentation;

import com.lm.lm_library.Card;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class MainModel
{
	private ObjectProperty<Card> playCardProperty = new SimpleObjectProperty<>();
	
	public ObjectProperty<Card> playCardProperty()
	{
		return playCardProperty;
	}
	
	public void showCard(Card card)
	{
		Platform.runLater(() -> playCardProperty.set(card));
	}

}
