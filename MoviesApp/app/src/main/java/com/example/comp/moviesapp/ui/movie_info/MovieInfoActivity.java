package com.example.comp.moviesapp.ui.movie_info;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.comp.moviesapp.R;

public class MovieInfoActivity extends AppCompatActivity {

    private static String KEY_ID_MOVIE_INFO = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info_view);
    }

    public static Intent getLaunchIntent(Context from, int id) {
        Intent intent = new Intent(from, MovieInfoActivity.class);
        intent.putExtra(KEY_ID_MOVIE_INFO, id);

        return intent;
    }
}
