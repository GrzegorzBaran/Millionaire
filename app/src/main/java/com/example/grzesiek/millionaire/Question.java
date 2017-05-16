package com.example.grzesiek.millionaire;


public class Question {
    public String question;
    public String[] answers = new String[4];
    private String correctAnswer;

    Question(String q, String a, String b, String c, String d, String ca){
        this.question = q;
        this.answers[0] = a;
        this.answers[1] = b;
        this.answers[2] = c;
        this.answers[3] = d;
        this.correctAnswer = ca;
    }

    public String getCorrectAnswer(){
        return correctAnswer;
    }

}
