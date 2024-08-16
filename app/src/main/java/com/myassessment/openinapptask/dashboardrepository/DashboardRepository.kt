package com.myassessment.openinapptask.dashboardrepository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.myassessment.openinapptask.apiservice.ApiInterface
import com.myassessment.openinapptask.models.DashboardNewData
import retrofit2.Response

class DashboardRepository(private val apiService: ApiInterface) {

    val btoken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI"

    private val dashMutableLiveData = MutableLiveData<DashboardNewData>()
    val dashLiveData: LiveData<DashboardNewData>
        get() = dashMutableLiveData

    suspend fun getDashboardData() {
        try {
            Log.d("Token", btoken)
            val result: Response<DashboardNewData> = apiService.getDashboardData("Bearer $btoken")

            if (result.isSuccessful && result.body() != null) {
                dashMutableLiveData.postValue(result.body())
                Log.d("API_SUCCESS", "Data: ${result.body()}")
            } else {
                Log.e("API_ERROR", "Failed to fetch data: ${result.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            Log.e("API_EXCEPTION", "Exception occurred: ${e.message}")
        }
    }
}
