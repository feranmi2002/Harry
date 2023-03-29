package com.faithdeveloper.harry.data

import com.faithdeveloper.harry.model.Characters
import com.faithdeveloper.harry.retrofit.ApiService

class ApiHelper(private val apiService: ApiService) {
    suspend fun getAllCharacters():List<Characters>{
        return apiService.getAllCharacters()
    }

    suspend fun getCharactersByHouse(house:String):List<Characters>{
        return apiService.getCharactersByHouse(house)
    }
}