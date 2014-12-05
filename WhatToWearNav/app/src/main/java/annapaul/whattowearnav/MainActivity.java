package annapaul.whattowearnav;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pavlick on 14-11-24.
 */
public class MainActivity extends Activity {
    public static final String PREFS_NAME = "UserPrefs";
    private EditText username=null;
    private EditText  password=null;
    private static Button login, register;
    private static String uname, pass;
    /**
     * Variable managing the session for being logged in to the app
     */
    static public boolean loggedIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean loggedIn = settings.getBoolean("loggedIn", false);

        // TODO: check if the person is loggedIn from shared_pref
        if (!loggedIn) {
            // TODO: add 'forgot password' on login screen!
            setContentView(R.layout.login);

            username = (EditText)findViewById(R.id.username);
            password = (EditText)findViewById(R.id.password);
            login = (Button)findViewById(R.id.login_button);
            register = (Button)findViewById(R.id.register_button);
            // login button
            //Button login = (Button) findViewById(R.id.login_button);
            login.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    //TODO: start login activity in which we update the shared preferences
                    new Login().execute();

                }
            });
            // register button
            /*
            Button register = (Button) findViewById(R.id.button3);
            register.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {


                    //Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    //startActivityForResult(intent, 0);
                }
            });*/
        } else {
            Intent intent = new Intent(this, annapaul.whattowearnav.HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }

    class Login extends AsyncTask<String, String, String> {

        private ProgressDialog pDialog;
        JSONParser jsonParser = new JSONParser();
        private String url_login = "http://scrapperia.com/android/login_details.php";
        private static final String TAG_SUCCESS = "success";
        public static final String PREFS_NAME = "UserPrefs";

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Logging in..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * Creating product
         */
        protected String doInBackground(String... args) {
            FragmentManager fragmentManager;
            Fragment fragment;
            uname = username.getText().toString();
            pass = password.getText().toString();
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("uname", uname));
            params.add(new BasicNameValuePair("pass", pass));
            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_login,
                    "POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {

                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    pDialog.dismiss();

                    // successfully created product
                    // We need an Editor object to make preference changes.
                    // All objects are from android.context.Context
                    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("loggedIn", true);
                    editor.putString("username", uname);
                    editor.putString("password", pass);
                    // Commit the edits!
                    editor.commit();

                    Intent intent = new Intent(MainActivity.this, annapaul.whattowearnav.HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                    finish();

                } else {
                    // failed to create product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * *
         */
        protected void onPostExecute(String file_url) {
            pDialog.dismiss();

        }

    }
}
