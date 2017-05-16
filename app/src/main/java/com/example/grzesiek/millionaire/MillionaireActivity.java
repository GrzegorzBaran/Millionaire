package com.example.grzesiek.millionaire;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

public class MillionaireActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_millionaire);
        setContentView(R.layout.start_screen);

        GameEngine.DbHelper = new DatabaseHelper(this);
        try {
            GameEngine.DbHelper.createDatabase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            GameEngine.DbHelper.openDatabase();
        }catch(SQLException sqle){
            throw sqle;
        }
        GameEngine.database  =  GameEngine.DbHelper.getReadableDatabase();

        /*new game thread with delay*/
        new Handler().postDelayed(new Thread() {
            @Override
            public void run() {
                Intent mainMenu = new Intent(MillionaireActivity.this, MainMenu.class);
                MillionaireActivity.this.startActivity(mainMenu);
                MillionaireActivity.this.finish();
                overridePendingTransition(R.layout.fadein, R.layout.fadeout);
            }
        }, MainMenu.MENU_THREAD_DELAY);
    }
}
