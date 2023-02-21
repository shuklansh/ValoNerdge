package com.shuklansh.valonerdge

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import java.lang.ref.Reference


class MapSelectActivity : AppCompatActivity() {

    lateinit var mapOne : TextView
    lateinit var mapTwo : TextView
    lateinit var mapThree : TextView

    lateinit var mapONEimg : ImageView
    lateinit var mapTWOimg : ImageView
    lateinit var mapTHREEimg : ImageView

    var mapName: String? = ""

    var maponestring : String? = ""
    var maptwostring : String? = ""
    var mapthreestring : String? = ""

    var mapOneImgLink : String? = ""
    var mapTwoImgLink : String? = ""
    var mapThreeImgLink : String? = ""


    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    val reference : DatabaseReference = database.reference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_select)

        supportActionBar?.hide()


//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )

        mapOne = findViewById(R.id.mapselect1)
        mapTwo = findViewById(R.id.mapselect2)
        mapThree = findViewById(R.id.mapselect3)

        mapONEimg = findViewById(R.id.map1img)
        mapTWOimg = findViewById(R.id.map2img)
        mapTHREEimg = findViewById(R.id.map3img)

        reference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                maponestring = snapshot.child("sova").child("Ascent").key as String
                maptwostring = snapshot.child("sova").child("Haven").key as String
                mapthreestring = snapshot.child("sova").child("Split").key as String

                mapOneImgLink = snapshot.child("sova").child("Ascent").child("imglink").value as String
                mapTwoImgLink = snapshot.child("sova").child("Haven").child("imglink").value as String
                mapThreeImgLink = snapshot.child("sova").child("Split").child("imglink").value as String

                mapOne.text = maponestring
                mapTwo.text = maptwostring
                mapThree.text = mapthreestring

                Picasso.get().load(mapOneImgLink).into(mapONEimg)
                Picasso.get().load(mapTwoImgLink).into(mapTWOimg)
                Picasso.get().load(mapThreeImgLink).into(mapTHREEimg)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        intent = Intent(this@MapSelectActivity, AttackDefenseActivity::class.java)
        mapOne.setOnClickListener{
            mapName = maponestring
            intent.putExtra("mapname" , mapName )
            startActivity(intent)
        }
        mapTwo.setOnClickListener{
            mapName = maptwostring
            intent.putExtra("mapname" , mapName )
            startActivity(intent)
        }
        mapThree.setOnClickListener{
            mapName = mapthreestring
            intent.putExtra("mapname" , mapName )
            startActivity(intent)
        }


    }
}