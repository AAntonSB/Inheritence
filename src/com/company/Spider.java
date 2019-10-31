package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

class SpiderFactory implements MonsterFactory<Spider> {
    @Override
    public Spider create(BufferedReader r) throws IOException {
        return Spider.loadSpider(r);
    }

    @Override
    public String monsterName() {
        return "Spider";
    }
}


class Spider extends Monster {

    private Boolean venomous;

    /**
     *This constructs a Spider with a specified name size and venomous tag.
     * @param name It's name.
     * @param size It's size.
     * @param venomous If the spider is venomous.
     */
    Spider(String name, String size, Boolean venomous) {
        super(name, size);
        this.venomous = venomous;
    }

    /**
     * Reads and creates the previously saved spiders.
     * @param bufferedReader Reads previously saved files from document and creates a new instance of them.
     * @return Returns the object as a constructor.
     * @throws IOException Throws the function if the file is inaccessible.
     */
    static Spider loadSpider(BufferedReader bufferedReader) throws IOException {
        String name = bufferedReader.readLine();
        String size = bufferedReader.readLine();
        boolean venomous = loadVenomous(bufferedReader);

        return new Spider(name, size, venomous);
    }

    /**
     *Applied when the user decides to create a Spider.
     * @param in Reads the users input
     * @return Returns a new Spider.
     */
    static Spider create(Scanner in) {
        System.out.println("What is the spiders name?");
        String name = in.nextLine();

        System.out.println("Describe the size of the spider");
        String size = in.nextLine();

        boolean venomous = readVenomous(in);

        return new Spider(name, size, venomous);
    }

    /**
     *Applied to read the previously saved booleans.
     * @param bufferedReader Reads previously saved values.
     * @return Returns the value to the other create.
     * @throws IOException When an io exception occurs
     */
    private static boolean loadVenomous(BufferedReader bufferedReader) throws IOException {
        return Boolean.parseBoolean(bufferedReader.readLine());
    }

    /**
     * Reads the venomous file and makes sure its the correct input.
     * @param in reads the player input.
     * @return returns the venomous value.
     */
    private static boolean readVenomous(Scanner in) {
        while (true) {
            try {
                System.out.println("Is the spider venomus? (true/false)");
                boolean x = in.nextBoolean();
                in.nextLine();
                return x;
            } catch (InputMismatchException exception) {
                System.out.println("Wrong input, try again.");
                in.nextLine();
            }
        }
    }

    /**
     * Shows a spider.
     */
    @Override
    public void show() {

        System.out.println("Name: " + getName() + " Size: " + getSize() + " Venomous? " + getVenomous());

    }

    /**
     *Applied to save a spider.
     * @param bufferedWriter Saves the files to a document
     * @throws IOException When an io exception occurs
     */
    public void save(BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(getName());
        bufferedWriter.newLine();

        bufferedWriter.write(getSize());
        bufferedWriter.newLine();

        bufferedWriter.write(Boolean.toString(getVenomous()));
        bufferedWriter.newLine();
    }

    /**
     *A getter for the Boolean venomous.
     * @return Returns the value of venomous.
     */
    private Boolean getVenomous() {
        return venomous;
    }
}
