package aleksandar.tendjer.chatapplication;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 3/31/2018.
 */

public class MessageAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Messages> messageList;

    public MessageAdapter(Context context) {
        mContext = context;
        messageList = new ArrayList<Messages>();
    }

    public void AddMessages(Messages mesg) {
        this.messageList.add(mesg);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return messageList.size();
    }

    @Override
    public Object getItem(int position) {


        return messageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void removeItem(int position)
    {
        messageList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.message_item, null);
            ViewHolder holder = new ViewHolder();

            holder.message = (TextView) view.findViewById(R.id.mesgtxtview);
            view.setTag(holder);
            //if the position of the message in the listview is even align the text to the left side
            if((position%2==0)&&(position<=3))
            {
                holder.message.setGravity(Gravity.LEFT);
                holder.message.setBackgroundColor(ContextCompat.getColor(mContext,R.color.msgBgColor));
            }
            else
            {
                holder.message.setGravity(Gravity.RIGHT);
            }
        }
        Messages currMesg = (Messages) getItem(position);
        final ViewHolder holder = (ViewHolder) view.getTag();
        holder.message.setText(currMesg.text);

        return view;
    }

    private class ViewHolder {
        public TextView message = null;

    }

}
