package com.example.whattoweartonight;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EventActivity extends Activity {
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.event);
        String[] values = new String[] { "Anna Fatsevych", "Andrey Guzenko", "David Novodchuk",
                "Peter Liu", "Peter McIntyre", "Parvin Soleymani" };
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
         //       android.R.layout.simple_list_item_1, values);
        ListView lv=(ListView)findViewById(R.id.listView);
        lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , values));


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
}