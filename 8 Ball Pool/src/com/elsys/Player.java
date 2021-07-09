package com.elsys;

public class Player {
    String name;
    private String ballType;

    Player(String name)
    {
        this.name = name;
        this.ballType = "None";
    }

    String getBallType()
    {
        return this.ballType;
    }

    void setBallType(String ballType)
    {
        this.ballType = ballType;
    }
}
