package com.example.whattoweartonight;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import android.app.ListActivity;

import android.widget.ArrayAdapter;

public class MainActivity extends Activity {

	   private EditText  username=null;
	   private EditText  password=null;
	   private TextView attempts;
	   private Button login, viewA, createE, homeB, newHome;
	   int counter = 3;
	   @Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_main);

	      login = (Button)findViewById(R.id.button2);
	      viewA = (Button)findViewById(R.id.button3);
          createE = (Button)findViewById(R.id.button1);
          homeB = (Button)findViewById(R.id.button4);
           newHome = (Button)findViewById(R.id.button5);
           newHome.setOnClickListener(new View.OnClickListener() {
               public void onClick(View v) {

                   setContentView(R.layout.newhome);
                   Button homeView = (Button)findViewById(R.id.button);
                   homeView.setOnClickListener(new View.OnClickListener() {
                       public void onClick(View v) {


                           Intent intent = new Intent(".HOME");
                           startActivity(intent);
                       }
                   });

                   Button register = (Button)findViewById(R.id.button3);
                   register.setOnClickListener(new View.OnClickListener() {
                       public void onClick(View v) {


                           Intent intent = new Intent(".REGISTER");
                           startActivity(intent);
                       }
                   });

               }
           });
           createE.setOnClickListener(new View.OnClickListener() {
               public void onClick(View v) {

                   setContentView(R.layout.create);
                   Button takeP = (Button)findViewById(R.id.button);
                   takeP.setOnClickListener(new View.OnClickListener() {
                       public void onClick(View v) {


                           Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                           startActivityForResult(intent, 0);
                       }
                   });
                   Button saveE = (Button)findViewById(R.id.button2);
                   saveE.setOnClickListener(new View.OnClickListener() {
                       public void onClick(View v) {


                           Intent intent = new Intent(".SAVE");
                           startActivity(intent);
                       }
                   });

               }
           });
	   }


public void sendEvents(View v) {
	 //ListView listView = (ListView) findViewById(R.id.list);
	 
     
     // Defined Array values to show in ListView
   /*  String[] values = new String[] { "Android List View", 
                                      "Adapter implementation",
                                      "Simple List View In Android",
                                      "Create List View Android", 
                                      "Android Example", 
                                      "List View Source Code", 
                                      "List View Array Adapter", 
                                      "Android Example List View" 
                                     };

     // Define a new Adapter
     // First parameter - Context
     // Second parameter - Layout for the row
     // Third parameter - ID of the TextView to which the data is written
     // Forth - the Array of data

     ArrayAdapter<String> adapter = new ArrayAdapter<String>(
             this, 
     android.R.layout.simple_list_item_1, // standard row layout provided by android
     values);
     listView.setAdapter(adapter);
*/	 
	
	  Intent intent = new Intent(this, EventsListActivity.class);
	  startActivity(intent);
}   

public void sendMessage(View v) {
	        	
    Intent intent = new Intent(this, LoginActivity.class);
	 startActivity(intent);
	 }

    public void goHome(View v) {



        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
	 
	   }


