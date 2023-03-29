package com.faithdeveloper.harry.retrofit

import com.faithdeveloper.harry.model.Characters
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("characters")
    suspend fun getAllCharacters():List<Characters>

    @GET("house/{house}")
    suspend fun getCharactersByHouse(
        @Path("house") house:String
    ):List<Characters>

}