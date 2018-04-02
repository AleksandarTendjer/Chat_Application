package aleksandar.tendjer.chatapplication;


import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by user on 3/28/2018.
 */

public class Contact  {

    public String contactName;
    public Drawable arrow;
   // static public Creator creator;

    public Contact(String name,Drawable Arr)
    {
        this.contactName=name;
        this.arrow=Arr;

    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        contactName = contactName;
    }


}
