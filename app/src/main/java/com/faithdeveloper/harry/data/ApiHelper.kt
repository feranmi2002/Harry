package com.faithdeveloper.harry.data

import kotlinx.coroutines.flow.Flow

interface ApiHelper {
    suspend fun getAllCharacters(): Flow<ApiResult>

    suspend fun getCharactersByHouse(house: String): Flow<ApiResult>

    suspend fun getCharactersByName(name: String): Flow<ApiResult>
}