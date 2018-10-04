package com.example.khale.k0002_commerce

import android.content.Context
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_sub_category5.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL


class SubCategory5 : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    var xProductNum: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_category5)
        mAuth = FirebaseAuth.getInstance()

        var bundle: Bundle = intent.extras
        xProductNum = bundle.getString("xNum")

        var url = "http://10.0.2.2/CommerceServer/cProductList.php?op=cat5&xNum=$xProductNum"

        MyAsyncTask(this).execute(url)
    }

    inner class MyAsyncTask(context: Context) : AsyncTask<String, String, String>() {
        var context = context

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
            } catch (ex: Exception) {
            }
            return " "
        }

        override fun onProgressUpdate(vararg values: String?) {
            try {
                var json = JSONObject(values[0])

                if (json.getString("msg") == "has products") {
                    val userInfo = JSONArray(json.getString("info"))
                    val userRecord = userInfo.getJSONObject(0)

                    ProductParCode.setText(userRecord.getString("ProductParCode"))
                    prBillNum.setText(userRecord.getString("prBillNum"))
                    prPerchaceDate.setText(userRecord.getString("prPerchaceDate"))
                    prProductName.setText(userRecord.getString("prProductName"))
                    prDescription.setText(userRecord.getString("prDescription"))
                    prQuantity.setText(userRecord.getString("prQuantity"))
                    prPiecesPackage.setText(userRecord.getString("prPiecesPackage"))
                    prPiecePrice.setText(userRecord.getString("prPiecePrice"))
                    prProductDate.setText(userRecord.getString("prProductDate"))
                    prExpireDate.setText(userRecord.getString("prExpireDate"))
                    prLocation.setText(userRecord.getString("prLocation"))
                    prReorderQuantity.setText(userRecord.getString("prReorderQuantity"))
                    prProductClass.setText(userRecord.getString("prProductClass"))
                    prProductDepartment.setText(userRecord.getString("prProductDepartment"))
                    PranchNum.setText(userRecord.getString("PranchNum"))
                    prPackageType.setText(userRecord.getString("prPackageType"))
                    prPayCase.setText(userRecord.getString("prPayCase"))
                    prDiscount.setText(userRecord.getString("prDiscount"))

                    var xProductImage = userRecord.getString("prProductImage")
                    Picasso.with(context).load(xProductImage).into(ProductImage)

                } else {
                    Toast.makeText(applicationContext, "no data record", Toast.LENGTH_LONG).show()
                }

            } catch (ex: Exception) {
            }
        }

        override fun onPostExecute(result: String?) {
            //after task done
        }
    }
}
