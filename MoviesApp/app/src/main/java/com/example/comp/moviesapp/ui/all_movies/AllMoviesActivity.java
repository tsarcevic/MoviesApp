package com.example.comp.moviesapp.ui.all_movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comp.moviesapp.R;
import com.example.comp.moviesapp.data.model.Movie;
import com.example.comp.moviesapp.database.DatabaseManager;
import com.example.comp.moviesapp.interfaces.AddListener;
import com.example.comp.moviesapp.interfaces.MovieClickListener;
import com.example.comp.moviesapp.network.NetworkManager;
import com.example.comp.moviesapp.presentation.AllMoviesPresenter;
import com.example.comp.moviesapp.ui.adapters.MoviesAdapter;
import com.example.comp.moviesapp.ui.movie_info.MovieInfoActivity;
import com.example.comp.moviesapp.ui.movies_watchlist.WatchListActivity;
import com.example.comp.moviesapp.utils.DialogUtils;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AllMoviesActivity extends AppCompatActivity implements AllMoviesInterface.View, MovieClickListener, AddListener {

    @BindView(R.id.verify_text)
    ImageView verifyText;

    @BindView(R.id.search_button)
    EditText searchText;

    @BindView(R.id.film_list)
    Button filmList;

    @BindView(R.id.watchlist_button)
    Button watchList;

    @BindView(R.id.no_data_movie_search)
    TextView noData;

    @BindView(R.id.movies_list)
    RecyclerView moviesList;

    @BindString(R.string.blank_error)
    String blankField;

    MoviesAdapter moviesAdapter;
    AllMoviesInterface.Presenter presenter;

    public static Intent getLaunchIntent(Context from) {
        Intent intent = new Intent(from, AllMoviesActivity.class);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_movies_view);

        setUI();

        presenter = new AllMoviesPresenter(NetworkManager.getInstance(), DatabaseManager.getDatabaseInstance());
        presenter.setView(this);
        presenter.viewReady();
    }

    private void setUI() {
        ButterKnife.bind(this);

        filmList.setClickable(false);

        moviesAdapter = new MoviesAdapter();
        moviesAdapter.setMovieClickListener(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        moviesList.setLayoutManager(layoutManager);
        moviesList.setAdapter(moviesAdapter);
    }

    @Override
    public void showMoviesList(List<Movie> movieList) {
        moviesList.setVisibility(View.VISIBLE);
        moviesAdapter.setMoviesList(movieList);
    }

    @Override
    public void showNoDataText() {
        noData.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoDataText() {
        noData.setVisibility(View.GONE);
    }

    @Override
    public void hideMoviesList() {
        moviesList.setVisibility(View.GONE);
    }

    @OnClick(R.id.verify_text)
    public void onVerifyClicked() {
        presenter.checkInput(searchText.getText().toString());
    }

    @Override
    public void searchedTermSuccess(String movieName) {
        presenter.onSearchedTermSuccess(movieName);
    }

    @Override
    public void showConnectionError() {
        Toast.makeText(this, R.string.connection_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAddDialog(int id) {
        DialogUtils.showAddDialog(this, id, this);
    }

    @OnClick(R.id.watchlist_button)
    public void watchlistClicked() {
        presenter.watchlistClicked();
    }

    @Override
    public void onItemClicked(int id) {
        presenter.onItemClicked(id);
    }

    @Override
    public void onItemLongClicked(int id) {
        presenter.onItemLongClicked(id);
    }

    @Override
    public void onAddClicked(int id) {
        presenter.onMovieChosen(id);
    }

    @Override
    public void onSearchedTermError() {
        searchText.setError(blankField);
    }

    @Override
    public void movieAdded() {
        Toast.makeText(this, R.string.movie_added, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToMovieInfo(int id) {
        int flagFromAllMovies = 1;
        startActivity(MovieInfoActivity.getLaunchIntent(this, id, flagFromAllMovies));
    }

    @Override
    public void navigateToWatchlist() {
        startActivity(WatchListActivity.getLaunchIntent(this));
    }
}