package com.example.khale.k0002_commerce

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_c_add_goods.*
import kotlinx.android.synthetic.main.activity_regester.*
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.*

class cAddGoods : AppCompatActivity() {
    var mAuth:FirebaseAuth?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c_add_goods)

        mAuth=FirebaseAuth.getInstance()
        ProductImage.setOnClickListener() {
            loadImage()
        }
    }

    fun bucAddGoodsEvent(view:View){
        SaveImageInFirebase()
    }

    val PICK_IMAGE_CODE = 123
    fun loadImage() {
        var intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_CODE && data != null && resultCode == RESULT_OK) {

            val selectedImage = data.data
            val filePathColum = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = contentResolver.query(selectedImage, filePathColum, null, null, null)
            cursor.moveToFirst()
            val coulomIndex = cursor.getColumnIndex(filePathColum[0])
            val picturePath = cursor.getString(coulomIndex)
            cursor.close()
            ProductImage.setImageBitmap(BitmapFactory.decodeFile(picturePath))
        }

    }

    fun SaveImageInFirebase() {
        var currentUser = mAuth!!.currentUser
        val email: String = currentUser!!.email.toString()
        val storage = FirebaseStorage.getInstance()
        val storgaRef = storage.getReferenceFromUrl("gs://k0002commerce.appspot.com/")
        val df = SimpleDateFormat("ddMMyyHHmmss")
        val dataobj = Date()
        val imagePath = SplitString(email) + "." + df.format(dataobj) + ".jpg"
        val ImageRef = storgaRef.child("images/" + imagePath)
        ProductImage.isDrawingCacheEnabled = true
        ProductImage.buildDrawingCache()
        val drawable = ProductImage.drawable as BitmapDrawable
        val bitmap = drawable.bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()
        val uploadTask = ImageRef.putBytes(data)
        uploadTask.addOnFailureListener {
        }.addOnSuccessListener { taskSnapshot ->
            var DownloadURL = taskSnapshot.downloadUrl!!.toString()
            val vProductParCode =URLEncoder.encode(ProductParCode.text.toString(), "utf-8")
            val vprBillNum = URLEncoder.encode(prBillNum.text.toString(), "utf-8")
            val vprPerchaceDate = URLEncoder.encode(prPerchaceDate.text.toString(), "utf-8")
            val vprProductName = URLEncoder.encode(prProductName.text.toString(), "utf-8")
            val vprDescription = URLEncoder.encode(prDescription.text.toString(), "utf-8")
            val vprQuantity = URLEncoder.encode(prQuantity.text.toString(), "utf-8")
            val vprPiecesPackage = URLEncoder.encode(prPiecesPackage.text.toString(), "utf-8")
            val vprPiecePrice = URLEncoder.encode(prPiecePrice.text.toString(), "utf-8")
            val vprProductDate = URLEncoder.encode(prProductDate.text.toString(), "utf-8")
            val vprExpireDate = URLEncoder.encode(prExpireDate.text.toString(), "utf-8")
            val vprLocation = URLEncoder.encode(prLocation.text.toString(), "utf-8")
            val vprReorderQuantity = URLEncoder.encode(prReorderQuantity.text.toString(), "utf-8")
            val vrProductClass = URLEncoder.encode(prProductClass.text.toString(), "utf-8")
            val vprProductDepartment = URLEncoder.encode(prProductDepartment.text.toString(), "utf-8")
            val vPranchNum = URLEncoder.encode(PranchNum.text.toString(), "utf-8")
            val vprPackageType = URLEncoder.encode(prPackageType.text.toString(), "utf-8")
            val vprPayCase = URLEncoder.encode(prPayCase.text.toString(), "utf-8")
            val vprDiscount = URLEncoder.encode(prDiscount.text.toString(), "utf-8")
            val vProductImage = URLEncoder.encode(DownloadURL, "utf-8")

            val url = "http://10.0.2.2/CommerceServer/cAddGoods.php?ProductParCode=$vProductParCode&prProductName=$vprProductName&prProductImage=$vProductImage&prPackageType=$vprPackageType&prQuantity=$vprQuantity&prPiecesPackage=$vprPiecesPackage&prPiecePrice=$vprPiecePrice&prReorderQuantity=$vprReorderQuantity&prProductClass=$vrProductClass&prProductDepartment=$vprProductDepartment&prPerchaceDate=$vprPerchaceDate&prProductDate=$vprProductDate&prExpireDate=$vprExpireDate&PranchNum=$vPranchNum&prBillNum=$vprBillNum&prPayCase=$vprPayCase&prDiscount=$vprDiscount&prLocation=$vprLocation&prDescription=$vprDescription"
            MyAsyncTask().execute(url)
        }
    }

    fun SplitString(email: String): String {
        val split = email.split("@")
        return split[0]
    }

    // CALL HTTP
    inner class MyAsyncTask : AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            //Before task started
        }

        override fun doInBackground(vararg p0: String?): String {
            try {
                val url = URL(p0[0])
                val urlConnect = url.openConnection() as HttpURLConnection
                urlConnect.connectTimeout = 7000
                val op = Operations()
                var inString = op.ConvertStreamToString(urlConnect.inputStream)
                publishProgress(inString)
            } catch (ex: Exception) {}
            return " "
        }

        override fun onProgressUpdate(vararg values: String?) {
            try {
                var json = JSONObject(values[0])
                Toast.makeText(applicationContext, json.getString("msg"), Toast.LENGTH_LONG).show()

                if (json.getString("msg") == "product is added") {
                  //  finish()
                } else {
                    buRegister.isEnabled = true
                }
            } catch (ex: Exception) {
            }
        }

        override fun onPostExecute(result: String?) {
            //after task done
        }
    }
}

