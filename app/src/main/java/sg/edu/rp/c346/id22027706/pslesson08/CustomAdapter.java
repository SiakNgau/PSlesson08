package sg.edu.rp.c346.id22027706.pslesson08;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import sg.edu.rp.c346.id22027706.pslesson08.songs;
public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<songs> List;

    public CustomAdapter(Context context, int resource, ArrayList<songs> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        List = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.textViewTitle);
        TextView tvSingers = rowView.findViewById(R.id.textViewSingers);
        TextView tvYearsReleased = rowView.findViewById(R.id.textViewyearsReleased);
        TextView tvStars = rowView.findViewById(R.id.textViewStars);

        // Obtain the Android Version information based on the position
        songs currentVersion = List.get(position);

        // Set values to the TextView to display the corresponding information
        tvTitle.setText(currentVersion.getTitle());
        tvSingers.setText(currentVersion.getSinger());
        tvYearsReleased.setText(""+currentVersion.getYear());

        int stars = Integer.parseInt(currentVersion.getStars());
        tvStars.setText("   "+stars+" Stars");

        tvTitle.setTextColor(Color.rgb(0,0,0));
        tvSingers.setTextColor(Color.rgb(128,128,128));
        tvYearsReleased.setTextColor(Color.rgb(255,0,0));

        return rowView;
    }


}
