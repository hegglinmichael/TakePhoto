package com.example.michael.takephoto;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

//uses a permission in the android manifest to use the camera

public class MainActivity extends AppCompatActivity {

    //is used as an identifier for the intent
    static final int REQUEST_IMAGE_CAPTURE = 1;
    //sets up a Button
    Button takePic = null;
    //sets up the imageView
    ImageView photo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //links the reference to the xml buttons and view
        takePic = (Button) findViewById(R.id.takePhoto);
        photo = (ImageView)findViewById(R.id.photo);

        //disables the button to take a picture if there is no camera
        if(!hasCamera()) {
            takePic.setEnabled(false);
        }
    }

    //checks to see if the user has a camer
    public boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    //launches the camera
    public void photoButtonClick(View view){
        //creates an intent to launch the camera
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //take a picture and pass results along to onActivityResults
        //want to send information back(photo)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    //if you want to return the image taken
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        //this says you took an image and that there where no errors
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            //get th photo
            Bundle extras = data.getExtras();
            //gets the data to the bitmap
            Bitmap image = (Bitmap) extras.get("data");
            //sets the imageView
            photo.setImageBitmap(image);
        }
    }
}
