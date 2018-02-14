package com.assignment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.toString();
    ImageView mTmageview;
    Button mButton;
    String extStorageDirectory;
    File file;
    private static final int PERMISSION_REQUEST_STORAGE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // init layout
        setContentView(R.layout.activity_main);
        // init views
        mTmageview = findViewById(R.id.imageView);
        mButton = findViewById(R.id.button);

        // Set on click listener to button
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.prabhu); // get bitmap reference of image stored in drawable folder
                extStorageDirectory = Environment.getExternalStorageDirectory().toString(); // get path of external Storage
                file = new File(extStorageDirectory, "prabhu.png"); // crate object of File with file name
                try {
                    /*creating object of FileOutputStream
                    FileOutputStream used to create a file and write data into it*/
                    FileOutputStream outStream = new FileOutputStream(file);
                    bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                    outStream.flush(); // Flushes this output stream and forces any buffered output bytes to be written out
                    outStream.close(); // close outputstream
                } catch (Exception e) {
                    Log.e(TAG, "onClick: " + e.getMessage());
                }
                Toast.makeText(MainActivity.this, "saved", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "onStart: permission granted"); // if granted go to next screen
        } else {
            // Permission is missing and must be requested.
            requestStoragePermission();
        }
    }

    private void requestStoragePermission() {
        // Permission has not been granted and must be requested.
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                PERMISSION_REQUEST_STORAGE);
    }

     /*
    *
    * This method will handle runtime permission request output( User selection )
    *
    * */

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {

        if (requestCode == PERMISSION_REQUEST_STORAGE) {
            // if user granted Storage permission start HomeScreenActivity
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Thank you", Toast.LENGTH_SHORT).show();
            } else {
            }
        }
    }
}
