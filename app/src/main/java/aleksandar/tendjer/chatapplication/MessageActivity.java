package aleksandar.tendjer.chatapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener{
    private Button sendMsg,logout;
    private EditText message;
    Intent intentMessageMain;
    String str="message sent!";
    TextWatcher Watcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        CheckEmpty();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    void CheckEmpty()
    {
        if(!message.getText().toString().isEmpty())
        {
            sendMsg.setEnabled(true);
        }
        else
        {
            sendMsg.setEnabled(false);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        sendMsg=findViewById(R.id.SendBtn);
        logout=findViewById(R.id.LogoutBtnMsg);
        message=findViewById(R.id.MessageEdit);
        sendMsg.setEnabled(false);
        message.addTextChangedListener(Watcher);
        sendMsg.setOnClickListener(this);
        logout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.LogoutBtnMsg:
                intentMessageMain=new Intent(this,MainActivity.class);
                finish();
                break;
            case R.id.SendBtn:
                Toast.makeText(this,str, Toast.LENGTH_LONG).show();


        }
    }
}
