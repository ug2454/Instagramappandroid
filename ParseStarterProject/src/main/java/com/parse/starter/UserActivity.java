package com.parse.starter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    ListView listView;
    private void getPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.share_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        if (item.getItemId() == R.id.share) {

            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);

            }
            else{
                getPhoto();
            }
        }
        else if (item.getItemId()==R.id.logout){
            ParseUser.logOut();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            finish();
            startActivity(intent);

        }
         return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        setTitle("User Feed");
        listView = findViewById(R.id.listView);
        ArrayList<String>  users = new ArrayList<>();



        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, users);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(getApplicationContext(),UserActivityFeed.class);
            intent.putExtra("username",users.get(i));
            startActivity(intent);
        });

        ParseQuery<ParseUser> query = ParseUser.getQuery();
//        query.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
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



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        assert data != null;
        Uri uri = data.getData();

        if (requestCode == 1 && resultCode == RESULT_OK) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                Log.i("Image selected","SUCCESS");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);

                byte[] byteArray=stream.toByteArray();
                ParseFile parseFile = new ParseFile("image.png",byteArray);
                ParseObject parseObject=new ParseObject("Image");
                parseObject.put("image",parseFile);
                parseObject.put("username",ParseUser.getCurrentUser().getUsername());
                parseObject.saveInBackground(e -> {
                    if(e==null){
                        Toast.makeText(this, "Image is saved!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}