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
    public String image = "Image";
    public String type = "Type";
    String[] values = new String[]{"Anna Fatsevych", "cool"};

    public EventInfo(String n, String description, String add, String pic, String extension){
        name = n;
        desc = desc;
        address = add;
        image = pic;
        type = extension;
    }
}
