package com.example.anar.domain;

public class River extends Entity<Integer>{
    private String name;
    private int mean;

    public River(Integer integer, String name, int mean) {
        super(integer);
        this.name = name;
        this.mean = mean;
    }

    public String getName() {
        return name;
    }

    public int getMean() {
        return mean;
    }

    public void setMean(int mean) {
        this.mean = mean;
    }
}
