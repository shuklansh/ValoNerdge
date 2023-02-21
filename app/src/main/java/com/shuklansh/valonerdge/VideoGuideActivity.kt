package com.shuklansh.valonerdge

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import com.google.firebase.database.*

class VideoGuideActivity : AppCompatActivity() {

    lateinit var mapname : String
    lateinit var side : String
    lateinit var siteSection : String

    lateinit var mediaController: MediaController
    lateinit var VideoName : TextView
    lateinit var LineupVideo : VideoView

    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    val reference : DatabaseReference = database.reference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_guide)

        supportActionBar?.hide()

        VideoName = findViewById(R.id.VideoLinkTextView)
        LineupVideo = findViewById(R.id.lineupVideoView)



        if (intent != null){
            mapname=intent.getStringExtra("mapname").toString()
            side = intent.getStringExtra("side").toString()
            siteSection = intent.getStringExtra("siteSection").toString()
        }

        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val linkofVid = snapshot.child("sova").child(mapname).child(side).child(siteSection).child("vidlink").value as String
                val vidname = snapshot.child("sova").child(mapname).child(side).child(siteSection).child("vidname").value as String

                VideoName.text = vidname

                mediaController = MediaController(this@VideoGuideActivity)
                mediaController.setAnchorView(LineupVideo)
                val vid = Uri.parse(linkofVid)
                LineupVideo.setMediaController(mediaController)
                LineupVideo.setVideoURI(vid)
                LineupVideo.start()
                //LineupVideo.setVideoURI(Uri.parse(linkofVid))
                //LineupVideo.start()


            }

            override fun onCancelled(error: DatabaseError) {

            }

        })




    }
}