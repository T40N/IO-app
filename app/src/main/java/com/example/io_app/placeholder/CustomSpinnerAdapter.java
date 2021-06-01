package com.example.io_app.placeholder;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.io_app.R;
import com.example.io_app.UserDB;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CustomSpinnerAdapter extends ArrayAdapter<UserDB> {
    LayoutInflater layoutInflater;

    public CustomSpinnerAdapter(@NonNull Context context, int resource, @NonNull ArrayList<UserDB> users) {
        super(context, resource, users);

        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = layoutInflater.inflate(R.layout.custom_spinner_adapter,null,true);
        UserDB user = getItem(position);
        TextView tVName = rowView.findViewById(R.id.tV_spinnerName);
        tVName.setText(user.getName() + " " + user.getSurname());
        notifyDataSetChanged();
        return rowView;
    }

    @Override
    public View getDropDownView(int position, @Nullable @org.jetbrains.annotations.Nullable View convertView, @NonNull @NotNull ViewGroup parent) {
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.custom_spinner_adapter,parent,false);
        }
        UserDB user = getItem(position);
        TextView tVName = convertView.findViewById(R.id.tV_spinnerName);
        tVName.setText(user.getName() + " " + user.getSurname());
        return convertView;
    }
}
