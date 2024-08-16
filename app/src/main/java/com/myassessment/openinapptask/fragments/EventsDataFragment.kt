package com.myassessment.openinapptask.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myassessment.openinapptask.MyRVAdapter
import com.myassessment.openinapptask.R

class EventsDataFragment : Fragment() {

    private lateinit var dataList: List<String>
    private lateinit var eventList: List<String>
    private lateinit var imagePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Prepare your data here (consider fetching from network or resources)
        dataList = listOf("123", "Ahmedabad", "Instagram")
        eventList = listOf("Today's Clicks", "Top Location", "Top Sources")
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_events_data, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val adapter = MyRVAdapter(dataList, eventList, null)
        recyclerView.adapter = adapter

        return view
    }
}