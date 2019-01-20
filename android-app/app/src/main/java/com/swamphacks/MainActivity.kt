package com.swamphacks

import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun uploadButton(view: View) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT)
            .show()
        val intent = Intent(this, PhotoActivity::class.java)
        startActivity(intent)
    }


    fun recognizeBtn(view: View) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT)
            .show()
        val bitmap = BitmapFactory.decodeResource(
            resources,
            R.drawable.receipt2
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
