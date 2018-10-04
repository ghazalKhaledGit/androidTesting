package com.example.khale.k0002_commerce

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import kotlinx.android.synthetic.main.activity_log_in.*


class LogIn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
    }
    fun loginEvent(view: View){


        val vEmail = URLEncoder.encode(etEmail.text.toString(), "utf-8")
        var vPass= URLEncoder.encode(etPassword.text.toString(),"utf-8")
        var url = "http://10.0.2.2/CommerceServer/cLogin.php?userEmail=$vEmail&userPassword=$vPass"
        MyAsyncTask().execute(url)
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

            if (json.getString("msg") == "pass login") {
                val userInfo = JSONArray(json.getString("info"))
                val userRecord = userInfo.getJSONObject(0)
                val userNum = userRecord.getString("userNum")
                val userName = userRecord.getString("userName")
                val userPassword = userRecord.getString("userPassword")
                val userEmail = userRecord.getString("userEmail")
                val userImage = userRecord.getString("userImage")
                val userType = userRecord.getString("userType")
                val saveSettings= SaveSettings(applicationContext)
                saveSettings.saveSettings(userNum)
                finish()

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
    fun regesterEvent(view:View){
        var intent=Intent(this,Regester::class.java)
        startActivity(intent)
    }
}
