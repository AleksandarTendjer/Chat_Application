package aleksandar.tendjer.chatapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener   {
    private Button register;
    private EditText username,password,email,firstName,lastName;
    DatePicker calendar;
    DbHelper database;

    Intent intetntRegisterContacts;
    TextWatcher TxtWatcher=new TextWatcher() {
         @Override
         public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

         }

         @Override
         public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            CheckEditTxt();
         }

         @Override
         public void afterTextChanged(Editable editable) {

         }
     };
    void CheckEditTxt()
    {      //ukoliko se pojavljuje monkey u mejlu moze
        if((!password.getText().toString().isEmpty())&&(!password.getText().toString().isEmpty())&&((email.getText().toString().indexOf('@')!=-1)))
        {
            register.setEnabled(true);
        }
        else
        {
            register.setEnabled(false);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register=findViewById(R.id.RegistBtns);
        database = new DbHelper(this);
        username=findViewById(R.id.UsernameRegister);
        password=findViewById(R.id.PasswordRegister);
        email=findViewById(R.id.EditEmail);
        firstName=findViewById(R.id.EditFirst);
        lastName=findViewById(R.id.EditLast);

        register.setEnabled(false);
        username.addTextChangedListener(TxtWatcher);
        password.addTextChangedListener(TxtWatcher);
        email.addTextChangedListener(TxtWatcher);
        register.setOnClickListener(this);
        //postavljanje data pickera do trenutnog vremena
         calendar = findViewById(R.id.DataPicker);
        calendar.setMaxDate(System.currentTimeMillis());

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.RegistBtns:
                //if name not found in the database you can make one
                if(database.search(username.getText().toString())==null) {
                    Contact contact=new Contact(username.getText().toString(),firstName.getText().toString(),lastName.getText().toString(),0);
                    database.insertContact(contact);
                    intetntRegisterContacts = new Intent(this, MainActivity.class);
                    startActivity(intetntRegisterContacts);
                    intetntRegisterContacts.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    finish();
                }
                //else show toast and do nothing
                else {
                    Toast.makeText(getApplicationContext(),R.string.nameExist,Toast.LENGTH_LONG).show();
                }
                break;
                /*************************************/
//                 intetntRegisterContacts = new Intent(this, ContactsActivity.class);
//                startActivity(intetntRegisterContacts);
//                intetntRegisterContacts.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                finish();
//                break;
        }
    }


}
