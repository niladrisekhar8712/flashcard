package com.project.flashcard.learning.tool.card;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Card {
    @Id
    @GeneratedValue
    private long id;
    @NotEmpty
    private String word;
    @NotEmpty
    private String description;

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotEmpty String getWord() {
        return word;
    }

    public void setWord(@NotEmpty String word) {
        this.word = word;
    }

    public @NotEmpty String getDescription() {
        return description;
    }

    public void setDescription(@NotEmpty String description) {
        this.description = description;
    }

    public Card() {
    }

    public Card(long id, String word, String description) {
        this.id = id;
        this.word = word;
        this.description = description;
    }
}
