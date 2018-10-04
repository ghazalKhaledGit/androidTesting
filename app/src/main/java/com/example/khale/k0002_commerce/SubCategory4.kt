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
import kotlinx.android.synthetic.main.activity_sub_category4.*
import kotlinx.android.synthetic.main.category4_ticket.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.util.ArrayList

class SubCategory4 : AppCompatActivity() {
    var CategoryList = ArrayList<cat4Tecket>()
    var CategoryAdapter: MyCategoryAdpater? = null
    var xNum=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_category4)

        CategoryAdapter = MyCategoryAdpater(this, CategoryList)
        productView.adapter = CategoryAdapter


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
        val url = "http://10.0.2.2/CommerceServer/cProductList.php?op=cat4&query=$SearchText&StartFrom=$startFrom"
        MyCategoryAsyncTask().execute(url)
    }



    inner class MyCategoryAdpater : BaseAdapter {
        var listNotesAdpater = ArrayList<cat4Tecket>()
        var context: Context? = null

        constructor(context: Context, listNotesAdpater: ArrayList<cat4Tecket>) : super() {
            this.listNotesAdpater = listNotesAdpater
            this.context = context
        }


        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val myProduct = listNotesAdpater[p0]
            var myView = layoutInflater.inflate(R.layout.category4_ticket, null)
            myView.prProductName.setText(myProduct.tprProductName)
            myView.prPiecePrice.setText(myProduct.tprPiecePrice)
            myView.prDescription.setText(myProduct.tprDescription)
            Picasso.with(context).load(myProduct.tprProductImage).into(myView.ProductImage)
            val url = "http://10.0.2.2/CommerceServer/cProductList.php?op=cat4&query=myProduct.tProductNum&StartFrom=0"
            MyCategoryAsyncTask().execute(url)

            myView.icInfo.setOnClickListener(){
                goToSubInfo(myProduct)
            }

            myView.icBasket.setOnClickListener() {
                goToBasket(myProduct)
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
                        CategoryList.add(cat4Tecket(
                                myProductInfo.getString("ProductNum"),
                                myProductInfo.getString("prProductImage"),
                                myProductInfo.getString("prProductName"),
                                myProductInfo.getString("prPiecePrice"),
                                myProductInfo.getString("prDescription")
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

    fun goToSubInfo(myProduct: cat4Tecket){
        xNum=myProduct.tProductNum.toString()
        val intent=Intent(this,SubCategory5::class.java)
        intent.putExtra("xNum",xNum)
        startActivity(intent)
    }

    fun goToBasket(myProduct: cat4Tecket){
        xNum=myProduct.tProductNum.toString()
        Toast.makeText(this,"you pressed basket it is under progress"+ xNum , Toast.LENGTH_LONG).show()
        /*
        val intent=Intent(this,SubCategory6::class.java)
        intent.putExtra("xNum",xNum)
        startActivity(intent)
         */
    }

}
