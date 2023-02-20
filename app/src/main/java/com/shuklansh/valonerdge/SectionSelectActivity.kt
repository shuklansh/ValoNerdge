package com.shuklansh.valonerdge

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import org.w3c.dom.Text

class SectionSelectActivity : AppCompatActivity() {

    lateinit var mapname : String
    lateinit var side : String
    lateinit var textboxstring : String
    //lateinit var textbox : TextView

    lateinit var siteSectionRecyclerView: RecyclerView


    var listOfSection = mutableListOf<String>()
    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    val reference : DatabaseReference = database.reference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_section_select)

        if(intent!=null){
            mapname=intent.getStringExtra("mapname").toString()
            side = intent.getStringExtra("side").toString()
        }

        //textbox = findViewById(R.id.textbox)

        siteSectionRecyclerView = findViewById(R.id.siteSectionRecyclerView)
        siteSectionRecyclerView.layoutManager = LinearLayoutManager(this)


        getSiteSectionData()


    }

    private fun getSiteSectionData(): MutableList<String> {

        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
//                attacktextfb = snapshot.child("sova").child(mapname).child("attack").key as String
//                defensetextfb = snapshot.child("sova").child(mapname).child("defense").key as String



                for (eachMap in snapshot.child("sova").child(mapname).child(side).children){
                    val nameOfSection = eachMap.key
                    if(nameOfSection != null){
                        //println("name of site section of map : ${mapname} , ${side} : ${nameOfSection}" )
                        //println("***********************************" )
                        listOfSection.add(nameOfSection)

                    }


                }
                System.out.println(listOfSection)

                siteSectionRecyclerView.adapter = SiteSelectAdapter(listOfSection)

                //textboxstring = "The list of all section of side ( ${side} ) of map ( ${mapname} ) ( ${printListFun(listOfSection)} )"
                //textbox.text = textboxstring



//                for (eachMap in snapshot.child("sova").child(mapname).child(side).children){
//                    val nameOfSection = eachMap.key
//                    if(nameOfSection != null){
//                        println("name of site section of map ${mapname} defense: ${nameOfSection}" )
//                        println("***********************************" )
//                    }
//                }


//                attack.text = attacktextfb
//                defense.text = defensetextfb



            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        return listOfSection
    }


    private fun printListFun(listOfSection: MutableList<String>): String {
//        var stringout = ""
//        for(i in listOfSection){
//            stringout += stringout.plus("$i ")
//        }
//        System.out.println(stringout)
//        return "allgood"
        var stringOfSides : String = ""
        for (eachsection in listOfSection){

            stringOfSides = stringOfSides.plus(" ${eachsection}")

        }
        //System.out.println(stringOfSides)
        return  stringOfSides
    }
}