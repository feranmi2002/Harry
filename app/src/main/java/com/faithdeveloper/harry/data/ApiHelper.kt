package com.faithdeveloper.harry.data

import com.faithdeveloper.harry.data.Result
import com.faithdeveloper.harry.model.HarryCharacter
import com.faithdeveloper.harry.retrofit.ApiService
import kotlinx.coroutines.flow.Flow

interface ApiHelper {
    suspend fun getAllCharacters(): Flow<Result<List<HarryCharacter>>>

    suspend fun getCharactersByHouse(house:String): Flow<Result<List<HarryCharacter>>>

    suspend fun getCharactersByName(name:String): Flow<Result<List<HarryCharacter>>>
}