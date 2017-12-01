package com.example.comp.moviesapp.ui.movies_watchlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comp.moviesapp.R;
import com.example.comp.moviesapp.data.model.Movie;
import com.example.comp.moviesapp.database.DatabaseManager;
import com.example.comp.moviesapp.interfaces.DeleteListener;
import com.example.comp.moviesapp.interfaces.MovieClickListener;
import com.example.comp.moviesapp.presentation.WatchListPresenter;
import com.example.comp.moviesapp.ui.adapters.MoviesAdapter;
import com.example.comp.moviesapp.ui.all_movies.AllMoviesActivity;
import com.example.comp.moviesapp.ui.movie_info.MovieInfoActivity;
import com.example.comp.moviesapp.utils.DialogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WatchListActivity extends AppCompatActivity implements WatchListInterface.View, MovieClickListener, DeleteListener {

    @BindView(R.id.film_list)
    Button filmList;

    @BindView(R.id.watchlist)
    Button watchList;

    @BindView(R.id.movies_list)
    RecyclerView moviesList;

    @BindView(R.id.no_data)
    TextView noData;

    WatchListInterface.Presenter presenter;
    MoviesAdapter moviesAdapter;

    public static Intent getLaunchIntent(Context from) {
        Intent intent = new Intent(from, WatchListActivity.class);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist_view);

        presenter = new WatchListPresenter(DatabaseManager.getDatabaseInstance());
        presenter.setView(this);

        setUI();
        presenter.viewReady();
    }

    private void setUI() {
        ButterKnife.bind(this);
        watchList.setClickable(false);

        moviesAdapter = new MoviesAdapter();
        moviesAdapter.setMovieClickListener(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        moviesList.setLayoutManager(layoutManager);
        moviesList.setAdapter(moviesAdapter);
    }

    @OnClick(R.id.film_list)
    public void onFilmListClicked() {
        presenter.onFilmListClicked();
    }

    @Override
    public void showMoviesList(List<Movie> moviesList) {
        moviesAdapter.setMoviesList(moviesList);
    }

    @Override
    public void showNoData() {
        noData.setVisibility(View.VISIBLE);
        Toast.makeText(this, R.string.no_movies_database, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMovieDeletedInfo() {
        Toast.makeText(this, R.string.movie_deleted, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDeleteDialog(int id) {
        DialogUtils.showDeleteDialog(this, id, this);
    }

    @Override
    public void onDeleteClicked(int id) {
        presenter.onDeleteClicked(id);
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
    public void navigateToMovieInfo(int id) {
        int flagFromWatchlist = 2;
        startActivity(MovieInfoActivity.getLaunchIntent(this, id, flagFromWatchlist));
    }

    @Override
    public void navigateToFilmList() {
        startActivity(AllMoviesActivity.getLaunchIntent(this));
    }
}
