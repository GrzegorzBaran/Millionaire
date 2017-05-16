package com.example.grzesiek.millionaire;


import java.util.Random;

public class Help {
    public Boolean isHelpFromAudience;
    public Boolean isHelpFromFriend;
    public Boolean isHelpFiftyFifty;
    private Random random = new Random();

    Help(){
        isHelpFromAudience = true;
        isHelpFromFriend= true;
        isHelpFiftyFifty = true;
    }

    public String getHelpFromFriend(Question[] questionsArray, int questionNumber){
        isHelpFromFriend = false;
        int helpLevel = 0;
        if(questionNumber <= 4)
            helpLevel = 10;
        else if(questionNumber > 4 && questionNumber <=8)
            helpLevel = 7;
        else if(questionNumber > 8)
            helpLevel = 5;

        int hfh = random.nextInt(100) * helpLevel;
        if(hfh > 800)
            return "Przyjaciel jest pewny że poprawną odpowiedziją jest: " + questionsArray[questionNumber-1].getCorrectAnswer();
        else if(hfh > 400)
            return "Przyjacielowi wydaje się, że poprawną odpowiedzią jest: " + questionsArray[questionNumber-1].getCorrectAnswer();
        else if(hfh > 100)
            return "Przyjaciel nie jest jest w stanie pomóc przy tym pytaniu";
        else
            return "Przyjacielowi wydaje się że poprawną odpowiedzią jest: " +  questionsArray[questionNumber-1].answers[random.nextInt(3)];
    }



    public int[] getHelpFromAudience(Question[] questionsArray, int questionNumber){
        isHelpFromAudience = false;
        int[] helpFromAudience = new int[4];
        int helpLevel = 0;
        int goodAnswer;
        int sum = 0;
        if(questionNumber <= 4)
            helpLevel = 7;
        else if(questionNumber > 4 && questionNumber <=8)
            helpLevel = 4;
        else if(questionNumber > 8)
            helpLevel = 2;

        goodAnswer = getGoodAnswer(questionsArray, questionNumber);
        for(int i = 0; i < 4; i++){
            helpFromAudience[i] = random.nextInt(100);
            if(i == goodAnswer)
                helpFromAudience[i] = helpFromAudience[i] * helpLevel;
            sum += helpFromAudience[i];
        }

        for(int i = 0; i < 4; i++){
            helpFromAudience[i] = ((helpFromAudience[i] * 100) / sum);
        }

        return helpFromAudience;
    }



    private int getGoodAnswer(Question[] questionsArray, int questionNumber){
        for(int i = 0; i < 4; i++){
            if(questionsArray[questionNumber-1].getCorrectAnswer().equals(questionsArray[questionNumber-1].answers[i]))
                return i;
        }
        return -1;
    }


}
