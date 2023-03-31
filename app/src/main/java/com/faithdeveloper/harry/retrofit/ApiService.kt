package com.faithdeveloper.harry.retrofit

import com.faithdeveloper.harry.model.HarryCharacter
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("characters")
    suspend fun getAllCharacters():List<HarryCharacter>

    @GET("characters/house/{house}")
    suspend fun getCharactersByHouse(
        @Path("house") house:String
    ):List<HarryCharacter>

}