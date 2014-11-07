package com.example.whattoweartonight;


import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ExpandableListView;

public class EventsListActivity extends Activity {
 // more efficient than HashMap for mapping integers to objects
 SparseArray<Group> groups = new SparseArray<Group>();

 @Override
 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);

 setContentView(R.layout.view_event);


 createData();
 ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
 Lists adapter = new Lists(this,
 groups);
 listView.setAdapter(adapter);
 }

 public void createData() {
 for (int j = 0; j < 10; j++) {
 Group group = new Group("Amazing Event  " + j);
 for (int i = 0; i < 5; i++) {
 group.children.add("Sub Item" + i);
 }
 groups.append(j, group);
 }
 }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

 }

