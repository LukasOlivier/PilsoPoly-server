package be.howest.ti.monopoly.logic.implementation;

public class Utility extends Property {
    String utilityRent;

    public Utility(String name, int position, String type, int groupSize, String color, int mortgage, int cost) {
        super(name, position, type, groupSize, color, -1, mortgage, cost);
        this.utilityRent = "4 or 10 times the dice roll";
    }

    /*public String getRent() {
        return utilityRent;
    }*/
}
