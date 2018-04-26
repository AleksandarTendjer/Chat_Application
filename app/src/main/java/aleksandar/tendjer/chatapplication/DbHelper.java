package aleksandar.tendjer.chatapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
import android.util.Log;


public class DbHelper extends SQLiteOpenHelper {
    private  static final String DATABASE_NAME="ChatApplication.db";
    private static final int DATABASE_VERSION=1;

    private static  final  String CONTACT_TABLE_NAME="Contact";
    private static final String COLUMN_CONTACT_ID="contact_id";
    private static final String COLUMN_USER_NAME="username";
    private static final String COLUMN_FIRST_NAME = "firstname";
    private static final String COLUMN_LAST_NAME = "lastname";

    private static  final  String MESSAGE_TABLE_NAME="Message";
    private static  final  String COLUMN_MESSAGE_ID="message_id";
    private static  final  String COLUMN_SENDER_ID="sender_id";
    private static  final  String COLUMN_RECEIVER_ID="reciever_id";
    private static  final  String COLUMN_MESSAGE="message";



     DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        //create a contact table
    database.execSQL(" CREATE TABLE "+DbHelper.CONTACT_TABLE_NAME+
            " ("+COLUMN_USER_NAME+" TEXT, "
            +COLUMN_FIRST_NAME+" TEXT, "
            +COLUMN_LAST_NAME+" TEXT, "+
            COLUMN_CONTACT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT); ");

        //creating a message table
        database.execSQL(" CREATE TABLE "+DbHelper.MESSAGE_TABLE_NAME+" ("
                +COLUMN_MESSAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SENDER_ID + " INTEGER, " +
                COLUMN_RECEIVER_ID + " INTEGER, " +
                COLUMN_MESSAGE + " TEXT, " +
                "FOREIGN KEY (" + COLUMN_SENDER_ID + ") REFERENCES " + CONTACT_TABLE_NAME + "(" + COLUMN_CONTACT_ID + ")," +
                "FOREIGN KEY (" + COLUMN_RECEIVER_ID + ") REFERENCES " + CONTACT_TABLE_NAME + "(" + COLUMN_CONTACT_ID + "));" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
//inserts a given Contact object into the database(table) with the insert SQL statement when someone new registers
    public void insertContact(Contact contact){
//        INSERT INTO CUSTOMERS (ID,NAME,AGE,ADDRESS,SALARY)
//        VALUES (1, 'Ramesh', 32, 'Ahmedabad', 2000.00 );
        SQLiteDatabase database=getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(COLUMN_USER_NAME,contact.getUsername());
        values.put(COLUMN_FIRST_NAME,contact.getFirstname());
        values.put(COLUMN_LAST_NAME,contact.getLastName());

        database.insert(CONTACT_TABLE_NAME, null, values);

     long vlue = database.insert(CONTACT_TABLE_NAME, null, values);
        Log.d("MISA", "insertContact: " + vlue);
        close();
    }

    //function that return a contact from the table(database) with that the given username
    public Contact search(String username)
    {
        Cursor cursor;
        SQLiteDatabase database=getReadableDatabase();

        cursor=database.query(CONTACT_TABLE_NAME,null,COLUMN_USER_NAME+"=?",new  String[]{username},null,null,null);
        if(!(cursor.getCount()>0)||!(cursor.moveToFirst()))
            return null;

        Contact contact=createContact(cursor);
        close();
        return contact;
    }
    //create an object from database elements
    private Contact createContact(Cursor cursor){
        String userName=cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME));
        String firstName=cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME));
        String lastName=cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME));
        Integer contactId=Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT_ID)));

        return new Contact(userName,firstName,lastName,contactId);
    }

    //creates a contact array from each database (each row, one array element),iterates through table with Cursor object
    public  Contact[] ReadContacts()
    {
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.query(CONTACT_TABLE_NAME, null, null, null, null, null, null, null);

        if(cursor.getCount()<=0)
            return  null;

        Contact[] contacts=new Contact[cursor.getCount()];
        int i=0;
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            contacts[i++]=createContact(cursor);
        }
        close();
        return  contacts;
    }
    /************************************FUNCTIONS FOR MESSAGE_TABLE********************/
    //function that inserts message object in the database
    public void insertMessage(Message message) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_SENDER_ID, message.getIdSender());
        values.put(DbHelper.COLUMN_RECEIVER_ID, message.getIdReceiver());
        values.put(DbHelper.COLUMN_MESSAGE, message.getText());
        db.insert(DbHelper.MESSAGE_TABLE_NAME, null, values);
        db.close();
    }
    //function for deleting a message that will be implemented with the long click
    public void deleteMessage(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DbHelper.MESSAGE_TABLE_NAME, DbHelper.COLUMN_MESSAGE_ID + "=?",
                new String[] {Integer.toString(id)});
        db.close();
    }
    //read all messages from table
    public Message[] ReadMessages(){
        SQLiteDatabase database=getReadableDatabase();
        Cursor cursor=database.query(MESSAGE_TABLE_NAME, null, null, null, null, null, null, null);
        if(cursor.getCount()<=0)
            return  null;
        Message[] messages=new Message[cursor.getCount()];
        int i=0;
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            messages[i++]=createMessage(cursor);
        }
        close();
        return  messages;
    }
    //create a new message object from the database current position
    private Message createMessage(Cursor cursor) {
        int messageId = cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMN_MESSAGE_ID));
        int senderId = cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMN_SENDER_ID));
        int receiverId = cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMN_RECEIVER_ID));
        String message = cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_MESSAGE));

        return new Message(message,messageId,senderId,receiverId);

    }
    /**************this could be the other way of implementing the function **********/
//    public Message[] readMessages(String sender, String receiver){
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.query(MESSAGE_TABLE_NAME, null,"("+DbHelper.COLUMN_SENDER_ID+"=? AND "+DbHelper.COLUMN_RECEIVER_ID+"=? ) OR "
//                        +" ( "+DbHelper.COLUMN_RECEIVER_ID+"=? AND "+DbHelper.COLUMN_SENDER_ID+"=?"+" )", new String[] {sender, receiver, receiver, sender}, null, null, null, null);
//
//        if(cursor.getCount() <= 0|| !(cursor.moveToFirst())){
//            return null;
//        }
//
//        Message[] messages = new Message[cursor.getCount()];
//        int i = 0;
//
//        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
//            messages[i++] = createMessage(cursor);
//        }
//        close();
//        return messages;
//    }
    //read all mesages that the person logged in recieves
public Message[] readMessages(long userId) {
    SQLiteDatabase db = getReadableDatabase();
    String[] userIds = new String[] {String.valueOf(userId), String.valueOf(userId)};
    Cursor cursor = db.query(DbHelper.MESSAGE_TABLE_NAME, null, DbHelper.COLUMN_SENDER_ID + "=? OR " + DbHelper.COLUMN_SENDER_ID + "=?",
            userIds, null, null, null, null);

    if (!(cursor.moveToFirst()) || cursor.getCount() <= 0){
        return null;
    }

    Message[] messages = new Message[cursor.getCount()];
    int i = 0;
    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
        messages[i++] = createMessage(cursor);
    }

    close();
    return messages;
}
}


