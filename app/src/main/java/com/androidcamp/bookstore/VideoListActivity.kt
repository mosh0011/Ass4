package com.androidcamp.bookstore

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.io.Serializable

class VideoListActivity {


    class VideoListActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_video_list)

            val db = Firebase.firestore
            var listView2 = findViewById<ListView>(R.id.listView)
            val items = mutableListOf<VidelItem>()
            val myListAdapter2 = BooksAdapter(this)


            db.collection("Videos").get()
                .addOnSuccessListener { result ->
                    //var a = result.toObjects<VidelItem>()

                    result.forEachIndexed { index, doc ->
                        var video = doc.toObject<VidelItem>()
                        items.add(video)

                        if (index == 4)
                            listView2.adapter =  BooksAdapter(this,())
                    }
                }

        }

        data class VidelItem(var Url:String? = "",var Title:String? = "",var Description:String? = "") :
            Serializable
    }
}