package com.viettel.gsct.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.widget.DatePicker;

/**
 * Created by admin2 on 06/04/2017.
 */

public class DialogUtils {
    public static void showDateTimePicker(Context context, final AppCompatEditText source, DialogInterface.OnDismissListener dismissListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, android.support.v7.appcompat.R.style.Base_Theme_AppCompat_Light_Dialog_Alert);
        final LayoutInflater inflater = LayoutInflater.from(context);
        final DatePicker view = new DatePicker(context);

        builder.setView(view);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                source.setText(view.getDayOfMonth() + "/"+view.getMonth() + "/"+ view.getYear());
            }
        });

        builder.setNegativeButton("Đóng", null);
        Dialog dialog = builder.create();
        dialog.setOnDismissListener(dismissListener);
        dialog.show();
    }

    public static void showCheckin(Context context, DialogInterface.OnDismissListener dismissListener, DialogInterface.OnClickListener saveListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, android.support.v7.appcompat.R.style.Base_Theme_AppCompat_Light_Dialog_Alert);
        final LayoutInflater inflater = LayoutInflater.from(context);
        final AppCompatEditText view = new AppCompatEditText(context);

        builder.setView(view);

        builder.setNegativeButton("Đóng", null);
        builder.setPositiveButton("Lưu", saveListener);
        Dialog dialog = builder.create();
        dialog.setOnDismissListener(dismissListener);
        dialog.show();
    }
}
