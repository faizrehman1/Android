package com.android.customlistnew;

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
 * Created by Kamran ALi on 2/25/2016.
 */
public class MessageAdapter extends BaseAdapter implements ListAdapter {

    private List<Message> message ;
    private Context context;

    public MessageAdapter(List<Message> message, Context context) {
        this.message = message;
        this.context = context;
    }

    @Override
    public int getCount() {
        return message.size();
    }

    @Override
    public Object getItem(int position) {
        return message.get(position);
    }

    @Override
    public long getItemId(int position) {
        return message.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.message_item_list,null);

        TextView titleView = (TextView) view.findViewById(R.id.message_item_title);
        TextView senderView = (TextView) view.findViewById(R.id.message_item_sender);
        final ImageView checkView = (ImageView) view.findViewById(R.id.message_item_icon);

        final Message messages = message.get(position);

        String title= messages.getTitle();
        String sender = messages.getSender();

        int iconId = messages.isCheckBox() ? R.drawable.btn_radio_on_disabled_holo_dark : R.drawable.btn_radio_on_holo_dark ;

        checkView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (messages.isCheckBox()) {
                    messages.setCheckBox(false);
                } else {
                    messages.setCheckBox(true);
                }
                notifyDataSetChanged();
            }
        });
        checkView.setImageResource(iconId);



        titleView.setText(title);
        senderView.setText(sender);


        return view;

    }
}
