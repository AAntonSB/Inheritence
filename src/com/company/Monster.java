package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

interface MonsterFactory<T> {
    T create(BufferedReader reader) throws IOException, SyntaxError;

    String monsterName();
}

abstract class Monster {
    private String name;
    private String size;

    Monster(String name, String size) {
        this.name = name;
        this.size = size;
    }

    public abstract void show();

    public abstract void save(BufferedWriter o) throws IOException;

    String getName() {
        return name;
    }

    String getSize() {
        return size;
    }

}