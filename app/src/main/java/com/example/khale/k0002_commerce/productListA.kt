package com.example.khale.k0002_commerce

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SearchView
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_list_a.*
import kotlinx.android.synthetic.main.product_ticket_ad.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class productListA : AppCompatActivity() {
    var ProductsList= ArrayList<sTicket>()
    var adpater:MyProductAdpater?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list_a)

        icAdd.setOnClickListener(){
            intent=Intent(this,cAddGoods::class.java)
            startActivity(intent)
        }
        val saveSettings = SaveSettings(this)
        saveSettings.loadSettings()

        // set adpater
        adpater = MyProductAdpater(this, ProductsList)
        productView.adapter = adpater

        SearchInDatabase("%", 0)
    }

    override fun onRestart() {
        SearchInDatabase("%", 0)
        super.onRestart()
    }

    // dapter loading
    inner class MyProductAdpater : BaseAdapter {
        var listNotesAdpater = ArrayList<sTicket>()
        var context: Context? = null

        constructor(context: Context, listNotesAdpater: ArrayList<sTicket>) : super() {
            this.listNotesAdpater = listNotesAdpater
            this.context = context
        }


        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var myProduct = listNotesAdpater[p0]
            var myView = layoutInflater.inflate(R.layout.product_ticket_ad, null)
            myView.prProductName.setText(myProduct.tprProductName+" ("+myProduct.tProductNum+")")
            myView.prPiecePrice.setText(myProduct.tprPiecePrice.toString())
            myView.prDescription.setText(myProduct.tprDescription)
            Picasso.with(context).load(myProduct.tprProductImage).into(myView.ProductImage)
            val xurl  = "http://10.0.2.2/CommerceServer/cProductList.php?op=proA&query=%&StartFrom=0"
            val url = "http://10.0.2.2/CommerceServer/cProductList.php?op=proA&query=myProduct.tProductNum&StartFrom=0"
            MyAsyncTask().execute(url)
            myView.icModify.setOnClickListener(){
                 goToModify(myProduct)
            }

            myView.icDelete.setOnClickListener(){
                goToDelete(myProduct)
                SearchInDatabase("%", 0)


            }
           // adpater!!.notifyDataSetChanged()

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

    fun SearchInDatabase(SearchText:String, startFrom:Int){
          val url = "http://10.0.2.2/CommerceServer/cProductList.php?op=proA&query=$SearchText&StartFrom=$startFrom"
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
            } catch (ex: Exception) {
            }
            return " "
        }

        override fun onProgressUpdate(vararg values: String?) {

            try {
                var json = JSONObject(values[0])
                if (json.getString("msg") == "has products") {
                    ProductsList.clear()
                    val productInfo = JSONArray(json.getString("info"))
                    for (i in 0..productInfo.length() - 1) {
                        val myProductInfo = productInfo.getJSONObject(i)
                        ProductsList.add(sTicket(
                                myProductInfo.getString("ProductNum"),
                                myProductInfo.getString("prProductImage"),
                                myProductInfo.getString("prProductName"),
                                myProductInfo.getString("prPiecePrice"),
                                myProductInfo.getString("prDescription")
                                 ))

                         adpater!!.notifyDataSetChanged()
                    }
                }
            } catch (ex: Exception) {}
        }

        override fun onPostExecute(result: String?) {
            //after task done
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        val sv:SearchView = menu.findItem(R.id.app_bar_search).actionView as SearchView

        val sm= getSystemService(Context.SEARCH_SERVICE) as SearchManager
        sv.setSearchableInfo(sm.getSearchableInfo(componentName))
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(applicationContext, query, Toast.LENGTH_LONG).show()
                SearchInDatabase(query,0)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item != null) {
            when(item.itemId){
                R.id.homePage->{
                    // TODO: go to home
                    // CALL http
                    SearchInDatabase("%",0)

                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun goToModify(myProduct:sTicket){
        var intent = Intent(this,cUpdateGoods::class.java)
        intent.putExtra("xProductNum",myProduct.tProductNum)
        startActivity(intent)
    }
    fun goToDelete(myProduct:sTicket){
        val url = "http://10.0.2.2/CommerceServer/cDeleteProduct.php?itemToDelete=${myProduct.tProductNum}"
         MyDeleteAsyncTask().execute(url)
    }
    inner class MyDeleteAsyncTask : AsyncTask<String, String, String>() {

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
                    ProductsList.clear()
                  //  val productInfo = JSONArray(json.getString("info"))
                        //TODo:
                   SearchInDatabase("%", 0)

                }
            } catch (ex: Exception) {}
        }

        override fun onPostExecute(result: String?) {
            //after task done
        }
    }
}

