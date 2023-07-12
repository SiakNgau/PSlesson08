package sg.edu.rp.c346.id22027706.pslesson08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class thirdActivity extends AppCompatActivity {
    Button btnUpdate, btnDelete, btnCancel;
    EditText title, singer, year, id;
    RadioGroup rating;
    songs data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        id = findViewById(R.id.etSongID);
        title = findViewById(R.id.etTitle);
        singer = findViewById(R.id.etSinger);
        year = findViewById(R.id.etYear);
        rating = findViewById(R.id.rgStar);

        Intent i = getIntent();
        data = (songs) i.getSerializableExtra("data");

        id.setText(""+data.getId());
        title.setText(data.getTitle());
        singer.setText(data.getSinger());
        year.setText(""+data.getYear());
        String stars = data.getStars();
        if(stars.equals("1")){
            rating.check(R.id.rb1);
        } else if (stars.equals("2")) {
            rating.check(R.id.rb2);
        }else if (stars.equals("3")) {
            rating.check(R.id.rb3);
        }else if (stars.equals("4")) {
            rating.check(R.id.rb4);
        }else {
            rating.check(R.id.rb5);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(thirdActivity.this);
                data.setSinger(singer.getText().toString());
                data.setTitle(title.getText().toString());
                data.setYear(Integer.parseInt(year.getText().toString()));
                int ratingID = rating.getCheckedRadioButtonId();
                String star = "";
                if (ratingID == R.id.rb1) {
                    star = "1";
                } else if (ratingID == R.id.rb2) {
                    star = "2";
                } else if (ratingID == R.id.rb3) {
                    star = "3";
                } else if (ratingID == R.id.rb4) {
                    star = "4";
                } else {
                    star = "5";
                }
                data.setStars(star);
                dbh.updateSong(data);
                dbh.close();
                Toast.makeText(thirdActivity.this, "Update successful", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(thirdActivity.this, SongList.class);
                startActivity(i);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(thirdActivity.this);
                dbh.deleteSong(data.getId());
                Toast.makeText(thirdActivity.this, "Delete successful", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(thirdActivity.this, SongList.class);
                startActivity(i);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(thirdActivity.this, SongList.class);
                startActivity(i);
                Toast.makeText(thirdActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
    }}