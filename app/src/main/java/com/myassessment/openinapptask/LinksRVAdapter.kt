package com.myassessment.openinapptask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.myassessment.openinapptask.models.RecentLink
import java.text.SimpleDateFormat
import java.util.Locale

class LinksRVAdapter(
    private val recentLinks: MutableList<RecentLink>,
) : RecyclerView.Adapter<LinksRVAdapter.MyLinksHolder>(){

    class MyLinksHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val linkname : TextView = itemView.findViewById(R.id.cardlinkname)
        val linkdate : TextView = itemView.findViewById(R.id.cardlinkdate)
        val linkclicks : TextView = itemView.findViewById(R.id.cardlinkclicks)
        val linkmain : TextView = itemView.findViewById(R.id.cardlinkmain)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyLinksHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.yourlinksinforcard, parent, false)
        return MyLinksHolder(view)
    }

    override fun getItemCount(): Int {
        return recentLinks.size
    }

    override fun onBindViewHolder(holder: MyLinksHolder, position: Int) {
        holder.linkname.text = recentLinks[position].title
        holder.linkdate.text = extractDateFromISO8601(recentLinks[position].created_at)
        holder.linkclicks.text = recentLinks[position].total_clicks.toString()
        holder.linkmain.text = recentLinks[position].web_link

        println(recentLinks[position].title)

    }
    private fun extractDateFromISO8601(isoDateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        val date = inputFormat.parse(isoDateString) ?: return ""
        return outputFormat.format(date)
    }
}