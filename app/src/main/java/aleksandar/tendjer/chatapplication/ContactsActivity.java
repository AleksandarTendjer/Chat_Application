package aleksandar.tendjer.chatapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    Drawable image;
    Intent intentContactMain;
    DbHelper database;
    private Long loggedUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        ListView contactList = (ListView) findViewById(R.id.contact_list);
        logout=findViewById(R.id.LogoutBtn);
        logout.setOnClickListener(this);

        //get the arrow from dresources folder
        image=getResources().getDrawable(R.drawable.arrow2);



        SharedPreferences preffs=this.getSharedPreferences("currentUser",MODE_PRIVATE);
        loggedUser=preffs.getLong("userId",-1);
        if(loggedUser==-1)
            startActivity(new Intent(ContactsActivity.this, MainActivity.class));

        ContactsAdapter adapter=new ContactsAdapter(this);
        database=new DbHelper(this);
        // read contacts and add to   a adapter
        Contact[] contacts=database.ReadContacts();
        if(contacts!=null)
            for (Contact contact:contacts)
                adapter.addContacts(contact);

        adapter.SetArrow(image);
        contactList.setAdapter(adapter);


    }

    @Override
    public void onClick(View view) {
        //svitchuj u odnosu na selektovani id i prebaci se na drugi activity
        switch(view.getId()) {
            case R.id.LogoutBtn:
                SharedPreferences pref = getApplicationContext().getSharedPreferences("currentUser", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putLong("userId", -1);
                editor.commit();
                intentContactMain= new Intent(this, MainActivity.class);
                startActivity(intentContactMain);
                finish();
                break;
        }
    }
}
