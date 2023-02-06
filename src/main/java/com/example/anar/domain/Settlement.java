package com.example.anar.domain;

public class Settlement extends Entity<Integer>{
    private String name;
    private int riverID;
    private int cmdr; //mean risk quotient
    private int cma; //maximum risk quotient

    public Settlement(Integer integer, String name, int riverID, int cmdr, int cma) {
        super(integer);
        this.name = name;
        this.riverID = riverID;
        this.cmdr = cmdr;
        this.cma = cma;
    }

    public String getName() {
        return name;
    }

    public int getRiverID() {
        return riverID;
    }

    public int getCmdr() {
        return cmdr;
    }

    public int getCma() {
        return cma;
    }
}
