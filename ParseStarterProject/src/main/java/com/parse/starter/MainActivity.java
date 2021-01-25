/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseAnalytics;
import com.parse.ParseUser;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnKeyListener {
    EditText username;
    EditText password;
    Button button;
    TextView textView;
    boolean isButtonTextLogin = false;
    ParseUser user = new ParseUser();
    String userName = "";
    String passWord = "";
    ArrayList<String> users;
    Intent intent;

    public void showUserList(){
        intent = new Intent(getApplicationContext(), UserActivity.class);
        finish();
        startActivity(intent);
    }
    public void hideKeyBoard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void clickButton(View view) {
        userName = username.getText().toString().trim();
        passWord = password.getText().toString().trim();
        if (!userName.matches("") && !passWord.matches("")) {
            if (!isButtonTextLogin) {


                System.out.println("INSIDE IF");


                user.setUsername(userName);  //qwe
                user.setPassword(passWord); //123
                user.signUpInBackground(e1 -> {
                    if (e1 == null) {
                        Log.i("Saved", "Signed up successful");
                        Toast.makeText(this, "Sign Up Successful!", Toast.LENGTH_SHORT).show();

                        showUserList();
                    } else {
                        Toast.makeText(this, e1.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            } else {
                ParseUser.logInInBackground(userName, passWord, (user, e) -> {
                    if (user != null && e == null) {
                        Log.i("user", user.getUsername());
                        Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        showUserList();
//                user.setEmail("uday@gmail.com");
//                user.saveInBackground();
                    } else {
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        } else {
            Toast.makeText(this, "Username and a Password are required", Toast.LENGTH_SHORT).show();
        }
        hideKeyBoard(view);


    }

    public void clickTextView(View view) {
        if (isButtonTextLogin) {
            button.setText("Sign Up");
            textView.setText("or, Login");
            isButtonTextLogin = false;
            username.setText("");
            password.setText("");
            username.requestFocus();
            hideKeyBoard(view);

        } else {
            button.setText("Log In");
            textView.setText("or, Sign Up");
            isButtonTextLogin = true;
            username.setText("");
            password.setText("");
            username.requestFocus();
            hideKeyBoard(view);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Instagram");
        ImageView imageView = findViewById(R.id.imageView2);
        username = findViewById(R.id.usernameEditText);
        button = findViewById(R.id.button);
        password = findViewById(R.id.editTextTextPassword);
        textView = findViewById(R.id.textView);
        imageView.setBackgroundColor(Color.rgb(255, 255, 255));
       if(ParseUser.getCurrentUser()!=null){
           showUserList();
       }


        ParseAnalytics.trackAppOpenedInBackground(getIntent());
        password.setOnKeyListener(this);
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

//        ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
//        query.whereGreaterThan("score",50);
//
//        query.findInBackground((objects, e) -> {
//            if (objects != null && e == null) {
//                if (objects.size() > 0) {
//                    for (ParseObject parseObject : objects) {
//                        parseObject.put("score",parseObject.getInt("score")+20);
//                        parseObject.saveInBackground();
//                        Log.i("username", parseObject.getString("username"));
//                        Log.i("score", Integer.toString(parseObject.getInt("score")));
//                    }
//                }
//            }
//        });


//
//
//
//
//        user.signUpInBackground(e -> {
//            if(e==null){
//                Log.i("Saved","Signed up successful");
//
//            }
//            else{
//                e.printStackTrace();
//            }
//        });

//        ParseUser.logInInBackground("Uday","Garg",(user, e) -> {
//            if(user!=null&&e==null){
//                Log.i("user",user.getEmail());
////                user.setEmail("uday@gmail.com");
////                user.saveInBackground();
//            }
//        });

//        ParseUser.logOut();
//        if (ParseUser.getCurrentUser() != null) {
//            Log.i("signed in", ParseUser.getCurrentUser().getUsername());
//        } else {
//            Log.i("Error", "Failed to sign in");
//        }

    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
            System.out.println("ONKEY");
            clickButton(view);
        }
        return false;
    }
}