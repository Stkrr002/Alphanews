package com.example.alphanews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class newslistadapter(private val listener: newitemclicked): RecyclerView.Adapter<newsviewholder>()
 {
  private val items: ArrayList<news> = ArrayList()
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newsviewholder {
   val view=LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
  val viewHolder=newsviewholder(view)
   view.setOnClickListener {
    listener.onitemclicked(items[viewHolder.adapterPosition])

   }

    return viewHolder

  }

  override fun onBindViewHolder(holder: newsviewholder, position: Int) {
   val currentitem=items[position]
   holder.titleview.text=currentitem.title
   holder.author.text=currentitem.author
   Glide.with(holder.itemView.context).load(currentitem.imageurl).into(holder.imageView)

  }

  override fun getItemCount(): Int {
    return items.size
  }

  fun updatenews(updatednews: ArrayList<news>){
   items.clear()
   items.addAll(updatednews)
   notifyDataSetChanged()
  }
 }
class newsviewholder(itemView: View) : RecyclerView.ViewHolder(itemView){
 val titleview: TextView=itemView.findViewById(R.id.title1)
 val imageView:ImageView=itemView.findViewById(R.id.image)
 val author:TextView=itemView.findViewById(R.id.author)
}

interface newitemclicked{
 fun onitemclicked(item:news)
}