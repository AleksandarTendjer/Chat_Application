package aleksandar.tendjer.chatapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ContactsActivity extends AppCompatActivity implements View.OnClickListener {
    private Button logout;
    private TextView contact;
    Intent intentContactMessage,intentContactMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
       // Button buttonLogoutObj=findViewById(R.id.ButtonLogout);
        logout=findViewById(R.id.LogoutBtn);
        logout.setOnClickListener(this);

        contact=findViewById(R.id.ContactView);
        contact.setOnClickListener(this);
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
            case R.id.ContactView:
                intentContactMessage= new Intent(this, MessageActivity.class);
                startActivity(intentContactMessage);
                finish();
                break;
        }
    }
}
