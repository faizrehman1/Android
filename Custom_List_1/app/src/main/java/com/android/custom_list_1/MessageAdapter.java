package com.android.custom_list_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Kamran ALi on 2/23/2016.
 */
public class MessageAdapter extends BaseAdapter implements ListAdapter {
    private List<Message> messages;
    private Context context;

    public MessageAdapter(List<Message> messages, Context context) {
        this.messages = messages;
        this.context = context;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return messages.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.message_item_list,null);

        final Message message = messages.get(position);
        String title = message.getTitle();
        String sender = message.getSender();

        TextView titleView = (TextView) view.findViewById(R.id.message_item_title);
        TextView senderView = (TextView) view.findViewById(R.id.message_item_sender);
        ImageView iconView = (ImageView) view.findViewById(R.id.message_item_btn);

        int icon = message.isRead() ? R.drawable.btn_radio_on_holo_dark:R.drawable.btn_radio_on_disabled_holo_dark ;

        iconView.setImageResource(icon);

        iconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (message.isRead()){
                    message.setRead(false);
                }else{
                    message.setRead(true
                    );
                }
                notifyDataSetChanged();
            }
        });

        titleView.setText(title);
        senderView.setText(sender);



        return view;
    }
}
