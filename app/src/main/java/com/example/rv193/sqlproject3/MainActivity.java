package com.example.rv193.sqlproject3;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.rv193.sqlproject3.fragment.AddContactFragment;
import com.example.rv193.sqlproject3.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity implements  HomeFragment.OnDbOpListener,
        HomeFragment.OnSomethingClick{
    private FragmentManager fm = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.fragment_container)!=null){
            if(savedInstanceState!=null){
                return;
            }

            HomeFragment homeFragment = new HomeFragment();
            fm.beginTransaction()
                    .addToBackStack("HomeFragment")
                    .add(R.id.fragment_container, homeFragment)
                    .commit();
        }
    }

    @Override
    public void dbOpPerformed(int method) {
        switch (method){
            case 0:
               fm.beginTransaction()
               .replace(R.id.fragment_container, new AddContactFragment())
               .addToBackStack("addContactFragment")
               .commit();
        }
    }

    @Override
    public void somethingClick(int method) {
        switch (method){
            case 0:
                Toast.makeText(this, "Something Click: 0", Toast.LENGTH_SHORT).show();
        }
    }
}
