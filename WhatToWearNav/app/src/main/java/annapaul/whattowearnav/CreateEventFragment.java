package annapaul.whattowearnav;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by pavlick on 14-11-24.
 */
public class CreateEventFragment extends Fragment {
    static public EventInfo ei;

    private static final int SELECT_PICTURE = 1;
    private static final String TAG_MY_CLASS = "EventInfo";
    private static String NAME = "success";
    private static String DESC = "success";
    private static String ADDRESS= "success";
    // Progress Dialog
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
    TextView inputName;
    EditText address;
    EditText inputDesc;
    String desDirectory="/";
    String filename="icon.png";
    String sourceUrl="";
    Button uploagimage;

////
    Button uploadButton;


    // url to create new product
    private static String url_create_product = "http://scrapperia.com/android/create_product.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_event, null);
        //Upload
        uploadButton = (Button) view.findViewById(R.id.button5);
        // button click event
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
              /*  if(isNetworkAvailable())
                {
                    new UploadTask().execute();
                }else{
                    AlertDialog.Builder b=new AlertDialog.Builder(CreateEventFragment.this.getActivity());
                    b.setMessage("Internet connectivity failure.Try again!");
                    b.show();
                }
                */
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECT_PICTURE);

            }
        });




    // Edit Text
        inputName = (TextView) view.findViewById(R.id.name);
        // inputPrice = (EditText) findViewById(R.id.inputPrice);
        inputDesc = (EditText) view.findViewById(R.id.eventDescription);
        address = (EditText) view.findViewById(R.id.eventAddress);
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
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivity = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public final void onActivityResult(final int requestCode, final int
            resultCode, final Intent i) {
        super.onActivityResult(requestCode, resultCode, i);

        // this matches the request code in the above call
        if (requestCode == 1) {
            Uri _uri = i.getData();
            Bitmap photo = (Bitmap) i.getExtras().get("data");
            File file1 = new File(Environment.getExternalStorageDirectory()+File.separator + "image.jpg");

            // this will be null if no image was selected...
            if (_uri != null) {
                // now we get the path to the image file
                Cursor cursor = getActivity().getContentResolver().query(_uri, null,
                        null, null, null);
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME);
                String result = cursor.getString(idx);
                Log.v(result, "Print------------");
                String imageFilePath = cursor.getString(0);
                filename = file1.toString();
                Log.v(filename, "Print");
                Log.v(file1.toString(), "Print");
                cursor.close();
                //sourceUrl = filename;
                if(isNetworkAvailable())
                {

                    new UploadTask().execute();
                }else{
                    AlertDialog.Builder b=new AlertDialog.Builder(CreateEventFragment.this.getActivity());
                    b.setMessage("Internet connectivity failure.Try again!");
                    b.show();
                }


            }
        }
    }

    class UploadTask extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;
        Boolean uploadStat;
        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(CreateEventFragment.this.getActivity());
            pDialog.setMessage("Uploading...");
            pDialog.show();
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            uploadStat=new FileUpload().ftpUpload1(sourceUrl, filename, desDirectory);
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(uploadStat)
                    {
                        AlertDialog.Builder b=new AlertDialog.Builder(CreateEventFragment.this.getActivity());
                        b.setMessage("Upload Successful");
                        b.show();
                    }else{
                        AlertDialog.Builder b=new AlertDialog.Builder(CreateEventFragment.this.getActivity());
                        b.setMessage("Upload Failed.");
                        b.show();
                    }
                }
            });
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            if (null != pDialog && pDialog.isShowing()) {
                pDialog.dismiss();
            }
            super.onPostExecute(result);
        }
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
            String add = address.getText().toString();
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("name", name));
            // params.add(new BasicNameValuePair("price", price));
            params.add(new BasicNameValuePair("description", description));
            params.add(new BasicNameValuePair("address", add));
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


                NAME = name;
                DESC = description;
                ADDRESS = add;




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
            EventInfo ep = new EventInfo(NAME, DESC, ADDRESS);

            Bundle args = new Bundle();
            args.putSerializable(TAG_MY_CLASS, ep);
            Fragment toFragment = new EventFragment();
            toFragment.setArguments(args);
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, toFragment, "EventFragment")
                    .addToBackStack("EventFragment").commit();

          /*  fragment = new EventFragment();
            fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
*/
            //View view = fragment.getView();
            //Activity activity = fragment.getActivity();

            //ListView lv = (ListView) view.findViewById(R.id.list);
            //lv.setAdapter(new ArrayAdapter<String>(fragment.getActivity(), android.R.layout.simple_list_item_1, new String[]{"anna", "anna"}));
        }



    }
}