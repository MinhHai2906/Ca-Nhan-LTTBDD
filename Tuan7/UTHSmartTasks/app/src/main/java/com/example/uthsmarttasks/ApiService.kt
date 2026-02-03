package com.example.uthsmarttasks

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    
    @GET("tasks")
    suspend fun getTasks(): ApiResponse

    @GET("task/{id}")
    suspend fun getTaskDetail(@Path("id") id: Int): TaskDetailResponse

    @DELETE("task/{id}")
    suspend fun deleteTask(@Path("id") id: Int): TaskDetailResponse
}

object RetrofitClient {
    private const val BASE_URL = "https://amock.io/api/researchUTH/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
