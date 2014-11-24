package annapaul.whattowearnav;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by pavlick on 14-11-24.
 */
public class HomeFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, null);
        String[] values = new String[]{"Birthday Party   ", "Wedding", "Concert",
                "Amazing Event", "Another Awesome Event", "Not-So-Awesome Event"};

        ListView lv = (ListView) view.findViewById(R.id.listView);
        lv.setAdapter(new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, values));

        Button createE = (Button) view.findViewById(R.id.button4);

        createE.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // TODO: review and revise, change to FragmentManager and create a
                // 'CreateEvent' class with fragments
                /*
                setContentView(R.layout.create);
                Button takeP = (Button) findViewById(R.id.button);
                takeP.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {


                        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                        startActivityForResult(intent, 0);
                    }
                });
                Button saveE = (Button) findViewById(R.id.button2);
                saveE.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(".SAVE");
                        startActivity(intent);
                    }
                });
                */
            }
        });

        return view;

    }
}
