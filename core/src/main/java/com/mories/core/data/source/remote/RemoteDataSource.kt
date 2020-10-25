package com.mories.core.data.source.remote

import android.util.Log
import com.mories.core.BuildConfig
import com.mories.core.data.source.remote.network.ApiResponse
import com.mories.core.data.source.remote.network.ApiService
import com.mories.core.data.source.remote.response.ResponseMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllFromRemote(): Flow<ApiResponse<List<ResponseMovie>>> {
        return flow {
            try {
                val response = apiService.getListMovie(BuildConfig.API_KEY)
                val dataArray = response.results
                if (!dataArray.isNullOrEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}