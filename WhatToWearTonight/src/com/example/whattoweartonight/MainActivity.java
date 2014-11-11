package com.example.whattoweartonight;


import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

// Sources: Darcey & Conder; Vogel's Tutorial
// Enhanced and Annotated by Peter Liu

// ActionBar.TabListener: abstract class for handling a tab (deprecated in API level 21)
public class MainActivity extends Activity
        implements ActionBar.TabListener {
    private boolean isHidden = false;
    private Button hideActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getActionBar();

        // view control of an action bar: tabs
        actionBar.setNavigationMode( ActionBar.NAVIGATION_MODE_TABS );

        // for each top-level view in the app, add a tab to the action bar.
        actionBar.addTab(
                actionBar.newTab().setText("Tab1")
                        .setTabListener(this) );

        actionBar.addTab(actionBar.newTab().setText("Tab2")
                .setTabListener(this));

        actionBar.addTab(actionBar.newTab().setText("Tab3")
                .setTabListener(this));

        // button
        hideActionBar = (Button) findViewById(R.id.hide);
        hideActionBar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isHidden) {
                    getActionBar().hide();
                    hideActionBar.setText("Show Action Bar");
                } else {
                    getActionBar().show();
                    hideActionBar.setText("Hide Bar");
                }
                isHidden = !isHidden;
            }
        });
    }

    @Override
    public void onTabSelected( ActionBar.Tab tab,
                               FragmentTransaction fragmentTransaction) {

        Toast.makeText( this, tab.getText() + " Clicked", Toast.LENGTH_SHORT ).show();
    }


    @Override
    public void onTabUnselected(ActionBar.Tab tab,
                                FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab,
                                FragmentTransaction fragmentTransaction) {
    }

    /* add actions items to the action bar
     * - inflate /res/menu/simple_action_bar.xml
     */
    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {

        // inflate the menu: add action items to the action bar if it is visible
        getMenuInflater().inflate(R.menu.whatmenu, menu);
        return true;
    }

    /* event handling of the selection of an action item (menu item)
     * - only 3 out 4 action items are handled
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch ( item.getItemId() ) {
/*
            case R.id.menu_add:
                Toast.makeText(this, "Add Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_close:
                finish();  // stop running the activity
                return true;
            case R.id.menu_help:
                Toast.makeText(this, "Help Clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
                */
        }
        return true;
    }
}

/*
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;public class MainActivity extends FragmentActivity {

	   private EditText  username=null;
	   private EditText  password=null;
	   private TextView attempts;
	   private Button login, viewA, createE, homeB, newHome;
	   int counter = 3;

        @Override
        public String getSubtitle(){
            return "test";
        }

        @Override
        public void addOnMenuVisibilityListener(OnMenuVisibilityListener listener){

        }

        @Override
        public int getTabCount(){
            return 5;
        }

        @Override
        public void addTab(Tab a, int b)
        {
            //return a;
        }

        @Override
        public void setCustomView(int a){

        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu items for use in the action bar
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.main, menu);
            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public Tab getSelectedTab(){
            return null;
        }

        @Override
        public void hide(){

        }

	   @Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_main);

           //ActionBar actionBar = getSupportActionBar();
           //actionBar.setDisplayHomeAsUpEnabled(true);


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
/*
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

	 
	   }


*/