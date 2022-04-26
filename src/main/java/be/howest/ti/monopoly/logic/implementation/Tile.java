package be.howest.ti.monopoly.logic.implementation;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tile {

    private String name;
    private int position;
    private String type;
    private String nameAsPathParameter;

    public Tile(String name, int position, String type, String nameAsPathParameter) {
        this.name = name;
        this.position = position;
        this.type = type;
        this.nameAsPathParameter = nameAsPathParameter;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public String getType() {
        return type;
    }

    public String getNameAsPathParameter() {
        return nameAsPathParameter;
    }
}