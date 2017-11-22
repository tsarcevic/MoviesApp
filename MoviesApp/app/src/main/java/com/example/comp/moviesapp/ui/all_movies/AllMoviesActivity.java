package com.example.comp.moviesapp.ui.all_movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

    @BindView(R.id.watchlist)
    Button watchList;

    @BindView(R.id.movies_list)
    RecyclerView moviesList;

    @BindString(R.string.blank_error)
    String blankField;

    MoviesAdapter moviesAdapter;
    AllMoviesInterface.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_movies_view);

        presenter = new AllMoviesPresenter(NetworkManager.getInstance(), DatabaseManager.getDatabaseInstance());
        presenter.setView(this);

        setUI();
        presenter.viewReady();
    }

    private void setUI() {
        ButterKnife.bind(this);

        moviesAdapter = new MoviesAdapter();
        moviesAdapter.setMovieClickListener(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        moviesList.setLayoutManager(layoutManager);
        moviesList.setAdapter(moviesAdapter);
    }

    @Override
    public void showMoviesList(List<Movie> moviesList) {
        moviesAdapter.setMoviesList(moviesList);
    }

    @OnClick(R.id.verify_text)
    public void onVerifyCliked() {
        presenter.checkInput(searchText.getText().toString());
    }

    @Override
    public void searchedTermSuccess() {
        presenter.fetchData();
    }

    @Override
    public void showConnectionError() {
        Toast.makeText(this, R.string.connection_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAddDialog(int id) {
        DialogUtils.showDialog(this, id, this);
    }

    @Override
    public void onItemClicked(int id) {
        presenter.itemClicked(id);
    }

    @Override
    public void onItemLongClicked(int id) {
        presenter.itemLongClicked(id);
    }

    @Override
    public void onAddClicked(int id) {
        presenter.onMovieChosen(id);
    }

    @Override
    public void searchedTermError() {
        searchText.setError(blankField);
    }

    @Override
    public void movieAdded() {
        Toast.makeText(this, R.string.movie_added, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToMovieInfo(int id) {
        startActivity(MovieInfoActivity.getLaunchIntent(this, id));
    }
}
