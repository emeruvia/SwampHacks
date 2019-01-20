package com.swamphacks

import android.content.Context
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage

class MainActivity : AppCompatActivity() {

  lateinit var image: ImageView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    image = findViewById(R.id.main_iv)
    image.setImageResource(R.drawable.receipt)
  }

  fun recognizeBtn(view: View) {
    Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT)
        .show()
    val bitmap = BitmapFactory.decodeResource(
        resources,
        R.drawable.receipt
    )
    val imageToBeAnalyzed = FirebaseVisionImage.fromBitmap(bitmap)
      val detector = FirebaseVision.getInstance()
          .onDeviceTextRecognizer

      val result = detector.processImage(imageToBeAnalyzed)
        .addOnSuccessListener { firebaseVisionText ->
          println(firebaseVisionText.text)
        }
        .addOnFailureListener {
          Log.d("failed", "trash")
        }
  }
}
