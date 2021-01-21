/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ParseObject parseObject = new ParseObject("Score");
//        parseObject.put("username","Uday");
//        parseObject.put("score",200);
//        parseObject.saveInBackground(e -> {
//            if(e==null){
//              System.out.println("SUCCESS");
//            }
//            else{
//                e.printStackTrace();
//            }
//        });

//        ParseObject parseObject = new ParseObject("Tweet");
//        parseObject.put("username","Uday");
//        parseObject.put("tweet","my second tweet");
//        parseObject.saveInBackground(e -> {
//            if(e==null){
//              System.out.println("SUCCESS");
//            }
//            else{
//                e.printStackTrace();
//            }
//        });
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
//        query.whereGreaterThan("score",50);
//
//       query.getInBackground("id", (object, e) -> {
//           if(object!=null&&e==null){
//               object.getString("username");
//
//           }
//       });

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
        query.whereGreaterThan("score",50);

        query.findInBackground((objects, e) -> {
            if (objects != null && e == null) {
                if (objects.size() > 0) {
                    for (ParseObject parseObject : objects) {
                        parseObject.put("score",parseObject.getInt("score")+20);
                        parseObject.saveInBackground();
                        Log.i("username", parseObject.getString("username"));
                        Log.i("score", Integer.toString(parseObject.getInt("score")));
                    }
                }
            }
        });
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }

}