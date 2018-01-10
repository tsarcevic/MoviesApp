package com.example.comp.moviesapp.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.comp.moviesapp.R;
import com.example.comp.moviesapp.interfaces.AddListener;
import com.example.comp.moviesapp.interfaces.DeleteListener;

/**
 * Created by COMP on 21.11.2017..
 */

public class DialogUtils {

    public static void showAddDialog(Context from, final int id, final AddListener listener) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(from);

        alertDialog.setMessage(R.string.dialog_add_message)
                .setPositiveButton(R.string.dialog_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onAddClicked(id);
                    }
                });

        alertDialog.setNegativeButton(R.string.dialog_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertShowDialog = alertDialog.create();
        alertShowDialog.show();
    }

    public static void showDeleteDialog(Context from, final int id, final DeleteListener listener) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(from);

        alertDialog.setMessage(R.string.dialog_remove_message)
                .setPositiveButton(R.string.dialog_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onDeleteClicked(id);
                    }
                });

        alertDialog.setNegativeButton(R.string.dialog_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertShowDialog = alertDialog.create();
        alertShowDialog.show();
    }
}
