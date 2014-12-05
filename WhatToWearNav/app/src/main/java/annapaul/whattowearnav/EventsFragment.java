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
import android.widget.AdapterView;
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

public class EventsFragment extends Fragment {

    // Progress Dialog
    private ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    ArrayList<HashMap<String, String>> eventsList = new ArrayList<HashMap<String, String>>();

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
    private static final String TAG_IMAGE = "image";
    private static final String TAG_TYPE = "type";


    private static final String TAG_MY_CLASS = "EventInfo";
    private static String NAME = "fail";
    private static String DESC = "fail";
    private static String ADDRESS= "fail";
    private static String IMAGE = "fail";
    private static String TYPE= "fail";


    public static ArrayList<EventInfo> infoList = new ArrayList<EventInfo>();
    public static ArrayList<String> descList = new ArrayList<String>();
    public static ArrayList<String> nameList = new ArrayList<String>();

    // products JSONArray
    JSONArray events = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.view_allevents, null);

        Bundle args = getArguments();

        if (args != null) {
            //Bundle args = getArguments();
            final ArrayList<String> nameArray = args.getStringArrayList("NameArray");
            final ArrayList<String> descArray = args.getStringArrayList("DescArray");
            final ArrayList<EventInfo> fullList = (ArrayList<EventInfo>) args.getSerializable("infoList");

            for (int i = 0; i < nameArray.size(); i++) {
                // creating new HashMap
                HashMap<String, String> map = new HashMap<String, String>();

                // adding each child node to HashMap key => value
                map.put(TAG_NAME, nameArray.get(i));
                map.put(TAG_DESC, descArray.get(i));


                eventsList.add(map);
            }

            ListView lv = (ListView) view.findViewById(R.id.listView);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Bundle args = new Bundle();
                    args.putSerializable(TAG_MY_CLASS, fullList.get(position));
                    Fragment toFragment = new EventFragment();
                    toFragment.setArguments(args);
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, toFragment, "EventFragment")
                            .addToBackStack("EventFragment").commit();
                }
            });

            ListAdapter adapter = new SimpleAdapter(
                    EventsFragment.this.getActivity(), eventsList,
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


                    // looping through All Products
                    for (int i = 0; i < events.length(); i++) {
                        JSONObject c = events.getJSONObject(i);

                        // Storing each json item in variable
                        String id = c.getString(TAG_PID);
                        String name = c.getString(TAG_NAME);
                        String description = c.getString(TAG_DESC);
                        String address = c.getString(TAG_ADDRESS);
                        String image = c.getString(TAG_IMAGE);
                        String type = c.getString(TAG_TYPE);
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_PID, id);
                        map.put(TAG_NAME, name);

                        nameList.add(name);
                        descList.add(description);
                        //addressList.add(address);
                        // adding HashList to ArrayList
                        infoList.add(new EventInfo(name, description, address, image, type));
                        eventsList.add(map);
                    }

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
            Bundle bundle = new Bundle();
            //EventInfo ep = new EventInfo("name", "desc", "address", "img", "type");
            bundle.putStringArrayList("NameArray", nameList);
            bundle.putStringArrayList("DescArray", descList);
            bundle.putSerializable("infoList", infoList);
            //bundle.putStringArrayList("DescArray", descList);
            //bundle.putString("test", "yey");


            //b.putSerializable("class",ep);
            Fragment fragment = new EventsFragment();
            fragment.setArguments(bundle);

            FragmentManager fragmentManager = getFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment, "EventsFragment")
                    .commit();

        }

    }
}