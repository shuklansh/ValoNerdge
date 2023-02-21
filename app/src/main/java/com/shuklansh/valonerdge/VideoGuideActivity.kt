package com.shuklansh.valonerdge

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.database.*

class VideoGuideActivity : AppCompatActivity() {

    lateinit var mapname : String
    lateinit var side : String
    lateinit var siteSection : String

    lateinit var VideoLink : TextView

    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    val reference : DatabaseReference = database.reference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_guide)

        VideoLink = findViewById(R.id.VideoLinkTextView)



        if (intent != null){
            mapname=intent.getStringExtra("mapname").toString()
            side = intent.getStringExtra("side").toString()
            siteSection = intent.getStringExtra("siteSection").toString()
        }

        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val linkofVid = snapshot.child("sova").child(mapname).child(side).child(siteSection).value as String
                VideoLink.text = linkofVid
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }
}