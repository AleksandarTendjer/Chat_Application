package aleksandar.tendjer.chatapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener{
    private Button sendMsg,logout;
    private EditText message;
    private TextView contactName;
    Intent intentMessageMain;
    String str="message sent!",currStr;
    MessageAdapter adapter;
    ListView msgList;
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
        contactName=findViewById(R.id.contactName);
        sendMsg.setEnabled(false);
        message.addTextChangedListener(Watcher);
        sendMsg.setOnClickListener(this);
        logout.setOnClickListener(this);

        Messages  m1=new Messages(getString(R.string.message1));
        Messages  m2=new Messages(getString(R.string.message2));
        Messages  m3=new Messages(getString(R.string.message3));
        Messages  m4=new Messages(getString(R.string.message4));

        adapter=new MessageAdapter(this);
        adapter.AddMessages(m1);
        adapter.AddMessages(m2);
        adapter.AddMessages(m3);
        adapter.AddMessages(m4);

        msgList = (ListView) findViewById(R.id.messageListView);
        msgList.setAdapter(adapter);
        //getting a string from contacts activity
        Bundle messageBundle=getIntent().getExtras();
        //setting the string inside the textview
        contactName.setText(messageBundle.get("Name").toString());

        msgList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //remove item if there was a long click
                adapter.removeItem(i);
                ;
                 return  true;
            }
        });
    }

    @Override
    public void onClick(View view){
        switch(view.getId()) {
            case R.id.LogoutBtnMsg:
                intentMessageMain=new Intent(this,MainActivity.class);
                startActivity(intentMessageMain);
                finish();
                break;
                //add item when i want to send text
            case R.id.SendBtn:
                currStr=message.getText().toString();
                Messages newmesg=new Messages(currStr);
                adapter.AddMessages(newmesg);
                msgList.setAdapter(adapter);
               Toast toast= Toast.makeText(this,str, Toast.LENGTH_LONG);
               toast.setGravity(Gravity.CENTER,0,0);
               toast.show();
               message.getText().clear();

        }
    }

}
