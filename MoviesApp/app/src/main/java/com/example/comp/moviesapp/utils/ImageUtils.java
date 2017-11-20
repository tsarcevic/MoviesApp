package com.example.comp.moviesapp.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by COMP on 20.11.2017..
 */

public class ImageUtils {

    public static void setPicture(ImageView target, String url) {
        Glide.with(target.getContext())
                .load(url)
                .into(target);
    }
}
