package aleksandar.tendjer.chatapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private  Button login,register;
    private EditText password,username;
    Intent intentMainReg,intentMainContacts;


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

    }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case (R.id.RegBtnMain):
                     intentMainReg = new Intent(this, RegisterActivity.class);
                    startActivity(intentMainReg);
                    break;
                case (R.id.LoginBtnMain):
                    intentMainContacts = new Intent(this, ContactsActivity.class);
                        startActivity(intentMainContacts);
                        finish();

                    break;
            }
        }




}
