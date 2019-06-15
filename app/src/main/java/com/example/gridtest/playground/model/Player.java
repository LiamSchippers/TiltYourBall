package com.example.gridtest.playground.model;

import com.example.gridtest.playground.MainActivity;

// Het player model die worden op opgeslagen in de database
public class Player {
    private String name;
    private int playerID;
    private int score;
    private static int count;
    private int level;

    /* Constructor van een speler, het playerID wordt opgehoogd met één,
     * het hoogste id wordt telkens opgehaald uit de database en daar wordt één bij op geteld.
     */
    public Player(String name, int score, int level){
        this.name = name;
        this.score = score;
        count = MainActivity.myDatabase.getLastPlayerID() + 1;
        this.playerID = count;
        this.level = level;
    }

    // Setter en getters
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
    public int getPlayerID(){
        return this.playerID;
    }
    public int getScore(){
        return this.score;
    }
    public int getLevel(){
        return this.level;
    }

}