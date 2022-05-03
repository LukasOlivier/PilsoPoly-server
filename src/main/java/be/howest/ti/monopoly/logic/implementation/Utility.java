package be.howest.ti.monopoly.logic.implementation;

public class Utility extends Property{

    public Utility(String name, int position, String type, int groupSize, String color, int mortgage, int cost) {
        super(name, position, type, groupSize, color, -1,mortgage,cost);
    }

    public String getRent() {
        return  "4 or 10 times the dice roll";
    }
}
