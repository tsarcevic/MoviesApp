package com.example.comp.moviesapp.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.comp.moviesapp.R;
import com.example.comp.moviesapp.interfaces.AddListener;

/**
 * Created by COMP on 21.11.2017..
 */

public class DialogUtils {

    public static void showDialog(Context from, final int id, final AddListener listener) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(from);

        alertDialog.setMessage(R.string.dialog_message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onAddClicked(id);
                    }
                });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertShowDialog = alertDialog.create();
        alertShowDialog.show();

    }
}
