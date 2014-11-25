package annapaul.whattowearnav;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by pavlick on 14-11-24.
 */
public class MainActivity extends Activity {

    static public boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: check if the person is loggedIn from shared_pref
        if (!loggedIn)
            // TODO: add 'forgot password' on login screen!
            setContentView(R.layout.login);
        else {
            Intent intent = new Intent(this, annapaul.whattowearnav.HomeActivity.class);
            startActivity(intent);
        }
    }
}
