package com.example.comp.moviesapp.ui.all_movies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comp.moviesapp.R;
import com.example.comp.moviesapp.constants.Constants;
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

/**
 * Created by COMP on 10.1.2018..
 */

public class AllMoviesFragment extends Fragment implements AllMoviesInterface.View, MovieClickListener, AddListener {

    @BindView(R.id.verify_text_button)
    ImageView verifyText;

    @BindView(R.id.search_button)
    EditText searchText;

    @BindView(R.id.no_data_movie_search)
    TextView noData;

    @BindView(R.id.movies_list)
    RecyclerView moviesList;

    @BindString(R.string.blank_error)
    String blankField;

    MoviesAdapter moviesAdapter;
    AllMoviesInterface.Presenter presenter;

    public static AllMoviesFragment newInstance() {
        return new AllMoviesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_movies, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUI(view);

        presenter = new AllMoviesPresenter(NetworkManager.getInstance(), DatabaseManager.getDatabaseInstance());
        presenter.setView(this);
        presenter.viewReady();
    }

    private void setUI(View view) {
        ButterKnife.bind(this, view);

        moviesAdapter = new MoviesAdapter();
        moviesAdapter.setMovieClickListener(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

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

    @OnClick(R.id.verify_text_button)
    public void onVerifyClicked() {
        presenter.checkInput(searchText.getText().toString());
    }

    @Override
    public void searchedTermSuccess(String movieName) {
        presenter.onSearchedTermSuccess(movieName);
    }

    @Override
    public void showConnectionError() {
        Toast.makeText(getActivity(), R.string.connection_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAddDialog(int id) {
        DialogUtils.showAddDialog(getActivity(), id, this);
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
        Toast.makeText(getActivity(), R.string.movie_added, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToMovieInfo(int id) {
        startActivity(MovieInfoActivity.getLaunchIntent(getActivity(), id, Constants.ALL_MOVIES_FLAG));
    }
}
