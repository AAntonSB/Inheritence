package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;


class Menu {
    private SMA sma;

    private Scanner in = new Scanner(System.in);

    private MonsterCollection skeletonList = new MonsterCollection<>(new SkeletonFactory());
    private MonsterCollection spiderList = new MonsterCollection<>(new SpiderFactory());
    private MonsterCollection goblinList = new MonsterCollection<>(new GoblinFactory());

    private static int readSearch(Scanner in, String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                return in.nextInt() - 1;
            } catch (InputMismatchException exception) {
                System.out.println("Wrong input, try again.");
                in.nextLine();
            }
        }
    }

    private void menu() {
        boolean quit = false;
        do {
            System.out.println(
                    "Welcome to the " + ConsoleColors.RED_BOLD + "MonsterManual" + ConsoleColors.RESET + ", please choose an option below\n" +
                            "1) Create a monster.\n" +
                            "2) Remove a monster.\n" +
                            "3) Show monster(s).\n" +
                            "4) Manual Save\n" +
                            "5) Help.\n" +
                            "0) Exit.");
            String choice = in.nextLine();
            switch (choice) {
                case "1":
                    sma = SMA.CREATE;
                    subMenu(sma);
                    break;
                case "2":
                    sma = SMA.REMOVE;
                    subMenu(sma);
                    break;
                case "3":
                    showMenu();
                    in.nextLine();
                    break;
                case "4":
                    saveToFile();
                    break;
                case "5":
                    System.out.println("Welcome to the help feature.\n" +
                            "Our goal is for you to understand the menu and sub menu systems completely when you are finished reading\n" +
                            "So to choose an item in the Main menu or the sub menus you simply press one of the numbers on your keyboard\n" +
                            "So for example 1. Then you press the enter key.\n\n" +
                            "In this example we will get thrown into a sub menu, the way you orientate yourself\n" +
                            "around it is simply by using the same method as before.\n" +
                            "Please press enter in order to confirm that you understand. If you don't you can always return to this menu.");
                    in.nextLine();
                    break;
                case "0":
                    saveToFile();
                    quit = true;
                    break;
                default:
                    System.out.println("Input is invalid, press  enter then 7 and finally enter again for instructions");
                    in.nextLine();
            }
        }
        while (!quit);
    }

    private void saveToFile() {
        skeletonList.save();
        spiderList.save();
        goblinList.save();
    }

    private <sma> void subMenu(sma s) {
        System.out.println("Would you like to " + s.toString().toLowerCase() + " a:\n" +
                "1) Spider\n" +
                "2) Skeleton\n" +
                "3) Goblin");
        String choice = in.nextLine();
        switch (sma) {
            case CREATE:
                createMenu(choice);
                break;
            case REMOVE:
                removeMenu(choice);
                break;
            case SEARCH_FOR:
                searchMenu(choice);
                break;
            case SHOW:
                break;
        }
    }

    private void createMenu(String subMenuChoice) {
        switch (subMenuChoice) {
            case "1":
                spiderList.add(Spider.create(this.in));
                break;
            case "2":
                skeletonList.add(Skeleton.create(this.in));
                break;
            case "3":
                goblinList.add(Goblin.create(this.in));
                break;
            default:
                System.out.println("Incorrect input, try again.");
        }
    }

    private void removeMenu(String subMenuChoice) {
        try {
            switch (subMenuChoice) {
                case "1":
                    spiderList.remove(chooseSpiderToRemove());
                    break;
                case "2":
                    skeletonList.remove(chooseSkeletonToRemove());
                    break;
                case "3":
                    goblinList.remove(chooseGoblinToRemove());
                    break;
                default:
                    System.out.println("Incorrect input, try again.");
                    break;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, there is no spider at that point in the list.");
            in.nextLine();
        }
    }

    private void searchMenu(String choice){
        try {
            switch (choice) {
                case "1":
                    spiderList.get(searchForSpider()).show();
                    break;
                case "2":
                    skeletonList.get(searchForSkeleton()).show();
                    break;
                case "3":
                    goblinList.get(searchForGoblin()).show();
                    break;
                default:
                    System.out.println("Incorrect input, try again.");
                    break;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, there is no spider at that point in the list.");
            in.nextLine();
        }
    }

    private void showMenu() {
        System.out.println("What show?\n" +
                "1) Search for specific monster through the index\n" +
                "2) Show all skeletons\n" +
                "3) Show all spiders\n" +
                "4) Show all goblin\n" +
                "5) Show all monsters");
        String choice = in.nextLine();
        switch (choice) {
            case "1":
                System.out.println("What monster type\n" +
                        "1) Skeleton\n" +
                        "2) Spider\n" +
                        "3) Goblin");
                choice = in.nextLine();
                searchMenu(choice);
                break;
            case "2":
                skeletonList.show();
                break;
            case "3":
                spiderList.show();
                break;
            case "4":
                goblinList.show();
                break;
            case "5":
                skeletonList.show();
                spiderList.show();
                goblinList.show();
                break;
            default:
                System.out.println("Incorrect input, try again.");
                break;
        }
    }

    private int chooseSpiderToRemove() {
        return Menu.readSearch(in, "Choose a spider to remove via the index (number from 1 - infinity)");
    }

    private int searchForSpider() {
        return readSearch(in, "Search for a spider through the index (number from 1 - infinity)");
    }

    private int chooseSkeletonToRemove() {
        return readSearch(in, "Choose a skeleton to remove via the index (number from 1 - infinity)");
    }

    private int searchForSkeleton() {
        return readSearch(in, "Search for a skeleton through the index (number from 1 - infinity)");
    }

    private int chooseGoblinToRemove() {
        return Menu.readSearch(in, "Choose a goblin to remove via the index (number from 1 - infinity)");
    }

    private int searchForGoblin() {
        return readSearch(in, "Search for a goblin through the index (number from 1 - infinity)");
    }

    void getMenu() {
        menu();
    }
}
