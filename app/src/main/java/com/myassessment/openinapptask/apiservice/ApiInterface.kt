package com.myassessment.openinapptask.apiservice

import com.myassessment.openinapptask.models.DashboardNewData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiInterface {
    @GET("dashboardNew")
    suspend fun getDashboardData(
        @Header("Authorization") authToken: String
    ):Response<DashboardNewData>
}