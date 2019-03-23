package com.example.rv193.sqlproject3.fragment;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rv193.sqlproject3.R;
import com.example.rv193.sqlproject3.db.ContactContract;
import com.example.rv193.sqlproject3.db.ContactDbHelper;

public class ReadContacts extends Fragment {
    private TextView txtDisplay;

    public ReadContacts() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_read_contacts, container, false);
        txtDisplay = v.findViewById(R.id.read_contacts);

        return v;
    }

    private void readContacts(){
        final ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
        new Thread(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase database = contactDbHelper.getReadableDatabase();
                Cursor cursor = contactDbHelper.readContacts(database);
                String info = "";
                while(cursor.moveToNext()){

                    String id =
                            Integer.toString(cursor.getInt(cursor.getColumnIndex(ContactContract.ContactEntry.CONTACT_ID)));
                    String name =
                            cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.CONTACT_NAME));
                    String email =
                            cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.CONTACT_EMAIL));

                    info += "\n\nid: "+id+"\nName: "+ name +"\nEmail: "+email;
                }
                //String used to pass inner class data
                final String finalInfo = info;
                getView().post(new Runnable() {
                    @Override
                    public void run() {
                        txtDisplay.setText(finalInfo);
                    }
                });
            }
        }).start();
    }

}
