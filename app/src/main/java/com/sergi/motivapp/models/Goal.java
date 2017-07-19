package com.sergi.motivapp.models;

/**
 * Created by gersoft on 13/07/2017.
 */

public class Goal {

    public String goal, why, tasks;

    public Goal(){

    }

    public Goal (String goal, String why, String tasks){
        this.goal = goal;
        this.why = why;
        this.tasks = tasks;
    }

}