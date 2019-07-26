package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Category {
    private LinkedList<String> listQuestion = new LinkedList<>();

    public Category(String name) {
        for (int i = 0; i < 50; i++) {
            listQuestion.addLast(TypeCategory.valueOf(name).toString()+ " Question " + i);
        }
    }

    public String removeFirstQuestion(){
        return listQuestion.removeFirst();
    }
}
