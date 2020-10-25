package com.mories.core.data.source.remote.network

import com.mories.core.data.source.remote.response.BaseResponse
import com.mories.core.data.source.remote.response.ResponseMovie
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getListMovie(
        @Query("api_key") apiKey: String
    ): BaseResponse<List<ResponseMovie>>
}