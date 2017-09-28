package com.deepakkaku.videogamesearch.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.deepakkaku.videogamesearch.R;

/**
 * Created by Deepak Kaku on 28-09-2017.
 */

public class CustomViewHolder extends RecyclerView.ViewHolder {
    public ImageView game_image;
    public TextView title;
    public CustomViewHolder(View itemView) {
        super(itemView);
        game_image = (ImageView) itemView.findViewById(R.id.game_image);
        title = (TextView) itemView.findViewById(R.id.title);

    }

}
