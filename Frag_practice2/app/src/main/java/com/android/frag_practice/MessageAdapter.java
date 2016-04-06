package com.android.frag_practice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Kamran ALi on 3/17/2016.
 */
public class MessageAdapter extends BaseAdapter implements ListAdapter {
    private List<Message> mMessages;
    private Context context;

    public MessageAdapter(List<Message> messages, Context context) {
        mMessages = messages;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mMessages.size();
    }

    @Override
    public Object getItem(int position) {
        return mMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mMessages.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.message_item_list, null);

        TextView nameView = (TextView) view.findViewById(R.id.name_View);
        TextView msgView = (TextView) view.findViewById(R.id.msg_view);
        ImageView iconView = (ImageView) view.findViewById(R.id.icon_view);

        final Message message = mMessages.get(position);

        String name = message.getName();
        String msg = message.getMsg();
        boolean read = message.isRead();

        nameView.setText(name);
        msgView.setText(msg);

        int iconId = message.isRead() ? R.drawable.btn_circle_selected : R.drawable.btn_circle_disable_focused;

        iconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (message.isRead()) {
                    message.setRead(false);
                } else {
                    message.setRead(true);
                }
                notifyDataSetChanged();
            }
        });

        iconView.setImageResource(iconId);
        return view;
    }
}
