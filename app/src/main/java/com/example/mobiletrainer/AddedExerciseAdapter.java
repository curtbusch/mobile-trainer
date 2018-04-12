package com.example.mobiletrainer;

/**
 * Created by curtd on 2/21/2018.
 */

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AddedExerciseAdapter extends ArrayAdapter<Exercise> {
    private final Context context;
    private final ArrayList<Exercise> itemsArrayList;


    public AddedExerciseAdapter(Context context, ArrayList<Exercise> itemsArrayList) {

        super(context, R.layout.addedexercise_row, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.addedexercise_row, parent, false);

        // 3. Get the two text view from the rowView
        TextView labelView = (TextView) rowView.findViewById(R.id.label);
        TextView valueView = (TextView) rowView.findViewById(R.id.value);


        // 4. Set the text for textView
        labelView.setText(itemsArrayList.get(position).getName());
        valueView.setText(Integer.toString(itemsArrayList.get(position).getSets()) + " x " + Integer.toString(itemsArrayList.get(position).getReps()));

        // 5. return rowView
        return rowView;
    }
}