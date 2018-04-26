package aleksandar.tendjer.chatapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 3/28/2018.
 */

public class ContactsAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Contact> mContacts;

    public ContactsAdapter(Context context) {
        mContext = context;
        mContacts = new ArrayList<Contact>();
    }

    public void addContacts(Contact contacts) {
        this.mContacts.add(contacts);
        notifyDataSetChanged();
    }
    //setting arrow for all elements of adapter list
    public void SetArrow(Drawable image)
    {
        if(mContacts==null)
            if(!mContacts.isEmpty())
               for(Contact contact : mContacts)
               {
                   //how to set arrow
                   contact.setArrow(image);

               }
    }

    @Override
    public int getCount() {
        return mContacts.size();
    }

    @Override
    public Object getItem(int position) {
        return mContacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.contact_item, null);
            ViewHolder holder = new ViewHolder();
            holder.name = (TextView) view.findViewById(R.id.Name);
            holder.arrow = (ImageView) view.findViewById(R.id.arrowId);
            holder.firstLetter = (TextView) view.findViewById(R.id.FirstLetterId);
            view.setTag(holder);
            //making a new random color for
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            holder.firstLetter.setBackgroundColor(color);
        }

        final Contact contacts = (Contact) getItem(position);

        String firstLetter = contacts.Concat().charAt(0) + "";
        final ViewHolder holder = (ViewHolder) view.getTag();
        holder.name.setText(contacts.Concat());
        holder.arrow.setImageDrawable(contacts.getArrow());
        holder.firstLetter.setText(firstLetter);

        holder.arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMessage = new Intent(parent.getContext(), MessageActivity.class);

                Bundle contactBundle=new Bundle();
                contactBundle.putString("receiverName",holder.name.getText().toString());
                contactBundle.putLong("receiverId",contacts.getContactId());
                goToMessage.putExtras(contactBundle);
                parent.getContext().startActivity(goToMessage);
            }
        });


        return view;
    }

    private class ViewHolder {
        public TextView name = null;
        public TextView firstLetter = null;
        public ImageView arrow = null;
    }

}
