/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Entity.Entity;
//import Entity.Survivor.Survivor;
import Entity.Survivor.Type.*;
//import Entity.Zombie.Zombie;
import Entity.Zombie.Type.*;
import java.util.*;

/**
 *
 * @author kaifa
 */
public class Main {

    private final ArrayList<Entity> survivorList = new ArrayList<>();
    private final ArrayList<Entity> zombieList = new ArrayList<>();

    private int childCount = 0, soldierCount = 0, teacherCount = 0, commonInfectCount = 0, tankCount = 0;

    //create random number of survivors
    public void createSurvivor() {
        Random rand = new Random();
        //generate numbers between 1 and 20
        int numOfSurvivor = rand.nextInt(20) + 1;
        for (int i = 0; i < numOfSurvivor; i++) {
            //generate numbers 0 to 2
            int randNum = rand.nextInt(3);
            switch (randNum) {
                case 0 -> //create Soldier survivor if random number is 0
                {
                    survivorList.add(new Soldier(soldierCount));
                    soldierCount++;
                }
                case 1 -> //create Teacher survivor if random number is 1
                {
                    survivorList.add(new Teacher(teacherCount));
                    teacherCount++;
                }

                case 2 -> //create Child survivor if random number is 2
                {
                    survivorList.add(new Child(childCount));
                    childCount++;
                }

            }
        }
    }

    //create random number of zombies
    public void createZombie() {
        Random rand = new Random();
        //generate numbers between 1 and 10;
        int numOfZombie = rand.nextInt(10) + 1;
        for (int i = 0; i < numOfZombie; i++) {
            //generate numbers 0 to 1
            int randNum = rand.nextInt(2);
            switch (randNum) {
                case 0 -> //create Tank zombie if random number is 0
                {
                    zombieList.add(new Tank(tankCount));
                    tankCount++;
                }

                case 1 -> //create CommonInfected zombie if random number is 1
                {
                    zombieList.add(new CommonInfected(commonInfectCount));
                    commonInfectCount++;
                }

            }
        }
    }

    //each survivor attacks every zombie
    public void survivorAttack() {

        for (int i = 0; i < survivorList.size(); i++) {
            for (int j = 0; j < zombieList.size(); j++) {
                //if survivor and zombie is alive, survivor attacks
                if (survivorList.get(i).checkAlive() && zombieList.get(j).checkAlive()) {
                    survivorList.get(i).doAttack(zombieList.get(j));

                    // Check if the survivor killed the zombie
                    if (!zombieList.get(j).checkAlive()) {
                        System.out.println(survivorList.get(i).getClass().getSimpleName() + " " + survivorList.get(i).getTypeCount()
                                + " killed " + zombieList.get(j).getClass().getSimpleName() + " " + zombieList.get(j).getTypeCount());
                    }
                }
            }
        }
    }

    //each zombie attacks every survivor
    public void zombieAttack() {
        for (int i = 0; i < zombieList.size(); i++) {
            for (int j = 0; j < survivorList.size(); j++) {
                //if zombie is alive, zombie attacks
                if (zombieList.get(i).checkAlive() && survivorList.get(j).checkAlive()) {
                    zombieList.get(i).doAttack(survivorList.get(j));

                    // Check if the zombie killed the survivor
                    if (!survivorList.get(j).checkAlive()) {
                        System.out.println(zombieList.get(i).getClass().getSimpleName() + " " + zombieList.get(i).getTypeCount()
                                + " killed " + survivorList.get(j).getClass().getSimpleName() + " " + survivorList.get(j).getTypeCount());
                    }
                }
            }
        }
    }

    //check for number of entities alive and return number
    public Integer checkSurvivingEntities(ArrayList<Entity> list) {
        int survivingSurvivors = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).checkAlive() == true) {
                survivingSurvivors++;
            }
        }
        return survivingSurvivors;
    }

    //prints results
    public void printResults() {
        System.out.println("It seems " + checkSurvivingEntities(survivorList) + " made it to safety.");
    }

    public void run() {
        createSurvivor();
        createZombie();
        int survivorsAlive = checkSurvivingEntities(survivorList);
        int zombiesAlive = checkSurvivingEntities(zombieList);

        // Print opening message.
        System.out.println("We have " + survivorList.size() + " survivors trying to make it to safety " + childCount + " children, " + teacherCount + " teachers, " + soldierCount + " soldiers)");
        System.out.println("But there are " + zombieList.size() + " zombies waiting for them (" + commonInfectCount + " common infected, " + tankCount + " tanks)");

        //while there are still more than 0 survivors alive and more than 0 zombies alive, they attack each other
        while (survivorsAlive > 0 && zombiesAlive > 0) {
            survivorAttack();
            zombieAttack();
            survivorsAlive = checkSurvivingEntities(survivorList);
            zombiesAlive = checkSurvivingEntities(zombieList);
        }
        printResults();
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
