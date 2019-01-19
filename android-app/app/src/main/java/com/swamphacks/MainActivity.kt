package com.swamphacks

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

  lateinit var image: ImageView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    image = findViewById(R.id.main_iv)
    image.setImageResource(R.drawable.receipt)
  }

  fun recognizeBtn(view: View) {
    Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
  }
}
