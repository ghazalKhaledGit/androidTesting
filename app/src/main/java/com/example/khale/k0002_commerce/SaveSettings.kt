package com.example.khale.k0002_commerce


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

class SaveSettings{
    var context: Context?=null
    var sharedRef: SharedPreferences?=null
    constructor(context: Context){
        this.context=context
        sharedRef=context.getSharedPreferences("myRef", Context.MODE_PRIVATE)
    }

    fun saveSettings(userNum:String){
        val editor=sharedRef!!.edit()
        editor.putString("userNum",userNum)
        editor.commit()
        loadSettings()
    }

    fun loadSettings(){
        userNum= sharedRef!!.getString("userNum","0")

        if (userNum=="0"){
            val intent= Intent(context,LogIn::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context!!.startActivity(intent)
        }
    }


    companion object {
        var userNum=""
    }
}