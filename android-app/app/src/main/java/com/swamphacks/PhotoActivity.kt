package com.swamphacks

import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView

class PhotoActivity : AppCompatActivity() {

  val REQUEST_IMAGE_CAPTURE = 1
  lateinit var photoIv: ImageView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_photo)
    photoIv = findViewById(R.id.photo_iv)
    dispathTakePictureIntent()
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
      val imageBitmap = data!!.extras.get("data") as Bitmap
      photoIv.setImageBitmap(imageBitmap)
    }
  }

  private fun dispathTakePictureIntent() {
    Intent(MediaStore.ACTION_IMAGE_CAPTURE).also{ takePictureIntent ->
      takePictureIntent.resolveActivity(packageManager)?.also {
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
      }
    }
  }
}
