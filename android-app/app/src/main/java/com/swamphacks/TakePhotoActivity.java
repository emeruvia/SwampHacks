package com.swamphacks;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakePhotoActivity extends AppCompatActivity {

    private int REQUEST_TAKE_PHOTO = 1;
    private ImageView photoIv;
    private String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        photoIv = findViewById(R.id.photo_iv);
        takePictureIntent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            loadImageFromCamera();
        }
    }

    public void takePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.swamphacks.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                "JPEG_" + timeStamp,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void loadImageFromCamera() {
        try {
            int targetW = photoIv.getWidth();
            int targetH = photoIv.getHeight();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            int photoW = options.outWidth;
            int photoH = options.outHeight;
            int scaleFactor = Math.min(photoW / targetW, photoH / targetH);
            options.inJustDecodeBounds = false;
            options.inSampleSize = scaleFactor;
            options.inPurgeable = true;
            Bitmap image = BitmapFactory.decodeFile(mCurrentPhotoPath, options);
            photoIv.setImageBitmap(image);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
