package com.adaptionsoft.games.uglytrivia;

public enum TypeCategory {
    POP("Pop"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    ROCK("Rock");

    private final String message;

    TypeCategory(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
