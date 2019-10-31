package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

class GoblinFactory implements MonsterFactory<Goblin> {
    @Override
    public Goblin create(BufferedReader r) throws SyntaxError {
        return Goblin.loadGoblin(r);
    }

    @Override
    public String monsterName() {
        return "Goblin";
    }
}

class Goblin extends Monster {

    private float gold;

    private Goblin(String name, String size, float gold) {
        super(name, size);
        this.gold = gold;
    }

    static Goblin loadGoblin(BufferedReader bufferedReader) throws SyntaxError {
        try {
            String name = bufferedReader.readLine();
            String size = bufferedReader.readLine();
            float bonesAmount = loadGold(bufferedReader);

            return new Goblin(name, size, bonesAmount);
        } catch (IOException e) {
            throw new SyntaxError();
        }
    }

    static Goblin create(Scanner in) {
        System.out.println("What is the goblins name?");
        String name = in.nextLine();

        System.out.println("Describe the size of the goblins");
        String size = in.nextLine();

        float bonesAmount = readGold(in);

        return new Goblin(name, size, bonesAmount);
    }

    private static float loadGold(BufferedReader bufferedReader) throws IOException {
        return Float.parseFloat(bufferedReader.readLine());
    }

    private static float readGold(Scanner in) {
        while (true) {
            try {
                System.out.println("How much gold?");
                float x = in.nextFloat();
                in.nextLine();
                return x;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input, please ");
                in.nextLine();

            }
        }
    }

    public void show() {
        System.out.println("Name: " + getName() + " Size: " + getSize() + " Amount of bones: " + getGold());
    }

    public void save(BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(getName());
        bufferedWriter.newLine();

        bufferedWriter.write(getSize());
        bufferedWriter.newLine();

        bufferedWriter.write(Float.toString(getGold()));
        bufferedWriter.newLine();
    }

    private float getGold() {
        return gold;
    }
}
