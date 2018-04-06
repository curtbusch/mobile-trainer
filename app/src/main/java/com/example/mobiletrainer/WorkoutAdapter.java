package com.example.mobiletrainer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by curtd on 3/25/2018.
 */

public class WorkoutAdapter extends ArrayAdapter<Workout> {
    private final Context context;
    private final ArrayList<Workout> itemsArrayList;


    public WorkoutAdapter(Context context, ArrayList<Workout> itemsArrayList) {
        super(context, android.R.layout.simple_list_item_1, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.workout_row, parent, false);

        // 3. Get the two text view from the rowView
        TextView labelView = (TextView) rowView.findViewById(R.id.label);

        // 4. Set the text for textView
        labelView.setText(itemsArrayList.get(position).getName());
        // 5. retrn rowView
        return rowView;
    }
}
