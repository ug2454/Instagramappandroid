/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;


public class StarterApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);

    // Add your initialization code here
    Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
            .applicationId("myappID")
            .clientKey("pYrbkiJdoUW7")
            .server("http://18.141.235.109/parse/")
            .build()
    );

//    ParseObject object = new ParseObject("ExampleObject");
//    object.put("myNumber", "1995");
//    object.put("myString", "uday");
//
//    object.saveInBackground(ex -> {
//      if (ex == null) {
//        Log.i("Parse Result", "Successful!");
//      } else {
//        Log.i("Parse Result", "Failed" + ex.toString());
//      }
//    });


//    ParseUser.enableAutomaticUser();

    ParseACL defaultACL = new ParseACL();
    defaultACL.setPublicReadAccess(true);
    defaultACL.setPublicWriteAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);

  }
}
