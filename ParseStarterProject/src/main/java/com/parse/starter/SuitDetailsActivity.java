package com.parse.starter;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SuitDetailsActivity extends AppCompatActivity {

    private ImageView pro;
    int position=0;
    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }

/*
TODO: To popout the image for better viewing, for later
 */


//    View.OnTouchListener touchListener = new View.OnTouchListener() {
//        @Override
//        public boolean onTouch(View view, MotionEvent motionEvent) {
//            Dialog builder = new Dialog(SuitDetailsActivity.this);
//            builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
//            builder.getWindow().setBackgroundDrawable(
//                    new ColorDrawable(android.graphics.Color.TRANSPARENT));
//            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                @Override
//                public void onDismiss(DialogInterface dialogInterface) {
//                    //nothing;
//                }
//            });
//            int a = view.getId();
////            if (R.id.go_pro == a) {
////                uri = Uri.parse("android.resource://" + getPackageName() + "/drawable/pro");    //path of image
////            } else if (R.id.img_View == a) {
////                uri = Uri.parse("android.resource://" + getPackageName() + "/drawable/profile"); //path of image
////            }
//            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/drawable/profile"); //path of image
//            ImageView imageView = new ImageView(getApplicationContext());
//            profile_pic.setImageBitmap(SuitListActivity.imageList.get(position));              //set the image in dialog popup
//            //below code fullfil the requirement of xml layout file for dialoge popup
//
//            builder.addContentView(imageView, new RelativeLayout.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT));
//            builder.show();
//            return false;
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suit_details);
        TextView txtView = findViewById(R.id.priceTextView);
        ImageView profile_pic = findViewById(R.id.suitDetailImageView);

        Intent intent = getIntent();
        position= intent.getIntExtra("position", 0);
        Toast.makeText(this, "Rate is" + SuitListActivity.rateList.get(position), Toast.LENGTH_SHORT).show();
        profile_pic.setImageBitmap(SuitListActivity.imageList.get(position));
        String price = getColoredSpanned("Price: ₹", "#888484");
        String rate = getColoredSpanned("" + SuitListActivity.rateList.get(position), "#E2BC49");

        txtView.setText(Html.fromHtml(price + "" + rate));
//        profile_pic.setOnTouchListener(touchListener);

    }


}