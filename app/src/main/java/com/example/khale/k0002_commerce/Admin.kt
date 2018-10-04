package com.example.khale.k0002_commerce

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_admin.*

class Admin : AppCompatActivity() {
    var nameList=ArrayList<String>()
    var passList=ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        addNames()
    }
    fun addNames() {
        nameList!!.add("alla")
        nameList!!.add("khaled")
        nameList!!.add("q")

        passList!!.add("123")
        passList!!.add("234")
        passList!!.add("q")


    }

    fun chechName(view: View){
        var bName = txtName.text.toString()
        var bPass = txtPass.text.toString()
        if (bName in nameList) {
            if (bPass in passList) {
                if (passList.indexOf(bPass) == nameList.indexOf(bName)) {
                    Toast.makeText(this, "You are allowed", Toast.LENGTH_SHORT).show()
                    goToAddGoods()
                } else {
                    Toast.makeText(this, "Not Your Password $bPass ", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Your Password $bPass is Wrong ", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Your Name $bName is Wrong ", Toast.LENGTH_SHORT).show()
        }
    }

    fun goToAddGoods(){
        var intent= Intent(this, productListA::class.java)
        // intent.putExtra("p1Name",txtName.text.toString())
        // intent.putExtra("p1Pass",txtPass.text.toString())
        startActivity(intent)
    }

}

