package com.company;

import java.io.*;
import java.util.ArrayList;

class MonsterCollection<T extends Monster> {
    private ArrayList<Monster> monsterList = new ArrayList<>();
    private String fileName;
    private String monsterName;

    MonsterCollection(MonsterFactory<T> factory) {
        fileName = factory.monsterName() + ".txt";
        monsterName = factory.monsterName();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int number_of_monsters = 0;
            try {
                number_of_monsters = Integer.parseInt(bufferedReader.readLine());
            } catch (NumberFormatException ex) {
                // Ignore this
            }
            for (int i = 0; i < number_of_monsters; ++i) {
                monsterList.add(factory.create(bufferedReader));
            }

            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            // ignore this
        } catch (IOException e) {
            System.out.println("ERROR: Corrupted data in " + fileName);
        } catch (SyntaxError syntaxError) {
            syntaxError.printStackTrace();
        }
    }

    void add(Monster monster) {
        monsterList.add(monster);
    }

    Monster get(int index) {
        return monsterList.get(index);
    }

    void show() {
        System.out.println(monsterName + ":");
        if (monsterList.size() != 0) {
            for (Monster monster : monsterList) {
                monster.show();
            }
        } else {
            System.out.println("List is currently empty");
        }
    }

    void remove(int index) {
        monsterList.remove(index);
    }

    void save() {
        try {
            FileWriter fr = new FileWriter(fileName);
            BufferedWriter out = new BufferedWriter(fr);

            out.write(Integer.toString(monsterList.size()));
            out.newLine();

            for (Monster monster : monsterList) {
                monster.save(out);
            }

            out.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

