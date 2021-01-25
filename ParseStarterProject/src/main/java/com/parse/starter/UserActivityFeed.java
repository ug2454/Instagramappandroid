package com.parse.starter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;

public class UserActivityFeed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feed);
        Intent intent=getIntent();
        LinearLayout linearLayout=findViewById(R.id.linearLayout);
        String username=  intent.getStringExtra("username");
        setTitle(username+"'s Photos");
//        ArrayList images = new ArrayList();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Image");
        query.whereEqualTo("username", username);
        query.orderByDescending("createdAt");
        Toast.makeText(this, "User:"+username, Toast.LENGTH_SHORT).show();
        query.findInBackground((objects, e) -> {
            if(e==null){
                if(objects.size()>0){
                    for(ParseObject object:objects){
                        ParseFile file = (ParseFile) object.get("image");
                      file.getDataInBackground((data, e1) -> {
                          if(e1==null&&data!=null){
                              Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);

                              ImageView imageView= new ImageView(getApplicationContext());



                              imageView.setLayoutParams(new ViewGroup.LayoutParams(
                                      ViewGroup.LayoutParams.MATCH_PARENT,
                                      ViewGroup.LayoutParams.WRAP_CONTENT
                              ));

                              imageView.setImageBitmap(bitmap);
                              linearLayout.addView(imageView);
                          }
                      });

                    }
                }
            }
        });


    }
}