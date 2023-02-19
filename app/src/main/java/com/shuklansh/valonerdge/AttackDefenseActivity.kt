package com.shuklansh.valonerdge

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*

class AttackDefenseActivity : AppCompatActivity() {

    lateinit var attack : TextView
    lateinit var defense : TextView
    lateinit var mapnamedisplay : TextView
    var mapname :String = ""
    lateinit var attacktextfb : String
    lateinit var defensetextfb : String


    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    val reference : DatabaseReference = database.reference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attack_defense)

        supportActionBar?.hide()


//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )

        mapnamedisplay = findViewById(R.id.mapselected)
        attack = findViewById(R.id.attack)
        defense = findViewById(R.id.defense)

        if(intent!=null){
            mapname=intent.getStringExtra("mapname").toString()
        }

        mapnamedisplay.text = mapname

        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                attacktextfb = snapshot.child("sova").child(mapname).child("attack").key as String
                defensetextfb = snapshot.child("sova").child(mapname).child("defense").key as String



//                for (eachMap in snapshot.child("sova").child(mapname).child(attacktextfb).children){
//                    val nameOfSection = eachMap.key
//                    if(nameOfSection != null){
//                        println("name of site section of map : ${mapname} attack : ${nameOfSection}" )
//                        println("***********************************" )
//                    }
//                }
//
//
//
//                for (eachMap in snapshot.child("sova").child(mapname).child(defensetextfb).children){
//                    val nameOfSection = eachMap.key
//                    if(nameOfSection != null){
//                        println("name of site section of map ${mapname} defense: ${nameOfSection}" )
//                        println("***********************************" )
//                    }
//                }


                attack.text = attacktextfb
                defense.text = defensetextfb



            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        intent = Intent(this@AttackDefenseActivity , SectionSelectActivity::class.java)
        attack.setOnClickListener{
            intent.putExtra("mapname" , mapname)
            intent.putExtra("side" , attacktextfb)
            startActivity(intent)
            Toast.makeText(this@AttackDefenseActivity,"${mapname} attack side pressed" , Toast.LENGTH_SHORT).show()
        }
        defense.setOnClickListener{
            intent.putExtra("mapname" , mapname)
            intent.putExtra("side" , defensetextfb)
            startActivity(intent)
            Toast.makeText(this@AttackDefenseActivity,"${mapname} defense side pressed" , Toast.LENGTH_SHORT).show()
        }



    }
}