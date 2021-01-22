package com.parse.starter;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        listView = findViewById(R.id.listView);
        ArrayList<String>  users = new ArrayList<>();



        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, users);

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.addAscendingOrder("username");
        System.out.println(users.toString());
        query.findInBackground((objects, e) -> {
            if (e == null) {
                if (objects.size() > 0) {
                    for (ParseUser user :
                            objects) {
                        users.add(user.getString("username"));
                    }
                    System.out.println(users.toString());
                    listView.setAdapter(adapter);
                }
            }
        });
//        adapter.notifyDataSetChanged();
//        ParseAnalytics.trackAppOpenedInBackground(getIntent());


    }
}