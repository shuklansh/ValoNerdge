package com.shuklansh.valonerdge

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SiteSelectAdapter(val context: Context?, private val listOfSection : MutableList<String>, val mapname :String , val side :String) : RecyclerView.Adapter <SiteSelectAdapter.SiteSelectViewHolder>()  {

    class SiteSelectViewHolder(itemView : View) : RecyclerView.ViewHolder ( itemView ){

        val siteSectionText : TextView = itemView.findViewById(R.id.siteSectionTextFB)
        val parentLayout : RelativeLayout = itemView.findViewById(R.id.SiteSectionParentLayout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiteSelectViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_single_row,parent,false)
        return SiteSelectViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: SiteSelectViewHolder, position: Int) {

        val currentItem = listOfSection[position]
        holder.siteSectionText.text = currentItem

        holder.parentLayout.setOnClickListener{
            val intent = Intent(context , VideoGuideActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("siteSection" , currentItem)
            intent.putExtra("mapname" , mapname)
            intent.putExtra("side" , side)
            context?.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {

        return listOfSection.size

    }

}