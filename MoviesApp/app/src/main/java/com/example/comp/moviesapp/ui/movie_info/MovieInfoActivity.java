package com.example.comp.moviesapp.ui.movie_info;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comp.moviesapp.R;
import com.example.comp.moviesapp.constants.Constants;
import com.example.comp.moviesapp.database.DatabaseManager;
import com.example.comp.moviesapp.network.NetworkManager;
import com.example.comp.moviesapp.presentation.MovieInfoPresenter;
import com.example.comp.moviesapp.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieInfoActivity extends AppCompatActivity implements MovieInfoInterface.View {

    private static String KEY_ID_MOVIE_INFO = "id";
    private static String KEY_FLAG_MOVIE_INFO = "flag";

    @BindView(R.id.movie_poster)
    ImageView moviePoster;

    @BindView(R.id.movie_name)
    TextView movieName;

    @BindView(R.id.movie_year)
    TextView movieYear;

    @BindView(R.id.movie_plot)
    TextView moviePlot;

    @BindView(R.id.movie_rating)
    TextView movieRating;

    MovieInfoInterface.Presenter presenter;

    public static Intent getLaunchIntent(Context from, int id, int flag) {
        Intent intent = new Intent(from, MovieInfoActivity.class);
        intent.putExtra(KEY_ID_MOVIE_INFO, id);
        intent.putExtra(KEY_FLAG_MOVIE_INFO, flag);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        presenter = new MovieInfoPresenter(NetworkManager.getInstance(), DatabaseManager.getDatabaseInstance());
        presenter.setView(this);

        setUI();
        getExtras();
    }

    private void setUI() {
        ButterKnife.bind(this);
    }

    private void getExtras() {
        if (getIntent().hasExtra(KEY_FLAG_MOVIE_INFO) && getIntent().hasExtra(KEY_ID_MOVIE_INFO)) {
            if (getIntent().getIntExtra(KEY_FLAG_MOVIE_INFO, -1) == 1) {
                presenter.viewReadyFromAllMovies(getIntent().getIntExtra(KEY_ID_MOVIE_INFO, -1));
            } else {
                presenter.viewReadyFromWatchlist(getIntent().getIntExtra(KEY_ID_MOVIE_INFO, -1));
            }
        }
    }

    @OnClick(R.id.back_button)
    public void backClicked() {
        presenter.onBackClicked();
    }

    @Override
    public void navigateBack() {
        finish();
    }

    @Override
    public void showNoIdError() {
        Toast.makeText(this, R.string.no_id_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNoMovieDatabaseError() {
        Toast.makeText(this, R.string.database_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showConnectionError() {
        Toast.makeText(this, R.string.connection_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMoviePicture(String poster) {
        ImageUtils.setPicture(moviePoster, Constants.BASE_URL_PATH + poster);
    }

    @Override
    public void showMovieTitle(String title) {
        movieName.setText(title);
    }

    @Override
    public void showMoviePlot(String plot) {
        moviePlot.setText(String.format(getString(R.string.movie_plot), plot));
    }

    @Override
    public void showMovieYear(String year) {
        movieYear.setText(String.format(getString(R.string.movie_year), year));
    }

    @Override
    public void showMovieVote(double vote_average) {
        movieRating.setText(String.format(getString(R.string.movie_rating), vote_average));
    }
}