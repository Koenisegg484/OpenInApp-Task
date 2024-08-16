package com.myassessment.openinapptask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyRVAdapter(
    private val datalist: List<String>,
    private val eventlist: List<String>,
    private val imagePathList: List<String>? = null
) : RecyclerView.Adapter<MyRVAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.yourinfocards, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.data.text = datalist[position]
        holder.event.text = eventlist[position]
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val data: TextView = itemView.findViewById(R.id.carddata)
        val event: TextView = itemView.findViewById(R.id.cardevent)
    }
}
