package com.example.grzesiek.millionaire;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.view.View;


public class GameEngine {

    public static final int SPLASH_SCREEN_MUSIC = R.raw.mainmenusound;
    public static final int EASY_QUESTION_MUSIC = R.raw.easyquestionssound;
    public static final int R_VOLUME = 100;
    public static final int L_VOLUME = 100;
    public static final boolean LOOP_BACKGROUND_MUSIC = true;
    public static final String TABLE_NAME_RANKING = "ranking";


    public static final int MENU_BUTTON_ALPHA = 0;
    public static final boolean HAPTIC_BUTTON_FEEDBACK = true;

    public static Thread musicThread;
    public static Context context;
    public static Handler handler;
    public static Handler musicHandler;
    public static DatabaseHelper DbHelper;
    public static SQLiteDatabase database;

    public static String DATABASE_NAME = "questions.db";
    //public static Message msg = new Message();


    public boolean onExit(View v){
        try{
            Intent bgmusic = new Intent(context, Music.class);
            context.stopService(bgmusic);
            musicThread.interrupt();

            return true;
        }catch(Exception e){
            return false;
        }
    }
}
