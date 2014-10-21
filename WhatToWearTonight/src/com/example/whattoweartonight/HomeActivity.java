package com.example.whattoweartonight;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import com.example.whattoweartonight.Lists;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import android.app.ListActivity;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import static android.widget.LinearLayout.*;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HomeActivity extends Activity {
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.home);
        String[] values = new String[]{"Birthday Party   ", "Wedding", "Concert",
                "Amazing Event", "Another Awesome Event", "Not-So-Awesome Event"};
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        //       android.R.layout.simple_list_item_1, values);
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values));

        Button createE = (Button) findViewById(R.id.button4);

        createE.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                setContentView(R.layout.create);
                Button takeP = (Button) findViewById(R.id.button);
                takeP.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {


                        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                        startActivityForResult(intent, 0);
                    }
                });
                Button saveE = (Button) findViewById(R.id.button2);
                saveE.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {


                        Intent intent = new Intent(".SAVE");
                        startActivity(intent);
                    }
                });

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
}