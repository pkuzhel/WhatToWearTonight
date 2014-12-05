package annapaul.whattowearnav;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.commons.net.util.Base64;

/**
 * Created by pavlick on 14-11-24.
 */
public class EventFragment extends Fragment {
    private static final String TAG_MY_CLASS = "EventInfo";
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        EventInfo myClass = (EventInfo) args
                .getSerializable(TAG_MY_CLASS);
        View view = inflater.inflate(R.layout.event, null);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(myClass.name);

        Bitmap bitmap= BitmapFactory.decodeFile(myClass.type);

        if(bitmap == null){
            byte[] imageDataBytes = Base64.decodeBase64(myClass.image);
            bitmap= BitmapFactory.decodeByteArray(imageDataBytes,0,imageDataBytes.length);
        }

        ImageView im = (ImageView) view.findViewById(R.id.imageView);
        im.setImageBitmap(bitmap);

        TextView tv = (TextView) view.findViewById(R.id.textView3);
        tv.setText(myClass.address);

        ListView lv = (ListView) view.findViewById(R.id.listView);
        lv.setAdapter(new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, myClass.values));

        return view;
    }
}
