package annapaul.whattowearnav;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * Created by pavlick on 14-11-24.
 */
public class CreateEventFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_event, null);
        Intent intent = new Intent(getActivity(), CreateEventActivity.class);
        CreateEventFragment.this.startActivity(intent);
        return view;
    }


}
