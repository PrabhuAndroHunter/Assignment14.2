package com.assignment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
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
}
