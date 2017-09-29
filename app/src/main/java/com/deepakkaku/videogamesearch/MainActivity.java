package com.deepakkaku.videogamesearch;

import android.content.DialogInterface;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.preference.DialogPreference;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.deepakkaku.videogamesearch.Adapters.MainRecyclerViewAdapter;
import com.deepakkaku.videogamesearch.Custom.Game;
import com.deepakkaku.videogamesearch.Custom.ListResponse;
import com.deepakkaku.videogamesearch.Utill.Config;
import com.deepakkaku.videogamesearch.api.ApiClient;
import com.deepakkaku.videogamesearch.api.GiantBombService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiClient apiClient;
    GiantBombService gianServices;
    private ArrayList<Game> games = new ArrayList<>();
    private RecyclerView recyclerView;
    private MainRecyclerViewAdapter adapter;
    private ProgressBar progressBar;
    private TextView noGamesText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting up the API call
        apiClient = new ApiClient(this);
        gianServices = apiClient.getGiantServices();

        //initializing views
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        noGamesText = (TextView)findViewById(R.id.no_games_txt);

        //load games on launch
        getTopGames();

        //initializing recycler
        recyclerView = (RecyclerView)findViewById(R.id.mainRecycler);

        //setting custom adapter and scroll listener
        adapter = new MainRecyclerViewAdapter(games);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        final SearchView searchViewAndroidActionBar = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchViewAndroidActionBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchViewAndroidActionBar.clearFocus();
              //  Toast.makeText(MainActivity.this,query,Toast.LENGTH_LONG).show();
                noGamesText.setVisibility(View.INVISIBLE);
                searchGame(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.action_refresh){
            getTopGames();
        }
        if(id == R.id.action_about){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Quicken Games is online game search engine.\nCreated by Deepak Kaku.")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();

                        }

                    });

            AlertDialog alert = builder.create();
            alert.show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void searchGame(final String query) {
        progressBar.setVisibility(View.VISIBLE);
        Call<ListResponse> listResponse = gianServices.getList(Config.API_KEY,0,Config.JSON,query,Config.GAME_RESOURCE,Config.FILTER_LIST,10);

        listResponse.enqueue(new Callback<ListResponse>() {
            @Override
            public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {


                if(response.isSuccessful()){
                        games.clear();
                        games.addAll(response.body().getGameList());
                        if(games.isEmpty() || games.size()==0){
                            noGamesText.setVisibility(View.VISIBLE);
                        }
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.INVISIBLE);

                }
            }

            @Override
            public void onFailure(Call<ListResponse> call, Throwable t) {

                Log.d("failure",t.getCause().getMessage());
                //isConnected = false;
                progressBar.setVisibility(View.INVISIBLE);
                AlertDialog.Builder alertBox = new AlertDialog.Builder(MainActivity.this);
                alertBox.setMessage("No Internet Connection/network failure. Please check your connection")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                .show();

            }
        });
    }

    private void getTopGames(){
        progressBar.setVisibility(View.VISIBLE);
        Call<ListResponse> listTopResponse = gianServices.getTopGames(Config.API_KEY,Config.JSON,Config.FILTER_LIST,10);


        listTopResponse.enqueue(new Callback<ListResponse>() {
            @Override
            public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {

                if(response.isSuccessful()){
                    games.clear();
                    games.addAll(response.body().getGameList());
                    if(games.isEmpty() || games.size()==0){
                        noGamesText.setVisibility(View.VISIBLE);
                    }
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.INVISIBLE);

                }
            }

            @Override
            public void onFailure(Call<ListResponse> call, Throwable t) {

                Log.d("failure",t.getCause().getMessage());
                //isConnected = false;
                progressBar.setVisibility(View.INVISIBLE);
                AlertDialog.Builder alertBox = new AlertDialog.Builder(MainActivity.this);
                alertBox.setMessage("No Internet Connection/network failure. Please check your connection")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();

            }
        });
    }

}
