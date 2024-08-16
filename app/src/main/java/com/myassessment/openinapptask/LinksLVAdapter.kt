package com.myassessment.openinapptask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.myassessment.openinapptask.models.TopLink
import java.text.SimpleDateFormat
import java.util.Locale

class LinksLVAdapter(
    private val topLinks: MutableList<TopLink>,
) : RecyclerView.Adapter<LinksLVAdapter.MyTopLinksHolder>(){

    class MyTopLinksHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val linkname : TextView = itemView.findViewById(R.id.cardlinkname)
        val linkdate : TextView = itemView.findViewById(R.id.cardlinkdate)
        val linkclicks : TextView = itemView.findViewById(R.id.cardlinkclicks)
        val linkmain : TextView = itemView.findViewById(R.id.cardlinkmain)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTopLinksHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.yourlinksinforcard, parent, false)
        return MyTopLinksHolder(view)
    }

    override fun getItemCount(): Int {
        return topLinks.size
    }

    override fun onBindViewHolder(holder: MyTopLinksHolder, position: Int) {
        holder.linkname.text = topLinks[position].title
        holder.linkdate.text = extractDateFromISO8601(topLinks[position].created_at)
        holder.linkclicks.text = topLinks[position].total_clicks.toString()
        holder.linkmain.text = topLinks[position].web_link

        println(topLinks[position].title)

    }
    private fun extractDateFromISO8601(isoDateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        val date = inputFormat.parse(isoDateString) ?: return ""
        return outputFormat.format(date)
    }
}