package com.myassessment.openinapptask

import LineChartFragment
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myassessment.openinapptask.apiservice.APIConfig
import com.myassessment.openinapptask.apiservice.ApiInterface
import com.myassessment.openinapptask.dashboardrepository.DashboardRepository
import com.myassessment.openinapptask.fragments.EventsDataFragment
import com.myassessment.openinapptask.models.RecentLink
import com.myassessment.openinapptask.models.TopLink
import com.myassessment.openinapptask.viewmodel.MainViewModel
import com.myassessment.openinapptask.viewmodel.MainViewModelFactory


class MainActivity : AppCompatActivity() {

    //TextViews
    lateinit var toplinksbutton:TextView
    lateinit var recentlinksbutton:TextView

    lateinit var linearLayoutManager: LinearLayoutManager

    //Lists for api
    lateinit var recentLinksList:MutableList<RecentLink>
    lateinit var topLinksList:MutableList<TopLink>

    // RecyclerViews
    private lateinit var toplinksrv: RecyclerView

    //Adapters
    lateinit var toplinksAdapter: LinksLVAdapter
    lateinit var recentlinksAdapter: LinksRVAdapter

    private lateinit var mainViewModel: MainViewModel

    @SuppressLint("MissingInflatedId", "ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Handle system windows for the root view
        val rootView = findViewById<View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { view, insets ->
            val systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(
                systemBarsInsets.left,
                systemBarsInsets.top,
                systemBarsInsets.right,
                systemBarsInsets.bottom
            )
            insets
        }

        // Adding the LineChartFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, LineChartFragment())
            .commit()

        //Adding the Events fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, EventsDataFragment())
            .commit()

        // Initialize RecyclerViews
        toplinksrv = findViewById(R.id.toplinksRecyclerView)

        toplinksbutton = findViewById(R.id.toplinks)
        recentlinksbutton = findViewById(R.id.recentlinks)

        // Initialize empty lists
        recentLinksList = mutableListOf()
        topLinksList = mutableListOf()

        toplinksAdapter = LinksLVAdapter(topLinksList)
        recentlinksAdapter = LinksRVAdapter(recentLinksList)

        //Adapter for top Links Recycler View
        linearLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        toplinksrv.layoutManager = linearLayoutManager
        toplinksrv.adapter = toplinksAdapter


        // Set up ViewModel and Repository
        val apiService = APIConfig.getInstance().create(ApiInterface::class.java)
        val repository = DashboardRepository(apiService)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        // Observe LiveData from ViewModel
        mainViewModel.data.observe(this) { dashboardData ->
            Log.d("RESPONSE", dashboardData.data.toString())

            recentLinksList.clear()
            recentLinksList.addAll(dashboardData.data.recent_links)
            topLinksList.clear()
            topLinksList.addAll(dashboardData.data.top_links)

            toplinksAdapter.notifyDataSetChanged()
            recentlinksAdapter.notifyDataSetChanged()
        }


        // Fetch dashboard data
        mainViewModel.fetchDashboardData()

    }





}
