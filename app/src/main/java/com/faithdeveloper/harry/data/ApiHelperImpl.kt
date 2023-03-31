package com.faithdeveloper.harry.data

import com.faithdeveloper.harry.model.HarryCharacter
import com.faithdeveloper.harry.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getAllCharacters(): Flow<Result<List<HarryCharacter>>> = flow {
        emit(Result.Success(apiService.getAllCharacters()))
    }

    override suspend fun getCharactersByHouse(house: String) = flow {
        emit(Result.Success(apiService.getCharactersByHouse(house)))
    }

    override suspend fun getCharactersByName(name: String) = flow {
        emit(
            Result.Success(apiService.getAllCharacters().filter {
                it.name.contains(name)
            })
        )
    }
}