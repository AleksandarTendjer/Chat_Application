package aleksandar.tendjer.chatapplication;

import android.content.Intent;
import android.content.SharedPreferences;
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
    long userId;
    long receiverId;
    private DbHelper database;
   private Message[] messages;
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

        SharedPreferences prefs = getSharedPreferences("currentUser", MODE_PRIVATE);
         userId = prefs.getLong("userId", -1);
        if (userId == -1) {
            startActivity(new Intent(MessageActivity.this, MainActivity.class));
        }
        //getting a string from contacts activity
        Bundle messageBundle=getIntent().getExtras();
        if (messageBundle == null) {
            Toast.makeText(this, R.string.bundleproblem, Toast.LENGTH_SHORT).show();
           startActivity(new Intent(MessageActivity.this, MainActivity.class));
            return;
        }
        setTitle(messageBundle.getString("receiverName"));
        final long receiverId = messageBundle.getLong("receiverId");
        adapter=new MessageAdapter(this);
        database=new DbHelper(this);
        Message[] messages=database.readMessages(userId);

        //adding a message to an adapter
        if (messages != null) {
            for (Message message : messages) {
                adapter.AddMessage(message);
            }
        }

        //making the adapter visible(putting him in a list)
        msgList = (ListView) findViewById(R.id.messageListView);
        msgList.setAdapter(adapter);

        msgList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //remove item if there was a long click
               database.deleteMessage(i);
                adapter.removeItem(i);
                 return  true;
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Message[] messages=database.readMessages(userId);

        //adding a message to an adapter
        if (messages != null) {
            for (Message message : messages) {
                adapter.AddMessage(message);
            }
        }

    }


    @Override
    public void onClick(View view){
        switch(view.getId()) {
            case R.id.LogoutBtnMsg:
                SharedPreferences pref = getApplicationContext().getSharedPreferences("currentUser", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putLong("userId", -1);
                editor.commit();
                intentMessageMain=new Intent(this,MainActivity.class);
                intentMessageMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentMessageMain);
                finish();
                break;
                //add item when i want to send text
            case R.id.SendBtn:
                currStr=message.getText().toString();
                Message newMessage = new Message(currStr,userId,receiverId);
                database.insertMessage(newMessage);
                adapter.AddMessage(newMessage);
                msgList.setAdapter(adapter);
               Toast toast= Toast.makeText(this,str, Toast.LENGTH_LONG);
               toast.setGravity(Gravity.CENTER,0,0);
               toast.show();
               message.getText().clear();

        }
    }

}
