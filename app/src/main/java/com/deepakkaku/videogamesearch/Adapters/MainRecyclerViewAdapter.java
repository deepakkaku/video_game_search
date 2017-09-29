package com.deepakkaku.videogamesearch.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deepakkaku.videogamesearch.Custom.Game;
import com.deepakkaku.videogamesearch.GameScreen;
import com.deepakkaku.videogamesearch.MainActivity;
import com.deepakkaku.videogamesearch.R;
import com.deepakkaku.videogamesearch.ViewHolders.CustomViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Deepak Kaku on 28-09-2017.
 */

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Game> games;
    Context mContext;
    public MainRecyclerViewAdapter(List<Game> games){

        this.games = games;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Game game = games.get(position);
        CustomViewHolder customViewHolder = (CustomViewHolder)holder;
        Picasso.with(mContext).load(game.getImage().getSuperUrl()).fit().into(customViewHolder.game_image);
        customViewHolder.title.setText(game.getTitle());
        customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, GameScreen.class);
                intent.putExtra("title",game.getTitle());
                intent.putExtra("image",game.getImage().getSuperUrl());
                intent.putExtra("description",game.getDescription());
                ((MainActivity)mContext).startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
       if(games.size()!=0)
            return games.size();
       return 0;
    }
}
