package com.example.khale.k0002_commerce

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val saveSettings= SaveSettings(this)
        saveSettings.loadSettings()
    }
    fun goToPage2(view: View){
        var intent1= Intent(this, MainCategories::class.java)
        startActivity(intent1)
    }
    fun goToAdmin(view: View){
        var intent2= Intent(this, Admin::class.java)
        startActivity(intent2)
    }

}
