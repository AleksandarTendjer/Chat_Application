package aleksandar.tendjer.chatapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private  Button login,register;
    private EditText password,username;
    Intent intentMainReg,intentMainContacts;

    DbHelper database;


    void Check() {
        if ((password.length() >6) && (username.getText().toString().isEmpty()!=true)) {
            login.setEnabled(true);

        } else {
            login.setEnabled(false);
        }
    }

    TextWatcher TxtWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Check();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.UsernameMain);
        password = findViewById(R.id.PasswordMain);
        login = findViewById(R.id.LoginBtnMain);
        register = findViewById(R.id.RegBtnMain);
        login.setEnabled(false);
        username.addTextChangedListener(TxtWatcher);
        password.addTextChangedListener(TxtWatcher);

        register.setOnClickListener(this);
        login.setOnClickListener(this);
        database=new DbHelper(this);

    }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case (R.id.RegBtnMain):
                     intentMainReg = new Intent(this, RegisterActivity.class);
                    startActivity(intentMainReg);
                    break;

                case (R.id.LoginBtnMain):
                    Contact contact=database.search(username.getText().toString());
                    if(contact==null) {
                        Toast.makeText(getApplicationContext(), R.string.nameNotExist, Toast.LENGTH_LONG).show();
                    }
                    else {

                        intentMainContacts = new Intent(this, ContactsActivity.class);
                        //change the preferences with editor
                        SharedPreferences sharedPreff=getSharedPreferences("currentUser", MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreff.edit();
                        editor.putLong("userId", contact.getContactId());
                        editor.apply();
                        startActivity(intentMainContacts);
                        finish();
                    }
                    break;
            }
        }




}
