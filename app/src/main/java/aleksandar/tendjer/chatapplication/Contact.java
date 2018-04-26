package aleksandar.tendjer.chatapplication;


import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by user on 3/28/2018.
 */

public class Contact  {

   // public String contactName;
    private Drawable arrow;
    private String userName;
    private String firstName;
    private String lastName;
    private Integer contactId;


    // static public Creator creator;

    public Contact(String userName,String firstName,String lastName,Integer contactId)
    {
        //this.contactName=name;
        //this.arrow=null;
        this.userName=userName;
        this.firstName=firstName;
        this.lastName=lastName;
        this.contactId=contactId;

    }
    public Contact(Drawable arrow,String userName,String firstName,String lastName,Integer contactId)
    {
        this.arrow=arrow;
        this.userName=userName;
        this.firstName=firstName;
        this.lastName=lastName;
        this.contactId=contactId;
    }

    public Drawable getArrow() {
        return arrow;
    }

//    public String getContactName() {
//        return contactName;
//    }


    public void setArrow(Drawable arrow) {
        this.arrow = arrow;
    }
    public void setContactName(String contactName) {
        contactName = contactName;
    }
    public Integer getContactId() {
        return contactId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstname() {
        return firstName;
    }

    public String getUsername() {
        return userName;
    }

    public String Concat()
    {
        String concatString=this.firstName+" "+this.lastName;
        return concatString;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }
}
