package com.example.khale.k0002_commerce

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_sub_category2.*
import kotlinx.android.synthetic.main.category1_ticket.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.util.ArrayList

class SubCategory2 : AppCompatActivity() {
    var CategoryList = ArrayList<SuperTicketClass>()
    var CategoryAdapter: MyCategoryAdpater? = null
    var xNum=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_category2)

        CategoryAdapter = MyCategoryAdpater(this, CategoryList)
        productView.adapter = CategoryAdapter


      //  Toast.makeText(this,"catagory 2 ", Toast.LENGTH_LONG).show()


        var bundle: Bundle = intent.extras
        xNum = bundle.getString("xNum")
        var xName = bundle.getString("xName")
        var xImage = bundle.getString("xImage")
        c1Name.setText("Welecome to $xName  $xNum")
        Picasso.with(this).load(xImage).into(c1Image)

        SearchInDatabase("$xNum",0)

    }

    override fun onResume() {
        SearchInDatabase("$xNum",0)
        super.onResume()
    }
    fun SearchInDatabase(SearchText:String, startFrom:Int){
        val url = "http://10.0.2.2/CommerceServer/cProductList.php?op=cat2&query=$SearchText&StartFrom=$startFrom"
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
            var myView = layoutInflater.inflate(R.layout.category2_ticket, null)
            myView.prProductName.setText(myProduct.tprProductName)
            Picasso.with(context).load(myProduct.tprProductImage).into(myView.ProductImage)
            val url = "http://10.0.2.2/CommerceServer/cProductList.php?op=cat2&query=myProduct.tProductNum&StartFrom=0"
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
      //   Toast.makeText(this,"from catagory 2 "+ myProduct.tProductNum , Toast.LENGTH_LONG).show()
        //TODO: SubCategory3
        val intent=Intent(this,SubCategory4::class.java)
        intent.putExtra("xNum",myProduct.tProductNum)
        intent.putExtra("xName",myProduct.tprProductName)
        intent.putExtra("xImage",myProduct.tprProductImage)
        startActivity(intent)

    }
}
