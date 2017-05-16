package com.example.grzesiek.millionaire;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomList extends ArrayAdapter<String> {

    private final Activity context;
    ArrayList<Ranking> rankingArrayList;

    public CustomList(Activity context, ArrayList<Ranking> rankingArrayList) {
        super(context, R.layout.ranking_element, new String[rankingArrayList.size()]);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.rankingArrayList = rankingArrayList;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.ranking_element, null,true);

        TextView nameTxt = (TextView) rowView.findViewById(R.id.name_ranking);
        ImageView imageView5050 = (ImageView) rowView.findViewById(R.id.help_5050_ranking);
        ImageView imageViewFriend = (ImageView) rowView.findViewById(R.id.help_friend_ranking);
        ImageView imageViewAudience = (ImageView) rowView.findViewById(R.id.help_audience_ranking);
        TextView winTxt = (TextView) rowView.findViewById(R.id.win_ranking);

        nameTxt.setText(rankingArrayList.get(position).name);
        winTxt.setText("Wygrana: "+ rankingArrayList.get(position).win);

        if(rankingArrayList.get(position).isHelpFiftyFifty == true)
            imageView5050.setImageResource( R.drawable.help_5050);
        else
            imageView5050.setImageResource( R.drawable.help_5050_used);

        if(rankingArrayList.get(position).isHelpFromFriend == true)
            imageViewFriend.setImageResource( R.drawable.help_friend);
        else
            imageViewFriend.setImageResource( R.drawable.help_friend_used);

        if(rankingArrayList.get(position).isHelpFromAudience == true)
            imageViewAudience.setImageResource( R.drawable.help_audience);
        else
            imageViewAudience.setImageResource( R.drawable.help_audience_used);

        return rowView;

    };
}
