package com.example.comp.moviesapp.ui.movies_watchlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comp.moviesapp.R;
import com.example.comp.moviesapp.constants.Constants;
import com.example.comp.moviesapp.data.model.Movie;
import com.example.comp.moviesapp.database.DatabaseManager;
import com.example.comp.moviesapp.interfaces.DeleteListener;
import com.example.comp.moviesapp.interfaces.MovieClickListener;
import com.example.comp.moviesapp.presentation.WatchListPresenter;
import com.example.comp.moviesapp.ui.adapters.MoviesAdapter;
import com.example.comp.moviesapp.ui.movie_info.MovieInfoActivity;
import com.example.comp.moviesapp.utils.DialogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by COMP on 10.1.2018..
 */

public class MovieWatchlistFragment extends Fragment implements WatchListInterface.View, MovieClickListener, DeleteListener {

    @BindView(R.id.movies_list)
    RecyclerView moviesList;

    @BindView(R.id.no_data)
    TextView noData;

    WatchListInterface.Presenter presenter;
    MoviesAdapter moviesAdapter;

    public static MovieWatchlistFragment newInstance() {
        return new MovieWatchlistFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_watchlist, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        setUI(view);

        presenter = new WatchListPresenter(DatabaseManager.getDatabaseInstance());

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
    public void showMoviesList(List<Movie> moviesList) {
        moviesAdapter.setMoviesList(moviesList);
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
    public void showMovieDeletedInfo() {
        Toast.makeText(getActivity(), R.string.movie_deleted, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDeleteDialog(int id) {
        DialogUtils.showDeleteDialog(getActivity(), id, this);
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
        startActivity(MovieInfoActivity.getLaunchIntent(getActivity(), id, Constants.WATCHLIST_MOVIES_FLAG));
    }

    @Override
    public void removeMovie(int id) {
        moviesAdapter.removeMovie(id);
        presenter.onMovieDeleted();
    }
}
