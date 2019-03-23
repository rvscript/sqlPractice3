package com.example.rv193.sqlproject3.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rv193.sqlproject3.R;
import com.example.rv193.sqlproject3.db.ContactDbHelper;

import timber.log.Timber;

public class AddContactFragment extends Fragment {
    private Button bnSave;
    private EditText etId, etName, etEmail;

    public AddContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_contact, container, false);
        bnSave = v.findViewById(R.id.bt_save);
        etId = v.findViewById(R.id.et_id);
        etName = v.findViewById(R.id.et_name);
        etEmail = v.findViewById(R.id.et_email);

        bnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String id = etId.getText().toString();
                final String name = etName.getText().toString();
                final String email = etEmail.getText().toString();

                final ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                            SQLiteDatabase database = contactDbHelper.getWritableDatabase();
                            contactDbHelper.addContact(Integer.parseInt(id), name, email, database);

                            contactDbHelper.close();

                    }
                }).start();

                etId.setText("");
                etName.setText("");
                etEmail.setText("");
                Toast.makeText(getActivity(), "Contact saved successfully", Toast.LENGTH_SHORT).show();
            }

        });
        return v;
    }
}
