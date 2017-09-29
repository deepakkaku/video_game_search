package com.deepakkaku.videogamesearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class GameScreen extends AppCompatActivity {

    private WebView descriptionView;
    private TextView no_description;
    private ImageView cover;
    String title;
    String description;
    String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);




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
        no_description = (TextView) findViewById(R.id.no_description);
        descriptionView = (WebView) findViewById(R.id.description);

      //  titleTxt.setText(title);
        actionBar.setTitle(title);
        if(image!=null) {
            Picasso.with(this).load(image).fit().into(cover);
        }
        if(description==null){
            no_description.setVisibility(View.VISIBLE);
        }
        else{
            descriptionView.loadData(description, "text/html", null);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("title",title);
        outState.putSerializable("description",description);
        outState.putSerializable("image",image);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
