package sg.edu.rp.c346.id22027706.pslesson08;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class songs extends AppCompatActivity {



}

    // Insert a task
    ArrayList<List> lists = db.getLists();
                db.close();

                        aaResults = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, lists);
                        lv.setAdapter(aaResults);