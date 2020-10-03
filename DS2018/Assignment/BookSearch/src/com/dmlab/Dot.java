package com.dmlab;

public class Dot {
    private int weight;

    public Dot(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void eat(int foodWeight) {
        weight += foodWeight;
    }

    public void wem(int wemWeight) {
        weight -= wemWeight;
    }

    public static String printMessage() {
        return "This is Dot class!";
    }
}
