package com.example.alphanews

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), newitemclicked {

     private lateinit var madapter: newslistadapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager=LinearLayoutManager(this)
        fetchdata()
         madapter = newslistadapter(this)
        recyclerView.adapter=madapter



    }
    private fun fetchdata()
    {
        val url="https://saurav.tech/NewsAPI/everything/cnn.json"
        val jsonObjectRequest=JsonObjectRequest(
            Request.Method.GET,url,null,
            Response.Listener {
                              val newsJsonArray=it.getJSONArray("articles")
                val newsArray=ArrayList<news>()
                for (i in 0 until newsJsonArray.length()){
                    val newsJSONObject=newsJsonArray.getJSONObject(i)
                    val News=news(
                        newsJSONObject.getString("title"),
                        newsJSONObject.getString("author"),
                        newsJSONObject.getString("url"),
                        newsJSONObject.getString("urlToImage")

                    )
                    newsArray.add(News)
                }
                madapter.updatenews(newsArray)

            },Response.ErrorListener {
                Log.d(String(),"something bad")

            }
        )

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)



    }



    override fun onitemclicked(item: news) {

         val builder= CustomTabsIntent.Builder();
         val customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(item.url));
    }
}