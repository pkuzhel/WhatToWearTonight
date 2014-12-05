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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventsTesterFragment extends Fragment {

    // Progress Dialog
    private ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    ArrayList<HashMap<String, String>> eventsList;

    // url to get all products list
    private static String url_get_events = "http://scrapperia.com/android/get_all_products.php";
    private static ArrayList<HashMap<String,String>> eventsArray = new ArrayList<HashMap<String, String>>();

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_EVENTS = "events";
    private static final String TAG_PID = "pid";
    private static final String TAG_NAME = "name";
    private static final String TAG_DESC = "description";
    private static final String TAG_ADDRESS = "address";

    // products JSONArray
    JSONArray events = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.view_allevents, null);

        if (savedInstanceState != null) {
            Bundle args = getArguments();
            ArrayList<String> nameArray = args.getStringArrayList("NameArray");
            ArrayList<String> descArray = args.getStringArrayList("DescArray");


            for (int i = 0; i < nameArray.size(); i++) {
                // creating new HashMap
                HashMap<String, String> map = new HashMap<String, String>();

                // adding each child node to HashMap key => value
                map.put(TAG_NAME, nameArray.get(i));
                map.put(TAG_DESC, nameArray.get(i));

                //nameList.add(TAG_NAME);
                //descList.add(TAG_DESC);

                // adding HashList to ArrayList
                eventsList.add(map);
            }

            ListView lv = (ListView) view.findViewById(R.id.listView);

            //ListAdapter adapter = new ArrayAdapter<String>(this.getActivity(), newArray);
            //lv.setAdapter(new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, newArray));


            ListAdapter adapter = new SimpleAdapter(
                    EventsTesterFragment.this.getActivity(), eventsList,
                    R.layout.list_item, new String[]{TAG_NAME,
                    TAG_DESC},
                    new int[]{R.id.pid, R.id.name});

            // updating listview
            lv.setAdapter(adapter);
        } else {
            new GetEvents().execute();
        }
        return view;
    }

    class GetEvents extends AsyncTask<String, String, String> {

        private ProgressDialog pDialog;
        JSONParser jsonParser = new JSONParser();
        private String url_login = "http://scrapperia.com/android/get_all_products.php";
        private static final String TAG_SUCCESS = "success";
        public static final String PREFS_NAME = "UserPrefs";

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Getting all events... Hold on!");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * Creating product
         */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_get_events,
                    "POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    pDialog.dismiss();

                    // products found
                    // Getting Array of Products
                    events = json.getJSONArray(TAG_EVENTS);
                    eventsList = new ArrayList<HashMap<String, String>>();
                    ArrayList<String> nameList = new ArrayList<String>();
                    ArrayList<String> descList = new ArrayList<String>();

                    // looping through All Products
                    for (int i = 0; i < events.length(); i++) {
                        JSONObject c = events.getJSONObject(i);

                        // Storing each json item in variable
                        String id = c.getString(TAG_PID);
                        String name = c.getString(TAG_NAME);
                        String description = c.getString(TAG_DESC);
                        String address = c.getString(TAG_ADDRESS);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_PID, id);
                        map.put(TAG_NAME, name);

                        nameList.add(name);
                        descList.add(description);

                        // adding HashList to ArrayList
                        eventsList.add(map);
                    }

                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("NameArray", nameList);
                    bundle.putStringArrayList("DescArray", descList);
                    bundle.putString("test", "yey");

                    Fragment fragment = new EventsTesterFragment();
                    fragment.setArguments(bundle);

                    FragmentManager fragmentManager = getFragmentManager();

                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragment, "EventsFragment")
                            .addToBackStack("EventsFragment").commit();
                    /*
                    Fragment newFragment = new EventsListFragment();
                    //FragmentManager fragmentManager
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, newFragment);
                            */
                    /*
                    //
                    ListView lv = (ListView) getView().findViewById(R.id.listView);

                    ListAdapter adapter = new SimpleAdapter(
                            getActivity(), eventsList,
                            R.id.listView, new String[] { TAG_PID,
                            TAG_NAME},
                            new int[] { R.id.pid, R.id.name });
                    // updating listview
                    lv.setAdapter(adapter);
*/
                    eventsArray = eventsList;
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


        }

    }
}