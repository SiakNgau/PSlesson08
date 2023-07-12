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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSingers, etYear;
    RadioGroup rg;
    Button btnInsert, btnList;

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
                db.close();
                Toast.makeText(MainActivity.this, "New song inserted successfully", Toast.LENGTH_SHORT).show();
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SongList.class);
                startActivity(intent);
            }
        });

    }
}

