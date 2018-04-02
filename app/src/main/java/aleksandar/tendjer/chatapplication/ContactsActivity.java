package aleksandar.tendjer.chatapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Random;

public class ContactsActivity extends AppCompatActivity implements View.OnClickListener {
    private Button logout;
    private TextView contact;
    public String name1,name2,name3,name4,name5;
    Drawable image;
    Intent intentContactMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        logout=findViewById(R.id.LogoutBtn);
        logout.setOnClickListener(this);

         name1="Marko Markovic";
         name2="Janko Markovic";
         name3="Stanko Markovic";
         name4="Ivan Ivanovic";
         name5="Pera Peric";
         //get the arrow from dresources folder
        image=getResources().getDrawable(R.drawable.arrow2);
        ContactsAdapter adapter=new ContactsAdapter(this);

        Contact c1=new Contact(name1,image);
        Contact c2=new Contact(name2,image);
        Contact c3=new Contact(name3,image);
        Contact c4=new Contact(name4,image);
        Contact c5=new Contact(name5,image);

        //adding dummy contacts to list
        adapter.addContacts(c1);
        adapter.addContacts(c2);
        adapter.addContacts(c3);
        adapter.addContacts(c4);
        adapter.addContacts(c5);
        //appending the created adapter to a xml
        ListView contactList = (ListView) findViewById(R.id.contact_list);
        contactList.setAdapter(adapter);
        //ima adapter samo ga ne prikazuje..
    }

    @Override
    public void onClick(View view) {
        //svitchuj u odnosu na selektovani id i prebaci se na drugi activity
        switch(view.getId()) {
            case R.id.LogoutBtn:
                intentContactMain= new Intent(this, MainActivity.class);
                startActivity(intentContactMain);
                finish();
                break;
        }
    }
}
