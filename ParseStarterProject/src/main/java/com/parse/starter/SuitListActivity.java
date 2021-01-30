package com.parse.starter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashMap;

public class SuitListActivity extends AppCompatActivity {

    GridView gridView;
    ImageView imageView;
    int lengthOfImage;
    static ArrayList<Bitmap> imageList = new ArrayList<>();
    static ArrayList<Integer> rateList = new ArrayList<>();
    ArrayList<HashMap<String, Object>> mapArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suit_list);
        gridView = findViewById(R.id.mainGrid);


        gridView.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4) {
//                Toast.makeText(SuitListActivity.this, "Rate is Rs. " + rateList.get(p3), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),SuitDetailsActivity.class);
                intent.putExtra("position",p3);
                startActivity(intent);
            }
        });
        System.out.println(imageList.size());
        LinearLayout linearLayout = findViewById(R.id.linearLayout);


//        ArrayList images = new ArrayList();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Image");
        query.orderByAscending("createdAt");

        query.findInBackground((objects, e) -> {
            imageList.clear();
            rateList.clear();
            if (e == null) {
                if (objects.size() > 0) {
                    for (ParseObject object : objects) {
                        ParseFile file = (ParseFile) object.get("image");

                        file.getDataInBackground((data, e1) -> {

                            if (e1 == null && data != null) {
                                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                imageList.add(bitmap);
                                rateList.add((Integer) object.get("rate"));
                                GridAdapter adapter = new GridAdapter(this);
                                gridView.setAdapter(adapter);
//                                ImageView imageView = new ImageView(getApplicationContext());
//                                TextView textView = new TextView(getApplicationContext());
//
//
//
//                                imageView.setLayoutParams(new ViewGroup.LayoutParams(
//                                        ViewGroup.LayoutParams.MATCH_PARENT, 600
//
//                                ));
//                                textView.setLayoutParams(new ViewGroup.LayoutParams(
//                                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
//                                ));
//
//                                imageView.setImageBitmap(bitmap);
//                                textView.setText(object.getString("rate"));
//
//                                linearLayout.addView(imageView);
//                                linearLayout.addView(textView);


                            }
                        });

                    }

                }
            }

        });
//        LinearLayout linearLayout=findViewById(R.id.linearLayout);
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("Image");
//
//        query.orderByAscending("createdAt");
//
//        query.findInBackground((objects, e) -> {
//            if (e == null) {
//                if (objects.size() > 0) {
//                    for (ParseObject object : objects) {
//                        ParseFile file = (ParseFile) object.get("image");
//
//                        file.getDataInBackground((data, e1) -> {
//
//                            if (e1 == null && data != null) {
//
//                                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//                                imageList.add(bitmap);
//
//
//
//                            }
//                        });
//
//                    }
//                    System.out.println(imageList.size());
//                }
//            }
//        });

//        gridView.setOnItemClickListener((adapterView, view, i, l) -> {
//
//        });
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.a);
//            mapArrayList.add(_item);
//        }
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.b);
//            mapArrayList.add(_item);
//        }
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.c);
//            mapArrayList.add(_item);
//        }
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.d);
//            mapArrayList.add(_item);
//        }
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.e);
//            mapArrayList.add(_item);
//        }
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.f);
//            mapArrayList.add(_item);
//        }
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.g);
//            mapArrayList.add(_item);
//        }
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.h);
//            mapArrayList.add(_item);
//        }
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.i);
//            mapArrayList.add(_item);
//        }
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.j);
//            mapArrayList.add(_item);
//        }
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.k);
//            mapArrayList.add(_item);
//        }
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.l);
//            mapArrayList.add(_item);
//        }
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.m);
//            mapArrayList.add(_item);
//        }
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.n);
//            mapArrayList.add(_item);
//        }
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.o);
//            mapArrayList.add(_item);
//        }
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.p);
//            mapArrayList.add(_item);
//        }
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.q);
//            mapArrayList.add(_item);
//        }
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.r);
//            mapArrayList.add(_item);
//        }
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.s);
//            mapArrayList.add(_item);
//        }
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.t);
//            mapArrayList.add(_item);
//        }
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.u);
//            mapArrayList.add(_item);
//        }
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.v);
//            mapArrayList.add(_item);
//        }
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.w);
//            mapArrayList.add(_item);
//        }
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.x);
//            mapArrayList.add(_item);
//        }
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.y);
//            mapArrayList.add(_item);
//        }
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.z);
//            mapArrayList.add(_item);
//        }
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.ab);
//            mapArrayList.add(_item);
//        }
//        {
//            HashMap<String, Object> _item = new HashMap<>();
//            _item.put("rate", "1650");
//            _item.put("image", R.drawable.ac);
//            mapArrayList.add(_item);
//        }


    }

    public class GridAdapter extends BaseAdapter {
        private final Context mContext;

        public GridAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return imageList.size();
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            // Inflate the layout for each list item
            LayoutInflater _inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (v == null) {
                v = _inflater.inflate(R.layout.list_item, null);
            }
            // Get the TextView and ImageView from CustomView for displaying item
            TextView txtview = v.findViewById(R.id.listItemTextView);
            ImageView imgview = v.findViewById(R.id.listItemImage);

            // Set the text and image for current item using data from map list
            txtview.setText("₹ " + rateList.get(position));
//            imgview.setImageResource((Integer) mapArrayList.get(position).get("image"));
            imgview.setImageBitmap(imageList.get(position));
            return v;
        }
    }

//    private class GridViewAdapter extends BaseAdapter {
//        private final Context context;
//        int length = lengthOfImage;
//
//        public GridViewAdapter(Context context) {
//            this.context = context;
//        }
//
//        @Override
//        public int getCount() {
//            return length;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            View v = convertView;
//            ParseQuery<ParseObject> query = ParseQuery.getQuery("Image");
//            query.orderByAscending("createdAt");
//
//            query.findInBackground((objects, e) -> {
//                if (e == null) {
//                    if (objects.size() > 0) {
//                        for (ParseObject object : objects) {
//                            ParseFile file = (ParseFile) object.get("image");
//
//                            file.getDataInBackground((data, e1) -> {
//
//                                if (e1 == null && data != null) {
//                                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//                                    ImageView imageView = new ImageView(getApplicationContext());
//                                    System.out.println(bitmap.toString());
//
//
////
////
////
////                                imageView.setLayoutParams(new ViewGroup.LayoutParams(
////                                        400,200
////
////                                ));
////
////                                imageView.setImageBitmap(bitmap);
//
//
//                                }
//                            });
//
//                        }
//                    }
//                }
//            });
//            return v;
//
//        }
//    }
}