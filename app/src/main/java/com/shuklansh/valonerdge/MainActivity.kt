package com.shuklansh.valonerdge

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

//    lateinit var agentSelect : TextView
//    lateinit var agentimg : ImageView
//
//    lateinit var agentimglink : String
//
//    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
//    val reference : DatabaseReference = database.reference
//
    lateinit var textplusimg : RelativeLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textplusimg = findViewById(R.id.textplusimg)

        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        textplusimg.alpha = 0f // transparent
        textplusimg.animate().setDuration(1000).alpha(1f)
            .withEndAction { // in 1.2 seconds animate transparent to opaque and then open main act. 2 sec after this

                Handler(Looper.getMainLooper()).postDelayed( // this is for opening main activity 2 seconds after the logoplustext has become 1f alpha
                    {
                        val intent = Intent(this, AgentSelectActivity::class.java)

                        startActivity(intent)
                        //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                        finish()
                    }, 2000
                )

            }


//        SovaButton = findViewById(R.id.sovaButton)
//
//        SovaButton.setOnClickListener{
//            Toast.makeText(this, "Sova click" , Toast.LENGTH_SHORT).show()
//        }

//        agentSelect = findViewById(R.id.agentselect)
//        agentimg = findViewById(R.id.agentimg)
//
//        reference.addValueEventListener(object : ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val agentname : String = snapshot.child("sova").key as String
//
//                agentimglink = snapshot.child("sova").child("agentimglink").value as String
//
//                agentSelect.text = agentname
//                Picasso.get().load(agentimglink).into(agentimg)
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//        })
////        reference.addValueEventListener(object : ValueEventListener {
////            override fun onDataChange(snapshot: DataSnapshot) {
////                for (eachMap in snapshot.child("sova").children){
////                    val nameOfMap = eachMap.key
////                    if(nameOfMap != null){
////                        println("name of map : ${nameOfMap}" )
////                        println("***********************************" )
////                    }
////                }
////            }
////
////            override fun onCancelled(error: DatabaseError) {
////
////            }
////        })
//
//        agentSelect.setOnClickListener{
//            intent = Intent(this@MainActivity , MapSelectActivity::class.java)
//            startActivity(intent)
//        }



    }
}