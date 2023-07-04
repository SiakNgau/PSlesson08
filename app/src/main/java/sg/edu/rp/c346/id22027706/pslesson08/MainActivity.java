package sg.edu.rp.c346.id22027706.pslesson08;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import kotlinx.coroutines.scheduling.Task;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSingers, etYear;
    RadioGroup rg;
    Button btnInsert, btnList, btn1, btn2, btn3, btn4, btn5;
    ArrayAdapter<String> aaResults;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rg = findViewById(R.id.rgStars);
        btnInsert = findViewById(R.id.btnInsert);
        btnList = findViewById(R.id.btnList);
        btn1 = findViewById(R.id.radioButton1);
        btn2 = findViewById(R.id.radioButton2);
        btn3 = findViewById(R.id.radioButton3);
        btn4 = findViewById(R.id.radioButton4);
        btn5 = findViewById(R.id.radioButton5);
        lv = findViewById(R.id.lv);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);

                //stars
                String stars = "0";
                int checkedRadioId = rg.getCheckedRadioButtonId();
                if (checkedRadioId == R.id.radioButton1) {
                    stars = "1";
                } else if (checkedRadioId == R.id.radioButton2) {
                    stars = "2";
                } else if (checkedRadioId == R.id.radioButton3) {
                    stars = "3";
                } else if (checkedRadioId == R.id.radioButton4) {
                    stars = "4";
                } else if (checkedRadioId == R.id.radioButton5) {
                    stars = "5";
                }


                db.insertTask(etTitle.getText().toString(), etSingers.getText().toString(), etYear.getText().toString(), stars);

            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                Intent intent = new Intent(MainActivity.this, songs.class);
                startActivity(intent);

                // Insert a task
                ArrayList<List> lists = db.getLists();
                db.close();

                aaResults = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, lists);
                lv.setAdapter(aaResults);
            }
        });

    }
}

