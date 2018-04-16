package com.example.mobiletrainer;

/**
 * Created by curtd on 2/21/2018.
 */

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ExerciseAdapter extends ArrayAdapter<Exercise> {
    private final Context context;
    private final ArrayList<Exercise> itemsArrayList;


    public ExerciseAdapter(Context context, ArrayList<Exercise> itemsArrayList) {

        super(context, R.layout.row, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.row, parent, false);

        // 3. Get the two text view from the rowView
        TextView labelView = (TextView) rowView.findViewById(R.id.label);
        TextView valueView = (TextView) rowView.findViewById(R.id.value);


        // 4. Set the text for textView
        labelView.setText(itemsArrayList.get(position).getName());
        valueView.setText(itemsArrayList.get(position).getCategory());

        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        boolean largeText = getPrefs.getBoolean("largetext", false);


        if(largeText) {
            labelView.setTextSize(30);
        }
        else {
            labelView.setTextSize(20);
        }

        // 5. u rowView
        return rowView;
    }
}