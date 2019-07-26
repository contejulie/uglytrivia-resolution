package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Category {
    private LinkedList<String> listQuestion = new LinkedList<>();

    public Category(TypeCategory typeCategory) {
        for (int i = 0; i < 50; i++) {
            listQuestion.addLast(typeCategory.toString() + " Question " + i);
        }
    }

    public String removeFirstQuestion() {
        return listQuestion.removeFirst();
    }
}
