package com.example.rv193.sqlproject3.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.rv193.sqlproject3.R;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private Button btAdd, btUpdate, btRemove, btView;
    private OnDbOpListener onDbOpListener;
    private OnSomethingClick somethingClick;

    public interface OnDbOpListener{
        void dbOpPerformed(int method);
    }

    public interface OnSomethingClick{
        void somethingClick(int method);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        btAdd = v.findViewById(R.id.add_contact);
        btRemove = v.findViewById(R.id.remove_contact);
        btUpdate = v.findViewById(R.id.update_contact);
        btView = v.findViewById(R.id.view_contact);
        btAdd.setOnClickListener(this);
        btRemove.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        btView.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.add_contact:
                onDbOpListener.dbOpPerformed(0);
                somethingClick.somethingClick(0);
            break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity)context;
        try{
            onDbOpListener = (OnDbOpListener)activity;
            somethingClick = (OnSomethingClick)activity;
        }catch (ClassCastException e){ throw new ClassCastException(activity.toString() + "must " +
                "implement interface method");}
    }
}
