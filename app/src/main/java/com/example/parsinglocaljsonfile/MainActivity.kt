package com.example.parsinglocaljsonfile

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.io.IOException


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvMain = findViewById<RecyclerView>(R.id.rvMain)

        val jsonFile = getJSONData(this, "data.json")
        val jsonArray = JSONArray(jsonFile)

        val images = arrayListOf<Image>()

        rvMain.adapter = RVadapter(images)
        rvMain.layoutManager = LinearLayoutManager(this)

        for(i in 0 until jsonArray.length()){
            val title = jsonArray.getJSONObject(i).getString("title")
            val url = jsonArray.getJSONObject(i).getString("url")
            //add the title and url into recycler view
            images.add(Image(title,url))
        }
    }

    //Read from JSON file
    fun getJSONData(context: Context, fileName: String): String?{
        val jsonString: String
        try{
            jsonString = context.assets.open(fileName).bufferedReader().use{
                it.readText()
            }
        }catch (ioException: IOException){
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}