package com.example.grzesiek.millionaire;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import java.io.IOException;

public class Music extends Service {
    public static boolean isRunning =false;
    MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        setMusicOptions(this, GameEngine.LOOP_BACKGROUND_MUSIC, GameEngine.R_VOLUME, GameEngine.L_VOLUME,
                GameEngine.SPLASH_SCREEN_MUSIC);

        GameEngine.musicHandler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                changeMusic(Integer.parseInt(msg.obj.toString()));
            }
        };
    }

    private void setMusicOptions(Context context, boolean isLopped, int rVolume, int lVolume, int soundFile) {
        player = MediaPlayer.create(context, soundFile);
        player.setLooping(isLopped);
        player.setVolume(rVolume, lVolume);

    }

    public void changeMusic(int soundFile){
        player.stop();
        player.release();
        setMusicOptions(this, GameEngine.LOOP_BACKGROUND_MUSIC, GameEngine.R_VOLUME, GameEngine.L_VOLUME,
                soundFile);
        player.start();

    }


    public int onStartCommand(Intent intent, int flags, int startId){
        try{
            player.start();
            isRunning = true;
        }catch(Exception e){
            isRunning = false;
            player.stop();
        }
        return 1;
    }
    public void onStart(Intent intent, int startId){

    }

    public void onStop(){
        isRunning = false;
    }

    public IBinder onUnBind(Intent arg0){
        return null;
    }

    public void onPause(){

    }

    @Override
    public void onDestroy(){
        player.stop();
        player.release();
    }

    @Override
    public void onLowMemory(){
        player.stop();
    }
}
