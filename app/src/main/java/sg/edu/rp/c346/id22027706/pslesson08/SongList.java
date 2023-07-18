package sg.edu.rp.c346.id22027706.pslesson08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SongList extends AppCompatActivity {

    Button btnReturn, btnfiveStars;
    ListView lv;
    ArrayAdapter<String> aa;
    ArrayList<songs> arrList;
    boolean filter;
    CustomAdapter adapter;

    @Override
    protected void onResume() {
        super.onResume();
        //aa = new ArrayAdapter(SongList.this, android.R.layout.simple_list_item_1, arrList);
        //lv.setAdapter(aa);
        adapter = new CustomAdapter(this, R.layout.row, arrList);
        lv.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        btnReturn = findViewById(R.id.btnReturn);
        btnfiveStars = findViewById(R.id.btn5Stars);
        lv = findViewById(R.id.lv);

        filter = false;

        DBHelper db = new DBHelper(SongList.this);
        db.close();
        DBHelper lv = new DBHelper(SongList.this);
        arrList = lv.getSongs();
        lv.close();

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SongList.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnfiveStars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SongList.this);
                arrList.clear();
                if(!filter){
                    arrList.addAll(dbh.getFiveStarSongs());
                    filter = true;
                    btnfiveStars.setText("Show all songs");
                }
                else {
                    arrList.addAll(dbh.getSongs());
                    filter = false;
                    btnfiveStars.setText("Show all songs with 5 stars");
                }
                aa.notifyDataSetChanged();
            }
        });

    }

}