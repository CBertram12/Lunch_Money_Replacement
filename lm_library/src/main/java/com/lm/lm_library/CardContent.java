package com.lm.lm_library;

import java.util.Optional;

public enum CardContent
{
    CARD_1("Title 1", "Longer description text for card 1.", Optional.of(1)),
    CARD_2("Title 2", "Longer description text for card 2.", Optional.of(2)),
    CARD_3("Title 3", "Longer description text for card 3.", Optional.of(3)),
    CARD_4("Title 4", "Longer description text for card 4.", Optional.of(4)),
    CARD_5("Title 5", "Longer description text for card 5.", Optional.of(5));

    private String title;
    private String description;
    private Optional<Integer> number;

    CardContent(String title, String description, Optional<Integer> number)
    {
        this.title = title;
        this.description = description;
        this.number = number;
    }

    public String getTitle()
    {
        return title;
    }

    public String getDescription()
    {
        return description;
    }

    public Optional<Integer> getNumber()
    {
        return number;
    }
    
    public static CardContent fromTitle(String title) 
    {
        for (CardContent cardContent : CardContent.values()) 
        {
            if (cardContent.title.equals(title)) 
            {
                return cardContent;
            }
        }
        throw new IllegalArgumentException("Invalid title: " + title);
    }
}
