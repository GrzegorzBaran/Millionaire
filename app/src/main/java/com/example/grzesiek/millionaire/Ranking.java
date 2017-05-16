package com.example.grzesiek.millionaire;


public class Ranking {
    public String name;
    public String win;
    public Boolean isHelpFromAudience;
    public Boolean isHelpFromFriend;
    public Boolean isHelpFiftyFifty;

    public Ranking(String name, String win, int isHelpFromAudience, int isHelpFromFriend, int isHelpFiftyFifty){
        this.name = name;
        this.win = win;
        if(isHelpFromAudience == 1)
            this.isHelpFromAudience = true;
        else
            this.isHelpFromAudience = false;

        if(isHelpFromFriend == 1)
            this.isHelpFromFriend = true;
        else
            this.isHelpFromFriend = false;

        if(isHelpFiftyFifty == 1)
            this.isHelpFiftyFifty = true;
        else
            this.isHelpFiftyFifty = false;
    }



}
