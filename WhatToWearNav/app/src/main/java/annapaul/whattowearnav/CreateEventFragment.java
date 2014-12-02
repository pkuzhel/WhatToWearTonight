package annapaul.whattowearnav;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by pavlick on 14-11-24.
 */
public class CreateEventFragment extends Fragment {

    // Progress Dialog
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
    TextView inputName;
    EditText inputPrice;
    EditText inputDesc;

    // url to create new product
    private static String url_create_product = "http://scrapperia.com/android/create_product.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_event, null);

        // Edit Text
        inputName = (TextView) view.findViewById(R.id.name);
        // inputPrice = (EditText) findViewById(R.id.inputPrice);
        inputDesc = (EditText) view.findViewById(R.id.eventDescription);

        // Create button
        Button btnCreateProduct = (Button) view.findViewById(R.id.button2);

        // button click event
        btnCreateProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread
                new CreateNewProduct().execute();
            }
        });

        //Intent intent = new Intent(getActivity(), CreateEventActivity.class);
        //CreateEventFragment.this.startActivity(intent);
        return view;
    }

    /**
     * Background Async Task to Create new product
     */
    class CreateNewProduct extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CreateEventFragment.this.getActivity());
            pDialog.setMessage("Creating Event..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         */
        protected String doInBackground(String... args) {
            FragmentManager fragmentManager;
            Fragment fragment;
            String name = inputName.getText().toString();
            //String price = inputPrice.getText().toString();
            String description = inputDesc.getText().toString();

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("name", name));
            // params.add(new BasicNameValuePair("price", price));
            params.add(new BasicNameValuePair("description", description));

            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_product,
                    "POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created product







                    /*
                    Intent i = new Intent(CreateEventFragment.this.getActivity(), EventsFragment.class);
                    startActivity(i);
                    */
                    // closing this screen
                    //finish();
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
            // dismiss the dialog once done
            Fragment fragment;
            FragmentManager fragmentManager;
            pDialog.dismiss();

            fragment = new EventsFragment();
            fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();

            //View view = fragment.getView();
            //Activity activity = fragment.getActivity();

            //ListView lv = (ListView) view.findViewById(R.id.list);
            //lv.setAdapter(new ArrayAdapter<String>(fragment.getActivity(), android.R.layout.simple_list_item_1, new String[]{"anna", "anna"}));
        }

    }
}