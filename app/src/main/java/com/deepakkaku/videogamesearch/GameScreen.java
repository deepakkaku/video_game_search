package com.deepakkaku.videogamesearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class GameScreen extends AppCompatActivity {

    private WebView descriptionView;
    private TextView titleTxt;
    private ImageView cover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        String title;
        String description;
        String image;


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                title= null;
                description=null;
                image = null;

            } else {
                title= extras.getString("title");
                description = extras.getString("description");
                image = extras.getString("image");

            }
        } else {
            title= (String) savedInstanceState.getSerializable("title");
            description = (String) savedInstanceState.getSerializable("description");
            image = (String) savedInstanceState.getSerializable("image");

        }

        cover = (ImageView) findViewById(R.id.cover_image);
        titleTxt = (TextView) findViewById(R.id.titleLabel);
        descriptionView = (WebView) findViewById(R.id.description);

        titleTxt.setText(title);
        Picasso.with(this).load(image).fit().into(cover);
        descriptionView.loadData(description,"text/html",null);
    }
}
