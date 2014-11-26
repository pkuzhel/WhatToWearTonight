package annapaul.whattowearnav;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by pavlick on 14-11-24.
 */
public class MainActivity extends Activity {

    /**
     * Variable managing the session for being logged in to the app
     */
    static public boolean loggedIn = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: check if the person is loggedIn from shared_pref
        if (!loggedIn)
            // TODO: add 'forgot password' on login screen!
            setContentView(R.layout.login);
        else {
            Intent intent = new Intent(this, annapaul.whattowearnav.HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }
}
