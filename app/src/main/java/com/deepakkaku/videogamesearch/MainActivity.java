package com.deepakkaku.videogamesearch;

import android.support.v4.view.MenuItemCompat;
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
import android.widget.Toast;

import com.deepakkaku.videogamesearch.Adapters.MainRecyclerViewAdapter;
import com.deepakkaku.videogamesearch.Custom.Game;
import com.deepakkaku.videogamesearch.Custom.ListResponse;
import com.deepakkaku.videogamesearch.Utill.Config;
import com.deepakkaku.videogamesearch.api.ApiClient;
import com.deepakkaku.videogamesearch.api.GiantBombService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiClient apiClient;
    GiantBombService gianServices;
    private List<Game> games = new ArrayList<>();
    private RecyclerView recyclerView;
    private MainRecyclerViewAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiClient = new ApiClient(this);
        gianServices = apiClient.getGiantServices();

        searchGame("Mortal");
        recyclerView = (RecyclerView)findViewById(R.id.mainRecycler);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        adapter = new MainRecyclerViewAdapter(games);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
                Toast.makeText(MainActivity.this,query,Toast.LENGTH_LONG).show();

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

    private void searchGame(String query) {
        progressBar.setVisibility(View.VISIBLE);
        Call<ListResponse> listResponse = gianServices.getList(Config.API_KEY,Config.JSON,query,Config.GAME_RESOURCE,Config.FILTER_LIST);

        listResponse.enqueue(new Callback<ListResponse>() {
            @Override
            public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {


                if(response.isSuccessful()){
                    games.clear();
                    games.addAll(response.body().getGameList());
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ListResponse> call, Throwable t) {

                Log.d("onResponse===",t.getMessage()+"");
            }
        });
    }
}
