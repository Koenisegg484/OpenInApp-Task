package com.myassessment.openinapptask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myassessment.openinapptask.dashboardrepository.DashboardRepository
import com.myassessment.openinapptask.models.DashboardNewData
import kotlinx.coroutines.launch

class MainViewModel(private val repository: DashboardRepository) : ViewModel() {

    val data: LiveData<DashboardNewData>
        get() = repository.dashLiveData

    fun fetchDashboardData() {
        viewModelScope.launch {
            repository.getDashboardData()
        }
    }
}
