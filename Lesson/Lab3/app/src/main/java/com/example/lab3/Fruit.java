package com.example.lab3;

public class Fruit {
    private final String name;
    private final String desc;
    private final int imageRes;

    public Fruit(String name, String desc, int imageRes) {
        this.name = name;
        this.desc = desc;
        this.imageRes = imageRes;
    }
    public String getName()  { return name; }
    public String getDesc()  { return desc; }
    public int getImageRes() { return imageRes; }
}

