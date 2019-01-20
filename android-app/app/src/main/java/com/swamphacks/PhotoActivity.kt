package com.swamphacks

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.design.button.MaterialButton
import android.support.v4.content.FileProvider
import android.util.Log
import android.widget.ImageView
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import android.view.View
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

class PhotoActivity : AppCompatActivity() {

  private val REQUEST_IMAGE_CAPTURE = 1
  lateinit var mCurrentPhotoPath: String
  lateinit var photoIv: ImageView
  lateinit var lottieIv: LottieAnimationView
  lateinit var analyzeBtn: MaterialButton
  private var imageBitmap: Bitmap? = null
  private var imageName: String = ""

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_photo)
    photoIv = findViewById(R.id.photo_iv)
    lottieIv = findViewById(R.id.loading_wave)
    analyzeBtn = findViewById(R.id.analyze_btn)
    dispatchTakePictureIntent()
  }

  override fun onActivityResult(
    requestCode: Int,
    resultCode: Int,
    data: Intent?
  ) {
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
      setPic()
    }
  }

  private fun dispatchTakePictureIntent() {
    Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
      // Ensure that there's a camera activity to handle the intent
      takePictureIntent.resolveActivity(packageManager)
          ?.also {
            // Create the File where the photo should go
            val photoFile: File? = try {
              createImageFile()
            } catch (ex: IOException) {
              // Error occurred while creating the File
              println("Error occurred taking photo")
              null
            }
            // Continue only if the File was successfully created
            photoFile?.also {
              val photoURI: Uri = FileProvider.getUriForFile(
                  this,
                  "com.swamphacks.fileprovider",
                  it
              )
              takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
              startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
          }
    }
  }

  @Throws(IOException::class)
  private fun createImageFile(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    val image = File.createTempFile(
        "JPEG_$timeStamp", /* prefix */
        ".jpg", /* suffix */
        storageDir      /* directory */
    )
    imageName = image.name
    // Save a file: path for use with ACTION_VIEW intents
    mCurrentPhotoPath = image.absolutePath
    Log.d("ImagePath", mCurrentPhotoPath)
    return image
  }

  private fun setPic() {
    // Get the dimensions of the View
    val targetW: Int = photoIv.width
    val targetH: Int = photoIv.height

    val bmOptions = BitmapFactory.Options()
        .apply {
          // Get the dimensions of the bitmap
          inJustDecodeBounds = true
          BitmapFactory.decodeFile(mCurrentPhotoPath, this)
          val photoW: Int = outWidth
          val photoH: Int = outHeight

          // Determine how much to scale down the image
          val scaleFactor: Int = Math.min(photoW / targetW, photoH / targetH)

          // Decode the image file into a Bitmap sized to fill the View
          inJustDecodeBounds = false
          inSampleSize = scaleFactor
          inPurgeable = true
        }
    BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions)
        ?.also { bitmap ->
          val matrix = Matrix()

          matrix.postRotate(90f)
          val rotatedBitmap = Bitmap.createBitmap(
              bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true
          )
          imageBitmap = rotatedBitmap
          recognize(rotatedBitmap)
          photoIv.setImageBitmap(rotatedBitmap)
          uploadToStorage()
        }
  }

  private fun recognize(bitmap: Bitmap) {
    Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT)
        .show()
    val imageToBeAnalyzed = FirebaseVisionImage.fromBitmap(bitmap)
    val detector = FirebaseVision.getInstance()
        .onDeviceTextRecognizer

    detector.processImage(imageToBeAnalyzed)
        .addOnSuccessListener { firebaseVisionText ->
          analyzeBtn.visibility = View.VISIBLE
          analyzeBtn.setOnClickListener {
            val intent = Intent(this, DataActivity::class.java)
            intent.putExtra("result", firebaseVisionText.text)
            startActivity(intent)
          }
        }
        .addOnFailureListener {
          Log.d("failed", "trash")
          finish()
        }

  }

  private fun uploadToStorage() {
    if (imageBitmap == null) return
    val storage = FirebaseStorage.getInstance()

    // Create a storage reference from our app
    val storageRef = storage.reference

    // Create a reference to "mountains.jpg"
    val imageRef = storageRef.child(imageName)

    photoIv.isDrawingCacheEnabled = true
    photoIv.buildDrawingCache()
    val bitmap = (photoIv.drawable as BitmapDrawable).bitmap
    val baos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    val data = baos.toByteArray()
    val uploadTask = imageRef.putBytes(data)
    uploadTask.addOnFailureListener {
      // Handle unsuccessful uploads
      println("Failure")
    }
        .addOnSuccessListener {
          // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
          // ...
          lottieIv.pauseAnimation()
          lottieIv.visibility = View.GONE
          println("Success")
        }
  }

}
