package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

class SkeletonFactory implements MonsterFactory<Skeleton> {
    @Override
    public Skeleton create(BufferedReader r) throws SyntaxError {
        return Skeleton.loadSkeleton(r);
    }

    @Override
    public String monsterName() {
        return "Skeleton";
    }
}

class Skeleton extends Monster {

    private float bones;

    private Skeleton(String name, String size, float bones) {
        super(name, size);
        this.bones = bones;
    }

    static Skeleton loadSkeleton(BufferedReader bufferedReader) throws SyntaxError {
        try {
            String name = bufferedReader.readLine();
            String size = bufferedReader.readLine();
            float bonesAmount = loadBones(bufferedReader);

            return new Skeleton(name, size, bonesAmount);
        } catch (IOException e) {
            throw new SyntaxError();
        }
    }

    static Skeleton create(Scanner in) {
        System.out.println("What is the skeletons name?");
        String name = in.nextLine();

        System.out.println("Describe the size of the skeleton");
        String size = in.nextLine();

        float bonesAmount = readBones(in);

        return new Skeleton(name, size, bonesAmount);
    }

    private static float loadBones(BufferedReader bufferedReader) throws IOException {
        return Float.parseFloat(bufferedReader.readLine());
    }

    private static float readBones(Scanner in) {
        while (true) {
            try {
                System.out.println("How many bones does the skeleton have? (If the bones are damaged add decimals to indicate the total amount.)");
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
        System.out.println("Name: " + getName() + " Size: " + getSize() + " Amount of bones: " + getBones());
    }

    public void save(BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(getName());
        bufferedWriter.newLine();

        bufferedWriter.write(getSize());
        bufferedWriter.newLine();

        bufferedWriter.write(Float.toString(getBones()));
        bufferedWriter.newLine();
    }

    private float getBones() {
        return bones;
    }

}
