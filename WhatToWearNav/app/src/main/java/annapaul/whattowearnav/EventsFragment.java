package annapaul.whattowearnav;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class EventsFragment extends Fragment {

    // Progress Dialog
    private ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    ArrayList<HashMap<String, String>> productsList;

    // url to get all products list
    private static String url_all_products = "http://scrapperia.com/android/get_all_products.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "products";
    private static final String TAG_PID = "pid";
    private static final String TAG_NAME = "name";

    // products JSONArray
    JSONArray products = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.view_allevents);
        View view = inflater.inflate(R.layout.event, null);

        // Hashmap for ListView
        productsList = new ArrayList<HashMap<String, String>>();

        // Loading products in Background Thread
        //new LoadAllProducts().execute();

        // Get listview

        /*
        ListView lv = getListView();


        ListAdapter adapter = new SimpleAdapter(
                EventsFragment.this.getActivity(), productsList,
                R.layout.list_item, new String[] { TAG_PID,
                TAG_NAME},
                new int[] { R.id.pid, R.id.name });

                */
        // updating listview
        //setListAdapter(adapter);


        // on seleting single product
        // launching Edit Product Screen

        //lv.setOnItemClickListener(new OnItemClickListener() {
/*
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
*/
                /*
                String pid = ((TextView) view.findViewById(R.id.pid)).getText()
                        .toString();

                // Starting new intent
                Intent in = new Intent(getApplicationContext(),
                        HomeActivity.class);
                // sending pid to next activity
                in.putExtra(TAG_PID, pid);

                // starting new activity and expecting some response back
                startActivityForResult(in, 100);
                */
        /*
            }
        });
        */
        return view;
    }

    /*
    // Response from Edit Product Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if result code 100
        if (resultCode == 100) {
            // if result code 100 is received
            // means user edited/deleted product
            // reload this screen again
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

    }
    */

    /**
     * Background Async Task to Load all product by making HTTP Request
    class LoadAllProducts extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(EventsFragment.this.getActivity());
            pDialog.setMessage("Loading Events. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
*/
        /*
        protected String doInBackground(String... args) {
            FragmentManager fragmentManager;
            Fragment fragment;
            ListView list = null;
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);

            // Check your log cat for JSON response
            Log.d("All Events: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    products = json.getJSONArray(TAG_PRODUCTS);

                    // looping through All Products
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);

                        // Storing each json item in variable
                        String id = c.getString(TAG_PID);
                        String name = c.getString(TAG_NAME);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_PID, id);
                        map.put(TAG_NAME, name);

                        // adding HashList to ArrayList
                        productsList.add(map);


                        ListAdapter adapter = new SimpleAdapter(
                                EventsFragment.this.getActivity(), productsList,
                                R.layout.list_item, new String[] { TAG_PID,
                                TAG_NAME},
                                new int[] { R.id.pid, R.id.name });

                        list.setAdapter(adapter);


                        //mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);


                    }
                } else {
                    // no products found
                    // Launch Add New product Activity
                    // TODO: change this to the CreateEventFragment
                    fragment = new CreateEventFragment();
                    fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
        */

        /**
         * After completing background task Dismiss the progress dialog

        protected void onPostExecute(String file_url) {
            FragmentManager fragmentManager;
            Fragment fragment;
            ListView list = null;

            // dismiss the dialog after getting all products
            pDialog.dismiss();

            // updating UI from Background Thread

            ListAdapter adapter = new SimpleAdapter(
                    EventsFragment.this.getActivity(), productsList,
                    R.layout.list_item, new String[] { TAG_PID,
                    TAG_NAME},
                    new int[] { R.id.pid, R.id.name });

            list.setAdapter(adapter);


            //mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);
*/
/*
            fragment = new EventsFragment();
            fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
*/

            /*
            runOnUiThread(new Runnable() {
                public void run() {

                    // Updating parsed JSON data into ListView

                    ListAdapter adapter = new SimpleAdapter(
                            ViewAllEventsActivity.this.getActivity(), productsList,
                            R.layout.list_item, new String[] { TAG_PID,
                            TAG_NAME},
                            new int[] { R.id.pid, R.id.name });
                    // updating listview
                    setListAdapter(adapter);
                }
            });
            */
        }
