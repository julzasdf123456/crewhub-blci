package com.lopez.julz.crmcrewhub;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;

import java.io.File;

public class ImageViewerActivity extends AppCompatActivity {

    public ImageView imageView;

    public String imgPath, scId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);

        imgPath = getIntent().getExtras().getString("IMG_PATH");
        scId = getIntent().getExtras().getString("SC_ID");

        imageView = findViewById(R.id.imageView);

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "/lineman/" + scId + "/" + imgPath);
        if (file.exists()) {
            // Decode the file into a Bitmap
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

            // Set the Bitmap to the ImageView
            imageView.setImageBitmap(bitmap);
        }
    }
}