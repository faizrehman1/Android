package com.android.fragments_intro;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Kamran ALi on 3/5/2016.
 */
public class Fragment_list extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        TextView title = (TextView) view.findViewById(R.id.title);

        Drawable icon = getResources().getDrawable(R.drawable.sym_def_app_icon);
        imageView.setImageDrawable(icon);
        title.setText("Learning Java");
        return view;

    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
