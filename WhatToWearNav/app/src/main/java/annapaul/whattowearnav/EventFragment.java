package annapaul.whattowearnav;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by pavlick on 14-11-24.
 */
public class EventFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.event, null);

        String[] values = new String[]{"Anna Fatsevych", "Andrey Guzenko", "David Novodchuk",
                "Peter Liu", "Peter McIntyre", "Parvin Soleymani"};

        ListView lv = (ListView) view.findViewById(R.id.listView);
        lv.setAdapter(new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, values));

        return view;
    }
}
