package com.shuklansh.valonerdge

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SiteSelectAdapter(private val listOfSection : MutableList<String>) : RecyclerView.Adapter <SiteSelectAdapter.SiteSelectViewHolder>()  {

    class SiteSelectViewHolder(itemView : View) : RecyclerView.ViewHolder ( itemView ){

        val siteSectionText : TextView = itemView.findViewById(R.id.siteSectionTextFB)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiteSelectViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_single_row,parent,false)
        return SiteSelectViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: SiteSelectViewHolder, position: Int) {

        val currentItem = listOfSection[position]
        holder.siteSectionText.text = currentItem

    }

    override fun getItemCount(): Int {

        return listOfSection.size

    }

}