package com.example.whattoweartonight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by Anna on 2014-10-21.
 */
public class RegisterActivity extends Activity {
    private static final int SELECT_PHOTO = 100;
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.register);

        Button takePhoto = (Button) findViewById(R.id.button);
        Button register = (Button) findViewById(R.id.button2);
    }


    public void takePhoto(View v) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);


    }

    public void registerPro(View v) {
        setContentView(R.layout.view_profile);
        Button deletePro = (Button) findViewById(R.id.button3);
        deletePro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent intent = new Intent(".DELETE");
                startActivity(intent);
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

}
