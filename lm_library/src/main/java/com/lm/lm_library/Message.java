package com.lm.lm_library;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

public class Message
{
	private String playerId;
	private Operation operation;
	private Optional<CardContent> cardContent;
	
	public Message(String playerId, Operation operation, Optional<CardContent> cardContent)
	{
		this.playerId = playerId;
		this.operation = operation;
		this.cardContent = cardContent;
	}
	
	public Message(String transmissionString)
	{
		List<String> splitLine = Arrays.asList(transmissionString.split(":"));
		this.playerId = splitLine.get(0);
		this.operation = Operation.fromOperationName(splitLine.get(1));
		if (splitLine.size() == 3)
		{
			this.cardContent = Optional.of(CardContent.fromTitle(splitLine.get(2)));
		}
		else
		{
			this.cardContent = Optional.empty();
		}
	}
	
	public String getPlayerId()
	{
		return playerId;
	}
	
	public void setPlayerId(String playerId)
	{
		this.playerId = playerId;
	}
	
	public Operation getOperation()
	{
		return operation;
	}
	
	public void setOperation(Operation operation)
	{
		this.operation = operation;
	}
	
	public Optional<CardContent> getCardContent()
	{
		return cardContent;
	}

	public void setCardContent(Optional<CardContent> cardContent)
	{
		this.cardContent = cardContent;
	}

	public String toTransmissionString()
	{
		StringJoiner joiner = new StringJoiner(":");
		joiner.add(playerId);
		joiner.add(operation.getOperationName());
		if (cardContent.isPresent())
		{
			joiner.add(cardContent.get().getTitle());
		}
		return joiner.toString() + "\n";
	}
}
