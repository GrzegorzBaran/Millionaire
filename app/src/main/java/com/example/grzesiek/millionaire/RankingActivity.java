package com.example.grzesiek.millionaire;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class RankingActivity extends Activity {
    private ListView list;
    Cursor cursor;
    ArrayList<Ranking> rankingArrayList = new ArrayList<Ranking>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        getData();
        CustomList adapter = new CustomList(this, rankingArrayList);
        list = (ListView) findViewById(R.id.rankingListView);
        list.setAdapter(adapter);

        /*
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });*/

    }

    public void getData(){
        String selectQuery = "SELECT * FROM " + GameEngine.TABLE_NAME_RANKING;
        cursor = GameEngine.database.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        rankingArrayList.add(
                new Ranking(cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("win")),
                        cursor.getInt(cursor.getColumnIndex("fifty_fifty")),
                        cursor.getInt(cursor.getColumnIndex("helpFromFriend")),
                        cursor.getInt(cursor.getColumnIndex("helpFromFriend"))));

        while(cursor.moveToNext()){
              rankingArrayList.add(
                      new Ranking(cursor.getString(cursor.getColumnIndex("name")),
                      cursor.getString(cursor.getColumnIndex("win")),
                      cursor.getInt(cursor.getColumnIndex("fifty_fifty")),
                      cursor.getInt(cursor.getColumnIndex("helpFromFriend")),
                      cursor.getInt(cursor.getColumnIndex("helpFromFriend"))));
        }
        cursor = null;
        System.out.println(rankingArrayList.get(0).name + "     " + rankingArrayList.get(0).win);
        System.out.println(rankingArrayList.get(1).name + "     " + rankingArrayList.get(1).win);

    }
}
