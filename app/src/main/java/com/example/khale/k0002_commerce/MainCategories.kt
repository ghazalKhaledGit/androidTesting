package com.example.khale.k0002_commerce

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main_categories.*
import kotlinx.android.synthetic.main.super_ticket.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.util.ArrayList

class MainCategories : AppCompatActivity() {
    var CategoryList = ArrayList<SuperTicketClass>()
    var CategoryAdapter: MyCategoryAdpater? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_categories)

        CategoryAdapter = MyCategoryAdpater(this, CategoryList)
        productView.adapter = CategoryAdapter

        SearchInDatabase("0",0)

    }

    override fun onResume() {
        SearchInDatabase("0",0)
        super.onResume()
    }
    fun SearchInDatabase(SearchText:String, startFrom:Int){
        val url = "http://10.0.2.2/CommerceServer/cProductList.php?op=cat0&query=$SearchText&StartFrom=$startFrom"
        MyCategoryAsyncTask().execute(url)
    }



    inner class MyCategoryAdpater : BaseAdapter {
        var listNotesAdpater = ArrayList<SuperTicketClass>()
        var context: Context? = null

        constructor(context: Context, listNotesAdpater: ArrayList<SuperTicketClass>) : super() {
            this.listNotesAdpater = listNotesAdpater
            this.context = context
        }


        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val myProduct = listNotesAdpater[p0]
            var myView = layoutInflater.inflate(R.layout.super_ticket, null)
            myView.prProductName.setText(myProduct.tprProductName)
            Picasso.with(context).load(myProduct.tprProductImage).into(myView.ProductImage)
            val url = "http://10.0.2.2/CommerceServer/cProductList.php?op=cat0&query=myProduct.tProductNum&StartFrom=0"
            MyCategoryAsyncTask().execute(url)


            myView.ProductImage.setOnClickListener(){
                goToSubCategory(myProduct)
            }
            myView.prProductName.setOnClickListener() {
                goToSubCategory(myProduct)
            }

            return myView
        }

        override fun getItem(p0: Int): Any {
            return listNotesAdpater[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {

            return listNotesAdpater.size
        }
    }

    inner class MyCategoryAsyncTask : AsyncTask<String, String, String>() {

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
                    CategoryList.clear()
                    val productInfo = JSONArray(json.getString("info"))
                    for (i in 0..productInfo.length() - 1) {
                        val myProductInfo = productInfo.getJSONObject(i)
                        CategoryList.add(SuperTicketClass(
                                myProductInfo.getString("ProductNum"),
                                myProductInfo.getString("prProductImage"),
                                myProductInfo.getString("prProductName")
                                  ))

                        CategoryAdapter!!.notifyDataSetChanged()
                    }
                }
            } catch (ex: Exception) {
            }
        }

        override fun onPostExecute(result: String?) {
            //after task done
        }
    }

    fun goToSubCategory(myProduct:SuperTicketClass){
        val intent=Intent(this,SubCategory1::class.java)
        intent.putExtra("xNum",myProduct.tProductNum)
        intent.putExtra("xName",myProduct.tprProductName)
        intent.putExtra("xImage",myProduct.tprProductImage)
        startActivity(intent)
    }
}
