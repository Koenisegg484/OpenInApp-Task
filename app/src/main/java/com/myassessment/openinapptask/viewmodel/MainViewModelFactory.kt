package com.myassessment.openinapptask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myassessment.openinapptask.dashboardrepository.DashboardRepository

class MainViewModelFactory(val repository: DashboardRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}