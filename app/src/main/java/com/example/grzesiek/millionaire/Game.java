package com.example.grzesiek.millionaire;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Random;

public class Game {
    String Nickname;
    String score;
    Question questionsArray[] = new Question[12];
    Cursor cursor;
    SQLiteDatabase db;
    Random random = new Random();
    public int questionNumber;
    Help help = new Help();

    Game(SQLiteDatabase db){
        this.db = db;
        this.questionNumber = 1;
    }

    void getQuestions() {
        final String TABLE_NAME_LVL1 = "questionsLvl1";
        final String TABLE_NAME_LVL2 = "questionsLvl2";
        final String TABLE_NAME_LVL3 = "questionsLvl3";
        for(int i = 0; i < 4; i++) {
            String selectQuery = "SELECT * FROM " + TABLE_NAME_LVL1 + " WHERE _id = " + (random.nextInt(5)+1);
            cursor = db.rawQuery(selectQuery, null);
            cursor.moveToFirst();
            questionsArray[i] = new Question(cursor.getString(cursor.getColumnIndex("question")),
                            cursor.getString(cursor.getColumnIndex("answerA")),
                            cursor.getString(cursor.getColumnIndex("answerB")),
                            cursor.getString(cursor.getColumnIndex("answerC")),
                            cursor.getString(cursor.getColumnIndex("answerD")),
                            cursor.getString(cursor.getColumnIndex("correctAnswer")));
            cursor = null;
        }
        for(int i = 4; i < 8; i++) {
            String selectQuery = "SELECT * FROM " + TABLE_NAME_LVL2 + " WHERE _id = " + (random.nextInt(4)+1);
            cursor = db.rawQuery(selectQuery, null);
            cursor.moveToFirst();
            questionsArray[i] = new Question(cursor.getString(cursor.getColumnIndex("question")),
                    cursor.getString(cursor.getColumnIndex("answerA")),
                    cursor.getString(cursor.getColumnIndex("answerB")),
                    cursor.getString(cursor.getColumnIndex("answerC")),
                    cursor.getString(cursor.getColumnIndex("answerD")),
                    cursor.getString(cursor.getColumnIndex("correctAnswer")));
            cursor = null;
        }
        for(int i = 8; i < 12; i++) {
            String selectQuery = "SELECT * FROM " + TABLE_NAME_LVL3 + " WHERE _id = " + (random.nextInt(4)+1);
            cursor = db.rawQuery(selectQuery, null);
            cursor.moveToFirst();
            questionsArray[i] = new Question(cursor.getString(cursor.getColumnIndex("question")),
                    cursor.getString(cursor.getColumnIndex("answerA")),
                    cursor.getString(cursor.getColumnIndex("answerB")),
                    cursor.getString(cursor.getColumnIndex("answerC")),
                    cursor.getString(cursor.getColumnIndex("answerD")),
                    cursor.getString(cursor.getColumnIndex("correctAnswer")));
            cursor = null;
        }

        //cursor.close();
    }


    public Boolean checkAnswer(String selectedAnswer) {
        if(selectedAnswer.equals(questionsArray[questionNumber-1].getCorrectAnswer())){
            questionNumber++;
            return true;

        }else
            return false;

    }


    public int[] getHelpFromAudience(){
        return help.getHelpFromAudience(questionsArray, questionNumber);
    }


    public void selectTwoIncorrectAnswers() {
        int i = 0;
        int x;
        while(i<2) {
            x = (random.nextInt(4) + 1);
            switch (x) {
                case 1:
                    if (!questionsArray[questionNumber - 1].answers[0].equals(questionsArray[questionNumber - 1].getCorrectAnswer())) {
                        questionsArray[questionNumber - 1].answers[0] = "";
                        i++;
                    }
                    break;
                case 2:
                    if (!questionsArray[questionNumber - 1].answers[1].equals(questionsArray[questionNumber - 1].getCorrectAnswer())) {
                        questionsArray[questionNumber - 1].answers[1] = "";
                        i++;
                    }
                    break;
                case 3:
                    if (!questionsArray[questionNumber - 1].answers[2].equals(questionsArray[questionNumber - 1].getCorrectAnswer())) {
                        questionsArray[questionNumber - 1].answers[2] = "";
                        i++;
                    }
                    break;
                case 4:
                    if (!questionsArray[questionNumber - 1].answers[3].equals(questionsArray[questionNumber - 1].getCorrectAnswer())) {
                        questionsArray[questionNumber - 1].answers[3] = "";
                        i++;
                    }
                    break;
            }
        }

    }

    public String getHelpFromFriend() {
        return help.getHelpFromFriend(questionsArray, questionNumber);
    }
}
