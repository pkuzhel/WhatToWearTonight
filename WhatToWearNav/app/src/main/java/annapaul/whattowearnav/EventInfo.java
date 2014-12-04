package annapaul.whattowearnav;

import java.io.Serializable;

/**
 * Created by Anna on 2014-12-03.
 */
public class EventInfo implements Serializable {
    private static final long serialVersionUID = -2163051469151804394L;
    private int id;
    public String name ="Name";
    public String desc = "Desc";
    public String address = "Address";
    private String photo = "photoURL";
    String[] values = new String[]{"Anna Fatsevych", "cool"};

    public EventInfo(String n, String description, String add){
        name = n;
        desc = desc;
        address = add;
    }
}
