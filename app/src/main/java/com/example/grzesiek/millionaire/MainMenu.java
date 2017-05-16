package com.example.grzesiek.millionaire;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;

public class MainMenu extends Activity{

    static final int MENU_THREAD_DELAY = 1000;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        GameEngine.handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                System.out.println("ODEBRANA WIADOMOSC:::::: " + msg.obj.toString());
            }
        };

        /**Run Music*/
        GameEngine.musicThread = new Thread(){
            public void run(){
                Intent bgmusic = new Intent(getApplicationContext(), Music.class);
                startService(bgmusic);
                GameEngine.context = getApplicationContext();

            }
        };

        GameEngine.musicThread.start();

        final GameEngine engine = new GameEngine();

        /*set button option*/
        ImageButton start = (ImageButton) findViewById(R.id.btnStart);
        ImageButton exit = (ImageButton) findViewById(R.id.btnExit);
        ImageButton ranking = (ImageButton) findViewById(R.id.btnWon);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*start game*/
                Intent game = new Intent(MainMenu.this, GameActivity.class);
                MainMenu.this.startActivity(game);
            }
        });

        ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*start ranking view*/
                Intent ranking = new Intent(MainMenu.this, RankingActivity.class);
                MainMenu.this.startActivity(ranking);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*end game*/
                boolean clean = false;
                clean = engine.onExit(v);
                if (clean) {
                    int pid = android.os.Process.myPid();
                    android.os.Process.killProcess(pid);
                }
            }
        });


    }
}
