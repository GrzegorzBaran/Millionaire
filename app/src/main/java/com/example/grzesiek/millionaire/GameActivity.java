package com.example.grzesiek.millionaire;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;

public class GameActivity extends Activity{

    TextView question_text, answer_a_text, answer_b_text, answer_c_text, answer_d_text, help_friend_text;
    ImageView ts1, ts2, ts3, ts4, ts5, ts6, ts7, ts8, ts9, ts10, ts11, ts12, help_window;
    ImageButton answer_a_ib, answer_b_ib, answer_c_ib, answer_d_ib, help_5050, help_friend, help_audience;
    Game game;
    ProgressBar pbA, pbB, pbC, pbD;
    RelativeLayout help_audience_layout, help_friend_layout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        game = new Game(GameEngine.database);
        game.getQuestions();

        answer_a_ib = (ImageButton) findViewById(R.id.answer_a);
        answer_b_ib = (ImageButton) findViewById(R.id.answer_b);
        answer_c_ib = (ImageButton) findViewById(R.id.answer_c);
        answer_d_ib = (ImageButton) findViewById(R.id.answer_d);
        help_5050 = (ImageButton) findViewById(R.id.help_5050);
        help_friend = (ImageButton) findViewById(R.id.help_friend);
        help_audience = (ImageButton) findViewById(R.id.help_audience);

        help_audience_layout = (RelativeLayout) findViewById(R.id.help_audience_layout);
        help_friend_layout = (RelativeLayout) findViewById(R.id.help_friend_layout);
        question_text = (TextView)findViewById(R.id.question_text);
        answer_a_text = (TextView)findViewById(R.id.answer_a_text);
        answer_b_text = (TextView)findViewById(R.id.answer_b_text);
        answer_c_text = (TextView)findViewById(R.id.answer_c_text);
        answer_d_text = (TextView)findViewById(R.id.answer_d_text);
        help_friend_text = (TextView)findViewById(R.id.help_friend_text);

        ts1 = (ImageView) findViewById(R.id.ts_bar1);
        ts2 = (ImageView) findViewById(R.id.ts_bar2);
        ts3 = (ImageView) findViewById(R.id.ts_bar3);
        ts4 = (ImageView) findViewById(R.id.ts_bar4);
        ts5 = (ImageView) findViewById(R.id.ts_bar5);
        ts6 = (ImageView) findViewById(R.id.ts_bar6);
        ts7 = (ImageView) findViewById(R.id.ts_bar7);
        ts8 = (ImageView) findViewById(R.id.ts_bar8);
        ts9 = (ImageView) findViewById(R.id.ts_bar9);
        ts10 = (ImageView) findViewById(R.id.ts_bar10);
        ts11 = (ImageView) findViewById(R.id.ts_bar11);
        ts12 = (ImageView) findViewById(R.id.ts_bar12);
        help_window = (ImageView) findViewById(R.id.help_window);
        pbA = (ProgressBar) findViewById(R.id.progress_bar_A);
        pbB = (ProgressBar) findViewById(R.id.progress_bar_B);
        pbC = (ProgressBar) findViewById(R.id.progress_bar_C);
        pbD = (ProgressBar) findViewById(R.id.progress_bar_D);

        answer_a_ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChoice(answer_a_text.getText().toString());
            }
        });

        answer_b_ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChoice(answer_b_text.getText().toString());
            }
        });

        answer_c_ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChoice(answer_c_text.getText().toString());
            }
        });

        answer_d_ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChoice(answer_d_text.getText().toString());

            }
        });

        help_5050.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                help5050();
            }
        });

        help_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpFromFriend();
            }
        });
        help_audience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHelpFromAudience();
            }
        });
        /** Change Music */
        Message msg = new Message();
        msg.obj = R.raw.easyquestionssound;
        GameEngine.musicHandler.sendMessage(msg);


        /**show first answer*/
        showQuestion(game.questionsArray[0]);
    }

    private void onChoice(String selectedAnswer){
        if(game.checkAnswer(selectedAnswer)){
            sleep(700);
            //waitd(700);
            showAmount();
            if(game.questionNumber < 13)
                showQuestion(game.questionsArray[game.questionNumber-1]);
            else {
                sleep(1500);
                this.finish();
                goToRank();
            }
            if(help_audience_layout.getVisibility() == View.VISIBLE){
                help_audience_layout.setVisibility(View.INVISIBLE);
            }
            if(help_friend_layout.getVisibility() == View.VISIBLE){
                help_friend_layout.setVisibility(View.INVISIBLE);
            }
            if(!help_5050.isClickable()){
                answer_a_ib.setClickable(true);
                answer_b_ib.setClickable(true);
                answer_c_ib.setClickable(true);
                answer_d_ib.setClickable(true);
            }


        }else{
            sleep(1000);

            /** Change Music */
            Message msg = new Message();
            msg.obj = R.raw.mainmenusound;
            GameEngine.musicHandler.sendMessage(msg);

            /** finish GameActivity*/
            this.finish();
            System.out.println("zakonczono Game!");

            //GameActivity.this.closeContextMenu();
        }
    }

    private void helpFromFriend() {
        if(help_audience_layout.getVisibility() == View.VISIBLE){
            help_audience_layout.setVisibility(View.INVISIBLE);
        }
        help_friend_layout.setVisibility(View.VISIBLE);
        help_friend_text.setText(game.getHelpFromFriend());
        help_friend.setImageResource(R.drawable.help_friend_used);
        help_friend.setClickable(false);
    }

    private void help5050() {
        game.selectTwoIncorrectAnswers();
        showQuestion(game.questionsArray[game.questionNumber-1]);

        if(answer_a_text.getText().equals(""))
            answer_a_ib.setClickable(false);
        if(answer_b_text.getText().equals(""))
            answer_b_ib.setClickable(false);
        if(answer_c_text.getText().equals(""))
            answer_c_ib.setClickable(false);
        if(answer_d_text.getText().equals(""))
            answer_d_ib.setClickable(false);
        help_5050.setImageResource(R.drawable.help_5050_used);
        help_5050.setClickable(false);
    }

    private void goToRank() {
        /*start rankActivity*/
        Intent rankActivity = new Intent(GameActivity.this, RankingActivity.class);
        GameActivity.this.startActivity(rankActivity);
    }

    public void waitd(int i){
        try {
            wait(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sleep(int t){
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void showAmount(){
        switch(game.questionNumber){
            case 2:
                ts1.setVisibility(View.VISIBLE);
                break;
            case 3:
                ts2.setVisibility(View.VISIBLE);
                break;
            case 4:
                ts3.setVisibility(View.VISIBLE);
                break;
            case 5:
                ts4.setVisibility(View.VISIBLE);
                break;
            case 6:
                ts5.setVisibility(View.VISIBLE);
                break;
            case 7:
                ts6.setVisibility(View.VISIBLE);
                break;
            case 8:
                ts7.setVisibility(View.VISIBLE);
                break;
            case 9:
                ts8.setVisibility(View.VISIBLE);
                break;
            case 10:
                ts9.setVisibility(View.VISIBLE);
                break;
            case 11:
                ts10.setVisibility(View.VISIBLE);
                break;
            case 12:
                ts11.setVisibility(View.VISIBLE);
                break;
            case 13:
                ts12.setVisibility(View.VISIBLE);
                break;

            default:
                break;

        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    void showHelpFromAudience(){
        if(help_friend_layout.getVisibility() == View.VISIBLE){
            help_friend_layout.setVisibility(View.INVISIBLE);
        }
        int[] help =  game.getHelpFromAudience();
        help_audience.setImageResource(R.drawable.help_audience_used);
        help_audience.setClickable(false);

        help_audience_layout.setVisibility(View.VISIBLE);
        pbA.setProgress(help[0], true);
        pbB.setProgress(help[1], true);
        pbC.setProgress(help[2], true);
        pbD.setProgress(help[3], true);
    }


    private void showQuestion(Question question) {
        question_text.setText(question.question);
        answer_a_text.setText(question.answers[0]);
        answer_b_text.setText(question.answers[1]);
        answer_c_text.setText(question.answers[2]);
        answer_d_text.setText(question.answers[3]);
    }


}
