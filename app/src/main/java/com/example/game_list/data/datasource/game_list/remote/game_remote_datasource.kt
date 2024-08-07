package com.example.game_list.data.datasource.game_list.remote

import com.example.game_list.service.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class GameRemoteDataSource{
    abstract fun makeRetrofitSevice(): ApiGameService
}

object GameRemoteDataSourceImp : GameRemoteDataSource() {
    override fun makeRetrofitSevice(): ApiGameService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiGameService::class.java)
    }
}